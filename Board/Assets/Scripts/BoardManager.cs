using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.Threading;
using System;

public class BoardManager : ExtendedBehavior
{
    public REntity[,] Rentities { set; get; }
    [NonSerializedAttribute]
    public REntity selectedEntity;

    private const float TILE_SIZE = 1.0f;
    private const float TILE_OFFSET = 0.5f;

    public int FIELD_SIZE_X;
    public int FIELD_SIZE_Y;

    private int selectionX;
    private int selectionY;

    public List<REntity> entities;

    public GameObject indicatorButton;
    public GameObject plane;

    public Command downCommand;
    public Command leftCommand;
    public Command rightCommand;
    public Command upCommand;
    public Command stayCommand;
    public Command genCommand;

    private bool blockInput = false;

    private List<GameObject> currentCommands = new List<GameObject>();

    private void Start()
    {
        plane.transform.position = new Vector3(FIELD_SIZE_X / 2, 0, FIELD_SIZE_Y / 2);
        plane.transform.localScale = new Vector3(FIELD_SIZE_X / 10f, 1, FIELD_SIZE_Y / 10f);
        Rentities = new REntity[FIELD_SIZE_X, FIELD_SIZE_Y];

        foreach (REntity re in entities)
        {
            int x = (int)re.transform.position.x;
            int y = (int)re.transform.position.z;

            re.SetPosition(x, y);
            Rentities[x, y] = re;
        }
    }

    private void Update()
    {
        UpdateSelection();

        if (!blockInput && Input.GetMouseButtonDown(0))
        {
            if (selectionX >= 0 && selectionY >= 0)
            {
                if (Rentities[selectionX, selectionY] != null && Rentities[selectionX, selectionY].isSelectable)
                {
                    SelectEntity(selectionX, selectionY);
                }
                else if(selectedEntity != null)
                {
                    MoveSelectedEntity(selectionX, selectionY);
                }
            }
        }
    }

    public bool IsInputBlocked()
    {
        return blockInput;
    }

    private void Deselect()
    {
        selectedEntity = null;
        foreach (GameObject go in currentCommands)
        {
            Destroy(go);
        }
        currentCommands.Clear();
        SetIndicatorButtonText("None");
    }

    public void Reselect()
    {
        SelectEntity(selectedEntity.CurrentX, selectedEntity.CurrentY);
    }

    private void SelectEntity(int x, int y)
    {
        if (Rentities[x, y] == null || Rentities[x, y].isSelectable == false)
            return;
        selectedEntity = Rentities[x, y];
        SetIndicatorButtonText(Rentities[x, y].entityName);

        foreach (GameObject go in currentCommands)
        {
            Destroy(go);
        }
        currentCommands.Clear();


        for (int i = 0; i < selectedEntity.commands.Count; ++i)
        {
            GameObject btn = Instantiate(indicatorButton, new Vector3(0, 0, 0), Quaternion.identity) as GameObject;
            currentCommands.Add(btn);
        }
        foreach (GameObject btn in currentCommands)
        {
            int i = currentCommands.IndexOf(btn);
            btn.transform.SetParent(indicatorButton.transform.parent.transform);
            btn.transform.position = indicatorButton.transform.position;
            btn.transform.rotation = indicatorButton.transform.rotation;
            btn.GetComponent<RectTransform>().localScale = indicatorButton.GetComponent<RectTransform>().localScale;
            btn.GetComponent<RectTransform>().position = indicatorButton.GetComponent<RectTransform>().position;

            Vector3 up = btn.GetComponent<RectTransform>().up;
            Vector3 right = btn.GetComponent<RectTransform>().right;

            btn.GetComponent<RectTransform>().position += up * (-55f - ((i / 3) * 10));
            btn.GetComponent<RectTransform>().position += right * (-15f + ((i % 3) * 10));
            btn.GetComponent<RectTransform>().sizeDelta = new Vector2(30, 30);
            btn.name = "Instruction";
            btn.GetComponentsInChildren<Text>()[0].text = selectedEntity.commands[i].getName();

            btn.GetComponent<Button>().onClick.AddListener(() => {
                selectedEntity.commands.RemoveAt(i);
                Reselect();
            });
        }
    }

    private void MoveSelectedEntity(int x, int y)
    {
        MoveEntityIfPossible(selectedEntity, x, y);
    }

    bool IsBoardCoordinates(int x, int y)
    {
        return 0 <= x && x < FIELD_SIZE_X && 0 <= y && y < FIELD_SIZE_Y;
    }

    public void MoveEntityIfPossible(REntity re, int x, int y)
    {
        if (!IsBoardCoordinates(x, y))
            return;

        if (Rentities[x, y] != null)
        {
            if (Rentities[x, y].isMovable)
            {
                REntity obstacle = Rentities[x, y];
                int xDest = x + x - re.CurrentX;
                int yDest = y + y - re.CurrentY;

                if (!IsBoardCoordinates(xDest, yDest))
                    return;
                if (Rentities[xDest, yDest] == null)
                {
                    MoveEntity(obstacle, xDest, yDest);
                    MoveEntity(re, x, y);
                }
            }
            return;
        }

        if (re.PossibleMove(x, y))
        {
            MoveEntity(re, x, y);
        }

    }

    void MoveEntity(REntity re, int x, int y)
    {
        Rentities[re.CurrentX, re.CurrentY] = null;
        re.transform.position = GetTileCenter(x, y);
        re.SetPosition(x, y);
        Rentities[x, y] = re;
    }

    private void UpdateSelection()
    {
        if (!Camera.main)
            return;

        RaycastHit hit;
        if (Physics.Raycast(Camera.main.ScreenPointToRay(Input.mousePosition), out hit, 50.0f, LayerMask.GetMask("Plane")))
        {
            selectionX = (int)hit.point.x;
            selectionY = (int)hit.point.z;
        }
        else
        {
            selectionX = -1;
            selectionY = -1;
        }
    }

    private Vector3 GetTileCenter(int x, int y)
    {
        Vector3 origin = Vector3.zero;
        origin.x += (TILE_SIZE * x);
        origin.z += (TILE_SIZE * y);
        return origin;
    }


    public void Simulate()
    {
        Deselect();
        blockInput = true;
        selectedEntity = null;
        SetIndicatorButtonText("Going");
        DoStep();
    }

    public void DoStep()
    {
        bool result = false;
        foreach (REntity re in entities)
        {
            if (re.commands.Count > 0)
            {
                re.commands[0].DoSelf(this, re);
                result = true;
            }
        }
        if (result)
            Wait(0.5f, () => DoStep());
        else
        {
            SetIndicatorButtonText("Done");
            blockInput = false;
        }
    }

    public void SetIndicatorButtonText(string newText)
    {
        indicatorButton.GetComponentsInChildren<Text>()[0].text = newText;
    }
}
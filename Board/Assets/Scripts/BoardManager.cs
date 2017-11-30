using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.Threading;
using System;

public class BoardManager : ExtendedBehavior
{    
    public REntity[,] Rentities { set; get; }
    private REntity selectedEntity;

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
    private bool genSet = false;

    private List<GameObject> currentCommands = new List<GameObject>();
    private List<Command> genCommands = new List<Command>();

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
        // DrawChessboard();

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

    private void Deselect()
    {
        selectedEntity = null;
        foreach (GameObject go in currentCommands)
        {
            Destroy(go);
        }
        currentCommands.Clear();
        indicatorButton.GetComponentsInChildren<Text>()[0].text = "None";
    }

    private void Reselect()
    {
        SelectEntity(selectedEntity.CurrentX, selectedEntity.CurrentY);
    }

    private void SelectEntity(int x, int y)
    {
        if (Rentities[x, y] == null || Rentities[x, y].isSelectable == false)
            return;
        selectedEntity = Rentities[x, y];
        indicatorButton.GetComponentsInChildren<Text>()[0].text = Rentities[x, y].entityName;

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
        Debug.Log("Created");
    }

    private void MoveSelectedEntity(int x, int y)
    {
        MoveEntity(selectedEntity, x, y);
    }

    public void MoveEntity(REntity re, int x, int y)
    {
        if (x < 0 || x >= FIELD_SIZE_X || y < 0 || y >= FIELD_SIZE_Y)
            return;

        if (Rentities[x, y] != null)
        {

            if (Rentities[x, y].isMovable)
            {
                REntity obstacle = Rentities[x, y];
                int xDest = x + x - re.CurrentX;
                int yDest = y + y - re.CurrentY;

                if (xDest < 0 || xDest >= FIELD_SIZE_X || yDest < 0 || yDest >= FIELD_SIZE_Y)
                    return;
                if (Rentities[xDest, yDest] == null)
                {
                    obstacle.transform.position = GetTileCenter(xDest, yDest);
                    obstacle.SetPosition(xDest, yDest);
                    Rentities[xDest, yDest] = obstacle;

                    Rentities[re.CurrentX, re.CurrentY] = null;
                    re.transform.position = GetTileCenter(x, y);
                    re.SetPosition(x, y);
                    Rentities[x, y] = re;
                }
            }
            return;
        }

        if (re.PossibleMove(x, y))
        {
            Rentities[re.CurrentX, re.CurrentY] = null;
            re.transform.position = GetTileCenter(x, y);
            re.SetPosition(x, y);
            Rentities[x, y] = re;
        }

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

    private void DrawChessboard()
    {
        Vector3 widthLine = Vector3.right * FIELD_SIZE_X;
        Vector3 lengthLine = Vector3.forward * FIELD_SIZE_Y;

        for (int i = 0; i < FIELD_SIZE_X + 1; ++i)
        {
            Debug.DrawLine(Vector3.right * i, Vector3.right * i + lengthLine);
        }
        for (int i = 0; i < FIELD_SIZE_Y + 1; ++i)
        {
            Debug.DrawLine(Vector3.forward * i, Vector3.forward * i + widthLine);
        }

        if (selectionX >= 0 && selectionY >= 0)
        {
            Debug.DrawLine(Vector3.forward * selectionY + Vector3.right * selectionX,
                Vector3.forward * (selectionY + 1) + Vector3.right * (selectionX + 1));
            Debug.DrawLine(Vector3.forward * (selectionY + 1) + Vector3.right * selectionX,
                Vector3.forward * selectionY + Vector3.right * (selectionX + 1));
        }
    }

    private Vector3 GetTileCenter(int x, int y)
    {
        Vector3 origin = Vector3.zero;
        origin.x += (TILE_SIZE * x);
        origin.z += (TILE_SIZE * y);
        return origin;
    }

    public void DownButtonPressed()
    {
        if (!blockInput && selectedEntity != null)
        {
            if (genSet == true)
            {
                genCommands.Add(downCommand);
            }
            else
            {
                selectedEntity.commands.Add(downCommand);
                Reselect();
            }
        }
    }

    public void LeftButtonPressed()
    {
        if (!blockInput && selectedEntity != null)
        {
            if (genSet == true)
            {
                genCommands.Add(leftCommand);
            }
            else
            {
                selectedEntity.commands.Add(leftCommand);
                Reselect();
            }
        }
    }

    public void RightButtonPressed()
    {
        if (!blockInput && selectedEntity != null)
        {
            if (genSet == true)
            {
                genCommands.Add(rightCommand);
            }
            else
            {
                selectedEntity.commands.Add(rightCommand);
                Reselect();
            }
        }
    }

    public void UpButtonPressed() {
        if (!blockInput && selectedEntity != null)
        {
            if(genSet == true)
            {
                genCommands.Add(upCommand);
            }
            else {
                selectedEntity.commands.Add(upCommand);
                Reselect();
            }
        }
    }

    public void StayButtonPressed()
    {
        if (!blockInput && selectedEntity != null)
        {
            if (genSet == true)
            {
                genCommands.Add(stayCommand);
            }
            else {
                selectedEntity.commands.Add(stayCommand);
                Reselect();
            }
        }
    }

    public void GenButtonPressed()
    {
        if (!blockInput && selectedEntity != null)
        {
            if (genSet == true)
            {
                GenCommand gen = new GenCommand();
                foreach (Command com in genCommands)
                {
                    gen.AddSubCommand(com);
                }
                genCommands.Clear();
                genSet = false;
                selectedEntity.commands.Add(gen);
                Reselect();
            }
            else {
                genSet = true;
            }
            //selectedEntity.commands.Add(genCommand);


            Reselect();
        }
    }

    public void Simulate()
    {
        Deselect();
        blockInput = true;
        selectedEntity = null;
        indicatorButton.GetComponentsInChildren<Text>()[0].text = "Going";
        DoStep();
    }

    public void DoStep()
    {
        bool result = false;
        foreach (REntity re in entities)
        {
            if (re.commands.Count > 0)
            {
                if (re.commands[0].getName() == "Gen")
                {
                    re.commands[0].DoSelf(this, re);
                    if (((GenCommand)re.commands[0]).Empty())
                    {
                        re.commands.RemoveAt(0);
                    }
                }
                else
                {
                    re.commands[0].DoSelf(this, re);
                    re.commands.RemoveAt(0);
                }
                result = true;
            }
        }
        if (result)
            Wait(0.5f, () => DoStep());
        else
        {
            indicatorButton.GetComponentsInChildren<Text>()[0].text = "None";
            blockInput = false;
        }
    }
}

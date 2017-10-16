using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BoardManager : MonoBehaviour
{
    public REntity[,] rentities { set; get; }
    private REntity selectedEntity;

    private const float TILE_SIZE = 1.0f;
    private const float TILE_OFFSET = 0.5f;

    private int selectionX;
    private int selectionY;

    public List<GameObject> entities;
    private List<GameObject> activeEntities = new List<GameObject>();

    private void Start()
    {
        rentities = new REntity[8, 8];
        SpawnEntity(0, 2, 3);
    }

    private void Update()
    {
        UpdateSelection();
        DrawChessboard();

        if (Input.GetMouseButtonDown(0))
        {
            if (selectionX >= 0 && selectionY >= 0)
            {
                if (selectedEntity == null)
                {
                    SelectEntity(selectionX, selectionY);
                }
                else
                {
                    MoveEntity(selectionX, selectionY);
                }
            }
        }
    }

    private void SelectEntity(int x, int y)
    {
        if (rentities[x, y] == null)
            return;
        selectedEntity = rentities[x, y];
    }

    private void MoveEntity(int x, int y)
    {
        if (selectedEntity.PossibleMove(x, y))
        {
            rentities[selectedEntity.CurrentX, selectedEntity.CurrentY] = null;
            selectedEntity.transform.position = GetTileCenter(x, y);
            rentities[x, y] = selectedEntity;
        }
        selectedEntity = null; 
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
        Vector3 widthLine = Vector3.right * 8;
        Vector3 lengthLine = Vector3.forward * 8;

        for (int i = 0; i < 9; ++i)
        {
            Debug.DrawLine(Vector3.forward * i, Vector3.forward * i + widthLine);
        }
        for (int i = 0; i < 9; ++i)
        {
            Debug.DrawLine(Vector3.right * i, Vector3.right * i + lengthLine);
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

    private void SpawnEntity(int index, int x, int y)
    {
        GameObject go = Instantiate(entities[index], GetTileCenter(2,3), Quaternion.identity) as GameObject;
        rentities[x, y] = go.GetComponent<REntity>();
        rentities[x, y].setPosition(x, y);
        activeEntities.Add(go);
    }
}

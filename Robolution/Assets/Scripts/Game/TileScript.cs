using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class TileScript : MonoBehaviour
{

    public Point GridPosition { get; private set; }

    private Color32 fullColor = new Color32(255, 118, 118, 255);

    private Color32 emptyColor = new Color32(96,255,90,255);
    

    public bool IsEmpty { get; private set; }

    private SpriteRenderer SpriteRenderer;

    public Vector2 WolrdPosition
    {
        get
        {
            return new Vector2(transform.position.x + (GetComponent<SpriteRenderer>().bounds.size.x / 2),
                transform.position.y - (GetComponent<SpriteRenderer>().bounds.size.y / 2));
        }
    }


    // Use this for initialization
    void Start()
    {
        SpriteRenderer = GetComponent<SpriteRenderer>();
    }

    // Update is called once per frame
    void Update()
    {

    }

    public void Setup(Point gridPos, Vector3 worldPos, Transform parant)
    {
        IsEmpty = true;
        this.GridPosition = gridPos;
        transform.position = worldPos;

        transform.SetParent(parant);

        LevelManager.Instance.Tiles.Add(gridPos, this);
    }

    private void OnMouseOver()
    {
        if (!EventSystem.current.IsPointerOverGameObject() && GamesManager.Instance.ClickedBtn != null)
        {
            if (IsEmpty)
            {
                ColorTile(emptyColor);
            }
            if (!IsEmpty)
            {
                ColorTile(fullColor);
            }
            else if (Input.GetMouseButtonDown(0)) {
                    PlaceObject();
            }

        }
        else if(GamesManager.Instance.ClickedBtn == null)
        {
            ColorTile(Color.white);
        }
        
    }

    private void PlaceObject()
    {
        GameObject newObject = (GameObject)Instantiate(GamesManager.Instance.ClickedBtn.ObjectPrefab, 
            new Vector2(transform.position.x + (GetComponent<SpriteRenderer>().bounds.size.x / 2), transform.position.y), Quaternion.identity);

        newObject.GetComponent<SpriteRenderer>().sortingOrder = GridPosition.Y;

        newObject.transform.SetParent(transform);

        IsEmpty = false;

        ColorTile(Color.white);

        GamesManager.Instance.ButObj();

        Character character = newObject.GetComponent<Character>();
        character.GridPosition = GridPosition;
        GamesManager.Instance.AddCharacter(character);
    }

    public void PlaceObject(GameObject newObject)
    {
        newObject.transform.position = new Vector3(transform.position.x + (GetComponent<SpriteRenderer>().bounds.size.x / 2), transform.position.y,0);

        newObject.GetComponent<SpriteRenderer>().sortingOrder = GridPosition.Y;

        newObject.transform.SetParent(transform);
        IsEmpty = false;
    }

    public void RemoveObject()
    {
        IsEmpty = true;
        ColorTile(Color.white);
    }

    private void OnMouseExit()
    {
        ColorTile(Color.white);
    }


    private void ColorTile(Color32 newColor)
    {
        SpriteRenderer.color = newColor;
    }
}

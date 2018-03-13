using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GamesManager : Singleton<GamesManager> {

    public ObjectBtn ClickedBtn { get; private set; }

    public Character ClickedChar { get; private set; }

    [SerializeField]
    private GameObject[] panels;


    /// <summary>
    /// Need change!!! Debug only!!!!
    /// </summary>
    [SerializeField]
    private ObjectBtn[] stepPrefabs;

   // public Dictionary<Point, ObjectBtn> Tiles { get; set; }


    List<Character> objectList = new List<Character>();

    // Use this for initialization
    void Start()
    {
        ClickedBtn = null;
    }

    // Update is called once per frame
    void Update()
    {
        wasdHandle();
        StoryActionHandler();
        HandleEscape();
    }

    public void PickObjectOnPanal(ObjectBtn obj)
    {
        this.ClickedBtn = obj;
        Hover.Instance.Activate(obj.Sprite);
    }

    public void ButObj()
    {
        Hover.Instance.Deactivate();
        ClickedBtn = null;
    }

    public void ClickObjOnMap(int sel, Character clickChar)
    {
        List<int> list = new List<int>()
        {
            1,2
        };

        Clear(panels[1].transform);
        SelectPanal(list);
        ClickedChar = clickChar;

        for (int i = ClickedChar.CurrentAcction; i < ClickedChar.Actions.Count; i++)
        {
            AddObjToPanal(ClickedChar.Actions[i].ListImgPosition);
        }
    }

    public void SelectPanal(int sel)
    {
        foreach (var panel in panels)
        {
            panel.SetActive(false);
        }
        panels[sel].SetActive(true);
    }

    public void SelectPanal(List<int> list)
    {
        foreach (var panel in panels)
        {
            panel.SetActive(false);
        }
        foreach (var item in list)
        {
            panels[item].SetActive(true);
        }
    }


    /// <summary>
    /// Need recoding! 
    /// </summary>
    /// <param name="listPicPosition"></param>
    public void AddObjToPanal(int listPicPosition)
    {
        ObjectBtn newObject = (ObjectBtn)Instantiate(stepPrefabs[listPicPosition], panels[1].transform);
    }

    private void HandleEscape()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            SelectPanal(0);
            Clear(panels[1].transform);
            ClickedChar = null;
            ButObj();
        }
    }

    private void wasdHandle()
    {
        ///
        /// THIS IS DEBUG ONLY
        ///
        if (panels[1].activeSelf && this.ClickedChar != null)
        {
            if (Input.GetKeyDown(KeyCode.W))
            {
                AddObjToPanal(0);
                ClickedChar.AddStep(new Action(0,new Point(0,-1)));
            }
            if (Input.GetKeyDown(KeyCode.S))
            {
                AddObjToPanal(1);
                ClickedChar.AddStep(new Action(1, new Point(0, 1)));
            }
            if (Input.GetKeyDown(KeyCode.D))
            {
                AddObjToPanal(3);
                ClickedChar.AddStep(new Action(3, new Point(1, 0)));
            }
            if (Input.GetKeyDown(KeyCode.A))
            {
                AddObjToPanal(2);
                ClickedChar.AddStep(new Action(2, new Point(-1, 0)));
            }
        }
        ///
        /// END DEBUG ONLY
        ///
    }

    public void AddAction(Action act)
    {
        if(ClickedChar != null)
        {
            AddObjToPanal(act.ListImgPosition);
            ClickedChar.AddStep(act);
        }
    }

    private void StoryActionHandler()
    {
        if (Input.GetKeyDown(KeyCode.RightArrow))
        {
            foreach (var obj in objectList)
            {
                if (obj.CurrentAcction < obj.Actions.Count && obj.CurrentAcction > -1)
                {
                    LevelManager.Instance.Tiles[obj.GridPosition].RemoveObject();

                    obj.GridPosition = new Point(obj.GridPosition.X + obj.Actions[obj.CurrentAcction].action.X, 
                        obj.GridPosition.Y + obj.Actions[obj.CurrentAcction].action.Y);

                    obj.CurrentAcction += 1;
                    LevelManager.Instance.Tiles[obj.GridPosition].PlaceObject(obj.gameObject);
                }

                if (panels[1].activeSelf)
                {
                    ClickObjOnMap(1,ClickedChar);
                }
            }
        }
        if (Input.GetKeyDown(KeyCode.LeftArrow))
        {
            foreach (var obj in objectList)
            {
                if (obj.CurrentAcction <= obj.Actions.Count && obj.CurrentAcction > 0)
                {
                    LevelManager.Instance.Tiles[obj.GridPosition].RemoveObject();

                    obj.CurrentAcction -= 1;

                    obj.GridPosition = new Point(obj.GridPosition.X + obj.Actions[obj.CurrentAcction].action.X*(-1),
                        obj.GridPosition.Y + obj.Actions[obj.CurrentAcction].action.Y * (-1));

                 
                    LevelManager.Instance.Tiles[obj.GridPosition].PlaceObject(obj.gameObject);
                }

                if (panels[1].activeSelf)
                {
                    ClickObjOnMap(1, ClickedChar);
                }

            }

        }
    }

    public void AddCharacter(Character newChar)
    {
        objectList.Add(newChar);
    }

    private Transform Clear(Transform transform)
    {
        foreach (Transform child in transform)
        {
            GameObject.Destroy(child.gameObject);
        }
        return transform;
    }
}

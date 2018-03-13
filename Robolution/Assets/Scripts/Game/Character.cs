using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Character : MonoBehaviour {

    public Point GridPosition { get; set; }

    public int CurrentAcction { get; set; }

    private List<Action> actions;

    public List<Action> Actions
    {
        get
        {
            return actions;
        }

        private set
        {
            actions = value;
        }
    }

   

    // Use this for initialization
    void Start()
    {
        Actions = new List<Action>();
        CurrentAcction = 0;
    }

    // Update is called once per frame
    void Update()
    {

    }

    private void OnMouseDown()
    {
        Debug.Log("THIS IS DOWN");
        GamesManager.Instance.ClickObjOnMap(1, this);
        //GamesManager.Instance.AddObjToPanal(1);
    }

    public void AddStep(Action newStep)
    {
        actions.Add(newStep);
        
    }

}

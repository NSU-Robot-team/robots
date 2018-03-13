using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Action : MonoBehaviour {

    [SerializeField]
    private int actionX;

    [SerializeField]
    private int actionY;

    [SerializeField]
    private int listImgPosition;

    public int ListImgPosition
    {
        get
        {
            return listImgPosition;
        }

        private set
        {
            listImgPosition = value;
        }
    }

    public Point action { get; private set; }
    

    public Action(int pos, Point act)
    {
        ListImgPosition = pos;
        action = act;
    }

    // Use this for initialization
    void Start()
    {
        action = new Point(actionX, actionY);
    }
}

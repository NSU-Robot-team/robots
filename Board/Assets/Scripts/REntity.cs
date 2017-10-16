using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public abstract class REntity : MonoBehaviour
{
    public int CurrentX { set; get; }
    public int CurrentY { set; get; }
    public bool isRobot;

    public virtual bool PossibleMove(int x, int y)
    {
        return true;
    }

    public void setPosition(int x, int y)
    {
        CurrentX = x;
        CurrentY = y;
    }
}

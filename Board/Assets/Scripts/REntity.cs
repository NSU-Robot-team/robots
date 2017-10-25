using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public abstract class REntity : MonoBehaviour
{
    public int CurrentX { set; get; }
    public int CurrentY { set; get; }

    public string entityName;
    public bool isSelectable = true;
    public bool isMovable = false;

    public List<Command> commands = new List<Command>();
   
    public virtual bool PossibleMove(int x, int y)
    {
        return true;
    }

    public void SetPosition(int x, int y)
    {
        CurrentX = x;
        CurrentY = y;
    }

}

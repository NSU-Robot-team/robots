using UnityEngine;
using System.Collections;

public class GoUpCommand : Command
{
    public override string getName()
    {
        return "↑";
    }

    public override void DoSelf(BoardManager manager, REntity re)
    {
        manager.MoveEntity(re, re.CurrentX, re.CurrentY + 1);
    }
}

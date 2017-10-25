using UnityEngine;
using System.Collections;

public class GoRightCommand : Command
{
    public override string getName()
    {
        return "→";
    }

    public override void DoSelf(BoardManager manager, REntity re)
    {
        manager.MoveEntity(re, re.CurrentX + 1, re.CurrentY);
    }
}

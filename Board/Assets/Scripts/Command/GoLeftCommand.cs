using UnityEngine;
using System.Collections;

public class GoLeftCommand : Command
{

    public static string name = "←";

    public override string getName()
    {
        return name;
    }

    public override void DoSelf(BoardManager manager, REntity re)
    {
        manager.MoveEntity(re, re.CurrentX - 1, re.CurrentY);
    }
}

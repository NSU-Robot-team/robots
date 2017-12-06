using UnityEngine;
using System.Collections;

public class GoDownCommand : Command
{
    public static string commandName = "↓";

    public override string getName()
    {
        return commandName;
    }

    public override void DoSelf(BoardManager manager, REntity re)
    {
        manager.MoveEntityIfPossible(re, re.CurrentX, re.CurrentY - 1);
        re.commands.RemoveAt(0);
    }
}

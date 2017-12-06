using UnityEngine;
using System.Collections;

public class GoRightCommand : Command
{
    public static string commandName = "→";

    public override string getName()
    {
        return commandName;
    }

    public override void DoSelf(BoardManager manager, REntity re)
    {
        manager.MoveEntityIfPossible(re, re.CurrentX + 1, re.CurrentY);
        re.commands.RemoveAt(0);
    }
}

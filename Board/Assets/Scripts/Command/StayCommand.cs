using UnityEngine;
using System.Collections.Generic;

public class StayCommand : Command
{
    public static string commandName = "•";

    public override string getName()
    {
        return commandName;
    }

    public override void DoSelf(BoardManager manager, REntity re)
    {
        re.commands.RemoveAt(0);
    }
}

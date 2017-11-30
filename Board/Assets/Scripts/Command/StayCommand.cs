using UnityEngine;
using System.Collections.Generic;
public class StayCommand : Command
{
    public static string name = "•";

    private List<GameObject> currentCommands = new List<GameObject>();
    public override string getName()
    {
        return name;
    }

    public override void DoSelf(BoardManager manager, REntity re)
    {
    }
}

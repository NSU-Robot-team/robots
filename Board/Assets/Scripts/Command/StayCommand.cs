using UnityEngine;
using System.Collections.Generic;
public class StayCommand : Command
{
    private List<GameObject> currentCommands = new List<GameObject>();
    public override string getName()
    {
        return "•";
    }

    public override void DoSelf(BoardManager manager, REntity re)
    {
    }
}

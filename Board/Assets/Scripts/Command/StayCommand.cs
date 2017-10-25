using UnityEngine;
using System.Collections;

public class StayCommand : Command
{
    public override string getName()
    {
        return "•";
    }

    public override void DoSelf(BoardManager manager, REntity re)
    {
    }
}

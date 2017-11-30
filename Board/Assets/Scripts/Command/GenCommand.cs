using UnityEngine;
using System.Collections.Generic;

public class GenCommand : Command
{
    public string name = "Gen";

    private List<Command> currentCommands = new List<Command>();

    public void setName(string name1)
    {
        name = name1;
    }

    public override string getName()
    {
        return name;
    }

    public override void DoSelf(BoardManager manager, REntity re)
    {
        currentCommands[0].DoSelf(manager, re);
        currentCommands.RemoveAt(0);
    }

    public void AddSubCommand(Command com)
    {
        currentCommands.Add(com);
    }

    public bool Empty()
    {
        if(currentCommands.Count == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void ClearCommand()
    {
        currentCommands.Clear();
    }
}

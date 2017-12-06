using UnityEngine;
using System.Collections.Generic;

public class GenCommand : Command
{
    public string commandName = "Gen";

    private List<Command> currentCommands;

    public GenCommand(string name, List<Command> commands)
    {
        commandName = name;
        currentCommands = commands;
    }

    public void SetName(string name)
    {
        commandName = name;
    }

    public override string getName()
    {
        return commandName;
    }

    public override void DoSelf(BoardManager manager, REntity re)
    {
        re.commands.RemoveAt(0);
        re.commands.InsertRange(0, currentCommands);
        re.commands[0].DoSelf(manager, re);
        re.commands.RemoveAt(0);
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

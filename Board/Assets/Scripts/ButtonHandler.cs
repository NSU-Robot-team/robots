using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI;

public class ButtonHandler : MonoBehaviour
{
    public BoardManager manager;
    public InputField nameField;

    private List<Command> genCommands;
    private bool genSet = false;

    public void Start()
    {
        nameField.gameObject.SetActive(false);
    }

    public void DownButtonPressed()
    {
        if (!manager.IsInputBlocked() && manager.selectedEntity != null)
        {
            if (genSet == true)
            {
                genCommands.Add(manager.downCommand);
            }
            else
            {
                manager.selectedEntity.commands.Add(manager.downCommand);
                manager.Reselect();
            }
        }
    }

    public void UpButtonPressed()
    {
        if (!manager.IsInputBlocked() && manager.selectedEntity != null)
        {
            if (genSet == true)
            {
                genCommands.Add(manager.upCommand);
            }
            else
            {
                manager.selectedEntity.commands.Add(manager.upCommand);
                manager.Reselect();
            }
        }
    }

    public void LeftButtonPressed()
    {
        if (!manager.IsInputBlocked() && manager.selectedEntity != null)
        {
            if (genSet == true)
            {
                genCommands.Add(manager.leftCommand);
            }
            else
            {
                manager.selectedEntity.commands.Add(manager.leftCommand);
                manager.Reselect();
            }
        }
    }

    public void RightButtonPressed()
    {
        if (!manager.IsInputBlocked() && manager.selectedEntity != null)
        {
            if (genSet == true)
            {
                genCommands.Add(manager.rightCommand);
            }
            else
            {
                manager.selectedEntity.commands.Add(manager.rightCommand);
                manager.Reselect();
            }
        }
    }

    public void StayButtonPressed()
    {
        if (!manager.IsInputBlocked() && manager.selectedEntity != null)
        {
            if (genSet == true)
            {
                genCommands.Add(manager.stayCommand);
            }
            else
            {
                manager.selectedEntity.commands.Add(manager.stayCommand);
                manager.Reselect();
            }
        }
    }

    public void GenButtonPressed()
    {
        if (!manager.IsInputBlocked() && manager.selectedEntity != null)
        {
            if (genSet == true)
            {
                genSet = false;

                nameField.gameObject.SetActive(false);
                nameField.text = "";

                manager.selectedEntity.commands.Add(new GenCommand(nameField.text, genCommands));
                manager.Reselect();

                Debug.Log("You create new command");
            }
            else
            {
                genCommands = new List<Command>();
                nameField.gameObject.SetActive(true);
                genSet = true;
            }
            manager.Reselect();
        }
    }
}

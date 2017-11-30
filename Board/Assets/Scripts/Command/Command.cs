using UnityEngine;
using System.Collections;

public abstract class Command : MonoBehaviour
{

    public static string name;

    public virtual string getName()
    {
        return "N";
    }

    public virtual void DoSelf(BoardManager manager, REntity re) { }
}

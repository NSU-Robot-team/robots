using UnityEngine;
using System.Collections;

public abstract class Command : MonoBehaviour
{
    public virtual string getName()
    {
        return "N";
    }

    public virtual void DoSelf(BoardManager manager, REntity re) { }
}

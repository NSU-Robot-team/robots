using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ObjectBtn : MonoBehaviour {

    [SerializeField]
    private GameObject objectPrefab;

    [SerializeField]
    private Sprite sprite;

    public GameObject ObjectPrefab
    {
        get
        {
            return objectPrefab;
        }
    }

    public Sprite Sprite
    {
        get
        {
            return sprite;
        }
    }

}

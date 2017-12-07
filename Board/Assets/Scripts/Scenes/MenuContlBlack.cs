using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MenuContlBlack : MonoBehaviour {

    public KeyCode levelName;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        if (Input.GetKeyDown(0))
        {
            SceneManager.LoadScene("Levelblack");
            Debug.Log("level = " + "Levelblack");
        }
    }
}

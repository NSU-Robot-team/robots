using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MenuContl : MonoBehaviour {

    public KeyCode moveMenu;

    // Use this for initialization
    void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        if (Input.GetKeyDown(moveMenu))
        {
            SceneManager.LoadScene("LevelMenu");
            Debug.Log("level = " + "LevelMenu");
        }
    }

    public void MonkeyButtonPressed()
    {
        SceneManager.LoadScene("LevelBlue");
        Debug.Log("level = " + "LevelBlue");
    }

    public void GokuButtonPressed()
    {
        
         SceneManager.LoadScene("LevelBlack");
         Debug.Log("next level = " + "LevelBlack");
        
    }
}

using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayMenu : MonoBehaviour {

    private bool state = false;

    public void DifState()
    {
        this.gameObject.SetActive(!state);
        state = !state;
    }

    public void NewGameLoad()
    {
        SceneManager.LoadScene("NewGame");
    }
}

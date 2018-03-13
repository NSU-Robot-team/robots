using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;

public class MainMenu : MonoBehaviour {

    [SerializeField]
    PlayMenu playMenu;

    public void PlayMenu()
    {
        playMenu.DifState();
    }


	public void QuitGame()
    {
        Application.Quit();
    }
}

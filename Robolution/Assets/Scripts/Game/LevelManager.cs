using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LevelManager : Singleton<LevelManager>
{

    [SerializeField]
    private GameObject[] tileList;

    [SerializeField]
    private Transform map;

    public Dictionary<Point, TileScript> Tiles { get; set; }

    public float TileSize
    {
        get
        {
            return tileList[0].GetComponent<SpriteRenderer>().bounds.size.x;
        }
    }




    // Use this for initialization
    void Start()
    {
        CreateLevel();
    }

    // Update is called once per frame
    void Update()
    {

    }

    private void CreateLevel()
    {
        Tiles = new Dictionary<Point, TileScript>();

        string[] mapData = ReadLevelText();
        int mapX = mapData[0].Length;
        int mapY = mapData.Length;


        Vector3 maxTile = Vector3.zero;

        Vector3 worldStart = Camera.main.ScreenToWorldPoint(new Vector3(0, Screen.height));
        for (int y = 0; y < mapY; y++)
        {
            char[] newTiles = mapData[y].ToCharArray();
            for (int x = 0; x < mapX; x++)
            {
                PlaceTile(newTiles[x].ToString(), x, y, worldStart);
            }
        }

    }



    private void PlaceTile(string tileName, int x,int y,Vector3 worldStart)
    {
        int tileIndex = int.Parse(tileName);
        TileScript newTile = Instantiate(tileList[tileIndex]).GetComponent<TileScript>();
        newTile.Setup(new Point(x, y), new Vector3(worldStart.x + TileSize * x, worldStart.y - TileSize * y, 0), map);

    }

    private string[] ReadLevelText()
    {
        TextAsset bindData = Resources.Load("Level1") as TextAsset;

        string data = bindData.text.Replace(Environment.NewLine, string.Empty);

        return data.Split('-');
    }
}

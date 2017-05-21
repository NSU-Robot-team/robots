package com.gmail.libra.demidov.s.Model;

import com.gmail.libra.demidov.s.Object.Heirs.Fork;
import com.gmail.libra.demidov.s.Object.Heirs.Philosopher;
import com.gmail.libra.demidov.s.Object.MyObject;

import java.util.ArrayList;

/**
 * Created by Sersh on 29.04.2017.
 */
public class ObjectModel {
    private ArrayList<MyObject> objectList = new ArrayList<>();

    private int posX = 515;
    private int posY = 240;

    public ObjectModel(ArrayList<MyObject> objectList) {
        this.objectList = objectList;
    }

    public ArrayList<MyObject> getObjectList() {
        return objectList;
    }

    public void setObjectList(ArrayList<MyObject> objectList) {
        this.objectList = objectList;
    }

    public ArrayList<Fork> getForks(){
        ArrayList<Fork> forks = new ArrayList<>();
        for(MyObject object: objectList){
            if(object.getName().equals("Fork")){
                forks.add((Fork)object);
            }
        }
        return forks;
    }

    public ArrayList<Philosopher> getPhilosophers(){
        ArrayList<Philosopher> forks = new ArrayList<>();
        for(MyObject object: objectList){
            if(object.getName().equals("Philosopher")){
                forks.add((Philosopher)object);
            }
        }
        return forks;
    }


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public MyObject getElement(String name){
        for(MyObject object: objectList){
            if(object.getName().equals(name)){
                return object;
            }
        }
        return null;
    }

    public int getCount(String name){
        int n = 0;
        for(MyObject object: objectList){
            if(object.getName().equals(name)){
                n+=1;
            }
        }
        return n;
    }
}

package com.example.user.myapplication.model;

import java.util.Random;

/**
 * Created by user on 26/10/17.
 */

public class Game {


    private int toFind;
    private int counter;
    private int maxValue;

public Game(){}

    public void initGame(int _maxValue){

        this.maxValue = _maxValue;

        Random r = new Random();

        this.counter = 0;
     //   this.comparable = 0;
//        this.toFind = (int) (Math.random() * 100) + 1;
        this.toFind = r.nextInt(_maxValue);
    }

    public void upCounter(){
        this.counter++;
    }

    public int getCounter(){
        return this.counter;
    }

    public int getToFind(){
        return this.toFind;
    }



}

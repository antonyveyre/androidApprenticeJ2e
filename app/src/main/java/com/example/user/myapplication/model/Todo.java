package com.example.user.myapplication.model;

import android.graphics.Color;

/**
 * Created by user on 02/11/17.
 */



public class Todo {

    public enum STATE {
        ACTIVE(Color.RED),
        COMPLETED(Color.BLACK);


        private final int color;

        STATE(int _color) {
            this.color = _color;
        }

        public int getColorState() {
            return color;
        }
    }




    private String action;
    private STATE currentState;

    public Todo(String _action){
        currentState = STATE.ACTIVE;
        this.action = _action;
    }

    public void changeStateB (boolean state){
        if  (state)
            this.currentState = STATE.ACTIVE;
        else
            this.currentState = STATE.COMPLETED ;
    }

    public void changeState (STATE state){
            this.currentState = state;
         }


    public String toString(){

        return this.action + this.currentState;
    }


    public String getAction(){
        return this.action;
    }

    public int getColor(){
        return this.currentState.color;
    }

    public STATE getCurrentState(){
        return this.currentState;
    }

    public boolean getEnabled(){

        return this.getCurrentState().equals(STATE.ACTIVE)?true:false;
    }


}

package com.pillreminder.pillreminder.helper;

public class Singleton {


    private static Singleton instance;

    private String floww ="";
    private Singleton() {
    }

    public static Singleton Instance() {
        //if no instance is initialized yet then create new instance
        //else return stored instance
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static Singleton clearSingleTon() {
        instance = null;
        return null;
    }


    //Clear section

    public void setFlow(String flow){
        floww=flow;
    }
    public String getFlow(){return floww;}

}

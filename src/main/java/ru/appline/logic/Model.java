package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {

    private static  final  Model instance = new Model();

    private final Map <Integer,User> model;

    public static Model getInstance(){
        return instance;
    }

    private Model(){

        model=new HashMap<>();
        model.put(1,new User("sasha","parshukov",20000));
        model.put(2,new User("ivan","ivanov",30000));
        model.put(3,new User("sergey","sergeev",40000));
    }

    public void add(User user,int id){
        model.put(id,user);
    }
    public Map<Integer,User> getFromList(){
        return model;
    }



}

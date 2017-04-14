package com.kevin.inventorymanagement.Class;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Kevin on 4/5/2017.
 */

public class Batik {
    private String code;
    private String name;
    private ArrayList<Long> quantityList;

    public Batik(){};
    //constructor to get view only
    public Batik (String code, String name){
        this.code = code;
        this.name = name;
        this.quantityList = null;
    }
    public Batik(String code, String name, ArrayList<Long> quantityList) {
        this.code = code;
        this.name = name;
        this.quantityList = quantityList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Long> getQuantityList() {
        return quantityList;
    }

    public void setQuantityList(ArrayList<Long> quantityList) {
        this.quantityList = quantityList;
    }
}

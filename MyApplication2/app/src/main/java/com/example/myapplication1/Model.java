package com.example.myapplication1;

public class Model {

    float bmiresult;
    String bmicategory;
    String id;

    public Model(float bmiresult, String bmicategory, String id) {
        this.bmiresult = bmiresult;
        this.bmicategory = bmicategory;
        this.id = id;
    }

    public float getBmiresult() {
        return bmiresult;
    }

    public void setBmiresult(float bmiresult) {
        this.bmiresult = bmiresult;
    }

    public String getBmicategory() {
        return bmicategory;
    }

    public void setBmicategory(String bmicategory) {
        this.bmicategory = bmicategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

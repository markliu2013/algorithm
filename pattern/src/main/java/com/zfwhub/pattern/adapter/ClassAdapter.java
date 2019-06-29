package com.zfwhub.pattern.adapter;

public class ClassAdapter extends Adaptee implements Target {

    @Override
    public void request() {
        specificRequest();
    }

}

package com.android.cglib.sample;

public class Printer {

    private String mContent;

    public Printer(String content) {
        mContent = content;
    }


    public void print() {
        print2();
    }

    private final void print2() {
        Logger.d("Hello, world " + mContent);
    }

}

package com.android.cglib.sample;

import android.util.Log;

public class Printer {

  private String mContent;

  public Printer(String content) {
    mContent = content;
  }


  public void print() {
    print2();
  }

  private final void print2() {
    Log.i("MyProxy", "Hello, world " + mContent);
  }

}

package com.batis.library.clib;

public class HelloWorld {
    public native void print();

    static {
        System.loadLibrary("clink");
    }
}

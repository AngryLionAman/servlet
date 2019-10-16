package com.test;


import java.util.Map;
import java.util.Random;


class Wrapper {

    public static void main(String args[]) throws Exception {
        Map map = System.getProperties();
        Random r = new Random();
        while (true) {
            map.put(r.nextInt(), "value");
        }
    }
}

package com.cloud.online.markdown.dao;

public class AppTest {

    public static void main(String[] args) {
        A a = new A();

        switch (a.getB()) {
            case RED:
                System.out.println("red");
                break;
            case GREEN:
                System.out.println("green");
                break;
            default:
                System.out.println("nothing");

        }
    }

}

class A {
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}

enum B {
    RED,
    GREEN
}

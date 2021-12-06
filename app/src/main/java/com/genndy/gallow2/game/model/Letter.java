package com.genndy.gallow2.game.model;

public class Letter {
    public final static int DEFAULT = 0;
    public final static int RIGHT = 1;
    public final static int WRONG = 2;

    private String letter;
    private int status;

    public Letter(String letter) {
        this.letter = letter;
        this.status = DEFAULT;
    }

    public Letter() {

    }

    public String getLetter() {
        return letter;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

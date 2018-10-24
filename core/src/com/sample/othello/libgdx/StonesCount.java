package com.sample.othello.libgdx;

public class StonesCount {
    private int black;
    private int white;
    private int none;

    public StonesCount(int black, int white, int none) {
        this.black = black;
        this.white = white;
        this.none = none;
    }

    public int getBlack() {
        return black;
    }

    public int getWhite() {
        return white;
    }

    public Boolean isComplete() {
        return none == 0;
    }
}

package com.sample.othello.libgdx.gameLogic;

public class Player {
    private Stone stone;

    public void setFirst() {
        stone = Stone.BLACK;
    }

    public Stone getStone() {
        return stone;
    }

    public String getName() {
        return stone.toString();
    }

    public void change() {
        switch(stone) {
            case BLACK:
                stone = Stone.WHITE;
                break;
            case WHITE:
                stone = Stone.BLACK;
                break;
            default:
                throw new IllegalStateException("Stone uninitialized.");
        }
    }
}

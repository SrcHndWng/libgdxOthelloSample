package com.sample.othello.libgdx.gameLogic;

public enum Stone {
    NONE(0),
    BLACK(1),
    WHITE(2);

    private final int id;

    private Stone(final int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}

package com.sample.othello.libgdx.gameLogic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Move {
    private String key;

    public Move(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public int getRow() {
        return Integer.parseInt(key.substring(0, 1));
    }

    public int getCol() {
        return ColumnNames.valueOf(key.substring(1)).getId();
    }
    public Boolean isValidAddress() {
        Pattern p = Pattern.compile("^[0-7][a-h]$");
        Matcher m = p.matcher(key);
        return m.find();
    }
    public Boolean isEnd() {
        // TODO: implement
        return false;
    }
    public Boolean isSkip() {
        // TODO: implement
        return false;
    }
}

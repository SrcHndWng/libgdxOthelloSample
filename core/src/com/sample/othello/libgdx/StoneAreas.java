package com.sample.othello.libgdx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoneAreas {
    private static StoneAreas stoneAreas = null;
    private List<Area> areas;

    public class Area {
        private int touchFromX;
        private int touchFromY;
        private int touchToX;
        private int touchToY;
        private int stoneX;
        private int stoneY;
        private String name;

        public Area(int touchFromX, int touchFromY, int touchToX, int touchToY, int stoneX, int stoneY, String name){
            this.touchFromX = touchFromX;
            this.touchFromY = touchFromY;
            this.touchToX = touchToX;
            this.touchToY = touchToY;
            this.stoneX = stoneX;
            this.stoneY = stoneY;
            this.name = name;
        }

        public int getTouchFromX() { return touchFromX; }
        public int getTouchFromY() { return touchFromY; }
        public int getTouchToX() { return touchToX; }
        public int getTouchToY() { return touchToY; }
        public int getStoneX(){ return  stoneX; }
        public int getStoneY() { return stoneY; }
        public String getName(){ return name; }
        public String toString(){
            return String.format(
                    "touchFromX = %d, touchFromY = %d, touchToX = %d, touchToY = %d, stoneX = %d, stoneY = %d, name=%s",
                    touchFromX, touchFromY, touchToX, touchToY, stoneX, stoneY, name);
        }
    }

    private StoneAreas(){
        List<String> columnNames = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        areas = new ArrayList<Area>();
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                int fromX = x * 100;
                int fromY = y * 100;
                int toX = fromX + 100 - 2;
                int toY = fromY + 100 - 2;
                int stoneX = fromX + 6;
                int stoneY = 700 - fromY + 3;
                String name = String.format("%d%s", y, columnNames.get(x));
                Area area = new Area(fromX, fromY, toX, toY, stoneX, stoneY, name);
                System.out.println(area.toString());
                areas.add(area);
            }
        }
    }

    public static StoneAreas initialize(){
        if(stoneAreas  == null){
            stoneAreas = new StoneAreas();
        }
        return stoneAreas;
    }

    public String getAreaName(int x, int y){
        for(Area area: areas){
            if((area.getTouchFromX() <= x) && (x < area.getTouchToX()) && (area.getTouchFromY() <= y) && (y < area.getTouchToY())){
                return area.getName();
            }
        }
        throw new IllegalArgumentException();
    }

    public Area getArea(String name){
        for(Area area: areas){
            if(area.getName().equals(name)){
                return area;
            }
        }
        throw new IllegalArgumentException();
    }
}

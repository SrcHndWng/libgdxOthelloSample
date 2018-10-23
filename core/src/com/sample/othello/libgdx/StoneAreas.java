package com.sample.othello.libgdx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoneAreas {
    private static StoneAreas stoneAreas = null;
    private List<Area> areas;

    public class Area {
        private int fromX;
        private int fromY;
        private int toX;
        private int toY;
        private int stoneX;
        private int stoneY;
        private String name;

        public Area(int fromX, int fromY, int toX, int toY, String name){
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
            this.stoneX = fromX + 6;
            this.stoneY = fromY + 3;
            this.name = name;
        }

        public String getAreaName(int x, int y){
            if((fromX <= x) && (x < toX) && (fromY <= y) && (y < toY)){
                return name;
            }
            return "";
        }

        public String getName(){ return name; }
        public int getStoneX(){ return  stoneX; }
        public int getStoneY() { return stoneY; }
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
                String name = String.format("%d%s", y, columnNames.get(x));
                System.out.printf("area fromX = %d, fromY = %d, toX = %d, toY = %d, name=%s%n", fromX, fromY, toX, toY, name);
                areas.add(new Area(fromX, fromY, toX, toY, name));
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
            String name = area.getAreaName(x, y);
            if(!name.isEmpty()){
                return name;
            }
        }
        return "";
    }

    public Area getArea(String name){
        for(Area area: areas){
            if(area.getName().equals(name)){
                return area;
            }
        }
        return null;
    }
}

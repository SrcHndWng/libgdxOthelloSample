package com.sample.othello.libgdx;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.geom.Area;

public class StoneAreasTest {
    @Test
    public void getAreaName() {
        String result;
        StoneAreas stoneAreas = StoneAreas.initialize();

        result = stoneAreas.getAreaName(54, 54);
        System.out.printf("result = %s%n", result);
        assertEquals("0a", result);

        result = stoneAreas.getAreaName(740, 53);
        System.out.printf("result = %s%n", result);
        assertEquals("0h", result);

        result = stoneAreas.getAreaName(49, 755);
        System.out.printf("result = %s%n", result);
        assertEquals("7a", result);

        result = stoneAreas.getAreaName(733, 754);
        System.out.printf("result = %s%n", result);
        assertEquals("7h", result);
    }

    @Test
    public void getName(){
        StoneAreas stoneAreas = StoneAreas.initialize();
        StoneAreas.Area area;

        area = stoneAreas.getArea("0a");
        assertEquals(6,  area.getStoneX());
        assertEquals(3, area.getStoneY());

        area = stoneAreas.getArea("7h");
        assertEquals(706,  area.getStoneX());
        assertEquals(703, area.getStoneY());
    }
}
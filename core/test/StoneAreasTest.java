package com.sample.othello.libgdx;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StoneAreasTest {
    StoneAreas stoneAreas;

    @Before
    public void before(){
        stoneAreas = StoneAreas.initialize();
    }

    @Test
    public void getAreaName() {
        String result;

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
        StoneAreas.Area area;

        area = stoneAreas.getArea("0a");
        assertEquals(6,  area.getStoneX());
        assertEquals(703, area.getStoneY());

        area = stoneAreas.getArea("4d");
        assertEquals(306,  area.getStoneX());
        assertEquals(303, area.getStoneY());

        area = stoneAreas.getArea("7h");
        assertEquals(706,  area.getStoneX());
        assertEquals(3, area.getStoneY());
    }
}
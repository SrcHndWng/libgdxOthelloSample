package com.sample.othello.libgdx.gameLogic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTests {
    Board board;
    Player player;

    @Before
    public void doBefore() {
        board = Board.initialize();
        player = new Player();
    }

    public void setFirstBoard() {
        player.setFirst();
        System.out.println("before");
//        Terminal.dispBoard(board);
    }

    /**
     * 初回、上方向へ置き換え
     */
    @Test
    public void firstInputSwapUpper() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());
        setFirstBoard();

        Boolean result = board.input(player, new Move("5d"));
        System.out.println("after");
//        Terminal.dispBoard(board);

        // 上方向に置き換え
        assertTrue(result);
        assertEquals(Stone.BLACK, board.getStones().get(5).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(3));
        // 隣の列は変更なし
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
        assertEquals(Stone.WHITE, board.getStones().get(3).get(4));
    }

    /**
     * 初回、下方向に置き換え
     */
    @Test
    public void firstInputSwapLower() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());
        setFirstBoard();

        Boolean result = board.input(player, new Move("2e"));
        System.out.println("after");
//        Terminal.dispBoard(board);

        // 下方向に置き換え
        assertTrue(result);
        assertEquals(Stone.BLACK, board.getStones().get(2).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
        // 隣の列は変更なし
        assertEquals(Stone.BLACK, board.getStones().get(3).get(3));
        assertEquals(Stone.WHITE, board.getStones().get(4).get(3));
    }

    /**
     * 初回、右方向に置き換え
     */
    @Test
    public void firstInputSwapRight() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());
        setFirstBoard();

        Boolean result = board.input(player, new Move("4c"));
        System.out.println("after");
//        Terminal.dispBoard(board);

        // 右方向に置き換え
        assertTrue(result);
        assertEquals(Stone.BLACK, board.getStones().get(4).get(2));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
        // 隣の行は変更なし
        assertEquals(Stone.BLACK, board.getStones().get(3).get(3));
        assertEquals(Stone.WHITE, board.getStones().get(3).get(4));
    }

    /**
     * 初回、左方向に置き換え
     */
    @Test
    public void firstInputSwapLeft() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());
        setFirstBoard();

        Boolean result = board.input(player, new Move("3f"));
        System.out.println("after");
//        Terminal.dispBoard(board);

        // 左方向に置き換え
        assertTrue(result);
        assertEquals(Stone.BLACK, board.getStones().get(3).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(5));
        // 隣の行は変更なし
        assertEquals(Stone.WHITE, board.getStones().get(4).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
    }

    /**
     * 右上方向に置き換え
     */
    @Test
    public void swapRightUpper() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());

        player.setFirst();
        board.getStones().get(3).set(3, Stone.WHITE);
        board.getStones().get(3).set(4, Stone.BLACK);
        board.getStones().get(4).set(3, Stone.WHITE);
        board.getStones().get(4).set(4, Stone.WHITE);
        board.getStones().get(3).set(2, Stone.WHITE);
        board.getStones().get(4).set(2, Stone.WHITE);
        board.getStones().get(5).set(2, Stone.WHITE);
        System.out.println("before");
//        Terminal.dispBoard(board);

        Boolean result = board.input(player, new Move("6b"));
        System.out.println("after");
//        Terminal.dispBoard(board);

        // 右上方向に置き換え
        assertTrue(result);
        assertEquals(Stone.BLACK, board.getStones().get(6).get(1));
        assertEquals(Stone.BLACK, board.getStones().get(5).get(2));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(4));
        // 他は変更なし
        assertEquals(Stone.WHITE, board.getStones().get(3).get(2));
        assertEquals(Stone.WHITE, board.getStones().get(3).get(3));
        assertEquals(Stone.WHITE, board.getStones().get(4).get(2));
        assertEquals(Stone.WHITE, board.getStones().get(4).get(4));
    }

    /**
     * 右下方向に置き換え
     */
    @Test
    public void swapRightLower() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());

        player.setFirst();
        board.getStones().get(3).set(3, Stone.WHITE);
        board.getStones().get(3).set(4, Stone.BLACK);
        board.getStones().get(4).set(3, Stone.WHITE);
        board.getStones().get(4).set(4, Stone.WHITE);
        board.getStones().get(5).set(5, Stone.BLACK);
        System.out.println("before");
//        Terminal.dispBoard(board);

        Boolean result = board.input(player, new Move("2c"));
        System.out.println("after");
//        Terminal.dispBoard(board);

        // 右下方向に置き換え
        assertTrue(result);
        assertEquals(Stone.BLACK, board.getStones().get(2).get(2));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(5).get(5));
        // 他は変更なし
        assertEquals(Stone.WHITE, board.getStones().get(4).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(4));
    }

    /**
     * 左上方向に置き換え
     */
    @Test
    public void swapLeftUpper() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());

        player.setFirst();
        board.getStones().get(2).set(2, Stone.BLACK);
        board.getStones().get(3).set(3, Stone.WHITE);
        board.getStones().get(4).set(4, Stone.WHITE);
        board.getStones().get(3).set(4, Stone.BLACK);
        board.getStones().get(4).set(3, Stone.BLACK);
        System.out.println("before");
//        Terminal.dispBoard(board);

        Boolean result = board.input(player, new Move("5f"));
        System.out.println("after");
//        Terminal.dispBoard(board);

        // 左上方向に置き換え
        assertTrue(result);
        assertEquals(Stone.BLACK, board.getStones().get(5).get(5));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(2).get(2));
        // 他は変更なし
        assertEquals(Stone.BLACK, board.getStones().get(3).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(3));
    }

    /**
     * 左下方向に置き換え
     */
    @Test
    public void swapLeftLower() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());

        player.setFirst();
        board.getStones().get(3).set(3, Stone.WHITE);
        board.getStones().get(5).set(2, Stone.BLACK);
        System.out.println("before");
//        Terminal.dispBoard(board);

        Boolean result = board.input(player, new Move("2f"));
        System.out.println("after");
//        Terminal.dispBoard(board);

        // 左下方向に置き換え
        assertTrue(result);
        assertEquals(Stone.BLACK, board.getStones().get(2).get(5));
        assertEquals(Stone.BLACK, board.getStones().get(3).get(4));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(5).get(2));
        // 他は変更なし
        assertEquals(Stone.WHITE, board.getStones().get(3).get(3));
        assertEquals(Stone.BLACK, board.getStones().get(4).get(4));
    }

    /**
     * 隣接して置き換え対象なし
     */
    @Test
    public void notExistNextSwapStones() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());

        System.out.println("right, left, upper, lower");
        player.setFirst();
//        Terminal.dispBoard(board);
        assertFalse(board.input(player, new Move("3c"))); // 右方向に隣接する石が入力したものと同じ
        assertFalse( board.input(player, new Move("4f"))); // 左方向に隣接する石が入力したものと同じ
        assertFalse( board.input(player, new Move("5e"))); // 上方向に隣接する石が入力したものと同じ
        assertFalse(board.input(player, new Move("2d"))); // 下方向に隣接する石が入力したものと同じ
        System.out.println("after");
//        Terminal.dispBoard(board);

        System.out.println("right-upper, right-lower, left-upper, left-lower");
        board.getStones().get(3).set(4, Stone.BLACK);
        board.getStones().get(4).set(3, Stone.BLACK);
//        Terminal.dispBoard(board);
        assertFalse(board.input(player, new Move("5c"))); // 右上方向に隣接する石が入力したものと同じ
        assertFalse(board.input(player, new Move("2c"))); // 右下方向に隣接する石が入力したものと同じ
        assertFalse(board.input(player, new Move("5f"))); // 左上方向に隣接する石が入力したものと同じ
        assertFalse(board.input(player, new Move("2f"))); // 左下方向に隣接する石が入力したものと同じ
        System.out.println("after");
//        Terminal.dispBoard(board);
    }

    /**
     * 端に同じ石が存在しない
     */
    @Test
    public void notExistEdgeStones() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());

        player.setFirst();
        board.getStones().get(3).set(3, Stone.WHITE);
        board.getStones().get(4).set(4, Stone.WHITE);

        // 右方向に端が存在しない
        System.out.println("upper");
//        Terminal.dispBoard(board);
        assertFalse(board.input(player, new Move("3c")));
        System.out.println("after");
//        Terminal.dispBoard(board);
        // 左方向に端が存在しない
        System.out.println("upper");
//        Terminal.dispBoard(board);
        assertFalse(board.input(player, new Move("3f")));
        System.out.println("after");
//        Terminal.dispBoard(board);
        // 上方向に端が存在しない
        System.out.println("upper");
//        Terminal.dispBoard(board);
        assertFalse(board.input(player, new Move("5d")));
//        System.out.println("after");
//        Terminal.dispBoard(board);
        // 下方向に端が存在しない
        assertFalse(board.input(player, new Move("2d")));
        System.out.println("after");
//        Terminal.dispBoard(board);
        // 右上方向に端が存在しない
        System.out.println("upper");
//        Terminal.dispBoard(board);
        assertFalse(board.input(player, new Move("5c")));
        System.out.println("after");
//        Terminal.dispBoard(board);
        // 右下方向に端が存在しない
        System.out.println("upper");
//        Terminal.dispBoard(board);
        assertFalse(board.input(player, new Move("2f")));
        System.out.println("after");
//        Terminal.dispBoard(board);
        // 左上方向に端が存在しない
        System.out.println("upper");
//        Terminal.dispBoard(board);
        assertFalse(board.input(player, new Move("5f")));
        System.out.println("after");
//        Terminal.dispBoard(board);
        // 左下方向に端が存在しない
        System.out.println("upper");
//        Terminal.dispBoard(board);
        assertFalse(board.input(player, new Move("2d")));
        System.out.println("after");
//        Terminal.dispBoard(board);

        System.out.println("after");
//        Terminal.dispBoard(board);
    }

    /**
     * 隅に入力した状態
     */
    @Test
    public void inputCorner() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());
        player.setFirst();
        Boolean result = false;

        class Stones {
            public void init() {
                for(int i = 1; i < 7; i++) {
                    for(int j = 0; j < 8; j++) {
                        board.getStones().get(i).set(j, Stone.WHITE);
                    }
                }
                for(int j = 1; j < 7; j++) {
                    board.getStones().get(0).set(j, Stone.WHITE);
                    board.getStones().get(7).set(j, Stone.WHITE);
                }
            }
        }

        // 右上に入力
        System.out.println("input right upper");
        new Stones().init();
        board.getStones().get(0).set(0, Stone.BLACK);
        board.getStones().get(7).set(0, Stone.BLACK);
        board.getStones().get(7).set(7, Stone.BLACK);
//        Terminal.dispBoard(board);
        result = board.input(player, new Move("0h"));
        System.out.println("after");
//        Terminal.dispBoard(board);
        assertTrue(result);

        // 左上に入力
        System.out.println("input left upper");
        new Stones().init();
        board.getStones().get(0).set(0, Stone.NONE);
        board.getStones().get(0).set(7, Stone.BLACK);
        board.getStones().get(7).set(0, Stone.BLACK);
        board.getStones().get(7).set(7, Stone.BLACK);
//        Terminal.dispBoard(board);
        result = board.input(player, new Move("0a"));
        System.out.println("after");
//        Terminal.dispBoard(board);
        assertTrue(result);

        // 右下に入力
        System.out.println("input right upper");
        new Stones().init();
        board.getStones().get(0).set(0, Stone.BLACK);
        board.getStones().get(0).set(7, Stone.BLACK);
        board.getStones().get(7).set(0, Stone.BLACK);
        board.getStones().get(7).set(7, Stone.NONE);
//        Terminal.dispBoard(board);
        result = board.input(player, new Move("7h"));
        System.out.println("after");
//        Terminal.dispBoard(board);
        assertTrue(result);

        // 左下に入力
        System.out.println("input left lower");
        new Stones().init();
        board.getStones().get(0).set(0, Stone.BLACK);
        board.getStones().get(0).set(7, Stone.BLACK);
        board.getStones().get(7).set(0, Stone.NONE);
        board.getStones().get(7).set(7, Stone.BLACK);
//        Terminal.dispBoard(board);
        result = board.input(player, new Move("7a"));
        System.out.println("after");
//        Terminal.dispBoard(board);
        assertTrue(result);
    }

    /**
     * 片方方向に変換、別方向は変換なし
     */
    @Test
    public void swapOneAndAnotherUnable() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());
        player.setFirst();

        // 上方向に変換、右方向は変換なし
        System.out.println("swap upper, unable right");
        board.getStones().get(3).set(0, Stone.BLACK);
        board.getStones().get(3).set(1, Stone.WHITE);
        board.getStones().get(3).set(2, Stone.WHITE);
        board.getStones().get(4).set(0, Stone.WHITE);
        board.getStones().get(4).set(1, Stone.WHITE);
        board.getStones().get(4).set(2, Stone.WHITE);
        board.getStones().get(5).set(0, Stone.WHITE);
        board.getStones().get(5).set(1, Stone.WHITE);
        board.getStones().get(5).set(2, Stone.WHITE);
        board.getStones().get(5).set(3, Stone.WHITE);
        board.getStones().get(5).set(4, Stone.WHITE);
        board.getStones().get(6).set(0, Stone.WHITE);
        board.getStones().get(6).set(1, Stone.WHITE);
        board.getStones().get(6).set(2, Stone.WHITE);
        board.getStones().get(6).set(3, Stone.WHITE);
        board.getStones().get(6).set(4, Stone.WHITE);
        board.getStones().get(7).set(2, Stone.WHITE);
        board.getStones().get(7).set(3, Stone.WHITE);
        board.getStones().get(7).set(4, Stone.BLACK);
        System.out.println("before");
//        Terminal.dispBoard(board);
        Boolean result = board.input(player, new Move("7a"));
        System.out.println("after");
//        Terminal.dispBoard(board);
        assertTrue(result);
    }

    /**
     * 全ての場所に石が配置されたか
     */
    @Test
    public void completeTest() {
        System.out.printf("----- %s -----%n", new Object(){}.getClass().getEnclosingMethod().getName());
        player.setFirst();
        StonesCount count;

        System.out.println("input complete");
        for(int i = 0; i < Const.MAX_ROW_COL_NUM; i++) {
            for(int j = 0; j < Const.MAX_ROW_COL_NUM; j++ ) {
                if((j % 2) == 0){
                    board.getStones().get(i).set(j, Stone.WHITE);
                }else {
                    board.getStones().get(i).set(j, Stone.BLACK);
                }
            }
        }
//        Terminal.dispBoard(board);
        count = board.getStonesCount();
        assertTrue(count.isComplete());
        assertEquals(32, count.getBlack());
        assertEquals(32, count.getWhite());

        System.out.println("input not complete");
        board.getStones().get(Const.MAX_ROW_COL_NUM -1).set(Const.MAX_ROW_COL_NUM -2, Stone.NONE);
//        Terminal.dispBoard(board);
        count = board.getStonesCount();
        assertFalse(count.isComplete());
        assertEquals(32, count.getBlack());
        assertEquals(31, count.getWhite());
    }
}

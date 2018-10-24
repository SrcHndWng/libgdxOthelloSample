package com.sample.othello.libgdx.gameLogic;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Stone>> stones;
    private final int UNDEFINED_ROW_COL = -1;

    private abstract class SwapStones{
        protected Stone inputStone;
        protected int inputRow;
        protected int inputCol;

        protected class Edge{
            private int row;
            private int col;
            public Edge(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }

        public SwapStones(Stone inputStone, int inputRow, int inputCol) {
            this.inputStone = inputStone;
            this.inputRow = inputRow;
            this.inputCol = inputCol;
        }
        public int exec(){
            if(!isNextEnableSwap()) {
                return 0;
            }
            Edge edge = getEdge();
            if(!isExistEdge(edge.row, edge.col)) {
                return 0;
            }
            return swap(edge.row, edge.col);
        }
        abstract protected Boolean isNextEnableSwap();
        abstract protected Edge getEdge();
        abstract protected int swap(int edgeRow, int edgeCol);
        protected Boolean isExistEdge(int edgeRow, int edgeCol) {
            return (edgeRow > UNDEFINED_ROW_COL) && (edgeCol > UNDEFINED_ROW_COL);
        }
        protected int getUpperOfInputRow() {
            return inputRow - 1;
        }
        protected int getLowerOfInputRow() {
            return inputRow + 1;
        }
        protected int getLeftOfInputCol() {
            return inputCol - 1;
        }
        protected int getRightOfInputCol() {
            return inputCol + 1;
        }
        protected Boolean isOppositeStone(int checkRow, int checkCol) {
            return (stones.get(checkRow).get(checkCol) != Stone.NONE) &&
                    (stones.get(checkRow).get(checkCol) != this.inputStone);
        }
    }

    private class SwapToUpper extends SwapStones{
        public SwapToUpper(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            for(int i = getUpperOfInputRow(); i >= edgeRow; i--) {
                if(i < 0) {
                    break;
                }
                stones.get(i).set(inputCol, inputStone);
            }
            return 1;
        }

        @Override
        protected Edge getEdge() {
            int edgeRow = UNDEFINED_ROW_COL;
            for(int i = getUpperOfInputRow(); i >= 0; i--) {
                if(i < 0) {
                    break;
                }
                if(stones.get(i).get(inputCol) == inputStone) {
                    edgeRow = i;
                    break;
                }
            }
            return new Edge(edgeRow, this.inputCol);
        }

        @Override
        protected Boolean isNextEnableSwap() {
            int checkRow = getUpperOfInputRow();
            if(checkRow < 0) return false;
            return isOppositeStone(checkRow, this.inputCol);
        }
    }

    private class SwapToLower extends SwapStones{
        public SwapToLower(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            for(int i = getLowerOfInputRow(); i <= edgeRow; i++) {
                if(i >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                stones.get(i).set(inputCol, inputStone);
            }
            return 1;
        }

        @Override
        protected Edge getEdge() {
            int edgeRow = UNDEFINED_ROW_COL;
            for(int i = getLowerOfInputRow(); i < Const.MAX_ROW_COL_NUM; i++) {
                if(i >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                if(stones.get(i).get(inputCol) == inputStone) {
                    edgeRow = i;
                    break;
                }
            }
            return new Edge(edgeRow, this.inputCol);
        }

        @Override
        protected Boolean isNextEnableSwap() {
            int checkRow = getLowerOfInputRow();
            if(checkRow >= Const.MAX_ROW_COL_NUM) {
                return true;
            }
            return isOppositeStone(checkRow, this.inputCol);
        }

    }

    private class SwapToRight extends SwapStones{
        public SwapToRight(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected Boolean isNextEnableSwap() {
            int checkCol = getRightOfInputCol();
            if(checkCol >= Const.MAX_ROW_COL_NUM) return true;
            return isOppositeStone(this.inputRow, checkCol);
        }

        @Override
        protected Edge getEdge() {
            int edgeCol = UNDEFINED_ROW_COL;
            for(int i = getRightOfInputCol(); i < Const.MAX_ROW_COL_NUM; i++) {
                if(i >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                if(stones.get(inputRow).get(i) == inputStone) {
                    edgeCol = i;
                    break;
                }
            }
            return new Edge(this.inputRow, edgeCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            for(int i = getRightOfInputCol(); i <= edgeCol; i++) {
                if(i >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                stones.get(inputRow).set(i, inputStone);
            }
            return 1;
        }

    }

    private class SwapToLeft extends SwapStones{
        public SwapToLeft(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected Boolean isNextEnableSwap() {
            int checkCol = this.inputCol -1;
            if(checkCol < 0) {
                return true;
            }
            return isOppositeStone(this.inputRow, checkCol);
        }

        @Override
        protected Edge getEdge() {
            int edgeCol = UNDEFINED_ROW_COL;
            for(int i = getLeftOfInputCol(); i >= 0; i--) {
                if(i < 0) {
                    break;
                }
                if(stones.get(inputRow).get(i) == inputStone) {
                    edgeCol = i;
                    break;
                }
            }
            return new Edge(this.inputRow, edgeCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            for(int i = getLeftOfInputCol(); i >= edgeCol; i--) {
                if(i < 0) {
                    break;
                }
                stones.get(inputRow).set(i, inputStone);
            }
            return 1;
        }

    }

    private class SwapToRightUpper extends SwapStones{
        public SwapToRightUpper(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            int k = getRightOfInputCol();
            for(int row = getUpperOfInputRow(); row >= edgeRow; row--) {
                if(row < 0) {
                    break;
                }
                if(k >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                stones.get(row).set(k, inputStone);
                k++;
            }
            return 1;
        }

        @Override
        protected Edge getEdge() {
            int j = getRightOfInputCol();
            int edgeRow = UNDEFINED_ROW_COL;
            int edgeCol = UNDEFINED_ROW_COL;
            for(int i = getUpperOfInputRow(); i >= 0; i--) {
                if(i < 0) {
                    break;
                }
                if(j > Const.MAX_ROW_COL_NUM - 1) {
                    break;
                }
                if(stones.get(i).get(j) == inputStone) {
                    edgeRow = i;
                    edgeCol = j;
                    break;
                }
                j++;
            }
            return new Edge(edgeRow, edgeCol);
        }

        @Override
        protected Boolean isNextEnableSwap() {
            int checkRow = getUpperOfInputRow();
            if(checkRow < 0) return true;
            int checkCol = getRightOfInputCol();
            if(checkCol >= Const.MAX_ROW_COL_NUM) return true;
            return isOppositeStone(checkRow, checkCol);
        }

    }

    private class SwapToRightLower extends SwapStones{
        public SwapToRightLower(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected Boolean isNextEnableSwap() {
            int checkRow = getLowerOfInputRow();
            if(checkRow >= Const.MAX_ROW_COL_NUM) {
                return true;
            }
            int checkCol = getRightOfInputCol();
            if(checkCol >= Const.MAX_ROW_COL_NUM) {
                return true;
            }
            return isOppositeStone(checkRow, checkCol);
        }

        @Override
        protected Edge getEdge() {
            int j = getRightOfInputCol();
            int edgeRow = UNDEFINED_ROW_COL;
            int edgeCol = UNDEFINED_ROW_COL;
            for(int i = getLowerOfInputRow(); i < Const.MAX_ROW_COL_NUM; i++) {
                if(i >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                if(j > Const.MAX_ROW_COL_NUM - 1) {
                    break;
                }
                if(stones.get(i).get(j) == inputStone) {
                    edgeRow = i;
                    edgeCol = j;
                    break;
                }
                j++;
            }
            return new Edge(edgeRow, edgeCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            int k = getRightOfInputCol();
            for(int row = getLowerOfInputRow(); row <= edgeRow; row++) {
                if(row >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                if(k >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                stones.get(row).set(k, inputStone);
                k++;
            }
            return 1;
        }

    }

    private class SwapToLeftUpper extends SwapStones{
        public SwapToLeftUpper(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected Boolean isNextEnableSwap() {
            int checkRow = getUpperOfInputRow();
            if(checkRow < 0) {
                return true;
            }
            int checkCol = getLeftOfInputCol();
            if(checkCol < 0) {
                return true;
            }
            return isOppositeStone(checkRow, checkCol);
        }

        @Override
        protected Edge getEdge() {
            int j = getLeftOfInputCol();
            int edgeRow = UNDEFINED_ROW_COL;
            int edgeCol = UNDEFINED_ROW_COL;
            for(int i = getUpperOfInputRow(); i >= 0; i--) {
                if(i < 0) {
                    break;
                }
                if(j < 0) {
                    break;
                }
                if(stones.get(i).get(j) == inputStone) {
                    edgeRow = i;
                    edgeCol = j;
                    break;
                }
                j--;
            }
            return new Edge(edgeRow, edgeCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            int k = getLeftOfInputCol();
            for(int row = getUpperOfInputRow(); row >= edgeRow; row--) {
                if(row < 0) {
                    break;
                }
                if(k < 0) {
                    break;
                }
                stones.get(row).set(k, inputStone);
                k--;
            }
            return 1;
        }

    }

    private class SwapToLeftLower extends SwapStones{
        public SwapToLeftLower(Stone inputStone, int inputRow, int inputCol) {
            super(inputStone, inputRow, inputCol);
        }

        @Override
        protected Boolean isNextEnableSwap() {
            int checkRow = getLowerOfInputRow();
            if(checkRow >= Const.MAX_ROW_COL_NUM) {
                return true;
            }
            int checkCol = getLeftOfInputCol();
            if(checkCol < 0) {
                return true;
            }
            return isOppositeStone(checkRow, checkCol);
        }

        @Override
        protected Edge getEdge() {
            int j = getLeftOfInputCol();
            int edgeRow = UNDEFINED_ROW_COL;
            int edgeCol = UNDEFINED_ROW_COL;
            for(int i = getLowerOfInputRow(); i < Const.MAX_ROW_COL_NUM; i++) {
                if(i >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                if(j < 0) {
                    break;
                }
                if(stones.get(i).get(j) == inputStone) {
                    edgeRow = i;
                    edgeCol = j;
                    break;
                }
                j--;
            }
            return new Edge(edgeRow, edgeCol);
        }

        @Override
        protected int swap(int edgeRow, int edgeCol) {
            int k = getLeftOfInputCol();
            for(int row = getLowerOfInputRow(); row <= edgeRow; row++) {
                if(row >= Const.MAX_ROW_COL_NUM) {
                    break;
                }
                if(k < 0) {
                    break;
                }
                stones.get(row).set(k, inputStone);
                k--;
            }
            return 1;
        }

    }

    private Board() {
        class Initializer{
            public void setStartStones() {
                stones = new ArrayList<ArrayList<Stone>>();
                for(int i = 0; i < Const.MAX_ROW_COL_NUM; i++) {
                    ArrayList<Stone> column = new ArrayList<Stone>();
                    for(int j = 0; j < Const.MAX_ROW_COL_NUM; j++) {
                        if(i == 3 && j == 3) {
                            column.add(Stone.BLACK);
                        }else if(i == 3 && j == 4){
                            column.add(Stone.WHITE);
                        }else if(i == 4 && j == 3){
                            column.add(Stone.WHITE);
                        }else if(i == 4 && j == 4){
                            column.add(Stone.BLACK);
                        }else {
                            column.add(Stone.NONE);
                        }
                    }
                    stones.add(column);
                }
            }
        }
        new Initializer().setStartStones();
    }

    public static Board initialize() {
        return new Board();
    }

    public ArrayList<ArrayList<Stone>> getStones(){
        return stones;
    }

    public Boolean input(Player player, Move move) {
        int inputRow = move.getRow();
        int inputCol = move.getCol();
        int swapCnt = 0;

        if(!isInputRowColEmpty(inputRow, inputCol)) {
            return false;
        }

        swapCnt += new SwapToUpper(player.getStone(), inputRow, inputCol).exec(); // 上方向にループして石を変える
        swapCnt += new SwapToLower(player.getStone(), inputRow, inputCol).exec(); // 下方向にループして石を変える
        swapCnt += new SwapToRight(player.getStone(), inputRow, inputCol).exec(); // 右方向にループして石を変える
        swapCnt += new SwapToLeft(player.getStone(), inputRow, inputCol).exec(); // 左方向にループして石を変える
        swapCnt += new SwapToRightUpper(player.getStone(), inputRow, inputCol).exec(); // 右上方向にループして石を変える
        swapCnt += new SwapToRightLower(player.getStone(), inputRow, inputCol).exec(); // 右下方向にループして石を変える
        swapCnt += new SwapToLeftUpper(player.getStone(), inputRow, inputCol).exec(); // 左上方向にループして石を変える
        swapCnt += new SwapToLeftLower(player.getStone(), inputRow, inputCol).exec(); // 左下方向にループして石を変える
        Boolean isSwapped = (swapCnt > 0);
        if(isSwapped) {
            setInputStone(player, inputRow, inputCol);
        }
        return isSwapped;
    }

    public StonesCount getStonesCount() {
        int blackCount = 0;
        int whiteCount = 0;
        int noneCount = 0;
        for(int i = 0; i < Const.MAX_ROW_COL_NUM; i++) {
            for(int j = 0; j < Const.MAX_ROW_COL_NUM; j++ ) {
                switch(stones.get(i).get(j)) {
                    case BLACK:
                        blackCount++;
                        break;
                    case WHITE:
                        whiteCount++;
                        break;
                    case NONE:
                        noneCount++;
                        break;
                }
            }
        }
        return new StonesCount(blackCount, whiteCount, noneCount);
    }

    private void setInputStone(Player player, int inputRow, int inputCol){
        stones.get(inputRow).set(inputCol, player.getStone());
    }

    private Boolean isInputRowColEmpty(int inputRow, int inputCol) {
        return stones.get(inputRow).get(inputCol) == Stone.NONE;
    }
}

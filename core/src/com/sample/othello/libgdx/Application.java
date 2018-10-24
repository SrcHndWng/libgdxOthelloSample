package com.sample.othello.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.sample.othello.libgdx.gameLogic.Board;
import com.sample.othello.libgdx.gameLogic.Const;
import com.sample.othello.libgdx.gameLogic.Stone;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;


public class Application extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture boardTexture;
	private Texture blackPushTexture;
	private Map<String, Texture> blackStones;
	private Map<String, Texture> whiteStones;
	private OrthographicCamera camera;
	private StoneAreas stoneAreas;
	private Board board;
	private int touchedX = 0;
	private int touchedY = 0;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 800);
		batch = new SpriteBatch();
		boardTexture = new Texture("board.png");
		blackPushTexture = new Texture("blackStone.png");
		stoneAreas = StoneAreas.initialize();
		blackStones = new Hashtable<>();
		whiteStones = new Hashtable<>();
	}

	@Override
	public void render () {
		board = Board.initialize();

		Gdx.gl.glClearColor(0, 0.5019f, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(boardTexture, 0, 0);
		displayBoard(board);
		batch.end();

		// process user input
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if(isFirstTouchEvent()){
				System.out.printf("touched!! x = %d, y = %d, name = %s%n",Gdx.input.getX(), Gdx.input.getY(), stoneAreas.getAreaName(Gdx.input.getX(), Gdx.input.getY()));
				touchedX = Gdx.input.getX();
				touchedY = Gdx.input.getY();
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		boardTexture.dispose();
		blackPushTexture.dispose();
		for(String key: blackStones.keySet()){
			blackStones.get(key).dispose();
		}
		for(String key: whiteStones.keySet()){
			whiteStones.get(key).dispose();
		}
		System.out.println("dispose");
	}

    private void displayBoard(Board board){
        for(int x = 0; x < Const.MAX_ROW_COL_NUM; x++) {
            ArrayList<Stone> row = board.getStones().get(x);
            for(int y = 0; y < Const.MAX_ROW_COL_NUM; y++){
                String areaName = String.format("%d%s", x, StoneAreas.columnNames.get(y));
                switch(row.get(y)) {
                    case NONE:
                        break;
                    case BLACK:
                        blackStones.put(areaName, new Texture("blackStone.png"));
                        batch.draw(blackStones.get(areaName), stoneAreas.getArea(areaName).getStoneX(), stoneAreas.getArea(areaName).getStoneY());
                        break;
                    case WHITE:
                        whiteStones.put(areaName, new Texture("whiteStone.png"));
                        batch.draw(whiteStones.get(areaName), stoneAreas.getArea(areaName).getStoneX(), stoneAreas.getArea(areaName).getStoneY());
                        break;
                    default:
                        throw new IllegalStateException("Illegal Stone.");
                }
            }
        }
    }

    private boolean isFirstTouchEvent(){
		return ((touchedX != Gdx.input.getX()) && (touchedY != Gdx.input.getY()));
	}
}

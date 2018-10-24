package com.sample.othello.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

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

	private void dispBoard(Board board){
		for(int x = 0; x < board.getStones().size(); x++) {
			ArrayList<Stone> row = board.getStones().get(x);
			for(int y = 0; y < row.size(); y++){
				String colName = StoneAreas.columnNames.get(y);
				String key = String.format("%d%s", x, colName);
				switch(row.get(y)) {
					case NONE:
						break;
					case BLACK:
						blackStones.put(key, new Texture("blackStone.png"));
						batch.draw(blackStones.get(key), stoneAreas.getArea(key).getStoneX(), stoneAreas.getArea(key).getStoneY());
						break;
					case WHITE:
						whiteStones.put(key, new Texture("whiteStone.png"));
						batch.draw(whiteStones.get(key), stoneAreas.getArea(key).getStoneX(), stoneAreas.getArea(key).getStoneY());
						break;
					default:
						throw new IllegalStateException("Illegal Stone.");
				}
            }
		}
	}

	@Override
	public void render () {
		Board board = Board.initialize();

		Gdx.gl.glClearColor(0, 0.5019f, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(boardTexture, 0, 0);
		dispBoard(board);
		batch.end();

		// process user input
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			System.out.printf("touched!! x = %d, y = %d, name = %s%n",Gdx.input.getX(), Gdx.input.getY(), stoneAreas.getAreaName(Gdx.input.getX(), Gdx.input.getY()));
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
}

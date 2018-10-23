package com.sample.othello.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


public class Application extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture boardTexture;
	private Texture blackTexture1;
	private Texture blackTexture2;
	private Texture blackPushTexture;
	private Texture whiteTexture1;
	private Texture whiteTexture2;
	private OrthographicCamera camera;
	private StoneAreas stoneAreas;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 800);
		batch = new SpriteBatch();
		boardTexture = new Texture("board.png");
		blackTexture1 = new Texture("blackStone.png");
		blackTexture2 = new Texture("blackStone.png");
		blackPushTexture = new Texture("blackStone.png");
		whiteTexture1 = new Texture("whiteStone.png");
		whiteTexture2 = new Texture("whiteStone.png");
		stoneAreas = StoneAreas.initialize();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0.5019f, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(boardTexture, 0, 0);

//		StoneAreas.Area area = stoneAreas.getArea("4d");
//		System.out.printf("whiteTexture1 x = %d, y = %d%n", area.getStoneX(), area.getStoneY());
		batch.draw(whiteTexture1, 306, 303);
		batch.draw(whiteTexture2, 406, 403);

		batch.draw(blackTexture1, 306, 403);
		batch.draw(blackTexture2, 406, 303);

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
		blackTexture1.dispose();
		blackTexture2.dispose();
		blackPushTexture.dispose();
		whiteTexture1.dispose();
		whiteTexture2.dispose();
	}
}

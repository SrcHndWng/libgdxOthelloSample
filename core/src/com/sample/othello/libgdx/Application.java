package com.sample.othello.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.sample.othello.libgdx.gameLogic.Board;
import com.sample.othello.libgdx.gameLogic.Const;
import com.sample.othello.libgdx.gameLogic.Move;
import com.sample.othello.libgdx.gameLogic.Player;
import com.sample.othello.libgdx.gameLogic.Stone;

import java.util.ArrayList;

public class Application extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture boardTexture;
	private Texture blackStoneTexture;
	private Texture whiteStoneTexture;
	private OrthographicCamera camera;
	private StoneAreas stoneAreas;
	private Board board;
	private Player player;
	private int touchedX = 0;
	private int touchedY = 0;
	private int pushSpace = 0;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 800);
		batch = new SpriteBatch();
		boardTexture = new Texture("board.png");
		blackStoneTexture =  new Texture("blackStone.png");
		whiteStoneTexture = new Texture("whiteStone.png");
		stoneAreas = StoneAreas.initialize();
		board = Board.initialize();
		player = new Player();
		player.setFirst();
		setMessage(String.format("You're %s, please start!", player.getName()));
	}

	@Override
	public void render () {
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
			pushSpace = 0;
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if(isFirstTouchEvent()){
				System.out.printf("touched!! x = %d, y = %d, name = %s%n",Gdx.input.getX(), Gdx.input.getY(), stoneAreas.getAreaName(Gdx.input.getX(), Gdx.input.getY()));
                Move move = new Move(stoneAreas.getAreaName(Gdx.input.getX(), Gdx.input.getY()));
                Boolean isSwapped = board.input(player, move);
				if(isSwapped) {
					player.change();
					setMessage(String.format("You're %s.", player.getName()));
					touchedX = Gdx.input.getX();
					touchedY = Gdx.input.getY();
				}else{
					setMessage(String.format("Your input = %s. You can't change stones.", move.getKey()));
				}
			}
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			if(pushSpace == 0){
				player.change();
				setMessage(String.format("You're %s.", player.getName()));
			}
			pushSpace++;
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		boardTexture.dispose();
		blackStoneTexture.dispose();
		whiteStoneTexture.dispose();
		System.out.println("dispose");
	}

	private void setMessage(String message){
		Gdx.graphics.setTitle(String.format("Othello Application. %s.", message));
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
						batch.draw(blackStoneTexture, stoneAreas.getArea(areaName).getStoneX(), stoneAreas.getArea(areaName).getStoneY());
                        break;
                    case WHITE:
						batch.draw(whiteStoneTexture, stoneAreas.getArea(areaName).getStoneX(), stoneAreas.getArea(areaName).getStoneY());
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

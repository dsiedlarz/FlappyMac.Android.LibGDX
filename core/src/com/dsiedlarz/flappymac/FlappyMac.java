package com.dsiedlarz.flappymac;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dsiedlarz.flappymac.Screens.PlayScreen;

public class FlappyMac extends Game {

	public static final short FLAPPY_BIT = 1;
	public static final short PIPE_BIT =2;
	public static final short ENIVRONMENT_BIT =4;
	public static final float PPM = 100;
	public static final int V_WIDTH =288;
	public static final int V_HEIGHT =512 ;


	public SpriteBatch batch;
	@Override
	public void create () {


		batch = new SpriteBatch();


		setScreen(new PlayScreen(this));
	}



	public void reload(){
		setScreen(new PlayScreen(this));

	}
	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}
}

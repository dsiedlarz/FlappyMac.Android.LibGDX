package com.dsiedlarz.flappymac.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dsiedlarz.flappymac.Environment.Environment;
import com.dsiedlarz.flappymac.FlappyMac;
import com.dsiedlarz.flappymac.Pipes.BottomPipe;
import com.dsiedlarz.flappymac.Pipes.Pipes;
import com.dsiedlarz.flappymac.Pipes.TopPipe;
import com.dsiedlarz.flappymac.Sceenes.Hud;
import com.dsiedlarz.flappymac.Sprites.Flappy;
import com.dsiedlarz.flappymac.Tools.ContactListenerW;
import com.dsiedlarz.flappymac.Tools.ParallaxBackground;
import com.dsiedlarz.flappymac.Tools.ParallaxLayer;


/**
 * Created by Dawid on 2016-03-11.
 */
public class PlayScreen implements Screen {
    public enum State{START,PLAY,GAME_OVER};
    public static State state=State.START;
    private FlappyMac game;
    private TextureAtlas atlas;


    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    private World world;
    private Box2DDebugRenderer b2dr;
   private Pipes pipes;

    private ParallaxBackground rbg;
    private ParallaxBackground rbg1;
    private Environment environment;

    private Flappy player;
    private  boolean isGameOver;


    public TextureAtlas getAtlas() {
        return atlas;
    }

    public World getWorld() {

        return world;
    }

    public State getState() {
        return state;
    }

    public PlayScreen(FlappyMac game) {
        state=State.START;
        world= new World(new Vector2(0,-10),true);
        isGameOver=false;
        this.game =game;
        atlas=new TextureAtlas("Flappy.pack");

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(FlappyMac.V_WIDTH /(FlappyMac.PPM),FlappyMac.V_HEIGHT/FlappyMac.PPM,gameCam);

        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        world= new World(new Vector2(0,-10),true);

        world.setContactListener(new ContactListenerW(this));




        hud = new Hud(game.batch,this);

        b2dr = new Box2DDebugRenderer();
        Gdx.app.log("asdad","dsds");

        player=new Flappy(this);


        rbg = new ParallaxBackground(new ParallaxLayer[]{
                new ParallaxLayer(new TextureRegion(atlas.findRegion("Atlas"),0,0,288,512),new Vector2(0.2f,0.2f),new Vector2(0, 0))
        }, FlappyMac.V_WIDTH, FlappyMac.V_HEIGHT,new Vector2(150 ,0));


        rbg1 = new ParallaxBackground(new ParallaxLayer[]{
                new ParallaxLayer(new TextureRegion(atlas.findRegion("Atlas"),586,0,288,100),new Vector2(1.0f,1.0f),new Vector2(0, 500))

        }, FlappyMac.V_WIDTH, FlappyMac.V_HEIGHT,new Vector2(150 ,0));

        pipes = new Pipes(this);
        environment = new Environment(this);

    }


    @Override
    public void show() {

    }

    void update(float delta){
        handleInput(delta);


        gameCam.update();


        world.step(1 / 60f, 6, 2);
        player.update(delta);
     pipes.update(delta);
        hud.update(delta);
        hud.update(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);



        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        rbg.render(delta);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();


        player.draw(game.batch);
        pipes.render(game.batch);
        game.batch.end();

        rbg1.render(delta);
        b2dr.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }


    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void handleInput(float dt) {


        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if(state!=State.GAME_OVER) {
                player.b2body.setLinearVelocity(0, 5);
                //  player.b2body.applyLinearImpulse(new Vector2(0, state==State.START?2f:7 ), player.b2body.getWorldCenter(), true);
                if (state == State.START) {
                    state = State.PLAY;
                    Hud.toPlay = true;

                }
            }
            else {
                game.reload();
            }



        }

        if (Gdx.input.justTouched()) {
            if (state != State.GAME_OVER) {
                player.b2body.setLinearVelocity(0, 5);
                //  player.b2body.applyLinearImpulse(new Vector2(0, state==State.START?2f:7 ), player.b2body.getWorldCenter(), true);
                if (state == State.START) {
                    state = State.PLAY;
                    Hud.toPlay = true;

                }
            } else {
                game.reload();
            }


        }}

    public Flappy getPlayer() {
        return player;
    }


    public void gameOver(){
        state=State.GAME_OVER;
        Hud.toGameOver=true;




    }
}

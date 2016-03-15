package com.dsiedlarz.flappymac.Sceenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dsiedlarz.flappymac.FlappyMac;
import com.dsiedlarz.flappymac.Screens.PlayScreen;

/**
 * Created by Dawid on 2016-03-11.
 */
public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private PlayScreen screen;


    private static Integer score;

    public static Label scoreLabel;
    public static Label startLabel;
    public static Label endLabel;
    public static Label endScoreLabel;
    public static Label bestScoreLabel;
    public static Label restartLabel;
   public static boolean toStart,toPlay,toGameOver;

    Preferences prefs;


    public Hud(SpriteBatch sb,PlayScreen screen) {
        toPlay=false;
        toStart=false;
        toGameOver=false;
        score = 0;

        prefs = Gdx.app.getPreferences("MyPreferences");
        if(!prefs.contains("bestScore")){
            prefs.putInteger("bestScore",score);
        }

        viewport = new FitViewport(FlappyMac.V_WIDTH, FlappyMac.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        scoreLabel = new Label(String.format("%03d", score),  new Label.LabelStyle(new BitmapFont(),com.badlogic.gdx.graphics.Color.WHITE));
        startLabel = new Label(String.format("Get ready"), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        endLabel= new Label(String.format("Game Over"), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
         restartLabel = new Label(String.format("Tap to restart"), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));



        table.add(scoreLabel).pad(4);
        table.row();
        table.add(startLabel).pad(4);

        stage.addActor(table);


    }

    @Override
    public void dispose() {
        stage.dispose();

    }

    public void update(float dt){

     switch (PlayScreen.state){

         case START:


             break;
         case PLAY:
             if(toPlay){
                 stage.clear();
                 Table table = new Table();
                 table.top();
                 table.setFillParent(true);
                 table.row();
                 table.row();
                 table.add(scoreLabel).pad(4);
                 stage.addActor(table);
             }

             break;
         case GAME_OVER:
             if(toGameOver){

                 if(prefs.getInteger("bestScore")<=score){
                     prefs.remove("bestScore");
                     prefs.putInteger("bestScore",score);
                     prefs.flush();
                 }
                 endScoreLabel = new Label(String.format("Your score: %03d", score), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
                 bestScoreLabel = new Label(String.format("Best score: %03d", prefs.getInteger("bestScore")), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));


                 stage.clear();
                 Table table = new Table();
                 table.top();
                 table.setFillParent(true);
                 table.row();
                 table.row();
                 table.add(endLabel).pad(4);
                 table.row();
                 table.add(endScoreLabel).pad(4);
                 table.row();
                 table.add(bestScoreLabel).pad(4);
                 table.row();
                 table.add(restartLabel).pad(4);
                 table.row();



                 stage.addActor(table);
                 toGameOver=false;
             }
             break;
     }




    }
    public static void addScore(int value){
        score+=value;
        scoreLabel.setText(String.format("%03d", score));

    }

}

package com.xqs.mypaoku.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.stage.GameStage;


/**
 * 主游戏场景
 *
 */
public class GameScreen extends ScreenAdapter {

    private MyPaokuGame mainGame;

    /** 主游戏舞台 */
    private GameStage gameStage;


    public GameScreen(MyPaokuGame mainGame) {
        this.mainGame = mainGame;
        init();
    }

    private void init() {
        // 创建主游戏舞台
        gameStage = new GameStage(
                getMainGame(),
                new ScalingViewport(Scaling.fit,
                        mainGame.getWorldWidth(),
                        mainGame.getWorldHeight()
                )
        );



        // 将输入处理设置到主游戏舞台
        Gdx.input.setInputProcessor(gameStage);
    }



    @Override
    public void render(float delta) {
        super.render(delta);

        // 更新并绘制舞台（主游戏舞台优先处理）

        if (gameStage.isVisible()) {
            gameStage.act();
            gameStage.draw();
        }

    }

    @Override
    public void dispose() {
        super.dispose();
        // 场景销毁时, 同时销毁所有的舞台
        if (gameStage != null) {
            gameStage.dispose();
        }
    }

    public MyPaokuGame getMainGame() {
        return mainGame;
    }

    public GameStage getGameStage() {
        return gameStage;
    }


}



























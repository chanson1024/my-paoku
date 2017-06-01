package com.xqs.mypaoku.actor.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.Bullet;
import com.xqs.mypaoku.actor.Player;
import com.xqs.mypaoku.actor.Tower;
import com.xqs.mypaoku.actor.base.BaseBullet;
import com.xqs.mypaoku.actor.base.BaseEnemy;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.stage.GameStage;
import com.xqs.mypaoku.util.GameState;
import com.xqs.mypaoku.util.TextureUtil;
import com.xqs.mypaoku.util.Util;

/**
 * Created by Administrator on 2017/3/4 0004.
 */

public class CaihuaEnemy extends BaseEnemy {

    public final static String TAG="CaihuaEnemy";

    public final static int StartY=150;



    public CaihuaEnemy(MyPaokuGame mainGame) {
        super(mainGame);

        this.mainGame=mainGame;

        position.x=this.mainGame.getWorldWidth();
        position.y=StartY;

        setStopX(Tower.getStopX());

        setPosition(position.x,position.y);

        walk();
    }

    @Override
    public void fire() {
        super.fire();
        GameStage.getInstance(mainGame).generateEnemyBullet(BaseBullet.CAIHUA,getX(),getY()+getHeight()/2);
    }

    @Override
    public Animation getWalkAnimation() {
        TextureAtlas.AtlasRegion walkRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_CAIHUA_WALK);
        Animation walkAnimation = new  Animation(0.08F, TextureUtil.getTextureRegions(walkRegion,1,6));
        return walkAnimation;
    }

    @Override
    public Animation getFireAnimation() {
        return getWalkAnimation(); // 与walk相同
    }

    @Override
    public Animation getHurtAnimation() {
        TextureAtlas.AtlasRegion deadRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_CAIHUA_DEAD);
        Animation hurtAnimation = new  Animation(0.1F, TextureUtil.getTextureRegions(deadRegion,1,6));
        return hurtAnimation;
    }


    @Override
    public void act(float delta) {

        super.act(delta);
    }

    @Override
    public void orderAct(float delta, int counter) {
        if(counter%20==0) {
            if(getX()>getStopX() && getX()<(mainGame.getWorldWidth()-280) && getState()==WALK) {
                fire();
            }
            // check if enemy was close to tower
            if(getState() == WALK && getX()<=getStopX()){
                GameStage.getInstance(mainGame).minusLife();
            }
        }
    }
}

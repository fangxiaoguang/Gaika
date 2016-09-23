package com.game.gaika.sprite;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.flyweight.NormalFlyweight;

/**
 * Created by fangxg on 2015/6/17.
 */
public class NormalSprite extends BaseSprite {
    public NormalSprite() {
        super();
    }

    public NormalSprite(float pX, float pY) {
        super(pX, pY);
    }

    public NormalSprite(float pX, float pY, String pTexKey) {
        this(pX, pY, pTexKey, 0);
    }

    public NormalSprite(float pX, float pY, String pTexKey, int pTiledIndex) {
        this(pX, pY, pTexKey, pTiledIndex, null);
    }

    public NormalSprite(float pX, float pY, String pTexKey, int pTiledIndex, TouchMessage pTouchMessage) {
        this(pX, pY, pTexKey, pTiledIndex, pTouchMessage, 1.0f);
    }

    public NormalSprite(float pX, float pY, String pTexKey, int pTiledIndex, TouchMessage pTouchMessage, float pScale) {
        this(pX, pY, pTexKey, pTiledIndex, pTouchMessage, pScale, pScale);
    }

    public NormalSprite(float pX, float pY, String pTexKey, int pTiledIndex, TouchMessage pTouchMessage, float pScaleX, float pScaleY) {
        this(pX, pY, pTexKey, pTiledIndex, pTouchMessage, pScaleX, pScaleY, 1.0f);
    }

    public NormalSprite(float pX, float pY, String pTexKey, int pTiledIndex, TouchMessage pTouchMessage, float pScaleX, float pScaleY, float pAlpha) {
        super(pX, pY);
        NormalFlyweight normalFlyweight = new NormalFlyweight(0.0f, 0.0f, pTexKey, pTiledIndex);
        normalFlyweight.setScaleXY(pScaleX, pScaleY);
        normalFlyweight.setAlpha(pAlpha);
        setFlyweight(normalFlyweight);
        setTouchMessage(pTouchMessage);
    }
}





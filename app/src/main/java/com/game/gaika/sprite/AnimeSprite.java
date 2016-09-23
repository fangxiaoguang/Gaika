package com.game.gaika.sprite;

import com.game.frame.sprite.BaseSprite;
import com.game.frame.FSM.TouchMessage;
import com.game.frame.flyweight.AnimeFlyweight;

/**
 * Created by fangxg on 2015/7/26.
 */
public class AnimeSprite extends BaseSprite {
    public AnimeSprite(float pX, float pY, String pTexKey, int pBeginTiledIndex, int pEndTiledIndex, int pTimeMS) {
        this(pX, pY, pTexKey, pBeginTiledIndex, pEndTiledIndex, pTimeMS, null);
    }

    public AnimeSprite(float pX, float pY, String pTexKey, int pBeginTiledIndex, int pEndTiledIndex, int pTimeMS, TouchMessage pTouchMessage) {
        super(pX, pY);
        AnimeFlyweight animeFlyweight = new AnimeFlyweight(0.0f, 0.0f, pTexKey, pBeginTiledIndex, pEndTiledIndex, pTimeMS);
        setFlyweight(animeFlyweight);
        setTouchMessage(pTouchMessage);
    }

    public AnimeSprite(float pX, float pY, String pTexKey, int[] ptiledIndexs, int pTimeMS) {
        this(pX, pY, pTexKey, ptiledIndexs, pTimeMS, null);
    }

    public AnimeSprite(float pX, float pY, String pTexKey, int[] ptiledIndexs, int pTimeMS, TouchMessage pTouchMessage) {
        super(pX, pY);
        AnimeFlyweight animeFlyweight = new AnimeFlyweight(0.0f, 0.0f, pTexKey, ptiledIndexs, pTimeMS);
        setFlyweight(animeFlyweight);
        setTouchMessage(pTouchMessage);
    }
}

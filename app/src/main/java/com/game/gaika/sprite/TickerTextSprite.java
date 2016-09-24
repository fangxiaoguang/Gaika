package com.game.gaika.sprite;

import com.game.gaika.flyweight.TickerTextFlyweight;

import org.andengine.opengl.font.Font;

/**
 * Created by fangxg on 2015/6/20.
 */
public class TickerTextSprite extends BaseSprite {
    public TickerTextSprite(float pX, float pY, String pText, Font pFont) {
        super(pX, pY);

        TickerTextFlyweight textFlyweight = new TickerTextFlyweight(pText, pFont);
        setFlyweight(textFlyweight);
    }
}

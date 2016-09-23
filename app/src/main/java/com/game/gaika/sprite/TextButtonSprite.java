package com.game.gaika.sprite;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.flyweight.NormalFlyweight;
import com.game.gaika.flyweight.TextFlyweight;
import com.game.gaika.texture.TexRegionManager;

/**
 * Created by fangxg on 2015/7/24.
 */
public class TextButtonSprite extends BaseSprite {
    public TextButtonSprite(float pX, float pY, String pText, TouchMessage pTouchMessage) {
     this(pX,pY,pText,pTouchMessage, 1.0f);
    }
    public TextButtonSprite(float pX, float pY, String pText, TouchMessage pTouchMessage, float pScale) {
        super(pX, pY);
        setScale(pScale);

        NormalFlyweight bkFlyweight = new NormalFlyweight(0.0f, 0.0f, "cary_wn2");

        TextFlyweight textFlyweight = new TextFlyweight(true, pText, TexRegionManager.getInstance().getFont16());
        bkFlyweight.addChildFlyweight(textFlyweight);
        
        setFlyweight(bkFlyweight);

        setTouchMessage(pTouchMessage);
    }
}

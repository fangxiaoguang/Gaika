package com.game.frame.sprite;

import com.game.frame.flyweight.TextFlyweight;
import com.game.frame.sprite.BaseSprite;

import org.andengine.opengl.font.Font;
import org.andengine.util.color.Color;

/**
 * Created by fangxg on 2015/6/17.
 */
public class TextSprite extends BaseSprite {

    public TextSprite(float pX, float pY, boolean pLeftAlign, String pText, Font pFont) {
        this(pX, pY, pLeftAlign, pText, pFont, Color.WHITE);
    }

    public TextSprite(float pX, float pY, boolean pLeftAlign, String pText, Font pFont, Color pColor) {
        super(pX, pY);

        String[] ss = pText.split("\\|");

        TextFlyweight textFlyweight = new TextFlyweight(pLeftAlign, ss[0], pFont, pColor);
        for (int i = 1; i < ss.length; i++) {
            TextFlyweight subTextFlyweight = new TextFlyweight(0.0f, 0.0f + 30.f * i, pLeftAlign, ss[i], pFont, pColor);
            textFlyweight.addChildFlyweight(subTextFlyweight);
        }
        setFlyweight(textFlyweight);

    }

   /* @Override
    public RectangularShape getShape() {

        RectangularShape text = super.getShape();


        return text;
    }*/

}

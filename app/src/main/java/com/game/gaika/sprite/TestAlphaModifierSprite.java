package com.game.gaika.sprite;

import com.game.gaika.flyweight.NormalFlyweight;

/**
 * Created by fangxg on 2015/9/3.
 */
public class TestAlphaModifierSprite extends BaseSprite {
    public TestAlphaModifierSprite() {
        super();
        NormalFlyweight wnFlyweight = new NormalFlyweight(0.0f, 0.0f, "weap_wn1");
        wnFlyweight.setAlpha(0.5f);
       // wnFlyweight.setScale(2);
        setFlyweight(wnFlyweight);

        NormalFlyweight flagFlyweight = new NormalFlyweight(169.0f, 13.0f, "flag03", 0);
       flagFlyweight.setAlpha(1.0f);
        wnFlyweight.addChildFlyweight(flagFlyweight);
    }
}

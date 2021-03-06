package com.game.gaika.flyweight;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.texture.TexRegionManager;

import org.andengine.entity.shape.RectangularShape;

/**
 * Created by fangxg on 2015/6/25.
 */
public class DelayFlyweight extends BaseFlyweight {
    private  float delayTimeS;
    public DelayFlyweight(float pDelayTimeS){
        delayTimeS = pDelayTimeS;

    }
    @Override
    public RectangularShape getShapeSelf(TouchMessage pTouchMessage) {
        return TexRegionManager.getDelaySprite(delayTimeS, pTouchMessage);
    }
}

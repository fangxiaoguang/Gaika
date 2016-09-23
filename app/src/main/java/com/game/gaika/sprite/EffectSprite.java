package com.game.gaika.sprite;

import com.game.frame.sprite.BaseSprite;
import com.game.gaika.data.EffectNode;
import com.game.frame.flyweight.AnimeFlyweight;

import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;

/**
 * Created by fangxg on 2015/7/24.
 */
public class EffectSprite extends BaseSprite {
    public EffectSprite(EffectNode pEffectNode) {
        super(pEffectNode.getPixelX(), pEffectNode.getPixelY());
        AnimeFlyweight animeFlyweight = new AnimeFlyweight(0.0f, 0.0f, "marker04", 0, 5, 100);

        if(pEffectNode.delayS >0){
            animeFlyweight.setAlpha(0.0f);
            this.registerEntityModifier(new SequenceEntityModifier(new DelayModifier(pEffectNode.delayS), new AlphaModifier(0.001f, 0.0f, 1.0f)));
        }

        setFlyweight(animeFlyweight);
    }
}

package com.game.frame.flyweight;

import com.game.gaika.FSM.TouchMessage;
import com.game.frame.texture.TexRegionManager;

import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.shape.RectangularShape;
import org.andengine.entity.sprite.TiledSprite;

/**
 * Created by fangxg on 2015/6/17.
 */
public class AlphaFlyweight extends NormalFlyweight {

    int time1MS, time2MS, time3MS;

    public AlphaFlyweight(float pOffsetX, float pOffsetY, String pTexKey, int pTime1MS, int pTime2MS, int pTime3MS) {
        this(pOffsetX, pOffsetY, pTexKey, 0, pTime1MS, pTime2MS, pTime3MS);
    }

    public AlphaFlyweight(float pOffsetX, float pOffsetY, String pTexKey, int pTiledIndex, int pTime1MS, int pTime2MS, int pTime3MS) {
        super(pOffsetX, pOffsetY, pTexKey, pTiledIndex);
        time1MS = pTime1MS;
        time2MS = pTime2MS;
        time3MS = pTime3MS;

    }

    @Override
    public RectangularShape getShapeSelf(TouchMessage pTouchMessage) {
        TiledSprite tiledSprite = TexRegionManager.getSprite(offsetX, offsetY, texKey, tileIndex,pTouchMessage);

       tiledSprite.registerEntityModifier(new SequenceEntityModifier(new AlphaModifier(time1MS / 1000.0f, 0.0f, 1.0f), new DelayModifier(time2MS / 1000.0f), new AlphaModifier(time3MS / 1000.0f, 1.0f,
                0.0f)));
        return tiledSprite;
    }
}

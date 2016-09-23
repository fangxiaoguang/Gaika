package com.game.frame.flyweight;

import com.game.frame.FSM.TouchMessage;
import com.game.frame.texture.TexRegionManager;

import org.andengine.entity.shape.RectangularShape;

/**
 * Created by fangxg on 2015/6/9.
 */
public class NormalFlyweight extends BaseFlyweight {

    protected int tileIndex;
    protected String texKey;
    public NormalFlyweight(float pOffsetX, float pOffsetY, String pTexKey) {
        this(pOffsetX, pOffsetY, pTexKey, 0);

    }
    public NormalFlyweight(float pOffsetX, float pOffsetY, String pTexKey, int pTiledIndex) {
        super(pOffsetX, pOffsetY);
        texKey = pTexKey;
        tileIndex = pTiledIndex;
    }
    @Override
    public RectangularShape getShapeSelf(TouchMessage pTouchMessage) {
        return TexRegionManager.getSprite(offsetX, offsetY, texKey, tileIndex, pTouchMessage);
    }
}

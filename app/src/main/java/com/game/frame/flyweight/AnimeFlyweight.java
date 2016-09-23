package com.game.frame.flyweight;

import com.game.frame.FSM.TouchMessage;
import com.game.frame.texture.TexRegionManager;

import org.andengine.entity.shape.RectangularShape;

/**
 * Created by fangxg on 2015/6/16.
 */
public class AnimeFlyweight extends BaseFlyweight {
    private enum EAnimeType {ANIME_TYPE_BEGIN_TO_END, ANIME_TYPE_INDEXS}

    protected String texKey;

    private EAnimeType type;
    private int beginTiledIndex, endTiledIndex;
    private int[] tiledIndexs = new int[0];

    private int timeMS; //ms

    public AnimeFlyweight(float pOffsetX, float pOffsetY, String pTexKey, int pBeginTiledIndex, int pEndTiledIndex, int pTimeMS) {
        super(pOffsetX, pOffsetY);
        texKey = pTexKey;
        type = EAnimeType.ANIME_TYPE_BEGIN_TO_END;
        beginTiledIndex = pBeginTiledIndex;
        endTiledIndex = pEndTiledIndex;
        timeMS = pTimeMS;
    }

    public AnimeFlyweight(float pOffsetX, float pOffsetY, String pTexKey, int[] pTiledIndexs, int pTimeMS) {
        super(pOffsetX, pOffsetY);
        texKey = pTexKey;
        type = EAnimeType.ANIME_TYPE_INDEXS;
        tiledIndexs = pTiledIndexs;
        timeMS = pTimeMS;
    }

    @Override
    public RectangularShape getShapeSelf(TouchMessage pTouchMessage) {

        if (type == EAnimeType.ANIME_TYPE_BEGIN_TO_END) {
            return TexRegionManager.getAnimatedSprite(offsetX, offsetY, texKey, beginTiledIndex, endTiledIndex, timeMS, pTouchMessage);
        } else { // type == EAnimeType.ANIME_TYPE_INDEXS
            return TexRegionManager.getAnimatedSprite(offsetX, offsetY, texKey, tiledIndexs, timeMS, pTouchMessage);
        }
    }
}

package com.game.gaika.flyweight;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.scene.hud.BattlefieldHUDScene;

import org.andengine.entity.IEntity;
import org.andengine.entity.shape.RectangularShape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fangxg on 2015/6/9.
 */
public abstract class BaseFlyweight {
    protected float offsetX;
    protected float offsetY;

    protected float scaleX;
    protected float scaleY;
    protected float alpha;

    protected List<BaseFlyweight> childFlyweightList;

    private int tag;

    private static Map<Integer, IEntity> regedEntitys = new HashMap<>();


    public BaseFlyweight() {
        this(0.0f, 0.0f);
    }

    public BaseFlyweight(float pOffsetX, float pOffsetY) {
        this(pOffsetX, pOffsetY, 1.0f);
    }

    public BaseFlyweight(float pOffsetX, float pOffsetY, float pScale) {
        this(pOffsetX, pOffsetY, pScale, pScale);
    }

    public BaseFlyweight(float pOffsetX, float pOffsetY, float pScaleX, float pScaleY) {
        this(pOffsetX, pOffsetY, pScaleX, pScaleY, 1.0f);
    }

    public BaseFlyweight(float pOffsetX, float pOffsetY, float pScaleX, float pScaleY, float pAlpha) {
        offsetX = pOffsetX;
        offsetY = pOffsetY;
        scaleX = pScaleX;
        scaleY = pScaleY;
        alpha = pAlpha;
        childFlyweightList = new ArrayList<>();
        tag = Integer.MIN_VALUE;
    }

    public void setScale(float pScale) {
        setScaleXY(pScale, pScale);
    }

    public void setScaleXY(float pScaleX, float pScaleY) {
        scaleX = pScaleX;
        scaleY = pScaleY;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getAlpha() {
        return alpha;
    }

    public void addChildFlyweight(BaseFlyweight pChildFlyweight) {
        childFlyweightList.add(pChildFlyweight);
    }

    public void setTag(int pTag) {
        tag = pTag;
    }

    public static void clearTagedEntity() {
        regedEntitys.clear();
    }

    public static void regTagedEntity(Integer pTag, IEntity pEntity) {
        regedEntitys.put(pTag, pEntity);
    }

    public static IEntity getTagedEntity(int pTag) {
        return regedEntitys.get(pTag);
    }

    public abstract RectangularShape getShapeSelf(TouchMessage pTouchMessage);

    public final RectangularShape getShape(TouchMessage pTouchMessage) {
        RectangularShape bkShape = getShapeSelf(pTouchMessage);

        bkShape.setScaleCenter(0.0f, 0.0f);
        bkShape.setScale(scaleX, scaleY);
        bkShape.setAlpha(alpha);

        for (BaseFlyweight flyweight : childFlyweightList) {
            RectangularShape childShape = flyweight.getShape(null);
            bkShape.attachChild(childShape);
        }

        if (tag != Integer.MIN_VALUE) {
            regTagedEntity(tag, bkShape);
        }

        return bkShape;
    }
}

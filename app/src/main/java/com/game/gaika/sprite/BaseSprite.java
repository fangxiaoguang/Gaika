package com.game.gaika.sprite;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.flyweight.BaseFlyweight;
import com.game.gaika.scene.BaseLogicScene;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.shape.RectangularShape;

/**
 * Created by fangxg on 2015/6/16.
 */
public abstract class BaseSprite {

    private String id;

    private float x, y;
    private int z;
    private BaseFlyweight flyweight;
    private TouchMessage touchMessage;

    private BaseLogicScene parentScene;

    private float scaleCenterX;
    private float scaleCenterY;

    private float scaleX;
    private float scaleY;
    protected float alpha;

    protected IEntityModifier modifier;
    protected IEntityModifier deepModifier;

    public BaseSprite() {
        this(0.0f, 0.0f);
    }

    public BaseSprite(float pX, float pY) {
        this(pX, pY, 0);
    }

    public BaseSprite(float pX, float pY, int pZ) {
        x = pX;
        y = pY;
        z = pZ;
        scaleCenterX = 0.0f;
        scaleCenterY = 0.0f;
        scaleX = 1.0f;
        scaleY = 1.0f;
        alpha = 1.0f;
    }


    public void setX(float pX) {
        x = pX;
    }
    public float getX() {
        return x;
    }
    public void setY(float pY) {
        y = pY;
    }
    public float getY() {
        return y;
    }
    public void setXY(float pX, float pY){
        x = pX;
        y = pY;
    }
    public void setZ(int pZ) {
        z = pZ;
    }
    public int getZ() {
        return z;
    }

    public void setFlyweight(BaseFlyweight pFlyweight) {
        flyweight = pFlyweight;
    }
    public BaseFlyweight getFlyweight() {
        return flyweight;
    }

    public void setTouchMessage(TouchMessage pMessage) {
        touchMessage = pMessage;
    }
    public TouchMessage getTouchMessage() {
        return touchMessage;
    }

    public void setParentScene(BaseLogicScene pParentScene) {
        parentScene = pParentScene;
    }
    public BaseLogicScene getParentScene() {
        return parentScene;
    }


    public void setScaleCenter(float pScaleCenterX, float pScaleCenterY) {
        this.scaleCenterX = pScaleCenterX;
        this.scaleCenterY = pScaleCenterY;
    }
    public void setScale(float pScale) {
        this.scaleX = pScale;
        this.scaleY = pScale;
    }
    public void setScaleX(float pScaleX) {
        this.scaleX = pScaleX;
    }
    public void setScaleY(float pScaleY) {
        this.scaleY = pScaleY;
    }
    public void setScaleXY(float pScaleX, float pScaleY) {
        this.scaleX = pScaleX;
        this.scaleY = pScaleY;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void registerEntityModifier(final IEntityModifier pEntityModifier) {
        modifier = pEntityModifier;
    }
    public void registerDeepEntityModifier(final IEntityModifier pDeepEntityModifier) {
        deepModifier = pDeepEntityModifier;
    }
    private static void registerDeepEntityModifier(IEntity pEntity, IEntityModifier pDeepEntityModifier) {
        pEntity.registerEntityModifier(pDeepEntityModifier.deepCopy());

        if (pEntity.getChildCount() == 0) {
            return;
        } else {
            for (int i = 0; i < pEntity.getChildCount(); i++) {
                IEntity childEntity = pEntity.getChildByIndex(i);
                registerDeepEntityModifier(childEntity, pDeepEntityModifier);
            }
        }
    }
    private static void setDeepAlpha(IEntity pEntity, float pAlpha) {
        pEntity.setAlpha(pEntity.getAlpha() * pAlpha);

        if (pEntity.getChildCount() == 0) {
            return;
        } else {
            for (int i = 0; i < pEntity.getChildCount(); i++) {
                IEntity childEntity = pEntity.getChildByIndex(i);
                setDeepAlpha(childEntity, pAlpha);
            }
        }
    }

    public RectangularShape getShape() {
        if (flyweight != null) {
            RectangularShape shape = flyweight.getShape(touchMessage);

            shape.setPosition(shape.getX() + x, shape.getY() + y);
//            shape.setZIndex(z);
            shape.setScaleCenter(scaleCenterX, scaleCenterY);
            shape.setScale(this.scaleX * shape.getScaleX(), this.scaleY * shape.getScaleY());
            setDeepAlpha(shape, this.alpha);

            if (modifier != null) {
                shape.registerEntityModifier(modifier);
            }
            if (deepModifier != null) {
                registerDeepEntityModifier(shape, deepModifier);
            }
            return shape;
        } else {
            return null;
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

package com.game.frame.scene.camera;

import com.game.gaika.data.Pair;
import com.game.frame.scene.BaseLogicScene;

/**
 * Created by fangxg on 2015/8/15.
 */
public abstract class BaseLogicCamera {
    protected BaseLogicScene parentScene;
    protected float sceneWidth;
    protected float sceneHeight;

    public BaseLogicCamera(float pSceneWidth, float pSceneHeight) {
        sceneWidth = pSceneWidth;
        sceneHeight = pSceneHeight;
    }
    public void setParentScene(BaseLogicScene parentScene) {
        this.parentScene = parentScene;
    }

    public abstract CameraRange getCameraRenge();

    public float getCameraWidth() {
        CameraRange cr = getCameraRenge();
        return cr.xMax - cr.xMin;
    }

    public float getCameraHeight() {
        CameraRange cr = getCameraRenge();
        return cr.yMax - cr.yMin;
    }

    public Pair<Float> getCenterXY() {
        CameraRange cr = getCameraRenge();
        return new Pair<>(cr.xMin + (cr.xMax - cr.xMin) / 2, cr.yMin + (cr.yMax - cr.yMin) / 2);
    }

    public abstract void setCente(float pCenterX, float pCenterY);
}

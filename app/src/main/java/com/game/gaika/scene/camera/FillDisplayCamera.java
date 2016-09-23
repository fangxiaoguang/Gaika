package com.game.gaika.scene.camera;

import com.game.gaika.data.GameSetup;

/**
 * Created by fangxg on 2015/8/15.
 */
public class FillDisplayCamera extends BaseLogicCamera {
    public FillDisplayCamera(float pSceneWidth, float pSceneHeight) {
        super(pSceneWidth,  pSceneHeight);
    }

    @Override
    public CameraRange getCameraRenge() {
        float r = 1.0f;

        if (GameSetup.deviceHeightPixels > GameSetup.deviceWidthPixels) {
            r = GameSetup.deviceHeightPixels / GameSetup.deviceWidthPixels;
        } else {
            r = GameSetup.deviceWidthPixels / GameSetup.deviceHeightPixels;
        }

        float xMin = (sceneWidth - sceneHeight * r) / 2;
        float xMax = (sceneWidth + sceneHeight * r) / 2;
        float yMin = 0;
        float yMax = 0 + sceneHeight;
        return new CameraRange(xMin, yMin, xMax, yMax);
    }

    @Override
    public void setCente(float pCenterX, float pCenterY) {

    }
}

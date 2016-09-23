package com.game.frame.scene.camera;

import com.game.gaika.data.GameSetup;
import com.game.frame.scene.BaseLogicScene;
import com.game.gaika.scene.hud.BattlefieldHUDScene;
import com.game.frame.scene.hud.HUDScene;

/**
 * Created by fangxg on 2015/8/15.
 */
public class ZoomSceneCamera extends BaseLogicCamera {
    float zoom;

    public ZoomSceneCamera(float pSceneWidth, float pSceneHeight, float pZoom) {
        super(pSceneWidth, pSceneHeight);
        zoom = pZoom;
    }

    @Override
    public CameraRange getCameraRenge() {
        float xMin = 0;
        float xMax = GameSetup.deviceWidthPixels / zoom;
        float yMin = 0;
        float yMax = GameSetup.deviceHeightPixels / zoom;


        BaseLogicScene.SceneValueMap sceneValues = parentScene.getSceneValuesMap();

        float centerX = sceneValues.getFloat("CameraCenterX");
        float centerY = sceneValues.getFloat("CameraCenterY");

        float offsetX = centerX - GameSetup.deviceWidthPixels / zoom / 2.0f;
        xMin += offsetX;
        xMax += offsetX;

        float offsetY = centerY - GameSetup.deviceHeightPixels / zoom / 2.0f;
        yMin += offsetY;
        yMax += offsetY;

        return new CameraRange(xMin, yMin, xMax, yMax);
    }

    @Override
    public void setCente(float pCenterX, float pCenterY) {
        float centerX = pCenterX;
        float centerY = pCenterY;
        CameraRange cr = getCameraRenge();
        float w = cr.xMax - cr.xMin;
        float h = cr.yMax - cr.yMin;

        if (centerX < w / 2.0f) {
            centerX = w / 2.0f;
        } else if (centerX > sceneWidth - w / 2.0f) {
            centerX = sceneWidth - w / 2.0f;
        }

        if (centerY < h / 2.0f) {
            centerY = h / 2.0f;
        } else if (centerY > sceneHeight - h / 2.0f) {
            centerY = sceneHeight - h / 2.0f;
        }

        BaseLogicScene.SceneValueMap sceneValues = parentScene.getSceneValuesMap();
        sceneValues.setFloat("CameraCenterX", centerX);
        sceneValues.setFloat("CameraCenterY", centerY);

//        BattlefieldHUDScene battlefieldHUDScene = (BattlefieldHUDScene) parentScene.getHUDScene();
        HUDScene hudScene = (HUDScene) parentScene.getHUDScene();
        if (hudScene != null) {
            if(hudScene instanceof  BattlefieldHUDScene) {
                ((BattlefieldHUDScene)hudScene).setSmallMapBoxPoint(centerX - parentScene.getLogicCamera().getCameraWidth() / 2.0f, centerY - parentScene.getLogicCamera().getCameraHeight() / 2.0f);
            }
        }
    }
}

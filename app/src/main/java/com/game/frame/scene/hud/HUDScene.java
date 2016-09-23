package com.game.frame.scene.hud;

import com.game.gaika.data.ID.SCENE_ID;
import com.game.frame.scene.BaseLogicScene;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.Scene;


/**
 * Created by fangxg on 2015/6/22.
 */
public abstract class HUDScene extends BaseLogicScene {
    public HUDScene(SCENE_ID pSceneId, float pWidth, float pHeight) {
        super(pSceneId, pWidth, pHeight);
    }

    @Override
    public Scene createBackupScene() {
        return new HUD();
    }

    ;
}

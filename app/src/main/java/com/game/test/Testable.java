package com.game.test;

import com.game.gaika.data.ID;
import com.game.frame.scene.BaseLogicScene;

/**
 * Created by devuser1 on 2016/9/22.
 */

public interface Testable {
    ID.SCENE_ID getCurrentSceneId();
    BaseLogicScene getCurrentScene();
    GameElement getElement(String id);
}

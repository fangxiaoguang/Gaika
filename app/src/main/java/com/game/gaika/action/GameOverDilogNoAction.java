package com.game.gaika.action;

import com.game.gaika.data.ID;
import com.game.gaika.scene.BaseLogicScene;
import com.game.gaika.scene.SceneFactory;
import com.game.gaika.scene.SceneManager;

import static com.game.gaika.data.ID.SCENE_INIT_TYPE.NOT_INIT;

/**
 * Created by fangxg on 2015/7/25.
 */
public class GameOverDilogNoAction implements BaseAction {
    @Override
    public void doAction() {
        ID.SCENE_ID currentSceneID = SceneManager.getTopBaseLogicScene().getSceneId();
        BaseLogicScene scene = SceneFactory.createScene(currentSceneID, NOT_INIT);
        SceneManager.render(scene);
    }
}

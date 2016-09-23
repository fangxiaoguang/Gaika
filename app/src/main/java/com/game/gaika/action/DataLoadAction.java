package com.game.gaika.action;

import com.game.gaika.data.ID;
import com.game.gaika.scene.LoadGameScene;
import com.game.gaika.scene.SceneManager;

/**
 * Created by fangxg on 2015/7/24.
 */
public class DataLoadAction implements BaseAction {
    @Override
    public void doAction() {
        ID.SCENE_ID secenID = SceneManager.getTopBaseLogicScene().getSceneId();
        LoadGameScene loadGameScene = new LoadGameScene(true, secenID);
        SceneManager.render(loadGameScene );
    }
}

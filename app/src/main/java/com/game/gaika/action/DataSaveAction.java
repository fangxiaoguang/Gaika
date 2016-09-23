package com.game.gaika.action;

import com.game.gaika.data.ID;
import com.game.gaika.scene.SaveGameScene;
import com.game.gaika.scene.SceneManager;

/**
 * Created by fangxg on 2015/7/24.
 */
public class DataSaveAction implements BaseAction {
    @Override
    public void doAction() {
        ID.SCENE_ID secenID = SceneManager.getTopBaseLogicScene().getSceneId();
        SaveGameScene saveGameScene = new SaveGameScene(true, secenID);
        SceneManager.render(saveGameScene);
    }
}

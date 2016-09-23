package com.game.gaika.action;

import com.game.gaika.data.ID;
import com.game.gaika.scene.BattlefieldInfoScene;
import com.game.gaika.scene.SceneManager;

/**
 * Created by fangxg on 2015/7/24.
 */
public class BattlefieldInfoAction implements BaseAction {
    @Override
    public void doAction() {

        ID.SCENE_ID secenID = SceneManager.getTopBaseLogicScene().getSceneId();
        BattlefieldInfoScene battlefieldInfoScene = new BattlefieldInfoScene(secenID);
        SceneManager.render(battlefieldInfoScene);
    }
}

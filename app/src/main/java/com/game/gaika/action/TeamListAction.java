package com.game.gaika.action;

import com.game.gaika.data.ID;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.TeamListScene;

/**
 * Created by fangxg on 2015/7/24.
 */
public class TeamListAction implements BaseAction {
    @Override
    public void doAction() {

        ID.SCENE_ID secenID = SceneManager.getTopBaseLogicScene().getSceneId();
        TeamListScene teamListScene = new TeamListScene(true, secenID);
        SceneManager.render(teamListScene);
    }
}

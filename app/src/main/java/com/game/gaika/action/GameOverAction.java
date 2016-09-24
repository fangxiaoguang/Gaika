package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.ID;
import com.game.gaika.scene.BaseLogicScene;
import com.game.gaika.scene.SceneFactory;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.dialg.Dialg6YesNoDialogScene;

import static com.game.gaika.data.ID.SCENE_INIT_TYPE.NOT_INIT;

/**
 * Created by fangxg on 2015/7/25.
 */
public class GameOverAction implements BaseAction {
    @Override
    public void doAction() {
        ID.SCENE_ID currentSceneID = SceneManager.getTopBaseLogicScene().getSceneId();
        BaseLogicScene scene = SceneFactory.createScene(currentSceneID, NOT_INIT);


        Dialg6YesNoDialogScene yesNoDialog = new Dialg6YesNoDialogScene("结束游戏。",
                new TouchMessage(ID.MSG_ID.MSG_SCENE_HUD__GAME_OVER_DILOG_YES, null, scene),
                new TouchMessage(ID.MSG_ID.MSG_SCENE_HUD__GAME_OVER_DILOG_NO, null, scene),scene );

        scene.setDialogSecne(yesNoDialog);
        SceneManager.render(scene);
    }
}

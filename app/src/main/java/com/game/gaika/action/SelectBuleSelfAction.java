package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.ID;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.dialg.Dialg6YesNoDialogScene;

/**
 * Created by fangxg on 2015/7/24.
 */
public class SelectBuleSelfAction implements BaseAction{

    @Override
    public void doAction() {

        BattlefieldScene battlefieldScene = new BattlefieldScene(false);

        Dialg6YesNoDialogScene yesNoDialog = new Dialg6YesNoDialogScene("在当前的位置结束行动。",
                new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__SELECT_SELF_DLG_YES, null, battlefieldScene),
                new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__SELECT_SELF_DLG_NO, null, battlefieldScene),battlefieldScene );

        battlefieldScene.setDialogSecne(yesNoDialog);
        SceneManager.render(battlefieldScene);
    }
}

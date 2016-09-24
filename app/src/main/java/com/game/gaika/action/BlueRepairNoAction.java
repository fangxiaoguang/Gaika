package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.sprite.DelaySprite;

/**
 * Created by fangxg on 2015/9/1.
 */
public class BlueRepairNoAction implements BaseAction {
    @Override
    public void doAction() {
        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_TIME_OUT, null, battlefieldScene)));
        SceneManager.render(battlefieldScene);
    }
}

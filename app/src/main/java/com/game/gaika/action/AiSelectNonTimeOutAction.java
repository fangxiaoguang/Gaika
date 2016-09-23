package com.game.gaika.action;

import com.game.frame.FSM.TouchMessage;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.frame.sprite.DelaySprite;

/**
 * Created by fangxg on 2015/8/16.
 */
public class AiSelectNonTimeOutAction implements BaseAction {
    private int weaponID;

    public AiSelectNonTimeOutAction(int weaponID) {
        this.weaponID = weaponID;
    }

    @Override
    public void doAction() {
        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__CRASH_NEXT, null, battlefieldScene, weaponID)));
        SceneManager.render(battlefieldScene);
    }
}

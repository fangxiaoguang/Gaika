package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.sprite.DelaySprite;

/**
 * Created by fangxg on 2015/7/29.
 */
public class AiTurnBeginTimeOutAction implements BaseAction {
    @Override
    public void doAction() {



        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__AI_REPAIR_NEXT, null, battlefieldScene)));
        SceneManager.render(battlefieldScene);
    }
}

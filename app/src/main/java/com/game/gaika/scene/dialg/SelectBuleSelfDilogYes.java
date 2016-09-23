package com.game.gaika.scene.dialg;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.action.BaseAction;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.frame.sprite.DelaySprite;

/**
 * Created by fangxg on 2015/7/24.
 */
public class SelectBuleSelfDilogYes  implements BaseAction {
    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();
        BaseWeapon selectedWeapon = gdm.getSelectedWeapon();

        selectedWeapon.doMoveEnd();

        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__LV_UP_TIME_OUT, null, battlefieldScene, selectedWeapon.id)));
        SceneManager.render(battlefieldScene);

    }
}

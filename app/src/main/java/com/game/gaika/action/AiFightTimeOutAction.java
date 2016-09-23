package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.dialg.LvUpDialogScene;
import com.game.gaika.sound.SoundManager;
import com.game.gaika.sprite.DelaySprite;

/**
 * Created by fangxg on 2015/8/7.
 */
public class AiFightTimeOutAction implements BaseAction {
    private int aiWeaponID ;
    public AiFightTimeOutAction(int pAiWeaponID) {
        aiWeaponID = pAiWeaponID;
    }
    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();
        BaseWeapon weapon = gdm.weapons.get(aiWeaponID);

        if (weapon.getLv() > weapon.getPreLv()) {
            SoundManager.getInstance().playSound("levelup");
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
            battlefieldScene.setDialogSecne(new LvUpDialogScene(aiWeaponID, battlefieldScene));
            SceneManager.render(battlefieldScene);

            weapon.preExp = weapon.exp;
        }else {
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
            battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__LV_UP_TIME_OUT, null, battlefieldScene, aiWeaponID)));
            SceneManager.render(battlefieldScene);
        }
    }
}

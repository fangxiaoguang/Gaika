package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.dialg.LvUpDialogScene;
import com.game.frame.sprite.DelaySprite;

import static com.game.frame.sound.SoundManager.playSound;

/**
 * Created by fangxg on 2015/7/24.
 */
public class FightTimeOutAction implements BaseAction {
    private int weaponID;

    public FightTimeOutAction(int weaponID) {
        this.weaponID = weaponID;
    }

    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();
        BaseWeapon weapon = gdm.weapons.get(weaponID);

      //  weapon.doMoveEnd();
       // weapon.selected = false;
        if (weapon.getLv() > weapon.getPreLv()) {
             playSound("levelup");
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
            battlefieldScene.setDialogSecne(new LvUpDialogScene(weaponID, battlefieldScene));
            SceneManager.render(battlefieldScene);

            weapon.preExp = weapon.exp;
        }else {
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
            battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__LV_UP_TIME_OUT, null, battlefieldScene, weaponID)));
            SceneManager.render(battlefieldScene);
        }


    }
}

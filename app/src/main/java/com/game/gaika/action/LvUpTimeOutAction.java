package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.City;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.dialg.CaptureDialogScene;
import com.game.gaika.sprite.DelaySprite;

/**
 * Created by fangxg on 2015/7/26.
 */
public class LvUpTimeOutAction implements BaseAction {
    private int weaponID;

    public LvUpTimeOutAction(int weaponID) {
        this.weaponID = weaponID;
    }

    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();

        BaseWeapon weapon = gdm.weapons.get(weaponID);
        City city = gdm.getCurrentChapter().getGameMap().citys.get(weapon.x * 100 + weapon.y);

        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        if (city != null && weapon.info.capture > 0 && city.teamColor != weapon.teamColor) {

            battlefieldScene.setDialogSecne(new CaptureDialogScene(weaponID, battlefieldScene));

            int oldCapture = city.capture;
            int newCapture = oldCapture - weapon.info.capture * weapon.getLifeEx();
            if (newCapture <= 0) {
                city.teamColor = weapon.teamColor;
                city.country = weapon.info.country;
                city.capture = 0;
            } else {
                city.capture = newCapture;
            }
        }else{
            DelaySprite delaySprite = new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__CAPTURING_TIME_OUT, null, battlefieldScene, weaponID));
            battlefieldScene.addSprite(delaySprite);
        }

        SceneManager.render(battlefieldScene);
    }
}

package com.game.gaika.action;

import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;

/**
 * Created by fangxg on 2015/7/25.
 */
public class SelectPassengerAction  implements  BaseAction{
    private int weaponID;

    public SelectPassengerAction(int pWeaponID) {
        this.weaponID = pWeaponID;
    }

    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();

        BaseWeapon weapon = gdm.weapons.get(weaponID);

        weapon.transporter.getPassengers().remove(weapon);

        weapon.x = weapon.transporter.x;
        weapon.y = weapon.transporter.y;
        weapon.transporter = null;
        weapon.doSelect();

        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        SceneManager.render(battlefieldScene);
    }
}

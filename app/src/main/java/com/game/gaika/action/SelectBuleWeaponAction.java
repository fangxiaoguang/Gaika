package com.game.gaika.action;

import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;

/**
 * Created by fangxg on 2015/7/23.
 */
public class SelectBuleWeaponAction implements BaseAction {
    private int weaponID;

    public SelectBuleWeaponAction(int pWeaponID) {
        weaponID = pWeaponID;
    }

    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();

        BaseWeapon weapon = gdm.weapons.get(weaponID);
        weapon.doSelect();
        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        battlefieldScene.setHUDInfoCardWeaponID(weaponID);
        SceneManager.render(battlefieldScene);
    }
}

package com.game.gaika.action;

import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;

/**
 * Created by fangxg on 2015/9/3.
 */
public class SelectJustInfoWeaponAction implements BaseAction {
    private int weaponID;
    public SelectJustInfoWeaponAction(int pWeaponID) {
        weaponID = pWeaponID;
    }

    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();

        BaseWeapon weapon = gdm.weapons.get(weaponID);

        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        battlefieldScene.setHUDInfoCardWeaponID(weaponID);
        SceneManager.render(battlefieldScene);
    }
}

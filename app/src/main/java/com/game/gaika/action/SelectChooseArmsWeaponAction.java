package com.game.gaika.action;

import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.dialg.ChooseArmsDialogScene;

/**
 * Created by fangxg on 2015/7/25.
 */
public class SelectChooseArmsWeaponAction implements BaseAction {
    private  int weaponID;
    public SelectChooseArmsWeaponAction(int pWeaponID) {

        weaponID= pWeaponID;
    }

    @Override
    public void doAction() {
        BattlefieldScene battlefieldScene = new BattlefieldScene(false);

        GameDataManager gdm = GameDataManager.getInstance();
        BaseWeapon weapon = gdm.weapons.get(weaponID);
        ChooseArmsDialogScene chooseArmsDialogScene = new ChooseArmsDialogScene(weapon, battlefieldScene);
        battlefieldScene.setDialogSecne(chooseArmsDialogScene);
        battlefieldScene.setHUDInfoCardWeaponID(weaponID);
        SceneManager.render(battlefieldScene);
    }
}

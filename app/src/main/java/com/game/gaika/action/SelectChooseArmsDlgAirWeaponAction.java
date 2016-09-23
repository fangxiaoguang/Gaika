package com.game.gaika.action;

import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;

/**
 * Created by fangxg on 2015/7/25.
 */
public class SelectChooseArmsDlgAirWeaponAction implements BaseAction {
    private  int weaponID;
    public SelectChooseArmsDlgAirWeaponAction(int pWeaponID){
        weaponID = pWeaponID;
    }
    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();

        BaseWeapon weapon = gdm.weapons.get(weaponID);
        weapon.chooseArms(ID.ARMS_TYPE.AIR);
        weapon.doSelect();

        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        SceneManager.render(battlefieldScene);
    }
}

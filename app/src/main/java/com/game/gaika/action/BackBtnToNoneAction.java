package com.game.gaika.action;

import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;

import java.util.List;

/**
 * Created by fangxg on 2015/7/24.
 */
public class BackBtnToNoneAction implements BaseAction {
    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();

        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.setSelected(true);
        List<BaseWeapon> weapons = gdm.getWeapons(filter);

        BaseWeapon selectWeapon = weapons.get(0);

//-----------------------------
        selectWeapon.doBackSelected();
//        selectWeapon.selected = false;
//        gdm.getCurrentChapter().getGameMap().cleanGreenBox(selectWeapon.teamColor);
//        gdm.cleanAdvantageFlag();
//        gdm.cleanTransportsFlag();
//---------------------------------

        WeaponSelectFilter filter2 = new WeaponSelectFilter();
        filter2.setSetOutInBattlefield();
        filter2.setIsTransporter(true);
        filter2.setMapXY(selectWeapon.x, selectWeapon.y);
        List<BaseWeapon> weapons2 = gdm.getWeapons(filter2);

        if (weapons2.size() > 0) {
            BaseWeapon transporter = weapons2.get(0);
            //---------------
            selectWeapon.doBackToTransporter(transporter);
//            selectWeapon.transporter = transporter;
//            selectWeapon.x = -2;
//            selectWeapon.y = -2;
//            transporter.getPassengers().add(selectWeapon);
            //--------------------------
        }
        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        SceneManager.render(battlefieldScene);
    }
}

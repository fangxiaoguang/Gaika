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
public class BackBtnToSelectedAction  implements BaseAction {
    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();

        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.setSelected(true);
        List<BaseWeapon> weapons = gdm.getWeapons(filter);
        BaseWeapon selectWeapon = weapons.get(0);

        selectWeapon.doBackMoveToSrc();

        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        SceneManager.render(battlefieldScene);

        return;
    }
}

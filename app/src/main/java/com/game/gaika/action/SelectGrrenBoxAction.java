package com.game.gaika.action;

import com.game.gaika.data.ColorBox;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;

import java.util.List;

/**
 * Created by fangxg on 2015/7/23.
 */
public class SelectGrrenBoxAction implements BaseAction {
    private int boxID;

    public SelectGrrenBoxAction(int pBoxID) {
        this.boxID = pBoxID;
    }

    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();

        ColorBox box = gdm.getCurrentChapter().getGameMap().boxs.get(boxID);

        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.setSelected(true);
        List<BaseWeapon> weapons = gdm.getWeapons(filter);
        BaseWeapon weapon = weapons.get(0);

        weapon.doMoveToDesc(box);

        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        SceneManager.render(battlefieldScene);
    }
}

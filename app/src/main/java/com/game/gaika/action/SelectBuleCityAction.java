package com.game.gaika.action;

import com.game.gaika.data.City;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.WeaponSetoutScene;

/**
 * Created by fangxg on 2015/7/23.
 */
public class SelectBuleCityAction implements BaseAction {
    private int cityID;

    public SelectBuleCityAction(int pCityID) {
        this.cityID = pCityID;
    }

    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();

        City cityNode = gdm.getCurrentChapter().getGameMap().citys.get(cityID);

        WeaponSetoutScene weaponSetoutScene = new WeaponSetoutScene(cityNode);
        SceneManager.render(weaponSetoutScene);
    }
}

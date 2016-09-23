package com.game.gaika.action;

import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;

/**
 * Created by fangxg on 2015/7/25.
 */
public class SelectTransporterIntoAction implements BaseAction {
    int transporterID;

    public SelectTransporterIntoAction(int pTransporterID) {
        this.transporterID = pTransporterID;
    }

    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();
        BaseWeapon transporter = gdm.weapons.get(transporterID);
        BaseWeapon selectedWeapon = gdm.getSelectedWeapon();


        selectedWeapon.doMoveToDesc(transporter);
        selectedWeapon.intoTransporter(transporter);
        selectedWeapon.x = -2;
        selectedWeapon.y = -2;


        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        SceneManager.render(battlefieldScene);
    }
}

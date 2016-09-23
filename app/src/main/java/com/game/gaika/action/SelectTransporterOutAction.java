package com.game.gaika.action;

import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.dialg.ChoosePassengerDialogScene;

/**
 * Created by fangxg on 2015/7/25.
 */
public class SelectTransporterOutAction implements BaseAction {
    private  int transporterID;

    public SelectTransporterOutAction(int pTransporterID) {
        this.transporterID = pTransporterID;
    }

    @Override
    public void doAction() {
        BattlefieldScene battlefieldScene = new BattlefieldScene(false);

        GameDataManager gdm = GameDataManager.getInstance();
        BaseWeapon transporter= gdm.weapons.get(transporterID);

        for(BaseWeapon weapon :transporter.getPassengers()){


        }
        ChoosePassengerDialogScene choosePassengerDialogScene = new ChoosePassengerDialogScene(transporter, battlefieldScene);
        battlefieldScene.setDialogSecne(choosePassengerDialogScene);
        battlefieldScene.setHUDInfoCardWeaponID(transporterID);
        SceneManager.render(battlefieldScene);
    }
}

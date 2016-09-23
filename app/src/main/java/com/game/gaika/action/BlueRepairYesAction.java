package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.frame.sprite.DelaySprite;

/**
 * Created by fangxg on 2015/9/1.
 */
public class BlueRepairYesAction implements BaseAction {
    private int weaponID;

    public BlueRepairYesAction(int pWeaponID) {
        weaponID = pWeaponID;
    }

    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();
        ID.TEAM_COLOR teamColor = ID.TEAM_COLOR.BLUE;
        BaseWeapon canRepairWeapon = gdm.weapons.get(weaponID);

        int fromLifeEx = canRepairWeapon.getLifeEx();
        int toLifeEx = fromLifeEx + canRepairWeapon.info.getSupplyLifeExByTurn();
        if (toLifeEx > 10) {
            toLifeEx = 10;
        }
        int needSupply = canRepairWeapon.info.supply * (toLifeEx - fromLifeEx) / 10;
        gdm.addSupply(teamColor, -needSupply);

//        canRepairWeapon.life = toLifeEx * BaseWeapon.LIFE_PER_LIFE_EX;
        canRepairWeapon.doRepair(toLifeEx);

        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_NEXT, null, battlefieldScene)));
        SceneManager.render(battlefieldScene);
    }
}

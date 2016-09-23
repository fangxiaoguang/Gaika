package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.dialg.BlueRepairYesNoDialog;
import com.game.frame.sprite.DelaySprite;

import java.util.ArrayList;
import java.util.List;

import static com.game.frame.sound.SoundManager.playSound;

/**
 * Created by fangxg on 2015/9/1.
 */
public class BlueRepairNextAction implements BaseAction {
    private static List<BaseWeapon> repairWeapons = null;
    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();
        ID.TEAM_COLOR teamColor = ID.TEAM_COLOR.BLUE;
        if (repairWeapons == null) {
            repairWeapons = new ArrayList<>();
            WeaponSelectFilter filter = new WeaponSelectFilter();
            filter.setSetOutInBattlefield();
            filter.addTeamColor(teamColor);
            List<BaseWeapon> weapons = gdm.getWeapons(filter);
            for (BaseWeapon weapon : weapons) {
                if (weapon.canRepair() && weapon.getLifeEx() < 10) {
                    repairWeapons.add(weapon);
                }
            }
        }

        int blueSupply = gdm.getSupply(teamColor);
        BaseWeapon canRepairWeapon = null;
        for (BaseWeapon weapon : repairWeapons) {
            int fromLifeEx = weapon.getLifeEx();
            int toLifeEx = fromLifeEx + weapon.info.getSupplyLifeExByTurn();
            if(toLifeEx > 10) {
                toLifeEx = 10;
            }
            int needSupply = weapon.info.supply * (toLifeEx - fromLifeEx) / 10;
            if (needSupply <= blueSupply) {
                canRepairWeapon = weapon;
                break;
            }
        }
        if (canRepairWeapon != null) {
            repairWeapons.remove(canRepairWeapon);
            int fromLifeEx = canRepairWeapon.getLifeEx();
            int toLifeEx = fromLifeEx + canRepairWeapon.info.getSupplyLifeExByTurn();
            if(toLifeEx > 10) {
                toLifeEx = 10;
            }

             playSound("messag01");
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
            int needSupply = canRepairWeapon.info.supply * (toLifeEx - fromLifeEx) / 10;
            BlueRepairYesNoDialog blueRepairYesNoDialog = new BlueRepairYesNoDialog(battlefieldScene, canRepairWeapon.id, fromLifeEx, toLifeEx, needSupply);
            battlefieldScene.setDialogSecne(blueRepairYesNoDialog);
            SceneManager.render(battlefieldScene);

//            int needSupply = canRepairWeapon.info.supply * (toLifeEx - fromLifeEx) / 10;
//            gdm.addSupply(teamColor, -needSupply);
//            canRepairWeapon.life = toLifeEx * BaseWeapon.LIFE_PER_LIFE_EX;
        } else {
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
            battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_TIME_OUT, null, battlefieldScene)));
            SceneManager.render(battlefieldScene);
            repairWeapons = null;
        }
    }
}

package com.game.gaika.action;

import com.game.frame.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.frame.sprite.DelaySprite;

import java.util.List;

/**
 * Created by fangxg on 2015/7/24.
 */
public class TurnFinishDilogYesAction implements BaseAction{
    @Override
    public void doAction() {

        GameDataManager gdm = GameDataManager.getInstance();

        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.setSetOutInBattlefield();
        filter.addTeamColor(ID.TEAM_COLOR.BLUE);
        filter.setMoveEnding(false);
        List<BaseWeapon> weapons = gdm.getWeapons(filter);

        for(BaseWeapon weapon: weapons){
            weapon.doTurnFinish();
        }




        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__CRASH_NEXT, null, battlefieldScene)));
        SceneManager.render(battlefieldScene);
    }
}

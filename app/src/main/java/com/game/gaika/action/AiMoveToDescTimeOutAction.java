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
 * Created by fangxg on 2015/8/7.
 */
public class AiMoveToDescTimeOutAction implements BaseAction {
    private int aiWeaponID;

    public AiMoveToDescTimeOutAction(int pAiWeaponID) {
        aiWeaponID = pAiWeaponID;
    }

    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();
        BaseWeapon aiWeapon = gdm.weapons.get(aiWeaponID);

        aiWeapon.doMoveEnd();
        gdm.getCurrentChapter().getGameMap().cleanGreenBox(aiWeapon.teamColor);

        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        //doFight
        if (aiWeapon.getStrategic().getEnemy() == null || aiWeapon.canAttack(aiWeapon.getStrategic().getEnemy()) == false) {
            battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__AI_FIGHT_TIME_OUT, null, battlefieldScene, aiWeaponID)));
        } else {
            //aiWeapon.getStrategic().getEnemy().doFight(aiWeapon);
            BaseWeapon enemy =  aiWeapon.getStrategic().getEnemy();
//            enemy.doFight(aiWeapon);
            aiWeapon.doFight(enemy);


            battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_AI_LOOP_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__AI_FIGHT_TIME_OUT, null, battlefieldScene, aiWeaponID)));
        }
        SceneManager.render(battlefieldScene);
    }
}

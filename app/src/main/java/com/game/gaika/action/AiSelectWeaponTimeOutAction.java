package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.IMapPoint;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.frame.sprite.DelaySprite;

/**
 * Created by fangxg on 2015/8/7.
 */
public class AiSelectWeaponTimeOutAction implements BaseAction {
    private int aiWeaponID ;
    public AiSelectWeaponTimeOutAction(int pAiWeaponID) {
        aiWeaponID = pAiWeaponID;
    }



    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();
        BaseWeapon aiWeapon = gdm.weapons.get(aiWeaponID);

        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        IMapPoint descPoint = aiWeapon.getStrategic().getDesc();
        if(descPoint != null) {
            aiWeapon.doMoveToDesc(aiWeapon.getStrategic().getDesc());

            battlefieldScene.addSprite(new DelaySprite(aiWeapon.path.size() * GameSetup.DELAY_PATH_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__AI_MOVE_TO_DESC_TIME_OUT, null, battlefieldScene, aiWeaponID)));
        }else {

            battlefieldScene.addSprite(new DelaySprite(1 * GameSetup.DELAY_PATH_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__AI_MOVE_TO_DESC_TIME_OUT, null, battlefieldScene, aiWeaponID)));
        }
        SceneManager.render(battlefieldScene);
    }
}

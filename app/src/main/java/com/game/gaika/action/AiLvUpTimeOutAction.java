package com.game.gaika.action;

/**
 * Created by fangxg on 2015/8/16.
 */
public class AiLvUpTimeOutAction implements BaseAction {
    private int aiWeaponID ;
    public AiLvUpTimeOutAction(int pAiWeaponID) {
        aiWeaponID = pAiWeaponID;
    }
    @Override
    public void doAction() {
/*
        GameDataManager gdm = GameDataManager.getInstance();
        BaseWeapon aiWeapon = gdm.weapons.get(aiWeaponID);

        //lv up
        //if(  lv > expLv)   setDialogUpLv  ;
        //   preExp = exp;


        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_AI_LOOP_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__CAPTURING_TIME_OUT, null, battlefieldScene, aiWeaponID)));
        SceneManager.render(battlefieldScene);*/
    }
}

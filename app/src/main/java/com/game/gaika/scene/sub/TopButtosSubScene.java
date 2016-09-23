package com.game.gaika.scene.sub;

import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.ID;
import com.game.gaika.scene.BaseLogicScene;
import com.game.gaika.scene.DiplomacyScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.SelectTargetScene;
import com.game.gaika.scene.dialg.SystemPopupMenuScene;
import com.game.gaika.scene.TeamBuildScene;
import com.game.gaika.scene.WeaponBuyScene;
import com.game.gaika.scene.WeaponRepairScene;
import com.game.gaika.scene.WeaponSellScene;
import com.game.gaika.sound.SoundManager;
import com.game.gaika.sprite.NormalSprite;

import static com.game.gaika.data.ID.MSG_ID.*;

import static com.game.gaika.data.ID.SCENE_ID.*;

/**
 * Created by fangxg on 2015/6/28.
 */
public class TopButtosSubScene extends BaseLogicScene implements IMessageHandler {

    // enum MSG_ID {MSG_BUTTON_SELECT_TARGET, MSG_BUTTON_TEAM_BUILD, MSG_BUTTON_WEAPON_BUY, MSG_BUTTON_WEAPON_REPAIR, MSG_BUTTON_DIPLOMACY, MSG_BUTTON_WEAPON_SELL, MSG_BUTTON_SYSTEM_MENU;}

    public TopButtosSubScene(BaseLogicScene pParentScene) {
        super(TOP_BUTTOS_SUB);

        ID.SCENE_ID parentSceneID = pParentScene.sceneId;


        int bx = 7;
        int by = 3;

        if (parentSceneID != SELECT_TARGET) {
            NormalSprite buttonSprite = new NormalSprite(bx, by, "oper_tg1", 0, new TouchMessage(MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_SELECT_TARGET, null, this));
            addSprite(buttonSprite);
        }
        bx += 99;

        if (parentSceneID != TEAM_BUILD) {
            NormalSprite buttonSprite = new NormalSprite(bx, by, "oper_tg1", 1, new TouchMessage(MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_TEAM_BUILD, null, this));
            addSprite(buttonSprite);
        }
        bx += 99;

        if (parentSceneID != WEAPON_BUY) {
            NormalSprite buttonSprite = new NormalSprite(bx, by, "oper_tg1", 2, new TouchMessage(MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_WEAPON_BUY, null, this));
            addSprite(buttonSprite);
        }
        bx += 99;

        if (parentSceneID != WEAPON_REPAIR) {
            NormalSprite buttonSprite = new NormalSprite(bx, by, "oper_tg1", 3, new TouchMessage(MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_WEAPON_REPAIR, null, this));
            addSprite(buttonSprite);
        }
        bx += 99;

        if (parentSceneID != DIPLOMACY) {
            NormalSprite buttonSprite = new NormalSprite(bx, by, "oper_tg1", 4, new TouchMessage(MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_DIPLOMACY, null, this));
            addSprite(buttonSprite);
        }
        bx += 99;

        if (parentSceneID != WEAPON_SELL) {
            NormalSprite buttonSprite = new NormalSprite(bx, by, "oper_tg1", 5, new TouchMessage(MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_WEAPON_SELL, null, this));
            addSprite(buttonSprite);
        }


        NormalSprite buttonSysSprite = new NormalSprite(800 - 86, 0, "sys_bt", 1, new TouchMessage(MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_SYSTEM_MENU, null, this));
        addSprite(buttonSysSprite);


    }

    @Override
    public boolean isBacegroundEnabled() {
        return false;
    }

    @Override
    public void buildScene() {

    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {



        ID.MSG_ID EMsgID = pTouchMessage.getMessageID();
        if (EMsgID == MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_SELECT_TARGET) {
            SoundManager.getInstance().playSound("select01");
            SelectTargetScene scene = new SelectTargetScene(true);
            SceneManager.render(scene);
        }
        if (EMsgID == MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_TEAM_BUILD) {
            SoundManager.getInstance().playSound("select01");
            TeamBuildScene scene = new TeamBuildScene(true);
            SceneManager.render(scene);
        }
        if (EMsgID == MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_WEAPON_BUY) {
            SoundManager.getInstance().playSound("select01");
            WeaponBuyScene scene = new WeaponBuyScene(true);
            SceneManager.render(scene);
        }
        if (EMsgID == MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_WEAPON_REPAIR) {
            SoundManager.getInstance().playSound("select01");
            WeaponRepairScene scene = new WeaponRepairScene(true);
            SceneManager.render(scene);
        }
        if (EMsgID == MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_DIPLOMACY) {
            SoundManager.getInstance().playSound("select01");
            DiplomacyScene scene = new DiplomacyScene(true);
            SceneManager.render(scene);
        }
        if (EMsgID == MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_WEAPON_SELL) {
            SoundManager.getInstance().playSound("select01");
            WeaponSellScene scene = new WeaponSellScene(true);
            SceneManager.render(scene);
        }
        if (EMsgID == MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_SYSTEM_MENU) {
            SoundManager.getInstance().playSound("select01");
            BaseLogicScene parentScene = getParentScene();
            parentScene.setDialogSecne(new SystemPopupMenuScene( parentScene, 1.6f));
            SceneManager.render(parentScene);
        }



    }
}

package com.game.gaika.scene.dialg;

import com.game.frame.scene.dialg.DialogScene;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.action.BattlefieldInfoAction;
import com.game.gaika.action.DataLoadAction;
import com.game.gaika.action.DataSaveAction;
import com.game.gaika.action.GameOverAction;
import com.game.gaika.action.ReturnAction;
import com.game.gaika.action.SettingAction;
import com.game.gaika.action.TeamListAction;
import com.game.gaika.action.TurnFinishAction;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.frame.scene.BaseLogicScene;
import com.game.gaika.scene.SceneManager;
import com.game.frame.sprite.NormalSprite;

import static com.game.gaika.data.ID.MSG_ID.*;
import static com.game.gaika.data.ID.SCENE_ID.*;
import static com.game.frame.sound.SoundManager.playSound;

/**
 * Created by fangxg on 2015/6/22.
 */
public class SystemPopupMenuScene extends DialogScene {   // DialogScene {
    public SystemPopupMenuScene(/*IMessageHandler pMessageHandler,*/ BaseLogicScene pParentScene) {
        this(pParentScene, 1.0f);
    }

    public SystemPopupMenuScene(/*IMessageHandler pMessageHandler,*/ BaseLogicScene pParentScene, float pScale) {
        //super("SystemPopupMenuScene", 175.0f, 296.0f);
        super(SYSTEM_POPUP_MENU, 0.0f, 0.0f, 175.0f * pScale, 296.0f * pScale, pParentScene, EPointModel.POINT_MODEL_CENTER);

        NormalSprite bkSprite = new NormalSprite(0.0f, 0.0f, "main_wn");
        bkSprite.setScale(pScale);
        addSprite(bkSprite);


        float x = 6 * pScale;
        float y = -29 * pScale;
        y += 35 * pScale;

        if (pParentScene.sceneId == ID.SCENE_ID.BATTLEFIELD) {
            NormalSprite button1 = new NormalSprite(x, y, "mainmenu", 0, new TouchMessage(MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_1, null, this));
            button1.setScale(pScale);
            addSprite(button1);
        } else {
            NormalSprite button1 = new NormalSprite(x, y, "mainmenu2", 0);
            button1.setScale(pScale);
            addSprite(button1);
        }
        y += 35 * pScale;

        if (pParentScene.sceneId == ID.SCENE_ID.BATTLEFIELD) {
            NormalSprite button2 = new NormalSprite(x, y, "mainmenu", 1, new TouchMessage(MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_2, null, this));
            button2.setScale(pScale);
            addSprite(button2);
        } else {
            NormalSprite button2 = new NormalSprite(x, y, "mainmenu2", 1);
            button2.setScale(pScale);
            addSprite(button2);
        }
        y += 35 * pScale;

        if (pParentScene.sceneId == ID.SCENE_ID.BATTLEFIELD) {
            NormalSprite button3 = new NormalSprite(x, y, "mainmenu", 2, new TouchMessage(MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_3, null, this));
            button3.setScale(pScale);
            addSprite(button3);
        } else {
            NormalSprite button3 = new NormalSprite(x, y, "mainmenu2", 2);
            button3.setScale(pScale);
            addSprite(button3);
        }
        y += 35 * pScale;

        NormalSprite button4 = new NormalSprite(x, y, "mainmenu", 3, new TouchMessage(MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_4, null, this));
        button4.setScale(pScale);
        addSprite(button4);
        y += 35 * pScale;

        NormalSprite button5 = new NormalSprite(x, y, "mainmenu", 4, new TouchMessage(MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_5, null, this));
        button5.setScale(pScale);
        addSprite(button5);
        y += 35 * pScale;

        NormalSprite button6 = new NormalSprite(x, y, "mainmenu", 5, new TouchMessage(MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_6, null, this));
        button6.setScale(pScale);
        addSprite(button6);
        y += 35 * pScale;

        NormalSprite button7 = new NormalSprite(x, y, "mainmenu", 6, new TouchMessage(MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_7, null, this));
        button7.setScale(pScale);
        addSprite(button7);
        y += 35 * pScale;

        NormalSprite button8 = new NormalSprite(x, y, "mainmenu", 7, new TouchMessage(MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_8, null, this));
        button8.setScale(pScale);
        addSprite(button8);

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
        ID.MSG_ID msgID = pTouchMessage.getMessageID();
        if (msgID == MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_1) {
             playSound("messag01");
            TurnFinishAction act = new TurnFinishAction();
            act.doAction();
        }
        if (msgID == MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_2) {
             playSound("select01");
            BattlefieldInfoAction act = new BattlefieldInfoAction();
            act.doAction();
        }
        if (msgID == MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_3) {
             playSound("select01");
            TeamListAction act = new TeamListAction();
            act.doAction();
        }

        if (msgID == MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_4) {
             playSound("select01");
            String oldPath = GameSetup.sdcartdPahtSave + "xiao.png";
            SceneManager.getTopBaseLogicScene().captureScene(oldPath, this);
        }
        if (msgID == MSG_SCENE_SYSTEM_POPUP_MENU__CAPTURED) {
            DataSaveAction act = new DataSaveAction();
            act.doAction();
        }

        if (msgID == MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_5) {
             playSound("select01");
            DataLoadAction act = new DataLoadAction();
            act.doAction();
        }

        if (msgID == MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_6) {
             playSound("select01");
            SettingAction act = new SettingAction();
            act.doAction();
        }
        if (msgID == MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_7) {
             playSound("messag01");
            GameOverAction act = new GameOverAction();
            act.doAction();
        }
        if (msgID == MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_8) {
             playSound("back01");
            ReturnAction act = new ReturnAction();
            act.doAction();
        }
    }
}

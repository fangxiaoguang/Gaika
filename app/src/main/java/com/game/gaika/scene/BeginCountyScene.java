package com.game.gaika.scene;

import com.game.gaika.FSM.FSMClass;
import com.game.gaika.FSM.FSMState;
import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.StateChange;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;
import com.game.gaika.data.SaveManager;
import com.game.gaika.scene.sub.borderSubScene;
import com.game.gaika.sprite.NormalSprite;
import com.game.gaika.sprite.TextSprite;
import com.game.gaika.string.StringManager;
import com.game.gaika.texture.TexRegionManager;

import static com.game.gaika.scene.BeginCountyScene.StateID.*;

import static com.game.gaika.data.ID.MSG_ID.*;

import static com.game.gaika.data.ID.SCENE_ID.*;
import static com.game.gaika.data.ID.COUNTRY.*;
import static com.game.gaika.sound.SoundManager.playSound;


/**
 * Created by fangxg on 2015/6/19.
 */
public class BeginCountyScene extends BaseLogicScene implements IMessageHandler, StateChange {

    enum StateID {STATE_ID_SELECTED_COUNTY_NON, STATE_ID_SELECTED_COUNTY_1, STATE_ID_SELECTED_COUNTY_2, STATE_ID_SELECTED_COUNTY_3, STATE_ID_SELECTED_COUNTY_4, STATE_ID_SELECTED_COUNTY_5, STATE_ID_SELECTED_COUNTY_6}

//    enum MSG_ID {MSG_BUTTON_SELECT_COUNTY_1, MSG_BUTTON_SELECT_COUNTY_2, MSG_BUTTON_SELECT_COUNTY_3, MSG_BUTTON_SELECT_COUNTY_4, MSG_BUTTON_SELECT_COUNTY_5, MSG_BUTTON_SELECT_COUNTY_6, MSG_BUTTON_BACK}

    public BeginCountyScene() {
        this(STATE_ID_SELECTED_COUNTY_NON);
    }

    public BeginCountyScene(Enum pStateID) {
        super(BEGIN_COUNTY);

        buildFSM(pStateID);

        NormalSprite backSprite = new NormalSprite(0.0f, 0.0f, "stat2_bg");
        addSprite(backSprite);


        NormalSprite flagSprite1 = new NormalSprite(153, 119, "flag05", getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_COUNTY_1 ? 6 : 0, new TouchMessage(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_1, null, this));
        addSprite(flagSprite1);
        NormalSprite flagSprite2 = new NormalSprite(333, 119, "flag05", getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_COUNTY_2 ? 7 : 1, new TouchMessage(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_2, null, this));
        addSprite(flagSprite2);
        NormalSprite flagSprite3 = new NormalSprite(513, 119, "flag05", getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_COUNTY_3 ? 8 : 2, new TouchMessage(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_3, null, this));
        addSprite(flagSprite3);
        NormalSprite flagSprite4 = new NormalSprite(153, 267, "flag05", getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_COUNTY_4 ? 9 : 3, new TouchMessage(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_4, null, this));
        addSprite(flagSprite4);
        NormalSprite flagSprite5 = new NormalSprite(333, 267, "flag05", getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_COUNTY_5 ? 10 : 4, new TouchMessage(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_5, null, this));
        addSprite(flagSprite5);
        NormalSprite flagSprite6 = new NormalSprite(513, 267, "flag05", getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_COUNTY_6 ? 11 : 5, new TouchMessage(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_6, null, this));
        addSprite(flagSprite6);

        String strInfo = "";
        if (getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_COUNTY_NON) {

        }
        if (getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_COUNTY_1) {
            strInfo = StringManager.getInstance().getString("S04001");
        }
        if (getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_COUNTY_2) {
            strInfo = StringManager.getInstance().getString("S04002");
        }
        if (getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_COUNTY_3) {
            strInfo = StringManager.getInstance().getString("S04003");
        }
        if (getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_COUNTY_4) {
            strInfo = StringManager.getInstance().getString("S04004");
        }
        if (getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_COUNTY_5) {
            strInfo = StringManager.getInstance().getString("S04005");
        }
        if (getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_COUNTY_6) {
            strInfo = StringManager.getInstance().getString("S04006");
        }
        TextSprite countyInfo = new TextSprite(183, 408, true, strInfo, TexRegionManager.getInstance().getFont16());
        addSprite(countyInfo);

        NormalSprite buttonSprite1 = new NormalSprite(35, 556, "retn_bt1", 0, new TouchMessage(MSG_SCENE_COUNTRY_MENU__BUTTON_BACK, null, this));
        addSprite(buttonSprite1);

        addChildScene(new borderSubScene(this));
    }

    @Override
    public boolean isBacegroundEnabled() {
        return true;
    }

    @Override
    public void buildScene() {

    }

    @Override
    public void onStateChanged(Enum oldState, Enum newState, TouchMessage pTouchMessage) {
        GameDataManager gdm = GameDataManager.getInstance();
        if (oldState != newState) {

            BeginCountyScene countyMenuScene = new BeginCountyScene(newState);
            SceneManager.render(countyMenuScene);
        }
        if (oldState == newState) {

            BattlefieldEnterInfoScene battlefieldEnterInfoScene = new BattlefieldEnterInfoScene(SaveManager.getNewGameChapterNo());
            SceneManager.render(battlefieldEnterInfoScene);
        }
    }

    @Override
    public void buildFSM(Enum pCurrentStateID) {
        addFSMClass(new FSMClass(pCurrentStateID));

        FSMState stateNon = new FSMState(STATE_ID_SELECTED_COUNTY_NON);
        stateNon.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_1, STATE_ID_SELECTED_COUNTY_1);
        stateNon.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_2, STATE_ID_SELECTED_COUNTY_2);
        stateNon.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_3, STATE_ID_SELECTED_COUNTY_3);
        stateNon.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_4, STATE_ID_SELECTED_COUNTY_4);
        stateNon.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_5, STATE_ID_SELECTED_COUNTY_5);
        stateNon.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_6, STATE_ID_SELECTED_COUNTY_6);
        getFSMClass().addState(stateNon);

        FSMState state1 = new FSMState(STATE_ID_SELECTED_COUNTY_1);
        state1.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_1, STATE_ID_SELECTED_COUNTY_1);
        state1.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_2, STATE_ID_SELECTED_COUNTY_2);
        state1.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_3, STATE_ID_SELECTED_COUNTY_3);
        state1.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_4, STATE_ID_SELECTED_COUNTY_4);
        state1.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_5, STATE_ID_SELECTED_COUNTY_5);
        state1.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_6, STATE_ID_SELECTED_COUNTY_6);
        getFSMClass().addState(state1);

        FSMState state2 = new FSMState(STATE_ID_SELECTED_COUNTY_2);
        state2.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_1, STATE_ID_SELECTED_COUNTY_1);
        state2.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_2, STATE_ID_SELECTED_COUNTY_2);
        state2.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_3, STATE_ID_SELECTED_COUNTY_3);
        state2.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_4, STATE_ID_SELECTED_COUNTY_4);
        state2.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_5, STATE_ID_SELECTED_COUNTY_5);
        state2.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_6, STATE_ID_SELECTED_COUNTY_6);
        getFSMClass().addState(state2);

        FSMState state3 = new FSMState(STATE_ID_SELECTED_COUNTY_3);
        state3.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_1, STATE_ID_SELECTED_COUNTY_1);
        state3.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_2, STATE_ID_SELECTED_COUNTY_2);
        state3.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_3, STATE_ID_SELECTED_COUNTY_3);
        state3.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_4, STATE_ID_SELECTED_COUNTY_4);
        state3.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_5, STATE_ID_SELECTED_COUNTY_5);
        state3.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_6, STATE_ID_SELECTED_COUNTY_6);
        getFSMClass().addState(state3);

        FSMState state4 = new FSMState(STATE_ID_SELECTED_COUNTY_4);
        state4.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_1, STATE_ID_SELECTED_COUNTY_1);
        state4.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_2, STATE_ID_SELECTED_COUNTY_2);
        state4.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_3, STATE_ID_SELECTED_COUNTY_3);
        state4.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_4, STATE_ID_SELECTED_COUNTY_4);
        state4.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_5, STATE_ID_SELECTED_COUNTY_5);
        state4.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_6, STATE_ID_SELECTED_COUNTY_6);
        getFSMClass().addState(state4);

        FSMState state5 = new FSMState(STATE_ID_SELECTED_COUNTY_5);
        state5.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_1, STATE_ID_SELECTED_COUNTY_1);
        state5.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_2, STATE_ID_SELECTED_COUNTY_2);
        state5.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_3, STATE_ID_SELECTED_COUNTY_3);
        state5.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_4, STATE_ID_SELECTED_COUNTY_4);
        state5.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_5, STATE_ID_SELECTED_COUNTY_5);
        state5.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_6, STATE_ID_SELECTED_COUNTY_6);
        getFSMClass().addState(state5);

        FSMState state6 = new FSMState(STATE_ID_SELECTED_COUNTY_6);
        state6.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_1, STATE_ID_SELECTED_COUNTY_1);
        state6.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_2, STATE_ID_SELECTED_COUNTY_2);
        state6.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_3, STATE_ID_SELECTED_COUNTY_3);
        state6.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_4, STATE_ID_SELECTED_COUNTY_4);
        state6.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_5, STATE_ID_SELECTED_COUNTY_5);
        state6.addTransition(MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_6, STATE_ID_SELECTED_COUNTY_6);
        getFSMClass().addState(state6);

    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {
        GameDataManager gdm = GameDataManager.getInstance();
        ID.MSG_ID msgID = pTouchMessage.getMessageID();
        if (msgID == MSG_SCENE_COUNTRY_MENU__BUTTON_BACK) {
             playSound("back01");
            BeginLocalScene beginLocalScene = new BeginLocalScene();
            SceneManager.render(beginLocalScene);
        }
        if (msgID == MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_1 || msgID == MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_2
                || msgID == MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_3 || msgID == MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_4
                || msgID == MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_5 || msgID == MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_6) {

             playSound("select01");
            if (msgID == MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_1) {
                gdm.gameBeginCounty = USA;
            }
            if (msgID == MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_2) {
                gdm.gameBeginCounty = USN;
            }
            if (msgID == MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_3) {
                gdm.gameBeginCounty = RUSSIA;
            }
            if (msgID == MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_4) {
                gdm.gameBeginCounty = GERMANY;
            }
            if (msgID == MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_5) {
                gdm.gameBeginCounty = ENGLAND;
            }
            if (msgID == MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_6) {
                gdm.gameBeginCounty = JAPAN;
            }

            Enum oldState = getFSMClass().getCurrentStateID();
            getFSMClass().stateTransition(pTouchMessage.getMessageID());
            Enum newState = getFSMClass().getCurrentStateID();
            onStateChanged(oldState, newState, pTouchMessage);
        }
    }
}

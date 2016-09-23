package com.game.gaika.scene.sub;

import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.ID;
import com.game.gaika.scene.BaseLogicScene;
import com.game.gaika.scene.SceneFactory;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.dialg.DialogScene;
import com.game.gaika.scene.dialg.SortMenuScene;
import com.game.gaika.sound.SoundManager;
import com.game.gaika.sprite.NormalSprite;
import com.game.gaika.sprite.TextSprite;
import com.game.gaika.texture.TexRegionManager;

/*import static com.game.gaika.scene.WeaponSetoutScene.MSG_ID.MSG_SELECT_WEAPON;
import static com.game.gaika.scene.WeaponSetoutScene.StateID.STATE_ID_SELECTED_NON;
import static com.game.gaika.scene.WeaponSetoutScene.StateID.STATE_ID_SELECTED_WEAPON;*/

import java.util.ArrayList;
import java.util.List;

import static com.game.gaika.data.ID.STOR_TYPE.*;
import static com.game.gaika.data.ID.SORT_ASCEND.*;
import static com.game.gaika.scene.sub.SortsSubScene.ShowStatic.*;
import static com.game.gaika.data.ID.MSG_ID.*;
import static com.game.gaika.data.ID.SCENE_ID.*;

/**
 * Created by fangxg on 2015/6/23.
 */
public class SortsSubScene extends BaseLogicScene implements IMessageHandler {


    enum ShowStatic {SHOW_STATIC_NON, SHOW_STATIC_SHOW_MENU}

//    enum MSG_ID {MSG_BUTTON_SORT, MSG_BUTTON_TYPE, MSG_BUTTON_NAME, MSG_BUTTON_LEVEL, MSG_BUTTON_SUPPLY, MSG_BUTTON_ASCEND;}

    private String subTag = "";

    private List<ID.STOR_TYPE> items;

    private Enum showState;
//
//    private Enum sortType;
//    private Enum sortAssend;
//    private IMessageHandler parentMsgHandler;


    public SortsSubScene(String pSubTag, List<ID.STOR_TYPE> pItems, BaseLogicScene pParentScene) {
        super(SORTS_SUB);
        setParentScene(pParentScene);
        subTag = pSubTag;
        items = pItems;

        NormalSprite btnSortSprite = new NormalSprite(0.0f, 0.0f, "oper_bt1", 0, new TouchMessage(MSG_SCENE_SORTS_SUB__BUTTON_SORT, null, this));
        addSprite(btnSortSprite);

        ID.STOR_TYPE eSortType = (ID.STOR_TYPE) getParentScene().getSceneValuesMap().getEnum("sortType" + subTag);
        TextSprite textSprite = new TextSprite(74.0f, 5.0f, true, eSortType.toDescribeString(), TexRegionManager.getInstance().getFont16());
        addSprite(textSprite);

        if (getParentScene().getSceneValuesMap().containsKey("sortShowState" + subTag) == true && getParentScene().getSceneValuesMap().getEnum("sortShowState" + subTag) == SHOW_STATIC_SHOW_MENU) {
            List<Enum> listItems = new ArrayList<>();
            List<ID.MSG_ID> listMessageIDs = new ArrayList<>();

            for (ID.STOR_TYPE sortType : items) {
                listItems.add(sortType);
                if (sortType == ID.STOR_TYPE.TYPE) {
                    listMessageIDs.add(MSG_SCENE_SORTS_SUB__BUTTON_TYPE);
                } else if (sortType == ID.STOR_TYPE.NAME) {
                    listMessageIDs.add(MSG_SCENE_SORTS_SUB__BUTTON_NAME);
                } else if (sortType == ID.STOR_TYPE.COUNTRY) {
                    listMessageIDs.add(MSG_SCENE_SORTS_SUB__BUTTON_COUNTRY);
                } else if (sortType == ID.STOR_TYPE.BUY_COST) {
                    listMessageIDs.add(MSG_SCENE_SORTS_SUB__BUTTON_BUY_COST);
                } else if (sortType == ID.STOR_TYPE.LEVEL) {
                    listMessageIDs.add(MSG_SCENE_SORTS_SUB__BUTTON_LEVEL);
                } else if (sortType == ID.STOR_TYPE.REPAIR_COST) {
                    listMessageIDs.add(MSG_SCENE_SORTS_SUB__BUTTON_REPAIR_COST);
                } else if (sortType == ID.STOR_TYPE.SELL_COST) {
                    listMessageIDs.add(MSG_SCENE_SORTS_SUB__BUTTON_SELL_COST);
                } else if (sortType == ID.STOR_TYPE.SUPPLY) {
                    listMessageIDs.add(MSG_SCENE_SORTS_SUB__BUTTON_SUPPLY);
                }
            }

            DialogScene sortMenuScene = new SortMenuScene(listItems, listMessageIDs, this);
            sortMenuScene.setOffsetXY(74.0f, 25.0f);
            setDialogSecne(sortMenuScene);
        }


        int index = 8;
        if (getParentScene().getSceneValuesMap().getEnum("sortAscend" + subTag) == UP) {
            index = 32;
        } else if (getParentScene().getSceneValuesMap().getEnum("sortAscend" + subTag) == DOWN) {
            index = 8;
        }
        NormalSprite btnSortAscendSprite = new NormalSprite(142.0f, 0.0f, "oper_bt2", index, new TouchMessage(MSG_SCENE_SORTS_SUB__BUTTON_ASCEND, null, this));
        addSprite(btnSortAscendSprite);

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


        //MSG_BUTTON_SORT, MSG_BUTTON_TYPE, MSG_BUTTON_NAME, MSG_BUTTON_LEVEL, MSG_BUTTON_SUPPLY, MSG_BUTTON_ASCEND
        if (pTouchMessage.getMessageID() == MSG_SCENE_SORTS_SUB__BUTTON_SORT) {
            SoundManager.getInstance().playSound("select01");
            getParentScene().getSceneValuesMap().setEnum("sortShowState" + subTag, SHOW_STATIC_SHOW_MENU);
            getParentScene().getSceneValuesMap().remove("selectedWeaponID" + subTag);

            BaseLogicScene baseLogicScene = SceneFactory.createScene(getParentScene().sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
            SceneManager.render(baseLogicScene);
//            WeaponSetoutScene WeaponSetoutScene = new WeaponSetoutScene();
//            SceneManager.render(WeaponSetoutScene);
        }

        if (pTouchMessage.getMessageID() == MSG_SCENE_SORTS_SUB__BUTTON_TYPE) {
            getParentScene().getSceneValuesMap().setEnum("sortShowState" + subTag, SHOW_STATIC_NON);
            getParentScene().getSceneValuesMap().setEnum("sortType" + subTag, TYPE);
            getParentScene().getSceneValuesMap().remove("selectedWeaponID");
            BaseLogicScene baseLogicScene = SceneFactory.createScene(getParentScene().sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
            SceneManager.render(baseLogicScene);
        }
        if (pTouchMessage.getMessageID() == MSG_SCENE_SORTS_SUB__BUTTON_NAME) {
            getParentScene().getSceneValuesMap().setEnum("sortShowState" + subTag, SHOW_STATIC_NON);
            getParentScene().getSceneValuesMap().setEnum("sortType" + subTag, NAME);
            getParentScene().getSceneValuesMap().remove("selectedWeaponID");
            BaseLogicScene baseLogicScene = SceneFactory.createScene(getParentScene().sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
            SceneManager.render(baseLogicScene);
        }
        if (pTouchMessage.getMessageID() == MSG_SCENE_SORTS_SUB__BUTTON_COUNTRY) {
            getParentScene().getSceneValuesMap().setEnum("sortShowState" + subTag, SHOW_STATIC_NON);
            getParentScene().getSceneValuesMap().setEnum("sortType" + subTag, COUNTRY);
            getParentScene().getSceneValuesMap().remove("selectedWeaponID");
            BaseLogicScene baseLogicScene = SceneFactory.createScene(getParentScene().sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
            SceneManager.render(baseLogicScene);
        }
        if (pTouchMessage.getMessageID() == MSG_SCENE_SORTS_SUB__BUTTON_BUY_COST) {
            getParentScene().getSceneValuesMap().setEnum("sortShowState" + subTag, SHOW_STATIC_NON);
            getParentScene().getSceneValuesMap().setEnum("sortType" + subTag, BUY_COST);
            getParentScene().getSceneValuesMap().remove("selectedWeaponID");
            BaseLogicScene baseLogicScene = SceneFactory.createScene(getParentScene().sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
            SceneManager.render(baseLogicScene);
        }
        if (pTouchMessage.getMessageID() == MSG_SCENE_SORTS_SUB__BUTTON_LEVEL) {
            getParentScene().getSceneValuesMap().setEnum("sortShowState" + subTag, SHOW_STATIC_NON);
            getParentScene().getSceneValuesMap().setEnum("sortType" + subTag, LEVEL);
            getParentScene().getSceneValuesMap().remove("selectedWeaponID");
            BaseLogicScene baseLogicScene = SceneFactory.createScene(getParentScene().sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
            SceneManager.render(baseLogicScene);
        }
        if (pTouchMessage.getMessageID() == MSG_SCENE_SORTS_SUB__BUTTON_REPAIR_COST) {
            getParentScene().getSceneValuesMap().setEnum("sortShowState" + subTag, SHOW_STATIC_NON);
            getParentScene().getSceneValuesMap().setEnum("sortType" + subTag, REPAIR_COST);
            getParentScene().getSceneValuesMap().remove("selectedWeaponID");
            BaseLogicScene baseLogicScene = SceneFactory.createScene(getParentScene().sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
            SceneManager.render(baseLogicScene);
        }
        if (pTouchMessage.getMessageID() == MSG_SCENE_SORTS_SUB__BUTTON_SELL_COST) {
            getParentScene().getSceneValuesMap().setEnum("sortShowState" + subTag, SHOW_STATIC_NON);
            getParentScene().getSceneValuesMap().setEnum("sortType" + subTag, SELL_COST);
            getParentScene().getSceneValuesMap().remove("selectedWeaponID");
            BaseLogicScene baseLogicScene = SceneFactory.createScene(getParentScene().sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
            SceneManager.render(baseLogicScene);
        }
        if (pTouchMessage.getMessageID() == MSG_SCENE_SORTS_SUB__BUTTON_SUPPLY) {
            getParentScene().getSceneValuesMap().setEnum("sortShowState" + subTag, SHOW_STATIC_NON);
            getParentScene().getSceneValuesMap().setEnum("sortType" + subTag, SUPPLY);
            getParentScene().getSceneValuesMap().remove("selectedWeaponID");
            BaseLogicScene baseLogicScene = SceneFactory.createScene(getParentScene().sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
            SceneManager.render(baseLogicScene);
        }
        if (pTouchMessage.getMessageID() == MSG_SCENE_SORTS_SUB__BUTTON_ASCEND) {
            SoundManager.getInstance().playSound("select01");
            if (getParentScene().getSceneValuesMap().getEnum("sortAscend" + subTag) == UP) {
                getParentScene().getSceneValuesMap().setEnum("sortAscend" + subTag, DOWN);
            } else if (getParentScene().getSceneValuesMap().getEnum("sortAscend" + subTag) == DOWN) {
                getParentScene().getSceneValuesMap().setEnum("sortAscend" + subTag, UP);
            }
            getParentScene().getSceneValuesMap().remove("selectedWeaponID");
            BaseLogicScene baseLogicScene = SceneFactory.createScene(getParentScene().sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
            SceneManager.render(baseLogicScene);
        }
    }

   /* @Override
    public void onStateChanged(Enum oldState, Enum newState, TouchMessage pTouchMessage) {

    }

    @Override
    public void buildFSM(Enum pCurrentStateID) {

        addFSMClass("showStateFSM", new FSMClass(showState));

       *//* FSMState showStateNON = new FSMState(SHOW_STATIC_NON);
        showStateNON.addTransition(MSG_BUTTON_SORT, SHOW_STATIC_SHOW_MENU);
        getFSMClass("showStateFSM").addState(showStateNON);*//*


        addFSMClass("sortTypeFSM", new FSMClass(sortType));

        FSMState stateType = new FSMState(TYPE);
        stateType.addTransition(MSG_BUTTON_TYPE, TYPE);
        stateType.addTransition(MSG_BUTTON_NAME, NAME);
        stateType.addTransition(MSG_BUTTON_LEVEL, LEVEL);
        stateType.addTransition(MSG_BUTTON_SUPPLY, SUPPLY);
        getFSMClass("sortTypeFSM").addState(stateType);

        FSMState stateName = new FSMState(NAME);
        stateName.addTransition(MSG_BUTTON_TYPE, TYPE);
        stateName.addTransition(MSG_BUTTON_NAME, NAME);
        stateName.addTransition(MSG_BUTTON_LEVEL, LEVEL);
        stateName.addTransition(MSG_BUTTON_SUPPLY, SUPPLY);
        getFSMClass("sortTypeFSM").addState(stateName);

        FSMState stateLevel = new FSMState(LEVEL);
        stateLevel.addTransition(MSG_BUTTON_TYPE, TYPE);
        stateLevel.addTransition(MSG_BUTTON_NAME, NAME);
        stateLevel.addTransition(MSG_BUTTON_LEVEL, LEVEL);
        stateLevel.addTransition(MSG_BUTTON_SUPPLY, SUPPLY);
        getFSMClass("sortTypeFSM").addState(stateLevel);

        FSMState stateSupply = new FSMState(SUPPLY);
        stateSupply.addTransition(MSG_BUTTON_TYPE, TYPE);
        stateSupply.addTransition(MSG_BUTTON_NAME, NAME);
        stateSupply.addTransition(MSG_BUTTON_LEVEL, LEVEL);
        stateSupply.addTransition(MSG_BUTTON_SUPPLY, SUPPLY);
        getFSMClass("sortTypeFSM").addState(stateSupply);


        addFSMClass("sortAscendFSM", new FSMClass(sortAssend));

        FSMState stateAssendUp = new FSMState(UP);
        stateAssendUp.addTransition(MSG_BUTTON_ASCEND, DOWN);
        getFSMClass("sortAscendFSM").addState(stateAssendUp);


        FSMState stateAssendDown = new FSMState(DOWN);
        stateAssendDown.addTransition(MSG_BUTTON_ASCEND, UP);
        getFSMClass("sortAscendFSM").addState(stateAssendDown);

    }*/

}

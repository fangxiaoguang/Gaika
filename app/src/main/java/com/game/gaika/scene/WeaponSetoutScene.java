package com.game.gaika.scene;

import com.game.gaika.FSM.FSMClass;
import com.game.gaika.FSM.FSMState;
import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.StateChange;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.ID;
import com.game.gaika.data.SortManager;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.data.City;
import com.game.gaika.data.GameDataManager;
//import com.game.gaika.profile.Profile;
import com.game.gaika.scene.sub.FlagsSubScene;
import com.game.gaika.scene.sub.SortsSubScene;
import com.game.gaika.scene.sub.borderSubScene;
import com.game.gaika.sprite.NormalSprite;
import com.game.gaika.sprite.NumberSprite;
import com.game.gaika.sprite.OutSetWeaponSprite;
import com.game.gaika.sprite.WeaponInfoSprite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.game.gaika.data.ID.SORT_ASCEND.UP;
import static com.game.gaika.data.ID.STOR_TYPE.*;
import static com.game.gaika.data.ID.COUNTRY.*;
import static com.game.gaika.data.ID.TEAM_COLOR.*;
import static com.game.gaika.scene.WeaponSetoutScene.StateID.*;
import static com.game.gaika.data.ID.MSG_ID.*;
import static com.game.gaika.data.ID.SCENE_ID.*;
import static com.game.gaika.sound.SoundManager.playSound;

/**
 * Created by fangxg on 2015/6/23.
 */
public class WeaponSetoutScene extends BaseLogicScene implements IMessageHandler, StateChange {

    enum StateID {STATE_ID_SELECTED_NON, STATE_ID_SELECTED_WEAPON}

    //    enum MSG_ID {MSG_SELECT_WEAPON, MSG_BUTTON_BACK}
    //private City city;
    private  City city;



    public WeaponSetoutScene(City pCityNode) {

        super(WEAPON_SETOUT);
        city = pCityNode;
    }
    public WeaponSetoutScene() {

        super(WEAPON_SETOUT);
        city = null;
    }


    @Override
    public boolean isBacegroundEnabled() {
        return true;
    }

    @Override
    public void buildScene() {
        //city = pCityNode;
        SceneValueMap sceneValues = getSceneValuesMap();
        if (city != null) {
            sceneValues.cleanAllValues();
            sceneValues.setObject("city", city);
            sceneValues.setEnum("sortTypeUp", TYPE);
            sceneValues.setEnum("sortAscendUp", UP);

            Set<Enum> countryFilterSet = new HashSet<>();
            countryFilterSet.add(USA);
            countryFilterSet.add(USN);
            countryFilterSet.add(RUSSIA);
            countryFilterSet.add(GERMANY);
            countryFilterSet.add(ENGLAND);
            countryFilterSet.add(JAPAN);
            countryFilterSet.add(ALL);
            sceneValues.setObject("countryFilterUp", countryFilterSet);

        }
        // buildFSM(pStateID);


        GameDataManager gdm = GameDataManager.getInstance();

        NormalSprite backSprite = new NormalSprite(0.0f, 0.0f, "oper_bg7");
        addSprite(backSprite);

        List<ID.STOR_TYPE> items = new ArrayList<>();
        items.add(TYPE);
        items.add(NAME);
        items.add(LEVEL);
        items.add(SUPPLY);

        SortsSubScene sortsSubScene = new SortsSubScene("Up", items, this);
        sortsSubScene.setOffsetXY(213, 74);
        addChildScene(sortsSubScene);

        FlagsSubScene flagsSubScene = new FlagsSubScene("Up", this);
        flagsSubScene.setOffsetXY(469, 74);
        addChildScene(flagsSubScene);


        NumberSprite supplySprite = new NumberSprite(375, 108, false, "font04", gdm.getSupply(BLUE));
        addSprite(supplySprite);

        int offsetUpX = 48;
        int offsetUpY = 113 + 28;
        int countUp = 0;

//        Set<Enum> filterSet = (Set<Enum>) sceneValues.getObject("countryFilter");


        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.setTeamOut(true);
        filter.addTeamColor(BLUE);
        filter.setCountrys((Set<Enum>) (sceneValues.getObject("countryFilterUp")));
        List<BaseWeapon> weapNodes = gdm.getWeapons(filter);


        SortManager.sortWeapons(weapNodes, (ID.STOR_TYPE) sceneValues.getEnum("sortTypeUp"), (ID.SORT_ASCEND) sceneValues.getEnum("sortAscendUp"));


        for (BaseWeapon weapNode : weapNodes) {

            OutSetWeaponSprite weaponSprite;
            weaponSprite = new OutSetWeaponSprite(offsetUpX + (countUp % 10) * 69, offsetUpY + (countUp / 10) * 62,
                    weapNode, (City) (sceneValues.getObject("city")), new TouchMessage(MSG_SCENE_WEAPON_SETOUT__SELECT_WEAPON, null, this, weapNode.id));
            addSprite(weaponSprite);

            countUp++;
        }

        countUp = 0;
        for (BaseWeapon weapNode : weapNodes) {
            if (sceneValues.containsKey("selectedWeaponID") == true && weapNode.id == sceneValues.getInt("selectedWeaponID")) {
                NormalSprite selectedSprite = new NormalSprite(offsetUpX + (countUp % 10) * 69, offsetUpY + (countUp / 10) * 62, "unitcur2", 0);
                addSprite(selectedSprite);

                WeaponInfoSprite infoSprite = new WeaponInfoSprite(weapNode, WeaponInfoSprite.INFO_CARD_ALPHA);
                if (countUp % 10 >= 5) {
                    infoSprite.setX(0);
                } else {
                    infoSprite.setX(800 - 211);
                }
                infoSprite.setY(100);
                addSprite(infoSprite);
            }
            countUp++;
        }


        NormalSprite rtSprite = new NormalSprite(35, 522, "retn_bt1", 0, new TouchMessage(MSG_SCENE_WEAPON_SETOUT__BUTTON_BACK, null, this));
        addSprite(rtSprite);

        NormalSprite back2Sprite = new NormalSprite(0.0f, 0.0f, "oper_bg7", 0, new TouchMessage(MSG_SCENE_WEAPON_SETOUT__BACKUP_TOUCH, null, this), 1, 1, 0.0f);
        addSprite(back2Sprite);

        addChildScene(new borderSubScene(this));
    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {
        SceneValueMap sceneValues = getSceneValuesMap();
        ID.MSG_ID msgID = pTouchMessage.getMessageID();
        if (msgID == MSG_SCENE_WEAPON_SETOUT__BUTTON_BACK) {
             playSound("back01");
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
            SceneManager.render(battlefieldScene);
        }
        if (msgID == MSG_SCENE_WEAPON_SETOUT__SELECT_WEAPON) {
            int newSelectedWeaponID = pTouchMessage.getParam();

            if (sceneValues.containsKey("selectedWeaponID") == true && sceneValues.getInt("selectedWeaponID") == newSelectedWeaponID) {
                GameDataManager gdm = GameDataManager.getInstance();
                BaseWeapon weapon = gdm.weapons.get(newSelectedWeaponID);
                City cityNode = (City) (sceneValues.getObject("city"));
                if (weapon.setOut == false && weapon.info.canSetoutByCityType(cityNode.type) == true && weapon.life > 0 && weapon.info.supply <= gdm.getSupply(BLUE)) {

                    gdm.addSupply(BLUE, -weapon.info.supply);
                    weapon.doSetOut(cityNode);
                     playSound("haichi01");
                    BattlefieldScene battlefieldScene = new BattlefieldScene(false);
                    SceneManager.render(battlefieldScene);
                } else {

                }

            } else {
                 playSound("select01");
                sceneValues.setInt("selectedWeaponID", newSelectedWeaponID);
                WeaponSetoutScene WeaponSetoutScene = new WeaponSetoutScene();
                SceneManager.render(WeaponSetoutScene);
            }
        }
        if (msgID == MSG_SCENE_WEAPON_SETOUT__BACKUP_TOUCH) {
            sceneValues.remove("selectedWeaponID");
            WeaponSetoutScene WeaponSetoutScene = new WeaponSetoutScene();
            SceneManager.render(WeaponSetoutScene);
        }
    }

    @Override
    public void onStateChanged(Enum oldState, Enum newState, TouchMessage pTouchMessage) {
    }

    @Override
    public void buildFSM(Enum pCurrentStateID) {
        addFSMClass(new FSMClass(pCurrentStateID));

        FSMState stateNon = new FSMState(STATE_ID_SELECTED_NON);
        stateNon.addTransition(MSG_SCENE_WEAPON_SETOUT__SELECT_WEAPON, STATE_ID_SELECTED_WEAPON);
        getFSMClass().addState(stateNon);

        FSMState state1 = new FSMState(STATE_ID_SELECTED_WEAPON);
        state1.addTransition(MSG_SCENE_WEAPON_SETOUT__SELECT_WEAPON, STATE_ID_SELECTED_WEAPON);
        getFSMClass().addState(state1);
    }
}

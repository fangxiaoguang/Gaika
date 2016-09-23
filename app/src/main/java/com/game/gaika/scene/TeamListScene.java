package com.game.gaika.scene;

import com.game.frame.scene.BaseLogicScene;
import com.game.frame.FSM.IMessageHandler;
import com.game.frame.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;
import com.game.gaika.data.SortManager;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.sub.FlagsSubScene;
import com.game.gaika.scene.sub.SortsSubScene;
import com.game.gaika.scene.sub.borderSubScene;
import com.game.frame.sprite.NormalSprite;
import com.game.frame.sprite.NumberSprite;
import com.game.frame.sprite.TextSprite;
import com.game.frame.texture.TexRegionManager;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.game.gaika.data.ID.COUNTRY.*;
import static com.game.gaika.data.ID.SCENE_INIT_TYPE.NOT_INIT;
import static com.game.gaika.data.ID.SORT_ASCEND.*;
import static com.game.gaika.data.ID.STOR_TYPE.*;
import static com.game.gaika.data.ID.TEAM_COLOR.*;
import static com.game.gaika.data.ID.MSG_ID.*;

import  static com.game.gaika.data.ID.SCENE_ID.*;
/**
 * Created by fangxg on 2015/7/3.
 */
public class TeamListScene extends BaseLogicScene implements IMessageHandler {

    //enum MSG_ID {MSG_RETURN, MSG_SCROOL_UP, MSG_SCROOL_DOWN}
    public TeamListScene(boolean pInit, ID.SCENE_ID pParentSceneID) {
        super(TEAM_LIST);
        preSceneID = pParentSceneID;

        GameDataManager gdm = GameDataManager.getInstance();

        NormalSprite backSprite = new NormalSprite(0.0f, 0.0f, "list_bg1");
        addSprite(backSprite);
        SceneValueMap sceneValues =  getSceneValuesMap();
        if (pInit == true) {
            sceneValues.cleanAllValues();
            sceneValues.setEnum("sortTypeUp", TYPE);
            sceneValues.setEnum("sortAscendUp", UP);

            Set<Enum> countryFilterSetUp = new HashSet<>();
            countryFilterSetUp.add(USA);
            countryFilterSetUp.add(USN);
            countryFilterSetUp.add(RUSSIA);
            countryFilterSetUp.add(GERMANY);
            countryFilterSetUp.add(ENGLAND);
            countryFilterSetUp.add(JAPAN);
            countryFilterSetUp.add(ALL);
            sceneValues.setObject("countryFilterUp", countryFilterSetUp);


            sceneValues.setInt("pageUp", 1);
        }

        List<ID.STOR_TYPE> items = new ArrayList<>();
        items.add(TYPE);
        items.add(NAME);
        items.add(COUNTRY);
        items.add(LEVEL);
        items.add(SUPPLY);

        SortsSubScene sortsSubSceneUp = new SortsSubScene("Up", items, this);
        sortsSubSceneUp.setOffsetXY(210, 30);
        addChildScene(sortsSubSceneUp);

        FlagsSubScene flagsSubSceneUp = new FlagsSubScene("Up", this);
        flagsSubSceneUp.setOffsetXY(524, 32);
        addChildScene(flagsSubSceneUp);


        if (sceneValues.getInt("pageUp") > 1) {

            NormalSprite scroolUpSprite = new NormalSprite(298, 60, "scroolb2", 0, new TouchMessage(MSG_SCENE_TEAM_LIST__SCROOL_UP, null, this));
            addSprite(scroolUpSprite);
        }

        int page = sceneValues.getInt("pageUp");
//-----------------------------------------------------
        WeaponSelectFilter filterUp = new WeaponSelectFilter();
        filterUp.setTeamOut(true);
        //filterUp.setSetOut(true);
        filterUp.addTeamColor(BLUE);
        filterUp.setCountrys((Set<Enum>) (sceneValues.getObject("countryFilterUp")));
        List<BaseWeapon> weapNodesUp = gdm.getWeapons(filterUp);


        SortManager.sortWeapons(weapNodesUp, (ID.STOR_TYPE) sceneValues.getEnum("sortTypeUp"), (ID.SORT_ASCEND) sceneValues.getEnum("sortAscendUp"));


//        List<BaseWeapon> weapNodes = gd.getSortedBlueWeapnoNodes(GameSetup.upSortTypes[ID.SCENE_TEAM_LIST], GameSetup.upSortAscends[ID.SCENE_TEAM_LIST]);
//        Set<Integer> countyFilter1 = new HashSet<Integer>();
//        if (GameSetup.upCountyFlag[ID.SCENE_TEAM_LIST][0]) {
//            countyFilter1.add(ID.COUNTRY_TYPE_1);
//        }
//        if (GameSetup.upCountyFlag[ID.SCENE_TEAM_LIST][1]) {
//            countyFilter1.add(ID.COUNTRY_TYPE_2);
//        }
//        if (GameSetup.upCountyFlag[ID.SCENE_TEAM_LIST][2]) {
//            countyFilter1.add(ID.COUNTRY_TYPE_3);
//        }
//        if (GameSetup.upCountyFlag[ID.SCENE_TEAM_LIST][3]) {
//            countyFilter1.add(ID.COUNTRY_TYPE_4);
//        }
//        if (GameSetup.upCountyFlag[ID.SCENE_TEAM_LIST][4]) {
//            countyFilter1.add(ID.COUNTRY_TYPE_5);
//        }
//        if (GameSetup.upCountyFlag[ID.SCENE_TEAM_LIST][5]) {
//            countyFilter1.add(ID.COUNTRY_TYPE_6);
//        }
//        if (GameSetup.upCountyFlag[ID.SCENE_TEAM_LIST][6]) {
//            countyFilter1.add(ID.COUNTRY_TYPE_7);
//        }

        // int offsetUpX = 52;
        // int offsetUpY = 120;
        int countUp = 0;

        for (BaseWeapon weap : weapNodesUp) {


            if ((page - 1) * 20 <= countUp && countUp < page * 20) {

                int statusIndex = 2;
                if (weap.life <= 0) {
                    statusIndex = 0;
                } else {

                    if (weap.setOut == false) {
                        statusIndex = 2;
                    } else {
                        if (weap.moveEnding == true) {
                            statusIndex = 1;
                        } else {
                            statusIndex = -1;
                        }
                    }
                }
                if (statusIndex != -1) {
                    NormalSprite statusSprite = new NormalSprite(27, 113 + 21 * (countUp % 20), "list_bt1", statusIndex);
                    addSprite(statusSprite);
                }

                TextSprite nameSprite = new TextSprite(50, 113 + 21 * (countUp % 20), true, weap.info.name, TexRegionManager.getInstance().getFont12());
                addSprite(nameSprite);

                TextSprite typeNameSprite = new TextSprite(179, 113 + 21 * (countUp % 20), true, weap.info.type.toString(), TexRegionManager.getInstance().getFont12());
                addSprite(typeNameSprite);

                NormalSprite countrySprite = new NormalSprite(268, 115 + 21 * (countUp % 20), "flag01", weap.info.country.ordinal());
                addSprite(countrySprite);

                NormalSprite lvSprite1 = new NormalSprite(305, 115 + 21 * (countUp % 20), "list_bt1", 4);
                addSprite(lvSprite1);
                NormalSprite lvSprite2 = new NormalSprite(323, 115 + 21 * (countUp % 20), "font03", weap.getLv());
                addSprite(lvSprite2);

                NumberSprite liveExSprite = new NumberSprite(355 + 24, 115 + 21 * (countUp % 20), false, "font03", weap.getLifeEx(), 0, 0, 1.0f);
                addSprite(liveExSprite);

                NumberSprite fuelSprite1 = new NumberSprite(460 + 5 - 12 * 3, 115 + 21 * (countUp % 20), false, "font03", weap.fuel, 0, 0, 1.0f);
                addSprite(fuelSprite1);
                NormalSprite fuelSprite2 = new NormalSprite(431, 115 + 21 * (countUp % 20), "list_bt1", 6);
                addSprite(fuelSprite2);
                NumberSprite fuelSprite3 = new NumberSprite(452 + 12 * 2, 115 + 21 * (countUp % 20), false, "font03", weap.info.fuel, 0, 0, 1.0f);
                addSprite(fuelSprite3);


                NormalSprite attackSprite1 = new NormalSprite(488 + 19 * 0, 115 + 21 * (countUp % 20), "font07", weap.getAttack(ID.WEAPON_TYPE_EX.AIR));
                addSprite(attackSprite1);
                NormalSprite attackSprite2 = new NormalSprite(488 + 19 * 1, 115 + 21 * (countUp % 20), "font07", weap.getAttack(ID.WEAPON_TYPE_EX.GROUND));
                addSprite(attackSprite2);
                NormalSprite attackSprite3 = new NormalSprite(488 + 19 * 2, 115 + 21 * (countUp % 20), "font07", weap.getAttack(ID.WEAPON_TYPE_EX.SHIP));
                addSprite(attackSprite3);
                NormalSprite attackSprite4 = new NormalSprite(488 + 19 * 3, 115 + 21 * (countUp % 20), "font07", weap.getAttack(ID.WEAPON_TYPE_EX.SUBMARINE));
                addSprite(attackSprite4);

                NormalSprite defenseSprite1 = new NormalSprite(572 + 19 * 0, 115 + 21 * (countUp % 20), "font07", weap.getDefense(ID.WEAPON_TYPE_EX.AIR));
                addSprite(defenseSprite1);
                NormalSprite defenseSprite2 = new NormalSprite(572 + 19 * 1, 115 + 21 * (countUp % 20), "font07", weap.getDefense(ID.WEAPON_TYPE_EX.GROUND));
                addSprite(defenseSprite2);
                NormalSprite defenseSprite3 = new NormalSprite(572 + 19 * 2, 115 + 21 * (countUp % 20), "font07", weap.getDefense(ID.WEAPON_TYPE_EX.SHIP));
                addSprite(defenseSprite3);
                NormalSprite defenseSprite4 = new NormalSprite(572 + 19 * 3, 115 + 21 * (countUp % 20), "font07", weap.getDefense(ID.WEAPON_TYPE_EX.SUBMARINE));
                addSprite(defenseSprite4);


                NumberSprite moveSprite = new NumberSprite( 676 + 24 - 10, 115 + 21 * (countUp % 20), false,"font03", weap.move, 0, 0, 1.0f);
                addSprite(moveSprite);

                NumberSprite supplySprite = new NumberSprite( 722 + 36 + 1, 115 + 21 * (countUp % 20), false,"font03", weap.info.supply, 0, 0, 1.0f);
                addSprite(supplySprite);

            }

            countUp++;

        }

//-----------------------------------------------------
        if (sceneValues.getInt("pageUp") < (countUp + 19) / 20) {

            NormalSprite scroolUpSprite = new NormalSprite(298, 545, "scroolb2", 1, new TouchMessage(MSG_SCENE_TEAM_LIST__SCROOL_DOWN, null, this));
            addSprite(scroolUpSprite);
        }
        NormalSprite buttonRetSprite = new NormalSprite(35, 556, "retn_bt1", 0, new TouchMessage(MSG_SCENE_TEAM_LIST__RETURN, null, this));
        addSprite(buttonRetSprite);

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
    public void onHandlMessage(TouchMessage pTouchMessage) {
        GameDataManager gdm = GameDataManager.getInstance();
        Enum msgID = pTouchMessage.getMessageID();
        SceneValueMap sceneValues =  getSceneValuesMap();
        if (msgID == MSG_SCENE_TEAM_LIST__RETURN) {
            BaseLogicScene scene = SceneFactory.createScene(preSceneID, NOT_INIT);
            SceneManager.render(scene);
        }

        if (msgID == MSG_SCENE_TEAM_LIST__SCROOL_UP) {
            int page = sceneValues.getInt("pageUp");
            sceneValues.setInt("pageUp", page - 1);
            TeamListScene scene = new TeamListScene(false, preSceneID);
            SceneManager.render(scene);
        }
        if (msgID == MSG_SCENE_TEAM_LIST__SCROOL_DOWN) {
            int page = sceneValues.getInt("pageUp");
            sceneValues.setInt("pageUp", page + 1);
            TeamListScene scene = new TeamListScene(false, preSceneID);
            SceneManager.render(scene);

        }
    }
}

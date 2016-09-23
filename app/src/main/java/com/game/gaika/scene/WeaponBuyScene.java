package com.game.gaika.scene;

import com.game.frame.scene.BaseLogicScene;
import com.game.frame.FSM.IMessageHandler;
import com.game.frame.FSM.TouchMessage;
import com.game.gaika.action.BaseAction;
import com.game.gaika.action.GameOverDilogNoAction;
import com.game.gaika.action.GameOverDilogYesAction;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;
import com.game.gaika.data.SortManager;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.data.weapon.WeaponFactory;
import com.game.gaika.data.weapon.WeaponInfo;
import com.game.gaika.scene.sub.FlagsSubScene;
import com.game.gaika.scene.sub.SortsSubScene;
import com.game.gaika.scene.sub.TopButtosSubScene;
import com.game.gaika.scene.sub.borderSubScene;
import com.game.frame.sprite.NormalSprite;
import com.game.frame.sprite.NumberSprite;
import com.game.gaika.sprite.TeamBuildWeaponSprite;
import com.game.gaika.sprite.WeaponInfoSprite;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.game.gaika.data.ID.COUNTRY.*;
import static com.game.gaika.data.ID.SORT_ASCEND.*;
import static com.game.gaika.data.ID.STOR_TYPE.*;
import static com.game.gaika.data.ID.TEAM_COLOR.*;
import static com.game.gaika.data.ID.MSG_ID.*;
import static com.game.gaika.data.ID.SCENE_ID.*;
import static com.game.frame.sound.SoundManager.playSound;

/**
 * Created by fangxg on 2015/6/29.
 */
public class WeaponBuyScene extends BaseLogicScene implements IMessageHandler {
//    enum MSG_ID {MSG_SELECT_WEAPON_UP, MSG_SELECT_WEAPON_DOWN, MSG_UP_LEFT, MSG_UP_RIGHT, MSG_DOWN_LEFT, MSG_DOWN_RIGHT}

    public WeaponBuyScene(boolean isInit) {
        super(WEAPON_BUY);

        GameDataManager gdm = GameDataManager.getInstance();

        NormalSprite backSprite = new NormalSprite(0.0f, 0.0f, "oper_bg2");
        addSprite(backSprite);

        TopButtosSubScene topButtosSubScene = new TopButtosSubScene(this);
        topButtosSubScene.setOffsetXY(0, 0);
        addChildScene(topButtosSubScene);
        SceneValueMap sceneValues = getSceneValuesMap();
        if (isInit == true) {
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

            sceneValues.setEnum("sortTypeDown", TYPE);
            sceneValues.setEnum("sortAscendDown", UP);

            Set<Enum> countryFilterSetDown = new HashSet<>();
            countryFilterSetDown.add(USA);
            countryFilterSetDown.add(USN);
            countryFilterSetDown.add(RUSSIA);
            countryFilterSetDown.add(GERMANY);
            countryFilterSetDown.add(ENGLAND);
            countryFilterSetDown.add(JAPAN);
            countryFilterSetDown.add(ALL);
            sceneValues.setObject("countryFilterDown", countryFilterSetDown);

            sceneValues.setInt("pageUp", 1);
            sceneValues.setInt("pageDown", 1);
        }

        List<ID.STOR_TYPE> items = new ArrayList<>();
        items.add(TYPE);
        items.add(NAME);
        items.add(COUNTRY);
        items.add(BUY_COST);

        SortsSubScene sortsSubSceneUp = new SortsSubScene("Up", items, this);
        sortsSubSceneUp.setOffsetXY(213, 46);
        addChildScene(sortsSubSceneUp);

        FlagsSubScene flagsSubSceneUp = new FlagsSubScene("Up", this);
        flagsSubSceneUp.setOffsetXY(469, 46);
        addChildScene(flagsSubSceneUp);


        NumberSprite moneySprite = new NumberSprite(375, 80, false, "font04", gdm.money, 0, 0, 1.0f);
        addSprite(moneySprite);



      /*  WeaponSelectFilter filterUp = new WeaponSelectFilter();
        filterUp.setTeamOut(true);
        //filterUp.setSetOut(true);
        filterUp.addTeamColor(BLUE);
        filterUp.setCountrys((Set<Enum>) (sceneValues.getObject("countryFilterUp")));
        List<BaseWeapon> weapNodesUp = gdm.getWeapons(filterUp);*/
        Set<Enum> countryFilterSetUp = (Set<Enum>) sceneValues.getObject("countryFilterUp");

        List<BaseWeapon> weaponsUp = gdm.getCanBuyWeapnos();

        SortManager.sortWeapons(weaponsUp, (ID.STOR_TYPE) sceneValues.getEnum("sortTypeUp"), (ID.SORT_ASCEND) sceneValues.getEnum("sortAscendUp"));

        int offsetUpX = 71;
        int offsetUpY = 123;
        int countUp = 0;
        for (BaseWeapon weaponUp : weaponsUp) {

            if (countryFilterSetUp.contains(weaponUp.info.country)) {
                if ((sceneValues.getInt("pageUp") - 1) * 27 <= countUp && countUp < sceneValues.getInt("pageUp") * 27) {
                    TeamBuildWeaponSprite weaponSprite;


                    float x = offsetUpX
                            + ((countUp - (sceneValues.getInt("pageUp") - 1) * 27) % 9) * 69;
                    float y = offsetUpY
                            + ((countUp - (sceneValues.getInt("pageUp") - 1) * 27) / 9) * 62;


                    weaponSprite = new TeamBuildWeaponSprite(x, y,
                            weaponUp, new TouchMessage(MSG_SCENE_WEAPON_BUY__SELECT_WEAPON_UP, null, this, weaponUp.info.id));
                    addSprite(weaponSprite);


                    int teildOffsetIndex = 0;
                    if (weaponUp.info.price > gdm.money) {
                        teildOffsetIndex = 22;
                    }
                    NumberSprite priceSprite = new NumberSprite(x + 59, y + 46, false, "font03", weaponUp.info.price, 0, teildOffsetIndex, 1.0f);
                    addSprite(priceSprite);

                    if (sceneValues.containsKey("selectedWeaponIDUp") == true && sceneValues.getInt("selectedWeaponIDUp") == weaponUp.info.id) {

                        NormalSprite unSprite = new NormalSprite(x, y, "unitcur3");
                        addSprite(unSprite);

                        WeaponInfoSprite infoSprite = new WeaponInfoSprite(weaponUp, WeaponInfoSprite.INFO_CARD_ALPHA);
                        if (countUp % 10 >= 5) {
                            infoSprite.setX(0);
                        } else {
                            infoSprite.setX(800 - 211);
                        }
                        infoSprite.setY(100);
                        infoSprite.setZ(100);
                        addSprite(infoSprite);
                    }
                }
                countUp++;
            }


        }

        if (sceneValues.getInt("pageUp") > 1) {
            NormalSprite liftSprite = new NormalSprite(34, 120, "scroolbt", 0, new TouchMessage(MSG_SCENE_WEAPON_BUY__UP_LEFT, null, this));
            addSprite(liftSprite);
        }
        if (sceneValues.getInt("pageUp") < (countUp - 1) / 30 + 1) {
            NormalSprite rightSprite = new NormalSprite(703, 120, "scroolbt", 1, new TouchMessage(MSG_SCENE_WEAPON_BUY__UP_RIGHT, null, this));
            addSprite(rightSprite);
        }

        ///////////////////////////////////////////////////////
        SortsSubScene sortsSubSceneDown = new SortsSubScene("Down", items, this);
        sortsSubSceneDown.setOffsetXY(247, 368);
        addChildScene(sortsSubSceneDown);

        FlagsSubScene flagsSubSceneDown = new FlagsSubScene("Down", this);
        flagsSubSceneDown.setOffsetXY(502, 368);
        addChildScene(flagsSubSceneDown);

        WeaponSelectFilter filterDown = new WeaponSelectFilter();
        // filterDown.setTeamOut(true);
        filterDown.addTeamColor(BLUE);
        filterDown.setCountrys((Set<Enum>) (sceneValues.getObject("countryFilterDown")));
        List<BaseWeapon> weapNodesDown = gdm.getWeapons(filterDown);


        SortManager.sortWeapons(weapNodesDown, (ID.STOR_TYPE) sceneValues.getEnum("sortTypeDown"), (ID.SORT_ASCEND) sceneValues.getEnum("sortAscendDown"));

        int offsetDownX = 138;
        int offsetDownY = 400;
        int countDown = 0;
        for (BaseWeapon weapNodeDown : weapNodesDown) {


            if ((sceneValues.getInt("pageDown") - 1) * 36 <= countDown && countDown < sceneValues.getInt("pageDown") * 36) {


                TeamBuildWeaponSprite weaponSprite;
                weaponSprite = new TeamBuildWeaponSprite(offsetDownX
                        + ((countDown - (sceneValues.getInt("pageDown") - 1) * 36) % 9) * 67, offsetDownY
                        + ((countDown - (sceneValues.getInt("pageDown") - 1) * 36) / 9) * 46,
                        weapNodeDown, new TouchMessage(MSG_SCENE_WEAPON_BUY__SELECT_WEAPON_DOWN, null, this, weapNodeDown.id));
                addSprite(weaponSprite);


                if (sceneValues.containsKey("selectedWeaponIDDown") == true && sceneValues.getInt("selectedWeaponIDDown") == weapNodeDown.id) {

                    NormalSprite unSprite = new NormalSprite(offsetDownX
                            + ((countDown - (sceneValues.getInt("pageDown") - 1) * 36) % 9) * 67, offsetDownY
                            + ((countDown - (sceneValues.getInt("pageDown") - 1) * 36) / 9) * 46, "unitcur2");
                    addSprite(unSprite);

                    WeaponInfoSprite infoSprite = new WeaponInfoSprite(weapNodeDown, WeaponInfoSprite.INFO_CARD_ALPHA);
                    if ((countDown - (sceneValues.getInt("pageDown") - 1) * 36) % 9 >= 4) {
                        infoSprite.setX(0);
                    } else {
                        infoSprite.setX(800 - 211);
                    }
                    infoSprite.setY(100);
                    infoSprite.setZ(100);
                    addSprite(infoSprite);
                }

            }
            countDown++;
        }

        if (sceneValues.getInt("pageDown") > 1) {

            NormalSprite liftSprite = new NormalSprite(100, 410, "scroolbt", 0, new TouchMessage(MSG_SCENE_WEAPON_BUY__DOWN_LEFT, null, this));
            addSprite(liftSprite);
        }
        if (sceneValues.getInt("pageDown") < (countDown - 1) / 36 + 1) {
            NormalSprite rightSprite = new NormalSprite(750, 410, "scroolbt", 1, new TouchMessage(MSG_SCENE_WEAPON_BUY__DOWN_RIGHT, null, this));
            addSprite(rightSprite);
        }

        NormalSprite back2Sprite = new NormalSprite(0.0f, 0.0f, "oper_bg2", 0, new TouchMessage(MSG_SCENE_WEAPON_BUY__BACKUP_TOUCH, null, this), 1, 1, 0.0f);
        addSprite(back2Sprite);

        //top button
        addChildScene(new TopButtosSubScene(this));

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
        SceneValueMap sceneValues = getSceneValuesMap();
        ID.MSG_ID msgID = pTouchMessage.getMessageID();
        if (msgID == MSG_SCENE_WEAPON_BUY__UP_LEFT) {
             playSound("scrl01");
            sceneValues.setInt("pageUp", sceneValues.getInt("pageUp") - 1);
            WeaponBuyScene weaponBuyScene = new WeaponBuyScene(false);
            SceneManager.render(weaponBuyScene);
        }
        if (msgID == MSG_SCENE_WEAPON_BUY__UP_RIGHT) {
             playSound("scrl01");
            sceneValues.setInt("pageUp", sceneValues.getInt("pageUp") + 1);
            WeaponBuyScene weaponBuyScene = new WeaponBuyScene(false);
            SceneManager.render(weaponBuyScene);
        }
        if (msgID == MSG_SCENE_WEAPON_BUY__DOWN_LEFT) {
             playSound("scrl01");
            sceneValues.setInt("pageDown", sceneValues.getInt("pageDown") - 1);
            WeaponBuyScene weaponBuyScene = new WeaponBuyScene(false);
            SceneManager.render(weaponBuyScene);
        }
        if (msgID == MSG_SCENE_WEAPON_BUY__DOWN_RIGHT) {
             playSound("scrl01");
            sceneValues.setInt("pageDown", sceneValues.getInt("pageDown") + 1);
            WeaponBuyScene weaponBuyScene = new WeaponBuyScene(false);
            SceneManager.render(weaponBuyScene);
        }
        if (msgID == MSG_SCENE_WEAPON_BUY__SELECT_WEAPON_UP) {
            int newSelectedWeaponInfoID = pTouchMessage.getParam();
            GameDataManager gdm = GameDataManager.getInstance();
            sceneValues.remove("selectedWeaponIDDown");
            if (sceneValues.containsKey("selectedWeaponIDUp") == true && sceneValues.getInt("selectedWeaponIDUp") == newSelectedWeaponInfoID) {
                WeaponInfo info = gdm.weapInfos.get(newSelectedWeaponInfoID);
                if (info.price <= gdm.money) {

                     playSound("haichi01");
                    sceneValues.remove("selectedWeaponIDUp");

                    gdm.money -= info.price;
                    int newID = gdm.getNewWeaponId();
                    BaseWeapon weapon = WeaponFactory.getInstance().newWeapon(info);
                    weapon.id = newID;
                    gdm.weapons.put(weapon.id, weapon);

                    WeaponBuyScene weaponBuyScene = new WeaponBuyScene(false);
                    SceneManager.render(weaponBuyScene);
                }


            } else {
                 playSound("select01");
                sceneValues.setInt("selectedWeaponIDUp", newSelectedWeaponInfoID);

                WeaponBuyScene weaponBuyScene = new WeaponBuyScene(false);
                SceneManager.render(weaponBuyScene);
            }
        }
        if (msgID == MSG_SCENE_WEAPON_BUY__SELECT_WEAPON_DOWN) {
            int newSelectedWeaponID = pTouchMessage.getParam();

            if (sceneValues.containsKey("selectedWeaponIDDown") == true && sceneValues.getInt("selectedWeaponIDDown") == newSelectedWeaponID) {

            } else {
                 playSound("select01");
                sceneValues.remove("selectedWeaponIDUp");
                sceneValues.remove("selectedWeaponIDDown");
                sceneValues.setInt("selectedWeaponIDDown", newSelectedWeaponID);

                WeaponBuyScene weaponBuyScene = new WeaponBuyScene(false);
                SceneManager.render(weaponBuyScene);
            }
        }

        if (msgID == MSG_SCENE_WEAPON_BUY__BACKUP_TOUCH) {
            sceneValues.remove("selectedWeaponIDUp");
            sceneValues.remove("selectedWeaponIDDown");
            WeaponBuyScene weaponBuyScene = new WeaponBuyScene(false);
            SceneManager.render(weaponBuyScene);
        }
        if (msgID == MSG_SCENE_HUD__GAME_OVER_DILOG_YES) {
             playSound("select01");
            BaseAction act = new GameOverDilogYesAction();
            act.doAction();
        }
        if (msgID == MSG_SCENE_HUD__GAME_OVER_DILOG_NO) {
             playSound("back01");
            BaseAction act = new GameOverDilogNoAction();
            act.doAction();
        }
    }
}

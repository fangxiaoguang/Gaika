package com.game.gaika.scene;

import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.action.BaseAction;
import com.game.gaika.action.GameOverDilogNoAction;
import com.game.gaika.action.GameOverDilogYesAction;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;
import com.game.gaika.data.SortManager;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.sub.FlagsSubScene;
import com.game.gaika.scene.sub.SortsSubScene;
import com.game.gaika.scene.sub.TopButtosSubScene;
import com.game.gaika.scene.sub.borderSubScene;
import com.game.gaika.sound.SoundManager;
import com.game.gaika.sprite.NormalSprite;
import com.game.gaika.sprite.NumberSprite;
import com.game.gaika.sprite.TeamBuildWeaponSprite;
import com.game.gaika.sprite.WeaponInfoSprite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.game.gaika.data.ID.COUNTRY.USA;
import static com.game.gaika.data.ID.COUNTRY.USN;
import static com.game.gaika.data.ID.COUNTRY.RUSSIA;
import static com.game.gaika.data.ID.COUNTRY.GERMANY;
import static com.game.gaika.data.ID.COUNTRY.ENGLAND;
import static com.game.gaika.data.ID.COUNTRY.JAPAN;
import static com.game.gaika.data.ID.COUNTRY.ALL;
import static com.game.gaika.data.ID.SORT_ASCEND.UP;
import static com.game.gaika.data.ID.STOR_TYPE.LEVEL;
import static com.game.gaika.data.ID.STOR_TYPE.NAME;
import static com.game.gaika.data.ID.STOR_TYPE.SELL_COST;
import static com.game.gaika.data.ID.STOR_TYPE.TYPE;
import static com.game.gaika.data.ID.TEAM_COLOR.*;

import static com.game.gaika.data.ID.MSG_ID.*;
import static com.game.gaika.data.ID.SCENE_ID.*;

/**
 * Created by fangxg on 2015/7/1.
 */
public class WeaponSellScene extends BaseLogicScene implements IMessageHandler {
    //    enum MSG_ID {MSG_SELECT_WEAPON_UP, MSG_UP_LEFT, MSG_UP_RIGHT}
    private static final int COUNT_PER_PAGE = 9 * 6;

    public WeaponSellScene(boolean isInit) {
        super(WEAPON_SELL);

        GameDataManager gdm = GameDataManager.getInstance();

        NormalSprite backSprite = new NormalSprite(0.0f, 0.0f, "oper_bg4");
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

            sceneValues.setInt("pageUp", 1);
        }


        List<ID.STOR_TYPE> items = new ArrayList<>();
        items.add(TYPE);
        items.add(NAME);
        items.add(LEVEL);
        items.add(SELL_COST);

        SortsSubScene sortsSubSceneUp = new SortsSubScene("Up", items, this);
        sortsSubSceneUp.setOffsetXY(213, 46);
        addChildScene(sortsSubSceneUp);

        FlagsSubScene flagsSubSceneUp = new FlagsSubScene("Up", this);
        flagsSubSceneUp.setOffsetXY(469, 46);
        addChildScene(flagsSubSceneUp);


        NumberSprite moneySprite = new NumberSprite(375, 80, false, "font04", gdm.money, 0, 0, 1.0f);
        addSprite(moneySprite);

        WeaponSelectFilter filterUp = new WeaponSelectFilter();
        //filterUp.setTeamOut(true);
        //filterUp.setSetOut(true);
        //filterUp.setLifeIsZero();
        filterUp.addTeamColor(BLUE);
        filterUp.setCountrys((Set<Enum>) (sceneValues.getObject("countryFilterUp")));
        List<BaseWeapon> weapNodesUp = gdm.getWeapons(filterUp);

        SortManager.sortWeapons(weapNodesUp, (ID.STOR_TYPE) sceneValues.getEnum("sortTypeUp"), (ID.SORT_ASCEND) sceneValues.getEnum("sortAscendUp"));

        int offsetUpX = 76;
        int offsetUpY = 123;
        int countUp = 0;
        for (BaseWeapon weapNodeUp : weapNodesUp) {

            if ((sceneValues.getInt("pageUp") - 1) * COUNT_PER_PAGE <= countUp && countUp < sceneValues.getInt("pageUp") * COUNT_PER_PAGE) {


                float x = offsetUpX
                        + ((countUp - (sceneValues.getInt("pageUp") - 1) * COUNT_PER_PAGE) % 9) * 69;
                float y = offsetUpY
                        + ((countUp - (sceneValues.getInt("pageUp") - 1) * COUNT_PER_PAGE) / 9) * 64;


                TeamBuildWeaponSprite weaponSprite = new TeamBuildWeaponSprite(x, y,
                        weapNodeUp, new TouchMessage(MSG_SCENE_WEAPON_SELL__SELECT_WEAPON_UP, null, this, weapNodeUp.id));
                addSprite(weaponSprite);


                NumberSprite priceSprite = new NumberSprite(x + 59, y + 46, false, "font03", weapNodeUp.info.price, 0, 0, 1.0f);
                addSprite(priceSprite);


                if (weapNodeUp.life == 0) {
                    NormalSprite breakSprite = new NormalSprite(x + 40.0f, y + 12.0f, "unit_des");
                    addSprite(breakSprite);
                }

                if (sceneValues.containsKey("selectedWeaponIDUp") == true && sceneValues.getInt("selectedWeaponIDUp") == weapNodeUp.id) {

                    NormalSprite unSprite = new NormalSprite(x, y, "unitcur3");
                    addSprite(unSprite);


                    WeaponInfoSprite infoSprite = new WeaponInfoSprite(weapNodeUp, WeaponInfoSprite.INFO_CARD_ALPHA);
                    if (countUp % 9 >= 5) {
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
        if (sceneValues.getInt("pageUp") > 1) {
            NormalSprite liftSprite = new NormalSprite(34, 220, "scroolbt", 0, new TouchMessage(MSG_SCENE_WEAPON_SELL__UP_LEFT, null, this));
            addSprite(liftSprite);
        }
        if (sceneValues.getInt("pageUp") < (countUp - 1) / COUNT_PER_PAGE + 1) {
            NormalSprite rightSprite = new NormalSprite(703, 220, "scroolbt", 1, new TouchMessage(MSG_SCENE_WEAPON_SELL__UP_RIGHT, null, this));
            addSprite(rightSprite);
        }

        NormalSprite back2Sprite = new NormalSprite(0.0f, 0.0f, "oper_bg4", 0, new TouchMessage(MSG_SCENE_WEAPON_SELL__BACKUP_TOUCH, null, this), 1, 1, 0.0f);
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
        if (msgID == MSG_SCENE_WEAPON_SELL__UP_LEFT) {
            SoundManager.getInstance().playSound("scrl01");
            sceneValues.remove("selectedWeaponIDUp");
            sceneValues.setInt("pageUp", sceneValues.getInt("pageUp") - 1);
            WeaponSellScene scene = new WeaponSellScene(false);
            SceneManager.render(scene);
        }
        if (msgID == MSG_SCENE_WEAPON_SELL__UP_RIGHT) {
            SoundManager.getInstance().playSound("scrl01");
            sceneValues.remove("selectedWeaponIDUp");
            sceneValues.setInt("pageUp", sceneValues.getInt("pageUp") + 1);
            WeaponSellScene scene = new WeaponSellScene(false);
            SceneManager.render(scene);
        }

        if (msgID == MSG_SCENE_WEAPON_SELL__SELECT_WEAPON_UP) {
            int newSelectedWeaponID = pTouchMessage.getParam();
            GameDataManager gdm = GameDataManager.getInstance();
            SoundManager.getInstance().playSound("select01");
            if (sceneValues.containsKey("selectedWeaponIDUp") == true && sceneValues.getInt("selectedWeaponIDUp") == newSelectedWeaponID) {
                sceneValues.remove("selectedWeaponIDUp");
                BaseWeapon weapon = gdm.weapons.get(newSelectedWeaponID);
                gdm.money += weapon.info.price;
                gdm.weapons.remove(newSelectedWeaponID);
                WeaponSellScene scene = new WeaponSellScene(false);
                SceneManager.render(scene);
            } else {
                sceneValues.setInt("selectedWeaponIDUp", newSelectedWeaponID);
                WeaponSellScene scene = new WeaponSellScene(false);
                SceneManager.render(scene);
            }
        }
        if (msgID == MSG_SCENE_WEAPON_SELL__BACKUP_TOUCH) {
            sceneValues.remove("selectedWeaponIDUp");
            WeaponSellScene scene = new WeaponSellScene(false);
            SceneManager.render(scene);
        }
        if (msgID == MSG_SCENE_HUD__GAME_OVER_DILOG_YES) {
            SoundManager.getInstance().playSound("select01");
            BaseAction act = new GameOverDilogYesAction();
            act.doAction();
        }
        if (msgID == MSG_SCENE_HUD__GAME_OVER_DILOG_NO) {
            SoundManager.getInstance().playSound("back01");
            BaseAction act = new GameOverDilogNoAction();
            act.doAction();
        }
    }


}

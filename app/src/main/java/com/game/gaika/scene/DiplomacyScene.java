package com.game.gaika.scene;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.game.frame.scene.BaseLogicScene;
import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.action.BaseAction;
import com.game.gaika.action.GameOverDilogNoAction;
import com.game.gaika.action.GameOverDilogYesAction;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;
import com.game.frame.db.DatabaseManager;
import com.game.gaika.scene.dialg.CanBuyWeaponDialgScene;
import com.game.gaika.scene.sub.TopButtosSubScene;
import com.game.gaika.scene.sub.borderSubScene;
import com.game.frame.sprite.NormalSprite;
import com.game.frame.sprite.NumberSprite;
import com.game.frame.sprite.TextSprite;
import com.game.frame.texture.TexRegionManager;

import java.util.ArrayList;
import java.util.List;

import static com.game.gaika.data.ID.COUNTRY.USA;
import static com.game.gaika.data.ID.COUNTRY.USN;
import static com.game.gaika.data.ID.COUNTRY.RUSSIA;
import static com.game.gaika.data.ID.COUNTRY.GERMANY;
import static com.game.gaika.data.ID.COUNTRY.ENGLAND;
import static com.game.gaika.data.ID.COUNTRY.JAPAN;
import static com.game.gaika.data.ID.MSG_ID.*;

import  static com.game.gaika.data.ID.SCENE_ID.*;
import static com.game.frame.sound.SoundManager.playSound;

/**
 * Created by fangxg on 2015/7/1.
 */
public class DiplomacyScene extends BaseLogicScene implements IMessageHandler {
//    enum MSG_ID {MSG_DIPLOMACY_LEFT, MSG_DIPLOMACY_RIGHT, MSG_DIPLOMACY_CONFIRM, MSG_SELECT_COUNTRY_1,
//        MSG_SELECT_COUNTRY_2, MSG_SELECT_COUNTRY_3, MSG_SELECT_COUNTRY_4, MSG_SELECT_COUNTRY_5,
//        MSG_SELECT_COUNTRY_6, MSG_CAN_BUY_WEAPON_NEXT}

    private static final int liveCostInfo[][] = { /*{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, */{0, 10000, 20000, 40000, 80000, 100000, 100000, 100000, 0},
            {0, 9000, 18000, 36000, 77000, 100000, 100000, 100000, 0}, {0, 9000, 18000, 36000, 77000, 100000, 100000, 100000, 0},
            {0, 7500, 15500, 30000, 67000, 100000, 100000, 100000, 0}, {0, 7500, 15500, 30000, 67000, 100000, 100000, 100000, 0},
            {0, 7500, 15500, 30000, 67000, 100000, 100000, 100000, 0}};

    public DiplomacyScene(boolean isInit) {
        super(DIPLOMACY);
        GameDataManager gdm = GameDataManager.getInstance();

        NormalSprite backSprite = new NormalSprite(0.0f, 0.0f, "oper_bg5");
        addSprite(backSprite);

        TopButtosSubScene topButtosSubScene = new TopButtosSubScene(this);
        topButtosSubScene.setOffsetXY(0, 0);
        addChildScene(topButtosSubScene);


        SceneValueMap sceneValues =  getSceneValuesMap();

        if (isInit == true) {
            sceneValues.cleanAllValues();
            sceneValues.setEnum("selectedCounty", gdm.gameBeginCounty);
            sceneValues.setInt("selectedDiplomacyLv", gdm.getDiplomacy((ID.COUNTRY)sceneValues.getEnum("selectedCounty")));
            sceneValues.setObject("newWeaponInfoIds", new ArrayList<Integer>());
        }

        ID.COUNTRY selectedCountry = (ID.COUNTRY)sceneValues.getEnum("selectedCounty");
        int nowLv = gdm.getDiplomacy(selectedCountry);
        int endLv = sceneValues.getInt("selectedDiplomacyLv");



        NumberSprite moneySprite = new NumberSprite(326, 81, false, "font04", gdm.money, 0, 0, 1.0f);
        addSprite(moneySprite);



        NormalSprite leftSprite = new NormalSprite(53, 217, "oper_bt7", 2, new TouchMessage(MSG_SCENE_DIPLOMACY__LEFT, null, this));
        addSprite(leftSprite);


       // beginDipLv = gdm.getDiplomacy(selectedCounty);
        int diploacyAllMoney = 0;
        for (int i = nowLv + 1; i <= endLv; i++) {
            diploacyAllMoney += liveCostInfo[selectedCountry.ordinal()][i];
        }
        NumberSprite numberSprite2 = new NumberSprite(628, 81, false, "font04", diploacyAllMoney, 0, 0, 1.0f);
        addSprite(numberSprite2);

        TextSprite textSprite = new TextSprite(108, 159, true, selectedCountry.toDescribeString(), TexRegionManager.getInstance().getFont16());
        addSprite(textSprite);

        NumberSprite numberSprite3 = new NumberSprite(308, 160, true, "font04", endLv, 0, 0, 1.0f);
        addSprite(numberSprite3);

        NumberSprite numberSprite4 = new NumberSprite(503, 184, false, "font04", liveCostInfo[selectedCountry.ordinal()][endLv + 1], 0, 0, 1.0f);
        addSprite(numberSprite4);


        // -------------------------
        int money1 = 0;

        for (int i = 0; i <= endLv; i++) {
            money1 += liveCostInfo[selectedCountry.ordinal()][i];
        }

       // int money2 = money1 + diploacyAllMoney;

        int moneyall = 0;
        for (int i = 0; i < 8; i++) {
            moneyall += liveCostInfo[selectedCountry.ordinal()][i];
        }

//        NormalSprite barSprite1 = new NormalSprite(122, 219, "dp_bar1", 1);
//        barSprite1.getFlyweight().setScale(360.0f, 1.0f);
//        addSprite(barSprite1);



        NormalSprite barSprite2 = new NormalSprite(122, 219, "dp_bar1", 0);

        barSprite2.getFlyweight().setScaleXY((float) money1 / (float) moneyall * 360.0f, 1.0f);
        addSprite(barSprite2);


        // -------------------------


        NormalSprite rightSprite = new NormalSprite(484, 217, "oper_bt7", 3, new TouchMessage(MSG_SCENE_DIPLOMACY__RIGHT, null, this));
        addSprite(rightSprite);

        NormalSprite confirmSprite = new NormalSprite(593, 234, "oper_bt8", 1, new TouchMessage(MSG_SCENE_DIPLOMACY__CONFIRM, null, this));
        addSprite(confirmSprite);

        if (selectedCountry == USA) {

            NormalSprite countySprite = new NormalSprite(576, 342, "oper_bt5", selectedCountry.ordinal());
            addSprite(countySprite);

            NormalSprite markSprite = new NormalSprite(576, 342, "oper_bt6");
            addSprite(markSprite);
        } else {

            NormalSprite countySprite = new NormalSprite(576, 342, "invisible34_21", 0, new TouchMessage(MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_1, null, this));
            addSprite(countySprite);
        }

        if (selectedCountry == USN) {

            NormalSprite countySprite = new NormalSprite(511, 380, "oper_bt5", selectedCountry.ordinal());
            addSprite(countySprite);

            NormalSprite markSprite = new NormalSprite(511, 380, "oper_bt6");
            addSprite(markSprite);
        } else {

            NormalSprite countySprite = new NormalSprite(511, 380, "invisible34_21", 0, new TouchMessage(MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_2, null, this));
            addSprite(countySprite);
        }

        if (selectedCountry == RUSSIA) {

            NormalSprite countySprite = new NormalSprite(253, 305, "oper_bt5", selectedCountry.ordinal());
            addSprite(countySprite);

            NormalSprite markSprite = new NormalSprite(253, 305, "oper_bt6");
            addSprite(markSprite);
        } else {

            NormalSprite countySprite = new NormalSprite(253, 305, "invisible34_21", 0, new TouchMessage(MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_3, null, this));
            addSprite(countySprite);
        }

        if (selectedCountry == GERMANY) {

            NormalSprite countySprite = new NormalSprite(191, 334, "oper_bt5", selectedCountry.ordinal());
            addSprite(countySprite);

            NormalSprite markSprite = new NormalSprite(191, 334, "oper_bt6");
            addSprite(markSprite);
        } else {

            NormalSprite countySprite = new NormalSprite(191, 334, "invisible34_21", 0, new TouchMessage(MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_4, null, this));
            addSprite(countySprite);
        }
        if (selectedCountry == ENGLAND) {

            NormalSprite countySprite = new NormalSprite(135, 315, "oper_bt5", selectedCountry.ordinal());
            addSprite(countySprite);

            NormalSprite markSprite = new NormalSprite(135, 315, "oper_bt6");
            addSprite(markSprite);
        } else {

            NormalSprite countySprite = new NormalSprite(135, 315, "invisible34_21", 0, new TouchMessage(MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_5, null, this));
            addSprite(countySprite);
        }
        if (selectedCountry == JAPAN) {

            NormalSprite countySprite = new NormalSprite(374, 365, "oper_bt5", selectedCountry.ordinal());
            addSprite(countySprite);

            NormalSprite markSprite = new NormalSprite(374, 365, "oper_bt6");
            addSprite(markSprite);
        } else {

            NormalSprite countySprite = new NormalSprite(374, 365, "invisible34_21", 0, new TouchMessage(MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_6, null, this));
            addSprite(countySprite);
        }

        NormalSprite livelSprite1 = new NormalSprite(715, 443 + 0 * 21, "dp_mark2", gdm.getDiplomacy(USA));
        addSprite(livelSprite1);
        NormalSprite livelSprite2 = new NormalSprite(715, 443 + 1 * 21, "dp_mark2", gdm.getDiplomacy(USN));
        addSprite(livelSprite2);
        NormalSprite livelSprite3 = new NormalSprite(715, 443 + 2 * 21, "dp_mark2", gdm.getDiplomacy(RUSSIA));
        addSprite(livelSprite3);
        NormalSprite livelSprite4 = new NormalSprite(715, 443 + 3 * 21, "dp_mark2", gdm.getDiplomacy(GERMANY));
        addSprite(livelSprite4);
        NormalSprite livelSprite5 = new NormalSprite(715, 443 + 4 * 21, "dp_mark2", gdm.getDiplomacy(ENGLAND));
        addSprite(livelSprite5);
        NormalSprite livelSprite6 = new NormalSprite(715, 443 + 5 * 21, "dp_mark2", gdm.getDiplomacy(JAPAN));
        addSprite(livelSprite6);

        List<Integer> infoIds = (List<Integer>)(sceneValues.getObject("newWeaponInfoIds"));
        if(infoIds.size()>0){
            int infoID = infoIds.get(0);


            CanBuyWeaponDialgScene dlgScne = new CanBuyWeaponDialgScene (infoID, this);


            /*
			int infoId = idList.get(idList.size() - 1);
			idList.remove(idList.size() - 1);

			WeaponInfo info = gd.weapInfos.get(infoId);

			TiledSprite sprite1 = BaseRender.getShape((800 - 406) / 2, 50, "dialg5", 0);

			Text textSsprite1 = BaseRender.getText16(true, 50, 50, StringManager.getInstance().getString("S05001"));//)"已经可以购买兵器");
			sprite1.attachChild(textSsprite1);

			Text textSsprite2 = BaseRender.getText16(true, 50, 50 + 25, info.name + " 了。");
			sprite1.attachChild(textSsprite2);

			bkScne.attachChild(sprite1);

			TiledSprite infoSprite = BaseRender.getInfoCardSprite(info);
			infoSprite.setPosition((800 - 211) / 2, 200);
			bkScne.attachChild(infoSprite);

			bkScne.clearTouchAreas();
			BaseRender.addTouchArea(bkScne, backsprite, ID.BID_DIPLOMACY_SCEEN_NEXT, 0, false);*/

            setDialogSecne(dlgScne);
        }
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
        GameDataManager gdm = GameDataManager.getInstance();
        Enum msgID = pTouchMessage.getMessageID();
        SceneValueMap sceneValues =  getSceneValuesMap();
        if (msgID == MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_1) {
             playSound("select01");
            sceneValues.setEnum("selectedCounty", USA);
            sceneValues.setInt("selectedDiplomacyLv", gdm.getDiplomacy(USA));
            DiplomacyScene scene = new DiplomacyScene(false);
            SceneManager.render(scene);
        }
        if (msgID == MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_2) {
             playSound("select01");
            sceneValues.setEnum("selectedCounty", USN);
            sceneValues.setInt("selectedDiplomacyLv", gdm.getDiplomacy(USN));
            DiplomacyScene scene = new DiplomacyScene(false);
            SceneManager.render(scene);
        }
        if (msgID == MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_3) {
             playSound("select01");
            sceneValues.setEnum("selectedCounty", RUSSIA);
            sceneValues.setInt("selectedDiplomacyLv", gdm.getDiplomacy(RUSSIA));
            DiplomacyScene scene = new DiplomacyScene(false);
            SceneManager.render(scene);
        }
        if (msgID == MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_4) {
             playSound("select01");
            sceneValues.setEnum("selectedCounty", GERMANY);
            sceneValues.setInt("selectedDiplomacyLv", gdm.getDiplomacy(GERMANY));
            DiplomacyScene scene = new DiplomacyScene(false);
            SceneManager.render(scene);
        }
        if (msgID == MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_5) {
             playSound("select01");
            sceneValues.setEnum("selectedCounty", ENGLAND);
            sceneValues.setInt("selectedDiplomacyLv", gdm.getDiplomacy(ENGLAND));
            DiplomacyScene scene = new DiplomacyScene(false);
            SceneManager.render(scene);
        }
        if (msgID == MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_6) {
             playSound("select01");
            sceneValues.setEnum("selectedCounty", JAPAN);
            sceneValues.setInt("selectedDiplomacyLv", gdm.getDiplomacy(JAPAN));
            DiplomacyScene scene = new DiplomacyScene(false);
            SceneManager.render(scene);
        }

        if (msgID == MSG_SCENE_DIPLOMACY__LEFT) {
             playSound("select01");
            int lv = sceneValues.getInt("selectedDiplomacyLv");

            if (lv > gdm.getDiplomacy((ID.COUNTRY)sceneValues.getEnum("selectedCounty"))) {
                sceneValues.setInt("selectedDiplomacyLv", lv - 1);
                DiplomacyScene scene = new DiplomacyScene(false);
                SceneManager.render(scene);
            }
        }
        if (msgID == MSG_SCENE_DIPLOMACY__RIGHT) {
             playSound("select01");
            //int lv = sceneValues.getInt("selectedDiplomacyLv");
            ID.COUNTRY selectedCountry = (ID.COUNTRY)sceneValues.getEnum("selectedCounty");
            int nowLv = gdm.getDiplomacy(selectedCountry);
            int endLv = sceneValues.getInt("selectedDiplomacyLv") + 1;
            int diploacyAllMoney = 0;
            for (int i = nowLv + 1; i <= endLv; i++) {
                diploacyAllMoney += liveCostInfo[selectedCountry.ordinal()][i];
            }

            if (endLv < 7 && diploacyAllMoney <= gdm.money) {
                sceneValues.setInt("selectedDiplomacyLv", endLv);
                DiplomacyScene scene = new DiplomacyScene(false);
                SceneManager.render(scene);
            }
        }
        if (msgID == MSG_SCENE_DIPLOMACY__CONFIRM) {

            ID.COUNTRY selectedCountry = (ID.COUNTRY)sceneValues.getEnum("selectedCounty");
            int nowLv = gdm.getDiplomacy(selectedCountry);
            int endLv = sceneValues.getInt("selectedDiplomacyLv");

            int diploacyAllMoney = 0;
            for (int i = nowLv + 1; i <= endLv; i++) {
                diploacyAllMoney += liveCostInfo[selectedCountry.ordinal()][i];
            }

            if(diploacyAllMoney <= gdm.money){
                 playSound("select01");
                gdm.money -= diploacyAllMoney;

                gdm.setDiplomacy(selectedCountry, endLv);
                sceneValues.setInt("selectedDiplomacyLv", endLv);


                List<Integer> infoIDs = getNewInfoIDs(selectedCountry, nowLv, endLv);

                sceneValues.setObject("newWeaponInfoIds", infoIDs);

                DiplomacyScene scene = new DiplomacyScene(false);
                SceneManager.render(scene);
            }else{

            }
        }
        if(msgID == MSG_SCENE_DIPLOMACY__CAN_BUY_WEAPON_NEXT){
             playSound("scrl01");
            List<Integer> infoIDs = (List<Integer>)(sceneValues.getObject("newWeaponInfoIds"));
            infoIDs.remove(0);
            DiplomacyScene scene = new DiplomacyScene(false);
            SceneManager.render(scene);
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

    private List<Integer>  getNewInfoIDs(Enum pCountry ,int pNowLv, int pEndLv){

        List<Integer> ids = new ArrayList<>();
        SQLiteDatabase dbData = DatabaseManager.getSqLiteDatabase("data.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "data.db", null, SQLiteDatabase.OPEN_READWRITE);
        String strWhere = "(nationality = '" + pCountry.toString() + "' and " + " diplomacy > " + pNowLv + " and " + " diplomacy <= " + pEndLv + " and " + " id < 170)" ;
        if(pNowLv < 3 && pEndLv >= 3){
            strWhere = strWhere + " or id = 145 ";
        }
        Cursor cursor = dbData.query("WEAPON_INFO", new String[] { "id" }, strWhere, null, null, null, " diplomacy , id ");
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            ids.add(id);
            cursor.moveToNext();
        }

        cursor.close();
        dbData.close();
        return ids;
    }
}

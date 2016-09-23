package com.game.gaika.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.data.weapon.WeaponFactory;
import com.game.gaika.data.weapon.WeaponInfo;
import com.game.frame.db.DatabaseManager;
import com.game.gaika.scene.SceneManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.game.gaika.data.ID.COUNTRY.ENGLAND;
import static com.game.gaika.data.ID.COUNTRY.GERMANY;
import static com.game.gaika.data.ID.COUNTRY.JAPAN;
import static com.game.gaika.data.ID.COUNTRY.RUSSIA;
import static com.game.gaika.data.ID.COUNTRY.USA;
import static com.game.gaika.data.ID.COUNTRY.USN;
import static com.game.gaika.data.ID.SCENE_ID.BEGIN_MENU;
import static com.game.gaika.data.ID.TEAM_COLOR.BLUE;
import static com.game.gaika.data.ID.TEAM_COLOR.GREEN;
import static com.game.gaika.data.ID.TEAM_COLOR.RED;
import static com.game.gaika.data.ID.TEAM_COLOR.YELLOW;

/**
 * Created by fangxg on 2015/8/7.
 */
public class SaveManager {
    public static class ShowSaveNode {
        public int saveNo;
        public String dateTime;
        public int turnCount;
        public String chapterName;
        public ID.SCENE_ID preSceneId;

        public ShowSaveNode(int pSaveNo, String pDateTime, int pTurnCount, String pChapterName, ID.SCENE_ID pPreSceneID) {
            saveNo = pSaveNo;
            dateTime = pDateTime;
            turnCount = pTurnCount;
            chapterName = pChapterName;
            preSceneId = pPreSceneID;
        }
    }

    public static Map<Integer, ShowSaveNode> getSaves(int pPage) {

        GameDataManager gdm = GameDataManager.getInstance();
        Map<Integer, ShowSaveNode> saves = new HashMap();

        int saveNoBegin = (pPage - 1) * 20 + 1;
        int saveNoEnd = pPage * 20 + 1;


        for (int i = saveNoBegin; i < saveNoEnd; i++) {
            saves.put(i, new ShowSaveNode(0, "", 0, "", BEGIN_MENU));
        }

        SQLiteDatabase dbSave = DatabaseManager.getSqLiteDatabase("save.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "save.db", null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = dbSave.query("SAVE_MAIN ", new String[]{"no", "date", "current_turn", "chapter_no", "pre_scene_id"}, "no >= " + saveNoBegin + " and no < " + saveNoEnd, null, null, null, null);


        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {

            // yyyy/MM/dd hh:mm:ss
            int no = cursor.getInt(cursor.getColumnIndex("no"));
            String dateTime = cursor.getString(cursor.getColumnIndex("date"));
            int turnCount = cursor.getInt(cursor.getColumnIndex("current_turn"));
            int chapterNo = cursor.getInt(cursor.getColumnIndex("chapter_no"));
            String chapterName = gdm.chapters.get(chapterNo).cName;
            ID.SCENE_ID preSceneId = ID.SCENE_ID.valueOf(cursor.getString(cursor.getColumnIndex("pre_scene_id")));

            saves.put(no, new ShowSaveNode(no, dateTime, turnCount, chapterName, preSceneId));

            cursor.moveToNext();
        }

        cursor.close();
        dbSave.close();
        return saves;
    }

    public static void loadWeaponInfos() {

        GameDataManager gdm = GameDataManager.getInstance();
        SQLiteDatabase dbData = DatabaseManager.getSqLiteDatabase("data.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "data.db", null, SQLiteDatabase.OPEN_READWRITE);
        if (gdm.weapInfos == null) {

            gdm.weapInfos = new HashMap<Integer, WeaponInfo>();
        }

        gdm.weapInfos.clear();

        Cursor cursor = dbData.query("WEAPON_INFO", new String[]{"id", "name", "nationality", "type", "fuel", "ammo_air", "ammo_ground", "ammo_ship",
                "ammo_submarine", "transport", "continue_attack", "frist_attack", "inferior_move", "night_attack_low", "half_move_loss", "range_air",
                "range_ground", "range_ship", "range_submarie", "move", "supply", "price", "detail", "diplomacy", "capture", "tex_index", "can_choose_arms",
                "arms_type", "air_arms_info_id", "ground_arms_info_id", "can_VTAL", "attack_tank_up", "attack_helicopter_up", "move_finished_attack",
                "attack_neighbour", "group_attack", "aircraft_carrier_TAL"}, null, null, null, null, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            ID.COUNTRY nationality = ID.COUNTRY.valueOf(cursor.getString(cursor.getColumnIndex("nationality")));
            ID.WEAPON_TYPE type = ID.WEAPON_TYPE.valueOf(cursor.getString(cursor.getColumnIndex("type")));
            int supply = cursor.getInt(cursor.getColumnIndex("supply"));
            int fuel = cursor.getInt(cursor.getColumnIndex("fuel"));
            int transport = cursor.getInt(cursor.getColumnIndex("transport"));
            int diplomacy = cursor.getInt(cursor.getColumnIndex("diplomacy"));
            int ammoOnAir = cursor.getInt(cursor.getColumnIndex("ammo_air"));
            int ammoOnGround = cursor.getInt(cursor.getColumnIndex("ammo_ground"));
            int ammoOnShip = cursor.getInt(cursor.getColumnIndex("ammo_ship"));
            int ammoOnSubmarine = cursor.getInt(cursor.getColumnIndex("ammo_submarine"));
            int price = cursor.getInt(cursor.getColumnIndex("price"));
            int move = cursor.getInt(cursor.getColumnIndex("move"));

            int rangeOnAir = cursor.getInt(cursor.getColumnIndex("range_air"));
            int rangeOnGround = cursor.getInt(cursor.getColumnIndex("range_ground"));
            int rangeOnShip = cursor.getInt(cursor.getColumnIndex("range_ship"));
            int rangeOnSubmarine = cursor.getInt(cursor.getColumnIndex("range_submarie"));
            String detail = cursor.getString(cursor.getColumnIndex("detail"));
            int capture = cursor.getInt(cursor.getColumnIndex("capture"));
            int texIndex = cursor.getInt(cursor.getColumnIndex("tex_index"));

            boolean pContinueAttack = cursor.getInt(cursor.getColumnIndex("continue_attack")) == 1 ? true : false;
            boolean canChooseArms = cursor.getInt(cursor.getColumnIndex("can_choose_arms")) == 1 ? true : false;
            int armsType = cursor.getInt(cursor.getColumnIndex("arms_type"));

            int airArmsInfoId = cursor.getInt(cursor.getColumnIndex("air_arms_info_id"));
            int groundArmsInfoId = cursor.getInt(cursor.getColumnIndex("ground_arms_info_id"));

            boolean inferiorMove = cursor.getInt(cursor.getColumnIndex("inferior_move")) == 1 ? true : false;
            boolean fristAttack = cursor.getInt(cursor.getColumnIndex("frist_attack")) == 1 ? true : false;
            boolean halfMoveLoss = cursor.getInt(cursor.getColumnIndex("half_move_loss")) == 1 ? true : false;

            boolean attackTankUp = cursor.getInt(cursor.getColumnIndex("attack_tank_up")) == 1 ? true : false;
            boolean attackHelicopterUp = cursor.getInt(cursor.getColumnIndex("attack_helicopter_up")) == 1 ? true : false;
            boolean nightAttackLow = cursor.getInt(cursor.getColumnIndex("night_attack_low")) == 1 ? true : false;

            boolean canVTAL = cursor.getInt(cursor.getColumnIndex("can_VTAL")) == 1 ? true : false;
            boolean moveFinishedAttack = cursor.getInt(cursor.getColumnIndex("move_finished_attack")) == 1 ? true : false;
            boolean attackNeighbour = cursor.getInt(cursor.getColumnIndex("attack_neighbour")) == 1 ? true : false;
            boolean groupAttack = cursor.getInt(cursor.getColumnIndex("group_attack")) == 1 ? true : false;
            boolean aircraftCarrierTAL = cursor.getInt(cursor.getColumnIndex("aircraft_carrier_TAL")) == 1 ? true : false;

            gdm.weapInfos.put(id, new WeaponInfo(id, name, nationality, type, supply, fuel, transport, diplomacy, ammoOnAir, ammoOnGround, ammoOnShip,
                    ammoOnSubmarine, price, move, rangeOnAir, rangeOnGround, rangeOnShip, rangeOnSubmarine, detail, capture, pContinueAttack, texIndex,
                    canChooseArms, armsType, airArmsInfoId, groundArmsInfoId, canVTAL, inferiorMove, fristAttack, halfMoveLoss, attackTankUp,
                    attackHelicopterUp, nightAttackLow, moveFinishedAttack, attackNeighbour, groupAttack, aircraftCarrierTAL));

            cursor.moveToNext();
        }

        cursor.close();

        cursor = dbData.query("WEAPON_LV_INFO", new String[]{"info_id", "lv", "attack_air", "attack_ground", "attack_ship", "attack_submarine", "defense_air",
                "defense_ground", "defense_ship", "defense_submarine"}, null, null, null, null, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int infoId = cursor.getInt(cursor.getColumnIndex("info_id"));
            int lv = cursor.getInt(cursor.getColumnIndex("lv"));
            int attack_air = cursor.getInt(cursor.getColumnIndex("attack_air"));
            int attack_ground = cursor.getInt(cursor.getColumnIndex("attack_ground"));
            int attack_ship = cursor.getInt(cursor.getColumnIndex("attack_ship"));
            int attack_submarine = cursor.getInt(cursor.getColumnIndex("attack_submarine"));
            int defense_air = cursor.getInt(cursor.getColumnIndex("defense_air"));
            int defense_ground = cursor.getInt(cursor.getColumnIndex("defense_ground"));
            int defense_ship = cursor.getInt(cursor.getColumnIndex("defense_ship"));
            int defense_submarine = cursor.getInt(cursor.getColumnIndex("defense_submarine"));

            WeaponInfo info = gdm.weapInfos.get(infoId);
            if (info != null) {
                if (info.lvDataFromType == null) {
                    info.lvDataFromType = new HashMap<Integer, WeaponInfo.WeaponInfoFromLv>();
                }
                WeaponInfo.WeaponInfoFromLv infoFromLv = info.new WeaponInfoFromLv(lv,
                        attack_air,
                        attack_ground,
                        attack_ship,
                        attack_submarine,
                        defense_air,
                        defense_ground,
                        defense_ship,
                        defense_submarine);
                info.lvDataFromType.put(lv, infoFromLv);
            }
            cursor.moveToNext();
        }
        cursor.close();
        dbData.close();
    }

    public static void reInitChapters() {
        SQLiteDatabase dbData = DatabaseManager.getSqLiteDatabase("data.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "data.db", null, SQLiteDatabase.OPEN_READWRITE);
        GameDataManager gdm = GameDataManager.getInstance();

        Cursor cursor;

        // ///////////////////////////////////////
        cursor = dbData.query("CHAPTER_MAIN", new String[]{"chapter_no", "map_name", "cname", "max_turn", "area", "point_x", "point_y", "point_x2", "point_y2",
                "victory_term", "get_weapon_ids", "add_money", "next_chapter_nos", "side_1s", "side_2s", "battle_info"}, null, null, null, null, null);

        cursor.moveToFirst();

        if (gdm.chapters == null) {
            gdm.chapters = new HashMap<Integer, GameChapter>();
        }
        gdm.chapters.clear();

        while (cursor.isAfterLast() == false) {
            int no = cursor.getInt(cursor.getColumnIndex("chapter_no"));
            String mapName = cursor.getString(cursor.getColumnIndex("map_name"));
            String cName = cursor.getString(cursor.getColumnIndex("cname"));
            int maxTurn = cursor.getInt(cursor.getColumnIndex("max_turn"));
            ID.COUNTRY area = ID.COUNTRY.valueOf(cursor.getString(cursor.getColumnIndex("area")));
            int pointX = cursor.getInt(cursor.getColumnIndex("point_x"));
            int pointY = cursor.getInt(cursor.getColumnIndex("point_y"));
            int pointX2 = cursor.getInt(cursor.getColumnIndex("point_x2"));
            int pointY2 = cursor.getInt(cursor.getColumnIndex("point_y2"));
            String victoryTerm = cursor.getString(cursor.getColumnIndex("victory_term"));
            String getWeaponIds = cursor.getString(cursor.getColumnIndex("get_weapon_ids"));

            int addMoney = cursor.getInt(cursor.getColumnIndex("add_money"));

            String nextChapterNos = cursor.getString(cursor.getColumnIndex("next_chapter_nos"));
            String side1s = cursor.getString(cursor.getColumnIndex("side_1s"));
            String side2s = cursor.getString(cursor.getColumnIndex("side_2s"));
            String battleInfo = cursor.getString(cursor.getColumnIndex("battle_info"));

            gdm.chapters.put(no, new GameChapter(no, mapName, cName, maxTurn, area, pointX, pointY, pointX2, pointY2, false, victoryTerm, getWeaponIds, addMoney,
                    nextChapterNos, side1s, side2s, battleInfo));
            cursor.moveToNext();
        }
        cursor.close();
        dbData.close();
    }

    /**
     * 支持以下的SceneID
     * 正常游戏：SELECT_TARGET, TEAM_BUILD, WEAPON_BUY,
     * WEAPON_REPAIR, DIPLOMACY,  WEAPON_SELL,
     * BATTLEFIELD,
     * 调试游戏：SCENE_ID_*
     */
    public static void save(int pSaveNo) {

        GameDataManager gdm = GameDataManager.getInstance();
        ID.SCENE_ID pPerSceneId;
        if (pSaveNo <= 20 * 99) {
            pPerSceneId = SceneManager.getTopBaseLogicScene().preSceneID;

            String oldPath = GameSetup.sdcartdPahtSave + "xiao.png";
            String newPath = GameSetup.sdcartdPahtSave + pSaveNo + ".png";
            copyFile(oldPath, newPath);

        } else {
            pPerSceneId = SceneManager.getTopBaseLogicScene().getSceneId();
        }


        SQLiteDatabase dbSave = DatabaseManager.getSqLiteDatabase("save.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "save.db", null, SQLiteDatabase.OPEN_READWRITE);
        dbSave.beginTransaction();

        // table SAVE_MAIN
        dbSave.delete("SAVE_MAIN", "no = " + pSaveNo, null);

        ContentValues v1 = new ContentValues();
        v1.put("no", pSaveNo);
        v1.put("chapter_no", gdm.getCurrentChapter() == null ? -1 : gdm.getCurrentChapter().no);
        v1.put("money", gdm.money);
        v1.put("date", GameTimer.getDateTimeNow());
        v1.put("country_1_diplomacy", gdm.getDiplomacy(USA));
        v1.put("country_2_diplomacy", gdm.getDiplomacy(USN));
        v1.put("country_3_diplomacy", gdm.getDiplomacy(RUSSIA));
        v1.put("country_4_diplomacy", gdm.getDiplomacy(GERMANY));
        v1.put("country_5_diplomacy", gdm.getDiplomacy(ENGLAND));
        v1.put("country_6_diplomacy", gdm.getDiplomacy(JAPAN));
//        v1.put("game_status", StatusMachine.getInstance().status);
        v1.put("game_status", 0);

        v1.put("blue_supply", gdm.getSupply(ID.TEAM_COLOR.BLUE));
        v1.put("red_supply", gdm.getSupply(ID.TEAM_COLOR.RED));
        v1.put("yellow_supply", gdm.getSupply(ID.TEAM_COLOR.YELLOW));
        v1.put("green_supply", gdm.getSupply(ID.TEAM_COLOR.GREEN));
        v1.put("score_count", gdm.scoreCount);
        v1.put("game_diff", gdm.gameBeginDiff.toString());
        v1.put("game_begin_local", gdm.gameBeginLocal.toString());
        v1.put("game_begin_county", gdm.gameBeginCounty.toString());
        v1.put("pre_scene_id", pPerSceneId.toString());
        v1.put("current_turn", gdm.gameTimer.getCurrentTurn());

        String ids = "";
        String turns = "";
        String myLosss = "";
        String enemyLosss = "";
        String scores = "";
        for (GameChapter chapter : gdm.chapters.values()) {

            if (chapter.finished == true) {

                ids = ids + chapter.no + "|";
               /* turns = turns + chapter.endTurnCount + "|";
                myLosss = myLosss + chapter.myLossCount + "|";
                enemyLosss = enemyLosss + chapter.myLossCount + "|";
                scores = scores + chapter.score + "|";*/
            }
        }
        v1.put("finished_chapter_ids", ids);
        v1.put("finished_chapter_turns", turns);
        v1.put("my_loss_counts", myLosss);
        v1.put("enemy_loss_counts", enemyLosss);
        v1.put("scores", scores);

        dbSave.insert("SAVE_MAIN", null, v1);


        // table CITY_NODE_SAVE
        dbSave.delete("CITY_NODE_SAVE", "save_no = " + pSaveNo, null);

//        if (pPerSceneId == BATTLEFIELD) {

        for (City cityNode : gdm.getCurrentChapter().getGameMap().citys.values()) {

            ContentValues v2 = new ContentValues();
            v2.put("save_no", pSaveNo);
            v2.put("x", cityNode.x);
            v2.put("y", cityNode.y);
//            v2.put("type", cityNode.type.ordinal());
//            v2.put("is_base", cityNode.isBase);
            v2.put("color", cityNode.teamColor.toString());
            v2.put("capture", cityNode.capture);
            v2.put("country", cityNode.country.toString());

//            v2.put("name", cityNode.name);
//            v2.put("is_point", cityNode.isPoint);
            v2.put("get_weapon_info_id", cityNode.getWeaponInfoId);
            v2.put("get_money", cityNode.getMoney);
            v2.put("get_supply", cityNode.getSupply);
//            v2.put("get_msg_id", cityNode.getMsgId);

            dbSave.insert("CITY_NODE_SAVE", null, v2);
        }
//        }


        // table WEAPON_NODE_SAVE
        dbSave.delete("WEAPON_NODE_SAVE", "save_no = " + pSaveNo, null);

        for (BaseWeapon weapNode : gdm.weapons.values()) {

            ContentValues v3 = new ContentValues();
            v3.put("save_no", pSaveNo);
            v3.put("id", weapNode.id);
            v3.put("info_id", weapNode.info.id);
            v3.put("exp", weapNode.exp);
            v3.put("life", weapNode.life);
            v3.put("map_x", weapNode.x);
            v3.put("map_y", weapNode.y);
            v3.put("fuel", weapNode.fuel);
            v3.put("move", weapNode.move);
            v3.put("ammo_air", weapNode.ammoOnAir);
            v3.put("ammo_ground", weapNode.ammoOnGround);
            v3.put("ammo_ship", weapNode.ammoOnShip);
            v3.put("ammo_submarine", weapNode.ammoOnSubmarine);
            v3.put("team_out", weapNode.teamOut);
            v3.put("set_out", weapNode.setOut);
            v3.put("team_color", weapNode.teamColor.toString());
            v3.put("move_ending", weapNode.moveEnding);

            String passengerIds = "";
            for (BaseWeapon passenger : weapNode.getPassengers()) {

                passengerIds = passengerIds + passenger.id + "|";
            }
            v3.put("passenger_id_list", passengerIds);

            dbSave.insert("WEAPON_NODE_SAVE", null, v3);
        }

        dbSave.setTransactionSuccessful();
        dbSave.endTransaction();

        dbSave.close();
        return;
    }


    public static void load(int pSaveNo) {
        GameDataManager gdm = GameDataManager.getInstance();
        SQLiteDatabase dbSave = DatabaseManager.getSqLiteDatabase("save.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "save.db", null, SQLiteDatabase.OPEN_READWRITE);

        // table SAVE_MAIN
        Cursor cursor = dbSave.query("SAVE_MAIN", new String[]{"no", "chapter_no", "money", "country_1_diplomacy", "country_2_diplomacy", "country_3_diplomacy",
                "country_4_diplomacy", "country_5_diplomacy", "country_6_diplomacy", "game_status", "blue_supply", "red_supply", //"current_turn",
                "pre_scene_id", "game_diff", "game_begin_local", "game_begin_county", "current_turn", "finished_chapter_ids", "finished_chapter_turns",
                "score_count", "yellow_supply", "green_supply", "my_loss_counts", "enemy_loss_counts", "scores"}, "no = " + pSaveNo, null, null, null, null);

        cursor.moveToFirst();
        int currentChapterNo = cursor.getInt(cursor.getColumnIndex("chapter_no"));
        gdm.setCurrentChapter(currentChapterNo);
        gdm.money = cursor.getInt(cursor.getColumnIndex("money"));
        gdm.setDiplomacy(USA, cursor.getInt(cursor.getColumnIndex("country_1_diplomacy")));
        gdm.setDiplomacy(USN, cursor.getInt(cursor.getColumnIndex("country_2_diplomacy")));
        gdm.setDiplomacy(RUSSIA, cursor.getInt(cursor.getColumnIndex("country_3_diplomacy")));
        gdm.setDiplomacy(GERMANY, cursor.getInt(cursor.getColumnIndex("country_4_diplomacy")));
        gdm.setDiplomacy(ENGLAND, cursor.getInt(cursor.getColumnIndex("country_5_diplomacy")));
        gdm.setDiplomacy(JAPAN, cursor.getInt(cursor.getColumnIndex("country_6_diplomacy")));
        gdm.setSupply(BLUE, cursor.getInt(cursor.getColumnIndex("blue_supply")));
        gdm.setSupply(RED, cursor.getInt(cursor.getColumnIndex("red_supply")));
//        gdm.gameTimer.setCurrentTurn(cursor.getInt(cursor.getColumnIndex("current_turn")));

        gdm.gameTimer = new GameTimer(gdm.getCurrentChapter().maxTurn, cursor.getInt(cursor.getColumnIndex("current_turn")));

        gdm.setSupply(YELLOW, cursor.getInt(cursor.getColumnIndex("yellow_supply")));
        gdm.setSupply(GREEN, cursor.getInt(cursor.getColumnIndex("green_supply")));
        gdm.scoreCount = cursor.getInt(cursor.getColumnIndex("score_count"));

        for (GameChapter chapter : gdm.chapters.values()) {
            chapter.finished = false;
        }
        String ids = cursor.getString(cursor.getColumnIndex("finished_chapter_ids"));
        String turns = cursor.getString(cursor.getColumnIndex("finished_chapter_turns"));
        String myLosss = cursor.getString(cursor.getColumnIndex("my_loss_counts"));
        String enemyLosss = cursor.getString(cursor.getColumnIndex("enemy_loss_counts"));
        String scores = cursor.getString(cursor.getColumnIndex("scores"));
        String[] ss = ids.split("\\|");
        String[] is = turns.split("\\|");
        String[] ms = myLosss.split("\\|");
        String[] es = enemyLosss.split("\\|");
        String[] ps = scores.split("\\|");
        int i = 0;
        for (String s : ss) {
            if (s.equals("") == false) {
                int id = Integer.valueOf(s);
                gdm.chapters.get(id).finished = true;
               /* gdm.chapters.get(id).endTurnCount = Integer.valueOf(is[i]);
                gdm.chapters.get(id).myLossCount = Integer.valueOf(ms[i]);
                gdm.chapters.get(id).enemyLossCount = Integer.valueOf(es[i]);
                gdm.chapters.get(id).score = Integer.valueOf(ps[i]);*/
            }
            i++;
        }

        gdm.gameBeginDiff = ID.GAME_DIFF.valueOf(cursor.getString(cursor.getColumnIndex("game_diff")));
        gdm.gameBeginLocal = ID.COUNTRY.valueOf(cursor.getString(cursor.getColumnIndex("game_begin_local")));
        gdm.gameBeginCounty = ID.COUNTRY.valueOf(cursor.getString(cursor.getColumnIndex("game_begin_county")));


        cursor.close();

        // table CITY_NODE_SAVE

        gdm.getCurrentChapter().getGameMap().resetCitys();


        /*Cursor cursor = dbSave.query("CITY_NODE_SAVE", new String[] { "save_no", "x", "y", "type", "is_base", "color", "capture", "country", "name", "is_point",
                "get_weapon_info_id","get_money","get_supply","get_msg_id"  }, "save_no = " + pSaveNo, null, null, null, null);

		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			int x = cursor.getInt(cursor.getColumnIndex("x"));
			int y = cursor.getInt(cursor.getColumnIndex("y"));
			int type = cursor.getInt(cursor.getColumnIndex("type"));
			boolean isBase = cursor.getInt(cursor.getColumnIndex("is_base")) == 1 ? true : false;
			int teamColor = cursor.getInt(cursor.getColumnIndex("color"));
			int capture = cursor.getInt(cursor.getColumnIndex("capture"));
			int country = cursor.getInt(cursor.getColumnIndex("country"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			boolean isPoint = cursor.getInt(cursor.getColumnIndex("is_point")) == 1 ? true : false;
			int getWeaponInfoId = cursor.getInt(cursor.getColumnIndex("get_weapon_info_id"));
			int getMoney = cursor.getInt(cursor.getColumnIndex("get_money"));
			int getSupply = cursor.getInt(cursor.getColumnIndex("get_supply"));
			String getMsgId = cursor.getString(cursor.getColumnIndex("get_msg_id"));
			map.citys.add(new CityNode(x, y, type, isBase, teamColor, capture, country, name, isPoint, getWeaponInfoId, getMoney, getSupply, getMsgId));

			cursor.moveToNext();
		}*/
        cursor = dbSave.query("CITY_NODE_SAVE", new String[]{"save_no", "x", "y", "type", "is_base", "color", "capture", "country", "name", "is_point",
                "get_weapon_info_id", "get_money", "get_supply", "get_msg_id"}, "save_no = " + pSaveNo, null, null, null, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int x = cursor.getInt(cursor.getColumnIndex("x"));
            int y = cursor.getInt(cursor.getColumnIndex("y"));
            ID.TEAM_COLOR teamColor = ID.TEAM_COLOR.valueOf(cursor.getString(cursor.getColumnIndex("color")));
            int capture = cursor.getInt(cursor.getColumnIndex("capture"));
            ID.COUNTRY country = ID.COUNTRY.valueOf(cursor.getString(cursor.getColumnIndex("country")));
            int getWeaponInfoId = cursor.getInt(cursor.getColumnIndex("get_weapon_info_id"));
            int getMoney = cursor.getInt(cursor.getColumnIndex("get_money"));
            int getSupply = cursor.getInt(cursor.getColumnIndex("get_supply"));

            City city = gdm.getCurrentChapter().getGameMap().citys.get(x * 100 + y);
            city.teamColor = teamColor;
            city.capture = capture;
            city.country = country;
            city.getWeaponInfoId = getWeaponInfoId;
            city.getMoney = getMoney;
            city.getSupply = getSupply;

            cursor.moveToNext();
        }
        cursor.close();

        //table WEAPON_NODE_SAVE
        gdm.weapons = new HashMap<>();

        cursor = dbSave.query("WEAPON_NODE_SAVE", new String[]{"save_no", "id", "info_id", "exp", "life", "map_x", "map_y", "fuel", "move", "ammo_air",
                        "ammo_ground", "ammo_ship", "ammo_submarine", "team_out", "set_out", "team_color", "move_ending", "passenger_id_list"},
                "save_no = " + pSaveNo, null, null, null, " id ");

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int infoId = cursor.getInt(cursor.getColumnIndex("info_id"));
            int exp = cursor.getInt(cursor.getColumnIndex("exp"));

            int x = cursor.getInt(cursor.getColumnIndex("map_x"));
            int y = cursor.getInt(cursor.getColumnIndex("map_y"));

            int fuel = cursor.getInt(cursor.getColumnIndex("fuel"));
            boolean teamOut = cursor.getInt(cursor.getColumnIndex("team_out")) == 1 ? true : false;
            boolean setOut = cursor.getInt(cursor.getColumnIndex("set_out")) == 1 ? true : false;
            ID.TEAM_COLOR teamColor = ID.TEAM_COLOR.valueOf(cursor.getString(cursor.getColumnIndex("team_color")));
            int life = cursor.getInt(cursor.getColumnIndex("life"));
            boolean moveEnding = cursor.getInt(cursor.getColumnIndex("move_ending")) == 1 ? true : false;

            int ammoOnAir = cursor.getInt(cursor.getColumnIndex("ammo_air"));
            int ammoOnGround = cursor.getInt(cursor.getColumnIndex("ammo_ground"));
            int ammoOnShip = cursor.getInt(cursor.getColumnIndex("ammo_ship"));
            int ammoOnSubmarine = cursor.getInt(cursor.getColumnIndex("ammo_submarine"));

            int move = cursor.getInt(cursor.getColumnIndex("move"));

            gdm.weapons.put(id, WeaponFactory.getInstance().newWeapon(id, infoId, x, y, teamOut, setOut, teamColor, life,
                    moveEnding, exp, fuel, ammoOnAir, ammoOnGround, ammoOnShip, ammoOnSubmarine, move));
            cursor.moveToNext();
        }

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            String passengerIdlist = cursor.getString(cursor.getColumnIndex("passenger_id_list"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            if (passengerIdlist != null) {
                BaseWeapon transporter = gdm.weapons.get(id);
                String passengerIDs[] = passengerIdlist.split("\\|");
                for (String passengerID : passengerIDs) {
                    if (passengerID.length() > 0) {
                        BaseWeapon passenger = gdm.weapons.get(Integer.valueOf(passengerID));
                        transporter.getPassengers().add(passenger);
                        passenger.transporter = transporter;
                    }
                }
            }
            cursor.moveToNext();
        }
        cursor.close();
        dbSave.close();
    }



    public static void reLoadAiWeapon() {
        GameDataManager gdm = GameDataManager.getInstance();

        Map<Integer, BaseWeapon> newWeapons = new HashMap<>();

        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.addTeamColor(BLUE);
        List<BaseWeapon> blueWeapons = filter.getWeapons();

        for (BaseWeapon blueWeapon : blueWeapons) {
            blueWeapon.doEnterBattlefield();
            newWeapons.put(blueWeapon.id, blueWeapon);
        }

        gdm.weapons = newWeapons;

        SQLiteDatabase dbData = DatabaseManager.getSqLiteDatabase("data.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "data.db", null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = dbData.query("WEAPON_NODE_CHAPTER",
                new String[]{"chapter_no", "id", "info_id", "exp", "team_color"}, "chapter_no = " + gdm.getCurrentChapter().no,
                null, null, null, " id ");

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int id = gdm.getNewWeaponId();
            int infoId = cursor.getInt(cursor.getColumnIndex("info_id"));
            int exp = cursor.getInt(cursor.getColumnIndex("exp"));
            ID.TEAM_COLOR teamColor = ID.TEAM_COLOR.valueOf(cursor.getString(cursor.getColumnIndex("team_color")));

            gdm.weapons.put(id, WeaponFactory.getInstance().newWeapon(id, 0, 0, exp, infoId, true, false, teamColor, true));

            cursor.moveToNext();
        }
        cursor.close();
        dbData.close();

    }

    private static void loadBlueWeapons() {
        GameDataManager gdm = GameDataManager.getInstance();

        SQLiteDatabase dbData = DatabaseManager.getSqLiteDatabase("data.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "data.db", null, SQLiteDatabase.OPEN_READWRITE);
        // BLUE weapon       this.gameBeginCounty;
        Cursor cursor = dbData.query("WEAPON_NODE_COUNTRY", new String[]{"country", "id", "info_id"}, "country = " + (gdm.gameBeginCounty.ordinal() + 1), null, null, null, " id ");

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            int id = gdm.getNewWeaponId();
            int infoId = cursor.getInt(cursor.getColumnIndex("info_id"));

            gdm.weapons.put(id, WeaponFactory.getInstance().newWeapon(id, 0, 0, 0, infoId, true, false, ID.TEAM_COLOR.BLUE, true));

            cursor.moveToNext();
        }

        cursor.close();

        //RED weapon
        reLoadAiWeapon();
        dbData.close();

    }

    public static int  getNewGameChapterNo() {
        GameDataManager gdm = GameDataManager.getInstance();
        int beginChapterNo;
        if (gdm.gameBeginLocal == USN) {
            beginChapterNo = 601;
        } else if (gdm.gameBeginLocal == GERMANY) {
            beginChapterNo = 501;
        } else if (gdm.gameBeginLocal == ENGLAND) {
            beginChapterNo = 401;
        } else if (gdm.gameBeginLocal == USA) {
            beginChapterNo = 301;
        } else if (gdm.gameBeginLocal == JAPAN) {
            beginChapterNo = 201;
        } else if (gdm.gameBeginLocal == RUSSIA) {
            beginChapterNo = 101;
        } else {
            throw new IllegalArgumentException("gameBeginLocal: " + gdm.gameBeginLocal);
        }

        for (GameChapter chapter : gdm.chapters.values()) {
            chapter.reset();
        }
        return beginChapterNo;
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：/data/data/com.gaika/databases/xiao.png
     * @param newPath String 复制后路径 如：/data/data/com.gaika/databases/21.png
     * @return boolean
     */
    private static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            // File oldfile = new File(oldPath);
            // if (oldfile.exists()) { //文件存在时
            InputStream inStream = new FileInputStream(oldPath);// /new
            // FileInputStream(oldPath);
            // //读入原文件
            FileOutputStream fs = new FileOutputStream(newPath);
            byte[] buffer = new byte[1444];
            // int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread; // 字节数 文件大小
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
            fs.close();
            // }
        } catch (Exception e) {
            Log.d("Gaika", "复制单个文件操作出错");
            e.printStackTrace();

        }

    }



    public static void enterChapter(int pChapterNo){
        GameDataManager gdm =  GameDataManager.getInstance();
        gdm.setCurrentChapter( pChapterNo);

        int finishedCount = 0;
        for(GameChapter chapter:gdm.chapters.values()){
            if(chapter.finished == true){
                finishedCount ++;
            }
        }
        if(finishedCount == 0){//第一关
            gdm.weapons.clear();
            loadBlueWeapons();
        }else {//第二关以后

        }
        reLoadAiWeapon();

        if(finishedCount ==0){//第一关
            reInitChapters();
        }


        GameMap currentGameMap = gdm.getCurrentChapter().getGameMap();
        currentGameMap.resetCitys();

        if(finishedCount ==0){//第一关
            gdm.setDiplomacy(ID.COUNTRY.USA, 0);
            gdm.setDiplomacy(ID.COUNTRY.USN, 0);
            gdm.setDiplomacy(ID.COUNTRY.RUSSIA, 0);
            gdm.setDiplomacy(ID.COUNTRY.GERMANY, 0);
            gdm.setDiplomacy(ID.COUNTRY.ENGLAND, 0);
            gdm.setDiplomacy(ID.COUNTRY.JAPAN, 0);
            gdm.setDiplomacy(gdm.gameBeginCounty, 1);
        }

        gdm.setSupply(ID.TEAM_COLOR.BLUE, 500);
        gdm.setSupply(ID.TEAM_COLOR.RED, 0);
        gdm.setSupply(ID.TEAM_COLOR.YELLOW, 0);
        gdm.setSupply(ID.TEAM_COLOR.GREEN, 0);

        if(finishedCount == 0){//第一关
            gdm.money  = 0;
            gdm.scoreCount = 0;
        }

        gdm.gameTimer = new GameTimer(gdm.getCurrentChapter().maxTurn);
        gdm.currentAiTeamColor = ID.TEAM_COLOR.BLUE;
    }
    public static void loadConfig() {

        SQLiteDatabase dbSave = DatabaseManager.getSqLiteDatabase("save.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "save.db", null, SQLiteDatabase.OPEN_READWRITE);

        Cursor cursor = dbSave.query("GAME_CONFIG", new String[] { "zoom", "sound", "save_page", "volume" }, null, null, null, null, null);

        cursor.moveToFirst();

        if (cursor.isAfterLast() == false) {

            GameSetup.settingBattlefieldZoom = ((float)(cursor.getInt(cursor.getColumnIndex("zoom")))) / 10.0f;
            GameSetup.settingOpenSound = cursor.getInt(cursor.getColumnIndex("sound")) == 1 ? true : false;
            GameSetup.settingSaveDatePage = cursor.getInt(cursor.getColumnIndex("save_page"));
            GameSetup.settingSoundVolume = ((float)(cursor.getInt(cursor.getColumnIndex("volume")))) / 100.0f;
        } else {

            ContentValues v1 = new ContentValues();
            v1.put("zoom", 10);//(int) (GameSetup.ZOOM * 10));
            v1.put("sound", 1);
            v1.put("save_page", 1);
            v1.put("volume", 100);
            dbSave.insert("GAME_CONFIG", null, v1);
        }
        cursor.close();
        dbSave.close();
    }

    public static void saveConfig() {

        SQLiteDatabase dbSave = DatabaseManager.getSqLiteDatabase("save.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "save.db", null, SQLiteDatabase.OPEN_READWRITE);


        dbSave.delete("GAME_CONFIG", null, null);

        ContentValues v1 = new ContentValues();
        v1.put("zoom", (int)(GameSetup.settingBattlefieldZoom * 10));
        v1.put("sound", GameSetup.settingOpenSound == true ? 1 : 0);
        v1.put("save_page", GameSetup.settingSaveDatePage);
        v1.put("volume", (int)(GameSetup.settingSoundVolume * 100));
        dbSave.insert("GAME_CONFIG", null, v1);

        dbSave.close();
    }
}

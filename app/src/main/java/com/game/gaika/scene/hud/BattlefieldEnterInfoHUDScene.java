package com.game.gaika.scene.hud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.ID.*;
import com.game.gaika.db.DatabaseManager;
import com.game.gaika.sprite.NormalSprite;
import com.game.gaika.sprite.TextSprite;
import com.game.gaika.texture.TexRegionManager;

import org.andengine.util.color.Color;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fangxg on 2015/8/18.
 */
public class BattlefieldEnterInfoHUDScene extends HUDScene {
    private IMessageHandler messageHandler;
    private static final int LINE = 20;
    private static final float OX = 100.0f;
    private int newSelectedChapterID;

    public BattlefieldEnterInfoHUDScene(float pWidth, float pHeight, IMessageHandler pMessageHandler, int pNewSelectedChapterID) {
        super(ID.SCENE_ID.BATTLEFIELD_ENTER_INFO_HUD, pWidth, pHeight);
        messageHandler = pMessageHandler;
        newSelectedChapterID = pNewSelectedChapterID;
    }

    @Override
    public boolean isBacegroundEnabled() {
        return false;
    }

    @Override
    public void buildScene() {
        float unZoom = 1.0f / GameSetup.settingBattlefieldZoom;
        GameDataManager gdm = GameDataManager.getInstance();

        NormalSprite printSprite = new NormalSprite(0.0f, 0.0f, "addprint5", 0, null, width / 800.0f, height / 440.0f, 0.5f);
        addSprite(printSprite);


        NormalSprite wn4Sprite = new NormalSprite(width - 300 * unZoom, 10 * unZoom, "adva_wn4", 0);
        wn4Sprite.setScale(unZoom);
        addSprite(wn4Sprite);

        NormalSprite bt1Sprite = new NormalSprite(width - 279 * unZoom, 23 * unZoom, "adva_bt2", 2, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD_ENTER_INFO_HUD__BUTTON1, null, messageHandler));
        bt1Sprite.setScale(unZoom);
        addSprite(bt1Sprite);

        NormalSprite bt2Sprite = new NormalSprite(width - 141 * unZoom, 23 * unZoom, "adva_bt2", 3, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD_ENTER_INFO_HUD__BUTTON2, null, messageHandler));
        bt2Sprite.setScale(unZoom);
        addSprite(bt2Sprite);


        NormalSprite wn1Sprite = new NormalSprite(width - 460 * unZoom, 70 * unZoom, "adva_wn1", 0);
        wn1Sprite.setScale(unZoom);
        addSprite(wn1Sprite);

        for (int i = 0; i < LINE; i++) {
            NormalSprite wn2_1Sprite = new NormalSprite(width - 460 * unZoom, (70 + 6 + 18 * i) * unZoom, "adva_wn2", 0);
            wn2_1Sprite.setScale(unZoom);
            addSprite(wn2_1Sprite);

            NormalSprite wn2_2Sprite = new NormalSprite(width - 12 * unZoom, (70 + 6 + 18 * i) * unZoom, "adva_wn2", 1);
            wn2_2Sprite.setScale(unZoom);
            addSprite(wn2_2Sprite);
        }

        String[] ss = gdm.chapters.get(newSelectedChapterID).battleInfo.split("\\|");
        int i = 0;
        for (String s : ss) {
            TextSprite textSprite = new TextSprite(width - 451 * unZoom, (75 + i * 18) * unZoom, true, s, TexRegionManager.getInstance().getFont16());
            textSprite.setScale(unZoom);
            addSprite(textSprite);
            i++;
        }


        i = 12;
        //【胜利条件】|  敌方全灭||
        TextSprite textSprite = new TextSprite(width - 451 * unZoom, (75 + i * 18) * unZoom, true, "【胜利条件】", TexRegionManager.getInstance().getFont16());
        textSprite.setScale(unZoom);
        addSprite(textSprite);
        i++;

        textSprite = new TextSprite(width - 451 * unZoom, (75 + i * 18) * unZoom, true, gdm.chapters.get(newSelectedChapterID).victoryTerm, TexRegionManager.getInstance().getFont16());
        textSprite.setScale(unZoom);
        addSprite(textSprite);
        i++;

        textSprite = new TextSprite(width - 451 * unZoom, (75 + i * 18) * unZoom, true, "", TexRegionManager.getInstance().getFont16());
        textSprite.setScale(unZoom);
        addSprite(textSprite);
        i++;


        //---------------------------------------
        Set<ID.WEAPON_TYPE> weaponTypeSet = new HashSet<>();

        SQLiteDatabase dbData = DatabaseManager.getSqLiteDatabase("data.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "data.db", null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = dbData.query("WEAPON_NODE_CHAPTER",
                new String[]{"chapter_no", "id", "info_id", "exp", "team_color"}, "chapter_no = " + newSelectedChapterID,
                null, null, null, " id ");

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {


            int infoId = cursor.getInt(cursor.getColumnIndex("info_id"));
            ID.TEAM_COLOR teamColor = ID.TEAM_COLOR.valueOf(cursor.getString(cursor.getColumnIndex("team_color")));

            if (gdm.chapters.get(this.newSelectedChapterID).getEnemyTeamColors(TEAM_COLOR.BLUE).contains(teamColor)) {
                weaponTypeSet.add(gdm.weapInfos.get(infoId).type);
            }


            cursor.moveToNext();
        }
        cursor.close();
        dbData.close();
        //---------------------------------------


        // 【敌方势力】|
        textSprite = new TextSprite(width - 451 * unZoom, (75 + i * 18) * unZoom, true, "【敌方势力】", TexRegionManager.getInstance().getFont16());
        textSprite.setScale(unZoom);
        addSprite(textSprite);
        i++;
/*
        case BATTLE_PLANE:
        return "战斗机";
        case ATTACK_PLANE:
        return "攻击机";
        case ATTACK_HELICOPTER:
        return "武装直升机";
        case UTILITY_HELICOPTER:
        return "通用直升机";*/

        if (weaponTypeSet.contains(WEAPON_TYPE.BATTLE_PLANE)) {
            textSprite = new TextSprite(width - 451 * unZoom, (75 + i * 18) * unZoom, true, "战斗机", TexRegionManager.getInstance().getFont16());
        } else {
            textSprite = new TextSprite(width - 451 * unZoom, (75 + i * 18) * unZoom, true, "战斗机", TexRegionManager.getInstance().getFont16(), Color.RED);
        }
        textSprite.setScale(unZoom);
        addSprite(textSprite);

        if (weaponTypeSet.contains(WEAPON_TYPE.ATTACK_PLANE)) {
            textSprite = new TextSprite(width - (451 - OX * 1) * unZoom, (75 + i * 18) * unZoom, true, "攻击机", TexRegionManager.getInstance().getFont16());
        } else {
            textSprite = new TextSprite(width - (451 - OX * 1) * unZoom, (75 + i * 18) * unZoom, true, "攻击机", TexRegionManager.getInstance().getFont16(), Color.RED);
        }
        textSprite.setScale(unZoom);
        addSprite(textSprite);

        if (weaponTypeSet.contains(WEAPON_TYPE.ATTACK_HELICOPTER)) {
            textSprite = new TextSprite(width - (451 - OX * 2) * unZoom, (75 + i * 18) * unZoom, true, "武装直升机", TexRegionManager.getInstance().getFont16());
        } else {
            textSprite = new TextSprite(width - (451 - OX * 2) * unZoom, (75 + i * 18) * unZoom, true, "武装直升机", TexRegionManager.getInstance().getFont16(), Color.RED);
        }
        textSprite.setScale(unZoom);
        addSprite(textSprite);

        if (weaponTypeSet.contains(WEAPON_TYPE.UTILITY_HELICOPTER)) {
            textSprite = new TextSprite(width - (451 - OX * 3) * unZoom, (75 + i * 18) * unZoom, true, "通用直升机", TexRegionManager.getInstance().getFont16());
        } else {
            textSprite = new TextSprite(width - (451 - OX * 3) * unZoom, (75 + i * 18) * unZoom, true, "通用直升机", TexRegionManager.getInstance().getFont16(), Color.RED);
        }
        textSprite.setScale(unZoom);
        addSprite(textSprite);
        i++;

        /*case TANK:
        return "坦克";
        case ARMORED_CAR:
        return "装甲车";
        case SCOUT_CAR:
        return "侦察车";
        case SELF_MECHANIZED_GUN:
        return "自行火炮";*/

        if (weaponTypeSet.contains(WEAPON_TYPE.TANK)) {
            textSprite = new TextSprite(width - 451 * unZoom, (75 + i * 18) * unZoom, true, "坦克", TexRegionManager.getInstance().getFont16());
        } else {
            textSprite = new TextSprite(width - 451 * unZoom, (75 + i * 18) * unZoom, true, "坦克", TexRegionManager.getInstance().getFont16(), Color.RED);
        }
        textSprite.setScale(unZoom);
        addSprite(textSprite);

        if (weaponTypeSet.contains(WEAPON_TYPE.ARMORED_CAR)) {
            textSprite = new TextSprite(width - (451 - OX * 1) * unZoom, (75 + i * 18) * unZoom, true, "装甲车", TexRegionManager.getInstance().getFont16());
        } else {
            textSprite = new TextSprite(width - (451 - OX * 1) * unZoom, (75 + i * 18) * unZoom, true, "装甲车", TexRegionManager.getInstance().getFont16(), Color.RED);
        }
        textSprite.setScale(unZoom);
        addSprite(textSprite);

        if (weaponTypeSet.contains(WEAPON_TYPE.SCOUT_CAR)) {
            textSprite = new TextSprite(width - (451 - OX * 2) * unZoom, (75 + i * 18) * unZoom, true, "侦察车", TexRegionManager.getInstance().getFont16());
        } else {
            textSprite = new TextSprite(width - (451 - OX * 2) * unZoom, (75 + i * 18) * unZoom, true, "侦察车", TexRegionManager.getInstance().getFont16(), Color.RED);
        }
        textSprite.setScale(unZoom);
        addSprite(textSprite);

        if (weaponTypeSet.contains(WEAPON_TYPE.SELF_MECHANIZED_GUN)) {
            textSprite = new TextSprite(width - (451 - OX * 3) * unZoom, (75 + i * 18) * unZoom, true, "自行火炮", TexRegionManager.getInstance().getFont16());
        } else {
            textSprite = new TextSprite(width - (451 - OX * 3) * unZoom, (75 + i * 18) * unZoom, true, "自行火炮", TexRegionManager.getInstance().getFont16(), Color.RED);
        }
        textSprite.setScale(unZoom);
        addSprite(textSprite);
        i++;
       /* case ANTIAIRCRAFT_GUN:
        return "自行高射炮";
        case ANTIAIRCRAFT_MISSILE:
        return "对空导弹";
        case INFANTRY_FIGHTING_VEHICLE:
        return "步兵战车";
        case INFANTRY:
        return "步兵";*/

        if (weaponTypeSet.contains(WEAPON_TYPE.ANTIAIRCRAFT_GUN)) {
            textSprite = new TextSprite(width - 451 * unZoom, (75 + i * 18) * unZoom, true, "自行高射炮", TexRegionManager.getInstance().getFont16());
        } else {
            textSprite = new TextSprite(width - 451 * unZoom, (75 + i * 18) * unZoom, true, "自行高射炮", TexRegionManager.getInstance().getFont16(), Color.RED);
        }
        textSprite.setScale(unZoom);
        addSprite(textSprite);

        if (weaponTypeSet.contains(WEAPON_TYPE.ANTIAIRCRAFT_MISSILE)) {
            textSprite = new TextSprite(width - (451 - OX * 1) * unZoom, (75 + i * 18) * unZoom, true, "对空导弹", TexRegionManager.getInstance().getFont16());
        } else {
            textSprite = new TextSprite(width - (451 - OX * 1) * unZoom, (75 + i * 18) * unZoom, true, "对空导弹", TexRegionManager.getInstance().getFont16(), Color.RED);
        }
        textSprite.setScale(unZoom);
        addSprite(textSprite);

        if (weaponTypeSet.contains(WEAPON_TYPE.INFANTRY_FIGHTING_VEHICLE)) {
            textSprite = new TextSprite(width - (451 - OX * 2) * unZoom, (75 + i * 18) * unZoom, true, "步兵战车", TexRegionManager.getInstance().getFont16());
        } else {
            textSprite = new TextSprite(width - (451 - OX * 2) * unZoom, (75 + i * 18) * unZoom, true, "步兵战车", TexRegionManager.getInstance().getFont16(), Color.RED);
        }
        textSprite.setScale(unZoom);
        addSprite(textSprite);

        if (weaponTypeSet.contains(WEAPON_TYPE.INFANTRY)) {
            textSprite = new TextSprite(width - (451 - OX * 3) * unZoom, (75 + i * 18) * unZoom, true, "步兵", TexRegionManager.getInstance().getFont16());
        } else {
            textSprite = new TextSprite(width - (451 - OX * 3) * unZoom, (75 + i * 18) * unZoom, true, "步兵", TexRegionManager.getInstance().getFont16(), Color.RED);
        }
        textSprite.setScale(unZoom);
        addSprite(textSprite);
        i++;
        /*case BATTLE_SHIP:
        return "战舰";
        case SUBMARINE:
        return "潜水艇";*/


        if (weaponTypeSet.contains(WEAPON_TYPE.BATTLE_SHIP)) {
            textSprite = new TextSprite(width - 451 * unZoom, (75 + i * 18) * unZoom, true, "战舰", TexRegionManager.getInstance().getFont16());
        } else {
            textSprite = new TextSprite(width - 451 * unZoom, (75 + i * 18) * unZoom, true, "战舰", TexRegionManager.getInstance().getFont16(), Color.RED);
        }
        textSprite.setScale(unZoom);
        addSprite(textSprite);

        if (weaponTypeSet.contains(WEAPON_TYPE.SUBMARINE)) {
            textSprite = new TextSprite(width - (451 - OX * 1) * unZoom, (75 + i * 18) * unZoom, true, "潜水艇", TexRegionManager.getInstance().getFont16());
        } else {
            textSprite = new TextSprite(width - (451 - OX * 1) * unZoom, (75 + i * 18) * unZoom, true, "潜水艇", TexRegionManager.getInstance().getFont16(), Color.RED);
        }
        textSprite.setScale(unZoom);
        addSprite(textSprite);
        i++;




        NormalSprite wn3Sprite = new NormalSprite(width - 460 * unZoom, (70 + 6 + 18 * LINE) * unZoom, "adva_wn3", 0);
        wn3Sprite.setScale(unZoom);
        addSprite(wn3Sprite);
    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {

    }
}

package com.game.gaika.data;

import android.os.Environment;
import android.util.DisplayMetrics;

import com.game.gaika.debug.DebugManager;
import com.game.gaika.main.MainActivity;

/**
 * Created by fangxg on 2015/6/4.
 */
public class GameSetup {

    //DEBUG FLAGS
    //  public static boolean isDebug_read_SD_res = true; //重要，不发布，不要改

    public static boolean isDebug_trackingUserProcess = true;
    public static boolean isDebug_setupDialog = true;
    public static boolean isDebug_HUDTextBox = false;
    public static boolean isDebug_delay_to_touch = false;
    public static boolean isDebug_city_sprite_xy = false;
    public static boolean isDebug_box_sprite_xy = false;
    public static boolean isDebug_Weapon_id = true;

    public static float DEBUG_BUTTON_SCALE_BATT = 1.5f;
    public static float DEBUG_BUTTON_SCALE_NOT_BATT = 2.0f;

    //游戏 画面设置
    public static boolean show_border_sub = true;
    //游戏 机能
    public static boolean open_group_attack = true;
    public static float GROUP_ATTACK_RATE = 0.6f;
    public static boolean open_move_finished_attack = true;
    public static boolean open_attack_neighbour = true;
    public static boolean open_first_attack = true;
    public static boolean open_attack_tank_up = true;
    public static float TANK_ATTACK_UP = 1.5f;
    public static boolean open_attack_helicopter_up = true;
    public static boolean open_night_attack_low = true;
    public static float NIGHT_ATTACK_LOW = 0.7f;
    public static float DIFF_EASE = 1.0f;
    public static float DIFF_HARD = 0.85f;
    public static float DIFF_VERY_HARD = 0.75f;

    //调试 延时时间 信息
    public static float DELAY_SHORT_S = 0.010f;
    public static float DELAY_PATH_S = 0.07f;
    public static float DELAY_FIGHT_S = 0.5f;
    public static float DELAY_LV_UP_S = 0.333f;
    public static int DELAY_CAPTURE_MS = 10;
    public static int DELAY_CAPTURED_MS = 1000;
    public static float DELAY_GET_SOME_THING_S = 1.5f;
    public static float DELAY_CRASH_DLG_S = 1.0f;
    public static float DELAY_EFFECT_S = 0.600f;
    public static float DELAY_TURN_BEGIN_DLG_S = 1.0f;
    public static float DELAY_AI_BUY_NEXT_S = 1.0f;
    public static float DELAY_AI_RE_SUPPLY_NEXT_S = 1.0f;
    public static float DELAY_AI_LOOP_S = 0.5f;


    public static boolean isDebug_delay_soon = false;

    static {
        if (isDebug_delay_soon == true) {
            DELAY_LV_UP_S = 2.0f;
            DELAY_PATH_S = 0.5f;
            DELAY_FIGHT_S = 0.5f;
            DELAY_SHORT_S = 2.0f;
            DELAY_CAPTURE_MS = 100;
            DELAY_CAPTURED_MS = 3000;
            DELAY_GET_SOME_THING_S = 3;
            DELAY_CRASH_DLG_S = 3;
            DELAY_EFFECT_S = 0.600f;
            DELAY_TURN_BEGIN_DLG_S = 3.0f;
            DELAY_AI_BUY_NEXT_S = 3.0f;
            DELAY_AI_RE_SUPPLY_NEXT_S = 3.0f;
            DELAY_AI_LOOP_S = 3.0f;
        }
    }


    public static float battlefieldBaseZoom = 1.5f;//2.0f;
    public static final float SETTING_BATTLEFIELD_ZOOM_MIN = 0.7f;
    public static final float SETTING_BATTLEFIELD_ZOOM_MAX = 1.3f;
    public static float settingBattlefieldZoom = 1.0f;
    public static int settingSaveDatePage = 1;
    public static boolean settingOpenSound = true;
    public static float settingSoundVolume = 1.0f;

    public static float deviceWidthPixels = 1280;
    public static float deviceHeightPixels = 720;
    public static String sdcartdRootPaht = "";
    public static String sdcartdAppPaht = "";
    public static String sdcartdPahtTexture = "";
    public static String sdcartdPahtSound = "";
    public static String sdcartdPahtDB = "";
    public static String sdcartdPahtSave = "";


    private GameSetup() {
    }

    public static void init(MainActivity pMainActivity) {

        if (DebugManager.getAppRunModel() == ID.RUN_MODELE.RELESE) {
            isDebug_trackingUserProcess = false;
            isDebug_setupDialog = false;
            isDebug_HUDTextBox = false;
            isDebug_delay_to_touch = false;
            isDebug_city_sprite_xy = false;
            isDebug_box_sprite_xy = false;
            isDebug_Weapon_id = false;
        } else if (DebugManager.getAppRunModel() == ID.RUN_MODELE.DEBUG) {
            isDebug_trackingUserProcess = true;
            isDebug_setupDialog = true;
            isDebug_HUDTextBox = false;
            isDebug_delay_to_touch = false;
            isDebug_city_sprite_xy = false;
            isDebug_box_sprite_xy = false;
            isDebug_Weapon_id = true;
        }


        DisplayMetrics metric = new DisplayMetrics();
        pMainActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);

       /* float xdpi = metric.xdpi;

        int zoom = (int) (10 + (xdpi - 132) / 35);

        GameSetup.ZOOM = ((float) zoom) / 10.0f;
        if (GameSetup.ZOOM < 1.0f) {
            GameSetup.ZOOM = 1.0f;
        } else if (GameSetup.ZOOM > 2.0f) {
            GameSetup.ZOOM = 2.0f;
        }*/

        if (metric.heightPixels < metric.widthPixels) {
            GameSetup.deviceHeightPixels = metric.heightPixels;
            GameSetup.deviceWidthPixels = metric.widthPixels;
        } else {
            GameSetup.deviceHeightPixels = metric.widthPixels;
            GameSetup.deviceWidthPixels = metric.heightPixels;
        }


        battlefieldBaseZoom = battlefieldBaseZoom / (720.0f / deviceHeightPixels);

        sdcartdRootPaht = Environment.getExternalStorageDirectory().toString() + "/";
        sdcartdAppPaht = Environment.getExternalStorageDirectory().toString() + "/com.game.gaika/";
        sdcartdPahtTexture = Environment.getExternalStorageDirectory().toString() + "/com.game.gaika/texture/";
        sdcartdPahtSound = Environment.getExternalStorageDirectory().toString() + "/com.game.gaika/sound/";
        sdcartdPahtDB = Environment.getExternalStorageDirectory().toString() + "/com.game.gaika/db/";
        sdcartdPahtSave = Environment.getExternalStorageDirectory().toString() + "/com.game.gaika/save/";
    }

}

package com.game.gaika.data;

/**
 * Created by fangxg on 2015/6/20.
 */
public class ID {

    public enum SCENE_ID {
        LOGO_1,
        LOGO_2,
        LOGO_3,
        BEGIN_MENU,
        DIFF_MENU,
        LOAD_GAME,
        SAVE_GAME,
        SETTING,
        BEGIN_LOCAL,
        BEGIN_COUNTY,
        SELECT_TARGET,
        TEAM_BUILD,
        WEAPON_BUY,
        WEAPON_REPAIR,
        DIPLOMACY,
        WEAPON_SELL,

        BATTLEFIELD_ENTER_INFO,
        BATTLEFIELD_ENTER_INFO_HUD,
        TUTORIAL,
        BATTLEFIELD,
        BATTLEFIELD_HUD,

        WEAPON_SETOUT,

        BATTLEFIELD_INFO,
        TEAM_LIST,

        CHAPTER_COMPLETE,
        CHAPTER_LOSE,

        CAN_BUY_WEAPON_DIALOG,
        LV_UP_DIALOG,
        CAPTURE_DIALOG,
        YES_NO_DIALOG,
        BLUE_REPAIR_YES_NO_DIALOG,
        GET_SOME_THING_DIALG,
        DIALOG_5_DIALOG,
        TURN_BEGIN_DIALG,
        AI_REPAIR_NEXT_DIALOG,

        FLAGS_SUB,
        SORT_MENU,
        SORTS_SUB,
        SYSTEM_POPUP_MENU,
        TOP_BUTTOS_SUB,
        BORDER_SUB,
        TOP_MENU,
        CHOOSE_ARMS_MENU,

        DEBUG_DIALOG_MAIN_DIALOG,
        DEBUG_DIALOG_1_DIALOG,
        DEBUG_DIALOG_2_DIALOG,
        DEBUG_DIALOG_3_DIALOG,
    }

    public enum SCENE_INIT_TYPE {
        INIT,
        NOT_INIT
    }

    public enum MSG_ID {

        MSG_SCENE_TEST1__BUTTON_1,
        MSG_SCENE_TEST1__BUTTON_2,

        MSG_SCENE_TEST2__BUTTON_1,


        MSG_SCENE_LOGO_1__DELAY_TIME_OUT,

        MSG_SCENE_LOGO_2__DELAY_TIME_OUT,

        MSG_SCENE_LOGO_3__DELAY_TIME_OUT,
        MSG_SCENE_LOGO_3__BACKUP_SCENE_TOUCH,

        MSG_SCENE_BEGIN_MENU__BUTTON_NEW_GAME,
        MSG_SCENE_BEGIN_MENU__BUTTON_LOAD_GAME,
        MSG_SCENE_BEGIN_MENU__BUTTON_SETTING,
        MSG_SCENE_BEGIN_MENU__BUTTON_BACK_GAME,

        MSG_SCENE_DIFF_MENU__BUTTON_EASE,
        MSG_SCENE_DIFF_MENU__BUTTON_HARD,
        MSG_SCENE_DIFF_MENU__BUTTON_VERY_HARD,
        MSG_SCENE_DIFF_MENU__BUTTON_BACK,

        MSG_SCENE_LOAD_GAME__PAGE_UP,
        MSG_SCENE_LOAD_GAME__PAGE_DOWN,
        MSG_SCENE_LOAD_GAME__RETURN,
        MSG_SCENE_LOAD_GAME__SELECT_SAVE_DATA,
        MSG_SCENE_LOAD_GAME__OVER_SAVE_YES,
        MSG_SCENE_LOAD_GAME__OVER_SAVE_NO,

        MSG_SCENE_SETTING_ZOOM_DOWN,
        MSG_SCENE_SETTING_ZOOM_UP,
        MSG_SCENE_SETTING_SOUND_OFF,
        MSG_SCENE_SETTING_SOUND_ON,
        MSG_SCENE_SETTING_VOLUME_DOWN,
        MSG_SCENE_SETTING_VOLUME_UP,
        MSG_SCENE_SETTING_RETURN,

        MSG_SCENE_BEGIN_LOCAL__BUTTON_SELECT_LOCAL_1,
        MSG_SCENE_BEGIN_LOCAL__BUTTON_SELECT_LOCAL_2,
        MSG_SCENE_BEGIN_LOCAL__BUTTON_SELECT_LOCAL_3,
        MSG_SCENE_BEGIN_LOCAL__BUTTON_SELECT_LOCAL_4,
        MSG_SCENE_BEGIN_LOCAL__BUTTON_SELECT_LOCAL_5,
        MSG_SCENE_BEGIN_LOCAL__BUTTON_SELECT_LOCAL_6,
        MSG_SCENE_BEGIN_LOCAL__BUTTON_BACK,

        MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_1,
        MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_2,
        MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_3,
        MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_4,
        MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_5,
        MSG_SCENE_COUNTRY_MENU__BUTTON_SELECT_COUNTY_6,
        MSG_SCENE_COUNTRY_MENU__BUTTON_BACK,

        MSG_SCENE_TUTORIAL__DELAY_TIME_OUT,
        MSG_SCENE_TUTORIAL__BACKUP_TOUCHE,

        //        MSG_SCENE_HUD__BUTTON_1,
        MSG_SCENE_HUD__BUTTON_BACKUP,
        MSG_SCENE_HUD__TURN_FINISH,
        MSG_SCENE_HUD__TURN_FINISH_DILOG_YES,
        MSG_SCENE_HUD__TURN_FINISH_DILOG_NO,
        MSG_SCENE_HUD__GAME_OVER_DILOG_YES,
        MSG_SCENE_HUD__GAME_OVER_DILOG_NO,
        MSG_SCENE_HUD__BUTTON_SYSTEM,

        MSG_SCENE_BATTLEFIELD_ENTER_INFO_HUD__BUTTON1,
        MSG_SCENE_BATTLEFIELD_ENTER_INFO_HUD__BUTTON2,

        MSG_SCENE_BATTLEFIELD_INFO__RETURN,

        MSG_SCENE_DIPLOMACY__LEFT,
        MSG_SCENE_DIPLOMACY__RIGHT,
        MSG_SCENE_DIPLOMACY__CONFIRM,
        MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_1,
        MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_2,
        MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_3,
        MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_4,
        MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_5,
        MSG_SCENE_DIPLOMACY__SELECT_COUNTRY_6,
        MSG_SCENE_DIPLOMACY__CAN_BUY_WEAPON_NEXT,


        MSG_SCENE_FLAGS_SUB__BUTTON_FLAG_1,
        MSG_SCENE_FLAGS_SUB__BUTTON_FLAG_2,
        MSG_SCENE_FLAGS_SUB__BUTTON_FLAG_3,
        MSG_SCENE_FLAGS_SUB__BUTTON_FLAG_4,
        MSG_SCENE_FLAGS_SUB__BUTTON_FLAG_5,
        MSG_SCENE_FLAGS_SUB__BUTTON_FLAG_6,
        MSG_SCENE_FLAGS_SUB__BUTTON_FLAG_7,

        MSG_SCENE_SAVE_GAME__PAGE_UP,
        MSG_SCENE_SAVE_GAME__PAGE_DOWN,
        MSG_SCENE_SAVE_GAME__RETURN,
        MSG_SCENE_SAVE_GAME__SELECT_SAVE_DATA,
        MSG_SCENE_SAVE_GAME__OVER_SAVE_YES,
        MSG_SCENE_SAVE_GAME__OVER_SAVE_NO,

        MSG_SCENE_SELECT_TARGET__SELECT_CHAPTER,

        MSG_SCENE_SORTS_SUB__BUTTON_SORT,

        MSG_SCENE_SORTS_SUB__BUTTON_TYPE,
        MSG_SCENE_SORTS_SUB__BUTTON_NAME,
        MSG_SCENE_SORTS_SUB__BUTTON_COUNTRY,
        MSG_SCENE_SORTS_SUB__BUTTON_BUY_COST,
        MSG_SCENE_SORTS_SUB__BUTTON_LEVEL,
        MSG_SCENE_SORTS_SUB__BUTTON_REPAIR_COST,
        MSG_SCENE_SORTS_SUB__BUTTON_SELL_COST,
        MSG_SCENE_SORTS_SUB__BUTTON_SUPPLY,

        MSG_SCENE_SORTS_SUB__BUTTON_ASCEND,


        MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_1,
        MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_2,
        MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_3,
        MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_4,
        MSG_SCENE_SYSTEM_POPUP_MENU__CAPTURED,
        MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_5,
        MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_6,
        MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_7,
        MSG_SCENE_SYSTEM_POPUP_MENU__BUTTON_8,

        MSG_SCENE_TEAM_BUILD__SELECT_WEAPON_UP,
        MSG_SCENE_TEAM_BUILD__SELECT_WEAPON_DOWN,
        MSG_SCENE_TEAM_BUILD__DOWN_LEFT,
        MSG_SCENE_TEAM_BUILD__DOWN_RIGHT,
        MSG_SCENE_TEAM_BUILD__BACKUP_TOUCH,

        MSG_SCENE_TEAM_LIST__RETURN,
        MSG_SCENE_TEAM_LIST__SCROOL_UP,
        MSG_SCENE_TEAM_LIST__SCROOL_DOWN,

        MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_SELECT_TARGET,
        MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_TEAM_BUILD,
        MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_WEAPON_BUY,
        MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_WEAPON_REPAIR,
        MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_DIPLOMACY,
        MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_WEAPON_SELL,
        MSG_SCENE_TOP_BUTTONS_SUB__BUTTON_SYSTEM_MENU,

        MSG_SCENE_WEAPON_BUY__SELECT_WEAPON_UP,
        MSG_SCENE_WEAPON_BUY__SELECT_WEAPON_DOWN,
        MSG_SCENE_WEAPON_BUY__UP_LEFT,
        MSG_SCENE_WEAPON_BUY__UP_RIGHT,
        MSG_SCENE_WEAPON_BUY__DOWN_LEFT,
        MSG_SCENE_WEAPON_BUY__DOWN_RIGHT,
        MSG_SCENE_WEAPON_BUY__BACKUP_TOUCH,

        MSG_SCENE_WEAPON_REPAIR__SELECT_WEAPON_UP,
        MSG_SCENE_WEAPON_REPAIR__UP_LEFT,
        MSG_SCENE_WEAPON_REPAIR__UP_RIGHT,
        MSG_SCENE_WEAPON_REPAIR__REPAIR_ALL,
        MSG_SCENE_WEAPON_REPAIR__REPAIR_ALL_DLG_YES,
        MSG_SCENE_WEAPON_REPAIR__REPAIR_ALL_DLG_NO,
        MSG_SCENE_WEAPON_REPAIR__BACKUP_TOUCH,

        MSG_SCENE_WEAPON_SELL__SELECT_WEAPON_UP,
        MSG_SCENE_WEAPON_SELL__UP_LEFT,
        MSG_SCENE_WEAPON_SELL__UP_RIGHT,
        MSG_SCENE_WEAPON_SELL__BACKUP_TOUCH,

        MSG_SCENE_WEAPON_SETOUT__SELECT_WEAPON,
        MSG_SCENE_WEAPON_SETOUT__BUTTON_BACK,
        MSG_SCENE_WEAPON_SETOUT__BACKUP_TOUCH,

        MSG_SCENE_BATTLEFIELD__SELECT_CITY,
        MSG_SCENE_BATTLEFIELD__SELECT_WEAPON,
        MSG_SCENE_BATTLEFIELD__SELECT_JUST_INFO_WEAPON,
        MSG_SCENE_BATTLEFIELD__SELECT_CHOOSE_ARMS_WEAPON,
        MSG_SCENE_BATTLEFIELD__SELECT_CHOOSE_ARMS_DLG_AIR,
        MSG_SCENE_BATTLEFIELD__SELECT_CHOOSE_ARMS_DLG_GROUND,
        MSG_SCENE_BATTLEFIELD__SELECT_DESC,
        MSG_SCENE_BATTLEFIELD__SELECT_ENEMY,
        MSG_SCENE_BATTLEFIELD__SELECT_SELF,
        MSG_SCENE_BATTLEFIELD__SELECT_TRANSPORTER,
        MSG_SCENE_BATTLEFIELD__SELECT_PASSENGER,
        MSG_SCENE_BATTLEFIELD__SELECT_SELF_DLG_YES,
        MSG_SCENE_BATTLEFIELD__SELECT_SELF_DLG_NO,
        MSG_SCENE_BATTLEFIELD__FIGHT_TIME_OUT,
        MSG_SCENE_BATTLEFIELD__LV_UP_TIME_OUT,
        MSG_SCENE_BATTLEFIELD__CAPTURING_TIME_OUT,
        MSG_SCENE_BATTLEFIELD__GET_SOME_THING_TIME_OUT,
        MSG_SCENE_BATTLEFIELD__CHECK_WIN_TIME_OUT,
        MSG_SCENE_BATTLEFIELD__CRASH_NEXT,
        MSG_SCENE_BATTLEFIELD__CRASH_TIME_OUT,
        MSG_SCENE_BATTLEFIELD__BLUE_TURN_BEGIN_TIME_OUT,
        MSG_SCENE_BATTLEFIELD__AI_TURN_BEGIN_TIME_OUT,
        MSG_SCENE_BATTLEFIELD__AI_REPAIR_NEXT,
        MSG_SCENE_BATTLEFIELD__AI_REPAIR_TIME_OUT,
        MSG_SCENE_BATTLEFIELD__AI_BUY_NEXT,
        MSG_SCENE_BATTLEFIELD__AI_BUY_TIME_OUT,
        MSG_SCENE_BATTLEFIELD__AI_SELECT_NON_NEXT,
        MSG_SCENE_BATTLEFIELD__AI_SELECT_NON_TIME_OUT,
        MSG_SCENE_BATTLEFIELD__AI_NEXT_TEAM,
        MSG_SCENE_BATTLEFIELD__AI_SELECT_WEAPON_TIME_OUT,
        MSG_SCENE_BATTLEFIELD__AI_MOVE_TO_DESC_TIME_OUT,
        MSG_SCENE_BATTLEFIELD__AI_FIGHT_TIME_OUT,
        MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_NEXT,
        MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_YES,
        MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_NO,
        MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_TIME_OUT,
//        MSG_SCENE_BATTLEFIELD__AI_LV_UP_TIME_OUT,
//        MSG_SCENE_BATTLEFIELD__AI_CAPLTURING_TIME_OUT,


        MSG_SCENE_CHAPTER_COMPLETE__BACK,
        MSG_SCENE_CHAPTER_LOSE__BACK,


        MSG_SCENE_DEBUG_SETUP_DIALOG_MAIN__BUTTON_1,
        MSG_SCENE_DEBUG_SETUP_DIALOG_MAIN__BUTTON_2,
        MSG_SCENE_DEBUG_SETUP_DIALOG_MAIN__BUTTON_3,


        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_1,
        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_2,
        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_3,
        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_4,
        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_5,
        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_6,
        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_7,
        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_8,
        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_9,
        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_10,
        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_11,
        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_12,
        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_13,
        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_14,


        MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_RETURN,

        MSG_SCENE_DEBUG_SETUP_DIALOG_2__BUTTON_1,
        MSG_SCENE_DEBUG_SETUP_DIALOG_2__BUTTON_2,
        MSG_SCENE_DEBUG_SETUP_DIALOG_2__BUTTON_3,
        MSG_SCENE_DEBUG_SETUP_DIALOG_2__BUTTON_4,
        MSG_SCENE_DEBUG_SETUP_DIALOG_2__BUTTON_5,
        MSG_SCENE_DEBUG_SETUP_DIALOG_2__BUTTON_6,
        MSG_SCENE_DEBUG_SETUP_DIALOG_2__BUTTON_7,
        MSG_SCENE_DEBUG_SETUP_DIALOG_2__BUTTON_8,
        MSG_SCENE_DEBUG_SETUP_DIALOG_2___LEFT,
        MSG_SCENE_DEBUG_SETUP_DIALOG_2___RIGHT,

        MSG_SCENE_DEBUG_SETUP_DIALOG_3__BUTTON_1,
        MSG_SCENE_DEBUG_SETUP_DIALOG_3__BUTTON_8,
        MSG_SCENE_DEBUG_SETUP_DIALOG_3__LEFT,
        MSG_SCENE_DEBUG_SETUP_DIALOG_3__RIGHT,
    }

    public enum TEAM_COLOR {
        BLUE,
        RED,
        YELLOW,
        GREEN,
        GRAY,
        WHITE
    }

    public enum COUNTRY {
        USA,
        USN,
        RUSSIA,
        GERMANY,
        ENGLAND,
        JAPAN,
        ALL,
        NON;

        public String toDescribeString() {
            switch (this) {
                case USA:
                    return "美国陆军";
                case USN:
                    return "美国海军";
                case RUSSIA:
                    return "俄罗斯";
                case GERMANY:
                    return "德国";
                case ENGLAND:
                    return "英国";
                case JAPAN:
                    return "日本";
            }
            return "";
        }
    }

    public enum CITY_TYPE {
        CITY,
        AIRPORT,
        HARBOUR;
    }

    public enum STOR_TYPE {
        TYPE,
        NAME,
        COUNTRY,
        BUY_COST,
        LEVEL,
        REPAIR_COST,
        SELL_COST,
        SUPPLY;

        public String toDescribeString() {

            switch (this) {
                case TYPE:
                    return "类型";
                case NAME:
                    return "名称";
                case COUNTRY:
                    return "国别";
                case SUPPLY:
                    return "补给";
                case BUY_COST:
                    return "购买价";
                case REPAIR_COST:
                    return "修理费";
                case SELL_COST:
                    return "出售价";
                case LEVEL:
                    return "级别";
            }
            return "";
        }
    }

    public enum SORT_ASCEND {
        UP,
        DOWN
    }

    public enum WEAPON_TYPE_EX {
        AIR,
        GROUND,
        SHIP,
        SUBMARINE
    }

    public enum WEAPON_TYPE {
        BATTLE_PLANE,
        ATTACK_PLANE,
        ATTACK_HELICOPTER,
        UTILITY_HELICOPTER,
        TRANSPORT_PLANE,
        TANK,
        ARMORED_CAR,
        SCOUT_CAR,
        SELF_MECHANIZED_GUN,
        ANTIAIRCRAFT_GUN,
        ANTIAIRCRAFT_MISSILE,
        INFANTRY_FIGHTING_VEHICLE,
        INFANTRY,
        BATTLE_SHIP,
        TRANSPORT_SHIP,
        AIRCRAFT_CARRIER,
        SUBMARINE,
        LANDING_SHIP;

        public String toDescribeString() {
            switch (this) {

                case BATTLE_PLANE:
                    return "战斗机";
                case ATTACK_PLANE:
                    return "攻击机";
                case ATTACK_HELICOPTER:
                    return "武装直升机";
                case UTILITY_HELICOPTER:
                    return "通用直升机";
                case TRANSPORT_PLANE:
                    return "运输机";
                case TANK:
                    return "坦克";
                case ARMORED_CAR:
                    return "装甲车";
                case SCOUT_CAR:
                    return "侦察车";
                case SELF_MECHANIZED_GUN:
                    return "自行火炮";
                case ANTIAIRCRAFT_GUN:
                    return "自行高射炮";
                case ANTIAIRCRAFT_MISSILE:
                    return "对空导弹";
                case INFANTRY_FIGHTING_VEHICLE:
                    return "步兵战车";
                case INFANTRY:
                    return "步兵";
                case BATTLE_SHIP:
                    return "战舰";
                case TRANSPORT_SHIP:
                    return "运输舰";
                case AIRCRAFT_CARRIER:
                    return "航母";
                case SUBMARINE:
                    return "潜水艇";
                case LANDING_SHIP:
                    return "登陆舰";
            }
            return "";
        }
    }

    public enum MAP_NODE_TYPE {

        PING_DI,
        TIAN_YUAN,
        SHEN_LIN,
        SHA_DI,
        QIU_LING,
        SHAN_DI,
        QIAN_TAN,
        HAI_HU,
        JIE_DAO,
        SHI_QU,
        CITY_CITY,
        CITY_AIR,
        CITY_HARBOUR,
        QIAO_LIANG;

        public String toDescribeString() {
            switch (this) {

                case PING_DI:
                    return "平地";
                case TIAN_YUAN:
                    return "田园";
                case SHEN_LIN:
                    return "森林";
                case SHA_DI:
                    return "沙地";
                case QIU_LING:
                    return "丘陵";
                case SHAN_DI:
                    return "山地";
                case QIAN_TAN:
                    return "浅滩";
                case HAI_HU:
                    return "海湖";
                case JIE_DAO:
                    return "街道";
                case SHI_QU:
                    return "市区";
                case CITY_CITY:
                    return "城市";
                case CITY_AIR:
                    return "机场";
                case CITY_HARBOUR:
                    return "海港";
                case QIAO_LIANG:
                    return "桥梁";
            }
            return "";
        }
    }

    public enum ARMS_TYPE {
        AIR,
        GROUND
    }

    public enum GAME_DIFF {
        NON,
        EASE,
        HARD,
        VERY_HARD
    }

    public enum BOX_COLOR {
        GREEN,
        RED
    }

    public enum ADVANTAGE {
        EASY,
        NORMAL,
        HARD,
        EASY_GREY,
        NORMAL_GREY,
        HARD_GREY,
        NON,
    }


    public enum DEF_ADD_TYPE {
        NONE,
        MIDDLE,
        HIGH,
    }

    public enum WIN_TYPE {
        WIN,
        LOSE,
        NON,
    }
    public enum RUN_MODELE {
        RELESE,
        DEBUG
    }
}

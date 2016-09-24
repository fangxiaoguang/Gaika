package com.game.gaika.scene;

import com.game.gaika.data.ID;

import static com.game.gaika.data.ID.SCENE_INIT_TYPE.*;
import static com.game.gaika.data.ID.SCENE_ID.*;

/**
 * Created by fangxg on 2015/7/20.
 */
public class SceneFactory {
    public static BaseLogicScene createScene(ID.SCENE_ID pSceneID) {
        return createScene(pSceneID, INIT);
    }

    public static BaseLogicScene createScene(ID.SCENE_ID pSceneID, ID.SCENE_INIT_TYPE pESceneInitType) {
        boolean isInit = true;

        if (pESceneInitType == INIT) {
            isInit = true;
        } else if (pESceneInitType == NOT_INIT) {
            isInit = false;
        }

        if (pSceneID == LOGO_1) {
            return new Logo1Scene();
        }
        if (pSceneID == LOGO_2) {
            return new Logo2Scene();
        }
        if (pSceneID == LOGO_3) {
            return new Logo3Scene();
        }
        if (pSceneID == BEGIN_MENU) {
            return new BeginMenuSceen();
        }
        if (pSceneID == BEGIN_LOCAL) {
            return new BeginLocalScene();
        }
        if (pSceneID == BEGIN_COUNTY) {
            return new BeginCountyScene();
        }
        if (pSceneID == DIFF_MENU) {
            return new DiffMenuScene();
        }
        if (pSceneID == DIPLOMACY) {
            return new DiplomacyScene(isInit);
        }
//        if (pSceneID == SAVE_GAME) {
//            return new SaveGameScene(isInit);
//        }
        if (pSceneID == SELECT_TARGET) {
            return new SelectTargetScene(isInit);
        }
        if (pSceneID == TEAM_BUILD) {
            return new TeamBuildScene(isInit);
        }
//        if (pSceneID == TEAM_LIST) {
//            return new TeamListScene(isInit);
//        }
        if (pSceneID == WEAPON_BUY) {
            return new WeaponBuyScene(isInit);
        }
        if (pSceneID == WEAPON_REPAIR) {
            return new WeaponRepairScene(isInit);
        }
        if (pSceneID == WEAPON_SELL) {
            return new WeaponSellScene(isInit);
        }
        if (pSceneID == BATTLEFIELD) {
            return new BattlefieldScene(isInit);
        }

        if (pSceneID == WEAPON_SETOUT) {
            return new WeaponSetoutScene();
        }
        throw new IllegalArgumentException("SCENE_ID: " + pSceneID);
    }
}

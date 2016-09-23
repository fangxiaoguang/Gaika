package com.game.gaika.scene;

import com.game.frame.scene.BaseLogicScene;
import com.game.frame.FSM.IMessageHandler;
import com.game.frame.FSM.TouchMessage;
import com.game.gaika.data.City;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameTimer;
import com.game.gaika.data.ID;
import com.game.frame.sprite.NormalSprite;
import com.game.frame.sprite.NumberSprite;
import com.game.frame.sprite.TextSprite;
import com.game.frame.texture.TexRegionManager;

import java.util.ArrayList;
import java.util.List;

import static com.game.gaika.data.ID.MSG_ID.*;

import static com.game.gaika.data.ID.SCENE_INIT_TYPE.NOT_INIT;
import static com.game.gaika.data.ID.TEAM_COLOR.*;
import static com.game.gaika.data.ID.CITY_TYPE.*;
import static com.game.gaika.data.ID.SCENE_ID.*;
/**
 * Created by fangxg on 2015/7/3.
 */
public class BattlefieldInfoScene extends BaseLogicScene implements IMessageHandler {

    //enum MSG_ID {MSG_RETURN}
    //private ID.SCENE_ID parentSceneID;

    public BattlefieldInfoScene(ID.SCENE_ID pParentSceneID) {
        super(BATTLEFIELD_INFO);
        preSceneID = pParentSceneID;

        GameDataManager gdm = GameDataManager.getInstance();

        NormalSprite backSprite = new NormalSprite(0.0f, 0.0f, "batt_info");
        addSprite(backSprite);

        //--------------------------------------------------------------
        String chapterName = gdm.getCurrentChapter().cName;

        TextSprite nameSprite = new TextSprite(169, 160, true, chapterName, TexRegionManager.getInstance().getFont16());
        addSprite(nameSprite);

        //int turnCount = gdm.currentTurn;
        GameTimer gt = gdm.gameTimer;
        int maxTurn = gdm.getCurrentChapter().maxTurn;
        GameTimer gtMax = new GameTimer(maxTurn, maxTurn);
        String sDateTime = "作战时间　" + gtMax.getDayFromTurn() + "日目  " + gtMax.getHourFromTurn() + ":00" + "限时／现在"
                + gt.getDayFromTurn() + "日目 " + gt.getHourFromTurn() + ":00";

        TextSprite timeSprite = new TextSprite(295, 160, true, sDateTime, TexRegionManager.getInstance().getFont16());// "作战时间　4日目21:00到到／现在1日目7:00");
        addSprite(timeSprite);


        String victoryTerm = gdm.getCurrentChapter().victoryTerm;
        List<String> victoryTerms = new ArrayList<String>();

        if (victoryTerm != null) {
            String[] ss = victoryTerm.split("\\|");

            for (String s1 : ss) {
                victoryTerms.add(s1);
            }
        }

        int detailCount = 0;
        for (String detail : victoryTerms) {
            TextSprite detileSprite = new TextSprite(161, 218 + 17 * detailCount, true, detail, TexRegionManager.getInstance().getFont16());
            addSprite(detileSprite);
            detailCount++;
        }


        int blueCityCount = 0;
        int blueAirportCount = 0;
        int blueHarbourCount = 0;
        int blueSupplyCount = 0;
        int redCityCount = 0;
        int redAirportCount = 0;
        int redHarbourCount = 0;
        int redSupplyCount = 0;
        int yellowCityCount = 0;
        int yellowAirportCount = 0;
        int yellowHarbourCount = 0;
        int yellowSupplyCount = 0;
        int greenCityCount = 0;
        int greenAirportCount = 0;
        int greenHarbourCount = 0;
        int greenSupplyCount = 0;
        for (City city : gdm.getCurrentChapter().getGameMap().citys.values()) {
            if (city.teamColor == BLUE) {
                if (city.type == CITY) {
                    blueCityCount++;
                }
                if (city.type == AIRPORT) {
                    blueAirportCount++;
                }
                if (city.type == HARBOUR) {
                    blueHarbourCount++;
                }
                blueSupplyCount += city.getAddSupply();

            }
            if (city.teamColor == RED) {
                if (city.type == CITY) {
                    redCityCount++;
                }
                if (city.type == AIRPORT) {
                    redAirportCount++;
                }
                if (city.type == HARBOUR) {
                    redHarbourCount++;
                }
                redSupplyCount += city.getAddSupply();
            }
            if (city.teamColor == YELLOW) {
                if (city.type == CITY) {
                    yellowCityCount++;
                }
                if (city.type == AIRPORT) {
                    yellowAirportCount++;
                }
                if (city.type == HARBOUR) {
                    yellowHarbourCount++;
                }
                yellowSupplyCount += city.getAddSupply();
            }
            if (city.teamColor == GREEN) {
                if (city.type == CITY) {
                    greenCityCount++;
                }
                if (city.type == AIRPORT) {
                    greenAirportCount++;
                }
                if (city.type == HARBOUR) {
                    greenHarbourCount++;
                }
                greenSupplyCount += city.getAddSupply();
            }
        }
        if (gdm.getCurrentChapter().side1.contains(BLUE) || gdm.getCurrentChapter().side2.contains(BLUE)) {
            NormalSprite colorSprite1 = new NormalSprite(150, 323, "fieldbt1", 0 * 2);
            addSprite(colorSprite1);

            NumberSprite numberSprite1 = new NumberSprite(262, 329, false, "font02", blueCityCount, 0, 0, 1.0f);
            addSprite(numberSprite1);
            NumberSprite numberSprite2 = new NumberSprite(262 + 61, 329, false,"font02", blueAirportCount, 0, 0, 1.0f);
            addSprite(numberSprite2);
            NumberSprite numberSprite3 = new NumberSprite(262 + 61 + 62, 329, false, "font02", blueHarbourCount, 0, 0, 1.0f);
            addSprite(numberSprite3);
            NumberSprite numberSprite4 = new NumberSprite(262 + 61 + 62 + 116, 329, false, "font02", blueSupplyCount, 0, 0, 1.0f);
            addSprite(numberSprite4);
            NumberSprite numberSprite5 = new NumberSprite(262 + 61 + 62 + 116 + 139, 329, false, "font02", gdm.getSupply(ID.TEAM_COLOR.BLUE), 0, 0, 1.0f);
            addSprite(numberSprite5);
        }
        int y = 0;

        if (gdm.getCurrentChapter().side1.contains(RED) || gdm.getCurrentChapter().side2.contains(RED)) {
            y++;
            NormalSprite colorSprite2 = new NormalSprite(150, 323 + 27, "fieldbt1", 1 * 2);
            addSprite(colorSprite2);
            NumberSprite numberSprite1 = new NumberSprite( 262, 329 + 27 * y, false,"font02", redCityCount, 0, 0, 1.0f);
            addSprite(numberSprite1);
            NumberSprite numberSprite2 = new NumberSprite( 262 + 61, 329 + 27 * y, false,"font02", redAirportCount, 0, 0, 1.0f);
            addSprite(numberSprite2);
            NumberSprite numberSprite3 = new NumberSprite( 262 + 61 + 62, 329 + 27 * y, false,"font02", redHarbourCount, 0, 0, 1.0f);
            addSprite(numberSprite3);
            NumberSprite numberSprite4 = new NumberSprite( 262 + 61 + 62 + 116, 329 + 27 * y, false,"font02", redSupplyCount, 0, 0, 1.0f);
            addSprite(numberSprite4);
            NumberSprite numberSprite5 = new NumberSprite( 262 + 61 + 62 + 116 + 139, 329 + 27 * y,false, "font02", gdm.getSupply(ID.TEAM_COLOR.RED), 0, 0, 1.0f);
            addSprite(numberSprite5);
        }
        if (gdm.getCurrentChapter().side1.contains(YELLOW) || gdm.getCurrentChapter().side2.contains(YELLOW)) {
            y++;
            NormalSprite colorSprite2 = new NormalSprite(150, 323 + 27, "fieldbt1", 2 * 2);
            addSprite(colorSprite2);
            NumberSprite numberSprite1 = new NumberSprite( 262, 329 + 27 * y, false,"font02", yellowCityCount, 0, 0, 1.0f);
            addSprite(numberSprite1);
            NumberSprite numberSprite2 = new NumberSprite( 262 + 61, 329 + 27 * y, false,"font02", yellowAirportCount, 0, 0, 1.0f);
            addSprite(numberSprite2);
            NumberSprite numberSprite3 = new NumberSprite( 262 + 61 + 62, 329 + 27 * y, false,"font02", yellowHarbourCount, 0, 0, 1.0f);
            addSprite(numberSprite3);
            NumberSprite numberSprite4 = new NumberSprite( 262 + 61 + 62 + 116, 329 + 27 * y, false,"font02", yellowSupplyCount, 0, 0, 1.0f);
            addSprite(numberSprite4);
            NumberSprite numberSprite5 = new NumberSprite( 262 + 61 + 62 + 116 + 139, 329 + 27 * y,false, "font02", gdm.getSupply(ID.TEAM_COLOR.YELLOW), 0, 0, 1.0f);
            addSprite(numberSprite5);
        }
        if (gdm.getCurrentChapter().side1.contains(GREEN) || gdm.getCurrentChapter().side2.contains(GREEN)) {
            y++;
            NormalSprite colorSprite2 = new NormalSprite(150, 323 + 27, "fieldbt1", 3 * 2);
            addSprite(colorSprite2);
            NumberSprite numberSprite1 = new NumberSprite( 262, 329 + 27 * y, false,"font02", greenCityCount, 0, 0, 1.0f);
            addSprite(numberSprite1);
            NumberSprite numberSprite2 = new NumberSprite( 262 + 61, 329 + 27 * y, false,"font02", greenAirportCount, 0, 0, 1.0f);
            addSprite(numberSprite2);
            NumberSprite numberSprite3 = new NumberSprite( 262 + 61 + 62, 329 + 27 * y, false,"font02", greenHarbourCount, 0, 0, 1.0f);
            addSprite(numberSprite3);
            NumberSprite numberSprite4 = new NumberSprite( 262 + 61 + 62 + 116, 329 + 27 * y, false,"font02", greenSupplyCount, 0, 0, 1.0f);
            addSprite(numberSprite4);
            NumberSprite numberSprite5 = new NumberSprite( 262 + 61 + 62 + 116 + 139, 329 + 27 * y,false, "font02", gdm.getSupply(ID.TEAM_COLOR.GREEN), 0, 0, 1.0f);
            addSprite(numberSprite5);
        }
        //---------------------------------------------------------------------------


        NormalSprite buttonRetSprite = new NormalSprite(144, 452, "retn_bt1", 0, new TouchMessage(MSG_SCENE_BATTLEFIELD_INFO__RETURN, null, this));
        addSprite(buttonRetSprite);

//        addChildScene(new borderSubScene(this));
    }
    @Override
    public void buildScene() {

    }


    @Override
    public boolean isBacegroundEnabled() {
        return true;
    }



    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {
        Enum msgID = pTouchMessage.getMessageID();
        if (msgID == MSG_SCENE_BATTLEFIELD_INFO__RETURN) {

            BaseLogicScene scene = SceneFactory.createScene(preSceneID, NOT_INIT);
            SceneManager.render(scene);
        }
    }
}

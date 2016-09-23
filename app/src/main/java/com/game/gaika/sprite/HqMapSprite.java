package com.game.gaika.sprite;

import com.game.frame.sprite.BaseSprite;
import com.game.gaika.data.City;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameMap;
import com.game.gaika.data.ID;
import com.game.frame.flyweight.NormalFlyweight;
import com.game.gaika.scene.SceneManager;

/**
 * Created by fangxg on 2015/8/15.
 */
public class HqMapSprite extends BaseSprite {

public static int WBOX_TAG = 10086;
    public HqMapSprite(float pX, float pY) {
        super(pX, pY);

        GameDataManager gdm = GameDataManager.getInstance();
        GameMap map = gdm.getCurrentChapter().getGameMap();
        NormalFlyweight hqMapFlyweight = new NormalFlyweight(0, 0, map.hqName);
        hqMapFlyweight.setAlpha(0.75f);


        for (City city : map.citys.values()) {
            float rx = 100.0f / map.sizeX;
            float ry = 100.0f / map.sizeY;

            int offY = 0;
            if (city.x % 2 == 1) {
                offY = (int) (ry * city.y + rx / 2);
            } else {
                offY = (int) (ry * city.y);
            }

            ID.TEAM_COLOR tc = city.teamColor;
            if (tc == ID.TEAM_COLOR.GRAY) {
                tc = ID.TEAM_COLOR.WHITE;
            }
//            NormalFlyweight cityBkFlyweight = new NormalFlyweight(rx * city.x, offY, "hp_city", 4);
//            hqMapFlyweight.addChildFlyweight(cityBkFlyweight);

            NormalFlyweight cityFlyweight = new NormalFlyweight(rx * city.x, offY, "hp_city", tc.ordinal());
            hqMapFlyweight.addChildFlyweight(cityFlyweight);
        }


        setFlyweight(hqMapFlyweight);


        float boxSizeX = SceneManager.getTopBaseLogicScene().getLogicCamera().getCameraWidth() / map.getMapSizePixelX() * 100.0f;
        float boxSizeY = SceneManager.getTopBaseLogicScene().getLogicCamera().getCameraHeight() / map.getMapSizePixelY() * 100.0f;





        NormalFlyweight whiteBox = new NormalFlyweight(0, 0, "white_1_1");
        whiteBox.setTag(HqMapSprite.WBOX_TAG);

        NormalFlyweight side1 = new NormalFlyweight(0, 0, "white_1_1");
//        side1.setScaleCenter(0.0f, 0.0f);
        side1.setScaleXY(boxSizeX / 1.0f, 1.0f);
        whiteBox.addChildFlyweight(side1);

        NormalFlyweight side2 = new NormalFlyweight(boxSizeX, 0, "white_1_1");
//        side2.setScaleCenter(0.0f, 0.0f);
        side2.setScaleXY(1.0f, boxSizeY / 1.0f);
        whiteBox.addChildFlyweight(side2);

        NormalFlyweight side3 = new NormalFlyweight(0, boxSizeY, "white_1_1");
//        side3.setScaleCenter(0.0f, 0.0f);
        side3.setScaleXY(boxSizeX / 1.0f, 1.0f);
        whiteBox.addChildFlyweight(side3);

        NormalFlyweight side4 = new NormalFlyweight(0, 0, "white_1_1");
//        side4.setScaleCenter(0.0f, 0.0f);
        side4.setScaleXY(1.0f, boxSizeY / 1.0f);
        whiteBox.addChildFlyweight(side4);

        hqMapFlyweight.addChildFlyweight(whiteBox);

		/*
			mapSprite.attachChild(whiteBox);
			SceneManager.getTopLogicScene().smallMapBox = whiteBox;

			mapSprite.setScaleCenter(0, 0);
			mapSprite.setScale(1.0f / GameSetup.ZOOM * 2.0f, 1.0f / GameSetup.ZOOM * 2.0f);

			mapSprite.setPosition(SceneManager.getTopLogicScene().bkWidth - 100.0f / GameSetup.ZOOM * 2.0f, SceneManager.getTopLogicScene().bkHeight - 100.0f
					/ GameSetup.ZOOM * 2.0f);

			mHud.attachChild(mapSprite);*/
    }
}

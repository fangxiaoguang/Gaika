package com.game.gaika.sprite;


import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.City;
import com.game.gaika.data.GameSetup;
import com.game.gaika.flyweight.AnimeFlyweight;
import com.game.gaika.flyweight.NormalFlyweight;
import com.game.gaika.flyweight.TextFlyweight;
import com.game.gaika.texture.TexRegionManager;

import static com.game.gaika.data.ID.TEAM_COLOR.*;
import static com.game.gaika.data.ID.CITY_TYPE.*;

/**
 * Created by fangxg on 2015/6/21.
 */
public class CitySprite extends BaseSprite {

    public CitySprite(City pCityNode, TouchMessage pTouchMessage) {//} IMessageHandler pMessageHandler) {
        super(pCityNode.getPixelX(), pCityNode.getPixelY());

        NormalFlyweight bkFlyweight = new NormalFlyweight(0.0f, 0.0f, "invisible50_48");

        TextFlyweight textFlyweight = new TextFlyweight(0.0f, 0.0f, true, pCityNode.name, TexRegionManager.getInstance().getFont12Stroke());
        bkFlyweight.addChildFlyweight(textFlyweight);

        int ci = 4;


        String texKey = null;
        if (pCityNode.teamColor == BLUE) {
            texKey = "marker01";
            ci = 0;
        } else if (pCityNode.teamColor == RED) {
            texKey = "marker0r";
            ci = 1;
        } else if (pCityNode.teamColor == YELLOW) {
            texKey = "marker0y";
            ci = 2;
        } else if (pCityNode.teamColor == GREEN) {
            texKey = "marker0g";
            ci = 3;
        }

        if (pCityNode.isBase == true) {
            AnimeFlyweight animeFlyweight = new AnimeFlyweight(0.0f, 0.0f, texKey, pCityNode.getBeginFreamIndex(), pCityNode.getEndFreamIndex(), 300);
            bkFlyweight.addChildFlyweight(animeFlyweight);
        }

        ci = ci * 4;
        if (pCityNode.isPoint == true) {
            ci = ci + 0;
        } else if (pCityNode.type == CITY) {
            ci = ci + 1;
        } else if (pCityNode.type == AIRPORT) {
            ci = ci + 2;
        } else if (pCityNode.type == HARBOUR) {
            ci = ci + 3;
        }

        NormalFlyweight normalFlyweight = new NormalFlyweight(0.0f, 30.0f, "tip04", ci);
        bkFlyweight.addChildFlyweight(normalFlyweight);


        int flagIndex = pCityNode.country.ordinal();
        if (flagIndex == 7) {
            flagIndex = 6;
        }
        NormalFlyweight flagFlyweight = new NormalFlyweight(28, 33, "flag02", flagIndex);
        bkFlyweight.addChildFlyweight(flagFlyweight);


        NormalFlyweight expFlyweight = new NormalFlyweight(0, 44, "color_1_1", pCityNode.teamColor.ordinal());
        expFlyweight.setScaleXY(pCityNode.capture * 50.0f / 100.0f, 5.0f);

        bkFlyweight.addChildFlyweight(expFlyweight);

        if (GameSetup.isDebug_city_sprite_xy) {
            TextFlyweight xyFlyweight = new TextFlyweight(0.0f, 0.0f, true, "(" + pCityNode.getMapX() + "," + pCityNode.getMapY() + ")", TexRegionManager.getInstance().getFont16());
            bkFlyweight.addChildFlyweight(xyFlyweight);
        }

        setFlyweight(bkFlyweight);


        setTouchMessage(pTouchMessage);
        /*TiledSprite citySprite = BaseRender.getShape(getPixelX(), getPixelY(), "invisible50_48", 0);

        Text nameSprite = BaseRender.getText12Stroke(true, 0, 0, name);
        citySprite.attachChild(nameSprite);*/
    }


}

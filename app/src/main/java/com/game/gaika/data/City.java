package com.game.gaika.data;


import static com.game.gaika.data.ID.CITY_TYPE.*;
/**
 * Created by fangxg on 2015/6/21.
 */
public class City extends IMapPoint {

    public int id;
    public int x;
    public int y;
    public ID.CITY_TYPE type;
    public boolean isBase;
    public ID.TEAM_COLOR teamColor;
    public int capture;
    public Enum country;
    // public int addSupply;

    // public boolean repair;

    public String name;
    public boolean isPoint;
    public int getWeaponInfoId;
    public int getMoney;
    public int getSupply;
    public String getMsgId;

    public City(int pId, int pX, int pY, ID.CITY_TYPE pType, boolean pIsBase, ID.TEAM_COLOR pTeamColor, int pCapture, Enum pCountry, String pName, boolean pIsPoint, int pGetWeaponInfoId, int pGetMoney, int pGetSupply, String pGetMsgId) {
       id = pId;
        x = pX;
        y = pY;
        type = pType;
        isBase = pIsBase;
        teamColor = pTeamColor;
        capture = pCapture;
        country = pCountry;
        // addSupply = pAddSupply;
        // repair = true;

        name = pName;
        isPoint = pIsPoint;
        getWeaponInfoId = pGetWeaponInfoId;
        getMoney = pGetMoney;
        getSupply = pGetSupply;
        getMsgId = pGetMsgId;
    }
    public int getBeginFreamIndex() {
        if (type == CITY) {
            return 12;
        } else if (type == AIRPORT) {
            return 15;
        } else if (type == HARBOUR) {
            return 18;
        }
        return 0;
    }

    public int getEndFreamIndex() {

        return getBeginFreamIndex() + 2;
    }

    public int getAddSupply() {

        return capture;
    }

    @Override
    public String toString() {
        return " X = " + x + "   Y = " + y + "    Name = " + name;
    }
    @Override
    public int getMapX(){
        return x;
    }


    @Override
    public int getMapY(){
        return y;
    }

    @Override
    public float getPixelX() {
        return getMapX() * 50.0f + 50.0f;
    }

    @Override
    public float getPixelY() {
        if (getMapX() % 2 == 1) {
            return getMapY() * 48.0f + 48.0f + 24.0f;
        } else {
            return getMapY() * 48.0f + 48.0f;
        }
    }
}

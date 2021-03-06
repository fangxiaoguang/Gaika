package com.game.gaika.data.weapon;

import java.util.ArrayList;
import java.util.List;


import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;
import com.game.gaika.data.MapNode;
import com.game.gaika.data.Pair;

import static com.game.gaika.data.ID.MAP_NODE_TYPE.*;
import static com.game.gaika.data.ID.WEAPON_TYPE.*;

public class LandingShip extends BaseWeapon {
    private List<BaseWeapon> passengers = new ArrayList<BaseWeapon>();
    private boolean showTransportFalg = false;

    public LandingShip(WeaponInfo pWeapInfo) {
        super(pWeapInfo);
        // TODO Auto-generated constructor stub
    }

    public LandingShip(/*int pId,*/ int pX, int pY, int pExp, int pInfoId, boolean pTeamOut, boolean pSetOut, ID.TEAM_COLOR pTeamColor, boolean pMoveEnding) {
        super(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        // TODO Auto-generated constructor stub
    }

    public LandingShip(int pId, int pInfoId, int pX, int pY, boolean pTeamout, boolean pSetout, ID.TEAM_COLOR pTemaColor, int pLife, boolean pMoveEnding, int pExp,
                       int pFuel, int pAmmoOnAir, int pAmmoOnGround, int pAmmoOnShip, int pAmmoOnSubmarine, int pMove) {
        super(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine,
                pMove);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean canTransport(BaseWeapon pWeapon) {
        GameDataManager gd = GameDataManager.getInstance();
        if (passengers.size() >= 4) {
            return false;
        }

        MapNode mapNode = gd.getCurrentChapter().getGameMap().getMapNodeFromXY(this);
        // 5 坦克 6 装甲车 7 侦察车 8 自行火炮 9 对空火炮 10 对空导弹 11 步兵战车 12 步兵
        if (mapNode != null && mapNode.type == QIAN_TAN
                && (pWeapon.info.type == TANK || pWeapon.info.type == ARMORED_CAR
                || pWeapon.info.type == SCOUT_CAR || pWeapon.info.type == SELF_MECHANIZED_GUN
                || pWeapon.info.type == ANTIAIRCRAFT_GUN || pWeapon.info.type == INFANTRY_FIGHTING_VEHICLE
                || pWeapon.info.type == INFANTRY)) {
            return true;
        }

        return false;
    }

    @Override
    public void setTransportFlag() {
        showTransportFalg = true;
    }

    @Override
    public void clearTransportFlag() {
        showTransportFalg = false;
    }

    @Override
    public Pair getTransportFlagTexIndex() {
        if (showTransportFalg == true) {
            return new Pair(9, 11);
        }
        return null;
    }

    @Override
    public void transport(BaseWeapon pWeapon) {
        passengers.add(pWeapon);
    }

    @Override
    public List<BaseWeapon> getPassengers() {
        return passengers;
    }

    @Override
    public boolean canTransportFromMapNode() {
        GameDataManager gd = GameDataManager.getInstance();
        MapNode mapNode = gd.getCurrentChapter().getGameMap().getMapNodeFromXY(this);
        if (mapNode != null && mapNode.type == QIAN_TAN) {
            return true;
        }
        return false;
    }
}

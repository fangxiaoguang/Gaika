package com.game.gaika.data.weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID.*;
import com.game.gaika.data.Pair;

/**
 * Created by fangxg on 2015/6/20.
 */
public class WeaponInfo {

    public class WeaponInfoFromLv {
        int lv;// = cursor.getInt(cursor.getColumnIndex("lv"));
        int attack_air;// = cursor.getInt(cursor.getColumnIndex("attack_air"));
        int attack_ground;// = cursor.getInt(cursor.getColumnIndex("attack_ground"));
        int attack_ship;// = cursor.getInt(cursor.getColumnIndex("attack_ship"));
        int attack_submarine;//= cursor.getInt(cursor.getColumnIndex("attack_submarine"));
        int defense_air;// = cursor.getInt(cursor.getColumnIndex("defense_air"));
        int defense_ground;// = cursor.getInt(cursor.getColumnIndex("defense_ground"));
        int defense_ship;// = cursor.getInt(cursor.getColumnIndex("defense_ship"));
        int defense_submarine;// = cursor.getInt(cursor.getColumnIndex("defense_submarine"));

        public WeaponInfoFromLv() {

        }

        public WeaponInfoFromLv(int lv, int attack_air, int attack_ground, int attack_ship, int attack_submarine, int defense_air, int defense_ground, int defense_ship, int defense_submarine) {
            this.lv = lv;
            this.attack_air = attack_air;
            this.attack_ground = attack_ground;
            this.attack_ship = attack_ship;
            this.attack_submarine = attack_submarine;
            this.defense_air = defense_air;
            this.defense_ground = defense_ground;
            this.defense_ship = defense_ship;
            this.defense_submarine = defense_submarine;
        }
    }

    public int id;
    public String name;
    public COUNTRY country;
    public WEAPON_TYPE type;
    public WEAPON_TYPE_EX typeEx;

    public int supply;
    public int price;

    public int fuel;

    public int transport;
    public int diplomacy;

    public int ammoOnAir;
    public int ammoOnGround;
    public int ammoOnShip;
    public int ammoOnSubmarine;

    public int rangeOnAir;
    public int rangeOnGround;
    public int rangeOnShip;
    public int rangeOnSubmarine;

    public int move;

    public String detail;

    public int capture;

    public boolean continueAttack;

    public int texIndex;

    public boolean canChooseArms;
    public int armsType;

    public int airArmsInfoId;
    public int groundArmsInfoId;

    public boolean canVTAL;
    public boolean inferiorMove;

    public boolean fristAttack;

    public boolean halfMoveLoss;

    public boolean attackTankUp;
    public boolean attackHelicopterUp;
    public boolean nightAttackLow;

    public boolean moveFinishedAttack;

    public boolean aircraftCarrierTAL;

    public boolean attackNeighbour;
    public boolean groupAttack;

    public Map<Integer, WeaponInfoFromLv> lvDataFromType;

    public WeaponInfo(int pId, String pName, COUNTRY pNationality, WEAPON_TYPE pType, int pSupply, int pFuel, int pTransport, int pDiplomacy, int pAmmoOnAir,
                      int pAmmoOnGround, int pAmmoOnShip, int pAmmoOnsubmarine, int pPrice, int pMove, int pRangeOnAir, int pRangeOnGround, int pRangeOnShip,
                      int pRangeOnSubmarine, String pDetail, int pCapture, boolean pContinueAttack, int pTexIndex, boolean pCanChooseArms, int pArmsType,
                      int pAirArmsInfoId, int pGroundArmsInfoId, boolean pCanVTAL, boolean pInferiorMove, boolean pFristAttack, boolean pHalfMoveLoss,
                      boolean pAttackTankUp, boolean pAttackHelicopterUp, boolean pNightAttackLow, boolean pMoveFinishedAttack, boolean pAttackNeighbour,
                      boolean pGroupAttack, boolean pAircraftCarrierTAL) {
        id = pId;
        name = pName;
        country = pNationality;
        type = pType;

        if (type == WEAPON_TYPE.BATTLE_PLANE || type == WEAPON_TYPE.ATTACK_PLANE || type == WEAPON_TYPE.ATTACK_HELICOPTER
                || type == WEAPON_TYPE.UTILITY_HELICOPTER || type == WEAPON_TYPE.TRANSPORT_PLANE) {
            typeEx = WEAPON_TYPE_EX.AIR;
        } else if (type == WEAPON_TYPE.TANK || type == WEAPON_TYPE.ARMORED_CAR || type == WEAPON_TYPE.SCOUT_CAR
                || type == WEAPON_TYPE.SELF_MECHANIZED_GUN || type == WEAPON_TYPE.ANTIAIRCRAFT_GUN
                || type == WEAPON_TYPE.ANTIAIRCRAFT_MISSILE || type == WEAPON_TYPE.INFANTRY_FIGHTING_VEHICLE
                || type == WEAPON_TYPE.INFANTRY) {
            typeEx = WEAPON_TYPE_EX.GROUND;
        } else if (type == WEAPON_TYPE.BATTLE_SHIP || type == WEAPON_TYPE.TRANSPORT_SHIP
                || type == WEAPON_TYPE.AIRCRAFT_CARRIER || type == WEAPON_TYPE.LANDING_SHIP) {
            typeEx = WEAPON_TYPE_EX.SHIP;
        } else if (type == WEAPON_TYPE.SUBMARINE) {
            typeEx = WEAPON_TYPE_EX.SUBMARINE;
        } else {
            throw new IllegalArgumentException("weapon tyep:" + type);
        }
        supply = pSupply;
        fuel = pFuel;

        transport = pTransport;
        diplomacy = pDiplomacy;
        ammoOnAir = pAmmoOnAir;
        ammoOnGround = pAmmoOnGround;
        ammoOnShip = pAmmoOnShip;
        ammoOnSubmarine = pAmmoOnsubmarine;
        price = pPrice;
        move = pMove;
        rangeOnAir = pRangeOnAir;
        rangeOnGround = pRangeOnGround;
        rangeOnShip = pRangeOnShip;
        rangeOnSubmarine = pRangeOnSubmarine;

        detail = pDetail;

        capture = pCapture;

        continueAttack = pContinueAttack;
        texIndex = pTexIndex;
        canChooseArms = pCanChooseArms;
        armsType = pArmsType;
        airArmsInfoId = pAirArmsInfoId;
        groundArmsInfoId = pGroundArmsInfoId;

        canVTAL = pCanVTAL;
        inferiorMove = pInferiorMove;
        fristAttack = pFristAttack;
        halfMoveLoss = pHalfMoveLoss;

        attackTankUp = pAttackTankUp;
        attackHelicopterUp = pAttackHelicopterUp;
        nightAttackLow = pNightAttackLow;
        moveFinishedAttack = pMoveFinishedAttack;
        attackNeighbour = pAttackNeighbour;
        groupAttack = pGroupAttack;
        aircraftCarrierTAL = pAircraftCarrierTAL;
    }

    public List<String> getDetails() {
        List<String> retList = new ArrayList<String>();

        if (detail == null) {
            return retList;
        } else if (detail.length() == 0) {
            return retList;
        }

        String[] ss = detail.split("\\|");

        for (String s1 : ss) {
            retList.add(s1);
        }

        return retList;
    }

    public WEAPON_TYPE_EX getTypeEx() {

        return typeEx;
        // ID.WEAPON_TYPE type = info.type;
       /* if (type == BATTLE_PLANE || type == ATTACK_PLANE || type == ATTACK_HELICOPTER
                || type == UTILITY_HELICOPTER || type == TRANSPORT_PLANE) {
            return AIR;
        }
        if (type == TANK || type == ARMORED_CAR || type == SCOUT_CAR
                || type == SELF_MECHANIZED_GUN || type == ANTIAIRCRAFT_GUN
                || type == ANTIAIRCRAFT_MISSILE || type == INFANTRY_FIGHTING_VEHICLE
                || type == INFANTRY) {
            return GROUND;
        }
        if (type == BATTLE_SHIP || type == TRANSPORT_SHIP
                || type == AIRCRAFT_CARRIER || type == LANDING_SHIP) {
            return SHIP;
        }
        if (type == SUBMARINE) {
            return SUBMARINE;
        }
        throw new IllegalArgumentException("weapon tyep:" + type);*/
    }

    public boolean canSetoutByCityType(CITY_TYPE pCityType) {

        if (pCityType == CITY_TYPE.CITY) {
            if (type == WEAPON_TYPE.ATTACK_HELICOPTER || type == WEAPON_TYPE.UTILITY_HELICOPTER
                    || type == WEAPON_TYPE.TANK || type == WEAPON_TYPE.ARMORED_CAR
                    || type == WEAPON_TYPE.SCOUT_CAR || type == WEAPON_TYPE.SELF_MECHANIZED_GUN
                    || type == WEAPON_TYPE.ANTIAIRCRAFT_GUN || type == WEAPON_TYPE.ANTIAIRCRAFT_MISSILE
                    || type == WEAPON_TYPE.INFANTRY_FIGHTING_VEHICLE || type == WEAPON_TYPE.INFANTRY) {
                return true;
            } else if (type == WEAPON_TYPE.ATTACK_PLANE && canVTAL == true) {
                return true;
            }
        } else if (pCityType == CITY_TYPE.HARBOUR) {
            if (type == WEAPON_TYPE.BATTLE_SHIP || type == WEAPON_TYPE.TRANSPORT_SHIP
                    || type == WEAPON_TYPE.AIRCRAFT_CARRIER || type == WEAPON_TYPE.SUBMARINE
                    || type == WEAPON_TYPE.LANDING_SHIP) {
                return true;
            }
        } else if (pCityType == CITY_TYPE.AIRPORT) {
            if (type == WEAPON_TYPE.BATTLE_PLANE || type == WEAPON_TYPE.ATTACK_PLANE
                    || type == WEAPON_TYPE.ATTACK_HELICOPTER || type == WEAPON_TYPE.UTILITY_HELICOPTER
                    || type == WEAPON_TYPE.TRANSPORT_PLANE) {
                return true;
            }
        }
        return false;
    }

    public int getSupplyLifeExByTurn() {
        if (country == COUNTRY.JAPAN) {// 日本
            return 4;
        } else if (country == COUNTRY.RUSSIA) {// 俄国
            return 2;
        } else {
            return 3;
        }
    }

    public String getUnitString() {
        if (type == WEAPON_TYPE.BATTLE_PLANE || type == WEAPON_TYPE.ATTACK_PLANE || type == WEAPON_TYPE.ATTACK_HELICOPTER
                || type == WEAPON_TYPE.UTILITY_HELICOPTER || type == WEAPON_TYPE.TRANSPORT_PLANE) {
            return "架";
        }
        if (type == WEAPON_TYPE.TANK || type == WEAPON_TYPE.ARMORED_CAR || type == WEAPON_TYPE.SCOUT_CAR
                || type == WEAPON_TYPE.SELF_MECHANIZED_GUN || type == WEAPON_TYPE.ANTIAIRCRAFT_GUN
                || type == WEAPON_TYPE.ANTIAIRCRAFT_MISSILE || type == WEAPON_TYPE.INFANTRY_FIGHTING_VEHICLE) {
            return "辆";
        }
        if (type == WEAPON_TYPE.INFANTRY) {
            return "单位";
        }
        if (type == WEAPON_TYPE.BATTLE_SHIP || type == WEAPON_TYPE.TRANSPORT_SHIP || type == WEAPON_TYPE.AIRCRAFT_CARRIER
                || type == WEAPON_TYPE.SUBMARINE || type == WEAPON_TYPE.LANDING_SHIP) {
            return "艘";
        }
        return "单位";
    }

    public Pair<Integer> getRange(WEAPON_TYPE_EX pTypeEx) {
        int maxRange;
        if (pTypeEx == WEAPON_TYPE_EX.AIR) {
            maxRange = rangeOnAir;
        } else if (pTypeEx == WEAPON_TYPE_EX.GROUND) {
            maxRange = rangeOnGround;
        } else if (pTypeEx == WEAPON_TYPE_EX.SHIP) {
            maxRange = rangeOnShip;
        } else if (pTypeEx == WEAPON_TYPE_EX.SUBMARINE) {
            maxRange = rangeOnSubmarine;
        } else {
            throw new IllegalArgumentException("Not supprt WeaponTypeEx: " + pTypeEx);
        }

        int minRange = 0;
        if (maxRange > 0) {
            minRange = 1;
            if (GameSetup.open_attack_neighbour == true && attackNeighbour == false) {
                minRange = 2;
            }
        }

        return new Pair(minRange, maxRange);
    }
}

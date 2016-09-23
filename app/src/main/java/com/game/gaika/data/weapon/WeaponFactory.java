package com.game.gaika.data.weapon;


import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;

import static com.game.gaika.data.ID.WEAPON_TYPE.*;


public class WeaponFactory {

    private WeaponFactory() {

    }

    private static WeaponFactory _nistance = null;

    public static WeaponFactory getInstance() {
        if (_nistance == null) {
            _nistance = new WeaponFactory();
        }
        return _nistance;
    }

    public BaseWeapon newWeapon(WeaponInfo pWeapInfo) {
        if (pWeapInfo.type == BATTLE_PLANE) {
            return new BattlePlane(pWeapInfo);
        }
        if (pWeapInfo.type == ATTACK_PLANE) {
            return new AttackPlane(pWeapInfo);
        }
        if (pWeapInfo.type == ATTACK_HELICOPTER) {
            return new AttackHelicopter(pWeapInfo);
        }
        if (pWeapInfo.type == UTILITY_HELICOPTER) {
            return new TransportHelicopter(pWeapInfo);
        }
        if (pWeapInfo.type == TRANSPORT_PLANE) {
            return new TransportPlane(pWeapInfo);
        }
        if (pWeapInfo.type == TANK) {
            return new Tank(pWeapInfo);
        }
        if (pWeapInfo.type == ARMORED_CAR) {
            return new ArmoredCar(pWeapInfo);
        }
        if (pWeapInfo.type == SCOUT_CAR) {
            return new ScoutCar(pWeapInfo);
        }
        if (pWeapInfo.type == SELF_MECHANIZED_GUN) {
            return new SelfMechanizedGun(pWeapInfo);
        }
        if (pWeapInfo.type == ANTIAIRCRAFT_GUN) {
            return new AntiaircraftGun(pWeapInfo);
        }
        if (pWeapInfo.type == ANTIAIRCRAFT_MISSILE) {
            return new AntiaircraftMissile(pWeapInfo);
        }
        if (pWeapInfo.type == INFANTRY_FIGHTING_VEHICLE) {
            return new InfantryFightingVehicle(pWeapInfo);
        }
        if (pWeapInfo.type == INFANTRY) {
            return new Infantry(pWeapInfo);
        }
        if (pWeapInfo.type == BATTLE_SHIP) {
            return new BattleShip(pWeapInfo);
        }
        if (pWeapInfo.type == TRANSPORT_SHIP) {
            return new TransportShip(pWeapInfo);
        }
        if (pWeapInfo.type == AIRCRAFT_CARRIER) {
            return new AircraftCarrier(pWeapInfo);
        }
        if (pWeapInfo.type == SUBMARINE) {
            return new Submarine(pWeapInfo);
        }
        if (pWeapInfo.type == LANDING_SHIP) {
            return new LandingShip(pWeapInfo);
        }
//		Log.d("Gaika", "no support weapon infoId:" + pWeapInfo);
        return null;
    }

    public BaseWeapon newWeapon(int pId, int pX, int pY, int pExp, int pInfoId, boolean pTeamOut, boolean pSetOut,
                                ID.TEAM_COLOR pTeamColor, boolean pMoveEnding) {
        GameDataManager gd = GameDataManager.getInstance();
        WeaponInfo info = gd.weapInfos.get(pInfoId);
        if (info.type == BATTLE_PLANE) {
            return new BattlePlane(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == ATTACK_PLANE) {
            return new AttackPlane(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == ATTACK_HELICOPTER) {
            return new AttackHelicopter(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == UTILITY_HELICOPTER) {
            return new TransportHelicopter(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == TRANSPORT_PLANE) {
            return new TransportPlane(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == TANK) {
            return new Tank(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == ARMORED_CAR) {
            return new ArmoredCar(/*pId, */pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == SCOUT_CAR) {
            return new ScoutCar(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == SELF_MECHANIZED_GUN) {
            return new SelfMechanizedGun(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == ANTIAIRCRAFT_GUN) {
            return new AntiaircraftGun(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == ANTIAIRCRAFT_MISSILE) {
            return new AntiaircraftMissile(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == INFANTRY_FIGHTING_VEHICLE) {
            return new InfantryFightingVehicle(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == INFANTRY) {
            return new Infantry(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == BATTLE_SHIP) {
            return new BattleShip(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == TRANSPORT_SHIP) {
            return new TransportShip(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == AIRCRAFT_CARRIER) {
            return new AircraftCarrier(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == SUBMARINE) {
            return new Submarine(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
        if (info.type == LANDING_SHIP) {
            return new LandingShip(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
        }
//		Log.d("Gaika", "no support weapon infoId:" + info);
        return null;
    }

    public BaseWeapon newWeapon(int pId, int pInfoId, int pX, int pY, boolean pTeamout, boolean pSetout, ID.TEAM_COLOR pTemaColor,
                                int pLife, boolean pMoveEnding, int pExp, int pFuel, int pAmmoOnAir, int pAmmoOnGround, int pAmmoOnShip,
                                int pAmmoOnSubmarine, int pMove) {
        GameDataManager gd = GameDataManager.getInstance();
        WeaponInfo info = gd.weapInfos.get(pInfoId);
        if (info.type == BATTLE_PLANE) {
            return new BattlePlane(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == ATTACK_PLANE) {
            return new AttackPlane(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == ATTACK_HELICOPTER) {
            return new AttackHelicopter(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == UTILITY_HELICOPTER) {
            return new TransportHelicopter(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == TRANSPORT_PLANE) {
            return new TransportPlane(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == TANK) {
            return new Tank(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == ARMORED_CAR) {
            return new ArmoredCar(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == SCOUT_CAR) {
            return new ScoutCar(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == SELF_MECHANIZED_GUN) {
            return new SelfMechanizedGun(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == ANTIAIRCRAFT_GUN) {
            return new AntiaircraftGun(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == ANTIAIRCRAFT_MISSILE) {
            return new AntiaircraftMissile(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == INFANTRY_FIGHTING_VEHICLE) {
            return new InfantryFightingVehicle(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == INFANTRY) {
            return new Infantry(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == BATTLE_SHIP) {
            return new BattleShip(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == TRANSPORT_SHIP) {
            return new TransportShip(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == AIRCRAFT_CARRIER) {
            return new AircraftCarrier(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == SUBMARINE) {
            return new Submarine(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
        if (info.type == LANDING_SHIP) {
            return new LandingShip(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine, pMove);
        }
//		Log.d("Gaika", "no support weapon infoId:" + info);
        return null;
    }
}

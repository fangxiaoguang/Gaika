package com.game.gaika.data.weapon;


import com.game.gaika.data.City;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;

import static com.game.gaika.data.ID.CITY_TYPE.*;
import static com.game.gaika.data.ID.ARMS_TYPE.*;

public class BattlePlane extends BaseWeapon {

	public BattlePlane(WeaponInfo pWeapInfo) {
		super(pWeapInfo);
		// TODO Auto-generated constructor stub
	}

	public BattlePlane(/*int pId,*/ int pX, int pY, int pExp, int pInfoId, boolean pTeamOut, boolean pSetOut, ID.TEAM_COLOR pTeamColor, boolean pMoveEnding) {
		super(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
		// TODO Auto-generated constructor stub
	}

	public BattlePlane(int pId, int pInfoId, int pX, int pY, boolean pTeamout, boolean pSetout, ID.TEAM_COLOR pTemaColor, int pLife, boolean pMoveEnding, int pExp,
			int pFuel, int pAmmoOnAir, int pAmmoOnGround, int pAmmoOnShip, int pAmmoOnSubmarine, int pMove) {
		super(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine,
				pMove);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canChooseArms() {
		GameDataManager gd = GameDataManager.getInstance();
		if (info.canChooseArms == false) {
			return false;
		} else {
			City city = gd.getCurrentChapter().getGameMap().getCityNodeFromXY(this);
			if (city != null && city.teamColor == teamColor && city.type == AIRPORT) {
				return true;
			}
//			BaseWeapon transporter = gd.getWeaponFromSetoutAndLifeTransporter(this, BLUE);
//			if(transporter != null){
//				return true;
//			}
		}
		return false;
	}

	@Override
	public void chooseArms(ID.ARMS_TYPE pArmsType) {

		GameDataManager gd = GameDataManager.getInstance();
		if (pArmsType == AIR) {
			info = gd.weapInfos.get(info.airArmsInfoId);
		} else if (pArmsType == GROUND) {
			info = gd.weapInfos.get(info.groundArmsInfoId);
		}

		this.ammoOnAir = info.ammoOnAir;
		this.ammoOnGround = info.ammoOnGround;
		this.ammoOnShip = info.ammoOnShip;
		this.ammoOnSubmarine = info.ammoOnSubmarine;
		return;
	}
}

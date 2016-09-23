package com.game.gaika.data.weapon;

import com.game.gaika.data.ID;

public class AttackHelicopter extends BaseWeapon {
	public AttackHelicopter(WeaponInfo pWeapInfo) {
		super(pWeapInfo);
		// TODO Auto-generated constructor stub
	}
	public AttackHelicopter(/*int pId,*/ int pX, int pY, int pExp, int pInfoId, boolean pTeamOut, boolean pSetOut,
							ID.TEAM_COLOR pTeamColor, boolean pMoveEnding) {
		super(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
		// TODO Auto-generated constructor stub
	}
	public AttackHelicopter(int pId, int pInfoId, int pX, int pY, boolean pTeamout, boolean pSetout, ID.TEAM_COLOR pTemaColor,
			int pLife, boolean pMoveEnding, int pExp, int pFuel, int pAmmoOnAir, int pAmmoOnGround, int pAmmoOnShip,
			int pAmmoOnSubmarine, int pMove) {
		super(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround,
				pAmmoOnShip, pAmmoOnSubmarine, pMove);
		// TODO Auto-generated constructor stub
	}
}

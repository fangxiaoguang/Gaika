package com.game.gaika.data.weapon;

import com.game.gaika.data.ID;
import com.game.gaika.data.Pair;

import java.util.ArrayList;
import java.util.List;


public class AircraftCarrier extends BaseWeapon {
	private List<BaseWeapon> passengers = new ArrayList<BaseWeapon>();
	private boolean showTransportFalg = false;

	public AircraftCarrier(WeaponInfo pWeapInfo) {
		super(pWeapInfo);
		// TODO Auto-generated constructor stub
	}

	public AircraftCarrier(/*int pId,*/ int pX, int pY, int pExp, int pInfoId, boolean pTeamOut, boolean pSetOut, ID.TEAM_COLOR pTeamColor, boolean pMoveEnding) {
		super(/*pId,*/ pX, pY, pExp, pInfoId, pTeamOut, pSetOut, pTeamColor, pMoveEnding);
		// TODO Auto-generated constructor stub
	}

	public AircraftCarrier(int pId, int pInfoId, int pX, int pY, boolean pTeamout, boolean pSetout, ID.TEAM_COLOR pTemaColor, int pLife, boolean pMoveEnding, int pExp,
			int pFuel, int pAmmoOnAir, int pAmmoOnGround, int pAmmoOnShip, int pAmmoOnSubmarine, int pMove) {
		super(pId, pInfoId, pX, pY, pTeamout, pSetout, pTemaColor, pLife, pMoveEnding, pExp, pFuel, pAmmoOnAir, pAmmoOnGround, pAmmoOnShip, pAmmoOnSubmarine,
				pMove);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean canTransport(BaseWeapon pWeapon) {
		if (passengers.size() >= 8) {
			return false;
		}

		if (pWeapon.info.aircraftCarrierTAL == true) {
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
		return true;
	}
}

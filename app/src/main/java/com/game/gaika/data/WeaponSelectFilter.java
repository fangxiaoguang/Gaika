package com.game.gaika.data;

import com.game.gaika.data.weapon.BaseWeapon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fangxg on 2015/6/28.
 */
public class WeaponSelectFilter {
    public enum CheckFlag {
        FLAG_COUNTRYS, FLAG_TEAM_OUT, FLAG_SET_OUT, FLAG_LIFE_IS_ZERO,
        FLAG_LIFE_NOT_ZERO, FLAG_TEAM_COLORS, FLAG_MAP_XY, FLAG_AMMO_NOT_ZERO_AIR, FLAG_AMMO_NOT_ZERO_GROUND,
        FLAG_AMMO_NOT_ZERO_SHIP, FLAG_AMMO_NOT_ZERO_SUBMARINE, FLAG_SELECTED,FLAG_WEAPON_TYPE, FLAG_WEAPON_TYPE_EX,
        FLAG_NOT_BE_TRANSPORT, FLAG_MOVE_ENDING, FLAG_FUEL_IS_ZERO, FLAG_IS_TRANSPORTER, FLAG_CAPTURE_NOT_ZERO
    }

    public Set<CheckFlag> checkFlags = new HashSet<>();

    public Set<Enum> countrys = new HashSet<>();

    //    public boolean isCheckTeamOut = false;
    public boolean teamOut;

    //    public boolean isCheckSetOut = false;
    public boolean setOut;

//    public boolean isCheckLifeIsZero = false;
//    public boolean isCheckLifeNotZero = false;
//    public int life;

    public Set<Enum> teamColors = new HashSet<>();

    public int mapX;
    public int mapY;

    public boolean selected;

    public Set<ID.WEAPON_TYPE> weaponTypes = new HashSet<>();
    public Set<ID.WEAPON_TYPE_EX> weaponTypeExs = new HashSet<>();

    public boolean moveEnding;

    public boolean isTransporter;

    // set set set set set......
    public void setCountrys(Set<Enum> pCountrys) {
        checkFlags.add(CheckFlag.FLAG_COUNTRYS);
        countrys = pCountrys;
    }

    public void addCountry(Enum pCountry) {
        checkFlags.add(CheckFlag.FLAG_COUNTRYS);
        countrys.add(pCountry);
    }
//
//    public void removeCountry(Enum pCountry) {
//        if (countrys != null) {
//            countrys.remove(pCountry);
//        }
//    }

    public void setTeamOut(boolean pTeamOut) {
        checkFlags.add(CheckFlag.FLAG_TEAM_OUT);
        teamOut = pTeamOut;
    }

//    public void removeTeamOut() {
//        isCheckTeamOut = false;
//    }

    public void setSetOut(boolean pSetOut) {
        checkFlags.add(CheckFlag.FLAG_SET_OUT);
        setOut = pSetOut;
    }

//    public void removeSetOut() {
//        isCheckSetOut = false;
//    }

    public void addTeamColor(ID.TEAM_COLOR pTeamColor) {
        checkFlags.add(CheckFlag.FLAG_TEAM_COLORS);
        teamColors.add(pTeamColor);
    }

    public void addTeamColor(Collection<ID.TEAM_COLOR> pTeamColors) {

        if (pTeamColors.size() > 0) {
            checkFlags.add(CheckFlag.FLAG_TEAM_COLORS);
            teamColors.addAll(pTeamColors);
        }
    }

    public void removeTeamColor(Enum pTeamColor) {
        if (teamColors != null) {
            teamColors.remove(pTeamColor);
        }
    }

    public void setLifeIsZero() {
        checkFlags.add(CheckFlag.FLAG_LIFE_IS_ZERO);
    }

//    public void removeLifeIsZero() {
//        isCheckLifeIsZero = false;
//    }

    public void setLifeNotZero() {
        checkFlags.add(CheckFlag.FLAG_LIFE_NOT_ZERO);
    }

//    public void removeLifeNotZero() {
//        isCheckLifeNotZero = false;
//    }

    public void setMapXY(int pMapX, int pMapY) {
        checkFlags.add(CheckFlag.FLAG_MAP_XY);
        mapX = pMapX;
        mapY = pMapY;
    }

    public void setAmmoNotZero(ID.WEAPON_TYPE_EX pTypeEx) {
        if (pTypeEx == ID.WEAPON_TYPE_EX.AIR) {
            checkFlags.add(CheckFlag.FLAG_AMMO_NOT_ZERO_AIR);
        }
        if (pTypeEx == ID.WEAPON_TYPE_EX.GROUND) {
            checkFlags.add(CheckFlag.FLAG_AMMO_NOT_ZERO_GROUND);
        }
        if (pTypeEx == ID.WEAPON_TYPE_EX.SHIP) {
            checkFlags.add(CheckFlag.FLAG_AMMO_NOT_ZERO_SHIP);
        }
        if (pTypeEx == ID.WEAPON_TYPE_EX.SUBMARINE) {
            checkFlags.add(CheckFlag.FLAG_AMMO_NOT_ZERO_SUBMARINE);
        }
    }

    public void setSetOutInBattlefield() {
        setTeamOut(true);
        setSetOut(true);
        setLifeNotZero();
        // setNotBeTransport();
    }

    public void setShowInBattlefield() {
        setTeamOut(true);
        setSetOut(true);
        setLifeNotZero();
        setNotBeTransport();
    }

    public void setSelected(boolean pSelected) {
        checkFlags.add(CheckFlag.FLAG_SELECTED);
        selected = pSelected;
    }

    public void addType(ID.WEAPON_TYPE pWeaponType) {
        checkFlags.add(CheckFlag.FLAG_WEAPON_TYPE);
        weaponTypes.add(pWeaponType);
    }

    public void addTypeEx(ID.WEAPON_TYPE_EX pWeaponTypeEx) {
        checkFlags.add(CheckFlag.FLAG_WEAPON_TYPE_EX);
        weaponTypeExs.add(pWeaponTypeEx);
    }



    public void setNotBeTransport() {
        checkFlags.add(CheckFlag.FLAG_NOT_BE_TRANSPORT);
    }

    public void setMoveEnding(boolean pMoveEnding) {
        checkFlags.add(CheckFlag.FLAG_MOVE_ENDING);
        moveEnding = pMoveEnding;

    }

    public void setFuelIsZero() {
        checkFlags.add(CheckFlag.FLAG_FUEL_IS_ZERO);
    }

    public void setIsTransporter(boolean pIsTransporter) {
        checkFlags.add(CheckFlag.FLAG_IS_TRANSPORTER);
        isTransporter = pIsTransporter;
    }

    public void setCaptureNotZero() {
        checkFlags.add(CheckFlag.FLAG_CAPTURE_NOT_ZERO);
    }

    public List<BaseWeapon> getWeapons() {
        GameDataManager gdm = GameDataManager.getInstance();
        List<BaseWeapon> weapons = new ArrayList<>();

        for (BaseWeapon weapon : gdm.weapons.values()) {
            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_COUNTRYS) == true) {
                if (countrys.contains(weapon.info.country) == false) {
                    continue;
                }
            }
            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_TEAM_OUT) == true) {
                if (weapon.teamOut != teamOut) {
                    continue;
                }
            }
            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_SET_OUT) == true) {
                if (weapon.setOut != setOut) {
                    continue;
                }
            }

            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_LIFE_IS_ZERO) == true) {
                if (weapon.life != 0) {
                    continue;
                }
            }
            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_LIFE_NOT_ZERO) == true) {

                if (weapon.life == 0) {
                    continue;
                }
            }

            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_TEAM_COLORS) == true) {

                if (teamColors.contains(weapon.teamColor) == false) {
                    continue;
                }
            }
            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_MAP_XY) == true) {

                if (mapX != weapon.getMapX() || mapY != weapon.getMapY()) {
                    continue;
                }
            }

            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_AMMO_NOT_ZERO_AIR) == true) {

                if (weapon.getAmmo(ID.WEAPON_TYPE_EX.AIR) == 0) {
                    continue;
                }
            }
            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_AMMO_NOT_ZERO_GROUND) == true) {

                if (weapon.getAmmo(ID.WEAPON_TYPE_EX.GROUND) == 0) {
                    continue;
                }
            }
            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_AMMO_NOT_ZERO_SHIP) == true) {

                if (weapon.getAmmo(ID.WEAPON_TYPE_EX.SHIP) == 0) {
                    continue;
                }
            }
            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_AMMO_NOT_ZERO_SUBMARINE) == true) {

                if (weapon.getAmmo(ID.WEAPON_TYPE_EX.SUBMARINE) == 0) {
                    continue;
                }
            }

            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_SELECTED) == true) {

                if (weapon.selected != selected) {
                    continue;
                }
            }
            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_WEAPON_TYPE) == true) {

                if (weaponTypes.contains(weapon.info.type) == false) {
                    continue;
                }
            }
            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_WEAPON_TYPE_EX) == true) {

                if (weaponTypeExs.contains(weapon.info.getTypeEx()) == false) {
                    continue;
                }
            }
            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_NOT_BE_TRANSPORT) == true) {

                if (weapon.transporter != null) {
                    continue;
                }
            }
            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_MOVE_ENDING) == true) {
                if (weapon.moveEnding != moveEnding) {
                    continue;
                }
            }

            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_FUEL_IS_ZERO) == true) {
                if (weapon.fuel != 0) {
                    continue;
                }
            }
            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_IS_TRANSPORTER) == true) {
                if (weapon.isTransporter() != isTransporter) {
                    continue;
                }
            }

            if (checkFlags.contains(WeaponSelectFilter.CheckFlag.FLAG_CAPTURE_NOT_ZERO) == true) {

                if (weapon.info.capture == 0) {
                    continue;
                }
            }
            weapons.add(weapon);
        }
        return weapons;
    }
}

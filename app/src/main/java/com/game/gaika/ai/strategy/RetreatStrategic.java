package com.game.gaika.ai.strategy;

import com.game.gaika.data.City;
import com.game.gaika.data.ColorBox;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameMap;
import com.game.gaika.data.IMapPoint;
import com.game.gaika.data.MapNode;
import com.game.gaika.data.weapon.BaseWeapon;

import java.util.List;
import java.util.Map;

/**
 * Created by fangxg on 2015/7/31.
 */
public class RetreatStrategic extends BaseStrategic {

    public RetreatStrategic(BaseWeapon pWeapon) {
        super(pWeapon);
    }

    @Override
    public void configCity() {
        GameDataManager gdm = GameDataManager.getInstance();
        GameMap map = gdm.getCurrentChapter().getGameMap();

        map.cleanGreenBox(weapon.teamColor);
        Map<Integer, ColorBox> weaponBoxs = map.getGreenBoxsLong(weapon, MAX_MOVE_FULL_SCENE);

        City minPathCity = null;
        for (City city : gdm.getCurrentChapter().getGameMap().citys.values()) {

            if (this.weapon.canRepair(city) == false) {
                continue;
            }

            if (weaponBoxs.get(city.x * 100 + city.y) == null) {
                continue;
            }

            if (weaponBoxs.get(city.x * 100 + city.y).weapon != null && weaponBoxs.get(city.x * 100 + city.y).weapon.id != weapon.id) {
                continue;
            }

            if (minPathCity == null) {
                minPathCity = city;
            } else {
                if (weaponBoxs.get(city.x * 100 + city.y).costFromStart < weaponBoxs.get(minPathCity.x * 100 + minPathCity.y).costFromStart) {
                    minPathCity = city;
                }
            }
        }

        setCity(minPathCity);
        return;
    }

    @Override
    public void configEnemy() {

    }

    @Override
    public void configDesc() {
        if (this.city == null) {
            setDesc(null);
            return;
        }

        GameDataManager gdm = GameDataManager.getInstance();
        GameMap map = gdm.getCurrentChapter().getGameMap();

        map.cleanGreenBox(weapon.teamColor);
        Map<Integer, ColorBox> CityBoxs = map.getGreenBoxsAllMap(city, weapon.info.getTypeEx()/*, weapon.teamColor, MAX_MOVE_FULL_SCENE*/);

        map.cleanGreenBox(weapon.teamColor);
        Map<Integer, ColorBox> CaptureBoxs = map.getGreenBoxs(weapon);

        ColorBox minWeaponBox = null;
        for (ColorBox weaponBox : CaptureBoxs.values()) {
            if (weaponBox.weapon == null || weaponBox.weapon.id == weapon.id) {
                if (minWeaponBox == null) {
                    minWeaponBox = weaponBox;
                } else {
                    if (CityBoxs.get(weaponBox.id).costFromStart < CityBoxs.get(minWeaponBox.id).costFromStart) {
                        minWeaponBox = weaponBox;
                    }
                }
            }
        }
        setDesc(minWeaponBox);
        return;
    }

    @Override
    public void configPath() {
        GameDataManager gdm = GameDataManager.getInstance();
        GameMap map = gdm.getCurrentChapter().getGameMap();

        if (city != null) {
            map.cleanGreenBox(weapon.teamColor);
            map.fullGreenBox(weapon);

            List<MapNode> p = map.getGreenBoxPath(weapon, this.getDesc());
            setPath(p);
            return;
        } else {
            setPath(null);
            return;
        }
    }

    @Override
    public void configCarmeMapPoint() {
        if (desc != null) {
            final int x = (weapon.x + desc.getMapX()) / 2;
            final int y = (weapon.y + desc.getMapY()) / 2;

            setCameraMapPoont(new IMapPoint() {
                                  @Override
                                  public int getMapX() {
                                      return x;
                                  }

                                  @Override
                                  public int getMapY() {
                                      return y;
                                  }
                              }
            );
        } else {
            final int x = weapon.x;
            final int y = weapon.y;

            setCameraMapPoont(new IMapPoint() {
                                  @Override
                                  public int getMapX() {
                                      return x;
                                  }

                                  @Override
                                  public int getMapY() {
                                      return y;
                                  }
                              }
            );
        }

    }

    @Override
    public void updateSelf() {
        cleanSelf();
    }
}

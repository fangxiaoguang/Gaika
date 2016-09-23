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
public class DefensiveStrategic extends BaseStrategic {


    public DefensiveStrategic(BaseWeapon pWeapon) {
        super(pWeapon);
    }


    @Override
    public void configCity() {

    }

    @Override
    public void configEnemy() {

    }

    @Override
    public void configDesc() {
        GameDataManager gdm = GameDataManager.getInstance();
        GameMap map = gdm.getCurrentChapter().getGameMap();

        City city = map.citys.get(weapon.x * 100 + weapon.y);
        if (city == null) {
            setDesc(null);
        } else {
            map.cleanGreenBox(weapon.teamColor);
            Map<Integer, ColorBox> cityBoxs = map.getGreenBoxs(weapon);
            ColorBox minPoint = null;
            for (ColorBox box : cityBoxs.values()) {
                if (map.getMapNodeFromXY(box).weapon == null && map.getCityNodeFromXY(box) == null) {
                    if (minPoint == null) {
                        minPoint = box;
                    } else {
                        if (box.costFromStart < minPoint.costFromStart) {
                            minPoint = box;
                        }
                    }
                }
            }
            setDesc(minPoint);
        }

    }

    @Override
    public void configPath() {
        GameDataManager gdm = GameDataManager.getInstance();
        GameMap map = gdm.getCurrentChapter().getGameMap();

        if (desc != null) {
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

    @Override
    public void updateSelf() {
        this.cleanSelf();
    }


}

package com.game.gaika.ai.strategy;

import android.util.Log;

import com.game.gaika.data.City;
import com.game.gaika.data.ColorBox;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameMap;
import com.game.gaika.data.ID;
import com.game.gaika.data.IMapPoint;
import com.game.gaika.data.MapNode;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by fangxg on 2015/7/31.
 */
public class CaptureStrategic extends BaseStrategic {

    public CaptureStrategic(BaseWeapon pWeapon) {
        super(pWeapon);
    }


    @Override
    public void configCity() {
        GameDataManager gdm = GameDataManager.getInstance();
        GameMap map = gdm.getCurrentChapter().getGameMap();

//        long l1 = System.currentTimeMillis();
        map.cleanGreenBox(weapon.teamColor);
        Map<Integer, ColorBox> boxs = map.getGreenBoxsLong(weapon, MAX_MOVE_FULL_SCENE);
//        long l2 = System.currentTimeMillis();
//        Log.d("gaika-ai", "AI.getStrategic() configCity = " + "  time(ms) = " + (l2 - l1));

        City minPathCity = null;
        Collection<ID.TEAM_COLOR> myTeamColor = gdm.getCurrentChapter().getAllianceTeamColors(weapon.teamColor);

        //无其他己方人员，有计划占领
       WeaponSelectFilter filter2 = new WeaponSelectFilter();
        filter2.setSetOutInBattlefield();
        filter2.addTeamColor(weapon.teamColor);
        filter2.setCaptureNotZero();
        List<BaseWeapon> chpturers = gdm.getWeapons(filter2);

//        long l3 = System.currentTimeMillis();

        for (City city : map.citys.values()) {
            if (myTeamColor.contains(city.teamColor) == true) {//城市 已经是己方颜色
                continue;
            }
            //无车辆在
            if (map.getMapNodeFromXY(city).weapon != null && map.getMapNodeFromXY(city).weapon.id != weapon.id) {//有其他单位占位城市中
                continue;
            }
           /* //无其他己方人员，有计划占领
            WeaponSelectFilter filter2 = new WeaponSelectFilter();
            filter2.setSetOutInBattlefield();
            filter2.addTeamColor(weapon.teamColor);
            filter2.setCaptureNotZero();
            List<BaseWeapon> chpturers = gdm.getWeapons(filter2);*/


            boolean bHaveChapturer = false;
            for (BaseWeapon chpturer : chpturers) {
                if (chpturer.getStrategic() != null && chpturer.getStrategic().getCity() != null && chpturer.getStrategic().getCity().id == city.id) {
                    bHaveChapturer = true;
                }
            }
            if (bHaveChapturer == true) {
                continue;
            }

            //本单位无法到达的城市位置
            if (boxs.get(city.x * 100 + city.y) == null) {
                continue;
            }


            if (minPathCity == null) {
                minPathCity = city;
            } else {
                if (boxs.get(city.x * 100 + city.y).costFromStart < boxs.get(minPathCity.x * 100 + minPathCity.y).costFromStart) {
                    minPathCity = city;
                }
            }
        }//end for

//        long l4 = System.currentTimeMillis();
//        Log.d("gaika-ai", "AI.getStrategic() for(;;;) = " + "  time(ms) = " + (l4 - l3));
        setCity(minPathCity);
        return;
    }

    @Override
    public void configEnemy() {
        setEnemy(null);
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

        ColorBox minCapthreBox = null;
        for (ColorBox capthreBox : CaptureBoxs.values()) {
            if (capthreBox.weapon == null || capthreBox.weapon.id == weapon.id) {
                if (minCapthreBox == null) {
                    minCapthreBox = capthreBox;
                } else {
                    if (CityBoxs.get(capthreBox.id).costFromStart < CityBoxs.get(minCapthreBox.id).costFromStart) {
                        minCapthreBox = capthreBox;
                    }
                }
            }
        }

        setDesc(minCapthreBox);
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
        if(desc != null){
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
        }else{
            final int x = weapon.x ;
            final int y = weapon.y ;

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

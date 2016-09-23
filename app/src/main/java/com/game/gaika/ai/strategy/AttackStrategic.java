package com.game.gaika.ai.strategy;

import com.game.gaika.data.ColorBox;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameMap;
import com.game.gaika.data.ID;
import com.game.gaika.data.IMapPoint;
import com.game.gaika.data.MapNode;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fangxg on 2015/7/31.
 */
public class AttackStrategic extends BaseStrategic {


    public AttackStrategic(BaseWeapon pWeapon) {
        super(pWeapon);
    }


    @Override
    public void configCity() {

    }

    @Override
    public void configEnemy() {
        GameDataManager gdm = GameDataManager.getInstance();
        GameMap map = gdm.getCurrentChapter().getGameMap();

        List<BaseWeapon> canAttackEnemys = new ArrayList<>();
        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.setShowInBattlefield();
        filter.addTeamColor(gdm.getCurrentChapter().getEnemyTeamColors(this.weapon.teamColor));
        List<BaseWeapon> enemys = gdm.getWeapons(filter);

        map.cleanGreenBox(weapon.teamColor);
        Map<Integer, ColorBox> weaponBoxs = map.getGreenBoxsAllMap(weapon, weapon.info.getTypeEx()/*, gdm.getCurrentChapter().getEnemyTeamColors(weapon.teamColor).iterator().next(), 100000.f*/);
        BaseWeapon minPathEney = null;

        gdm.cleanAdvantageFlag();
        gdm.computeEnemyWeaponAdvantage(this.weapon);
        for (BaseWeapon enemy : enemys) {
            if (weapon.info.getRange(enemy.info.getTypeEx()).getFar() > 1) { //遠程攻擊武器
                if (enemy.advantage == ID.ADVANTAGE.NON) {
                    continue; //canAttackEnemys.add(enemy);
                }
            } else {
                if (enemy.advantage != ID.ADVANTAGE.EASY && enemy.advantage != ID.ADVANTAGE.EASY_GREY
                        && enemy.advantage != ID.ADVANTAGE.NORMAL && enemy.advantage != ID.ADVANTAGE.NORMAL_GREY) {
                    continue; //canAttackEnemys.add(enemy);
                }
            }

            if (minPathEney == null) {
                minPathEney = enemy;
            } else {
                ColorBox b1 = weaponBoxs.get(enemy.x * 100 + enemy.y);
                ColorBox b2 = weaponBoxs.get(minPathEney.x * 100 + minPathEney.y);
                if (b1.costFromStart < b2.costFromStart) {
                    minPathEney = enemy;
                }

            }
        }
        gdm.cleanAdvantageFlag();

        setEnemy(minPathEney);
        return;
    }


    @Override
    public void configDesc() {
        if (this.enemy == null) {
            setDesc(null);
            return;
        }

        if (this.weapon.canAttack(this.enemy)) {
            setDesc(null);
            return;
        }

        ColorBox minDesc = null;

        GameDataManager gdm = GameDataManager.getInstance();
        GameMap map = gdm.getCurrentChapter().getGameMap();

        map.cleanGreenBox(weapon.teamColor);
        Map<Integer, ColorBox> weaponBoxs = map.getGreenBoxs(weapon);


        int backX = weapon.x;
        int backY = weapon.y;
        for (ColorBox box : weaponBoxs.values()) {
            weapon.x = box.x;
            weapon.y = box.y;

            if ((box.weapon == null || box.weapon.id == weapon.id) && weapon.canAttack(enemy)) {
                if (minDesc == null) {
                    minDesc = box;
                } else {
                    if (weaponBoxs.get(box.x * 100 + box.y).costFromStart < weaponBoxs.get(minDesc.x * 100 + minDesc.y).costFromStart) {
                        minDesc = box;
                    }
                }
            }
        }
        weapon.x = backX;
        weapon.y = backY;

        if (minDesc != null) {
            setDesc(minDesc);
            return;
        }

        map.cleanGreenBox(weapon.teamColor);
        Map<Integer, ColorBox> enemyBosx = map.getGreenBoxsAllMap(enemy, weapon.info.getTypeEx()/*, weapon.teamColor, MAX_MOVE_FULL_SCENE*/);

        for (ColorBox weaponBox : weaponBoxs.values()) {
            if (weaponBox.weapon == null || weaponBox.weapon.id == weapon.id) {
                if (minDesc == null) {
                    minDesc = weaponBox;
                } else {
                    if (enemyBosx.get(weaponBox.x * 100 + weaponBox.y).costFromStart < enemyBosx.get(minDesc.x * 100 + minDesc.y).costFromStart) {
                        minDesc = weaponBox;
                    }
                }
            }
        }

        setDesc(minDesc);
        return;
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

        if (desc == null) {
            if(enemy == null){
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
            }else {
                final int x = (weapon.x + enemy.getMapX()) / 2;
                final int y = (weapon.y + enemy.getMapY()) / 2;

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

        } else {
            int backX = weapon.x;
            int backY = weapon.y;
            weapon.x = desc.getMapX();
            weapon.y = desc.getMapY();
            if (weapon.canAttack(enemy) == true) {


                weapon.x = backX;
                weapon.y = backY;

                final int x = (weapon.x + desc.getMapX() + enemy.getMapX()) / 3;
                final int y = (weapon.y + desc.getMapY() + enemy.getMapY()) / 3;

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
                weapon.x = backX;
                weapon.y = backY;
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
            }
        }
    }


    @Override
    public void updateSelf() {
        cleanSelf();
    }
}

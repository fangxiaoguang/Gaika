package com.game.gaika.ai.strategy;

import android.util.Log;

import com.game.gaika.data.City;
import com.game.gaika.data.IMapPoint;
import com.game.gaika.data.MapNode;
import com.game.gaika.data.weapon.BaseWeapon;

import java.util.List;

/**
 * Created by fangxg on 2015/7/31.
 */
public abstract class BaseStrategic {

    protected  static final float MAX_MOVE_FULL_SCENE = Float.MAX_VALUE;

    protected BaseWeapon weapon;
    protected City city;
    protected BaseWeapon enemy;
    protected IMapPoint desc;
    private List<MapNode> path;



    private IMapPoint cameraMapPoont;

    public BaseStrategic(BaseWeapon pWeapon) {
        weapon = pWeapon;
    }



    public void setWeapon(BaseWeapon weapon) {
        this.weapon = weapon;
    }

    public void setCity(City pCity) {
        city = pCity;
    }
    public City getCity() {
        return city;
    }

    public void setEnemy(BaseWeapon pEnemy){
        enemy = pEnemy;
    }
    public BaseWeapon getEnemy() {
        return enemy;
    }

    public void setDesc(IMapPoint pDesc) {
        desc = pDesc;
    }
    public IMapPoint getDesc() {
        return desc;
    }

    public void setPath(List<MapNode> pPath) {
        path = pPath;
    }
    public List<MapNode> getPath() {
        return path;
    }

    public IMapPoint getCameraMapPoont() {
        return cameraMapPoont;
    }
    public void setCameraMapPoont(IMapPoint cameraMapPoont) {
        this.cameraMapPoont = cameraMapPoont;
    }
    public abstract void updateSelf();

    protected void cleanSelf() {
        weapon.setStrategic(null);
    }

    public abstract void configCity();
    public abstract void configEnemy();
    public abstract void configDesc();
    public abstract void configPath();
    public abstract void configCarmeMapPoint();

    public  final  void build(){
        long l1 = System.currentTimeMillis();
        configCity();
        long l2 = System.currentTimeMillis();
        configEnemy();
        long l3 = System.currentTimeMillis();
        configDesc();
        long l4 = System.currentTimeMillis();
//        configPath();
        long l5 = System.currentTimeMillis();
        configCarmeMapPoint();
        long l6 = System.currentTimeMillis();
//        Log.d("gaika-ai", "AI.getStrategic() aiWeapon = " + weapon.id + ":" + weapon.info.name + "  type = " + this.getClass().getName() + "  time(ms) = t1: " + (l2 - l1)  + "  t2: " + (l3 - l2)  + "  t3: " + (l4 - l3)  + "  t4: " + (l5 - l4)  + "  t5: " + (l6 - l5));
        Log.d("gaika-ai", "AI.getStrategic() aiWeapon = " + weapon.id + ":" + weapon.info.name + "  type = " + this.getClass().getName() + "  time(ms) =  " + (l6 - l1));
    }

    @Override
    public String toString() {
        return "weapon:" + weapon.info.name + "(" + weapon.id + ")"
                + "  desc:" + desc.getMapX() + "," + desc.getMapY()
                + "  city:" + city.name + "(" + city.id + ")"
                + "weapon:" + enemy.info.name + "(" + enemy.id + ")";
    }

}

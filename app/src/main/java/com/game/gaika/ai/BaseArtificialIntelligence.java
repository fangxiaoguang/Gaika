package com.game.gaika.ai;

import android.util.Log;

import com.game.gaika.ai.strategy.AttackStrategic;
import com.game.gaika.ai.strategy.BaseStrategic;
import com.game.gaika.ai.strategy.CaptureStrategic;
import com.game.gaika.ai.strategy.DefensiveStrategic;
import com.game.gaika.ai.strategy.RetreatStrategic;
import com.game.gaika.data.City;
import com.game.gaika.data.ColorBox;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameMap;
import com.game.gaika.data.ID;
import com.game.gaika.data.MapNode;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by fangxg on 2015/7/31.
 */
public class BaseArtificialIntelligence {

    public static BaseStrategic getStrategic(BaseWeapon pWeapon){
        GameDataManager gdm = GameDataManager.getInstance();

        if (pWeapon.info.capture > 0) {
            if (pWeapon.life > 666) {

//                long l1 = System.currentTimeMillis();

                CaptureStrategic captureStrategic = new CaptureStrategic(pWeapon);
                captureStrategic.build();

//                long l2 = System.currentTimeMillis();
//                Log.d("gaika-ai", "AI.getStrategic() aiWeapon = " + pWeapon.id + ":" + pWeapon.info.name + "  type = " + captureStrategic.getClass().getName() + "  time(ms) = " + (l2 - l1));


                if(captureStrategic.getCity() != null){
                    return captureStrategic;
                }else {
                    DefensiveStrategic defensiveStrategic = new DefensiveStrategic(pWeapon);
                    defensiveStrategic.build();
                    return defensiveStrategic;
                }
            } else {
                RetreatStrategic  defensiveStrategic =new RetreatStrategic(pWeapon);
                defensiveStrategic.build();
                return defensiveStrategic;
            }
        } else {
            if (pWeapon.life <= 333 || pWeapon.getMainAmmon() <= 1 || pWeapon.fuel < pWeapon.info.fuel / 4) {
                RetreatStrategic  retreatStrategic =new RetreatStrategic(pWeapon);
                retreatStrategic.build();
                return retreatStrategic;
            } else if (pWeapon.life >= 666 && pWeapon.getMainAmmon() >= 2 && pWeapon.fuel > pWeapon.info.fuel / 2) {
                AttackStrategic attackStrategic = new AttackStrategic(pWeapon);

                attackStrategic.build();
                if(attackStrategic.getEnemy() != null){
                    return  attackStrategic;
                }else {
                    DefensiveStrategic defensiveStrategic = new DefensiveStrategic(pWeapon);
                    defensiveStrategic.build();
                    return defensiveStrategic;
                }
            } else {
                RetreatStrategic  defensiveStrategic =new RetreatStrategic(pWeapon);
                defensiveStrategic.build();
                return defensiveStrategic;
            }
        }
    }
}

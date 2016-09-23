package com.game.gaika.sprite;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.data.City;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.flyweight.NormalFlyweight;
import com.game.gaika.flyweight.NumberFlyweight;

/**
 * Created by fangxg on 2015/6/23.
 */
public class OutSetWeaponSprite extends BaseSprite {


    public OutSetWeaponSprite(float pX, float pY, BaseWeapon pBaseWeapon, City pCity, TouchMessage pTouchMessage) {
        super(pX, pY);


        GameDataManager gdm = GameDataManager.getInstance();

        NormalFlyweight backFlyweight = new NormalFlyweight(0.0f, 0.0f, "invisible67_46");

        NormalFlyweight unit02Flyweight = new NormalFlyweight(0, 12, "unit02", pBaseWeapon.info.texIndex);
        backFlyweight.addChildFlyweight(unit02Flyweight);

        NormalFlyweight nameFlyweight = new NormalFlyweight(0, 0, "weap_nm1", pBaseWeapon.info.texIndex);
        backFlyweight.addChildFlyweight(nameFlyweight);

        NormalFlyweight flagFlyweight = new NormalFlyweight(0, 12, "flag01", pBaseWeapon.info.country.ordinal());
        backFlyweight.addChildFlyweight(flagFlyweight);

        if (pBaseWeapon.life <= 0) {
            NormalFlyweight breachFlyweight = new NormalFlyweight(40, 12, "unit_des", 0);
            backFlyweight.addChildFlyweight(breachFlyweight);
        }

        NormalFlyweight typeFlyweight = new NormalFlyweight(0, 33, "font_typ", pBaseWeapon.info.type.ordinal());
        backFlyweight.addChildFlyweight(typeFlyweight);

        if (pBaseWeapon.life <= 0) {// 出击不可
            NormalFlyweight numberFlyweight = new NormalFlyweight(0, 46, "unitmak1", 2);
            backFlyweight.addChildFlyweight(numberFlyweight);

        } else {
            if (pBaseWeapon.setOut == true) {// 出击中
                NormalFlyweight numberFlyweight = new NormalFlyweight(0, 46, "unitmak1", 0);
                backFlyweight.addChildFlyweight(numberFlyweight);
            } else {
                if (pBaseWeapon.info.canSetoutByCityType(pCity.type) == false) {// 配置不可
                    NormalFlyweight numberFlyweight = new NormalFlyweight(0, 46, "unitmak1", 1);
                    backFlyweight.addChildFlyweight(numberFlyweight);
                } else {
                    if (pBaseWeapon.info.supply <= gdm.getSupply(ID.TEAM_COLOR.BLUE)) {
                        NumberFlyweight numberFlyweight = new NumberFlyweight(59, 46, false, "font03", pBaseWeapon.info.supply, 0, 0);
                        backFlyweight.addChildFlyweight(numberFlyweight);
                    } else {
                        NumberFlyweight numberFlyweight = new NumberFlyweight(59, 46, false, "font03", pBaseWeapon.info.supply, 0, 22);
                        backFlyweight.addChildFlyweight(numberFlyweight);
                    }
                }
            }
        }


        setFlyweight(backFlyweight);
        setTouchMessage(pTouchMessage);

    }


}

package com.game.gaika.sprite;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.flyweight.NormalFlyweight;

/**
 * Created by fangxg on 2015/6/28.
 */
public class TeamBuildWeaponSprite extends BaseSprite {


    public TeamBuildWeaponSprite(float pX, float pY, BaseWeapon pBaseWeapon,  TouchMessage pTouchMessage) {
        super(pX, pY);

        long L1 = System.currentTimeMillis();

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



        setFlyweight(backFlyweight);
        setTouchMessage(pTouchMessage);

    }

}

package com.game.gaika.sprite;

import com.game.frame.sprite.BaseSprite;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.weapon.WeaponFactory;
import com.game.frame.flyweight.NormalFlyweight;
import com.game.frame.flyweight.NumberFlyweight;
import com.game.frame.flyweight.TextFlyweight;
import com.game.frame.texture.TexRegionManager;

/**
 * Created by fangxg on 2015/6/27.
 */
public class WeaponInfoSprite extends BaseSprite {
    public static float INFO_CARD_ALPHA = 0.75f;

    public WeaponInfoSprite(int pInfoID,float pAlpha){
        this(WeaponFactory.getInstance().newWeapon(GameDataManager.getInstance().weapInfos.get(pInfoID)), pAlpha);
    }
    public WeaponInfoSprite(BaseWeapon pWeapNode, float pAlpha) {

        super();
        GameDataManager gdm = GameDataManager.getInstance();
        //float pAlpha = pAlpha;

        //NormalSprite bkSprite = new NormalSprite(0.0f, 0.0f, "info_bg");
        NormalFlyweight bkFlyweight = new NormalFlyweight(0.0f, 0.0f, "info_bg");
        setFlyweight(bkFlyweight);

        NormalFlyweight wnFlyweight = new NormalFlyweight(0.0f, 0.0f, "weap_wn1");
        wnFlyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(wnFlyweight);

        NormalFlyweight grFlyweight = new NormalFlyweight(13.0f, 12.0f, "weap_gr1", pWeapNode.info.texIndex);
        grFlyweight.setAlpha(pAlpha - 0.2f);
        bkFlyweight.addChildFlyweight(grFlyweight);

        TextFlyweight textFlyweight = new TextFlyweight(13.0f, 17.0f, true, pWeapNode.info.name, TexRegionManager.getInstance().getFont12());
        bkFlyweight.addChildFlyweight(textFlyweight);

        TextFlyweight text2Flyweight = new TextFlyweight(13.0f, 35.0f, true, pWeapNode.info.type.toDescribeString(), TexRegionManager.getInstance().getFont12());
        bkFlyweight.addChildFlyweight(text2Flyweight);

        NormalFlyweight flagFlyweight = new NormalFlyweight(169.0f, 13.0f, "flag03", pWeapNode.info.country.ordinal());
        flagFlyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(flagFlyweight);

        NormalFlyweight lvFlyweight = new NormalFlyweight(50.0f, 105.0f, "font04", pWeapNode.getLv());
        lvFlyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(lvFlyweight);

        NormalFlyweight lifeFlyweight = new NormalFlyweight(140, 90, "font05", pWeapNode.getLifeEx());
        lifeFlyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(lifeFlyweight);

        float fPasento = ((float) (pWeapNode.exp % BaseWeapon.EXPERINCE_PER_LV)) / ((float) BaseWeapon.EXPERINCE_PER_LV);
        if (fPasento > 1.0f) {
            fPasento = 1.0f;
        }
        NormalFlyweight expBarFlyweight = new NormalFlyweight(34, 123, "experi01", 0);
        expBarFlyweight.setScaleXY(fPasento, 1.0f);
        expBarFlyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(expBarFlyweight);

        int iOffsetIdx = 0;
        int iFuelbarIdx = 0;
        if (pWeapNode.fuel < pWeapNode.info.fuel * 0.2f) {
            iOffsetIdx = 22;
            iFuelbarIdx = 2;
        } else if (pWeapNode.fuel < pWeapNode.info.fuel * 0.5f) {
            iOffsetIdx = 11;
            iFuelbarIdx = 1;
        }

        NumberFlyweight numberFlyweight1 = new NumberFlyweight(81 - 12 * 4, 135, true, "font03", pWeapNode.fuel, 3, iOffsetIdx, pAlpha);
        bkFlyweight.addChildFlyweight(numberFlyweight1);

        NumberFlyweight numberFlyweight2 = new NumberFlyweight(80, 135, true, "font03", pWeapNode.info.fuel, 3, iOffsetIdx, pAlpha);
        bkFlyweight.addChildFlyweight(numberFlyweight2);

        float fPasento2 = ((float) pWeapNode.fuel) / ((float) pWeapNode.info.fuel);
        if (fPasento2 > 1.0f) {
            fPasento2 = 1.0f;
        }
        NormalFlyweight fuelBarFlyweight = new NormalFlyweight(140 - 9, 90 + 35 + 8, "fuelbar1", iFuelbarIdx);
        fuelBarFlyweight.setScaleXY(fPasento2, 1.0f);
        fuelBarFlyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(fuelBarFlyweight);

        NormalFlyweight a1Flyweight = new NormalFlyweight(45 + 41 * 0, 164, "font06", pWeapNode.getAttack(ID.WEAPON_TYPE_EX.AIR));
        a1Flyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(a1Flyweight);

        NormalFlyweight a2Flyweight = new NormalFlyweight(45 + 41 * 1, 164, "font06", pWeapNode.getAttack(ID.WEAPON_TYPE_EX.GROUND));
        a2Flyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(a2Flyweight);

        NormalFlyweight a3Flyweight = new NormalFlyweight(45 + 41 * 2, 164, "font06", pWeapNode.getAttack(ID.WEAPON_TYPE_EX.SHIP));
        a3Flyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(a3Flyweight);

        NormalFlyweight a4Flyweight = new NormalFlyweight(45 + 41 * 3, 164, "font06", pWeapNode.getAttack(ID.WEAPON_TYPE_EX.SUBMARINE));
        a4Flyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(a4Flyweight);

        NormalFlyweight d1Flyweight = new NormalFlyweight(45 + 41 * 0, 187, "font06", pWeapNode.getDefense(ID.WEAPON_TYPE_EX.AIR));
        d1Flyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(d1Flyweight);

        NormalFlyweight d2Flyweight = new NormalFlyweight(45 + 41 * 1, 187, "font06", pWeapNode.getDefense(ID.WEAPON_TYPE_EX.GROUND));
        d2Flyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(d2Flyweight);

        NormalFlyweight d3Flyweight = new NormalFlyweight(45 + 41 * 2, 187, "font06", pWeapNode.getDefense(ID.WEAPON_TYPE_EX.SHIP));
        d3Flyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(d3Flyweight);

        NormalFlyweight d4Flyweight = new NormalFlyweight(45 + 41 * 3, 187, "font06", pWeapNode.getDefense(ID.WEAPON_TYPE_EX.SUBMARINE));
        d4Flyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(d4Flyweight);

        if (pWeapNode.getAttack(ID.WEAPON_TYPE_EX.AIR) == 0) {
            NormalFlyweight tempSprite = new NormalFlyweight(70 + 41 * 0 - 12, 210, "font03", 32);
            tempSprite.setAlpha(pAlpha);
            bkFlyweight.addChildFlyweight(tempSprite);
        } else {
            NumberFlyweight tempSprite = new NumberFlyweight(70 + 41 * 0, 210, false, "font03", pWeapNode.ammoOnAir, 0, 0, pAlpha);
            bkFlyweight.addChildFlyweight(tempSprite);
        }

        if (pWeapNode.getAttack(ID.WEAPON_TYPE_EX.GROUND) == 0) {
            NormalFlyweight tempSprite = new NormalFlyweight(70 + 41 * 1 - 12, 210, "font03", 32);
            tempSprite.setAlpha(pAlpha);
            bkFlyweight.addChildFlyweight(tempSprite);
        } else {
            NumberFlyweight tempSprite = new NumberFlyweight(70 + 41 * 1, 210, false, "font03", pWeapNode.ammoOnGround, 0, 0, pAlpha);
            bkFlyweight.addChildFlyweight(tempSprite);
        }

        if (pWeapNode.getAttack(ID.WEAPON_TYPE_EX.SHIP) == 0) {
            NormalFlyweight tempSprite = new NormalFlyweight(70 + 41 * 2 - 12, 210, "font03", 32);
            tempSprite.setAlpha(pAlpha);
            bkFlyweight.addChildFlyweight(tempSprite);
        } else {
            NumberFlyweight tempSprite = new NumberFlyweight(70 + 41 * 2, 210, false, "font03", pWeapNode.ammoOnShip, 0, 0, pAlpha);
            bkFlyweight.addChildFlyweight(tempSprite);
        }

        if (pWeapNode.getAttack(ID.WEAPON_TYPE_EX.SUBMARINE) == 0) {
            NormalFlyweight tempSprite = new NormalFlyweight(70 + 41 * 3 - 12, 210, "font03", 32);
            tempSprite.setAlpha(pAlpha);
            bkFlyweight.addChildFlyweight(tempSprite);
        } else {
            NumberFlyweight tempSprite = new NumberFlyweight(70 + 41 * 3, 210, false, "font03", pWeapNode.ammoOnSubmarine, 0, 0, pAlpha);
            bkFlyweight.addChildFlyweight(tempSprite);
        }

        int detailCount = 0;
        for (String detail : pWeapNode.info.getDetails()) {
            NormalFlyweight normalFlyweight = new NormalFlyweight(0, 225 + 14 * detailCount, "weap_sp1", 0);
            TextFlyweight textFlyweight1 = new TextFlyweight(10, 0, true, detail, TexRegionManager.getInstance().getFont12());

            if (pAlpha <= 0.8f) {
                textFlyweight1.setAlpha(pAlpha + 0.2f);
            }
            normalFlyweight.addChildFlyweight(textFlyweight1);
            normalFlyweight.setAlpha(pAlpha);
            bkFlyweight.addChildFlyweight(normalFlyweight);
            detailCount++;
        }

        NormalFlyweight wn2Flyweight = new NormalFlyweight(0, 225 + 14 * detailCount, "weap_wn2", 0);
        wn2Flyweight.setAlpha(pAlpha);
        bkFlyweight.addChildFlyweight(wn2Flyweight);

        NumberFlyweight moveFlyweight = new NumberFlyweight(100 - 16 * 2, 164 + 20 + 20 + 40 - 14 + 14 * detailCount, true, "font02", pWeapNode.move, 2, 0, pAlpha);
        bkFlyweight.addChildFlyweight(moveFlyweight);

        NumberFlyweight supplyFlyweight = new NumberFlyweight(70 + 41 * 3, 164 + 17 + 40 + 40 - 14 + 14 * detailCount, false, "font02", pWeapNode.info.supply, 2, 0, pAlpha);
        bkFlyweight.addChildFlyweight(supplyFlyweight);
    }
}

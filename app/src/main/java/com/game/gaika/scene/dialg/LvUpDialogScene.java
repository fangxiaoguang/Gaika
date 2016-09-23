package com.game.gaika.scene.dialg;

import com.game.frame.scene.dialg.DialogScene;
import com.game.frame.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.frame.scene.BaseLogicScene;
import com.game.gaika.sprite.AnimeSprite;
import com.game.frame.sprite.DelaySprite;
import com.game.frame.sprite.NormalSprite;
import com.game.frame.sprite.TextSprite;
import com.game.frame.texture.TexRegionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fangxg on 2015/7/26.
 */
public class LvUpDialogScene extends DialogScene {


    public LvUpDialogScene(int pWeaponID, BaseLogicScene pParentScene) {
        super(ID.SCENE_ID.LV_UP_DIALOG, 0.0f, 0.0f, 406.0f, 142.0f, pParentScene, EPointModel.POINT_MODEL_CENTER);
        GameDataManager gdm = GameDataManager.getInstance();
        BaseWeapon weapon = gdm.weapons.get(pWeaponID);

        NormalSprite bkSprite = new NormalSprite(0.0f, 0.0f, "dialg2");
        addSprite(bkSprite);

        NormalSprite nmSprite = new NormalSprite(48, 40 + 5, "weap_nm1", weapon.info.texIndex);
        addSprite(nmSprite);
        NormalSprite typeSprite = new NormalSprite(48, 50 + 5, "font_typ", weapon.info.type.ordinal());
        addSprite(typeSprite);
        NormalSprite unitSprite = new NormalSprite(48, 60 + 5, "unit02", weapon.info.texIndex);
        addSprite(unitSprite);
        NormalSprite flagSprite = new NormalSprite(48, 90 - 5 + 5, "flag01", weapon.info.country.ordinal());
        addSprite(flagSprite);
        NormalSprite lifeExSprite = new NormalSprite(93, 90 - 5 + 5, "font01", weapon.getLifeEx());
        addSprite(lifeExSprite);
        NormalSprite preLvSprite = new NormalSprite(48, 95 - 5 + 5 + 16 - 3, "font_lv1", weapon.getPreLv() - 1);
        addSprite(preLvSprite);
        NormalSprite lvSprite = new NormalSprite(90, 95 - 5 + 5 + 16 - 3, "font_lv1", weapon.getLv() - 1);
        addSprite(lvSprite);

        String s1 = "BLUE";
        TextSprite textSprite1 = new TextSprite(135, 45, true, s1 + "军作战单位" + weapon.info.name, TexRegionManager.getInstance().getFont16());
        addSprite(textSprite1);

        TextSprite textSprite2 = new TextSprite(135, 65, true, "级别上升。", TexRegionManager.getInstance().getFont16());
        addSprite(textSprite2);

        List<Integer> index1 = new ArrayList<Integer>();
        List<Integer> index2 = new ArrayList<Integer>();
        List<Integer> index3 = new ArrayList<Integer>();
        if (weapon.getAttack(ID.WEAPON_TYPE_EX.AIR) > weapon.getPreAttack(ID.WEAPON_TYPE_EX.AIR)) {
            index1.add(0);
            index2.add(weapon.getPreAttack(ID.WEAPON_TYPE_EX.AIR) - 1);
            index3.add(weapon.getAttack(ID.WEAPON_TYPE_EX.AIR) - 1);
        }
        if (weapon.getAttack(ID.WEAPON_TYPE_EX.GROUND) > weapon.getPreAttack(ID.WEAPON_TYPE_EX.GROUND)) {
            index1.add(1);
            index2.add(weapon.getPreAttack(ID.WEAPON_TYPE_EX.GROUND) - 1);
            index3.add(weapon.getAttack(ID.WEAPON_TYPE_EX.GROUND) - 1);
        }
        if (weapon.getAttack(ID.WEAPON_TYPE_EX.SHIP) > weapon.getPreAttack(ID.WEAPON_TYPE_EX.SHIP)) {
            index1.add(2);
            index2.add(weapon.getPreAttack(ID.WEAPON_TYPE_EX.SHIP) - 1);
            index3.add(weapon.getAttack(ID.WEAPON_TYPE_EX.SHIP) - 1);
        }
        if (weapon.getAttack(ID.WEAPON_TYPE_EX.SUBMARINE) > weapon.getPreAttack(ID.WEAPON_TYPE_EX.SUBMARINE)) {
            index1.add(3);
            index2.add(weapon.getPreAttack(ID.WEAPON_TYPE_EX.SUBMARINE) - 1);
            index3.add(weapon.getAttack(ID.WEAPON_TYPE_EX.SUBMARINE) - 1);
        }
        if (weapon.getDefense(ID.WEAPON_TYPE_EX.AIR) > weapon.getPreDefense(ID.WEAPON_TYPE_EX.AIR)) {
            index1.add(4);
            index2.add(weapon.getPreDefense(ID.WEAPON_TYPE_EX.AIR) - 1);
            index3.add(weapon.getDefense(ID.WEAPON_TYPE_EX.AIR) - 1);
        }
        if (weapon.getDefense(ID.WEAPON_TYPE_EX.GROUND) > weapon.getPreDefense(ID.WEAPON_TYPE_EX.GROUND)) {
            index1.add(5);
            index2.add(weapon.getPreDefense(ID.WEAPON_TYPE_EX.GROUND) - 1);
            index3.add(weapon.getDefense(ID.WEAPON_TYPE_EX.GROUND) - 1);
        }
        if (weapon.getDefense(ID.WEAPON_TYPE_EX.SHIP) > weapon.getPreDefense(ID.WEAPON_TYPE_EX.SHIP)) {
            index1.add(6);
            index2.add(weapon.getPreDefense(ID.WEAPON_TYPE_EX.SHIP) - 1);
            index3.add(weapon.getDefense(ID.WEAPON_TYPE_EX.SHIP) - 1);
        }
        if (weapon.getDefense(ID.WEAPON_TYPE_EX.SUBMARINE) > weapon.getPreDefense(ID.WEAPON_TYPE_EX.SUBMARINE)) {
            index1.add(7);
            index2.add(weapon.getPreDefense(ID.WEAPON_TYPE_EX.SUBMARINE) - 1);
            index3.add(weapon.getDefense(ID.WEAPON_TYPE_EX.SUBMARINE) - 1);
        }

        int[] i1 = new int[index1.size()];
        int[] i2 = new int[index1.size()];
        int[] i3 = new int[index1.size()];
        for (int i = 0; i < index1.size(); i++) {
            i1[i] = index1.get(i);
            i2[i] = index2.get(i);
            i3[i] = index3.get(i);
        }

        if (i1.length > 0) {
            AnimeSprite animeSprite1 = new AnimeSprite(135, 100, "dialg2bt", i1, (int)(GameSetup.DELAY_LV_UP_S * 1000));
            addSprite(animeSprite1);
            AnimeSprite animeSprite2 = new AnimeSprite(240, 97, "font06", i2,  (int)(GameSetup.DELAY_LV_UP_S * 1000));
            addSprite(animeSprite2);
            AnimeSprite animeSprite3 = new AnimeSprite(320, 97, "font06", i3,  (int)(GameSetup.DELAY_LV_UP_S * 1000));
            addSprite(animeSprite3);
        }

        DelaySprite delaySprite = new DelaySprite(index1.size() * GameSetup.DELAY_LV_UP_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__LV_UP_TIME_OUT, null, pParentScene, pWeaponID));
        addSprite(delaySprite);
    }

    @Override
    public boolean isBacegroundEnabled() {
        return false;
    }

    @Override
    public void buildScene() {

    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {

    }
}

package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.EffectNode;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.dialg.Dialog5Scene;
import com.game.frame.sprite.DelaySprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fangxg on 2015/7/28.
 */
public class CrashNextAction implements BaseAction {
    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();

        ID.TEAM_COLOR aiTeamColer = gdm.getCurrentAiTeamColor();

        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.setShowInBattlefield();
        filter.addTeamColor(aiTeamColer);
        filter.setFuelIsZero();
        filter.addType(ID.WEAPON_TYPE.BATTLE_PLANE);
        filter.addType(ID.WEAPON_TYPE.ATTACK_PLANE);
        filter.addType(ID.WEAPON_TYPE.TRANSPORT_PLANE);
        List<BaseWeapon> weapons = gdm.getWeapons(filter);

        if (weapons.size() > 0) {

            BaseWeapon weapon = weapons.get(0);

            //需要延时
            gdm.effectNodes.add(new EffectNode(weapon.x, weapon.y, EffectNode.EEffectID.EFFECT_ID_MARKER04));

            BattlefieldScene battlefieldScene = new BattlefieldScene(false);

            List<String> texts = new ArrayList<>();
            texts.add(weapon.info.name);// "f-16);
            texts.add("因燃料耗尽坠毁。");// " 因燃料耗尽坠毁。");

            battlefieldScene.getLogicCamera().setCente(weapon.getPixelX(), weapon.getPixelY());
            Dialog5Scene dialog5Scene = new Dialog5Scene(battlefieldScene, texts);

            battlefieldScene.setDialogSecne(dialog5Scene);

            gdm.effectNodes.add(new EffectNode(weapon.getMapX(), weapon.getMapY(), EffectNode.EEffectID.EFFECT_ID_MARKER04,GameSetup.DELAY_CRASH_DLG_S ));

            battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_CRASH_DLG_S + GameSetup.DELAY_EFFECT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__CRASH_NEXT, null, battlefieldScene)));

            SceneManager.render(battlefieldScene);
        } else {
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
            battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__CRASH_TIME_OUT, null, battlefieldScene)));
            SceneManager.render(battlefieldScene);
        }

        if (weapons.size() > 0) {
            BaseWeapon weapon = weapons.get(0);
            weapon.doDie();
        }
    }
}

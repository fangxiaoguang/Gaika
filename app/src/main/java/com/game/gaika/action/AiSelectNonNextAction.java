package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.ai.BaseArtificialIntelligence;
import com.game.gaika.ai.strategy.BaseStrategic;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.sprite.DelaySprite;

import java.util.ArrayList;
import java.util.List;

import static com.game.gaika.sound.SoundManager.playSound;

/**
 * Created by fangxg on 2015/8/7.
 */
public class AiSelectNonNextAction implements BaseAction {
    private static List<BaseWeapon> aiWeapons = null;
//    private static List<Long> items = new ArrayList<>();
    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();
        ID.TEAM_COLOR aiTeamColor = gdm.getCurrentAiTeamColor();
        if (aiWeapons == null) {
            aiWeapons = new ArrayList<>();
            WeaponSelectFilter filter = new WeaponSelectFilter();
            filter.setSetOutInBattlefield();
            filter.addTeamColor(aiTeamColor);
            filter.setMoveEnding(false);
            aiWeapons = filter.getWeapons();
        }

        if(aiWeapons.size() > 0){
            BaseWeapon aiWeapon = aiWeapons.get(0);
            aiWeapons.remove(0);

//            long l1 = System.currentTimeMillis();
            BaseStrategic strategic = BaseArtificialIntelligence.getStrategic(aiWeapon);
//            items.add(System.currentTimeMillis());
//            long l2 = System.currentTimeMillis();
//            Log.d("gaika-ai", "AI.getStrategic() aiWeapon = " + aiWeapon.id + ":" + aiWeapon.info.name  + "  type = " + strategic.getClass().getName() + "  time(ms) = " + (l2 - l1));

            aiWeapon.setStrategic(strategic);

            aiWeapon.doSelect();
             playSound("select01");
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
//            battlefieldScene.getLogicCamera().setCente(aiWeapon.getPixelX(), aiWeapon.getPixelY());
            battlefieldScene.getLogicCamera().setCente(strategic.getCameraMapPoont().getPixelX(), strategic.getCameraMapPoont().getPixelY());
            battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_AI_LOOP_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__AI_SELECT_WEAPON_TIME_OUT, null, battlefieldScene, aiWeapon.id)));
            SceneManager.render(battlefieldScene);


        }else {
//            Log.d("gaika-ai", "  ai weapons of items = " + items);
//            for(int i = 1; i < items.size();i ++){
//                Log.d("gaika-ai", "  ai weapons of item = " + (items.get(i) - items.get(i -1)) );
//            }
//            items.clear();
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
            battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__AI_SELECT_NON_TIME_OUT, null, battlefieldScene)));
            SceneManager.render(battlefieldScene);
            aiWeapons = null;
        }

    }
}

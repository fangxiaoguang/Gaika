package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.City;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.sound.SoundManager;
import com.game.gaika.sprite.DelaySprite;

import java.util.List;

import static com.game.gaika.sound.SoundManager.playSound;

/**
 * Created by fangxg on 2015/8/18.
 */
public class AiBuyNextAction implements   BaseAction {
    private static List<BaseWeapon> repairWeapons = null;

    @Override
    public void doAction() {

        GameDataManager gdm = GameDataManager.getInstance();

        ID.TEAM_COLOR aiTeamColor = gdm.getCurrentAiTeamColor();

        City tCity = null;
        BaseWeapon tWeapon = null;
        for(City city:gdm.getCurrentChapter().getGameMap().citys.values()){
            WeaponSelectFilter filter = new WeaponSelectFilter();
            filter.setSetOutInBattlefield();
            filter.setMapXY(city.getMapX(),city.getMapY());
            List<BaseWeapon> weapons = filter.getWeapons();
            if(city.isBase == true && city.teamColor == aiTeamColor && weapons.size() == 0){
                WeaponSelectFilter filter2 = new WeaponSelectFilter();
                filter2.addTeamColor(aiTeamColor);
                filter2.setTeamOut(true);
                filter2.setSetOut(false);
                filter2.setLifeNotZero();
                List<BaseWeapon> weapons2 = filter2.getWeapons();
                for(BaseWeapon weapon : weapons2){
                    if(weapon.info.canSetoutByCityType(city.type) == true &&  weapon.info.supply <= gdm.getSupply(aiTeamColor)){
                        tCity = city;
                        tWeapon = weapon;
                        break;
                    }
                }
                if(tCity != null){
                    break;
                }
            }
        }

        if (tCity != null) {
            tWeapon.doSetOut(tCity);
            gdm.setSupply(aiTeamColor, gdm.getSupply(aiTeamColor) - tWeapon.info.supply);
//------------------------
           playSound("haichi01");
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
            battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_AI_BUY_NEXT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__AI_BUY_NEXT, null, battlefieldScene)));
            battlefieldScene.getLogicCamera().setCente(tCity.getPixelX(), tCity.getPixelY());
            SceneManager.render(battlefieldScene);
//------------------

        } else {
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
            battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__AI_BUY_TIME_OUT, null, battlefieldScene)));
            SceneManager.render(battlefieldScene);
        }
    }
}

package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.City;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.data.weapon.WeaponFactory;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.dialg.GetSomeThingScene;
import com.game.frame.sprite.DelaySprite;

import static com.game.frame.sound.SoundManager.playSound;

/**
 * Created by fangxg on 2015/7/26.
 */
public class CapliuringTimeOutAction implements BaseAction {
    private int weaponID;

    public CapliuringTimeOutAction(int weaponID) {
        this.weaponID = weaponID;
    }

    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();

        BaseWeapon weapon = gdm.weapons.get(weaponID);
        City city = gdm.getCurrentChapter().getGameMap().citys.get(weapon.x * 100 + weapon.y);

        if (city != null && (city.getWeaponInfoId != -1 || city.getMoney != 0 || city.getSupply != 0)) {
             playSound("messag01");
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
            battlefieldScene.setDialogSecne(new GetSomeThingScene(weaponID, battlefieldScene));
            SceneManager.render(battlefieldScene);

            if(city.getWeaponInfoId != -1){
                BaseWeapon getWeapon = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(city.getWeaponInfoId));
                gdm.weapons.put(getWeapon.id, getWeapon);
                city.getWeaponInfoId = -1;
            }
            if(city.getMoney != 0 ){
                gdm.money += city.getMoney;
                city.getMoney = 0;
            }
            if(city.getSupply != 0){
                gdm.addSupply(ID.TEAM_COLOR.BLUE, city.getSupply);
                city.getSupply = 0;
            }




        } else {
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
            battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__GET_SOME_THING_TIME_OUT, null, battlefieldScene, weaponID)));
            SceneManager.render(battlefieldScene);
        }
    }
}

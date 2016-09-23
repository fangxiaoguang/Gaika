package com.game.gaika.scene.dialg;

import com.game.frame.scene.dialg.DialogScene;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.City;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.data.weapon.WeaponInfo;
import com.game.frame.scene.BaseLogicScene;
import com.game.gaika.sprite.BottomMessageSprite;
import com.game.frame.sprite.DelaySprite;
import com.game.gaika.sprite.WeaponInfoSprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fangxg on 2015/7/26.
 */
public class GetSomeThingScene extends DialogScene {

    public GetSomeThingScene(int pWeaponID, BaseLogicScene pParentScene) {
        super(ID.SCENE_ID.GET_SOME_THING_DIALG, 0.0f, 0.0f, 376.0f, 256.0f + 88.0f, pParentScene, EPointModel.POINT_MODEL_CENTER);
        GameDataManager gdm = GameDataManager.getInstance();
        BaseWeapon weapon = gdm.weapons.get(pWeaponID);
        City city = gdm.getCurrentChapter().getGameMap().citys.get(weapon.x * 100 + weapon.y);

        if (city.getWeaponInfoId != -1) {
            WeaponInfoSprite infoSprite = new WeaponInfoSprite(city.getWeaponInfoId, WeaponInfoSprite.INFO_CARD_ALPHA);
            infoSprite.setX((376.0f - 211.0f) / 2.0f);
            infoSprite.setY(0.0f);
            addSprite(infoSprite);

            List<String> texts = new ArrayList<>();
            texts.add("捕获了敌方兵器。");
            texts.add("暂时先加入我方的兵器仓库。");
            WeaponInfo info = gdm.weapInfos.get(city.getWeaponInfoId);
            texts.add("取得了一个单位的" + info.type.toString() + " " + info.name);
            BottomMessageSprite bottomMessageSprite = new BottomMessageSprite(texts);
            bottomMessageSprite.setX(0.0f);
            bottomMessageSprite.setY(256.0f);
            addSprite(bottomMessageSprite);
            addSprite(new DelaySprite(GameSetup.DELAY_GET_SOME_THING_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__GET_SOME_THING_TIME_OUT, null, pParentScene, weapon.id)));
        } else if (city.getMoney != 0) {
            List<String> texts = new ArrayList<>();
            texts.add("由于未知原因。");
            texts.add("取得了军事资金 " + city.getMoney + " 。");
            BottomMessageSprite bottomMessageSprite = new BottomMessageSprite(texts);
            bottomMessageSprite.setX(0.0f);
            bottomMessageSprite.setY(256.0f);
            addSprite(bottomMessageSprite);
            addSprite(new DelaySprite(GameSetup.DELAY_GET_SOME_THING_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__GET_SOME_THING_TIME_OUT, null, pParentScene, weapon.id)));
        } else if (city.getSupply != 0) {
            List<String> texts = new ArrayList<>();
            texts.add("由于未知原因。");
            texts.add("取得了补给物资 " + city.getSupply + " 。");
            BottomMessageSprite bottomMessageSprite = new BottomMessageSprite(texts);
            bottomMessageSprite.setX(0.0f);
            bottomMessageSprite.setY(256.0f);
            addSprite(bottomMessageSprite);
            addSprite(new DelaySprite(GameSetup.DELAY_GET_SOME_THING_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__GET_SOME_THING_TIME_OUT, null, pParentScene, weapon.id)));
        }
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

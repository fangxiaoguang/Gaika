package com.game.gaika.scene.dialg;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.BaseLogicScene;
import com.game.gaika.sprite.NormalSprite;

/**
 * Created by fangxg on 2015/7/25.
 */
public class ChooseArmsDialogScene extends DialogScene {
    private static final float BUTTON_SCALE = 1.5f;
    public ChooseArmsDialogScene(BaseWeapon pWeapon, BaseLogicScene pParentScene) {
        super(ID.SCENE_ID.CHOOSE_ARMS_MENU, pWeapon.getPixelX(), pWeapon.getPixelY(), 0.0f, 0.0f, pParentScene,EPointModel.POINT_MODEL_OFFSET);

        GameDataManager gdm = GameDataManager.getInstance();


        if (pWeapon.canChooseArms() == false) {
            throw new IllegalArgumentException("The weapon is not choose arms. Weapon.id: " + pWeapon.id);

        }

        NormalSprite bt1Sprite  = new NormalSprite(50.0f, 50.0f, "pack_bt1", 0, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__SELECT_CHOOSE_ARMS_DLG_AIR, null, pParentScene, pWeapon.id));
        bt1Sprite.setScale(BUTTON_SCALE);
        addSprite(bt1Sprite);
        NormalSprite bt2Sprite  = new NormalSprite(50.0f, 50.0f + 21.0f  * BUTTON_SCALE, "pack_bt1", 1, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__SELECT_CHOOSE_ARMS_DLG_GROUND, null, pParentScene, pWeapon.id));
        bt2Sprite.setScale(BUTTON_SCALE);
        addSprite(bt2Sprite);
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

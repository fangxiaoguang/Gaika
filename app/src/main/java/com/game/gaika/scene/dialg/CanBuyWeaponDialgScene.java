package com.game.gaika.scene.dialg;

import com.game.frame.scene.dialg.DialogScene;
import com.game.frame.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.WeaponFactory;
import com.game.gaika.data.weapon.WeaponInfo;
import com.game.frame.scene.BaseLogicScene;
import com.game.frame.sprite.NormalSprite;
import com.game.frame.sprite.TextSprite;
import com.game.gaika.sprite.WeaponInfoSprite;
import com.game.frame.string.StringManager;
import com.game.frame.texture.TexRegionManager;

import static com.game.gaika.data.ID.SCENE_ID.*;

/**
 * Created by fangxg on 2015/7/1.
 */
public class CanBuyWeaponDialgScene extends DialogScene {
    public CanBuyWeaponDialgScene(int pInfoID, BaseLogicScene pParentScene){//TouchMessage pTouchMessage) {
        super(CAN_BUY_WEAPON_DIALOG, 0.0f, 0.0f, 0.0f,0.0f, null, EPointModel.POINT_MODEL_OFFSET);
		GameDataManager gdm = GameDataManager.getInstance();
		WeaponInfo info = gdm.weapInfos.get(pInfoID);

        NormalSprite bkSlprite = new NormalSprite(0.0f, 0.0f, "delay", 0, new TouchMessage(ID.MSG_ID.MSG_SCENE_DIPLOMACY__CAN_BUY_WEAPON_NEXT, null, pParentScene));//pTouchMessage);
        bkSlprite.getFlyweight().setScaleXY(800.0f, 600.0f);
        addSprite(bkSlprite);


        NormalSprite testBkSlprite = new NormalSprite((800 - 406) / 2, 50, "dialg5");
        addSprite(testBkSlprite);

		TextSprite textSprite1 = new TextSprite((800 - 406) / 2 + 50, 50 + 50, true, StringManager.getInstance().getString("S05001") , TexRegionManager.getInstance().getFont16());
		addSprite(textSprite1);

		TextSprite textSprite2 = new TextSprite((800 - 406) / 2 + 50, 50 + 50 +  25, true, info.name + " 了。" , TexRegionManager.getInstance().getFont16());
		addSprite(textSprite2);

		WeaponInfoSprite infoSprite = new WeaponInfoSprite(WeaponFactory.getInstance().newWeapon(info), WeaponInfoSprite.INFO_CARD_ALPHA);
		infoSprite.setX((800 - 211) / 2);
		infoSprite.setY(200);
		addSprite(infoSprite);
        /*
            int infoId = idList.get(idList.size() - 1);
			idList.remove(idList.size() - 1);

			WeaponInfo info = gd.weapInfos.get(infoId);

			TiledSprite sprite1 = BaseRender.getShape((800 - 406) / 2, 50, "dialg5", 0);

			Text textSsprite1 = BaseRender.getText16(true, 50, 50, StringManager.getInstance().getString("S05001"));//)"已经可以购买兵器");
			sprite1.attachChild(textSsprite1);

			Text textSsprite2 = BaseRender.getText16(true, 50, 50 + 25, info.name + " 了。");
			sprite1.attachChild(textSsprite2);

			bkScne.attachChild(sprite1);

			TiledSprite infoSprite = BaseRender.getInfoCardSprite(info);
			infoSprite.setPosition((800 - 211) / 2, 200);
			bkScne.attachChild(infoSprite);

			bkScne.clearTouchAreas();
			BaseRender.addTouchArea(bkScne, backsprite, ID.BID_DIPLOMACY_SCEEN_NEXT, 0, false);*/
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

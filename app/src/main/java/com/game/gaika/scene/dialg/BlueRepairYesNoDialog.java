package com.game.gaika.scene.dialg;

import com.game.frame.scene.dialg.DialogScene;
import com.game.frame.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.frame.scene.BaseLogicScene;
import com.game.frame.sprite.NormalSprite;
import com.game.frame.sprite.TextSprite;
import com.game.frame.texture.TexRegionManager;

import static com.game.gaika.data.ID.MSG_ID.MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_NO;
import static com.game.gaika.data.ID.MSG_ID.MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_YES;

/**
 * Created by fangxg on 2015/9/1.
 */
public class BlueRepairYesNoDialog  extends DialogScene {
    public BlueRepairYesNoDialog(BaseLogicScene pParentScene, int pWeaponID, int pFromLifeEx, int pToLifeEx, int pNeedSupply) {
        super(ID.SCENE_ID.BLUE_REPAIR_YES_NO_DIALOG, 0.0f, 0.0f, 406, 185, pParentScene, EPointModel.POINT_MODEL_CENTER);

        GameDataManager gdm =GameDataManager.getInstance();
       BaseWeapon blueNode =  gdm.weapons.get(pWeaponID);

        NormalSprite bkSprite = new NormalSprite(0, 0, "dialg4");
        addSprite(bkSprite);


        NormalSprite weapSprite = new NormalSprite(50, 40, "weap_nm1", blueNode.info.texIndex);
        addSprite(weapSprite);
        NormalSprite typeSprite = new NormalSprite(50, 50, "font_typ", blueNode.info.type.ordinal());
        addSprite(typeSprite);
        NormalSprite unitSprite = new NormalSprite(50, 60, "unit02", blueNode.info.texIndex);
        addSprite(unitSprite);
        NormalSprite flagSprite = new NormalSprite(50, 90 - 5, "flag01", blueNode.info.country.ordinal());
        addSprite(flagSprite);
        NormalSprite lvSprite = new NormalSprite(90, 95 - 5, "font_lv1", blueNode.getLv());
        addSprite(lvSprite);
        NormalSprite fromLifeExSprite = new NormalSprite(55, 105, "font01", pFromLifeEx);
        addSprite(fromLifeExSprite);
        NormalSprite toLifeExSprite = new NormalSprite(90, 105, "font01", pToLifeEx);
        addSprite(toLifeExSprite);




        TextSprite text1Sprite = new TextSprite(130, 45, true, "消耗" + pNeedSupply + "补给物资", TexRegionManager.getInstance().getFont16());
        addSprite(text1Sprite);
        TextSprite text2Sprite = new TextSprite(130, 70, true,"补充" + (pToLifeEx - pFromLifeEx) + blueNode.info.getUnitString(), TexRegionManager.getInstance().getFont16());
        addSprite(text2Sprite);
        TextSprite text3Sprite = new TextSprite(130, 95,true, blueNode.info.name, TexRegionManager.getInstance().getFont16());
        addSprite(text3Sprite);


        NormalSprite buttonYesSprite = new NormalSprite(43, 137, "dialg6bt", 0, new TouchMessage(  MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_YES, null, pParentScene, pWeaponID) );
        addSprite(buttonYesSprite);
        NormalSprite buttonNoSprite = new NormalSprite(287, 137, "dialg6bt", 1, new TouchMessage(  MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_NO, null, pParentScene, pWeaponID));
        addSprite(buttonNoSprite);
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

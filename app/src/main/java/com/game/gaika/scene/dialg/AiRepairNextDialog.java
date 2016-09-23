package com.game.gaika.scene.dialg;

import com.game.frame.scene.dialg.DialogScene;
import com.game.frame.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.frame.scene.BaseLogicScene;
import com.game.frame.sprite.DelaySprite;
import com.game.frame.sprite.NormalSprite;
import com.game.frame.sprite.TextSprite;
import com.game.frame.texture.TexRegionManager;

/**
 * Created by fangxg on 2015/7/29.
 */
public class AiRepairNextDialog extends DialogScene {

    public AiRepairNextDialog(BaseLogicScene pParentScene, int pWeaponID) {
        super(ID.SCENE_ID.AI_REPAIR_NEXT_DIALOG, 0.0f, 0.0f, 406.0f, 135.0f, pParentScene, EPointModel.POINT_MODEL_CENTER);

        GameDataManager gdm = GameDataManager.getInstance();
        BaseWeapon weapon = gdm.weapons.get(pWeaponID);

        int fromLifeEx = weapon.getLifeEx();
        int toLifeEx = fromLifeEx + weapon.info.getSupplyLifeExByTurn();
        if (toLifeEx > 10) {
            toLifeEx = 10;
        }
        int needSupply = weapon.info.supply * (toLifeEx - fromLifeEx) / 10;

        NormalSprite bkSprite = new NormalSprite(0.0f, 0.0f, "dialg4_2");
        addSprite(bkSprite);

        NormalSprite nmSprite = new NormalSprite(50, 40 - 10, "weap_nm1", weapon.info.texIndex);
        addSprite(nmSprite);
        NormalSprite typeSprite = new NormalSprite(50, 50 - 10, "font_typ", weapon.info.type.ordinal());
        addSprite(typeSprite);
        NormalSprite unitSprite = new NormalSprite(50, 60 - 10, "unit02", weapon.info.texIndex);
        addSprite(unitSprite);
        NormalSprite flagSprite = new NormalSprite(50, 90 - 5 - 10, "flag01", weapon.info.country.ordinal());
        addSprite(flagSprite);
        NormalSprite lv1Sprite = new NormalSprite(90, 95 - 5 - 10, "font_lv1", weapon.getLv() - 1);
        addSprite(lv1Sprite);
        NormalSprite fromLifeExSprite = new NormalSprite(55, 105 - 10, "font01", fromLifeEx);
        addSprite(fromLifeExSprite);
        NormalSprite toLifeExSprite = new NormalSprite(90, 105 - 10, "font01", toLifeEx);
        addSprite(toLifeExSprite);
        TextSprite text1Sprite = new TextSprite(130, 45 - 10, true, "消耗" + needSupply + "补给物资", TexRegionManager.getInstance().getFont16());
        addSprite(text1Sprite);
        TextSprite text2Sprite = new TextSprite(130, 70 - 10, true, "补充" + (toLifeEx - fromLifeEx) + weapon.info.getUnitString(), TexRegionManager.getInstance().getFont16());
        addSprite(text2Sprite);
        TextSprite text3Sprite = new TextSprite(130, 95 - 10, true, weapon.info.name, TexRegionManager.getInstance().getFont16());
        addSprite(text3Sprite);

        addSprite(new DelaySprite(GameSetup.DELAY_AI_RE_SUPPLY_NEXT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__AI_REPAIR_NEXT, null, pParentScene)));
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

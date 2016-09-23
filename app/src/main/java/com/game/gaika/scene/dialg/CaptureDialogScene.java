package com.game.gaika.scene.dialg;

import com.game.frame.scene.dialg.DialogScene;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.City;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.frame.scene.BaseLogicScene;
import com.game.frame.sprite.DelaySprite;
import com.game.frame.sprite.NormalSprite;
import com.game.frame.sprite.NumberSprite;
import com.game.frame.sprite.TextSprite;
import com.game.frame.texture.TexRegionManager;

import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;

/**
 * Created by fangxg on 2015/7/26.
 */
public class CaptureDialogScene extends DialogScene {
    public CaptureDialogScene(int pWeaponID, BaseLogicScene pParentScene) {
        super(ID.SCENE_ID.CAPTURE_DIALOG, 0.0f, 0.0f, 406.0f, 142.0f, pParentScene, EPointModel.POINT_MODEL_CENTER);
        GameDataManager gdm = GameDataManager.getInstance();
        BaseWeapon weapon = gdm.weapons.get(pWeaponID);

        City city = gdm.getCurrentChapter().getGameMap().citys.get(weapon.x * 100 + weapon.y);

//             playSound("messag01");

            NormalSprite bkSprite = new NormalSprite(0.0f, 0.0f, "dialg3");
            addSprite(bkSprite);

            NormalSprite nmSprite = new NormalSprite(50, 40 + 5, "weap_nm1", weapon.info.texIndex);
            addSprite(nmSprite);
            NormalSprite typeSprite = new NormalSprite(50, 50 + 5, "font_typ", weapon.info.type.ordinal());
            addSprite(typeSprite);
            NormalSprite unitSprite = new NormalSprite(50, 60 + 5, "unit02", weapon.info.texIndex);
            addSprite(unitSprite);
            NormalSprite flagSprite = new NormalSprite(50, 90 - 5 + 5, "flag01", weapon.info.country.ordinal());
            addSprite(flagSprite);
            NormalSprite fontSprite = new NormalSprite(93, 75 + 5, "font01", weapon.getLifeEx());
            addSprite(fontSprite);
            NormalSprite lvSprite = new NormalSprite(90, 95 - 5 + 5, "font_lv1", weapon.getLv() - 1);
            addSprite(lvSprite);

            TextSprite textSprite1 = new TextSprite(140, 45, true, "正在占领城市", TexRegionManager.getInstance().getFont16());
            addSprite(textSprite1);
            TextSprite textSprite2 = new TextSprite(140, 65, true, city.name, TexRegionManager.getInstance().getFont16());
            addSprite(textSprite2);


            int oldCapture = city.capture;
            int newCapture = oldCapture - weapon.info.capture * weapon.getLifeEx();
            if (newCapture <= 0) {
                newCapture = 0;
            }

            NumberSprite numberSprite1 = new NumberSprite(315, 85, false, "font03", newCapture);
            addSprite(numberSprite1);
            NumberSprite numberSprite2 = new NumberSprite(360, 85, false, "font03", 100);
            addSprite(numberSprite2);



            int delayMS = GameSetup.DELAY_CAPTURE_MS * (oldCapture - newCapture);

            NormalSprite pt1 = new NormalSprite(172, 99, "dialg3pt", city.teamColor.ordinal() * 2 + 1, null, 200.0f, 1.0f);
            addSprite(pt1);
           // if (newCapture == 0) {
                NormalSprite pt2 = new NormalSprite(172, 99, "dialg3pt", city.teamColor.ordinal() * 2, null, newCapture * 2, 1.0f);
                pt2.registerEntityModifier(new ScaleModifier(((float)delayMS / 1000.0f), oldCapture * 2, newCapture * 2, 1.0f, 1.0f));
                addSprite(pt2);
          //  }



            if(newCapture == 0){
                NormalSprite dialg3p1Sprete = new NormalSprite(125, 45, "dialg3p1", gdm.getCurrentAiTeamColor().ordinal());
                dialg3p1Sprete.getFlyweight().setAlpha(0.0f);
                dialg3p1Sprete.registerEntityModifier(new SequenceEntityModifier(/* new AlphaModifier(0.0f, 0.0f, 0.0f),*/new DelayModifier(((float)delayMS)/1000.f),  new AlphaModifier(0.001f, 0.0f, 1.0f)));
                addSprite(dialg3p1Sprete);

                delayMS += GameSetup.DELAY_CAPTURED_MS ;
            }

            DelaySprite delaySprite = new DelaySprite(((float)delayMS / 1000.0f), new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__CAPTURING_TIME_OUT, null, pParentScene, pWeaponID));
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

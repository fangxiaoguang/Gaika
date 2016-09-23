package com.game.gaika.scene.hud;

import com.game.frame.scene.hud.HUDScene;
import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.TouchMessage;

import static com.game.gaika.data.ID.SCENE_ID.*;

import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.debug.DebugManager;
import com.game.frame.flyweight.BaseFlyweight;
import com.game.gaika.sprite.HqMapSprite;
import com.game.frame.sprite.NormalSprite;
import com.game.frame.sprite.NumberSprite;
import com.game.frame.sprite.TextSprite;
import com.game.gaika.sprite.WeaponInfoSprite;
import com.game.frame.texture.TexRegionManager;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;

import static com.game.gaika.data.ID.MSG_ID.*;

/**
 * Created by fangxg on 2015/6/22.
 */
public class BattlefieldHUDScene extends HUDScene {
  //  public static IEntity wbox;



//    private  int infoCardWeaponID  = -1;

    public BattlefieldHUDScene(float pWidth, float pHeight, IMessageHandler pMessageHandler, int pInfoCardWeaponID) {
        super(BATTLEFIELD_HUD, pWidth, pHeight);

        float unZoom = 1.0f / GameSetup.settingBattlefieldZoom;
        //wbox = null;
        GameDataManager gdm = GameDataManager.getInstance();


        //傍晚和夜晚的特效
        if (gdm.gameTimer.getHourFromTurn() == 17) { //傍晚
            NormalSprite printSprite = new NormalSprite(0.0f, 0.0f, "addprint4", 0, null, width / 800.0f, height / 440.0f, 0.5f);
            addSprite(printSprite);
        } else if (gdm.gameTimer.getHourFromTurn() == 21 || gdm.gameTimer.getHourFromTurn() == 2) {//夜晚
            NormalSprite printSprite = new NormalSprite(0.0f, 0.0f, "addprint5", 0, null, width / 800.0f, height / 440.0f, 0.5f);
            addSprite(printSprite);
        }


        ID.TEAM_COLOR aiTeamColor = gdm.getCurrentAiTeamColor();
        NormalSprite normalSpriteL = new NormalSprite(0.0f * unZoom, 0.0f * unZoom, "turn_col", aiTeamColor.ordinal());
        normalSpriteL.setScale(unZoom);
        addSprite(normalSpriteL);
        NumberSprite numberSprite1 = new NumberSprite(30 * unZoom, 12 * unZoom, false, "font04", gdm.gameTimer.getDayFromTurn());
        numberSprite1.setScale(unZoom);
        addSprite(numberSprite1);
        NumberSprite numberSprite2 = new NumberSprite(30 * unZoom, 33 * unZoom, false, "font03", gdm.gameTimer.getHourFromTurn(), 2, 0, 1.0f);
        numberSprite2.setScale(unZoom);
        addSprite(numberSprite2);
        NumberSprite numberSprite3 = new NumberSprite(60 * unZoom, 33 * unZoom, false, "font03", 0, 2, 0, 1.0f);
        numberSprite3.setScale(unZoom);
        addSprite(numberSprite3);

        NormalSprite leftSprite2 = new NormalSprite(62 * unZoom, 2 * unZoom, "map_bar1", 0);
        leftSprite2.setScale(unZoom);
        addSprite(leftSprite2);
        NumberSprite supplySprite = new NumberSprite(275 * unZoom, 7 * unZoom, false, "font02", gdm.getSupply(aiTeamColor));//, 0, 0);
        supplySprite.setScale(unZoom);
        addSprite(supplySprite);


        if (pMessageHandler != null) {
            NormalSprite turnFinishButtonSprite = new NormalSprite(width - 158.0f * unZoom, 0.0f * unZoom, "sys_bt2", 1, new TouchMessage(MSG_SCENE_HUD__TURN_FINISH, null, pMessageHandler));
            turnFinishButtonSprite.setScale(unZoom);
            addSprite(turnFinishButtonSprite);

            NormalSprite systemButtonSprite = new NormalSprite(width - 87.0f * unZoom, 0.0f * unZoom, "sys_bt", 1, new TouchMessage(MSG_SCENE_HUD__BUTTON_SYSTEM, null, pMessageHandler));
            systemButtonSprite.setScale(unZoom);
            addSprite(systemButtonSprite);
        } else {
            NormalSprite turnFinishButtonSprite = new NormalSprite(width - 158.0f * unZoom, 0.0f * unZoom, "sys_bt2", 0);
            turnFinishButtonSprite.setScale(unZoom);
            addSprite(turnFinishButtonSprite);

            NormalSprite systemButtonSprite = new NormalSprite(width - 87.0f * unZoom, 0.0f * unZoom, "sys_bt", 0);
            systemButtonSprite.setScale(unZoom);
            addSprite(systemButtonSprite);
        }


        if (GameSetup.isDebug_HUDTextBox) {
            String info = DebugManager.getInstance().getDebugInfoString();
            TextSprite debugInfoSprite = new TextSprite(0.0f, 0.0f, true, info, TexRegionManager.getInstance().getFont12());
            addSprite(debugInfoSprite);
        }

        HqMapSprite hqMapSprite = new HqMapSprite(width - 100.0f * unZoom, 75.0f * unZoom);
        hqMapSprite.setScale(unZoom);
        addSprite(hqMapSprite);


        if(pInfoCardWeaponID > -1) {
            BaseWeapon selectWeapon = gdm.weapons.get(pInfoCardWeaponID);

            WeaponInfoSprite weaponInfoSprite = new WeaponInfoSprite(selectWeapon, WeaponInfoSprite.INFO_CARD_ALPHA);
            weaponInfoSprite.setScale(0.65f * unZoom);
            weaponInfoSprite.setX(0);
            weaponInfoSprite.setY(70.0f  * unZoom);
            weaponInfoSprite.registerDeepEntityModifier(new SequenceEntityModifier(new DelayModifier(3.0f), new AlphaModifier(2.0f, WeaponInfoSprite.INFO_CARD_ALPHA, 0.0f)));
            addSprite(weaponInfoSprite);
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

    public void setSmallMapBoxPoint(float xMin, float yMin) {

        //IEntity wbox = bkScne.getChildByTag(10086);
        IEntity wbox = BaseFlyweight.getTagedEntity(HqMapSprite.WBOX_TAG);
        if (wbox != null) {
            GameDataManager gd = GameDataManager.getInstance();

//        if (SceneManager.getTopLogicScene().smallMapBox == null) {
//            return;
//        }

//            Camera camera = MainActivity.mGameActiviy.getEngine().getCamera();
//
//            float xMin = camera.getXMin();
//            float yMin = camera.getYMin();

            float xMapMini = 100.0f / gd.getCurrentChapter().getGameMap().getMapSizePixelX() * xMin;
            float yMapMini = 100.0f / gd.getCurrentChapter().getGameMap().getMapSizePixelY() * yMin;

            wbox.setPosition(xMapMini, yMapMini);
        }
    }
//    public void setInfoCardWeaponID(int infoCardWeaponID) {
//        this.infoCardWeaponID = infoCardWeaponID;
//    }
}

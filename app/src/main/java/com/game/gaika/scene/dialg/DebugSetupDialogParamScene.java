package com.game.gaika.scene.dialg;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.City;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.SaveManager;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.debug.DebugManager;
import com.game.gaika.scene.BaseLogicScene;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.sprite.NormalSprite;
import com.game.gaika.sprite.TextButtonSprite;

/**
 * Created by fangxg on 2015/7/20.
 */
public class DebugSetupDialogParamScene extends DialogScene {
    private  static int page = 1;
    public DebugSetupDialogParamScene(BaseLogicScene pParentScene) {
        super(ID.SCENE_ID.DEBUG_DIALOG_1_DIALOG, 0.0f, 0.0f, pParentScene.getLogicCamera().getCameraWidth(), pParentScene.getLogicCamera().getCameraHeight(), pParentScene, EPointModel.POINT_MODEL_CENTER);

        float scale = DebugManager.getDebugButtonScale(pParentScene.sceneId);


        if (page > 1) {
            //<-
            NormalSprite liftSprite = new NormalSprite(10, 110, "scroolbt", 0, new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_2___LEFT, null, this), scale);
            addSprite(liftSprite);
        }

        if (page < 2) {
            //->
            NormalSprite liftSprite = new NormalSprite(450, 110, "scroolbt", 0, new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_2___RIGHT, null, this), scale);
            addSprite(liftSprite);

        }

        int x = 50;
        int y = 10;
        if(page == 1) {
            TextButtonSprite button1 = new TextButtonSprite(x, y, "isDebug_HUDTextBox off/on   ", new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_1, null, this), scale);
            addSprite(button1);
            y += 40;
            TextButtonSprite button2 = new TextButtonSprite(x, y, "set all ->moveEnding = false", new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_2, null, this), scale);
            addSprite(button2);
            y += 40;
            TextButtonSprite button3 = new TextButtonSprite(x, y, "set all ->exp.preexp = 90   ", new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_3, null, this), scale);
            addSprite(button3);
            y += 40;
            TextButtonSprite button4 = new TextButtonSprite(x, y, "set all ->city.capture = 50 ", new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_4, null, this), scale);
            addSprite(button4);
            y += 40;
            TextButtonSprite button5 = new TextButtonSprite(x, y, "save(2001)                  ", new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_5, null, this), scale);
            addSprite(button5);
            y += 40;
            TextButtonSprite button6 = new TextButtonSprite(x, y, "load(2001)                  ", new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_6, null, this), scale);
            addSprite(button6);
            y += 40;
            TextButtonSprite button7 = new TextButtonSprite(x, y, "We lose............         ", new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_7, null, this), scale);
            addSprite(button7);
            y += 40;
            TextButtonSprite button8 = new TextButtonSprite(x, y, "We win.............         ", new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_8, null, this), scale);
            addSprite(button8);
            y += 40;
        }else if(page == 2){
            TextButtonSprite button9 = new TextButtonSprite(x, y, "all enemy.life = 250        ", new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_9, null, this), scale);
            addSprite(button9);
            y += 40;
        }

        TextButtonSprite buttonRet = new TextButtonSprite(x, y, "Return                      ", new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_RETURN, null, this), scale);
        addSprite(buttonRet);
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

        ID.MSG_ID msgID = pTouchMessage.getMessageID();
        if(msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_2___LEFT){
            page --;
            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                if (parentScene.getSceneId() == ID.SCENE_ID.BATTLEFIELD) {
                    BattlefieldScene battlefieldScene = new BattlefieldScene(false);
                    battlefieldScene.setDialogSecne(new DebugSetupDialogParamScene(battlefieldScene));
                    SceneManager.render(battlefieldScene);
                }
            }
        }
        if(msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_2___RIGHT){
            page ++;
            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                if (parentScene.getSceneId() == ID.SCENE_ID.BATTLEFIELD) {
                    BattlefieldScene battlefieldScene = new BattlefieldScene(false);
                    battlefieldScene.setDialogSecne(new DebugSetupDialogParamScene(battlefieldScene));
                    SceneManager.render(battlefieldScene);
                }
            }
        }
        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_1) {
            GameSetup.isDebug_HUDTextBox = !GameSetup.isDebug_HUDTextBox;

            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                if (parentScene.getSceneId() == ID.SCENE_ID.BATTLEFIELD) {
                    BattlefieldScene battlefieldScene = new BattlefieldScene(false);
                    SceneManager.render(battlefieldScene);
                }
            }
        }
        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_2) {

            GameDataManager gdm = GameDataManager.getInstance();
            for (BaseWeapon weapon : gdm.weapons.values()) {
                weapon.moveEnding = false;
            }

            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                if (parentScene.getSceneId() == ID.SCENE_ID.BATTLEFIELD) {
                    BattlefieldScene battlefieldScene = new BattlefieldScene(false);
                    SceneManager.render(battlefieldScene);
                }
            }
        }
        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_3) {

            GameDataManager gdm = GameDataManager.getInstance();
            for (BaseWeapon weapon : gdm.weapons.values()) {
                weapon.exp = 90;
                weapon.preExp = 90;
            }

            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                if (parentScene.getSceneId() == ID.SCENE_ID.BATTLEFIELD) {
                    BattlefieldScene battlefieldScene = new BattlefieldScene(false);
                    SceneManager.render(battlefieldScene);
                }
            }
        }

        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_4) {

            GameDataManager gdm = GameDataManager.getInstance();
            for (City city : gdm.getCurrentChapter().getGameMap().citys.values()) {
                city.capture = 50;
            }

            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                if (parentScene.getSceneId() == ID.SCENE_ID.BATTLEFIELD) {
                    BattlefieldScene battlefieldScene = new BattlefieldScene(false);
                    SceneManager.render(battlefieldScene);
                }
            }
        }
        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_5) {

            SaveManager.save(2001);

            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                if (parentScene.getSceneId() == ID.SCENE_ID.BATTLEFIELD) {
                    BattlefieldScene battlefieldScene = new BattlefieldScene(false);
                    SceneManager.render(battlefieldScene);
                }
            }
        }
        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_6) {

            SaveManager.load(2001);

            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                if (parentScene.getSceneId() == ID.SCENE_ID.BATTLEFIELD) {
                    BattlefieldScene battlefieldScene = new BattlefieldScene(false);
                    SceneManager.render(battlefieldScene);
                }
            }
        }
        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_7) {

            GameDataManager gdm = GameDataManager.getInstance();
            if (gdm.gameTimer.getCurrentTurn() <= 3) {
                gdm.gameTimer.setCurrentTurn(4);
            }
            for (BaseWeapon enemy : gdm.weapons.values()) {
                if (enemy.teamColor == ID.TEAM_COLOR.BLUE) {
                    enemy.life = 0;
                }
            }

            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                if (parentScene.getSceneId() == ID.SCENE_ID.BATTLEFIELD) {
                    BattlefieldScene battlefieldScene = new BattlefieldScene(false);
                    SceneManager.render(battlefieldScene);
                }
            }
        }
        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_8) {

            GameDataManager gdm = GameDataManager.getInstance();
            if (gdm.gameTimer.getCurrentTurn() <= 3) {
                gdm.gameTimer.setCurrentTurn(4);
            }
            for (BaseWeapon enemy : gdm.weapons.values()) {
                if (enemy.teamColor != ID.TEAM_COLOR.BLUE) {
                    enemy.life = 0;
                }
            }

            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                if (parentScene.getSceneId() == ID.SCENE_ID.BATTLEFIELD) {
                    BattlefieldScene battlefieldScene = new BattlefieldScene(false);
                    SceneManager.render(battlefieldScene);
                }
            }
        }
        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_9) {
            GameDataManager gdm = GameDataManager.getInstance();

            for (BaseWeapon enemy : gdm.weapons.values()) {
                if (enemy.teamColor != ID.TEAM_COLOR.BLUE) {
                    enemy.life = 250;
                }
            }

            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                if (parentScene.getSceneId() == ID.SCENE_ID.BATTLEFIELD) {
                    BattlefieldScene battlefieldScene = new BattlefieldScene(false);
                    SceneManager.render(battlefieldScene);
                }
            }
        }
        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_1__BUTTON_RETURN) {


            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(new DebugSetupDialogMainScene(parentScene));
                //  BaseLogicScene topScene = SceneFactory.createScene(parentScene.sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
                SceneManager.render(parentScene);

            }
        }

    }
}

package com.game.gaika.scene.dialg;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.ID;
import com.game.gaika.debug.DebugManager;
import com.game.gaika.scene.BaseLogicScene;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneFactory;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.sprite.TextButtonSprite;

/**
 * Created by fangxg on 2015/8/16.
 */
public class DebugSetupDialogMainScene  extends DialogScene {
    public DebugSetupDialogMainScene(BaseLogicScene pParentScene) {
        super(ID.SCENE_ID.DEBUG_DIALOG_MAIN_DIALOG, 0.0f, 0.0f, pParentScene.getLogicCamera().getCameraWidth(), pParentScene.getLogicCamera().getCameraHeight(), pParentScene, EPointModel.POINT_MODEL_CENTER);

        float scale = DebugManager.getDebugButtonScale(pParentScene.sceneId);


        int x = 50;
        int y = 10;
        TextButtonSprite button1 = new TextButtonSprite(x, y, "Debug setup param...   ", new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_MAIN__BUTTON_1, null, this), scale);

        addSprite(button1);
        y += 50;
        TextButtonSprite button2 = new TextButtonSprite(x, y, "Load debug save...", new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_MAIN__BUTTON_2, null, this), scale);
        addSprite(button2);
        y += 50;
        TextButtonSprite button3 = new TextButtonSprite(x, y, "Return", new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_MAIN__BUTTON_3, null, this), scale);
        addSprite(button3);

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
        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_MAIN__BUTTON_1) {

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
        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_MAIN__BUTTON_2) {
            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();

            parentScene.setDialogSecne(null);
            BaseLogicScene topScene = SceneFactory.createScene(parentScene.sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
            topScene.setDialogSecne(new DebugSetupDialogSaveFristScene(topScene,  ID.SCENE_INIT_TYPE.INIT));
            SceneManager.render(topScene);


        }
        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_MAIN__BUTTON_3) {
          



            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                //  BaseLogicScene topScene = SceneFactory.createScene(parentScene.sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
                SceneManager.render(parentScene);

            }
        }

    }
}

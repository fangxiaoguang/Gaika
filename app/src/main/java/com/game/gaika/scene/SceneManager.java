package com.game.gaika.scene;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import com.game.frame.scene.BaseLogicScene;
import com.game.gaika.data.GameSetup;
import com.game.frame.flyweight.BaseFlyweight;
import com.game.gaika.main.MainActivity;
import com.game.frame.scene.dialg.DialogScene;
//import com.game.gaika.profile.Profile;


public class SceneManager {

    private static BaseLogicScene topBaseLogicScene;


    public static void setGameActivity(BaseGameActivity pGameActivity) {
        MainActivity.mGameActiviy = pGameActivity;

    }

    public  static BaseLogicScene getTopBaseLogicScene(){
        return topBaseLogicScene;
    }

    public static void render(final BaseLogicScene pBaseLogicScene) {

        MainActivity.mGameActiviy.runOnUpdateThread(new Runnable() {
            @Override
            public void run() {

                BaseFlyweight.clearTagedEntity();
                SceneManager.topBaseLogicScene = pBaseLogicScene;

                Scene tScene = pBaseLogicScene.getScene();

                MainActivity.mGameActiviy.getEngine().getScene().clearChildScene();
                MainActivity.mGameActiviy.getEngine().getScene().setChildSceneModal(tScene);


//                HUD mHud = null;
                if (pBaseLogicScene.getHUDScene() != null) {
                    HUD mHud = (HUD) (pBaseLogicScene.getHUDScene().getScene());
                    MainActivity.mGameActiviy.getEngine().getCamera().setHUD(mHud);
                } else {
                    MainActivity.mGameActiviy.getEngine().getCamera().setHUD(null);
                }

                DialogScene dialogScene = pBaseLogicScene.getDialogSecne();
                if (dialogScene != null) {

                    if (MainActivity.mGameActiviy.getEngine().getCamera().getHUD() != null) {
                        MainActivity.mGameActiviy.getEngine().getCamera().getHUD().clearTouchAreas();
                    }

                    Scene lostScene = tScene;
                    lostScene.clearTouchAreas();
                    lostScene.setOnSceneTouchListener(null);

                    while (lostScene.hasChildScene()) {
                        lostScene = lostScene.getChildScene();
                        lostScene.clearTouchAreas();
                        lostScene.setOnSceneTouchListener(null);
                    }

                    Scene dialog = dialogScene.getScene();
                    dialog.setPosition(dialogScene.getFinalOffsetX(), dialogScene.getFinaOffsetY());
                    dialog.setScaleCenter(dialogScene.getWidth() / 2.0f, dialogScene.getHeight() / 2.0f);
                    dialog.setScale(1.0f / GameSetup.settingBattlefieldZoom);//fangxg
                    lostScene.setChildScene(dialog);
                }
                pBaseLogicScene.loadCameraPoint();
            }
        });
    }


}
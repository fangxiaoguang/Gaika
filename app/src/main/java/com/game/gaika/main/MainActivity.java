package com.game.gaika.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;

import com.game.frame.FSM.TouchMessage;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.SaveManager;
import com.game.gaika.debug.DebugManager;
import com.game.frame.scene.BaseLogicScene;
import com.game.gaika.scene.BeginMenuSceen;
import com.game.gaika.scene.Logo1Scene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.dialg.DebugSetupDialogMainScene;
import com.game.frame.sound.SoundManager;
import com.game.frame.sprite.BaseSprite;
import com.game.frame.texture.TexRegionManager;
import com.game.frame.tool.Tools;
import com.game.test.GameElement;
import com.game.test.Testable;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import java.io.File;
import java.util.List;

public class MainActivity extends SimpleBaseGameActivity implements Testable {
    final String TAG = "Gaika";
    public static BaseGameActivity mGameActiviy;

    @Override
    public EngineOptions onCreateEngineOptions() {
        Log.d(TAG, "-------onCreateEngineOptions------");
        GameSetup.init(this);
        initDataBase();
        initSavePngPath();
        mGameActiviy = this;

        Camera mCamera = new Camera((800 - 600 * GameSetup.deviceWidthPixels / GameSetup.deviceHeightPixels) / 2, 0, 600 * GameSetup.deviceWidthPixels
                / GameSetup.deviceHeightPixels, 600);

        final EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(GameSetup.deviceWidthPixels,
                GameSetup.deviceHeightPixels), mCamera);
        engineOptions.getAudioOptions().setNeedsSound(true);
//        engineOptions.getAudioOptions().setNeedsMusic(true);

        Handler mHandler = new Handler();
        SoundManager.getInstance().setHandler(mHandler);


        return engineOptions;
    }

    @Override
    protected void onCreateResources() {
        Log.d(TAG, "-------onCreateResources------");
        TexRegionManager.getInstance().init();
        SoundManager.getInstance().init();
        return;
    }

    @Override
    protected Scene onCreateScene() {
        Log.d(TAG, "-------onCreateScene------");
        Scene scene = new Scene();

        SaveManager.loadConfig();

        if (DebugManager.getAppRunModel() == ID.RUN_MODELE.RELESE) {
            BaseLogicScene startScene = new Logo1Scene();
            SceneManager.render(startScene);
        } else if (DebugManager.getAppRunModel() == ID.RUN_MODELE.DEBUG) {
            BeginMenuSceen beginMenuSceen = new BeginMenuSceen();
            SceneManager.render(beginMenuSceen);
        }

        return scene;
    }

    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "-------onCreate------");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "-------onStart------");
        super.onStart();
    }

    @Override
    public void onRestart() {
        Log.d(TAG, "-------onRestart------");
        super.onRestart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "-------onResume------");
        super.onResume();
        // 输出日志
    }

    @Override
    public void onPause() {
        Log.d(TAG, "-------onPause------");
        super.onPause();
        // 输出日志
    }

    @Override
    public void onStop() {
        Log.d(TAG, "-------onStop------");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "-------onDestroy------");
    }

    @Override
    public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
        BaseLogicScene topBaseLogicScene = SceneManager.getTopBaseLogicScene();

        if (pKeyCode == KeyEvent.KEYCODE_BACK) {
            if (topBaseLogicScene != null) {
                topBaseLogicScene.onHandlMessage(new TouchMessage(ID.MSG_ID.MSG_SCENE_HUD__BUTTON_BACKUP, null, topBaseLogicScene));
            }
            return true;
        } else if (pKeyCode == KeyEvent.KEYCODE_MENU) {
            Log.d(TAG, "KeyEvent.KEYCODE_MENU  pEvent.getAction() = " + pEvent.getAction());

            if (DebugManager.getAppRunModel() == ID.RUN_MODELE.DEBUG) {

                if (topBaseLogicScene != null && topBaseLogicScene.getDialogSecne() == null && GameSetup.isDebug_setupDialog == true) {
                    DebugSetupDialogMainScene debugSetupDialogMainScene = new DebugSetupDialogMainScene(topBaseLogicScene);
                    topBaseLogicScene.setDialogSecne(debugSetupDialogMainScene);
                    SceneManager.render(topBaseLogicScene);
                }
            }
            return true;
        } else {
            return super.onKeyDown(pKeyCode, pEvent);
        }
    }


    private void initDataBase() {
        // if (GameSetup.isDebug_read_SD_res == false) {

        String strApp = GameSetup.sdcartdAppPaht;
        File fApp = new File(strApp);
        if (fApp.exists() == false) {
            fApp.mkdir();
        }

        String strDB = GameSetup.sdcartdPahtDB;
        File fDB = new File(strDB);
        if (fDB.exists() == false) {
            fDB.mkdir();
        }


        String strData = GameSetup.sdcartdPahtDB + "data.db";
        File fData = new File(strData);
        if (fData.exists()) {
//            if (GameSetup.isDebug_read_SD_res == true) {
//                File pf = fData.getParentFile();
//                pf.mkdir();
//                Tools.copyAsstsFile(getAssets(), "db/data.db", strData.toString());
//            }
        } else {
            File pf = fData.getParentFile();
            pf.mkdir();
            Tools.copyAsstsFile(getAssets(), "db/data.db", fData.toString());
        }

        String strSave = GameSetup.sdcartdPahtDB + "save.db";
        File fSave = new File(strSave);
        if (fSave.exists()) {
//            if (GameSetup.isDebug_read_SD_res == true) {
//                File pf = fSave.getParentFile();
//                pf.mkdir();
//                Tools.copyAsstsFile(getAssets(), "bkData/save.db", fSave.toString());
//            }
        } else {
            /*File pf = fSave.getParentFile();
            pf.mkdir();*/
            Tools.copyAsstsFile(getAssets(), "db/save.db", fSave.toString());
        }

//        GameSetup.gameDataPath = strData.substring(0, strData.length() - "data.db".length());
        // }
    }

    private void initSavePngPath() {
        String pahtSave = GameSetup.sdcartdPahtSave;
        File pfSave = new File(pahtSave);
        File pParSave = pfSave.getParentFile();
        if (pParSave.exists() == false) {
            pParSave.mkdir();
        }
        if (pfSave.exists() == false) {
            pfSave.mkdir();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        String screen = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕" : "竖向屏幕";
        Log.d(TAG, screen);

        Log.d(TAG, "++++++++++++++------------" + screen);
    }

    @Override
    public ID.SCENE_ID getCurrentSceneId() {
        return SceneManager.getTopBaseLogicScene().sceneId;

    }

    @Override
    public BaseLogicScene getCurrentScene() {
        return SceneManager.getTopBaseLogicScene();
    }

    @Override
    public GameElement getElement(String id) {
        BaseLogicScene scene = getCurrentScene();

        List<BaseSprite> sl = scene.getSpriteList();
        for(int i = 0; i < sl.size(); i ++){
            BaseSprite s = sl.get(i);

            if(id.equals(s.getId())){
                return new GameElement(scene, s);
            }

        }

        return null;
    }

}

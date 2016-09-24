package com.game.gaika.scene;

import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.City;
import com.game.gaika.data.GameChapter;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.SaveManager;
import com.game.gaika.scene.camera.BaseLogicCamera;
import com.game.gaika.scene.camera.CameraRange;
import com.game.gaika.scene.camera.ZoomSceneCamera;
import com.game.gaika.scene.hud.BattlefieldEnterInfoHUDScene;
import com.game.gaika.sprite.CitySprite;
import com.game.gaika.sprite.NormalSprite;

import org.andengine.input.touch.detector.SurfaceScrollDetector;

import static com.game.gaika.sound.SoundManager.playSound;

/**
 * Created by fangxg on 2015/8/17.
 */
public class BattlefieldEnterInfoScene extends BaseLogicScene implements IMessageHandler {
    private static final int Z_INDEX_1 = 1;
    private static final int Z_INDEX_2 = 2;
    private SurfaceScrollDetector mScrollDetector;
    private int newSelectedChapterID;

    public BattlefieldEnterInfoScene(int pNewSelectedChapterID) {
        super(ID.SCENE_ID.BATTLEFIELD_ENTER_INFO,
                GameDataManager.getInstance().chapters.get(pNewSelectedChapterID).getGameMap().getMapSizePixelX(),
                GameDataManager.getInstance().chapters.get(pNewSelectedChapterID).getGameMap().getMapSizePixelY());

        newSelectedChapterID = pNewSelectedChapterID;

        GameDataManager gdm = GameDataManager.getInstance();
        SceneValueMap sceneValues = getSceneValuesMap();
        sceneValues.cleanAllValues();
        for (City city : gdm.chapters.get(pNewSelectedChapterID).getGameMap().citys.values()) {
            if (city.teamColor == ID.TEAM_COLOR.BLUE && city.isPoint == true) {
                sceneValues.setFloat("CameraCenterX", 0);
                sceneValues.setFloat("CameraCenterY", 0);
                getLogicCamera().setCente(city.getPixelX(), city.getPixelY());
                break;
            }
        }
    }

    @Override
    public boolean isBacegroundEnabled() {
        return false;
    }

    @Override
    public void buildScene() {
        GameDataManager gdm = GameDataManager.getInstance();
        // draw map backup
        NormalSprite backSprite = new NormalSprite(0.0f, 0.0f, gdm.chapters.get(newSelectedChapterID).getGameMap().name, 0, null, 2.0f);
        backSprite.setZ(Z_INDEX_1);
        addSprite(backSprite);


        // draw city
        for (City cityNode : gdm.chapters.get(newSelectedChapterID).getGameMap().citys.values()) {


            CitySprite citySprite = new CitySprite(cityNode, null);
            citySprite.setZ(Z_INDEX_2);
            addSprite(citySprite);
        }
        //draw HUD
        CameraRange cr = getLogicCamera().getCameraRenge();

        setHUDScene(new BattlefieldEnterInfoHUDScene(cr.xMax - cr.xMin, cr.yMax - cr.yMin, this, newSelectedChapterID));

    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {
        GameDataManager gdm = GameDataManager.getInstance();
        Enum msgID = pTouchMessage.getMessageID();

        int finishedCount = 0;
        for (GameChapter chapter : gdm.chapters.values()) {
            if (chapter.finished == true) {
                finishedCount++;
            }
        }

        if (msgID == ID.MSG_ID.MSG_SCENE_BATTLEFIELD_ENTER_INFO_HUD__BUTTON1) { //enter
             playSound("select01");
            SaveManager.enterChapter(newSelectedChapterID);

            if (finishedCount == 0) {
                TutorialScene tutorialScene = new TutorialScene(TutorialScene.InitID.INIT_DATA_1);
                SceneManager.render(tutorialScene);
            } else if (finishedCount == 1) {
                TutorialScene tutorialScene = new TutorialScene(TutorialScene.InitID.INIT_DATA_2);
                SceneManager.render(tutorialScene);
            } else if (finishedCount == 2) {
                TutorialScene tutorialScene = new TutorialScene(TutorialScene.InitID.INIT_DATA_3);
                SceneManager.render(tutorialScene);
            } else {
                BattlefieldScene battlefieldScene = new BattlefieldScene(true);
                SceneManager.render(battlefieldScene);
            }
        }
        if (msgID == ID.MSG_ID.MSG_SCENE_BATTLEFIELD_ENTER_INFO_HUD__BUTTON2) { // back
             playSound("back01");
            if (finishedCount == 0) {
                BeginCountyScene beginCountyScene = new BeginCountyScene();
                SceneManager.render(beginCountyScene);
            } else {
                SelectTargetScene selectTargetScene = new SelectTargetScene(true);
                SceneManager.render(selectTargetScene);
            }
        }
    }


    @Override
    public BaseLogicCamera getLogicCamera() {
        GameDataManager gdm = GameDataManager.getInstance();
        BaseLogicCamera camera = new ZoomSceneCamera(width, height, GameSetup.battlefieldBaseZoom * GameSetup.settingBattlefieldZoom);
        camera.setParentScene(this);
        return camera;
    }

}

package com.game.gaika.scene;


import com.game.frame.scene.BaseLogicScene;
import com.game.gaika.action.AiBuyNextAction;
import com.game.gaika.action.AiBuyTimeOutAction;
import com.game.gaika.action.AiCapliuringTimeOutAction;
import com.game.gaika.action.AiCheckWinTimeOutAction;
import com.game.gaika.action.AiMoveToDescTimeOutAction;
import com.game.gaika.action.AiRepairNextAction;
import com.game.gaika.action.AiRepairTimeOutAction;
import com.game.gaika.action.AiSelectNonNextAction;
import com.game.gaika.action.AiSelectNonTimeOutAction;
import com.game.gaika.action.AiSelectWeaponTimeOutAction;
import com.game.gaika.action.AiTurnBeginTimeOutAction;
import com.game.gaika.action.BlueRepairNextAction;
import com.game.gaika.action.BlueRepairNoAction;
import com.game.gaika.action.BlueRepairTimeOutAction;
import com.game.gaika.action.BlueRepairYesAction;
import com.game.gaika.action.BlueTurnBeginTimeOutAction;
import com.game.gaika.action.ButtonSystemAction;
import com.game.gaika.FSM.FSMClass;
import com.game.gaika.FSM.FSMState;
import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.StateChange;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.action.SelectJustInfoWeaponAction;
import com.game.gaika.data.GameSetup;
import com.game.gaika.scene.dialg.SelectBuleSelfDilogYes;
import com.game.gaika.action.BackBtnToNoneAction;
import com.game.gaika.action.BackBtnToSelectedAction;
import com.game.gaika.action.BaseAction;
import com.game.gaika.action.CapliuringTimeOutAction;
import com.game.gaika.action.CheckWinTimeOutAction;
import com.game.gaika.action.CrashNextAction;
import com.game.gaika.action.CrashTimeOutAction;
import com.game.gaika.action.FightTimeOutAction;
import com.game.gaika.action.GameOverDilogNoAction;
import com.game.gaika.action.GameOverDilogYesAction;
import com.game.gaika.action.GetSomeThingTimeOutAction;
import com.game.gaika.action.LvUpTimeOutAction;
import com.game.gaika.action.SelectBuleCityAction;
import com.game.gaika.action.SelectBuleSelfAction;
import com.game.gaika.action.SelectBuleSelfDilogNo;
import com.game.gaika.action.SelectBuleWeaponAction;
import com.game.gaika.action.SelectChooseArmsDlgAirWeaponAction;
import com.game.gaika.action.SelectChooseArmsDlgGroundWeaponAction;
import com.game.gaika.action.SelectChooseArmsWeaponAction;
import com.game.gaika.action.SelectEnemyWeaponAction;
import com.game.gaika.action.SelectGrrenBoxAction;
import com.game.gaika.action.SelectPassengerAction;
import com.game.gaika.action.SelectTransporterIntoAction;
import com.game.gaika.action.SelectTransporterOutAction;
import com.game.gaika.action.TurnFinishAction;
import com.game.gaika.action.TurnFinishDilogNoAction;
import com.game.gaika.action.TurnFinishDilogYesAction;
import com.game.gaika.data.City;
import com.game.gaika.data.ColorBox;
import com.game.gaika.data.EffectNode;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameMap;
import com.game.gaika.data.ID;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.debug.DebugManager;
import com.game.gaika.main.MainActivity;
import com.game.frame.scene.camera.BaseLogicCamera;
import com.game.frame.scene.camera.CameraRange;
import com.game.frame.scene.camera.ZoomSceneCamera;
import com.game.gaika.scene.hud.BattlefieldHUDScene;
import com.game.gaika.sprite.BattlefieldWeaponSprite;
import com.game.gaika.sprite.CitySprite;
import com.game.gaika.sprite.ColorBoxSprite;
import com.game.gaika.sprite.EffectSprite;
import com.game.frame.sprite.NormalSprite;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.input.touch.detector.SurfaceScrollDetector;

import java.util.Collection;
import java.util.List;

import static com.game.gaika.data.ID.MSG_ID.*;
import static com.game.gaika.data.ID.SCENE_ID.*;

import static com.game.gaika.scene.BattlefieldScene.StateID.*;
import static com.game.frame.sound.SoundManager.playSound;


/**
 * Created by fangxg on 2015/6/21.
 */
public class BattlefieldScene extends BaseLogicScene implements IOnSceneTouchListener, IScrollDetectorListener, IMessageHandler, StateChange {
    private static final int Z_INDEX_1 = 1;
    private static final int Z_INDEX_2 = 2;
    private static final int Z_INDEX_3 = 3;
    private static final int Z_INDEX_4 = 4;
    private static final int Z_INDEX_5 = 5;
    private static final int Z_INDEX_6 = 6;

    public enum StateID {
        STATE_ID_SELECTED_NON, STATE_ID_SELECTED_WEAPON, STATE_ID_MOVE_TO_DESC,
        STATE_ID_FIGHT, STATE_ID_LV_UP, STATE_ID_CAPTURING, STATE_ID_GET_SOME_THING, STATE_ID_CHECK_WIN,
        STATE_ID_CRASH, STATE_ID_AI_TURN_BEGIN, STATE_ID_BLUE_REPAIR, STATE_ID_AI_REPAIR, STATE_ID_AI_BUY, STATE_ID_AI_SELECT_NON,
        STATE_ID_AI_SELECTED_WEAPON, STATE_ID_AI_MOVE_TO_DESC, STATE_ID_AI_FIGHT, STATE_ID_AI_LV_UP,
        STATE_ID_AI_CAPLTURING, STATE_ID_AI_CHECK_WIN, STATE_ID_AI_CRASH
    }


    private SurfaceScrollDetector mScrollDetector;
    private int infoCardWeaponID = -1;

    public BattlefieldScene(boolean isInit) {
        super(BATTLEFIELD,
                GameDataManager.getInstance().getCurrentChapter().getGameMap().getMapSizePixelX(),
                GameDataManager.getInstance().getCurrentChapter().getGameMap().getMapSizePixelY());


        if (isInit == true) {
            SceneValueMap sceneValues = getSceneValuesMap();
            sceneValues.cleanAllValues();
            sceneValues.setFloat("CameraCenterX", 0);
            sceneValues.setFloat("CameraCenterY", 0);

            GameDataManager gdm = GameDataManager.getInstance();
            City cityPoint = gdm.getPointCity(gdm.getCurrentAiTeamColor());
            getLogicCamera().setCente(cityPoint.getPixelX(), cityPoint.getPixelY());
            Collection<City> citys = gdm.getCurrentChapter().getGameMap().citys.values();


            buildFSM(STATE_ID_SELECTED_NON);
        }
    }

    @Override
    public void buildScene() {
        GameDataManager gdm = GameDataManager.getInstance();
        // draw map backup
        NormalSprite backSprite = new NormalSprite(0.0f, 0.0f, gdm.getCurrentChapter().getGameMap().name, 0, null, 2.0f);
        backSprite.setZ(Z_INDEX_1);
        addSprite(backSprite);

        // draw city
        for (City cityNode : gdm.getCurrentChapter().getGameMap().citys.values()) {

            TouchMessage msg = null;

            GameMap gameMap = GameDataManager.getInstance().getCurrentChapter().getGameMap();
            if (getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_NON) {
                if (cityNode.teamColor == ID.TEAM_COLOR.BLUE && cityNode.isBase == true) {

                    WeaponSelectFilter filter = new WeaponSelectFilter();
                    /*filter.setTeamOut(true);
                    filter.setSetOut(true);
                    filter.addTeamColor(ID.TEAM_COLOR.BLUE);
                    filter.addTeamColor(ID.TEAM_COLOR.RED);
                    filter.addTeamColor(ID.TEAM_COLOR.YELLOW);
                    filter.addTeamColor(ID.TEAM_COLOR.GREEN);
                    filter.setLifeNotZero();*/
                    filter.setShowInBattlefield();
                    filter.setMapXY(cityNode.getMapX(), cityNode.getMapY());
                    List<BaseWeapon> weapNodes = gdm.getWeapons(filter);

                    if (weapNodes.size() == 0) {
                        msg = new TouchMessage(MSG_SCENE_BATTLEFIELD__SELECT_CITY, null, this, cityNode.id);
                    }
                }
            }

            CitySprite citySprite = new CitySprite(cityNode, msg);
            citySprite.setZ(Z_INDEX_2);
            addSprite(citySprite);
        }


        //draw clolrBoxs
        Collection<ColorBox> boxs = gdm.getCurrentChapter().getGameMap().boxs.values();

        for (ColorBox box : boxs) {
            TouchMessage msg = null;
            if (getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_WEAPON) {
                WeaponSelectFilter filter = new WeaponSelectFilter();
                filter.setMapXY(box.x, box.y);
                filter.setSetOutInBattlefield();
                List<BaseWeapon> weapons = gdm.getWeapons(filter);
                if (weapons.size() == 0) {
                    msg = new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__SELECT_DESC, null, this, box.x * 100 + box.y);
                }
            }
            ColorBoxSprite boxSprite = new ColorBoxSprite(box, msg);
            boxSprite.setZ(Z_INDEX_3);
            addSprite(boxSprite);
        }

        // draw weapon Node
        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.setSetOutInBattlefield();
        List<BaseWeapon> weapons = gdm.getWeapons(filter);
        for (BaseWeapon weapon : weapons) {

            TouchMessage msg = null;
            if (getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_NON) {
                //all blue weapon
                City city = gdm.getCurrentChapter().getGameMap().citys.get(weapon.x * 100 + weapon.y);
                if (city != null && city.teamColor == ID.TEAM_COLOR.BLUE && city.type == ID.CITY_TYPE.AIRPORT && weapon.getPassengers().size() > 0) {
                    msg = new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__SELECT_TRANSPORTER, null, this, weapon.id);
                } else if (weapon.teamColor == ID.TEAM_COLOR.BLUE && weapon.moveEnding == false && weapon.canChooseArms() == true) {
                    msg = new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__SELECT_CHOOSE_ARMS_WEAPON, null, this, weapon.id);
                } else if (weapon.teamColor == ID.TEAM_COLOR.BLUE && weapon.moveEnding == false) {
                    msg = new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__SELECT_WEAPON, null, this, weapon.id);
                } else {
                    msg = new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__SELECT_JUST_INFO_WEAPON, null, this, weapon.id);
                }
            }
            if (getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_WEAPON) {
                //my self blue weapon
                if (weapon.teamColor == ID.TEAM_COLOR.BLUE && weapon.selected == true) {
                    msg = new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__SELECT_SELF, null, this, weapon.id);
                }
                //weapon weapon
                if (gdm.getCurrentChapter().side2.contains(weapon.teamColor) == true
                        && (weapon.advantage == ID.ADVANTAGE.EASY
                        || weapon.advantage == ID.ADVANTAGE.NORMAL
                        || weapon.advantage == ID.ADVANTAGE.HARD)) {
                    msg = new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__SELECT_ENEMY, null, this, weapon.id);
                }
                if (weapon.getTransportFlagTexIndex() != null) {
                    msg = new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__SELECT_TRANSPORTER, null, this, weapon.id);
                }
            }

            if (getFSMClass().getCurrentStateID() == STATE_ID_MOVE_TO_DESC) {
                //my self blue weapon
                if (weapon.teamColor == ID.TEAM_COLOR.BLUE && weapon.selected == true) {
                    msg = new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__SELECT_SELF, null, this, weapon.id);
                }
                //weapon weapon
                if (gdm.getCurrentChapter().side2.contains(weapon.teamColor) == true
                        && (weapon.advantage == ID.ADVANTAGE.EASY
                        || weapon.advantage == ID.ADVANTAGE.NORMAL
                        || weapon.advantage == ID.ADVANTAGE.HARD)) {
                    msg = new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__SELECT_ENEMY, null, this, weapon.id);
                }
            }

            BattlefieldWeaponSprite weaponSprite = new BattlefieldWeaponSprite(weapon, msg);
            weaponSprite.setZ(Z_INDEX_4);
            addSprite(weaponSprite);
        }

        //draw Effect
        for (EffectNode effectNode : gdm.effectNodes) {
            if (effectNode.id.equals(EffectNode.EEffectID.EFFECT_ID_MARKER04)) {
                 playSound("m_effe01");
                EffectSprite effectSprite = new EffectSprite(effectNode);
                effectSprite.setZ(Z_INDEX_5);
                addSprite(effectSprite);
            }
        }
        if (gdm.effectNodes.size() > 0) {
            gdm.effectNodes.clear();
        }

        //draw HUD
        CameraRange cr = getLogicCamera().getCameraRenge();
        if (getFSMClass().getCurrentStateID() == STATE_ID_SELECTED_NON) {
            BattlefieldHUDScene hudScene = new BattlefieldHUDScene(cr.xMax - cr.xMin, cr.yMax - cr.yMin, this, this.infoCardWeaponID);
            setHUDScene(hudScene);
        } else {
            BattlefieldHUDScene hudScene = new BattlefieldHUDScene(cr.xMax - cr.xMin, cr.yMax - cr.yMin, null, this.infoCardWeaponID);
            setHUDScene(hudScene);
        }
    }

    @Override
    public boolean isBacegroundEnabled() {
        return false;
    }

    @Override
    public boolean onSceneTouchEvent(Scene scene, TouchEvent pSceneTouchEvent) {
        if (pSceneTouchEvent.isActionDown()) {
            this.mScrollDetector.setEnabled(true);
        }
        this.mScrollDetector.onTouchEvent(pSceneTouchEvent);
        return true;
    }

    @Override
    public void onScrollStarted(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY) {
    }

    @Override
    public void onScroll(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY) {
        GameDataManager gd = GameDataManager.getInstance();
        Camera camera = MainActivity.mGameActiviy.getEngine().getCamera();

        float centerX = camera.getCenterX() - pDistanceX;
        float centerY = camera.getCenterY() - pDistanceY;
        if (centerX < camera.getWidth() / 2.0f) {
            centerX = camera.getWidth() / 2.0f;
        } else if (centerX > width - camera.getWidth() / 2.0f) {
            centerX = width - camera.getWidth() / 2.0f;
        }
        if (centerY < camera.getHeight() / 2.0f) {
            centerY = camera.getHeight() / 2.0f;
        } else if (centerY > height - camera.getHeight() / 2.0f) {
            centerY = height - camera.getHeight() / 2.0f;
        }

        camera.setCenter(centerX, centerY);


        getLogicCamera().setCente(centerX, centerY);
    }

    @Override
    public void onScrollFinished(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY) {

        // Log.d("SEL", "onScrollFinished = x: " + pDistanceX);
    }

    @Override
    public Scene getScene() {
        this.mScrollDetector = new SurfaceScrollDetector(this);
        Scene bkScne = super.getScene();
        bkScne.setOnSceneTouchListener(this);
        return bkScne;
    }


    @Override
    public BaseLogicCamera getLogicCamera() {
        GameDataManager gdm = GameDataManager.getInstance();
        BaseLogicCamera camera = new ZoomSceneCamera(width, height, GameSetup.settingBattlefieldZoom * GameSetup.battlefieldBaseZoom);
        camera.setParentScene(this);
        return camera;
    }

    /*public void resetCameraCenterFromXY(float pX, float pY) {

        SceneValueMap sceneValues = getSceneValuesMap();

        sceneValues.setFloat("CameraCenterX", pX);
        sceneValues.setFloat("CameraCenterY", pY);


        //        setSmallMapBoxPoint();
        BattlefieldHUDScene battlefieldHUDScene = (BattlefieldHUDScene) getHUDScene();
        if (battlefieldHUDScene != null) {
            float tx = getLogicCamera().getCameraWidth() / 2.0f;
            battlefieldHUDScene.setSmallMapBoxPoint(pX - getLogicCamera().getCameraWidth() / 2.0f, pY - getLogicCamera().getCameraHeight() / 2.0f);
//            battlefieldHUDScene.setSmallMapBoxPoint(pX, pY );
//            IEntity wbox = battlefieldHUDScene.bkScne.getChildByTag(10086);
//            IEntity wbox2 = battlefieldHUDScene.bkScne.getChildByTag(10086);
        }

    }*/

    @Override
    public void onStateChanged(Enum oldState, Enum newState, TouchMessage pTouchMessage) {
        GameDataManager gdm = GameDataManager.getInstance();


//        if (oldState != newState) {
//
//            BeginLocalScene beginLocalScene = new BeginLocalScene(newState);
//
//            SceneManager.render(beginLocalScene);
//        }
//        if (oldState == newState) {
//
//
//
//            BeginCountyScene scene = new BeginCountyScene();
//            SceneManager.render(scene);
//        }
    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {
        GameDataManager gdm = GameDataManager.getInstance();
        Enum msgID = pTouchMessage.getMessageID();

        FSMClass fsm = getFSMClass();
        Enum oldState = fsm.getCurrentStateID();
        fsm.stateTransition(msgID);
        Enum newState = fsm.getCurrentStateID();

//        DebugManager.onStateChenge(oldState, newState);


        if (oldState == STATE_ID_SELECTED_NON) {

//            if (msgID == MSG_SCENE_HUD__BUTTON_BACKUP) {
//                int cityID = pTouchMessage.getParam();
//                BaseAction act = new SelectBuleCityAction(cityID);
//                act.doAction();
//            }

            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_CITY) {
                int cityID = pTouchMessage.getParam();
                BaseAction act = new SelectBuleCityAction(cityID);
                act.doAction();
            }

            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_WEAPON) {
                 playSound("unit01a");
                int weaponID = pTouchMessage.getParam();
                BaseAction act = new SelectBuleWeaponAction(weaponID);
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_JUST_INFO_WEAPON) {
                 playSound("unit01a");
                int weaponID = pTouchMessage.getParam();
                BaseAction act = new SelectJustInfoWeaponAction(weaponID);
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_CHOOSE_ARMS_WEAPON) {
                 playSound("unit01a");
                int weaponID = pTouchMessage.getParam();
                BaseAction act = new SelectChooseArmsWeaponAction(weaponID);
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_CHOOSE_ARMS_DLG_AIR) {
                int weaponID = pTouchMessage.getParam();
                BaseAction act = new SelectChooseArmsDlgAirWeaponAction(weaponID);
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_CHOOSE_ARMS_DLG_GROUND) {
                int weaponID = pTouchMessage.getParam();
                BaseAction act = new SelectChooseArmsDlgGroundWeaponAction(weaponID);
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_TRANSPORTER) {
                 playSound("unit01a");
                int transporterID = pTouchMessage.getParam();
                BaseAction act = new SelectTransporterOutAction(transporterID);
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_PASSENGER) {
                int weaponID = pTouchMessage.getParam();
                BaseAction act = new SelectPassengerAction(weaponID);
                act.doAction();
            }
            if (msgID == MSG_SCENE_HUD__TURN_FINISH) {
                 playSound("messag01");
                BaseAction act = new TurnFinishAction();
                act.doAction();
            }
            if (msgID == MSG_SCENE_HUD__TURN_FINISH_DILOG_YES) {
                if (GameSetup.isDebug_trackingUserProcess == true) {
                    DebugManager.debugSave();
                }
                 playSound("select01");
                BaseAction act = new TurnFinishDilogYesAction();
                act.doAction();
            }
            if (msgID == MSG_SCENE_HUD__TURN_FINISH_DILOG_NO) {
                 playSound("back01");
                BaseAction act = new TurnFinishDilogNoAction();
                act.doAction();
            }
            if (msgID == MSG_SCENE_HUD__BUTTON_SYSTEM) {
                 playSound("select01");
                BaseAction act = new ButtonSystemAction();
                act.doAction();
            }
            if (msgID == MSG_SCENE_HUD__GAME_OVER_DILOG_YES) {
                 playSound("select01");
                BaseAction act = new GameOverDilogYesAction();
                act.doAction();
            }
            if (msgID == MSG_SCENE_HUD__GAME_OVER_DILOG_NO) {
                 playSound("back01");
                BaseAction act = new GameOverDilogNoAction();
                act.doAction();
            }
        }

        if (oldState == STATE_ID_SELECTED_WEAPON) {
            if (msgID == MSG_SCENE_HUD__BUTTON_BACKUP) {
                 playSound("back01");
                BaseAction act = new BackBtnToNoneAction();
                act.doAction();
            }

            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_SELF) {
                 playSound("messag01");
                BaseAction act = new SelectBuleSelfAction();
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_SELF_DLG_YES) {
                 playSound("select01");
                BaseAction act = new SelectBuleSelfDilogYes();
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_SELF_DLG_NO) {
                 playSound("back01");
                BaseAction act = new SelectBuleSelfDilogNo();
                act.doAction();
            }

            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_DESC) {
                int boxID = pTouchMessage.getParam();
                BaseAction act = new SelectGrrenBoxAction(boxID);
                act.doAction();
            }

            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_ENEMY) {
                int enemyID = pTouchMessage.getParam();
                BaseAction act = new SelectEnemyWeaponAction(enemyID);
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_TRANSPORTER) {
                int transporterID = pTouchMessage.getParam();
                BaseAction act = new SelectTransporterIntoAction(transporterID);
                act.doAction();
            }


        }
        if (oldState == STATE_ID_MOVE_TO_DESC) {
            if (msgID == MSG_SCENE_HUD__BUTTON_BACKUP) {
                 playSound("back01");
                BaseAction act = new BackBtnToSelectedAction();
                act.doAction();
            }

            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_SELF) {
                 playSound("messag01");
                BaseAction act = new SelectBuleSelfAction();
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_SELF_DLG_YES) {
                 playSound("select01");
                BaseAction act = new SelectBuleSelfDilogYes();
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_SELF_DLG_NO) {
                 playSound("back01");
                BaseAction act = new SelectBuleSelfDilogNo();
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__SELECT_ENEMY) {
                int enemyID = pTouchMessage.getParam();
                BaseAction act = new SelectEnemyWeaponAction(enemyID);
                act.doAction();
            }
        }

        if (oldState == STATE_ID_FIGHT) {
            if (msgID == MSG_SCENE_BATTLEFIELD__FIGHT_TIME_OUT) {
                int weaponID = pTouchMessage.getParam();
                BaseAction act = new FightTimeOutAction(weaponID);
                act.doAction();
            }
        }

        if (oldState == STATE_ID_LV_UP) {
            if (msgID == MSG_SCENE_BATTLEFIELD__LV_UP_TIME_OUT) {
                int weaponID = pTouchMessage.getParam();
                BaseAction act = new LvUpTimeOutAction(weaponID);
                act.doAction();

            }
        }
        if (oldState == STATE_ID_CAPTURING) {
            if (msgID == MSG_SCENE_BATTLEFIELD__CAPTURING_TIME_OUT) {
                int weaponID = pTouchMessage.getParam();
                BaseAction act = new CapliuringTimeOutAction(weaponID);
                act.doAction();
            }
        }
        if (oldState == STATE_ID_GET_SOME_THING) {
            if (msgID == MSG_SCENE_BATTLEFIELD__GET_SOME_THING_TIME_OUT) {
                int weaponID = pTouchMessage.getParam();
                BaseAction act = new GetSomeThingTimeOutAction(weaponID);
                act.doAction();
            }
        }
        if (oldState == STATE_ID_CHECK_WIN) {
            if (msgID == MSG_SCENE_BATTLEFIELD__CHECK_WIN_TIME_OUT) {
                int weaponID = pTouchMessage.getParam();
                BaseAction act = new CheckWinTimeOutAction(weaponID);
                act.doAction();
            }
        }

        if (oldState == STATE_ID_CRASH) {
            if (msgID == MSG_SCENE_BATTLEFIELD__CRASH_NEXT) {
                BaseAction act = new CrashNextAction();
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__CRASH_TIME_OUT) {

                BaseAction act = new CrashTimeOutAction();
                act.doAction();
            }
        }

        if (oldState == STATE_ID_AI_TURN_BEGIN) {
            if (msgID == MSG_SCENE_BATTLEFIELD__BLUE_TURN_BEGIN_TIME_OUT) {
                BaseAction act = new BlueTurnBeginTimeOutAction();
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__AI_TURN_BEGIN_TIME_OUT) {
                BaseAction act = new AiTurnBeginTimeOutAction();
                act.doAction();
            }
        }
        if (oldState == STATE_ID_AI_REPAIR) {
            if (msgID == MSG_SCENE_BATTLEFIELD__AI_REPAIR_NEXT) {
                BaseAction act = new AiRepairNextAction();
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__AI_REPAIR_TIME_OUT) {
                BaseAction act = new AiRepairTimeOutAction();
                act.doAction();
            }
        }

        if (oldState == STATE_ID_AI_BUY) {
            if (msgID == MSG_SCENE_BATTLEFIELD__AI_BUY_NEXT) {
                BaseAction act = new AiBuyNextAction();
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__AI_BUY_TIME_OUT) {
                BaseAction act = new AiBuyTimeOutAction();
                act.doAction();
            }
        }
        if (oldState == STATE_ID_AI_SELECT_NON) {
            if (msgID == MSG_SCENE_BATTLEFIELD__AI_SELECT_NON_NEXT) {
                BaseAction act = new AiSelectNonNextAction();
                act.doAction();
            }
        }
        if (oldState == STATE_ID_AI_SELECTED_WEAPON) {
            if (msgID == MSG_SCENE_BATTLEFIELD__AI_SELECT_WEAPON_TIME_OUT) {
                int aiWeaponID = pTouchMessage.getParam();
                BaseAction act = new AiSelectWeaponTimeOutAction(aiWeaponID);
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__AI_SELECT_NON_TIME_OUT) {
                int aiWeaponID = pTouchMessage.getParam();
                BaseAction act = new AiSelectNonTimeOutAction(aiWeaponID);
                act.doAction();
            }
        }
        if (oldState == STATE_ID_AI_MOVE_TO_DESC) {
            if (msgID == MSG_SCENE_BATTLEFIELD__AI_MOVE_TO_DESC_TIME_OUT) {
                int aiWeaponID = pTouchMessage.getParam();
                BaseAction act = new AiMoveToDescTimeOutAction(aiWeaponID);
                act.doAction();
            }
        }

        if (oldState == STATE_ID_AI_FIGHT) {
            if (msgID == MSG_SCENE_BATTLEFIELD__AI_FIGHT_TIME_OUT) {
                int aiWeaponID = pTouchMessage.getParam();
                BaseAction act = new FightTimeOutAction(aiWeaponID); //new AiFightTimeOutAction(aiWeaponID);
                act.doAction();
            }
        }

        if (oldState == STATE_ID_AI_LV_UP) {
            if (msgID == MSG_SCENE_BATTLEFIELD__LV_UP_TIME_OUT) {
                int aiWeaponID = pTouchMessage.getParam();
                BaseAction act = new LvUpTimeOutAction(aiWeaponID);
                act.doAction();
            }
        }
        if (oldState == STATE_ID_AI_CAPLTURING) {
            if (msgID == MSG_SCENE_BATTLEFIELD__CAPTURING_TIME_OUT) {
                int aiWeaponID = pTouchMessage.getParam();
                BaseAction act = new AiCapliuringTimeOutAction(aiWeaponID);
                act.doAction();
            }
        }

        if (oldState == STATE_ID_AI_CHECK_WIN) {
            if (msgID == MSG_SCENE_BATTLEFIELD__CHECK_WIN_TIME_OUT) {
                int weaponID = pTouchMessage.getParam();
                BaseAction act = new AiCheckWinTimeOutAction(weaponID);
                act.doAction();
            }
        }
        if (oldState == STATE_ID_AI_CRASH) {
            if (msgID == MSG_SCENE_BATTLEFIELD__CRASH_NEXT) {
                BaseAction act = new CrashNextAction();
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__CRASH_TIME_OUT) {
                BaseAction act = new CrashTimeOutAction();
                act.doAction();
            }
        }
        if (oldState == STATE_ID_BLUE_REPAIR) {
            if (msgID == MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_NEXT) {
                //找到一个可以修理的单位， 显示对话框。
                BaseAction act = new BlueRepairNextAction();
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_YES) {
                //修理，然后打mark， next
                 playSound("select01");
                int weaponID = pTouchMessage.getParam();
                BaseAction act = new BlueRepairYesAction(weaponID);
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_NO) {
                //不修理，然后打mark， next
                 playSound("back01");
                BaseAction act = new BlueRepairNoAction();
                act.doAction();
            }
            if (msgID == MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_TIME_OUT) {
                //迁移到NON状态，显示BattScene画面，啥也不做。
                BaseAction act = new BlueRepairTimeOutAction();
                act.doAction();
            }
        }

    }

    @Override
    public void buildFSM(Enum pCurrentStateID) {
        addFSMClass(new FSMClass(pCurrentStateID));

        FSMState stateNon1 = new FSMState(STATE_ID_SELECTED_NON);
        stateNon1.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_SELECTED_NON);
        stateNon1.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_CITY, STATE_ID_SELECTED_NON);
        stateNon1.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_WEAPON, STATE_ID_SELECTED_WEAPON);
        stateNon1.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_JUST_INFO_WEAPON, STATE_ID_SELECTED_NON);

        stateNon1.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_CHOOSE_ARMS_WEAPON, STATE_ID_SELECTED_NON);
        stateNon1.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_CHOOSE_ARMS_DLG_AIR, STATE_ID_SELECTED_WEAPON);
        stateNon1.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_CHOOSE_ARMS_DLG_GROUND, STATE_ID_SELECTED_WEAPON);

        stateNon1.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_TRANSPORTER, STATE_ID_SELECTED_NON);
        stateNon1.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_PASSENGER, STATE_ID_SELECTED_WEAPON);

        stateNon1.addTransition(MSG_SCENE_HUD__TURN_FINISH, STATE_ID_SELECTED_NON);
        stateNon1.addTransition(MSG_SCENE_HUD__TURN_FINISH_DILOG_YES, STATE_ID_CRASH);
        stateNon1.addTransition(MSG_SCENE_HUD__TURN_FINISH_DILOG_NO, STATE_ID_SELECTED_NON);

        stateNon1.addTransition(MSG_SCENE_HUD__BUTTON_SYSTEM, STATE_ID_SELECTED_NON);

        stateNon1.addTransition(MSG_SCENE_HUD__GAME_OVER_DILOG_YES, STATE_ID_SELECTED_NON);
        stateNon1.addTransition(MSG_SCENE_HUD__GAME_OVER_DILOG_NO, STATE_ID_SELECTED_NON);
        getFSMClass().addState(stateNon1);


        FSMState stateNon2 = new FSMState(STATE_ID_SELECTED_WEAPON);
        stateNon2.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_SELECTED_NON);

        stateNon2.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_SELF, STATE_ID_SELECTED_WEAPON);
        stateNon2.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_SELF_DLG_YES, STATE_ID_LV_UP);
        stateNon2.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_SELF_DLG_NO, STATE_ID_SELECTED_WEAPON);

        stateNon2.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_DESC, STATE_ID_MOVE_TO_DESC);
        stateNon2.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_ENEMY, STATE_ID_FIGHT);
        stateNon2.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_TRANSPORTER, STATE_ID_SELECTED_NON);
        getFSMClass().addState(stateNon2);


        FSMState stateNon3 = new FSMState(STATE_ID_MOVE_TO_DESC);
        stateNon3.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_SELECTED_WEAPON);
        stateNon3.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_SELF, STATE_ID_MOVE_TO_DESC);
        stateNon3.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_SELF_DLG_YES, STATE_ID_LV_UP);
        stateNon3.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_SELF_DLG_NO, STATE_ID_MOVE_TO_DESC);
        stateNon3.addTransition(MSG_SCENE_BATTLEFIELD__SELECT_ENEMY, STATE_ID_FIGHT);
        getFSMClass().addState(stateNon3);


        FSMState stateNon4 = new FSMState(STATE_ID_FIGHT);
        stateNon4.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_FIGHT);
        stateNon4.addTransition(MSG_SCENE_BATTLEFIELD__FIGHT_TIME_OUT, STATE_ID_LV_UP);
        getFSMClass().addState(stateNon4);

        FSMState stateNon5 = new FSMState(STATE_ID_LV_UP);
        stateNon5.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_LV_UP);
        stateNon5.addTransition(MSG_SCENE_BATTLEFIELD__LV_UP_TIME_OUT, STATE_ID_CAPTURING);
        getFSMClass().addState(stateNon5);

        FSMState stateNon6 = new FSMState(STATE_ID_CAPTURING);
        stateNon6.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_CAPTURING);
        stateNon6.addTransition(MSG_SCENE_BATTLEFIELD__CAPTURING_TIME_OUT, STATE_ID_GET_SOME_THING);
        getFSMClass().addState(stateNon6);

        FSMState stateNon7 = new FSMState(STATE_ID_GET_SOME_THING);
        stateNon7.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_GET_SOME_THING);
        stateNon7.addTransition(MSG_SCENE_BATTLEFIELD__GET_SOME_THING_TIME_OUT, STATE_ID_CHECK_WIN);
        getFSMClass().addState(stateNon7);


        FSMState stateNon8 = new FSMState(STATE_ID_CHECK_WIN);
        stateNon8.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_CHECK_WIN);
        stateNon8.addTransition(MSG_SCENE_BATTLEFIELD__CHECK_WIN_TIME_OUT, STATE_ID_SELECTED_NON);
        getFSMClass().addState(stateNon8);

        FSMState stateNon9 = new FSMState(STATE_ID_CRASH);
        stateNon9.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_CRASH);
        stateNon9.addTransition(MSG_SCENE_BATTLEFIELD__CRASH_NEXT, STATE_ID_CRASH);
        stateNon9.addTransition(MSG_SCENE_BATTLEFIELD__CRASH_TIME_OUT, STATE_ID_AI_TURN_BEGIN);
        getFSMClass().addState(stateNon9);

        //----------------AI loop begin---------

        FSMState stateNon10 = new FSMState(STATE_ID_AI_TURN_BEGIN);
        stateNon10.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_AI_TURN_BEGIN);
        stateNon10.addTransition(MSG_SCENE_BATTLEFIELD__BLUE_TURN_BEGIN_TIME_OUT, STATE_ID_BLUE_REPAIR);
        stateNon10.addTransition(MSG_SCENE_BATTLEFIELD__AI_TURN_BEGIN_TIME_OUT, STATE_ID_AI_REPAIR);
        getFSMClass().addState(stateNon10);

        FSMState stateNon11 = new FSMState(STATE_ID_AI_REPAIR);
        stateNon11.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_AI_REPAIR);
        stateNon11.addTransition(MSG_SCENE_BATTLEFIELD__AI_REPAIR_NEXT, STATE_ID_AI_REPAIR);
        stateNon11.addTransition(MSG_SCENE_BATTLEFIELD__AI_REPAIR_TIME_OUT, STATE_ID_AI_BUY);
        getFSMClass().addState(stateNon11);

        FSMState stateNon11_2 = new FSMState(STATE_ID_AI_BUY);
        stateNon11_2.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_AI_BUY);
        stateNon11_2.addTransition(MSG_SCENE_BATTLEFIELD__AI_BUY_NEXT, STATE_ID_AI_BUY);
        stateNon11_2.addTransition(MSG_SCENE_BATTLEFIELD__AI_BUY_TIME_OUT, STATE_ID_AI_SELECT_NON);
        getFSMClass().addState(stateNon11_2);


        FSMState stateNon12 = new FSMState(STATE_ID_AI_SELECT_NON);
        stateNon12.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_AI_SELECT_NON);
        stateNon12.addTransition(MSG_SCENE_BATTLEFIELD__AI_SELECT_NON_NEXT, STATE_ID_AI_SELECTED_WEAPON);
//        stateNon12.addTransition(MSG_SCENE_BATTLEFIELD__AI_SELECT_NON_TIME_OUT, STATE_ID_AI_CRASH);
        getFSMClass().addState(stateNon12);

        FSMState stateNon13 = new FSMState(STATE_ID_AI_SELECTED_WEAPON);
        stateNon13.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_AI_SELECTED_WEAPON);
        stateNon13.addTransition(MSG_SCENE_BATTLEFIELD__AI_SELECT_WEAPON_TIME_OUT, STATE_ID_AI_MOVE_TO_DESC);
        stateNon13.addTransition(MSG_SCENE_BATTLEFIELD__AI_SELECT_NON_TIME_OUT, STATE_ID_AI_CRASH);
        getFSMClass().addState(stateNon13);

        FSMState stateNon14 = new FSMState(STATE_ID_AI_MOVE_TO_DESC);
        stateNon14.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_AI_MOVE_TO_DESC);
        stateNon14.addTransition(MSG_SCENE_BATTLEFIELD__AI_MOVE_TO_DESC_TIME_OUT, STATE_ID_AI_FIGHT);
        getFSMClass().addState(stateNon14);


        FSMState stateNon15 = new FSMState(STATE_ID_AI_FIGHT);
        stateNon15.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_AI_FIGHT);
        stateNon15.addTransition(MSG_SCENE_BATTLEFIELD__AI_FIGHT_TIME_OUT, STATE_ID_AI_LV_UP);
        getFSMClass().addState(stateNon15);

        FSMState stateNon16 = new FSMState(STATE_ID_AI_LV_UP);
        stateNon16.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_AI_LV_UP);
        stateNon16.addTransition(MSG_SCENE_BATTLEFIELD__LV_UP_TIME_OUT, STATE_ID_AI_CAPLTURING);
        getFSMClass().addState(stateNon16);

        FSMState stateNon17 = new FSMState(STATE_ID_AI_CAPLTURING);
        stateNon17.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_AI_CAPLTURING);
        stateNon17.addTransition(MSG_SCENE_BATTLEFIELD__CAPTURING_TIME_OUT, STATE_ID_AI_CHECK_WIN);
        getFSMClass().addState(stateNon17);

        FSMState stateNon18 = new FSMState(STATE_ID_AI_CHECK_WIN);
        stateNon18.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_AI_CHECK_WIN);
        stateNon18.addTransition(MSG_SCENE_BATTLEFIELD__CHECK_WIN_TIME_OUT, STATE_ID_AI_SELECT_NON);
        getFSMClass().addState(stateNon18);

        FSMState stateNon19 = new FSMState(STATE_ID_AI_CRASH);
        stateNon19.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_AI_CRASH);
        stateNon19.addTransition(MSG_SCENE_BATTLEFIELD__CRASH_NEXT, STATE_ID_AI_CRASH);
        stateNon19.addTransition(MSG_SCENE_BATTLEFIELD__CRASH_TIME_OUT, STATE_ID_AI_TURN_BEGIN);
        getFSMClass().addState(stateNon19);
        //-----------------------------
        FSMState stateNon20 = new FSMState(STATE_ID_BLUE_REPAIR);
        stateNon20.addTransition(MSG_SCENE_HUD__BUTTON_BACKUP, STATE_ID_BLUE_REPAIR);
        stateNon20.addTransition(MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_NEXT, STATE_ID_BLUE_REPAIR);
        stateNon20.addTransition(MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_YES, STATE_ID_BLUE_REPAIR);
        stateNon20.addTransition(MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_NO, STATE_ID_BLUE_REPAIR);
        stateNon20.addTransition(MSG_SCENE_BATTLEFIELD__BLUE_REPAIR_TIME_OUT, STATE_ID_SELECTED_NON);
        getFSMClass().addState(stateNon20);


    }

    public void setHUDInfoCardWeaponID(int pInfoCardWeaponID) {
        infoCardWeaponID = pInfoCardWeaponID;
    }

}

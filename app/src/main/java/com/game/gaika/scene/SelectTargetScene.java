package com.game.gaika.scene;

import com.game.frame.scene.BaseLogicScene;
import com.game.frame.FSM.IMessageHandler;
import com.game.frame.FSM.TouchMessage;
import com.game.gaika.action.BaseAction;
import com.game.gaika.action.GameOverDilogNoAction;
import com.game.gaika.action.GameOverDilogYesAction;
import com.game.gaika.data.GameChapter;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;
import com.game.gaika.data.WeaponSelectFilter;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.scene.sub.TopButtosSubScene;
import com.game.gaika.scene.sub.borderSubScene;
import com.game.frame.sprite.NormalSprite;
import com.game.frame.sprite.NumberSprite;
import com.game.gaika.sprite.SelectChapterSprite;
import com.game.frame.sprite.TextSprite;
import com.game.frame.texture.TexRegionManager;

import java.util.List;

import static com.game.gaika.data.ID.COUNTRY.*;
import static com.game.gaika.data.ID.MSG_ID.*;
import static com.game.gaika.data.ID.SCENE_ID.*;
import static com.game.frame.sound.SoundManager.playSound;

/**
 * Created by fangxg on 2015/7/1.
 */
public class SelectTargetScene extends BaseLogicScene implements IMessageHandler {

    // enum MSG_ID {MSG_SELECT_CHAPTER}
    public SelectTargetScene(boolean isInit) {
        super(SELECT_TARGET);

        GameDataManager gdm = GameDataManager.getInstance();

        String bkKey = "";
        boolean localAllFinished = true;

        for (GameChapter chapter : gdm.chapters.values()) {
            if (chapter.area == gdm.gameBeginLocal && chapter.finished == false) {
                localAllFinished = false;
                break;
            }
        }
        if (localAllFinished == true) {
            bkKey = "oper_mp0";
        } else {
            if (gdm.gameBeginLocal == USN) {
                bkKey = "oper_mp1";
            }
            if (gdm.gameBeginLocal == GERMANY) {
                bkKey = "oper_mp2";
            }
            if (gdm.gameBeginLocal == ENGLAND) {
                bkKey = "oper_mp3";
            }
            if (gdm.gameBeginLocal == USA) {
                bkKey = "oper_mp4";
            }
            if (gdm.gameBeginLocal == JAPAN) {
                bkKey = "oper_mp5";
            }
            if (gdm.gameBeginLocal == RUSSIA) {
                bkKey = "oper_mp6";
            }
        }
        NormalSprite backSprite = new NormalSprite(0.0f, 0.0f, bkKey);
        addSprite(backSprite);

        TopButtosSubScene topButtosSubScene = new TopButtosSubScene(this);
        topButtosSubScene.setOffsetXY(0, 0);
        addChildScene(topButtosSubScene);
        SceneValueMap sceneValues = getSceneValuesMap();
        if (isInit == true) {
            sceneValues.cleanAllValues();
//            sceneValues.setEnum("sortTypeUp", TYPE);
        }

        if (localAllFinished == true) {
            for (GameChapter chapter : gdm.chapters.values()) {
                if (chapter.area == ALL) {
                    if (chapter.finished == true) {
                        SelectChapterSprite chapterSprite = new SelectChapterSprite(chapter, false, null);
                        addSprite(chapterSprite);
                    } else if (chapter.canSelect() == true) {
                        boolean isSelected = false;
                        if (sceneValues.containsKey("selectedChapterID") == true && sceneValues.getInt("selectedChapterID") == chapter.no) {
                            isSelected = true;
                        }

                        SelectChapterSprite chapterSprite = new SelectChapterSprite(chapter, isSelected, new TouchMessage(MSG_SCENE_SELECT_TARGET__SELECT_CHAPTER, null, this, chapter.no));
                        addSprite(chapterSprite);
                    }

                    if (chapter.no == 311 && chapter.finished == false) {
                        boolean isSelected = false;
                        if (sceneValues.containsKey("selectedChapterID") == true && sceneValues.getInt("selectedChapterID") == chapter.no) {
                            isSelected = true;
                        }
                        SelectChapterSprite chapterSprite = new SelectChapterSprite(chapter, isSelected, new TouchMessage(MSG_SCENE_SELECT_TARGET__SELECT_CHAPTER, null, this, chapter.no));
                        addSprite(chapterSprite);
                    }
                }
            }
        } else {
            for (GameChapter chapter : gdm.chapters.values()) {
                if (chapter.area == gdm.gameBeginLocal) {
                    if (chapter.finished == true) {
                        SelectChapterSprite chapterSprite = new SelectChapterSprite(chapter, false, null);
                        addSprite(chapterSprite);
                    } else if (chapter.canSelect() == true) {
                        boolean isSelected = false;
                        if (sceneValues.containsKey("selectedChapterID") == true && sceneValues.getInt("selectedChapterID") == chapter.no) {
                            isSelected = true;
                        }

                        SelectChapterSprite chapterSprite = new SelectChapterSprite(chapter, isSelected, new TouchMessage(MSG_SCENE_SELECT_TARGET__SELECT_CHAPTER, null, this, chapter.no));
                        addSprite(chapterSprite);
                    }
                }
            }
        }

        NumberSprite number1Sprite = new NumberSprite(725, 435, false, "font02", gdm.money);
        addSprite(number1Sprite);

        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.addTeamColor(ID.TEAM_COLOR.BLUE);
        List<BaseWeapon> weapons = filter.getWeapons();
        NumberSprite number2Sprite = new NumberSprite(725, 475, false, "font02", weapons.size());
        addSprite(number2Sprite);


        int finishedCount = 0;
        for (GameChapter chapter : gdm.chapters.values()) {
            if (chapter.finished == true) {
                finishedCount++;
            }
        }
        NumberSprite number3Sprite = new NumberSprite(725, 515, false, "font02", finishedCount);
        addSprite(number3Sprite);

        TextSprite textSprite = new TextSprite(760, 551, false, gdm.getCurrentChapter().cName, TexRegionManager.getInstance().getFont16());
        addSprite(textSprite);

        //top button
        addChildScene(new TopButtosSubScene(this));

        addChildScene(new borderSubScene(this));
    }

    @Override
    public boolean isBacegroundEnabled() {
        return true;
    }

    @Override
    public void buildScene() {

    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {
        GameDataManager gdm = GameDataManager.getInstance();
        Enum msgID = pTouchMessage.getMessageID();
        SceneValueMap sceneValues = getSceneValuesMap();
        if (msgID == MSG_SCENE_SELECT_TARGET__SELECT_CHAPTER) {
             playSound("select01");
            int newSelectedChapterID = pTouchMessage.getParam();
            if (sceneValues.containsKey("selectedChapterID") == false) {
                sceneValues.setInt("selectedChapterID", newSelectedChapterID);
                SelectTargetScene scene = new SelectTargetScene(false);
                SceneManager.render(scene);
            } else {
                int oldSelectedChapterID = sceneValues.getInt("selectedChapterID");
                if (newSelectedChapterID != oldSelectedChapterID) {
                    sceneValues.setInt("selectedChapterID", newSelectedChapterID);
                    SelectTargetScene scene = new SelectTargetScene(false);
                    SceneManager.render(scene);
                } else {

                    // gdm.setCurrentChapter( gdm.chapters.get(newSelectedChapterID));

                    BattlefieldEnterInfoScene battlefieldEnterInfoScene = new BattlefieldEnterInfoScene(newSelectedChapterID);
                    SceneManager.render(battlefieldEnterInfoScene);
                }
            }


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
}

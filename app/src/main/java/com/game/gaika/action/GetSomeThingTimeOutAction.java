package com.game.gaika.action;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.ChapterCompleteScene;
import com.game.gaika.scene.ChapterLoseScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.sprite.DelaySprite;

import java.util.Random;

/**
 * Created by fangxg on 2015/7/27.
 */
public class GetSomeThingTimeOutAction implements BaseAction {
    private int weaponID;

    public GetSomeThingTimeOutAction(int weaponID) {
        this.weaponID = weaponID;
    }

    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();


        ID.WIN_TYPE win = gdm.checkWinOrLose();

        if (win == ID.WIN_TYPE.NON) {
            BattlefieldScene battlefieldScene = new BattlefieldScene(false);
            battlefieldScene.addSprite(new DelaySprite(GameSetup.DELAY_SHORT_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__CHECK_WIN_TIME_OUT, null, battlefieldScene, weaponID)));
            SceneManager.render(battlefieldScene);
        }
        if (win == ID.WIN_TYPE.WIN) {

            //本关卡得分
            Random random1 = new Random(20);
            int score = 80 + random1.nextInt() % 20;

            ChapterCompleteScene chapterCompleteScene = new ChapterCompleteScene(score);
            SceneManager.render(chapterCompleteScene);
        }
        if (win == ID.WIN_TYPE.LOSE) {

            ChapterLoseScene chapterLoseScene = new ChapterLoseScene();
            SceneManager.render(chapterLoseScene);
        }


    }
}

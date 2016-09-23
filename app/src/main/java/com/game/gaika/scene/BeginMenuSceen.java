package com.game.gaika.scene;

import com.game.frame.scene.BaseLogicScene;
import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.SaveManager;
import com.game.gaika.main.MainActivity;
import com.game.gaika.scene.sub.borderSubScene;
import com.game.frame.sprite.NormalSprite;

import static com.game.gaika.data.ID.MSG_ID.*;
import static com.game.gaika.data.ID.SCENE_ID.*;
import static com.game.frame.sound.SoundManager.playSound;

/**
 * Created by fangxg on 2015/6/18.
 */
public class BeginMenuSceen extends BaseLogicScene implements IMessageHandler {

    public BeginMenuSceen() {
        super(BEGIN_MENU);
        SaveManager.reInitChapters();
    }

    @Override
    public boolean isBacegroundEnabled() {
        return true;
    }

    @Override
    public void buildScene() {
        NormalSprite backSprite = new NormalSprite(0.0f, 0.0f, "titl_bg3");
        addSprite(backSprite);

        NormalSprite buttonSprite1 = new NormalSprite(326, 368, "titl_bt1", 0, new TouchMessage(MSG_SCENE_BEGIN_MENU__BUTTON_NEW_GAME, null, this));
        addSprite(buttonSprite1);
        NormalSprite buttonSprite2 = new NormalSprite(326, 406, "titl_bt1", 1, new TouchMessage(MSG_SCENE_BEGIN_MENU__BUTTON_LOAD_GAME, null, this));
        addSprite(buttonSprite2);
        NormalSprite buttonSprite3 = new NormalSprite(326, 464, "titl_bt1", 2, new TouchMessage(MSG_SCENE_BEGIN_MENU__BUTTON_SETTING, null, this));
        addSprite(buttonSprite3);
        NormalSprite buttonSprite4 = new NormalSprite(326, 520, "titl_bt1", 3, new TouchMessage(MSG_SCENE_BEGIN_MENU__BUTTON_BACK_GAME, null, this));
        addSprite(buttonSprite4);

        addChildScene(new borderSubScene(this));
    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {
        if (pTouchMessage.getMessageID() == MSG_SCENE_BEGIN_MENU__BUTTON_NEW_GAME) {
             playSound("select01");
            DiffMenuScene scene = new DiffMenuScene();
            SceneManager.render(scene);
        }
        if (pTouchMessage.getMessageID() == MSG_SCENE_BEGIN_MENU__BUTTON_LOAD_GAME) {
             playSound("select01");
            LoadGameScene scene = new LoadGameScene(true, BEGIN_MENU);
            SceneManager.render(scene);
        }
        if (pTouchMessage.getMessageID() == MSG_SCENE_BEGIN_MENU__BUTTON_SETTING) {
             playSound("select01");
            SettingScene scene = new SettingScene(true,BEGIN_MENU);
            SceneManager.render(scene);
        }
        if (pTouchMessage.getMessageID() == MSG_SCENE_BEGIN_MENU__BUTTON_BACK_GAME) {
             playSound("back01");
            MainActivity.mGameActiviy.finish();
        }
    }
}

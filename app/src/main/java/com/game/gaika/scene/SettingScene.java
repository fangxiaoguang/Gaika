package com.game.gaika.scene;

import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.SaveManager;
import com.game.gaika.scene.sub.borderSubScene;
import com.game.gaika.sprite.NormalSprite;
import com.game.gaika.sprite.NumberSprite;
import com.game.gaika.sprite.TextSprite;
import com.game.gaika.string.StringManager;
import com.game.gaika.texture.TexRegionManager;

import static com.game.gaika.data.ID.MSG_ID.*;
import static com.game.gaika.data.ID.SCENE_ID.*;
import static com.game.gaika.sound.SoundManager.playSound;

/**
 * Created by fangxg on 2015/7/3.
 */
public class SettingScene extends BaseLogicScene implements IMessageHandler {


//    enum MSG_ID {MSG_RETURN}

    public SettingScene(boolean pInit, ID.SCENE_ID pPreSceneID) {
        super(SETTING);
        preSceneID = pPreSceneID;

        if(pInit == true){
            SaveManager.loadConfig();
        }

    }

    @Override
    public boolean isBacegroundEnabled() {
        return true;
    }

    @Override
    public void buildScene() {
        GameDataManager gdm = GameDataManager.getInstance();



        //ZOOM 图片
        //zoom 数字
        NormalSprite shotSprite = new NormalSprite(200, 100, "screenshot", 0);
        shotSprite.setScaleCenter(200, 150);
        shotSprite.setScale(GameSetup.settingBattlefieldZoom + (1.0f - GameSetup.SETTING_BATTLEFIELD_ZOOM_MIN )+ 0.3f);
        addSprite(shotSprite);

        NormalSprite backSprite = new NormalSprite(0.0f, 0.0f, "conf_bg1");
        addSprite(backSprite);

        NormalSprite button1 = new NormalSprite(200, 420, "oper_bt7", 2, new TouchMessage(MSG_SCENE_SETTING_ZOOM_DOWN, null, this, 0));
        addSprite(button1);

        TextSprite textSprite = new TextSprite(290, 418, true, StringManager.getInstance().getString("S08001"), TexRegionManager.getInstance().getFont32());
        addSprite(textSprite);

//        TiledSprite zoomText = getZoomSprite(465, 426, GameSetup.ZOOM);
        int i1 = ((int) (GameSetup.settingBattlefieldZoom * 10.0f)) / 10;
        int i2 = ((int) (GameSetup.settingBattlefieldZoom * 10.0f)) % 10;

       // TiledSprite backSprite = BaseRender.getSprite(offsetX, offsetY, "delay", 0);

        NumberSprite i1Sprite = new NumberSprite( 465, 426, false,"font04", i1);
        addSprite(i1Sprite);

        NormalSprite i0Sprite = new NormalSprite(465 + 2, 426 +11, "hp_city", 3);
        i0Sprite.setScale(2.0f);
        addSprite(i0Sprite);

        NumberSprite i2Sprite = new NumberSprite( 465 + 30, 426,false, "font04", i2);
        addSprite(i2Sprite);

        NormalSprite button2 = new NormalSprite(600 - 67, 420, "oper_bt7", 3, new TouchMessage(MSG_SCENE_SETTING_ZOOM_UP, null, this, 0));
        addSprite(button2);

        //音效开关


        TextSprite text2Sprite = new TextSprite(200, 470, true, StringManager.getInstance().getString("S08002"), TexRegionManager.getInstance().getFont32()  );
        addSprite(text2Sprite);


        if (GameSetup.settingOpenSound == true) {
            NormalSprite btnSoundOn = new NormalSprite(384, 470, "conf_bt1", 8);
            addSprite(btnSoundOn);

            NormalSprite btnSoundOff = new NormalSprite(494, 470, "conf_bt1", 3, new TouchMessage(MSG_SCENE_SETTING_SOUND_OFF, null, this, 0));
            addSprite(btnSoundOff);

        } else {
            NormalSprite btnSoundOn = new NormalSprite(384, 470, "conf_bt1", 2, new TouchMessage(MSG_SCENE_SETTING_SOUND_ON, null, this, 0));
            addSprite(btnSoundOn);

            NormalSprite btnSoundOff = new NormalSprite(494, 470, "conf_bt1", 9);
            addSprite(btnSoundOff);

        }


        //音效音量
        float offY = 100.0f;
        NormalSprite button3 = new NormalSprite(200, 420 +offY, "oper_bt7", 2, new TouchMessage(MSG_SCENE_SETTING_VOLUME_DOWN, null, this, 0));
        addSprite(button3);

        TextSprite text3Sprite = new TextSprite(290, 418 +offY, true, StringManager.getInstance().getString("S08003"), TexRegionManager.getInstance().getFont32());
        addSprite(text3Sprite);

        int i3 = ((int) (GameSetup.settingSoundVolume * 100.0f));

        NumberSprite i3Sprite = new NumberSprite( 465 + 30, 426 +offY, false,"font04", i3);
        addSprite(i3Sprite);


        NormalSprite button4 = new NormalSprite(600 - 67, 420  +offY, "oper_bt7", 3, new TouchMessage(MSG_SCENE_SETTING_VOLUME_UP, null, this, 0));
        addSprite(button4);


        //返回按钮
        NormalSprite buttonRetSprite = new NormalSprite(35, 556, "retn_bt1", 0, new TouchMessage(MSG_SCENE_SETTING_RETURN, null, this, 0));
        addSprite(buttonRetSprite);

        addChildScene(new borderSubScene(this));
    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {
        GameDataManager gdm = GameDataManager.getInstance();
        Enum msgID = pTouchMessage.getMessageID();
//        if (msgID == MSG_SCENE_SETTING_RETURN) {
//            BeginMenuSceen scene = new BeginMenuSceen();
//            SceneManager.render(scene);
//        }



        if(msgID == MSG_SCENE_SETTING_ZOOM_DOWN){
             playSound("back01");

            GameSetup.settingBattlefieldZoom = ((float)((int)(GameSetup.settingBattlefieldZoom * 10) - 1)) / 10.0f;
            if(GameSetup.settingBattlefieldZoom < GameSetup.SETTING_BATTLEFIELD_ZOOM_MIN){
                GameSetup.settingBattlefieldZoom = GameSetup.SETTING_BATTLEFIELD_ZOOM_MIN;
            }
            BaseLogicScene scene = new SettingScene(false, preSceneID);
            SceneManager.render(scene);
        }

        if(msgID == MSG_SCENE_SETTING_ZOOM_UP){
             playSound("back01");

            GameSetup.settingBattlefieldZoom = ((float)((int)(GameSetup.settingBattlefieldZoom * 10) + 1)) / 10.0f;
            if(GameSetup.settingBattlefieldZoom > GameSetup.SETTING_BATTLEFIELD_ZOOM_MAX){
                GameSetup.settingBattlefieldZoom = GameSetup.SETTING_BATTLEFIELD_ZOOM_MAX;
            }
            BaseLogicScene scene = new SettingScene(false, preSceneID);
            SceneManager.render(scene);
        }

        if(msgID == MSG_SCENE_SETTING_SOUND_OFF){
            GameSetup.settingOpenSound = false;
             playSound("back01");

            BaseLogicScene scene = new SettingScene(false, preSceneID);
            SceneManager.render(scene);
        }
        if(msgID == MSG_SCENE_SETTING_SOUND_ON){
            GameSetup.settingOpenSound = true;
             playSound("back01");

            BaseLogicScene scene = new SettingScene(false, preSceneID);
            SceneManager.render(scene);
        }


        if(msgID == MSG_SCENE_SETTING_VOLUME_DOWN){
             playSound("back01");

            GameSetup.settingSoundVolume = ((float)((int)(GameSetup.settingSoundVolume * 100) - 10)) / 100.0f;
            if(GameSetup.settingSoundVolume < 0){
                GameSetup.settingSoundVolume = 0;
            }

            BaseLogicScene scene = new SettingScene(false, preSceneID);
            SceneManager.render(scene);

        }

        if(msgID == MSG_SCENE_SETTING_VOLUME_UP){
             playSound("back01");

            GameSetup.settingSoundVolume = ((float)((int)(GameSetup.settingSoundVolume * 100) + 10)) / 100.0f;
            if(GameSetup.settingSoundVolume > 1.0f){
                GameSetup.settingSoundVolume = 1.0f;
            }

            BaseLogicScene scene = new SettingScene(false,preSceneID);
            SceneManager.render(scene);

        }
        if (msgID == MSG_SCENE_SETTING_RETURN) {
             playSound("back01");

            SaveManager.saveConfig();

            BaseLogicScene scene = SceneFactory.createScene(preSceneID, ID.SCENE_INIT_TYPE.NOT_INIT);
            SceneManager.render(scene);
        }
    }
}

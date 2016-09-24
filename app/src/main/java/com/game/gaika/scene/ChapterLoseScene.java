package com.game.gaika.scene;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.ID;
import com.game.gaika.sprite.NormalSprite;

/**
 * Created by fangxg on 2015/7/27.
 */
public class ChapterLoseScene extends BaseLogicScene {
    public ChapterLoseScene() {
        super(ID.SCENE_ID.CHAPTER_LOSE);

        NormalSprite bkSprite = new NormalSprite(0.0f, 0.0f, "fail_bg1");
        addSprite(bkSprite);

        NormalSprite buttonSprite = new NormalSprite(553, 499, "fail_bt1", 1, new TouchMessage(ID.MSG_ID.MSG_SCENE_CHAPTER_LOSE__BACK,null, this));
        addSprite(buttonSprite);

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
        ID.MSG_ID msgID = pTouchMessage.getMessageID();
        if(msgID == ID.MSG_ID.MSG_SCENE_CHAPTER_LOSE__BACK){
            BeginMenuSceen  beginMenuSceen = new BeginMenuSceen();
            SceneManager.render(beginMenuSceen);
        }
    }
}

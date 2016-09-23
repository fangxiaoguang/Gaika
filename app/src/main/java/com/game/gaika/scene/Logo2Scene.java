package com.game.gaika.scene;

import com.game.frame.scene.BaseLogicScene;
import com.game.frame.FSM.IMessageHandler;
import com.game.frame.FSM.TouchMessage;
import com.game.frame.sprite.DelaySprite;
import com.game.frame.sprite.NormalSprite;

import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;

import static com.game.gaika.data.ID.SCENE_ID.*;
import static com.game.gaika.data.ID.MSG_ID.*;

/**
 * Created by fangxg on 2015/6/17.
 */
public class Logo2Scene extends BaseLogicScene implements IMessageHandler {

    public Logo2Scene() {
        super(LOGO_2);
    }

    @Override
    public boolean isBacegroundEnabled() {
        return true;
    }

    @Override
    public void buildScene() {
        NormalSprite backSprite = new NormalSprite(0.0f, 0.0f, "logo_2");
        backSprite.registerEntityModifier(new SequenceEntityModifier(new AlphaModifier(1000 / 1000.0f, 0.0f, 1.0f), new DelayModifier(1000 / 1000.0f), new AlphaModifier(1000 / 1000.0f, 1.0f, 0.0f)));
        addSprite(backSprite);

        DelaySprite delaySprite = new DelaySprite(3.0f, new TouchMessage(MSG_SCENE_LOGO_2__DELAY_TIME_OUT, null, this));
        addSprite(delaySprite);
    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {
        if (pTouchMessage.getMessageID() == MSG_SCENE_LOGO_2__DELAY_TIME_OUT) {
            Logo3Scene logo3Scene = new Logo3Scene();
            SceneManager.render(logo3Scene);
        }
    }
}

package com.game.gaika.scene.dialg;

import com.game.frame.scene.dialg.DialogScene;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.frame.scene.BaseLogicScene;
import com.game.frame.sprite.NormalSprite;
import com.game.frame.sprite.TextSprite;
import com.game.frame.texture.TexRegionManager;

import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;

import java.util.List;

/**
 * Created by fangxg on 2015/7/29.
 */
public class Dialog5Scene extends DialogScene {
    public Dialog5Scene(BaseLogicScene pParentScene, List<String> pTexts ) {
        super(ID.SCENE_ID.DIALOG_5_DIALOG, 0.0f, 0.0f, 406.0f, 142.0f, pParentScene, EPointModel.POINT_MODEL_CENTER);

        IEntityModifier modifier = new SequenceEntityModifier(new DelayModifier(GameSetup.DELAY_CRASH_DLG_S ), new AlphaModifier(0.01f, 1.0f, 0.0f));

        NormalSprite bkSprite = new NormalSprite(0.0f, 0.0f, "dialg5");
        bkSprite.registerEntityModifier(modifier);
        addSprite(bkSprite);

        float x = 45;
        float y = 45;

        for(String text : pTexts){
            TextSprite textSprite = new TextSprite(x, y, true, text, TexRegionManager.getInstance().getFont16());
            IEntityModifier modifier2 = new SequenceEntityModifier(new DelayModifier(GameSetup.DELAY_CRASH_DLG_S ), new AlphaModifier(0.01f, 1.0f, 0.0f));
            textSprite.registerEntityModifier(modifier2);
            addSprite(textSprite);
            y += 25;
        }
    }

    @Override
    public boolean isBacegroundEnabled() {
        return false;
    }

    @Override
    public void buildScene() {

    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {

    }
}
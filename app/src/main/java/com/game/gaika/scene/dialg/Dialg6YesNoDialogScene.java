package com.game.gaika.scene.dialg;

import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.scene.BaseLogicScene;
import com.game.gaika.sprite.NormalSprite;
import com.game.gaika.sprite.TextSprite;
import com.game.gaika.texture.TexRegionManager;

import static com.game.gaika.data.ID.SCENE_ID.*;
import com.game.gaika.data.ID.MSG_ID;
/**
 * Created by fangxg on 2015/6/30.
 */
public class Dialg6YesNoDialogScene extends DialogScene {

    public Dialg6YesNoDialogScene(String pText, MSG_ID pYesEMsgID, MSG_ID pNoEMsgID, IMessageHandler pHandler,BaseLogicScene pParentScene) {
        this(pText, new TouchMessage(pYesEMsgID, null, pHandler), new TouchMessage(pNoEMsgID, null, pHandler),pParentScene);

    }
    public Dialg6YesNoDialogScene(String pText, TouchMessage pYesMsg, TouchMessage pNoMsg, BaseLogicScene pParentScene) {
        super(YES_NO_DIALOG, 0.0f, 0.0f, 406, 173,  pParentScene, EPointModel.POINT_MODEL_CENTER);



        NormalSprite bkSprite = new NormalSprite(0.0f, 0.0f, "dialg6");
        addSprite(bkSprite);

        TextSprite textSprite = new TextSprite(40, 45, true, pText, TexRegionManager.getInstance().getFont16());
        addSprite(textSprite);

        NormalSprite yesSprite = new NormalSprite(43, 127, "dialg6bt", 2, pYesMsg);
        addSprite(yesSprite);

        NormalSprite noSprite = new NormalSprite(287, 127, "dialg6bt", 3, pNoMsg);
        addSprite(noSprite);
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

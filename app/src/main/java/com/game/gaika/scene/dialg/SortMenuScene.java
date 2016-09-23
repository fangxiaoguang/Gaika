package com.game.gaika.scene.dialg;

import com.game.frame.scene.dialg.DialogScene;
import com.game.frame.FSM.TouchMessage;
import com.game.gaika.data.ID.MSG_ID;
import com.game.frame.scene.BaseLogicScene;
import com.game.frame.sprite.NormalSprite;

import java.util.List;

import  static com.game.gaika.data.ID.SCENE_ID.*;

/**
 * Created by fangxg on 2015/6/24.
 */
public class SortMenuScene extends DialogScene {
    public SortMenuScene(List<Enum> pListItems, List<MSG_ID> pListMessageIDs, BaseLogicScene pParentScene) {
        super(SORT_MENU, 0.0f, 0.0f, 0.0f,0.0f,pParentScene, EPointModel.POINT_MODEL_OFFSET);

        NormalSprite bkSprite = new NormalSprite(0.0f, 0.0f, "delay");
        addSprite(bkSprite);

        int count = 0;
        for (int i = 0; i < pListItems.size(); i ++) {
            NormalSprite itemSprite = new NormalSprite(0.0f, 23.0f * count, "sort_nm", pListItems.get(i).ordinal(), new TouchMessage(pListMessageIDs.get(i), null, pParentScene));
            addSprite(itemSprite );
            count++;
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

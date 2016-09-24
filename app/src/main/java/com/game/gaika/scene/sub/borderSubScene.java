package com.game.gaika.scene.sub;

import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.scene.BaseLogicScene;
import com.game.gaika.sprite.NormalSprite;


/**
 * Created by fangxg on 2015/9/19.
 */
public class borderSubScene extends BaseLogicScene implements IMessageHandler {
    public borderSubScene(BaseLogicScene pParentScene) {
        super(ID.SCENE_ID.BORDER_SUB);
        setParentScene(pParentScene);
    }

    @Override
    public boolean isBacegroundEnabled() {
        return false;
    }

    @Override
    public void buildScene() {
        if(GameSetup.show_border_sub==true) {
            int lx = -200;
            int ly = 0;
            float alpha = 0.5f;
            NormalSprite map1Sprite = new NormalSprite(lx, ly, "hq001");
            map1Sprite.setScale(2.0f);
            map1Sprite.setAlpha(alpha);
            addSprite(map1Sprite);
            ly += 200;

            NormalSprite map2Sprite = new NormalSprite(lx, ly, "hq002");
            map2Sprite.setScale(2.0f);
            map2Sprite.setAlpha(alpha);
            addSprite(map2Sprite);
            ly += 200;

            NormalSprite map3Sprite = new NormalSprite(lx, ly, "hq003");
            map3Sprite.setScale(2.0f);
            map3Sprite.setAlpha(alpha);
            addSprite(map3Sprite);
            ly += 200;

            int rx = 800;
            int ry = 0;

            NormalSprite map4Sprite = new NormalSprite(rx, ry, "hq004");
            map4Sprite.setScale(2.0f);
            map4Sprite.setAlpha(alpha);
            addSprite(map4Sprite);
            ry += 200;

            NormalSprite map5Sprite = new NormalSprite(rx, ry, "hq005");
            map5Sprite.setScale(2.0f);
            map5Sprite.setAlpha(alpha);
            addSprite(map5Sprite);
            ry += 200;

            NormalSprite map6Sprite = new NormalSprite(rx, ry, "hq006");
            map6Sprite.setScale(2.0f);
            map6Sprite.setAlpha(alpha);
            addSprite(map6Sprite);
            ry += 200;
        }
    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {

    }
}

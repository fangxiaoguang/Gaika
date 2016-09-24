package com.game.test;

import com.game.gaika.scene.BaseLogicScene;
import com.game.gaika.sprite.BaseSprite;

/**
 * Created by devuser1 on 2016/9/22.
 */

public class GameElement {

   private BaseLogicScene blScene;
    private BaseSprite bSprite;

    public GameElement(BaseLogicScene blScene, BaseSprite bSprite) {

        this.blScene = blScene;
        this.bSprite = bSprite;
    }

    public void click (){

        blScene.onHandlMessage(bSprite.getTouchMessage());
    }
}

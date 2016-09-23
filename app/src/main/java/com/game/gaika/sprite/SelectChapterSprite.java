package com.game.gaika.sprite;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameChapter;
import com.game.gaika.flyweight.AnimeFlyweight;
import com.game.gaika.flyweight.NormalFlyweight;
import com.game.gaika.flyweight.TextFlyweight;
import com.game.gaika.texture.TexRegionManager;

/**
 * Created by fangxg on 2015/7/1.
 */
public class SelectChapterSprite  extends BaseSprite {
    public SelectChapterSprite(GameChapter pChapter,/* String pLable,*/ boolean pSelected, TouchMessage pTouchMessage) {
        super(pChapter.pointX - 34, pChapter.pointY - 90);



        NormalFlyweight bkFlyweight= new NormalFlyweight(0.0f, 0.0f, "invisible64_90");
        setFlyweight(bkFlyweight);

        setTouchMessage(pTouchMessage);





        if (pChapter.finished) {
            NormalFlyweight mkFlyweight = new NormalFlyweight(17, 42, "oper_mk1");
            bkFlyweight.addChildFlyweight(mkFlyweight);
        } else {

           // if (pChapter.canSelect() == true) {

                if (pSelected == true) {

                    AnimeFlyweight localSprite1 = new AnimeFlyweight(17, 42, "oper_mk1",  7, 13, 100);
                    bkFlyweight.addChildFlyweight(localSprite1);
                } else {

                    NormalFlyweight localSprite1 = new NormalFlyweight(17, 42, "oper_mk1", 0);
                    bkFlyweight.addChildFlyweight(localSprite1);
                }

                NormalFlyweight labelSprite = new NormalFlyweight(-35 + 7 + 10, -30 + 42, "oper_btb", 2);
                bkFlyweight.addChildFlyweight(labelSprite);

                TextFlyweight textSprite = new TextFlyweight(-35 + 7 + 10 + 8, -30 + 42 + 3, true, pChapter.cName, TexRegionManager.getInstance().getFont16());
                bkFlyweight.addChildFlyweight(textSprite);
           // }
        }
    }
}

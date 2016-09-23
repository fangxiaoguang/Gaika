package com.game.gaika.sprite;

import com.game.frame.flyweight.NormalFlyweight;
import com.game.frame.flyweight.TextFlyweight;
import com.game.frame.sprite.BaseSprite;
import com.game.frame.texture.TexRegionManager;

import java.util.List;

/**
 * Created by fangxg on 2015/7/27.
 */
public class BottomMessageSprite extends BaseSprite {
    public BottomMessageSprite(List<String> pTexts) {
        super();
        NormalFlyweight bkFlyweight = new NormalFlyweight(0.0f, 0.0f, "evnt_wn1_2");

        NormalFlyweight bk2Flyweight = new NormalFlyweight(8.0f, 16.0f, "evnt_wn2_2");
        bkFlyweight.addChildFlyweight(bk2Flyweight);

        int count = 0;
        for (String text : pTexts) {
            TextFlyweight textFlyweight = new TextFlyweight(28.0f, 10  + 12.0f  + 20 * count, true, text, TexRegionManager.getInstance().getFont16());
            bkFlyweight.addChildFlyweight(textFlyweight);
            count++;
        }
        setFlyweight(bkFlyweight);
    }
}

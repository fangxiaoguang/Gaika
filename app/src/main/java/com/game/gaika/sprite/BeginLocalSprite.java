package com.game.gaika.sprite;

import com.game.frame.sprite.BaseSprite;
import com.game.gaika.FSM.TouchMessage;
import com.game.frame.flyweight.AnimeFlyweight;
import com.game.frame.flyweight.NormalFlyweight;
import com.game.frame.flyweight.TextFlyweight;
import com.game.frame.texture.TexRegionManager;

/**
 * Created by fangxg on 2015/6/19.
 */
public class BeginLocalSprite extends BaseSprite {
    private String lable;
    private boolean selected;
    private int flagIndex;
    public BeginLocalSprite(float pX, float pY, String pLable, int pFlagIndex, boolean pSelected, TouchMessage pTouchMessage){
        super(pX, pY);
        lable = pLable;
        selected = pSelected;
        flagIndex = pFlagIndex;

        NormalFlyweight normalFlyweight = new NormalFlyweight(0.0f, 0.0f, "invisible64_90");

        if(selected == true){
            AnimeFlyweight arrowFlyweight = new AnimeFlyweight(17, 42,"oper_mk1",7, 13, 100);
            normalFlyweight.addChildFlyweight(arrowFlyweight);
        }else{
            NormalFlyweight arrowFlyweight = new NormalFlyweight(17, 42,"oper_mk1", 0);
            normalFlyweight.addChildFlyweight(arrowFlyweight);
        }

        NormalFlyweight labelFlyweight = new NormalFlyweight(-18, 12, "oper_btb", 2);
        normalFlyweight.addChildFlyweight(labelFlyweight);



       /* Text text = BaseRender.getText16(true, 8, 3, pLable);
        sprite.attachChild(text);*/

        TextFlyweight textFlyweight = new TextFlyweight(-10, 15, true, pLable, TexRegionManager.getInstance().getFont16());
        normalFlyweight.addChildFlyweight(textFlyweight);

        NormalFlyweight flagFlyweight = new NormalFlyweight(21, 94, "flag03", flagIndex);
        normalFlyweight.addChildFlyweight(flagFlyweight);


        setFlyweight(normalFlyweight);


        setTouchMessage(pTouchMessage);

    }
}

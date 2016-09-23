package com.game.gaika.sprite;

import com.game.frame.sprite.BaseSprite;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.ColorBox;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.frame.flyweight.NormalFlyweight;
import com.game.frame.flyweight.TextFlyweight;
import com.game.frame.texture.TexRegionManager;

/**
 * Created by fangxg on 2015/7/23.
 */
public class ColorBoxSprite extends BaseSprite {
    public ColorBoxSprite(ColorBox pColorBox, TouchMessage pTouchMessage) {//} IMessageHandler pMessageHandler) {
        super(pColorBox.getPixelX(), pColorBox.getPixelY());

        GameDataManager gdm = GameDataManager.getInstance();

        NormalFlyweight boxSprite = new NormalFlyweight(0.0f, 0.0f, "greenBox", pColorBox.color.ordinal());
        setFlyweight(boxSprite);

        if (GameSetup.isDebug_box_sprite_xy) {
            TextFlyweight xyFlyweight = new TextFlyweight(10.0f, 10.0f, true, "(" + pColorBox.getMapX() + "," + pColorBox.getMapY() + ")", TexRegionManager.getInstance().getFont12());
            boxSprite.addChildFlyweight(xyFlyweight);

//            TextFlyweight miniFlyweight = new TextFlyweight(15.0f, 22.0f, true, "" + String.format("%.1f",gdm.getCurrentChapter().getGameMap().getMapNodeFromXY(pColorBox).minStep) + ":" + String.format("%.1f",gdm.getCurrentChapter().getGameMap().getMapNodeFromXY(pColorBox).groundStep), TexRegionManager.getInstance().getFont12());
            TextFlyweight miniFlyweight = new TextFlyweight(15.0f, 22.0f, true, "" + String.format("%.1f",gdm.getCurrentChapter().getGameMap().getMapNodeFromXY(pColorBox).costFromStart) + ":" + String.format("%.1f",gdm.getCurrentChapter().getGameMap().getMapNodeFromXY(pColorBox).groundStep), TexRegionManager.getInstance().getFont12());
//            TextFlyweight miniFlyweight = new TextFlyweight(15.0f, 22.0f, true, "" + String.format("%.1f", gdm.getCurrentChapter().getGameMap().getMapNodeFromXY(pColorBox).minStep), TexRegionManager.getInstance().getFont12());
//            TextFlyweight miniFlyweight = new TextFlyweight(15.0f, 22.0f, true, "" +  gdm.getCurrentChapter().getGameMap().getMapNodeFromXY(pColorBox).minStep, TexRegionManager.getInstance().getFont12());
//            TextFlyweight miniFlyweight = new TextFlyweight(15.0f, 22.0f, true, "" + gdm.getCurrentChapter().getGameMap().getMapNodeFromXY(pColorBox).costFromStart, TexRegionManager.getInstance().getFont12());
            boxSprite.addChildFlyweight(miniFlyweight);
        }

        setTouchMessage(pTouchMessage);


    }
}

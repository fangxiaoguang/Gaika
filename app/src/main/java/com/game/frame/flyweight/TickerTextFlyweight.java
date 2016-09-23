package com.game.frame.flyweight;

import com.game.frame.flyweight.BaseFlyweight;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.main.MainActivity;

import org.andengine.entity.shape.RectangularShape;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TickerText;
import org.andengine.opengl.font.Font;
import org.andengine.util.HorizontalAlign;

/**
 * Created by fangxg on 2015/6/20.
 */
public class TickerTextFlyweight  extends BaseFlyweight {

    public String text = "";
    public Font font;

    public TickerTextFlyweight(String pText, Font pFont) {
        this(0.0f, 0.0f,  pText, pFont);
    }

    public TickerTextFlyweight(float pOffsetX, float pOffsetY, String pText, Font pFont) {
        super(pOffsetX, pOffsetY);
        this.text = pText;
        font = pFont;
    }

    @Override
    public RectangularShape getShapeSelf(TouchMessage pTouchMessage) {

        Text msg = new TickerText(offsetX,offsetY, font, text, new TickerText.TickerTextOptions(HorizontalAlign.LEFT, 15),
                MainActivity.mGameActiviy.getVertexBufferObjectManager());
        return msg;
    }
}
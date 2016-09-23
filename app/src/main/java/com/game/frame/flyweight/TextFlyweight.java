package com.game.frame.flyweight;

import com.game.frame.FSM.TouchMessage;
import com.game.gaika.main.MainActivity;

import org.andengine.entity.shape.RectangularShape;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

/**
 * Created by fangxg on 2015/6/19.
 */
public class TextFlyweight extends BaseFlyweight {

    public boolean leftAlign;
    public String text = "";
    public Font font;
    public Color color;

    public TextFlyweight(boolean pLeftAlign, String pText, Font pFont) {
        this(0.0f, 0.0f, pLeftAlign, pText, pFont, Color.WHITE);
    }

    public TextFlyweight(boolean pLeftAlign, String pText, Font pFont, Color pColor) {
        this(0.0f, 0.0f, pLeftAlign, pText, pFont, pColor);
    }
    public TextFlyweight(float pOffsetX, float pOffsetY, boolean pLeftAlign, String pText, Font pFont){
        this(pOffsetX, pOffsetY, pLeftAlign, pText, pFont, Color.WHITE);
    }
    public TextFlyweight(float pOffsetX, float pOffsetY, boolean pLeftAlign, String pText, Font pFont, Color pColor) {
        super(pOffsetX, pOffsetY);
        this.leftAlign = pLeftAlign;
        this.text = pText;
        font = pFont;
        color = pColor;
    }

    @Override
    public RectangularShape getShapeSelf(TouchMessage pTouchMessage) {

        Text text = new Text(0, 0, font, this.text, new TextOptions(HorizontalAlign.LEFT),
                MainActivity.mGameActiviy.getVertexBufferObjectManager());
        float x = 0.0f;
        float y = offsetY;
        if (leftAlign == true) {
            x = offsetX;
        } else {
            x = offsetX - text.getWidth();
        }
        text.setPosition(x, y);
        text.setAlpha(this.alpha);
        text.setColor(color);
        return text;
    }
}

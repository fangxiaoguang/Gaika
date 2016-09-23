package com.game.frame.flyweight;

import com.game.gaika.FSM.TouchMessage;
import com.game.frame.texture.TexRegionManager;

import org.andengine.entity.shape.RectangularShape;

/**
 * Created by fangxg on 2015/6/23.
 */
public class NumberFlyweight extends BaseFlyweight {
    private boolean leftAlign;
    private String texKey;
    private int number;
    private int reLength;
    private int offsetIndex;


    public NumberFlyweight(float pX, float pY, boolean pLeftAlign, String texKey, int pNumber) {
        this(pX, pY, pLeftAlign, texKey, pNumber, 0, 0);
    }
    public NumberFlyweight(float pX, float pY, boolean pLeftAlign, String pTexKey, int pNumber, int pReLength, int pOffsetIndex) {
        this(pX, pY, pLeftAlign, pTexKey, pNumber, pReLength, pOffsetIndex, 1.0f);
    }
    public NumberFlyweight(float pX, float pY, boolean pLeftAlign, String pTexKey, int pNumber, int pReLength, int pOffsetIndex, float pAlpha) {
        super(pX, pY);
        leftAlign = pLeftAlign;
        texKey = pTexKey;
        number = pNumber;
        reLength = pReLength;
        offsetIndex = pOffsetIndex;
        setAlpha(pAlpha);

    }

    @Override
    public RectangularShape getShapeSelf(TouchMessage pTouchMessage) {
        NormalFlyweight bkFlyweight = new NormalFlyweight(offsetX,offsetY, "delay");

        String strNumber = String.valueOf(number);

        if (reLength > strNumber.length()) {
            String sZeros = "";
            for (int i = 0; i < reLength - strNumber.length(); i++) {
                sZeros = sZeros + "0";
            }
            strNumber = sZeros + strNumber;
        }

        int charWidth = (int) (TexRegionManager.getInstance().getTexRegion(texKey).getWidth());
        if (leftAlign == true) {
            int x = 0;
            for (int i = 0; i < strNumber.length(); i++) {
                int idx = Integer.valueOf(strNumber.substring(i, i + 1));
                NormalFlyweight charFlyweight = new NormalFlyweight(x += charWidth, 0, texKey, offsetIndex + idx);
                charFlyweight.setAlpha(this.getAlpha());
                bkFlyweight.addChildFlyweight(charFlyweight);
            }
        } else if (leftAlign == false) {
            int x = 0;
            for (int i = 0; i < strNumber.length(); i++) {
                int idx = Integer.valueOf(strNumber.substring(strNumber.length() - i - 1, strNumber.length() - i));
                NormalFlyweight charFlyweight = new NormalFlyweight(x -= charWidth, 0, texKey, offsetIndex + idx);
                charFlyweight.setAlpha(this.getAlpha());
                bkFlyweight.addChildFlyweight(charFlyweight);
            }
        }
        RectangularShape shape = bkFlyweight.getShape(pTouchMessage);
        return shape;
    }
}

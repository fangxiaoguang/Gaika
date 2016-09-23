package com.game.frame.sprite;

import com.game.frame.flyweight.NumberFlyweight;
import com.game.frame.sprite.BaseSprite;

/**
 * Created by fangxg on 2015/6/23.
 */
public class NumberSprite extends BaseSprite {

    public NumberSprite(float pX, float pY, boolean pLeftAlign, String texKey, int pNumber) {
        this(pX, pY, pLeftAlign, texKey, pNumber, 0, 0, 1.0f);
    }

    public NumberSprite(float pX, float pY, boolean pLeftAlign, String texKey, int pNumber, int pReLength, int pOffsetIndex, float pAlpha) {
        super(pX, pY);


        NumberFlyweight numberFlyweight = new NumberFlyweight(0.0f, 0.0f, pLeftAlign, texKey, pNumber, pReLength, pOffsetIndex, pAlpha);
        setFlyweight(numberFlyweight);
       /* NormalFlyweight bkFlyweight = new NormalFlyweight(0.0f, 0.0f, "delay", 0);


        String strNumber = String.valueOf(pNumber);

        if (pReLength > strNumber.length()) {
            String sZeros = "";
            for (int i = 0; i < pReLength - strNumber.length(); i++) {
                sZeros = sZeros + "0";
            }
            strNumber = sZeros + strNumber;
        }

        int charWidth = (int) (TexRegionManager.getInstance().getTexRegion(texKey).getWidth());
        if (pLeftAlign == true) {
            int x = 0;
            for (int i = 0; i < strNumber.length(); i++) {
                int idx = Integer.valueOf(strNumber.substring(i, i + 1));
                NormalFlyweight charFlyweight = new NormalFlyweight(x += charWidth, 0, texKey, pOffsetIndex + idx);
                // TiledSprite sprite = getShape(x += charWidth, 0, texKey, pOffsetIndex + idx);
                charFlyweight.setAlpha(pAlpha);
                bkFlyweight.addChildFlyweight(charFlyweight);
            }

        } else if (pLeftAlign == false) {
            int x = 0;
            for (int i = 0; i < strNumber.length(); i++) {
                int idx = Integer.valueOf(strNumber.substring(strNumber.length() - i - 1, strNumber.length() - i));
                NormalFlyweight charFlyweight = new NormalFlyweight(x -= charWidth, 0, texKey, pOffsetIndex + idx);
                charFlyweight.setAlpha(pAlpha);
                bkFlyweight.addChildFlyweight(charFlyweight);
            }

        }

        setFlyweight(bkFlyweight);*/
    }
}

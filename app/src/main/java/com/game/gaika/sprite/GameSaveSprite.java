package com.game.gaika.sprite;

import com.game.frame.sprite.BaseSprite;
import com.game.frame.FSM.TouchMessage;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.GameTimer;
import com.game.gaika.data.SaveManager;
import com.game.frame.flyweight.BaseFlyweight;
import com.game.frame.flyweight.FileFlyweight;
import com.game.frame.flyweight.NormalFlyweight;
import com.game.frame.flyweight.NumberFlyweight;
import com.game.frame.flyweight.TextFlyweight;
import com.game.frame.texture.TexRegionManager;

import java.io.File;

/**
 * Created by fangxg on 2015/7/2.
 */
public class GameSaveSprite extends BaseSprite {

    public GameSaveSprite(float pX, float pY, SaveManager.ShowSaveNode pSsave, TouchMessage pTouchMessage) {
        super(pX, pY);
        int saveNo = pSsave.saveNo;

        if (saveNo != 0) {

            String fileName = GameSetup.sdcartdPahtSave + "/" + saveNo + ".png";
            File file = new File(fileName);

            BaseFlyweight saveFlyweight;
            if (file.exists() == true) {

                saveFlyweight = new FileFlyweight(0.0f, 0.0f, fileName);
                setFlyweight(saveFlyweight);
            } else {

                saveFlyweight = new NormalFlyweight(0.0f, 0.0f, "data_bt2");
                setFlyweight(saveFlyweight);
            }

            // yyyy/MM/dd hh:mm:ss
            String dateTime = pSsave.dateTime;
            int turnCount = pSsave.turnCount;
            String chapterName = pSsave.chapterName;

            int yyyy = Integer.valueOf(dateTime.substring(0, 4));
            int MM = Integer.valueOf(dateTime.substring(5, 7));
            int dd = Integer.valueOf(dateTime.substring(8, 10));

            int hh = Integer.valueOf(dateTime.substring(11, 13));
            int mm = Integer.valueOf(dateTime.substring(14, 16));
            int ss = Integer.valueOf(dateTime.substring(17, 19));

            NumberFlyweight yyyyFlyweight = new NumberFlyweight(-7, 8, true, "font08", yyyy, 4, 0);
            NormalFlyweight markFlyweight1 = new NormalFlyweight(-7 + 13 * 4 + 13, 8, "font08", 10);
            NumberFlyweight MMFlyweight = new NumberFlyweight(-7 + 13 * 5, 8, true, "font08", MM, 2, 0);
            NormalFlyweight markFlyweight2 = new NormalFlyweight(-7 + 13 * 7 + 13, 8, "font08", 10);
            NumberFlyweight ddFlyweight = new NumberFlyweight(-7 + 13 * 8, 8, true, "font08", dd, 2, 0);

            saveFlyweight.addChildFlyweight(yyyyFlyweight);
            saveFlyweight.addChildFlyweight(markFlyweight1);
            saveFlyweight.addChildFlyweight(MMFlyweight);
            saveFlyweight.addChildFlyweight(markFlyweight2);
            saveFlyweight.addChildFlyweight(ddFlyweight);

            NumberFlyweight hhFlyweight = new NumberFlyweight(29, 5 + 14, true, "font08", hh, 2, 0);
            NormalFlyweight markFlyweight3 = new NormalFlyweight(29 + 13 * 2 + 13, 5 + 14, "font08_2", 0);
            NumberFlyweight mmFlyweight = new NumberFlyweight(29 + 13 * 2 + 8, 5 + 14, true, "font08", mm, 2, 0);
            NormalFlyweight markFlyweight4 = new NormalFlyweight(29 + 13 * 4 + 8 + 13, 5 + 14, "font08_2", 0);
            NumberFlyweight ssFlyweight = new NumberFlyweight(29 + 13 * 4 + 8 * 2, 5 + 14, true, "font08", ss, 2, 0);

            saveFlyweight.addChildFlyweight(hhFlyweight);
            saveFlyweight.addChildFlyweight(markFlyweight3);
            saveFlyweight.addChildFlyweight(mmFlyweight);
            saveFlyweight.addChildFlyweight(markFlyweight4);
            saveFlyweight.addChildFlyweight(ssFlyweight);

            GameTimer gt = new GameTimer(0, turnCount);

            String str1 = gt.getDayFromTurn() + "日目 " + gt.getHourFromTurn() + ":00";
            TextFlyweight textFlyweight1 = new TextFlyweight(133, 65, false, str1, TexRegionManager.getInstance().getFont16());
            saveFlyweight.addChildFlyweight(textFlyweight1);

            TextFlyweight textFlyweight2 = new TextFlyweight(133, 82, false, chapterName, TexRegionManager.getInstance().getFont16());
            saveFlyweight.addChildFlyweight(textFlyweight2);
        } else {
            NormalFlyweight saveFlyweight = new NormalFlyweight(0.0f, 0.0f, "data_bt1");
            setFlyweight(saveFlyweight);
        }

        setTouchMessage(pTouchMessage);
    }
}

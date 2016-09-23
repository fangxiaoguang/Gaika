package com.game.gaika.scene;

import com.game.frame.scene.BaseLogicScene;
import com.game.gaika.FSM.IMessageHandler;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.ID;
import com.game.gaika.scene.sub.borderSubScene;
import com.game.frame.sprite.NormalSprite;
import com.game.frame.sprite.TickerTextSprite;
import com.game.frame.string.StringManager;
import com.game.frame.texture.TexRegionManager;

import java.util.ArrayList;
import java.util.List;

import static com.game.gaika.scene.TutorialScene.InitID.*;
import static com.game.gaika.data.ID.MSG_ID.*;
import static com.game.gaika.data.ID.SCENE_ID.*;
import static com.game.frame.sound.SoundManager.playSound;

/**
 * Created by fangxg on 2015/6/20.
 */

public class TutorialScene extends BaseLogicScene implements IMessageHandler {
    private static InitID initID;

    public static class TutorialNode {
        public final String imageName;
        public final String msgKey;

        public TutorialNode(String pImageName, String pMsgKey) {
            imageName = pImageName;
            msgKey = pMsgKey;
        }
    }

    enum InitID {INIT_DATA_1, INIT_DATA_2, INIT_DATA_3, INIT_NEXT_PAGE}

//    enum MSG_ID {MSG_DELAY_TIME_OUT, MSG_BACKUP_TOUCHE}

    static public List<TutorialNode> imageList = new ArrayList<TutorialNode>();

    public TutorialScene(InitID pInitID) {
        super(TUTORIAL);

        initID = pInitID;

        if (pInitID == INIT_DATA_1) {
            setTurtorialData1();
        }

        if (pInitID == INIT_DATA_2) {
            setTurtorialData2();
        }
        if (pInitID == INIT_DATA_3) {
            setTurtorialData3();
        }

        NormalSprite backSprite = new NormalSprite(0.0f, 0.0f, imageList.get(0).imageName, 0, new TouchMessage(MSG_SCENE_TUTORIAL__BACKUP_TOUCHE, null, this));
        addSprite(backSprite);


        NormalSprite sprite1 = new NormalSprite((this.width - 721) / 2 - 15, (this.height - 136) - 32, "evnt_wn1");
        addSprite(sprite1);

        NormalSprite sprite2 = new NormalSprite((this.width - 721) / 2, (this.height - 136), "evnt_wn2");
        addSprite(sprite2);

        TickerTextSprite tickerTextSprite = new TickerTextSprite((this.width - 721) / 2 + 35, (this.height - 136) + 15,
                StringManager.getInstance().getString(imageList.get(0).msgKey), TexRegionManager.getInstance().getFont32());
        addSprite(tickerTextSprite);

        addChildScene(new borderSubScene(this));

    }

    @Override
    public boolean isBacegroundEnabled() {
        return true;
    }

    @Override
    public void buildScene() {

    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {
        ID.MSG_ID EMsgID = pTouchMessage.getMessageID();
        if (EMsgID == MSG_SCENE_TUTORIAL__BACKUP_TOUCHE) {
             playSound("scrl01");
            if(imageList.size() > 0) {
                imageList.remove(0);
            }
            if (imageList.size() > 0) {
                TutorialScene tutorialScene = new TutorialScene(TutorialScene.InitID.INIT_NEXT_PAGE);
                SceneManager.render(tutorialScene);
            } else {
                BattlefieldScene battlefieldScene = new BattlefieldScene(true);
                SceneManager.render(battlefieldScene);
            }
        }
    }

    private void setTurtorialData1() {
        TutorialScene.imageList.clear();
        TutorialScene.imageList.add(new TutorialNode("evnt_010", "S09001"));
        TutorialScene.imageList.add(new TutorialNode("evnt_011", "S09002"));
        TutorialScene.imageList.add(new TutorialNode("evnt_012", "S09003"));
        TutorialScene.imageList.add(new TutorialNode("evnt_013", "S09004"));
        TutorialScene.imageList.add(new TutorialNode("evnt_014", "S09005"));
        TutorialScene.imageList.add(new TutorialNode("evnt_015", "S09006"));
        TutorialScene.imageList.add(new TutorialNode("evnt_016", "S09007"));
        TutorialScene.imageList.add(new TutorialNode("evnt_016", "S09008"));
        TutorialScene.imageList.add(new TutorialNode("evnt_017", "S09009"));
        TutorialScene.imageList.add(new TutorialNode("evnt_017", "S09010"));
        TutorialScene.imageList.add(new TutorialNode("evnt_017", "S09011"));
        // TutorialScene.imageList.add(new TutorialNode("evnt_018", "S09012"));
        // TutorialScene.imageList.add(new TutorialNode("evnt_019", "S09013"));
        TutorialScene.imageList.add(new TutorialNode("evnt_020", "S09014"));
        TutorialScene.imageList.add(new TutorialNode("evnt_020", "S09015"));

    }

    private void setTurtorialData2() {
        TutorialScene.imageList.clear();
        TutorialScene.imageList.add(new TutorialNode("evnt_021", "S09016"));
        TutorialScene.imageList.add(new TutorialNode("evnt_022", "S09017"));
        TutorialScene.imageList.add(new TutorialNode("evnt_022", "S09018"));
        TutorialScene.imageList.add(new TutorialNode("evnt_022", "S09019"));
        TutorialScene.imageList.add(new TutorialNode("evnt_023", "S09020"));
        TutorialScene.imageList.add(new TutorialNode("evnt_023", "S09021"));
    }

    private void setTurtorialData3() {
        TutorialScene.imageList.clear();
        TutorialScene.imageList.add(new TutorialNode("evnt_001", "S09022"));
        TutorialScene.imageList.add(new TutorialNode("evnt_001", "S09023"));
        TutorialScene.imageList.add(new TutorialNode("evnt_002", "S09024"));
        TutorialScene.imageList.add(new TutorialNode("evnt_002", "S09025"));
        TutorialScene.imageList.add(new TutorialNode("evnt_002", "S09026"));
        TutorialScene.imageList.add(new TutorialNode("evnt_003", "S09027"));
        TutorialScene.imageList.add(new TutorialNode("evnt_003", "S09028"));
        TutorialScene.imageList.add(new TutorialNode("evnt_003", "S09029"));
        // TutorialScene.imageList.add(new TutorialNode("evnt_003", "S09030"));
        TutorialScene.imageList.add(new TutorialNode("evnt_003", "S09031"));
        TutorialScene.imageList.add(new TutorialNode("evnt_003", "S09032"));
        TutorialScene.imageList.add(new TutorialNode("evnt_003", "S09033"));
        TutorialScene.imageList.add(new TutorialNode("evnt_003", "S09034"));
        TutorialScene.imageList.add(new TutorialNode("evnt_004", "S09035"));
        TutorialScene.imageList.add(new TutorialNode("evnt_004", "S09036"));
        TutorialScene.imageList.add(new TutorialNode("evnt_005", "S09037"));
        TutorialScene.imageList.add(new TutorialNode("evnt_005", "S09038"));
        TutorialScene.imageList.add(new TutorialNode("evnt_005", "S09039"));
        TutorialScene.imageList.add(new TutorialNode("evnt_006", "S09040"));
    }
}

package com.game.gaika.scene.dialg;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.City;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.GameTimer;
import com.game.gaika.data.ID;
import com.game.gaika.scene.BaseLogicScene;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.sprite.DelaySprite;
import com.game.gaika.sprite.NormalSprite;
import com.game.gaika.sprite.NumberSprite;
import com.game.gaika.sprite.TextSprite;
import com.game.gaika.texture.TexRegionManager;

/**
 * Created by fangxg on 2015/7/29.
 */
public class TurnBeginDialog extends DialogScene {


    public TurnBeginDialog(BaseLogicScene pParentScene, ID.TEAM_COLOR pTeamColor) {
        super(ID.SCENE_ID.TURN_BEGIN_DIALG, 0.0f, 0.0f, 406.0f, 142.0f, pParentScene, EPointModel.POINT_MODEL_CENTER);
        GameDataManager gdm = GameDataManager.getInstance();

        BattlefieldScene battlefieldScene = new BattlefieldScene(false);


        NormalSprite bkSprite = new NormalSprite(0.0f, 0.0f, "dialg1");
        addSprite(bkSprite);


        GameTimer gt = gdm.gameTimer;
        String sDateTime = gt.getDayFromTurn() + "日目 " + gt.getAmPmFromTurn() + " " + gt.getHourFromTurn() + ":00";

        TextSprite dataSprite = new TextSprite(82, 25, true, sDateTime, TexRegionManager.getInstance().getFont16());// "1日目  午后  9:00");
        addSprite(dataSprite);

        int backDay = gdm.gameTimer.getBackDay();
        if (backDay > 9) {
            backDay = 9;
        }
        NormalSprite backDaySprite = new NormalSprite(276, 24, "dialg1p2", backDay);
        addSprite(backDaySprite);

        NormalSprite flagSprite = new NormalSprite(33, 53, "dialg1pt", pTeamColor.ordinal());
        addSprite(flagSprite);

        int cityCount = 0;
        int supplyCount = 0;
        for (City city : gdm.getCurrentChapter().getGameMap().citys.values()) {
            if (city.teamColor == pTeamColor) {
                cityCount++;
                supplyCount += city.getAddSupply();
            }
        }
        NumberSprite cityCountSprite = new NumberSprite(130, 98, false, "font02", cityCount);
        addSprite(cityCountSprite);

        NumberSprite supplySprite = new NumberSprite(330, 98, false, "font02", supplyCount);
        addSprite(supplySprite);

        if(pTeamColor == ID.TEAM_COLOR.BLUE){
            addSprite(new DelaySprite(GameSetup.DELAY_TURN_BEGIN_DLG_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__BLUE_TURN_BEGIN_TIME_OUT, null, battlefieldScene)));
        }else {

            addSprite(new DelaySprite(GameSetup.DELAY_TURN_BEGIN_DLG_S, new TouchMessage(ID.MSG_ID.MSG_SCENE_BATTLEFIELD__AI_TURN_BEGIN_TIME_OUT, null, battlefieldScene)));
        }

    }

    @Override
    public boolean isBacegroundEnabled() {
        return false;
    }

    @Override
    public void buildScene() {

    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {

    }
}

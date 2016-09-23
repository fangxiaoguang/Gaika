package com.game.gaika.action;

import com.game.gaika.data.City;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.dialg.TurnBeginDialog;
import com.game.gaika.sound.SoundManager;


/**
 * Created by fangxg on 2015/7/28.
 */
public class CrashTimeOutAction implements BaseAction {
    @Override
    public void doAction() {
        GameDataManager gdm = GameDataManager.getInstance();

        gdm.nextAiTeamColor();
        ID.TEAM_COLOR aiTeamColor = gdm.getCurrentAiTeamColor();
        gdm.aiTrunBegin(aiTeamColor);

        SoundManager.getInstance().playSound("messag01");
        BattlefieldScene battlefieldScene = new BattlefieldScene(false);
        City pointCity = gdm.getPointCity(aiTeamColor);
        battlefieldScene.getLogicCamera().setCente(pointCity.getPixelX(), pointCity.getPixelY());
        battlefieldScene.setDialogSecne(new TurnBeginDialog(battlefieldScene, aiTeamColor));
        SceneManager.render(battlefieldScene);
    }
}

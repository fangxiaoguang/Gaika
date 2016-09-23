package com.game.gaika.action;

import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.dialg.SystemPopupMenuScene;

/**
 * Created by fangxg on 2015/7/24.
 */
public class ButtonSystemAction implements BaseAction{
    @Override
    public void doAction() {
        BattlefieldScene battlefieldScene = new BattlefieldScene(false);

        SystemPopupMenuScene systemPopupMenuScene = new SystemPopupMenuScene( battlefieldScene);

        battlefieldScene.setDialogSecne(systemPopupMenuScene);
        SceneManager.render(battlefieldScene);
    }
}

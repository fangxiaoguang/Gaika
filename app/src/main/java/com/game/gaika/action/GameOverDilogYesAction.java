package com.game.gaika.action;

import com.game.gaika.scene.BeginMenuSceen;
import com.game.gaika.scene.SceneManager;

/**
 * Created by fangxg on 2015/7/25.
 */
public class GameOverDilogYesAction implements BaseAction {
    @Override
    public void doAction() {
        BeginMenuSceen beginMenuSceen = new BeginMenuSceen();
        SceneManager.render(beginMenuSceen );

    }
}

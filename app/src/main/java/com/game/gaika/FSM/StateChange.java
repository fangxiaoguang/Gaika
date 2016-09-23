package com.game.gaika.FSM;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fangxg on 2015/6/19.
 */
public interface StateChange {

    public void onStateChanged(Enum oldState, Enum newState, TouchMessage pTouchMessage);
    public void buildFSM(Enum  pCurrentStateID);
}

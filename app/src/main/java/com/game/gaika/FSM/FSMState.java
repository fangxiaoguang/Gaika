package com.game.gaika.FSM;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fangxg on 2015/6/16.
 */
public class FSMState {
    private Enum stateID;
    private Map<Enum, Enum> transistions = new HashMap<Enum, Enum>();

    public FSMState(Enum pStateID) {
        stateID = pStateID;

    }

    public Enum getStateID() {
        return stateID;
    }

    public void addTransition(Enum pInput, Enum pOutputID) {
        transistions.put(pInput, pOutputID);
    }

    public Enum getOutput(Enum pInput) {
        return transistions.get(pInput);
    }
}

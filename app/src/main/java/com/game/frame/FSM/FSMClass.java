package com.game.frame.FSM;

import com.game.gaika.data.GameSetup;
import com.game.gaika.debug.DebugManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fangxg on 2015/6/16.
 */
public class FSMClass {
    private Enum currentStateID;
    private Map<Enum, FSMState> states = new HashMap<Enum, FSMState>();

    public FSMClass(Enum pCurrentStateID) {
        currentStateID = pCurrentStateID;

    }

    public FSMClass(FSMState pState) {
        this(pState.getStateID());
        states.put(pState.getStateID(), pState);
    }

    public Enum getCurrentStateID() {
        return currentStateID;
    }

    public void setCurrentStateID(Enum pCurrentStateID) {
        currentStateID = pCurrentStateID;
    }

    public FSMState getFSMState(Enum pStateID) {
        return states.get(pStateID);
    }

    public void addState(FSMState pFSMState) {
        states.put(pFSMState.getStateID(), pFSMState);
    }

    public void stateTransition(Enum pInput) {
        Enum oldStateID = currentStateID;
        currentStateID = getFSMState(currentStateID).getOutput(pInput);
        if (currentStateID == null) {
            throw new IllegalArgumentException("FSMClass path not complete.   StateID:" + oldStateID + " -> MsgID: " + pInput);
        }
        if(GameSetup.isDebug_HUDTextBox){
            StringBuilder sb = new StringBuilder();
            sb.append("oldStateID: " + oldStateID);
            sb.append("\n");
            sb.append("     msgID: " + pInput);
            sb.append("\n");
            sb.append("newStateID: " + currentStateID);
            sb.append("\n");
            DebugManager.getInstance().stateInfo = sb.toString();
        }

    }
}

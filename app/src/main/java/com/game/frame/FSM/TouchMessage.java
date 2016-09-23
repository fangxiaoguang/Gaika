package com.game.frame.FSM;

import android.util.Log;

import com.game.gaika.debug.DebugManager;
import com.game.gaika.data.ID.MSG_ID;
/**
 * Created by fangxg on 2015/6/16.
 */
public class TouchMessage {
    //private int messageID;
    private MSG_ID messageID;
    private IMessageSender sender;
    private IMessageHandler handler;
    private int param;
    private Object data;

    public TouchMessage(MSG_ID pMessageID, IMessageSender pMsgSender, IMessageHandler pMsgHandler) {
        this(pMessageID, pMsgSender, pMsgHandler, 0);
    }

    public TouchMessage(MSG_ID pMessageID, IMessageSender pMsgSender, IMessageHandler pMsgHandler, int pParam) {
        this(pMessageID, pMsgSender, pMsgHandler, pParam, null);
    }

    private TouchMessage(MSG_ID pMessageID, IMessageSender pMsgSender, IMessageHandler pMsgHandler, int pParam, Object pData) {
        messageID = pMessageID;
        sender = pMsgSender;
        handler = pMsgHandler;
        param = pParam;
        data = pData;
    }

    public MSG_ID getMessageID() {
        return messageID;
    }

    public IMessageSender getSender() {
        return sender;
    }

    public IMessageHandler getHandler() {
        return handler;
    }

    public int getParam() {
        return param;
    }

    private Object getData() {
        return data;
    }

    @Override
    public String toString(){
        return "TouchMessage--  " + "ID:" + messageID + " SENDER:" + sender + " HANDLER:"+ handler + " PARAM:" + param + " DATA:" + data;
    }

    public void doProcess(){
        DebugManager.getInstance().recordMsg(this);
        Log.d("Gaika", this.toString());
        handler.onHandlMessage(this);
    }
}

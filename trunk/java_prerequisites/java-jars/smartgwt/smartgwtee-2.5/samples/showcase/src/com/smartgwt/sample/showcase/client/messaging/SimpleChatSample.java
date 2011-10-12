/*
 * Isomorphic SmartGWT web presentation layer
 * Copyright (c) 2011 Isomorphic Software, Inc.
 *
 * OWNERSHIP NOTICE
 * Isomorphic Software owns and reserves all rights not expressly granted in this source code,
 * including all intellectual property rights to the structure, sequence, and format of this code
 * and to all designs, interfaces, algorithms, schema, protocols, and inventions expressed herein.
 *
 *  If you have any questions, please email <sourcecode@isomorphic.com>.
 *
 *  This entire comment must accompany any portion of Isomorphic Software source code that is
 *  copied or moved from this file.
 */

package com.smartgwt.sample.showcase.client.messaging;

import com.smartgwt.client.core.KeyIdentifier;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.rpc.Messaging;
import com.smartgwt.client.rpc.MessagingCallback;
import com.smartgwt.client.rpc.RPCCallback;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.util.KeyCallback;
import com.smartgwt.client.util.Page;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.viewer.DetailViewer;

import com.smartgwt.sample.showcase.client.PanelFactory;
import com.smartgwt.sample.showcase.client.ShowcasePanel;
import com.smartgwt.sample.showcase.client.SourceEntity;


public class SimpleChatSample extends ShowcasePanel {
    private static final String DESCRIPTION = 
        "<p>This preview sample illustrates using the optional Real-Time Messaging (RTM) "+
        "module to build a simple chat application, using server push to distribute data "+
        "from the server.</p>";

    public static class Factory implements PanelFactory {

        private String id;

        public Canvas create() {
            SimpleChatSample panel = new SimpleChatSample();
            id = panel.getID();
            return panel;
        }

        public String getID() {
            return id;
        }

        public String getDescription() {
            return DESCRIPTION;
        }
    }

	private Canvas chatLog;
    private DynamicForm chatForm;
    
    public Canvas getViewPanel() {
        Canvas topCanvas=null;
        if(SC.hasRealtimeMessaging()) {
            VLayout layout = new VLayout();
            layout.setWidth(500);
            layout.setHeight100();
            
            chatLog = new Canvas();
            chatLog.setBackgroundColor("white");
            chatLog.setBorder("2px solid gray");
            chatLog.setContents(
                "Chat Session<br>"
                +"Open this page in multiple client browsers for multi-user chat.<br><br>");
            chatLog.setWidth(500);
            chatLog.setHeight(200);
            chatLog.setOverflow(Overflow.AUTO);
            
            layout.addMember(chatLog);
            
            chatForm = new DynamicForm();
            chatForm.setHeight(200);
            
            TextItem userName = new TextItem("user");
            userName.setRequired(true);
            userName.setTitle("User Name");
            
            TextAreaItem messageArea = new TextAreaItem("msg");
            messageArea.setHeight(50);
            messageArea.setTitle("Message");
            messageArea.setWidth(400);
            
            ButtonItem send = new ButtonItem();
            send.setTitle("Send");
            send.setColSpan("*");
            send.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    sendChatMessage();
                }
            });
            
            chatForm.setItems(userName, messageArea, send);
            
            Messaging.subscribe("chatChannel", new MessagingCallback() {
                @Override
                public void execute(Object data) {
                    chatLog.setContents(chatLog.getContents() + (String)data);
                }
            });
            
            layout.addMember(chatForm);
            
            topCanvas = layout;
        } else {
            HTMLFlow htmlFlow = new HTMLFlow("<div class='explorerCheckErrorMessage'><p>This example is disabled in this SDK because it requires the optional " +
            "<a href=\"http://www.smartclient.com/product/index.jsp\" target=\"_blank\">Real Time Messaging module</a>.</p>");
            htmlFlow.setWidth100();
            topCanvas = htmlFlow;
        }
        return topCanvas;
    }

    public void sendChatMessage() {
        if (!chatForm.validate()) return;
        String userName = (String) chatForm.getValue("user");
        Object messageText = chatForm.getValue("msg");
        if (messageText == null) return;
        String message = "<b>" + userName + ":</b> " + (String)messageText + "<br><br>";
        Messaging.send("chatChannel", message, new RPCCallback () {
            @Override
            public void execute(RPCResponse response, Object rawData,
                    RPCRequest request) {
                if (response.getStatus() != RPCResponse.STATUS_SUCCESS) SC.say("Failed send message to server.");
            }
        });
    }

    public String getIntro() {
        return DESCRIPTION;
    }
}
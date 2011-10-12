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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.rpc.Messaging;
import com.smartgwt.client.rpc.MessagingCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

import com.smartgwt.sample.showcase.client.PanelFactory;
import com.smartgwt.sample.showcase.client.ShowcasePanel;
import com.smartgwt.sample.showcase.client.SourceEntity;


public class StockQuotesSample extends ShowcasePanel {
    private static final String DESCRIPTION = 
        "<p>The grid is receiving simulated, real-time updates of stock data via the "+
        "Real Time Messaging (RTM) module. Updates stop after 90 seconds, after which time, "+
        "you may click on the &lquot;Generate more updates&rquot; button to continue "+
        "receiving updates.</p> " +
        "<p>The RTM module provides low-latency, high data volume streaming capabilities for "+
        "latency-sensitive applications such as trading desks and operations centers. "+
        "Randomly generated updates will stream from the server for 90 seconds &mdash; "+
        "click 'Generate more updates' to restart streaming.</p>" +
        "<p>The RTM module can connect to Java Message Service (JMS) channels without "+
        "writing any code, or can be connected to custom messaging solutions with a simple "+
        "adapter.</p>";

    public static class Factory implements PanelFactory {

        private String id;

        public Canvas create() {
            StockQuotesSample panel = new StockQuotesSample();
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

    private ListGrid stockQuotesGrid;
    
    public Canvas getViewPanel() {
        Canvas topCanvas=null;
        if(SC.hasRealtimeMessaging()) {
            final long startParameter = System.currentTimeMillis();
    
            // Grid and button below it
            VLayout contentLayout = new VLayout();
            contentLayout.setMembersMargin(10);
            contentLayout.setPadding(10);
            
            stockQuotesGrid = new ListGrid() {
                private int blinkPeriod = 2000;
    
                // Cell animation - go bright green or red on a change, then fades.
                protected String getCellCSSText(ListGridRecord record, final int rowNum,
                        final int colNum) {
                    // changeValue column
                    Date lastUpdated = record.getAttributeAsDate("lastUpdated");
                    if (colNum == 2 && lastUpdated != null) {
                        long delta = System.currentTimeMillis() - lastUpdated.getTime();
                        if (delta < blinkPeriod) {
                            // refresh 10x / second
                            new Timer() {
                                public void run() {
                                    stockQuotesGrid.refreshCell(rowNum, colNum);
                                }
                            }.schedule(100);
                            float changeValue = record.getAttributeAsFloat("changeValue");
    
                            float ratio = ((float) (blinkPeriod - delta)) / blinkPeriod;
                            int color = 255 - Math.round(200 * ratio);
    
                            if (changeValue > 0) {
                                return "background-color:#" + Integer.toHexString(color) + "FF"
                                        + Integer.toHexString(color);
                            } else if (changeValue < 0) {
                                return "background-color:#FF" + Integer.toHexString(color)
                                        + Integer.toHexString(color);
                            }
                        } else {
                            record.setAttribute("lastUpdated", (Date) null);
                        }
                    }
                    // no style override
                    return null;
                };
            };
            stockQuotesGrid.setWidth(600);
            stockQuotesGrid.setHeight(300);
            stockQuotesGrid.setShowAllRecords(true);
            stockQuotesGrid.setDataSource(StockQuotesDS.getInstance());
            stockQuotesGrid.setAutoFetchData(true);
            stockQuotesGrid.setCellFormatter(getFloatValuesFormatter());
            contentLayout.addMember(stockQuotesGrid);
    
            final Button generateUpdatesButton = new Button("Generate more updates");
            generateUpdatesButton.setWidth(200);
            generateUpdatesButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    generateUpdates(startParameter, generateUpdatesButton);
                }
            });
            contentLayout.addMember(generateUpdatesButton);
    
            // receive messages from the stockQuotes channel and update data grid
            Messaging.subscribe("stockQuotes" + startParameter, new MessagingCallback() {
                @Override
                public void execute(Object data) {
                    updateStockQuotes(data);
                }
            });
            
            generateUpdates(startParameter, generateUpdatesButton);
            
            topCanvas = contentLayout;
        } else {
            HTMLFlow htmlFlow = new HTMLFlow("<div class='explorerCheckErrorMessage'><p>This example is disabled in this SDK because it requires the optional " +
            "<a href=\"http://www.smartclient.com/product/index.jsp\" target=\"_blank\">Real Time Messaging module</a>.</p>");
            htmlFlow.setWidth100();
            topCanvas = htmlFlow;
        }
        return topCanvas;
    }
        
    /**
     * Float values should be formatted into human-readable text
     * 
     * @return
     */
    private CellFormatter getFloatValuesFormatter() {
        return new CellFormatter() {
            @Override
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                if (value == null)
                    return null;

                if (value instanceof Float) {
                    NumberFormat nf = NumberFormat.getFormat("0.00");
                    try {
                        return nf.format(((Number) value).floatValue());
                    } catch (Exception e) {
                        return value.toString();
                    }
                }
                return String.valueOf(value);
            }
        };
    }

    /**
     * We get id and changeValue only from server - combine it with the record
     * in the grid to get the rest of the fields
     * 
     * @param data
     */
    @SuppressWarnings("unchecked")
    private void updateStockQuotes(Object data) {
        List<List<?>> stockData = (List<List<?>>) JSOHelper
                .convertToJava((JavaScriptObject) data);
        List<Record> newStockData = new ArrayList<Record>();
        // prepare data for grid manually using received data from servlet
        // we receive only 'id' and 'change percent' data here
        for (List<?> recordData : stockData) {
            float change = ((Number) recordData.get(1)).floatValue();
            if (change != 0) {
                Integer id = (Integer) recordData.get(0);
                Record record = stockQuotesGrid.getDataAsRecordList().find("id", id);
                float lastValue = record.getAttributeAsFloat("lastValue");
                float newChangeValue = change * lastValue / 100;
                float newLastValue = newChangeValue + lastValue;
                record.setAttribute("changeValue", newChangeValue);
                record.setAttribute("lastValue", newLastValue);
                record.setAttribute("dayHighValue",
                        Math.max(record.getAttributeAsFloat("dayHighValue"), newLastValue));
                record.setAttribute("dayLowValue",
                        Math.min(record.getAttributeAsFloat("dayLowValue"), newLastValue));
                record.setAttribute("lastUpdated", new Date());
                newStockData.add(record);
            }
        }

        DSResponse dsResponse = new DSResponse();
        dsResponse.setData((Record[]) newStockData.toArray(new Record[newStockData.size()]));

        DSRequest dsRequest = new DSRequest();
        dsRequest.setOperationType(DSOperationType.UPDATE);
        // broadcast the change - the grid will notice this automatically (and so would other
        // components showing the same record)
        StockQuotesDS.getInstance().updateCaches(dsResponse, dsRequest);
    }

    private void generateUpdates(final long startParameter, final Button generateUpdatesButton) {
        generateUpdatesButton.disable();
        RPCRequest request = new RPCRequest();
        // we tells servlet which channel it should use for sending data
        request.setActionURL("examples/StockQuotes/generate?sp=" + startParameter);
        RPCManager.sendRequest(request);
        // block button repeat click for 90 seconds - time while servlet
        // will sends data to us
        new Timer() {
            public void run() {
                generateUpdatesButton.enable();
            }
        }.schedule(90000);
    }
    
    public String getIntro() {
        return DESCRIPTION;
    }
    
    public SourceEntity[] getSourceUrls() {
        return new SourceEntity[]{
            new SourceEntity("WEB-INF/web.xml", XML, "source/ds/common/web.xml.html", true),
            new SourceEntity("server/StockQuotesServlet.java", JAVA, "source/messaging/StockQuotesServlet.java.html", true),
            new SourceEntity("StockQuotesDS.java", JAVA, "source/messaging/StockQuotesDS.java.html", false),
            new SourceEntity("stockQuotes.data.xml", XML, "source/test_data/stockQuotes.data.xml.html", true)
        };
    }
}
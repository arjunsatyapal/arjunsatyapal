/*
 * Isomorphic SmartGWT web presentation layer
 * Copyright 2000 and beyond Isomorphic Software, Inc.
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

package com.smartgwt.sample.showcase.client.dataintegration.java.others;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.BooleanItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.SortNormalizer;
import com.smartgwt.client.types.Autofit;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ExportDisplay;
import com.smartgwt.client.types.ExportFormat;
import com.smartgwt.client.util.EnumUtil;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.sample.showcase.client.PanelFactory;
import com.smartgwt.sample.showcase.client.ShowcasePanel;

import java.util.LinkedHashMap;
import java.util.Date;

public class FormattedExportSample extends ShowcasePanel {
    private static final String DESCRIPTION = 
        "You can export the client-side data from a DataBoundComponent. That is, the data as "+
        "seen in a component, including the effects of client-side formatters." + 
        "<p>In the example below, choose an export format from the select-list, decide whether " + 
        "to download the results or view them in a window using the checkbox and click the " +
        "Export button." +
        "<p>Data is exported according to the filters and sort-order on the grid and includes " +
        "the formatted values and field-titles as seen in the grid.";
        
    public static class Factory implements PanelFactory {

        private String id;

        public Canvas create() {
            FormattedExportSample panel = new FormattedExportSample();
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

    protected boolean isTopIntro() {
        return true;
    }
    
    public Canvas getViewPanel() {
        final DataSource dataSource = DataSource.get("largeValueMap_orderItem");
    	
        final ListGrid orderListGrid = new ListGrid();
        orderListGrid.setWidth100();
        orderListGrid.setAlternateRecordStyles(true);
        orderListGrid.setDataSource(dataSource);
        orderListGrid.setAutoFetchData(true);

        ListGridField orderIdField = new ListGridField("orderID");
        orderIdField.setWidth(64);
        ListGridField itemIdField = new ListGridField("itemID", "Item Name");
        itemIdField.setWidth("*");
        itemIdField.setDisplayField("itemName");
        
        ListGridField quantityField = new ListGridField("quantity","Qty");
        quantityField.setWidth(48);
        ListGridField unitPriceField = new ListGridField("unitPrice");
        unitPriceField.setAlign(Alignment.RIGHT);
        unitPriceField.setWidth(64);
        ListGridField orderDateField = new ListGridField("orderDate");
        orderDateField.setWidth(144);
        final DateTimeFormat dateFormatter = DateTimeFormat.getFormat("EEE MMMM d yyyy");
        orderDateField.setCellFormatter(new CellFormatter() {
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                if (value == null) return (String) value;
                if (value.getClass() == java.util.Date.class) {
                    return dateFormatter.format((Date)value);
                }
                return value.toString();
            }
        });
        
        ListGridField itemTotalField = new ListGridField("itemTotalField", "Item Total");
        itemTotalField.setWidth(64);
        itemTotalField.setAlign(Alignment.RIGHT);
        itemTotalField.setCellAlign(Alignment.RIGHT);
        final NumberFormat numFormatter = NumberFormat.getFormat("###,###,###.00");
        itemTotalField.setCellFormatter(new CellFormatter() {
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
            	double quantity=record.getAttributeAsDouble("quantity");
            	double price=record.getAttributeAsDouble("unitPrice");
                return numFormatter.format(quantity*price);
            }
        });
        
        orderListGrid.setFields(orderIdField, itemIdField, quantityField, unitPriceField,
        		orderDateField, itemTotalField);
        orderListGrid.setShowFilterEditor(true);

        final DynamicForm exportForm = new DynamicForm();
        
        exportForm.setWidth(300);

        SelectItem exportTypeItem = new SelectItem("exportType", "Export Type");
        exportTypeItem.setWidth("*");
        exportTypeItem.setDefaultToFirstOption(true);

        LinkedHashMap valueMap = new LinkedHashMap();
        valueMap.put("csv", "CSV (Excel)");
        valueMap.put("xml", "XML");
        valueMap.put("json", "JSON");
        valueMap.put("xls", "XLS (Excel97)");
        valueMap.put("ooxml", "XLSX (Excel2007/OOXML)");

        exportTypeItem.setValueMap(valueMap);

        BooleanItem showInWindowItem = new BooleanItem();
        showInWindowItem.setName("showInWindow");
        showInWindowItem.setTitle("Show in Window");
        showInWindowItem.setAlign(Alignment.LEFT);

        exportForm.setItems(exportTypeItem, showInWindowItem);

        IButton exportButton = new IButton("Export");
        exportButton.addClickHandler(new ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                String exportAs = (String) exportForm.getField("exportType").getValue();

                FormItem item = exportForm.getField("showInWindow");
                boolean showInWindow =  item.getValue() == null ? false : (Boolean) item.getValue();

                if(exportAs.equals("json")) {
                    // JSON exports are server-side only, so use the OperationBinding on the DataSource
                    DSRequest dsRequestProperties = new DSRequest();
                    dsRequestProperties.setOperationId("customJSONExport");
                    dsRequestProperties.setExportDisplay(showInWindow ? ExportDisplay.WINDOW : ExportDisplay.DOWNLOAD);

                    orderListGrid.exportClientData(dsRequestProperties);
                } else {
                    // exportAs is either XML or CSV, which we can do with requestProperties
                    DSRequest dsRequestProperties = new DSRequest();
                    dsRequestProperties.setExportAs((ExportFormat)EnumUtil.getEnum(ExportFormat.values(), exportAs));
                    dsRequestProperties.setExportDisplay(showInWindow ? ExportDisplay.WINDOW : ExportDisplay.DOWNLOAD);

                    orderListGrid.exportClientData(dsRequestProperties);
                }
            }
        });
    	
        VLayout layout = new VLayout(15);
        layout.setHeight100();

        HLayout formLayout = new HLayout(15);
        formLayout.setAutoHeight();
        formLayout.addMember(exportForm);
        formLayout.addMember(exportButton);
        layout.addMember(formLayout);

        layout.addMember(orderListGrid);

        return layout;
    }

    public String getIntro() {
        return DESCRIPTION;
    }
}
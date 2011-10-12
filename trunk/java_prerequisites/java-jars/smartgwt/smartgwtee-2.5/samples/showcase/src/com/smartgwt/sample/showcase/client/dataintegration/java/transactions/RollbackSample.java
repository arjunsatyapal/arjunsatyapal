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
package com.smartgwt.sample.showcase.client.dataintegration.java.transactions;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.sample.showcase.client.PanelFactory;
import com.smartgwt.sample.showcase.client.ShowcasePanel;

public class RollbackSample extends ShowcasePanel {

    private static final String DESCRIPTION = "SmartClient Server detects when a DSRequest that is part of a transaction fails, " +
            "and automatically rolls the transaction back.<p/>" +
            "Change several records in the grid, then click \"Save\".  The underlying DataSource " +
            "specifies a \"hasRelatedRecord\" validation on the country name, looking up against " +
            "all the countries of the world; if you change a country's name to something " +
            "non-existent, that validation will fail and the entire transaction will be rolled " +
            "back.  All of your changes will remain pending (the changed values will still be " +
            "shown in blue), and if you refresh the page you can verify that the data is " +
            "unchanged on the server.<p/>" +
            "If you correct the validation error and click \"Save\" again, the transaction will " +
            "be committed and your changes will be persisted.";

    public static class Factory implements PanelFactory {

        private String id;

        public Canvas create() {
            RollbackSample panel = new RollbackSample();
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
        final ListGrid listGrid = new ListGrid();
        listGrid.setWidth(300);
        listGrid.setHeight(224);
        listGrid.setAlternateRecordStyles(true);
        listGrid.setDataSource(DataSource.get("rbCountryTransactions"));
        listGrid.setAutoFetchData(true);
        listGrid.setCanEdit(true);
        listGrid.setAutoSaveEdits(false);
        listGrid.setFields(new ListGridField[] {
            new ListGridField("countryName"),
            new ListGridField("capital"),
            new ListGridField("continent"),
            new ListGridField("gdp")
        });

        IButton saveButton = new IButton("Save");
        saveButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                listGrid.saveAllEdits();
            }
        });

        VLayout layout = new VLayout(15);
        layout.addMember(listGrid);
        layout.addMember(saveButton);

        return layout;
    }

    public String getIntro() {
        return DESCRIPTION;
    // Do not remove commented lines.
    // Forces GenerateSourceFiles to generate DataURLRecords for both data sources.
    // Generated DataURLRecords are used in 'View source' window for showing data source configuration.
//        DataSource.get("rbCountryTransactions");
//        DataSource.get("worldDS");
    }
}

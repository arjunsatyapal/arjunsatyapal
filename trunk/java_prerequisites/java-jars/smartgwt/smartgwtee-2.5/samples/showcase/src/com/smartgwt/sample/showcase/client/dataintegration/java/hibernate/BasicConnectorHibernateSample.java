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

package com.smartgwt.sample.showcase.client.dataintegration.java.hibernate;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.sample.showcase.client.PanelFactory;
import com.smartgwt.sample.showcase.client.ShowcasePanel;


public class BasicConnectorHibernateSample extends ShowcasePanel {
    private static final String DESCRIPTION = "Beanless mode allows you to use Hibernate for persistence without writing any Java code at all. " +
            "Declare the properties of your object in your DataSource descriptor (.ds.xml file), and " +
            "SmartClient will generate the Hibernate configuration automatically.  You can use the Admin " +
            "Console to generate the underlying SQL table as well, so the only file you create is the .ds.xml file.<p/>" +
            "As with the previous example, the grid below provides the ability to search, edit, and delete records.<p/>" +
            "Beanless mode helps you avoid writing boilerplate Java code (several classes full of getter " +
            "and setter methods that do nothing) for simple entities.  Even in beanless mode, you can still " +
            "use DMI to add Java business logic that takes place before and after Hibernate operations; the " +
            "Hibernate data is represented as a Java Map.<p/>" +
            "You can also use a mixture of beanless mode and normal Hibernate beans, even in the same transaction.";

    public static class Factory implements PanelFactory {

        private String id;

        public Canvas create() {
            BasicConnectorHibernateSample panel = new BasicConnectorHibernateSample();
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
        listGrid.setWidth(700);
        listGrid.setHeight(224);
        listGrid.setAlternateRecordStyles(true);
        listGrid.setDataSource(DataSource.get("worldHB"));
        listGrid.setAutoFetchData(true);
        listGrid.setShowFilterEditor(true);
        listGrid.setCanEdit(true);
        listGrid.setEditEvent(ListGridEditEvent.CLICK);
        listGrid.setCanRemoveRecords(true);

        IButton newButton = new IButton("Add New");
        newButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                listGrid.startEditingNew();
            }
        });


        VLayout layout = new VLayout(15);
        layout.addMember(listGrid);
        layout.addMember(newButton);

        return layout;
    }

    public String getIntro() {
        return DESCRIPTION;
    }
}
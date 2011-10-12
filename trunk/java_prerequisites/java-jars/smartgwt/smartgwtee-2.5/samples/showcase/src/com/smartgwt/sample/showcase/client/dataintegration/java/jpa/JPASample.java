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
package com.smartgwt.sample.showcase.client.dataintegration.java.jpa;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.sample.showcase.client.PanelFactory;
import com.smartgwt.sample.showcase.client.ShowcasePanel;
import com.smartgwt.sample.showcase.client.SourceEntity;

public class JPASample extends ShowcasePanel {

    private static final String DESCRIPTION = "If you have pre-existing JPA entities, SmartClient can automatically derive fully functional " +
            "DataSources given just the Java classname of the mapped JPA entity.  The grid below is connected " +
            "to a JPA-managed entity via the simple declarations in supplyItemJPAAutoDerive.ds.xml - no other " +
            "configuration or Java code is required beyond the entity itself with JPA mapping, which are " +
            "samples intended to represent a pre-existing JPA entity.<p/>" +
            "To search, use the controls above the grid's header. Note that data paging is automatically " +
            "enabled - just scroll down to load data on demand. Click on the red icon next to each record to " +
            "delete it. Click on a record to edit it and click \"Add New\" to add a new record.  Note that the " +
            "editing controls are type sensitive: a date picker appears for the \"Next Shipment\" field, and " +
            "the \"Units\" field shows a picklist because its Java type is an Enum.<p/>" +
            "You can use DMI to add business logic that takes place before and after JPA operations to " +
            "enforce security or add additional data validation rules.<p/>" +
            "In server.properties file you can specify entity manager factory acquisition and transaction management " +
            "mode by setting \"jpa.emfProvider\" property to:<ul>" +
            "<li>com.isomorphic.jpa.EMFProviderLMT - for Locally Managed Transactions (LMT)</li>" +
            "<li>com.isomorphic.jpa.EMFProviderNoTransactions - no transactions support</li>" +
            "<li>com.isomorphic.jpa.EMFProviderCMT - for Container Managed Transactions (CMT)</li>" +
            "<li>your implementation of com.isomorphic.jpa.EMFProviderInterface</li></ul>" +
            "For LMT provider you have to specify \"jpa.persistenceUnitName\" property specifying PU name.<br/>" +
            "For CMT provider you have to specify \"jpa.cmt.entityManager\" and \"jpa.cmt.transaction\" properties " +
            "specifying appropriate resource reference names declared in /WEB-INF/web.xml.<p/>" +
            "When creating DataSource descriptors specify properties:<ul>" +
            "<li>\"serverConstructor\" to:<ul>" +
            "<li>com.isomorphic.jpa.JPADataSource - for JPA 1.0 implementation</li>" +
            "<li>com.isomorphic.jpa.JPA2DataSource - for JPA 2.0 implementation which uses Criteria API</li></ul></li>" +
            "<li>\"beanClassName\" to fully qualified entity class name</li></ul>";

    public static class Factory implements PanelFactory {

        private String id;

        public Canvas create() {
            JPASample panel = new JPASample();
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
        listGrid.setDataSource(DataSource.get("supplyItemJPAAutoDerive"));
        listGrid.setAutoFetchData(true);
        listGrid.setShowFilterEditor(true);
        listGrid.setDataPageSize(50);
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

    public SourceEntity[] getSourceUrls() {
        return new SourceEntity[]{
                new SourceEntity("SupplyItemHB.java", JAVA, "source/beans/SupplyItemHB.java.html", true),
                new SourceEntity("META-INF/persistence.xml", XML, "source/ds/common/persistence1.xml.html", true)
            };
    }
}

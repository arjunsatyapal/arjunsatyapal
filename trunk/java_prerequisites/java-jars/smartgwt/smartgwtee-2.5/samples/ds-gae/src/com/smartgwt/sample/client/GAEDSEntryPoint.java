package com.smartgwt.sample.client;

import com.smartgwt.client.widgets.Label;

import com.smartgwt.client.widgets.layout.LayoutSpacer;

import com.smartgwt.client.widgets.Img;

import com.smartgwt.client.widgets.layout.HLayout;

import com.smartgwt.client.widgets.form.DynamicForm;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.core.KeyIdentifier;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.rpc.RPCCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.util.KeyCallback;
import com.smartgwt.client.util.Page;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.EditorEnterEvent;
import com.smartgwt.client.widgets.grid.events.EditorEnterHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VStack;

public class GAEDSEntryPoint implements EntryPoint {

    /**
     * Creates a new instance of GAEDSEntryPoint
     */
    public GAEDSEntryPoint() {
    }

    /**
     * The entry point method, called automatically by loading a module that declares an
     * implementing class as an entry-point
     */
    public void onModuleLoad() {
        // handleOriginal();
        
        // URL : http://www.smartclient.com/releases/SmartGWT_Quick_Start_Guide.pdf
        // Page : 18
//        handleListGrid();
        
        // URL : http://www.smartclient.com/releases/SmartGWT_Quick_Start_Guide.pdf
        // Page 21
//        handleContactsDataSource();
        
     // URL : http://www.smartclient.com/releases/SmartGWT_Quick_Start_Guide.pdf
        // Page 30
        handleLayouts();
        
    }

    /**
     * 
     */
    private void handleLayouts() {
        HLayout hLayout = new HLayout(10);
        hLayout.setID("myPageHeader");
        hLayout.setHeight(50);
        hLayout.setLayoutMargin(10);
        hLayout.addMember(new Img("lantern_logo.png"));
        hLayout.addMember(new LayoutSpacer());
        hLayout.addMember(new Label("My Title"));
        hLayout.draw();
    }

    /**
     * 
     */
    private void handleContactsDataSource() {
        DataSource contactsDS = DataSource.get("contacts");
        ListGrid grid = new ListGrid();
        grid.setDataSource(contactsDS);
        DynamicForm form = new DynamicForm();
        form.setLeft(300); // avoid overlap
        form.setDataSource(contactsDS);
        
        grid.setWidth100();
        grid.draw();
    }

    /**
     * 
     */
    private void handleListGrid() {
        // TODO Auto-generated method stub
        ListGrid grid = new ListGrid();
        ListGridField salutationField = new ListGridField();
        salutationField.setName("salutation");
        salutationField.setTitle("Title");
        ListGridField firstNameField = new ListGridField();
        firstNameField.setName("firstname");
        firstNameField.setTitle("First Name");
        ListGridField lastNameField = new ListGridField();
        lastNameField.setName("lastname");
        lastNameField.setTitle("Last Name");
        grid.setFields(salutationField,
                firstNameField,
                lastNameField);
        grid.setData(getListGridRecords());
        grid.draw();

    }
    
    private ListGridRecord[] getListGridRecords() {
        ListGridRecord record1 = new ListGridRecord();
        record1.setAttribute("salutation", "Ms");
        record1.setAttribute("firstname", "Kathy");
        record1.setAttribute("lastname", "Whitting");
        ListGridRecord record2 = new ListGridRecord();
        record2.setAttribute("salutation", "Mr");
        record2.setAttribute("firstname", "Chris");
        record2.setAttribute("lastname", "Glover");
        ListGridRecord record3 = new ListGridRecord();
        record3.setAttribute("salutation", "Mrs");
        record3.setAttribute("firstname", "Gwen");
        record3.setAttribute("lastname", "Glover");
        
        return new ListGridRecord[] {record1, record2, record3};
    }

    /**
     * 
     */
    private void handleOriginal() {
        KeyIdentifier debugKey = new KeyIdentifier();
        debugKey.setCtrlKey(true);
        debugKey.setKeyName("D");

        Page.registerKey(debugKey, new KeyCallback() {
            public void execute(String keyName) {
                SC.showConsole();
            }
        });

        VStack vStack = new VStack();
        vStack.setLeft(175);
        vStack.setTop(75);
        vStack.setWidth("70%");
        vStack.setMembersMargin(20);

        final DataSource countryDS = DataSource.get("country_DataSource");

        final ListGrid countryGrid = new ListGrid();
        countryGrid.setWidth(700);
        countryGrid.setHeight(224);
        countryGrid.setAlternateRecordStyles(true);
        countryGrid.setDataSource(countryDS);
        countryGrid.setAutoFetchData(true);
        countryGrid.setShowFilterEditor(true);
        countryGrid.setCanEdit(true);
        countryGrid.setEditEvent(ListGridEditEvent.CLICK);
        countryGrid.setCanRemoveRecords(true);

        ListGridField countryCode = new ListGridField("countryCode", "Code", 50);
        ListGridField countryName = new ListGridField("countryName", "Country");

        countryGrid.setFields(countryCode, countryName);

        IButton newCountryButton = new IButton("New country");
        newCountryButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                countryGrid.startEditingNew();
            }
        });

        DataSource cityDS = DataSource.get("city_DataSource");
        final ListGrid cityGrid = new ListGrid();
        cityGrid.setWidth(700);
        cityGrid.setHeight(224);
        cityGrid.setAlternateRecordStyles(true);
        cityGrid.setDataSource(cityDS);
        cityGrid.setAutoFetchData(false);
        cityGrid.setShowFilterEditor(true);
        cityGrid.setCanEdit(true);
        cityGrid.setEditEvent(ListGridEditEvent.CLICK);
        cityGrid.setCanRemoveRecords(true);

        ListGridField cityName = new ListGridField("cityName", "City");

        cityGrid.setFields(cityName);

        IButton newCityButton = new IButton("New city");
        newCityButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                ListGridRecord record = countryGrid.getSelectedRecord();
                if (record != null) {
                    cityGrid.startEditingNew();
                }
                else {
                    SC.warn("Select country first.");
                }
            }
        });

        countryGrid.addSelectionChangedHandler(new SelectionChangedHandler()
        {
            public void onSelectionChanged(SelectionEvent event)
            {
                if (event.getState()) {
                    ListGridRecord record = countryGrid.getSelectedRecord();
                    if (record != null) {
                        cityGrid.fetchRelatedData(record, countryDS);
                    }
                }
            }
        });

        cityGrid.addEditorEnterHandler(new EditorEnterHandler()
        {
            public void onEditorEnter(EditorEnterEvent event)
            {
                cityGrid.setEditValue(event.getRowNum(), "countryId",
                        countryGrid.getSelectedRecord().getAttribute("countryId"));
            }
        });

        IButton fillDataButton = new IButton("Add some data");
        fillDataButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event)
            {
                RPCRequest req = new RPCRequest();
                req.setActionURL("/fillData");
                RPCManager.sendRequest(req, new RPCCallback() {
                    public void execute(RPCResponse response, Object rawData,
                            RPCRequest request)
                    {
                        countryGrid.fetchData();
                    }
                });
            }
        });

        vStack.addMember(newCountryButton);
        vStack.addMember(countryGrid);
        vStack.addMember(newCityButton);
        vStack.addMember(cityGrid);
        vStack.addMember(fillDataButton);

        vStack.draw();

        countryGrid.getResultSet().setUseClientFiltering(Boolean.FALSE);
        countryGrid.getResultSet().setUseClientSorting(Boolean.FALSE);
    }
}

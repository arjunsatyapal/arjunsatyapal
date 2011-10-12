package com.smartgwt.sample.showcase.client.messaging;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;

/**
 * Datasource definition for stock quotes data.
 */
class StockQuotesDS extends DataSource {  
    // The DataSource would normally be defined external to any classes that use it.  

    private static StockQuotesDS instance = null;  
      
    public static StockQuotesDS getInstance() {  
        if (instance == null) {  
          instance = new StockQuotesDS("stockQuotesDS_DS");  
        }  
        return instance;  
    }  

    public StockQuotesDS(String id) {  
        setID(id);  
        setRecordXPath("/List/stockQuotes");
        
        DataSourceField idField = new DataSourceField("id", FieldType.INTEGER, "Id");
        idField.setHidden(Boolean.TRUE);
        idField.setPrimaryKey(Boolean.TRUE);
        DataSourceField nameField = new DataSourceField("name", FieldType.TEXT, "Name");
        nameField.setAttribute("width", 200);
        DataSourceField symbolField = new DataSourceField("symbol", FieldType.TEXT, "Symbol");
        DataSourceField lastValueField = new DataSourceField("lastValue", FieldType.FLOAT, "Last");
        DataSourceField changeValueField = new DataSourceField("changeValue", FieldType.FLOAT, "Change");
        DataSourceField openValueField = new DataSourceField("openValue", FieldType.FLOAT, "Open");
        DataSourceField dayHighValueField = new DataSourceField("dayHighValue", FieldType.FLOAT, "DayHigh");
        DataSourceField dayLowValueField = new DataSourceField("dayLowValue", FieldType.FLOAT, "DayLow");

        setFields(idField, nameField, symbolField, lastValueField, changeValueField, openValueField, dayHighValueField, dayLowValueField);  
        setDataURL("ds/test_data/stockQuotes.data.xml");  
    }  

}
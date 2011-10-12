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
package com.smartgwt.sample.showcase.server.transactions;

import java.sql.*;
import com.isomorphic.datasource.*;

public class JDBCOperations {

    public DSResponse goodJDBCUpdate(DSRequest req) throws Exception {
        return update(req, "UPDATE lastUpdated SET lastUpdatedTime = ? WHERE pk = ?");
    }

    public DSResponse badJDBCUpdate(DSRequest req) throws Exception {
        // Deliberately broken - misspelt column name
        return update(req, "UPDATE lastUpdated SET lastUpatedTime = ? WHERE pk = ?");
    }
    
    public DSResponse update(DSRequest req, String sql) throws Exception {
        
        // We must mark the DSRequest as transactional, so that SmartClient Server knows whether
        // to mark it as failed if another transactional update fails
        req.setPartOfTransaction(true);
        
        // We make the update part of the transaction by using the dataSource's transaction
        // object, which in the case of a SQLDataSource will be a java.sql.Connection
        Connection conn = (Connection)((BasicDataSource)req.getDataSource()).getTransactionObject(req);
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
        Object pk = req.getValues().get("pk");
        stmt.setInt(2, Integer.parseInt(pk.toString()));
        stmt.executeUpdate();
        stmt.close();
        DSResponse resp = new DSResponse();
        resp.setStatus(DSResponse.STATUS_SUCCESS);
        return resp;
    }
}

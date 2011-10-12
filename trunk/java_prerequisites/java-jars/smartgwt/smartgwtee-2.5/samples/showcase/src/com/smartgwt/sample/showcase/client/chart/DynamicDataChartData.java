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
package com.smartgwt.sample.showcase.client.chart;

import com.smartgwt.client.widgets.tree.TreeNode;

public class DynamicDataChartData extends TreeNode {

    public DynamicDataChartData(String id, String parentId, String title) {
        setID(id);
        if (parentId != null) {
            setParentID(parentId);
        }
        setTitle(title);
    }

    public static DynamicDataChartData[] getData() {
        return new DynamicDataChartData[] {
            new DynamicDataChartData("sum", null, "All Years"),
            new DynamicDataChartData("2002", "sum", "2002"),
            new DynamicDataChartData("2003", "sum", "2002"),
            new DynamicDataChartData("2004", "sum", "2004"),
            new DynamicDataChartData("Q1-2002", "2002", "Q1-2002"),
            new DynamicDataChartData("Q2-2002", "2002", "Q2-2002"),
            new DynamicDataChartData("Q3-2002", "2002", "Q3-2002"),
            new DynamicDataChartData("Q4-2002", "2002", "Q4-2002"),
            new DynamicDataChartData("Q1-2003", "2003", "Q1-2003"),
            new DynamicDataChartData("Q2-2003", "2003", "Q2-2003"),
            new DynamicDataChartData("Q3-2003", "2003", "Q3-2003"),
            new DynamicDataChartData("Q4-2003", "2003", "Q4-2003"),
            new DynamicDataChartData("Q1-2004", "2004", "Q1-2004"),
            new DynamicDataChartData("Q2-2004", "2004", "Q2-2004"),
            new DynamicDataChartData("Q3-2004", "2004", "Q3-2004"),
            new DynamicDataChartData("Q4-2004", "2004", "Q4-2004"),
            new DynamicDataChartData("1/1/2002", "Q1-2002", "1/1/2002"),
            new DynamicDataChartData("2/1/2002", "Q1-2002", "2/1/2002"),
            new DynamicDataChartData("3/1/2002", "Q1-2002", "3/1/2002"),
            new DynamicDataChartData("4/1/2002", "Q2-2002", "4/1/2002"),
            new DynamicDataChartData("5/1/2002", "Q2-2002", "5/1/2002"),
            new DynamicDataChartData("6/1/2002", "Q2-2002", "6/1/2002"),
            new DynamicDataChartData("7/1/2002", "Q3-2002", "7/1/2002"),
            new DynamicDataChartData("8/1/2002", "Q3-2002", "8/1/2002"),
            new DynamicDataChartData("9/1/2002", "Q3-2002", "9/1/2002"),
            new DynamicDataChartData("10/1/2002", "Q4-2002", "10/1/2002"),
            new DynamicDataChartData("11/1/2002", "Q4-2002", "11/1/2002"),
            new DynamicDataChartData("12/1/2002", "Q4-2002", "12/1/2002"),
            new DynamicDataChartData("1/1/2003", "Q1-2003", "1/1/2003"),
            new DynamicDataChartData("2/1/2003", "Q1-2003", "2/1/2003"),
            new DynamicDataChartData("3/1/2003", "Q1-2003", "3/1/2003"),
            new DynamicDataChartData("4/1/2003", "Q2-2003", "4/1/2003"),
            new DynamicDataChartData("5/1/2003", "Q2-2003", "5/1/2003"),
            new DynamicDataChartData("6/1/2003", "Q2-2003", "6/1/2003"),
            new DynamicDataChartData("7/1/2003", "Q3-2003", "7/1/2003"),
            new DynamicDataChartData("8/1/2003", "Q3-2003", "8/1/2003"),
            new DynamicDataChartData("9/1/2003", "Q3-2003", "9/1/2003"),
            new DynamicDataChartData("10/1/2003", "Q4-2003", "10/1/2003"),
            new DynamicDataChartData("11/1/2003", "Q4-2003", "11/1/2003"),
            new DynamicDataChartData("12/1/2003", "Q4-2003", "12/1/2003"),
            new DynamicDataChartData("1/1/2004", "Q1-2004", "1/1/2004"),
            new DynamicDataChartData("2/1/2004", "Q1-2004", "2/1/2004"),
            new DynamicDataChartData("3/1/2004", "Q1-2004", "3/1/2004"),
            new DynamicDataChartData("4/1/2004", "Q2-2004", "4/1/2004"),
            new DynamicDataChartData("5/1/2004", "Q2-2004", "5/1/2004"),
            new DynamicDataChartData("6/1/2004", "Q2-2004", "6/1/2004"),
            new DynamicDataChartData("7/1/2004", "Q3-2004", "7/1/2004"),
            new DynamicDataChartData("8/1/2004", "Q3-2004", "8/1/2004"),
            new DynamicDataChartData("9/1/2004", "Q3-2004", "9/1/2004"),
            new DynamicDataChartData("10/1/2004", "Q4-2004", "10/1/2004"),
            new DynamicDataChartData("11/1/2004", "Q4-2004", "11/1/2004"),
            new DynamicDataChartData("12/1/2004", "Q4-2004", "12/1/2004"),
        };
    }

}

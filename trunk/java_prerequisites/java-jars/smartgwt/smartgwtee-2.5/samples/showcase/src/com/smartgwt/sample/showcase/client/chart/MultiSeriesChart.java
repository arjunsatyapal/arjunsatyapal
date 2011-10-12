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

import com.smartgwt.client.types.ChartType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.chart.FacetChart;
import com.smartgwt.client.widgets.cube.Facet;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.sample.showcase.client.PanelFactory;
import com.smartgwt.sample.showcase.client.ShowcasePanel;
import com.smartgwt.sample.showcase.client.SourceEntity;

public class MultiSeriesChart extends ShowcasePanel {

    private static final String DESCRIPTION = 
            "<p>Multi-series charts can be viewed with \"stacked\" data (to show totals) or \"unstacked\" to compare " +
            "values from each series. The \"Area\" chart type defaults to using stacked data, while the \"Line\" chart " +
            "type defaults to unstacked. You can use the default setting, or explicitly specify whether to stack data.</p>" +
            "<p>Use the \"Chart Type\" selector to see same data rendered by multiple different chart types. " +
            "You can also right-click on the chart to change the way data is visualized.</p>";

    public static class Factory implements PanelFactory {

        private String id;

        public Canvas create() {
            MultiSeriesChart panel = new MultiSeriesChart();
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

    @Override
    protected boolean isTopIntro() {
        return true;
    }

    public Canvas getViewPanel() {

        if(SC.hasAnalytics()) {
            if(SC.hasDrawing()) {
                final FacetChart chart = new FacetChart();
                chart.setData(MultiSeriesChartData.getData());
                chart.setFacets(new Facet("time", "Period"), new Facet("region", "Region"));
                chart.setValueProperty("value");
                chart.setChartType(ChartType.AREA);
                chart.setTitle("Revenue");

                final DynamicForm chartSelector = new DynamicForm();
                final SelectItem chartType = new SelectItem("chartType", "Chart Type");
                chartType.setValueMap(
                        ChartType.AREA.getValue(),
                        ChartType.BAR.getValue(),
                        ChartType.COLUMN.getValue(),
                        ChartType.DOUGHNUT.getValue(),
                        ChartType.LINE.getValue(),
                        ChartType.PIE.getValue(),
                        ChartType.RADAR.getValue());
                chartType.setDefaultToFirstOption(true);
                chartType.addChangedHandler(new ChangedHandler() {
                    public void onChanged(ChangedEvent event) {
                        String selectedChartType = chartType.getValueAsString();
                        if (ChartType.AREA.getValue().equals(selectedChartType)) {
                            chart.setChartType(ChartType.AREA);
                        } else if (ChartType.BAR.getValue().equals(selectedChartType)) {
                            chart.setChartType(ChartType.BAR);
                        } else if (ChartType.COLUMN.getValue().equals(selectedChartType)) {
                            chart.setChartType(ChartType.COLUMN);
                        } else if (ChartType.DOUGHNUT.getValue().equals(selectedChartType)) {
                            chart.setChartType(ChartType.DOUGHNUT);
                        } else if (ChartType.LINE.getValue().equals(selectedChartType)) {
                            chart.setChartType(ChartType.LINE);
                        } else if (ChartType.PIE.getValue().equals(selectedChartType)) {
                            chart.setChartType(ChartType.PIE);
                        } else if (ChartType.RADAR.getValue().equals(selectedChartType)) {
                            chart.setChartType(ChartType.RADAR);
                        }
                    }
                });
                chartSelector.setFields(chartType);

                VLayout layout = new VLayout(15);
                layout.addMember(chartSelector);
                layout.addMember(chart);

                return layout;
            } else {
                HTMLFlow htmlFlow = new HTMLFlow("<div class='explorerCheckErrorMessage'><p>This example is disabled in this SDK because it requires the optional " +
                "<a href=\"http://www.smartclient.com/product/index.jsp#drawing\" target=\"_blank\">Drawing module</a>.</p>" +
                "<p>Click <a href=\"http://www.smartclient.com/smartgwtee/showcase/#multiSeriesChart\" target=\"\">here</a> to see this example on smartclient.com</p></div>");
                htmlFlow.setWidth100();
                return htmlFlow;
            }
        } else {
            HTMLFlow htmlFlow = new HTMLFlow("<div class='explorerCheckErrorMessage'><p>This example is disabled in this SDK because it requires the optional " +
            "<a href=\"http://www.smartclient.com/product/index.jsp#analytics\" target=\"_blank\">Analytics module</a>.</p>" +
            "<p>Click <a href=\"http://www.smartclient.com/smartgwtee/showcase/#multiSeriesChart\" target=\"\">here</a> to see this example on smartclient.com</p></div>");
            htmlFlow.setWidth100();
            return htmlFlow;
        }
    }

    @Override
    public String getIntro() {
        return DESCRIPTION;
    }

    @Override
    public SourceEntity[] getSourceUrls() {
        return new SourceEntity[]{
                    new SourceEntity("MultiSeriesChartData.java", JAVA, "source/chart/MultiSeriesChartData.java.html", false)
                };
    }
}

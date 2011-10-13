/* 
 * Smart GWT (GWT for SmartClient) 
 * Copyright 2008 and beyond, Isomorphic Software, Inc. 
 * 
 * Smart GWT is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License version 3 
 * as published by the Free Software Foundation.  Smart GWT is also 
 * available under typical commercial license terms - see 
 * http://smartclient.com/license 
 * 
 * This software is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * Lesser General Public License for more details. 
 */  
package com.google.lantern.client;
  

import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.user.client.ui.Composite;

import com.smartgwt.client.widgets.tab.Tab;

import com.smartgwt.client.types.Side;

import com.smartgwt.client.widgets.tab.TabSet;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
  
public class LanternMain implements EntryPoint {  
  
    public void onModuleLoad() {  
//  
//        VLayout mainLayout = new VLayout();
//        mainLayout.setHeight100();
//        mainLayout.setWidth100();
//        
//        getTitleBar(mainLayout);
//        
//        getWorkSpace(mainLayout);
//        
//        mainLayout.draw();
        
        RootPanel.get().clear();
        RootPanel.get().add(new MainLayout());
    }

    private void getTitleBar(VLayout mainLayout) {
        HLayout titleBar = new HLayout();
        titleBar.setWidth100();
        titleBar.setHeight("30%");
        mainLayout.addChild(titleBar);
        
        Img logo = new Img("lantern_logo.png");
        titleBar.addChild(logo);
    }  

    private void getWorkSpace(VLayout mainLayout) {
        final TabSet tabSet = new TabSet();  
        tabSet.setTabBarPosition(Side.TOP);  
        tabSet.setTabBarAlign(Side.RIGHT);  
        tabSet.setWidth100() ; 
        tabSet.setHeight100();  
  
        Tab student = new Tab("Blue", "pieces/16/pawn_blue.png");  
        Tab teacher = new Tab("Green", "pieces/16/pawn_green.png");  
        Tab author = new Tab("Green", "pieces/16/pawn_green.png");
  
        tabSet.addTab(student);  
        tabSet.addTab(teacher);
        tabSet.addTab(author);
        
        HLayout workArea = new HLayout();
        workArea.addChild(tabSet);
        mainLayout.addChild(workArea);
    }


}  
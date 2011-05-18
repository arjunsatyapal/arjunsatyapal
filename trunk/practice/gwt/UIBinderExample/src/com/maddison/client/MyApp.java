package com.maddison.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.maddison.client.widgets.ImageGalleryWidget;
import com.maddison.client.widgets.ImageGalleryWidget.GalleryImage;

/**
 * Flickr Credits : 
 * 
 * "Family Of Goats" Kris247 : http://www.flickr.com/photos/kris247/928632149/
 * "Wooden Boat - Sailing - Port Townsend" : tiarescott : http://www.flickr.com/photos/tiarescott/193636096/
 * "Bishop's Moat" : Lawrence OP : http://www.flickr.com/photos/paullew/3622157565/
 */
public class MyApp extends Composite {
	@UiTemplate("MainAppInterface.ui.xml")
	interface MyAppUiBinder extends UiBinder<HorizontalPanel, MyApp> {}
	private static MyAppUiBinder uiBinder = GWT.create(MyAppUiBinder.class);

	private static final GalleryImage[] images = {
		new GalleryImage("http://farm4.static.flickr.com/3309/3622157565_fd079ac983.jpg","Bishop's Moat"),
		new GalleryImage("http://farm2.static.flickr.com/1257/928632149_71d88ac137.jpg","Family Of Goats"),
		new GalleryImage("http://farm1.static.flickr.com/61/193636096_1f34d7a78d.jpg","Wooden Boat - Sailing - Port Townsend")};
		
	
	@UiField ImageGalleryWidget galleryOne, galleryTwo;
	
	public MyApp() {
		initWidget(uiBinder.createAndBindUi(this));
		galleryOne.setImages(images);
		galleryTwo.setImages(images);
	}
}

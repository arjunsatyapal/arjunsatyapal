package com.maddison.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ImageGalleryWidget extends Composite {
	interface ImageGalleryUiBinder extends UiBinder<VerticalPanel, ImageGalleryWidget> {}
	private static ImageGalleryUiBinder uiBinder = GWT.create(ImageGalleryUiBinder.class);
	
	@UiField Image image;
	@UiField Label caption, title;
	@UiField Button btnNext, btnPrev; 
	
	private GalleryImage[] images;
	private int currentImageIndex = 0;
	
	public ImageGalleryWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setTitle(String value) {
		title.setText(value);
	}
	
	public void setImages(GalleryImage[] images) {
		this.images = images;
		currentImageIndex = 0;
		displayImage();
	}
	
	@UiHandler("btnPrev")
	public void handlePrev(ClickEvent event) {
		if (currentImageIndex != 0) {
			currentImageIndex--;
			displayImage();
		}
	}

	@UiHandler("btnNext")
	public void handleNext(ClickEvent event) {
		if (currentImageIndex != images.length -1) {
			currentImageIndex++;
			displayImage();
		} 
	}	
	
	private void displayImage() {
		image.setUrl(images[currentImageIndex].getUrl());
		image.setTitle(images[currentImageIndex].getCaption());
		caption.setText(images[currentImageIndex].getCaption());
	}
	
	public static class GalleryImage {
		private final String caption;
		private final String url;
		
		public GalleryImage(String url, String caption) {
			this.caption = caption;
			this.url = url;
		}
		
		public String getUrl() {
			return url;
		}
		
		public String getCaption() {
			return caption;
		}
	}
}

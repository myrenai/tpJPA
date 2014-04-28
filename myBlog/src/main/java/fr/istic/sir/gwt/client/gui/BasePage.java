package fr.istic.sir.gwt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.istic.sir.gwt.client.service.BlogServiceClientImpl;

public class BasePage extends Composite{
	
	protected MainView mainview;
	protected BlogServiceClientImpl serviceImpl;
	protected VerticalPanel vPanel = new VerticalPanel();
	
	public BasePage(MainView mainview){
		this.initWidget(vPanel);
		this.mainview = mainview;
		this.serviceImpl = mainview.getListener();
	}
	
	public void refresh(){ }
	
}

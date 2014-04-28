package fr.istic.sir.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import fr.istic.sir.gwt.client.service.BlogServiceClientImpl;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyBlog implements EntryPoint {

	public void onModuleLoad() {
		
		BlogServiceClientImpl clientImpl = new BlogServiceClientImpl(GWT.getModuleBaseURL() + "blogservice");
		RootPanel.get().add(clientImpl.getMainGUI());
	}
}

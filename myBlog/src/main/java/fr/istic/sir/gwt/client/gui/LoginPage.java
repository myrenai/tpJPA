package fr.istic.sir.gwt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginPage extends BasePage{
	
	private TextBox userId = new TextBox();
	private TextBox pw = new TextBox();
	private Button signUpBtn = new Button("Sign up");
	private Button loginBtn = new Button("Log in");
	
	public LoginPage(MainView mainview){
		super(mainview);
		
		this.loginBtn.addClickHandler(new LoginClickHandler());
		this.signUpBtn.addClickHandler(new SignUpClickHandler());
		
		this.vPanel.add(signUpBtn);
		this.vPanel.setCellHorizontalAlignment(signUpBtn, HasHorizontalAlignment.ALIGN_CENTER);
		
		this.vPanel.add(onInitialize());
	}
	
	private class SignUpClickHandler implements ClickHandler{
		public void onClick(ClickEvent event) {
			mainview.openSignUpPage();
		}
	}
	
	private class LoginClickHandler implements ClickHandler{
		public void onClick(ClickEvent event) {
			String uid = userId.getText();
			String upw = pw.getText();
			serviceImpl.logIn(uid, upw);
		}
	}
	
	/**
	 * Initialize this example.
	 */
	public Widget onInitialize() {
		// Create a table to layout the form options
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		// Add a title to the form
		layout.setHTML(0, 0, "Log in");
		cellFormatter.setColSpan(0, 0, 2);

		// Add some standard form options
		layout.setHTML(1, 0, "User ID:");
		layout.setWidget(1, 1, userId);
		
		layout.setHTML(2, 0, "Password:");
		layout.setWidget(2, 1, pw);
		
		cellFormatter.setColSpan(5, 0, 2);
		layout.setWidget(5, 0, loginBtn);
		cellFormatter.setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);

		// Wrap the content in a DecoratorPanel
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(layout);
		return decPanel;
	}

}

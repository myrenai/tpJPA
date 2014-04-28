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

public class SignUpPage extends BasePage{
	
	private TextBox userId = new TextBox();
	private TextBox pw = new TextBox();
	private TextBox firstName = new TextBox();
	private TextBox lastName = new TextBox();
	private Button signUpBtn = new Button("Sign up");
	private Button loginBtn = new Button("Log in");
	
	public SignUpPage(MainView mainview){
		super(mainview);
		
		this.loginBtn.addClickHandler(new LoginClickHandler());
		this.signUpBtn.addClickHandler(new SignUpClickHandler());
		
		this.vPanel.add(loginBtn);
		this.vPanel.setCellHorizontalAlignment(loginBtn, HasHorizontalAlignment.ALIGN_CENTER);
		
		this.vPanel.add(onInitialize());
	}
	
	private class SignUpClickHandler implements ClickHandler{
		public void onClick(ClickEvent event) {
			serviceImpl.signUp(userId.getText(), pw.getText(), firstName.getText(), lastName.getText());
		}
	}
	
	private class LoginClickHandler implements ClickHandler{
		public void onClick(ClickEvent event) {
			mainview.openLoginPage();
		}
	}
	
	/**
	 * Initialize Signup table.
	 */
	private Widget onInitialize() {
		// Create a table to layout the form options
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		// Add a title to the form
		layout.setHTML(0, 0, "Sign Up");
		cellFormatter.setColSpan(0, 0, 2);

		// Add some standard form options
		layout.setHTML(1, 0, "User ID:");
		layout.setWidget(1, 1, userId);
		
		layout.setHTML(2, 0, "Password:");
		layout.setWidget(2, 1, pw);
		
		layout.setHTML(3, 0, "First Name:");
		layout.setWidget(3, 1, firstName);
		
		layout.setHTML(4, 0, "Last Name:");
		layout.setWidget(4, 1, lastName);
		
		cellFormatter.setColSpan(5, 0, 2);
		layout.setWidget(5, 0, signUpBtn);		
		cellFormatter.setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);

		// Wrap the content in a DecoratorPanel
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(layout);
		return decPanel;
	}
}

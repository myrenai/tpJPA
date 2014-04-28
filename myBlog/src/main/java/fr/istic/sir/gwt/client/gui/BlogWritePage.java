package fr.istic.sir.gwt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BlogWritePage extends BasePage{
	
	private TextBox title;
	private TextArea content;

	public BlogWritePage(MainView mainview) {
		super(mainview);
		
		this.vPanel.add(onInitialize());
		
	}
	
	public Widget onInitialize() {
		
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setWidth("800px");
		
		title = new TextBox();
		title.setWidth("800px");
		vPanel.add(title);
		
		// Add a text area
		content = new TextArea();
		content.ensureDebugId("cwBasicText-textarea");
		content.setHeight("450px");
		content.setWidth("800px");
		content.setVisibleLines(5);
	    vPanel.add(createTextExample(content));
	    
	    Button witeBlogBtn = new Button("Save");
	    witeBlogBtn.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				String t = title.getText();
				String c = content.getText();
				
				serviceImpl.createBlog(t, c, mainview.getBlogUser(), mainview.getCurrentCategory());
			}
	    	
	    });
	    vPanel.add(witeBlogBtn);
	    this.vPanel.setCellHorizontalAlignment(witeBlogBtn, HasHorizontalAlignment.ALIGN_CENTER);

	    // Return the panel
	    return vPanel;
	}
	
	private HorizontalPanel createTextExample(final TextBoxBase textBox) {
		// Add the text box and label to a panel
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setSpacing(4);
		hPanel.add(textBox);

		// Return the panel
		return hPanel;
	}

}

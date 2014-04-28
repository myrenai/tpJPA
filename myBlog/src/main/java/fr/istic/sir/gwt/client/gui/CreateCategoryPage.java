package fr.istic.sir.gwt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextBox;

public class CreateCategoryPage extends BasePage{

	public CreateCategoryPage(MainView mainview) {
		super(mainview);
		
		final TextBox category = new TextBox();
		this.vPanel.add(category);
		this.vPanel.setCellHorizontalAlignment(category, HasHorizontalAlignment.ALIGN_CENTER);
		
		Button createCategoryBtn = new Button("Create a Category");
		createCategoryBtn.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				serviceImpl.createCategory(category.getText());
			}
		});
		
		this.vPanel.add(createCategoryBtn);
		this.vPanel.setCellHorizontalAlignment(createCategoryBtn, HasHorizontalAlignment.ALIGN_CENTER);
	}

}

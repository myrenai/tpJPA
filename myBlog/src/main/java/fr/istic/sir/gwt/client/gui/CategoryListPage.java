package fr.istic.sir.gwt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.istic.sir.gwt.client.model.BlogCategory;

public class CategoryListPage extends BasePage{
	private ToggleButton currentCategoryBtn;
	private DockPanel dock;
	public CategoryListPage(MainView mainview) {
		super(mainview);
		this.vPanel.add(getCategories());
	}
	
	public CategoryListPage(MainView mainview, DockPanel dock) {
		super(mainview);
		this.dock = dock;
		this.vPanel.add(getCategories());
	}
	
	private Widget getCategories(){
		
		VerticalPanel vPanel = new VerticalPanel();
		
		vPanel.add(new HTML("Categories"));
		
	    for (final BlogCategory curCategory : mainview.getCategories()) {
	    	
	    	final ToggleButton categoryBtn = new ToggleButton(curCategory.getCategory());
	    	categoryBtn.addClickHandler(new ClickHandler(){

				public void onClick(ClickEvent event) {
					mainview.setCurrentCategory(curCategory);
					currentCategoryBtn.setDown(false);
					currentCategoryBtn = categoryBtn;
					
					getBlogList();
					
				}
	    	});
	    	
	    	if(curCategory.getCategory().equals(mainview.getCurrentCategory().getCategory())){
	    		categoryBtn.setDown(true);
	    		currentCategoryBtn = categoryBtn;
	    	}
	    	vPanel.add(categoryBtn);
	    }
	    
	    vPanel.add(new CreateCategoryPage(mainview));
		return vPanel;
	}
	
	private void getBlogList(){
		serviceImpl.blogList(mainview.getCurrentCategory().getCategory());
	}

}

package fr.istic.sir.gwt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import fr.istic.sir.gwt.client.model.MyBlog;

public class BlogListPage extends BasePage {
	public BlogListPage(MainView mainview) {
		
		super(mainview);
		getBlogList();
	}
	
	public void getBlogList(){
		vPanel.clear();
		Label title = new Label(mainview.getCurrentCategory().getCategory() + " - List");
		title.setWidth("800px");
		
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(title);
		
		Button createBlog = new Button("Create Blog");
		createBlog.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				openBlogWritePage();
			}
			
		});
		
		hPanel.add(createBlog);
		vPanel.add(hPanel);
		
		if(mainview.getBlogList() != null)
		for(MyBlog b : mainview.getBlogList()){
			Label lbBlog = new Label(b.getTitle());
			vPanel.add(lbBlog);
		}
	}
	
	public void refresh(){ 
		getBlogList();
	}
	
	private void openBlogWritePage(){
		this.vPanel.clear();
		Label title = new Label(mainview.getCurrentCategory().getCategory() + " - Blog");
		title.setWidth("800px");
		this.vPanel.add(title);
		this.vPanel.add(new BlogWritePage(mainview));
		
	}
}

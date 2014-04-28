package fr.istic.sir.gwt.client.gui;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;

public class BlogFrame extends BasePage{
	
	private DockPanel dock = new DockPanel();
	private CategoryListPage clp;
	private BlogListPage blp;
	
	public BlogFrame(MainView mainview) {
		super(mainview);
		
		clp = new CategoryListPage(mainview, dock);
		blp = new BlogListPage(mainview);

		// Create a Dock Panel
		dock.setStyleName("cw-DockPanel");
		dock.setSpacing(4);
		dock.setHorizontalAlignment(DockPanel.ALIGN_CENTER);

		// Add text all around
		dock.add(new HTML(":: " + mainview.getBlogUser().getFirstName() + "' Blog ::"), DockPanel.NORTH);
		dock.add(clp, DockPanel.WEST);
		dock.add(blp, DockPanel.CENTER);
		
		// Return the content
		dock.ensureDebugId("cwDockPanel");
		
		this.vPanel.add(dock);
	}
	
	public void refresh(){ 
		blp.refresh();
	}
}

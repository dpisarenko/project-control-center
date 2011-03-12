package at.silverstrike.pcc.impl.centraleditingpanel;

import com.vaadin.ui.Table;

public class TempTableObjectModel{
	private static final String[] depend1 = new String[] { "1.1", "Project 1", "Task 1" };
    private static final String[] depend2 = new String[] { "2.1", "Project 4", "Task 5" };
	
	public static Table setTable(Table table){
		if (table == null)
			table = new Table();
		
		table.addContainerProperty("¹", String.class, null);
		table.addContainerProperty("Project", String.class, null);
		table.addContainerProperty("Name", String.class, null);
        
		table.addItem(depend1, null);
		table.addItem(depend2, null);
       
			
		return table;
	}
	
}
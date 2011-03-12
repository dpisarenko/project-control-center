package at.silverstrike.pcc.impl.centraleditingpanel;

import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;

class TempTreeObjectModel {

	private static final String[] filterImpactTypes = { "Passive filters", "Active filters" };
    private static final String[] filterTypes = { "Sums", "Parities", "Numbers" };
    
	 public static HierarchicalContainer getFilterHierarchicalContainer(){
	        HierarchicalContainer getFilterHierarchicalContainer = new HierarchicalContainer();
	         getFilterHierarchicalContainer.addContainerProperty("FILTER_NAME", String.class, null);
	       
	        Item item = null;
	        Integer itemId = 0;
	        for(int i = 0; i < filterImpactTypes.length; i++ ) {            
	            // Add new item
	            item = getFilterHierarchicalContainer.addItem(itemId);
	            // Add name property for item
	         //item.getItemProperty(FILTER_NAME).setValue(filterImpactTypes[ i ]);
	           // Allow children
	            getFilterHierarchicalContainer.setChildrenAllowed(itemId, true);
	            itemId++;
	        }
	       
	        itemId = 100;
	        for(int i = 0; i < filterTypes.length; i++ ){
	            // Add new item
	            item = getFilterHierarchicalContainer.addItem(itemId);
	            // Add name property for item
	            //item.getItemProperty(FILTER_NAME).setValue(filterTypes[ i ]);
	            // Set parent
	            getFilterHierarchicalContainer.setParent(itemId, 0);
	            // Allow children
	            getFilterHierarchicalContainer.setChildrenAllowed(itemId, false);
	            itemId++;
	        }
	       
	       
	        return getFilterHierarchicalContainer;
	    }
}
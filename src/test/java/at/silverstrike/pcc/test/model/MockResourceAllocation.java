package at.silverstrike.pcc.test.model;

import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.ResourceAllocation;

class MockResourceAllocation implements ResourceAllocation {
	
	private Long id;
	private Resource resource;

	public Long getId() {
		return this.id;
	}

	public void setResource(final Resource aResource) {
		this.resource = aResource;
	}

	public Resource getResource() {
		return this.resource;
	}

}

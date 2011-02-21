/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.persistence;

import org.apache.commons.lang.builder.ToStringBuilder;

import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.ResourceAllocation;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultResourceAllocation implements ResourceAllocation {
    private Resource resource;
    private Long id;

    public Resource getResource() {
        return resource;
    }

    public void setResource(final Resource aResource) {
        this.resource = aResource;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);

        builder.append("id", this.id);
        builder.append("resource", resource);

        return builder.toString();
    }

}

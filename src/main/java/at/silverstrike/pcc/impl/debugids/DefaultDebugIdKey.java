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

package at.silverstrike.pcc.impl.debugids;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry.Module;
import at.silverstrike.pcc.api.debugids.DebugIdKey;

class DefaultDebugIdKey implements DebugIdKey {
    private static final int HASH_CODE_MULTIPLIER = 3;
    private Module module;
    private String key;

    public DefaultDebugIdKey() {
    }

    @Override
    public void setModule(final Module aModule) {
        this.module = aModule;
    }

    @Override
    public Module getModule() {
        return this.module;
    }

    @Override
    public void setKey(final String aKey) {
        this.key = aKey;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public boolean equals(final Object aObject) {
        if (aObject == null) {
            return false;
        }
        if (aObject == this) {
            return true;
        }
        if (aObject.getClass() != getClass()) {
            return false;
        }

        final DefaultDebugIdKey rhs = (DefaultDebugIdKey) aObject;
        return new EqualsBuilder().append(module, rhs.module)
                .append(key, rhs.key).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(1, HASH_CODE_MULTIPLIER).append(module)
                .append(key)
                .toHashCode();
    }
}

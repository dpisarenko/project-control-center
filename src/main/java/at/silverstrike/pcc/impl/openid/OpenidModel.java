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

package at.silverstrike.pcc.impl.openid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

class OpenidModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String openId;
    private Map<String, String> attributes;

    public OpenidModel() {
        attributes = new HashMap<String, String>();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(final String aOpenId) {
        this.openId = aOpenId;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(final Map<String, String> aAttributes) {
        this.attributes = aAttributes;
    }

    @Override
    public String toString() {
        return "OpenidModel [openId=" + openId + ", attributes=[\n"
                + TextUtils.mapToString(attributes) + "\n]\n]";
    }
}

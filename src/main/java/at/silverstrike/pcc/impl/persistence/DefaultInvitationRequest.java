/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.persistence;

import java.util.Date;

import at.silverstrike.pcc.api.model.InvitationRequest;
import at.silverstrike.pcc.api.model.InvitationRequestStatus;
import at.silverstrike.pcc.api.openid.SupportedOpenIdProvider;

/**
 * @author DP118M
 * 
 */
class DefaultInvitationRequest implements InvitationRequest {
    private Long id;
    private Date submissionDateTime;
    private SupportedOpenIdProvider openIdProvider;
    private String enteredId;
    private InvitationRequestStatus status;
    private String identity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSubmissionDateTime() {
        return submissionDateTime;
    }

    public void setSubmissionDateTime(Date submissionDateTime) {
        this.submissionDateTime = submissionDateTime;
    }

    public SupportedOpenIdProvider getOpenIdProvider() {
        return openIdProvider;
    }

    public void setOpenIdProvider(SupportedOpenIdProvider openIdProvider) {
        this.openIdProvider = openIdProvider;
    }

    public String getEnteredId() {
        return enteredId;
    }

    public void setEnteredId(String enteredId) {
        this.enteredId = enteredId;
    }

    public InvitationRequestStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationRequestStatus status) {
        this.status = status;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}

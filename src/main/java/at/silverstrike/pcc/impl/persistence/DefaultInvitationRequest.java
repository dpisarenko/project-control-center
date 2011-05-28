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

/**
 * @author DP118M
 * 
 */
class DefaultInvitationRequest implements InvitationRequest {
    private Long id;
    private Date submissionDateTime;
    private InvitationRequestStatus status;
    private String email;
    private String password;
    
    public Long getId() {
        return id;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

    public Date getSubmissionDateTime() {
        return submissionDateTime;
    }

    public void setSubmissionDateTime(final Date aSubmissionDateTime) {
        this.submissionDateTime = aSubmissionDateTime;
    }

    @Override
    public void setEmail(final String aEmail) {
        this.email = aEmail;
    }

    @Override
    public String getEmail() {
        return this.email;
    }
    
    @Override
    public void setPassword(final String aPassword) {
        this.password = aPassword;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public void setStatus(final InvitationRequestStatus aStatus) {
        this.status = aStatus;
    }

    @Override
    public InvitationRequestStatus getStatus() {
        return this.status;
    }
}

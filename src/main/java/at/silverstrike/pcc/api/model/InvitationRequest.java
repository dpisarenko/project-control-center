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

package at.silverstrike.pcc.api.model;

import java.util.Date;
import ru.altruix.commons.api.conventions.UniquelyIdentifiableObject;

/**
 * @author DP118M
 *
 */
public interface InvitationRequest extends UniquelyIdentifiableObject {
    void setSubmissionDateTime(final Date aDate);
    Date getSubmissionDateTime();
    
    void setStatus(final InvitationRequestStatus aStatus);
    InvitationRequestStatus getStatus();
    
    void setEmail(final String aEmail);
    String getEmail();
    
    void setPassword(final String aPassword);
    String getPassword();
}

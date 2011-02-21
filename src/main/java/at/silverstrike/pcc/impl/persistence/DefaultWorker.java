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

import at.silverstrike.pcc.api.model.Worker;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultWorker extends DefaultResource implements Worker {
    private String firstName;
    private String middleName;
    private String surname;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String aFirstName) {
        this.firstName = aFirstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String aMiddleName) {
        this.middleName = aMiddleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String aSurName) {
        this.surname = aSurName;
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);

        builder.append("firstName", this.firstName);
        builder.append("middleName", this.middleName);
        builder.append("surname", this.surname);
        builder.append("abbreviation", this.getAbbreviation());
        builder.append("id", this.getId());

        return builder.toString();
    }

}

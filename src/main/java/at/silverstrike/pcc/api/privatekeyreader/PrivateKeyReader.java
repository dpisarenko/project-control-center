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

package at.silverstrike.pcc.api.privatekeyreader;

import java.io.InputStream;
import java.security.PrivateKey;

import ru.altruix.commons.api.conventions.SingleActivityModule;

/**
 * @author DP118M
 * 
 */
public interface PrivateKeyReader extends SingleActivityModule {
    void setInputStream(final InputStream aInputStream);

    PrivateKey getPrivateKey();
}

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

package at.silverstrike.pcc.impl.privatekeyreader;

import at.silverstrike.pcc.api.privatekeyreader.PrivateKeyReader;
import at.silverstrike.pcc.api.privatekeyreader.PrivateKeyReaderFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultPrivateKeyReaderFactory implements
        PrivateKeyReaderFactory {

    @Override
    public PrivateKeyReader create() {
        return new DefaultPrivateKeyReader();
    }

}

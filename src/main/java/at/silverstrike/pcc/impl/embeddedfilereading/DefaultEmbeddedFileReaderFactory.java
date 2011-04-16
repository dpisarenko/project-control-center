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

package at.silverstrike.pcc.impl.embeddedfilereading;

import at.silverstrike.pcc.api.embeddedfilereading.EmbeddedFileReader;
import at.silverstrike.pcc.api.embeddedfilereading.EmbeddedFileReaderFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultEmbeddedFileReaderFactory implements
        EmbeddedFileReaderFactory {
    /**
     * @see ru.altruix.commons.api.conventions.Factory#create()
     */
    @Override
    public final EmbeddedFileReader create() {
        return new DefaultEmbeddedFileReader();
    }

}

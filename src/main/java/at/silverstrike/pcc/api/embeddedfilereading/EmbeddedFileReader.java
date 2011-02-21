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

package at.silverstrike.pcc.api.embeddedfilereading;

import at.silverstrike.pcc.api.conventions.PccException;

/**
 * @author Dmitri Pisarenko
 * 
 */
public interface EmbeddedFileReader {
	String readEmbeddedFile(final String aFileName) throws PccException;
}

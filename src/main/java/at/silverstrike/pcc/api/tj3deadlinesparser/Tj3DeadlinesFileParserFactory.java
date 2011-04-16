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

package at.silverstrike.pcc.api.tj3deadlinesparser;

import ru.altruix.commons.api.conventions.Factory;
import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;

public interface Tj3DeadlinesFileParserFactory extends
	Factory<Tj3DeadlinesFileParser>, ModuleWithInjectableDependencies {

}

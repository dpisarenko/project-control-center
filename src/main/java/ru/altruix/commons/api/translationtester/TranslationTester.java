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

package ru.altruix.commons.api.translationtester;

import java.io.File;
import java.util.List;

import ru.altruix.commons.api.conventions.SingleActivityModule;

/**
 * @author DP118M
 *
 */
public interface TranslationTester extends SingleActivityModule {
    void setTranslationDirectories(final List<File> aDirectories);
    List<ProblemTuple> getTranslationProblems();
}

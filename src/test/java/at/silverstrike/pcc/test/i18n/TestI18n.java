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

package at.silverstrike.pcc.test.i18n;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;
import ru.altruix.commons.api.translationtester.ProblemTuple;
import ru.altruix.commons.api.translationtester.TranslationTester;
import ru.altruix.commons.api.translationtester.TranslationTesterFactory;
import ru.altruix.commons.impl.translationtester.DefaultTranslationTesterFactory;

/**
 * @author DP118M
 * 
 */
public class TestI18n {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(TestI18n.class);

    @Test
    public final void testTranslations() {
        final TranslationTesterFactory factory = new DefaultTranslationTesterFactory();
        final TranslationTester translationTester = factory.create();
        
        translationTester.setTranslationDirectories(getTranslationDirectories());
        try {
            translationTester.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
        
        for (final ProblemTuple tuple : translationTester.getTranslationProblems()) {
            Assert.assertEquals(
                    "Problems in translation files for culture '${culture}'"
                            .replace("${culture}", tuple.getCulture()), 0,
                    tuple.getProblematicKeys().size());
        }
    }


    private List<File> getTranslationDirectories() {
        final List<File> returnValue = new LinkedList<File>();

        returnValue.add(new File("src/main/webapp/VAADIN/themes/pcc/i18n/en"));
        returnValue.add(new File("src/main/webapp/VAADIN/themes/pcc/i18n/ru"));

        for (final File curFile : returnValue) {
            Assert.assertTrue(
                    "File not readable and/or non-existent and/or not a directory: "
                            + curFile.getAbsolutePath(),
                    curFile.exists() && curFile.canRead()
                            && curFile.isDirectory());
        }

        return returnValue;
    }
}

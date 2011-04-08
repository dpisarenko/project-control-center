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

/**
 * @author DP118M
 * 
 */
public class TestI18n {
    @Test
    public void testTranslations() {
        final List<File> translationDirectories = getTranslationDirectories();
        final List<KeyAndLanguageTuple> klt =
                 getKeyAndLanguageTuples(translationDirectories);
        final List<ProblemTuple> pt = getProblemTuples(klt);

        for (final ProblemTuple tuple : pt) {
            Assert.assertEquals(0, tuple.getProblematicKeys().size());
        }
    }

    private List<ProblemTuple> getProblemTuples(List<KeyAndLanguageTuple> klt) {
        // TODO Auto-generated method stub
        return null;
    }

    private List<KeyAndLanguageTuple> getKeyAndLanguageTuples(
            List<File> translationDirectories) {
        // TODO Auto-generated method stub
        return null;
    }

    private List<File> getTranslationDirectories() {
        final List<File> returnValue = new LinkedList<File>();

        returnValue.add(new File("webapp/VAADIN/themes/pcc/i18n/en"));
        returnValue.add(new File("webapp/VAADIN/themes/pcc/i18n/en"));

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

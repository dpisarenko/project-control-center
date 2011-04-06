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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * @author DP118M
 * 
 */
final class I18nTest {
    // @Test
    public void test01() {
        Properties myFile = new Properties();
        String dirName = "C:\\i18n\\ru\\";
        String fullName = "";
        String list[] = new File(dirName).list();
        for (String fileName : list) {
            try {
                fullName = dirName + fileName;
                FileInputStream fis = new FileInputStream(fullName);
                try {
                    myFile.load(fis);
                } catch (IOException ie) {
                    Assert.fail(ie.getMessage());
                } finally {
                    IOUtils.closeQuietly(fis);
                }
            } catch (FileNotFoundException fnfe) {
                Assert.fail(fnfe.getMessage());
            }

            for (final Object obj : myFile.keySet()) {
                final String key = (String) obj;
                final String value = (String) myFile.get(key);
                if (StringUtils.isBlank(value)) {
                    // Assert.assertTrue("Key is localized for file: " +
                    // fullName,StringUtils.isBlank(value));
                    Assert.assertFalse("Key " + key + " is not localized by "
                            + value + " for file: " + fullName,
                            StringUtils.isBlank(value));
                }
            }
        }
    }

    // @Test
    public void correctTest() {
        List<File> translationDirectories = getTranslationDirectories();
        List<KeysAndLanguageTuples> klt =
                getKeyAndLanguageTuples(translationDirectories);
        List<ProblemTuples> pt = getProblemTuples(klt);

        for (ProblemTuples tuple : pt) {
            Assert.assertEquals(0, tuple.getProblematicKeys().size());
        }
    }

    private List<ProblemTuples>
            getProblemTuples(final List<KeysAndLanguageTuples> klt) {
        // TODO Auto-generated method stub
        return null;
    }

    private List<KeysAndLanguageTuples> getKeyAndLanguageTuples(
            final List<File> aTranslationDirectories) {
        final List<KeysAndLanguageTuples> returnValue = new LinkedList<KeysAndLanguageTuples>();
        for (final File localeDirectory : aTranslationDirectories)
        {
            final File files[] = localeDirectory.listFiles();
            for (final File translationFile : files)
            {
                final Properties keysAndValues = getProperties(translationFile);
                
                
            }
        }
        return null;
    }

    private Properties getProperties(File translationFile) {
        // TODO Auto-generated method stub
        return null;
    }

    private List<File> getTranslationDirectories() {
        final List<File> returnValue = new LinkedList<File>();
        
        returnValue.add(new File("webapp/VAADIN/themes/pcc/i18n/en"));
        returnValue.add(new File("webapp/VAADIN/themes/pcc/i18n/ru"));
        
        return returnValue;
    }
}

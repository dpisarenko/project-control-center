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

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.util.Base64;

import ru.altruix.commons.api.di.PccException;
import at.silverstrike.pcc.api.privatekeyreader.PrivateKeyReader;

/**
 * @author DP118M
 * 
 */
class DefaultPrivateKeyReader implements PrivateKeyReader {
    private InputStream inputStream;
    private PrivateKey privateKey;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultPrivateKeyReader.class);

    /**
     * @see ru.altruix.commons.api.conventions.SingleActivityModule#run()
     */
    @Override
    public void run() throws PccException {
        try {
            byte[] privKeyBytes = null;
            try {
                privKeyBytes = IOUtils.toByteArray(inputStream);
            } catch (final IOException exception) {
                LOGGER.error("", exception);
                IOUtils.closeQuietly(inputStream);
            }

            LOGGER.debug("privKeyBytes: {}", privKeyBytes);

            String BEGIN = "-----BEGIN RSA PRIVATE KEY-----";
            String END = "-----END RSA PRIVATE KEY-----";
            String str = new String(privKeyBytes);
            if (str.contains(BEGIN) && str.contains(END)) {
                str = str.substring(BEGIN.length(), str.lastIndexOf(END));
            }

            KeyFactory fac = KeyFactory.getInstance("RSA");
            EncodedKeySpec privKeySpec =
                    new PKCS8EncodedKeySpec(Base64.decode(str.getBytes()));

            this.privateKey = fac.generatePrivate(privKeySpec);
        } catch (final NoSuchAlgorithmException exception) {
            LOGGER.error("", exception);
            throw new PccException(exception);
        } catch (final IOException exception) {
            LOGGER.error("", exception);
            throw new PccException(exception);
        } catch (final InvalidKeySpecException exception) {
            LOGGER.error("", exception);
            throw new PccException(exception);
        }
    }

    /**
     * @see at.silverstrike.pcc.api.privatekeyreader.PrivateKeyReader#setInputStream(java.io.InputStream)
     */
    @Override
    public void setInputStream(final InputStream aInputStream) {
        this.inputStream = aInputStream;
    }

    /**
     * @see at.silverstrike.pcc.api.privatekeyreader.PrivateKeyReader#getPrivateKey()
     */
    @Override
    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

}

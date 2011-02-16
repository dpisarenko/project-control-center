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

package at.silverstrike.pcc.test.export2tj3;

import at.silverstrike.pcc.api.embeddedfilereading.EmbeddedFileReader;
import at.silverstrike.pcc.api.export2tj3.TaskJuggler3Exporter;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.xmlserialization.XmlDeserializerFactory;
import at.silverstrike.pcc.impl.embeddedfilereading.DefaultEmbeddedFileReaderFactory;
import at.silverstrike.pcc.impl.export2tj3.DefaultTaskJuggler3ExporterFactory;
import at.silverstrike.pcc.impl.xmlserialization.DefaultXmlDeserializerFactory;
import at.silverstrike.pcc.test.mockpersistence.MockObjectFactory;

import com.google.inject.AbstractModule;

class MockInjectorModule extends AbstractModule {
	@Override
	protected void configure() {
		final MockObjectFactory mockObjectFactory = new MockObjectFactory();

		bind(Persistence.class).toInstance(
				mockObjectFactory.createPersistence());
		bind(EmbeddedFileReader.class).toInstance(
				new DefaultEmbeddedFileReaderFactory().create());
		bind(TaskJuggler3Exporter.class).toInstance(
				new DefaultTaskJuggler3ExporterFactory().create());
		bind(XmlDeserializerFactory.class).toInstance(
				new DefaultXmlDeserializerFactory());
	}
}

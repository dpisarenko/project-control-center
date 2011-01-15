package at.silverstrike.pcc.impl.xmlserialization;

import at.silverstrike.pcc.api.xmlserialization.XmlDeserializer;
import at.silverstrike.pcc.api.xmlserialization.XmlDeserializerFactory;

public class DefaultXmlDeserializerFactory implements XmlDeserializerFactory {

	public final XmlDeserializer create() {
		return new DefaultXmlDeserializer();
	}

}
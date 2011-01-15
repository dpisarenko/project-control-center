package at.silverstrike.pcc.impl.xmlserialization;

import at.silverstrike.pcc.api.xmlserialization.XmlSerializer;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializerFactory;

public class DefaultXmlSerializerFactory implements XmlSerializerFactory {

	public final XmlSerializer create() {
		return new DefaultXmlSerializer();
	}

}
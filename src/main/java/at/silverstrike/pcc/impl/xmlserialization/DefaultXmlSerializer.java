package at.silverstrike.pcc.impl.xmlserialization;

import java.io.IOException;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializer;

class DefaultXmlSerializer implements XmlSerializer {
	private UserData userData;
	private OutputStream outputStream;
	
	public void run() throws PccException {
		XStream xstream = new XStream(new DomDriver());
		String xml = xstream.toXML(userData);
		try {
			outputStream.write(xml.getBytes());
		} catch (final IOException e) {
			System.out.println("Error while writting to output stream.");
			e.printStackTrace();
		}
	}

	public void setUserData(final UserData aUserData) {
		this.userData = aUserData;
	}

	public void setOutputStream(final OutputStream aOutputStream) {
		this.outputStream = aOutputStream;
	}

}
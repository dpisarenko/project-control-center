package at.silverstrike.pcc.impl.xmlserialization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.xmlserialization.XmlDeserializer;

class DefaultXmlDeserializer implements XmlDeserializer {
	private InputStream inputStream;
	private UserData userData;
	
	public void run() throws PccException {
		XStream xstream = new XStream(new DomDriver());
		
		Writer writer = new StringWriter();
		char[] buffer = new char[1024];
		try {
			Reader reader = null;
			reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			int n;
			while ((n = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}
		} catch (final Exception e) {
			System.out.println("Input stream reading error.");
		} finally {
			try {
				inputStream.close();
			} catch (final IOException e) {
				System.out.println("Error while closing input stream.");
				e.printStackTrace();
			}
		}

		String xml = writer.toString();
		userData = (UserData) xstream.fromXML(xml);
	}

	public void setInputStream(final InputStream aInputStream) {
		this.inputStream = aInputStream;
	}

	public UserData getUserData() {
		return this.userData;
	}
}

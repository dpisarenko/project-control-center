/**
 * 
 */
package at.silverstrike.pcc.api.xmlserialization;

import java.io.InputStream;

import at.silverstrike.pcc.api.model.UserData;

/**
 * @author DP118M
 *
 */
public interface XmlDeserializer {
	void setInputStream(final InputStream aInputStream);
	UserData getUserData();
}

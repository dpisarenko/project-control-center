package at.silverstrike.pcc.api.xmlserialization;

import java.io.OutputStream;

import at.silverstrike.pcc.api.conventions.SingleActivityModule;
import at.silverstrike.pcc.api.model.UserData;

public interface XmlSerializer extends SingleActivityModule {
	void setUserData(final UserData aUserData);
	void setOutputStream(final OutputStream aOutputStream);
}

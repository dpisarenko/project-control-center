package at.silverstrike.pcc.impl.openid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

class OpenidModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String openId;
	private Map<String, String> attributes;

	public OpenidModel() {
		attributes = new HashMap<String, String>();
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "OpenidModel [openId=" + openId + ", attributes=[\n"
				+ TextUtils.mapToString(attributes) + "\n]\n]";
	}
}

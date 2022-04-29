package de.jhoppmann.phclient.client.json;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * @author jhoppman
 */
public abstract class JsonRpcRequest extends JSONObject {

	/**
	 * Convenience method to assign a string value to a property.
	 *
	 * @param key   The key of the property
	 * @param value The value of the property
	 * @return The previous value of this key, or <tt>null</tt> if it didn't exist
	 */
	public JSONValue put(String key, String value) {
		return put(key, new JSONString(value));
	}

	/**
	 * Implementing subclasses should return their method to be used.
	 *
	 * @return The JSON RPC method to call
	 */
	public abstract String getMethod();

	/**
	 * @return The request's id
	 */
	public String getId() {
		return JsonRequestIdProvider.get();
	}
}

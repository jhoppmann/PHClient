package de.jhoppmann.phclient.client.json;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author jhoppman
 */
@FunctionalInterface
public interface JsonRequestCallback<T extends JavaScriptObject> {

	void onResponseReceived(T response);

	default void onErrorReceived(T response) {
		GWT.log(response.toString());
	}
}

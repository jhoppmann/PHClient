package de.jhoppmann.phclient.client.data.requests;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author jhoppman
 */
public class TimeJso extends JavaScriptObject {

	protected TimeJso() {}
	public final native int getPrintTime() /*-{return this.PRINT_TIME}-*/;
}

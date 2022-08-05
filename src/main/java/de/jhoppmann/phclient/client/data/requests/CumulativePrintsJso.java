package de.jhoppmann.phclient.client.data.requests;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author jhoppman
 */
public class CumulativePrintsJso  extends JavaScriptObject {

	protected CumulativePrintsJso() {}

	public final native int getPrints() /*-{return this.PRINTS}-*/;

	public final native int getSuccesses() /*-{return this.SUCCESSES}-*/;

	public final native int getFails() /*-{return this.FAILS}-*/;
}

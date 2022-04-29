package de.jhoppmann.phclient.client.json;

/**
 * This class generates and provides unique ids for JSONRPC requests.
 *
 * @author jhoppman
 */
public class JsonRequestIdProvider {
	private static int counter = 0;

	/**
	 * @return A unique id for a request
	 */
	public static String get() {
		return "" + counter++;
	}
}

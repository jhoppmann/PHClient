package de.jhoppmann.phclient.client.data.requests;

import de.jhoppmann.phclient.client.json.JsonRequestIdProvider;
import de.jhoppmann.phclient.client.json.JsonRpcRequest;

/**
 * @author jhoppman
 */
public class TimeRequest extends JsonRpcRequest {
	@Override
	public String getMethod() {
		return "CALCULATOR.PRINT_TIME";
	}

	public String getId() {
		return JsonRequestIdProvider.get();
	}
}

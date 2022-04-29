package de.jhoppmann.phclient.client.data.requests;

import de.jhoppmann.phclient.client.json.JsonRequestIdProvider;
import de.jhoppmann.phclient.client.json.JsonRpcRequest;

/**
 * @author jhoppman
 */
public class AllDataRequest extends JsonRpcRequest {
	@Override
	public String getMethod() {
		return "CALCULATOR.ALL_DATA";
	}

	@Override
	public String getId() {
		return JsonRequestIdProvider.get();
	}
}

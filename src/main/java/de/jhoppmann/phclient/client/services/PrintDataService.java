package de.jhoppmann.phclient.client.services;

import de.jhoppmann.phclient.client.data.requests.AllDataJso;
import de.jhoppmann.phclient.client.data.requests.AllDataRequest;
import de.jhoppmann.phclient.client.json.JsonRequestCallback;
import de.jhoppmann.phclient.client.json.JsonRpcRequestSender;

import java.util.function.Consumer;

/**
 * @author jhoppman
 */
public class PrintDataService {
	public static void getData(Consumer<AllDataJso> r) {
		AllDataRequest request = new AllDataRequest();
		JsonRpcRequestSender requestSender = new JsonRpcRequestSender();
		requestSender.sendRequest(request, r::accept);

	}

}

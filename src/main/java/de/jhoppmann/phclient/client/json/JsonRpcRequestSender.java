package de.jhoppmann.phclient.client.json;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 * @author jhoppman
 */
public class JsonRpcRequestSender {

	public <T extends JavaScriptObject> void sendRequest(JsonRpcRequest object, JsonRequestCallback<T> callback) {
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, "http://127.0.0.1:5000/jsonrpc");
		try {
			builder.setHeader("Content-Type", "application/json");
			String payload = buildPayload(object);
			builder.sendRequest(payload, getCallback(callback));
			GWT.log(payload);
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}

	private <T extends JavaScriptObject> RequestCallback getCallback(JsonRequestCallback<T> callback) {
		return new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				String jsonString = response.getText();
				GWT.log(jsonString);
				JavaScriptObject json = JsonUtils.safeEval(jsonString);
				T object = json.cast();
				callback.onResponseReceived(object);
			}

			@Override
			public void onError(Request request, Throwable exception) {

			}
		};
	}

	private String buildPayload(JsonRpcRequest object) {
		JSONObject json = new JSONObject();

		json.put("jsonrpc", new JSONString("2.0"));
		json.put("params", object);
		json.put("method", new JSONString(object.getMethod()));
		json.put("id", new JSONString(object.getId()));

		return json.toString();
	}
}

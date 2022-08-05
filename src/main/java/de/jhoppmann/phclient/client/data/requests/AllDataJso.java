package de.jhoppmann.phclient.client.data.requests;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jhoppman
 */
public class AllDataJso extends JavaScriptObject {

	protected AllDataJso() {}

	public final native TimeJso getPrintTime() /*-{return this.result.PRINT_TIME}-*/;

	public final native CumulativePrintsJso getCumulativeData() /*-{return this.result.CUMULATIVE_PRINTS}-*/;

	private native JavaScriptObject getPrintTimeByPrinter() /*-{return this.result.PRINT_TIME_BY_PRINTER}-*/;

	private native JavaScriptObject dataByMonth() /*-{return this.result.PRINTS_BY_MONTH_AND_PRINTER}-*/;

	public final Map<String, Long> getPrinterTimes() {
		JSONObject times = new JSONObject(getPrintTimeByPrinter());
		Map<String, Long> result = new HashMap<>();
		for (String key : times.keySet()) {
			result.put(key, Long.valueOf(times.get(key).toString()));
		}
		return result;
	}

	public final Map<String, List<PrintData>> getByMonth() {
		Map<String, List<PrintData>> result = new HashMap<>();
		JSONObject data = new JSONObject(dataByMonth());
		for(String month : data.keySet()) {
			List<PrintData> printDataValues = new ArrayList<>();
			JSONObject monthlyData = data.get(month).isObject();
			for (String printer : monthlyData.keySet()) {
				JSONObject object = monthlyData.get(printer).isObject();
				PrintData printData = new PrintData();
				printData.printer = printer;
				printData.prints = Integer.parseInt(object.get("PRINTS").isNumber().toString());
				printData.success = Integer.parseInt(object.get("SUCCESS").isNumber().toString());
				printData.fails = Integer.parseInt(object.get("FAIL").isNumber().toString());
				printData.time = Long.parseLong(object.get("TIME").isNumber().toString());
				printDataValues.add(printData);
			}
			result.put(month, printDataValues);
		}
		return result;
	}

	public static class PrintData {
		public String printer;
		public int prints;
		public long time;
		public int success;
		public int fails;

		@Override
		public String toString() {
			return "PrintData{" +
					"printer='" + printer + '\'' +
					", prints=" + prints +
					", time=" + time +
					", success=" + success +
					", fails=" + fails +
					'}';
		}
	}
}

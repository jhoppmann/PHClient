package de.jhoppmann.phclient.client.view.charts;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.*;
import de.jhoppmann.phclient.client.data.requests.AllDataJso;
import de.jhoppmann.phclient.client.services.ConcurrentChartLoader;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jhoppman
 */
public class ChartView extends SimplePanel {

	private final ChartActivity presenter;
	private final VerticalPanel content = new VerticalPanel();
	private final AllDataJso allData;

	public ChartView(ChartActivity presenter, AllDataJso allData) {
		this.presenter = presenter;
		setWidget(content);
		this.allData = allData;
		content.add(new Label("Cumulative print time: " + allData.getPrintTime().getPrintTime()));
		buildPrintTimePie();
		buildPrintsByMonth();
	}

	private void buildPrintTimePie() {
		ConcurrentChartLoader chartLoader = new ConcurrentChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(() -> {
			Map<String, Long> printTimes = allData.getPrinterTimes();

			PieChart pieChart = new PieChart();
			DataTable data = DataTable.create();
			data.addColumn(ColumnType.STRING, "Printer");
			data.addColumn(ColumnType.NUMBER, "Print Time");
			for (String printer : printTimes.keySet()){
				int printTime = printTimes.get(printer).intValue();
				data.addRow(printer, printTime);
			}

			pieChart.setWidth("400px");
			pieChart.setHeight("400px");
			pieChart.draw(data);
			PieChartOptions options = PieChartOptions.create();
			options.setEnableInteractivity(true);
			content.add(pieChart);
		});
	}

	public void buildPrintsByMonth() {
		ConcurrentChartLoader chartLoader = new ConcurrentChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(() -> {

			Map<String, List<AllDataJso.PrintData>> byMonth = allData.getByMonth();
			ColumnChart chart = new ColumnChart();
			DataTable data = DataTable.create();
			data.addColumn(ColumnType.STRING, "Month");
			List<String> printers = findPrinters();
			for (String printer : printers) {
				data.addColumn(ColumnType.NUMBER, printer);
			}
			data.addRows(byMonth.size());
			int row = 0;
			for (String month : byMonth.keySet()) {
				data.setValue(row, 0, month);
				List<AllDataJso.PrintData> printData = byMonth.get(month);
				for (AllDataJso.PrintData printDataSet : printData) {
					data.setValue(row, printers.indexOf((printDataSet.printer)) + 1, printDataSet.prints);
				}
				row++;
			}

			chart.setWidth("1600px");
			chart.setHeight("700px");
			ColumnChartOptions options = ColumnChartOptions.create();
			options.setTitle("Prints by Month");
			chart.draw(data, options);
			content.add(chart);
		});
	}

	private List<String> findPrinters() {
		Set<String> result = new HashSet<>();
		Map<String, List<AllDataJso.PrintData>> byMonth = allData.getByMonth();
		for (String month : byMonth.keySet()) {
			for(AllDataJso.PrintData pd : byMonth.get(month)) {
				result.add(pd.printer);
			}
		}

		return result.stream().sorted().collect(Collectors.toList());
	}
}

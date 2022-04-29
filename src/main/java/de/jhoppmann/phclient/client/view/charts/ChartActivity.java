package de.jhoppmann.phclient.client.view.charts;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import de.jhoppmann.phclient.client.data.requests.AllDataJso;
import de.jhoppmann.phclient.client.data.requests.TimeJso;
import de.jhoppmann.phclient.client.data.requests.TimeRequest;
import de.jhoppmann.phclient.client.json.JsonRequestCallback;
import de.jhoppmann.phclient.client.json.JsonRpcRequestSender;
import de.jhoppmann.phclient.client.services.PrintDataService;

import java.util.function.Consumer;

/**
 * @author jhoppman
 */
public class ChartActivity extends AbstractActivity {
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Consumer<AllDataJso> r = allDataJso -> {panel.setWidget(new ChartView(this, allDataJso));};
		PrintDataService.getData(r);
	}
}

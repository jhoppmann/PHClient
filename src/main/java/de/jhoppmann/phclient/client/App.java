package de.jhoppmann.phclient.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import de.jhoppmann.phclient.client.navigation.ActivityHistoryMapper;
import de.jhoppmann.phclient.client.navigation.ClientFactory;
import de.jhoppmann.phclient.client.navigation.PlaceId;
import de.jhoppmann.phclient.client.navigation.SimplePlace;
import de.jhoppmann.phclient.client.view.MainPanel;

import java.io.Console;

public class App implements EntryPoint {

  /**
   * This is the entry point method.
   */
  @Override
  public void onModuleLoad() {
    Panel appSpace = RootPanel.get("contentPanel");
    ClientFactory clientFactory = GWT.create(ClientFactory.class);
    EventBus eventBus = clientFactory.getEventBus();
    PlaceController placeController = clientFactory.getPlaceController();

    MainPanel body = new MainPanel();
    appSpace.add(body);

    ActivityHistoryMapper activityMapper = new ActivityHistoryMapper();
    ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
    activityManager.setDisplay(body);

    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(activityMapper);
    historyHandler.register(placeController, eventBus, new SimplePlace(PlaceId.MAIN_SCREEN));
    historyHandler.handleCurrentHistory();
  }
}


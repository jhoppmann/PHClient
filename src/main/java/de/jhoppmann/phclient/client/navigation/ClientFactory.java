package de.jhoppmann.phclient.client.navigation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * @author jhoppman
 */
public class ClientFactory {
	private static final ClientFactory INSTANCE = GWT.create(ClientFactory.class);
	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(eventBus);

	public static ClientFactory getInstance() {
		return INSTANCE;
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public PlaceController getPlaceController() {
		return placeController;
	}
}

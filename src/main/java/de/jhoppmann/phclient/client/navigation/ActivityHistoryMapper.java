package de.jhoppmann.phclient.client.navigation;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import de.jhoppmann.phclient.client.view.charts.ChartActivity;

/**
 * @author jhoppman
 */
public class ActivityHistoryMapper implements PlaceHistoryMapper, ActivityMapper {
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof SimplePlace) {
			SimplePlace simplePlace = (SimplePlace) place;

			Activity activity = new ChartActivity();
			switch (simplePlace.getId()) {

				case MAIN_SCREEN:
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + simplePlace.getId());
			}
			return activity;
		} else {
			return null;
		}
	}

	@Override
	public Place getPlace(String token) {
		return new SimplePlace(PlaceId.valueOf(token));
	}

	@Override
	public String getToken(Place place) {
		if (place instanceof SimplePlace) {
			SimplePlace simplePlace = (SimplePlace) place;
			return simplePlace.getId().toString();
		} else {
			return null;
		}
	}
}

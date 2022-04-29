package de.jhoppmann.phclient.client.navigation;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author jhoppman
 */
public class SimplePlace extends Place {
	private PlaceId id;


	public SimplePlace(PlaceId placeId) {
		id = placeId;
	}

	public PlaceId getId() {
		return id;
	}

	public static class Tokenizer implements PlaceTokenizer<SimplePlace> {

		@Override
		public SimplePlace getPlace(String token) {
			return new SimplePlace(PlaceId.valueOf(token));
		}

		@Override
		public String getToken(SimplePlace place) {
			return place.getId().name();
		}
	}
}

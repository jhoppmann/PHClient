package de.jhoppmann.phclient.client.services;

import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;

import java.util.ArrayDeque;
import java.util.Queue;

public class ConcurrentChartLoader extends ChartLoader {

	private static final Queue<DelegatingLoaderRunnable> LOADING_QUEUE = new ArrayDeque<>();

	public ConcurrentChartLoader(ChartPackage... packages) {
		super(packages);
	}

	@Override
	public void loadApi(Runnable callback) {
		doLoadApi(this, callback);
	}

	protected void doLoadApi(ConcurrentChartLoader loader, Runnable callback) {
		// @see https://github.com/google/gwt-charts/issues/66
		if (LOADING_QUEUE.isEmpty()) {
			LOADING_QUEUE.add(new DelegatingLoaderRunnable(callback, loader));
			final Runnable delegatingCallback = new Runnable() {
				@Override
				public void run() {
					try {
						LOADING_QUEUE.poll().run();
					} finally {
						final DelegatingLoaderRunnable peek = LOADING_QUEUE.peek();
						if (peek != null) {
							peek.loader.doLoadApi(this);
						}
					}
				}
			};

			doLoadApi(delegatingCallback);
		} else {
			LOADING_QUEUE.add(new DelegatingLoaderRunnable(callback, loader));
		}
	}

	protected void doLoadApi(Runnable callback) {
		super.loadApi(callback);
	}

	protected static class DelegatingLoaderRunnable implements Runnable {

		private final Runnable delegate;
		private final ConcurrentChartLoader loader;

		public DelegatingLoaderRunnable(Runnable delegate, ConcurrentChartLoader loader) {
			this.delegate = delegate;
			this.loader = loader;
		}

		@Override
		public void run() {
			delegate.run();
		}
	}
}
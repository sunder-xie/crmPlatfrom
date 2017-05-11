package com.kintiger.platform.framework.content.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author
 */
public class CountListener implements HttpSessionListener {

	// private CountService countService;

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public final void sessionCreated(final HttpSessionEvent arg0) {
		init();
		// countService.getCount(true);
		OnlineCounter.raise();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public final void sessionDestroyed(final HttpSessionEvent arg0) {
		if (OnlineCounter.getOnline() > 0) {
			OnlineCounter.reduce();
		}
	}

	private void init() {
		/*
		 * if (countService == null) { countService = (CountService)
		 * ServiceLocator.getInstance() .getService("countService"); }
		 */
	}
}
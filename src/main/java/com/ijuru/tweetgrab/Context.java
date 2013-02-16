/**
 * Copyright 2011 Rowan Seymour
 *
 * This file is part of TweetGrab.
 *
 * TweetGrab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TweetGrab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TweetGrab. If not, see <http://www.gnu.org/licenses/>.
 */

package com.ijuru.tweetgrab;

import org.codehaus.jackson.map.ObjectMapper;

import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Application context
 */
public class Context {

	private static final String ENVVAR_NAME = "TWEETGRAB_OPTIONS";
	private static final String JVMPROP_NAME = "tweetgrab.options";

	private static Options options;

	private static TwitterFactory twitterFactory;
	
	/**
	 * Starts the application
	 * @throws Exception 
	 */
	public static void startApplication() throws Exception {
		// Look for environment variable or system property
		String optionsJSON = System.getenv(ENVVAR_NAME);
		if (optionsJSON == null) {
			optionsJSON = System.getProperty(JVMPROP_NAME);
			if (optionsJSON == null) {
				throw new Exception("Could not find environment variable (" + ENVVAR_NAME + ") or JVM property (" + JVMPROP_NAME + ")");
			}
		}
			
		ObjectMapper mapper = new ObjectMapper();
		options = mapper.readValue(optionsJSON, Options.class);

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(false)
			.setOAuthConsumerKey(options.getCredentials().get("consumerKey"))
			.setOAuthConsumerSecret(options.getCredentials().get("consumerSecret"))
			.setOAuthAccessToken(options.getCredentials().get("accessToken"))
			.setOAuthAccessTokenSecret(options.getCredentials().get("accessTokenSecret"));

		twitterFactory = new TwitterFactory(cb.build());
	}
	
	public static void destroyApplication() {
		
	}
	
	public static Options getOptions() {
		return options;
	}

	public static TwitterFactory getTwitterFactory() {
		return twitterFactory;
	}
}
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

package com.ijuru.tweetgrab.web;

import com.ijuru.tweetgrab.Context;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import twitter4j.*;

/**
 * Grabs a tweet from a randomly selected account
 */
public class GrabServlet extends HttpServlet {
	
	protected static final Logger log = LogManager.getLogger(GrabServlet.class);

	private static final long serialVersionUID = 1L;

	private Random rand = new Random();

	private static final String ERROR_MESSAGE = "Something went wrong... sorry";
	
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		// Get random account
		List<String> accounts = Context.getOptions().getAccounts();
		String randAccount = accounts.get(rand.nextInt(accounts.size()));

		try {
			Twitter twitter = Context.getTwitterFactory().getInstance();
			ResponseList<Status> tweets =  twitter.getUserTimeline(randAccount);

			if (tweets.size() == 0) {
				log.warn("No tweets for " + randAccount);
				out.write(ERROR_MESSAGE);
				return;
			}

			// Pick random tweet
			Status randomTweet = tweets.get(rand.nextInt(tweets.size()));
			//long tweetId = randomTweet.getId();
			String tweetText = randomTweet.getText();

			out.write(tweetText);
		}
		catch (TwitterException ex) {
			log.error(ex);
			out.write(ERROR_MESSAGE);
			return;
		}
	}
}
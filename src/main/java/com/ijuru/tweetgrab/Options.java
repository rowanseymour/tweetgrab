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

import java.util.List;
import java.util.Map;

/**
 * Application options
 */
public class Options {

	private List<String> accounts;

	private Map<String, String> credentials;

	public List<String> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<String> accounts) {
		this.accounts = accounts;
	}

	public Map<String, String> getCredentials() {
		return credentials;
	}

	public void setCredentials(Map<String, String> credentials) {
		this.credentials = credentials;
	}
}
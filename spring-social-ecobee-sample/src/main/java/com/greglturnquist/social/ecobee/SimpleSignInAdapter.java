/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.social.ecobee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author Greg Turnquist
 */
public class SimpleSignInAdapter implements SignInAdapter {

	private final RequestCache requestCache;

	public SimpleSignInAdapter(RequestCache requestCache) {
		this.requestCache = requestCache;
	}

	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {

		SignInUtils.signin(userId);
		return extractOriginalUrl(request);
	}

	private String extractOriginalUrl(NativeWebRequest request) {

		HttpServletRequest nativeRequest = request.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse nativeResponse = request.getNativeResponse(HttpServletResponse.class);
		SavedRequest saved = requestCache.getRequest(nativeRequest, nativeResponse);
		if (saved == null) {
			return null;
		}
		requestCache.removeRequest(nativeRequest, nativeResponse);
		removeAuthenticationAttributes(nativeRequest.getSession(false));
		return saved.getRedirectUrl();
	}

	private void removeAuthenticationAttributes(HttpSession session) {

		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}

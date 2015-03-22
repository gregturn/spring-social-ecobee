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

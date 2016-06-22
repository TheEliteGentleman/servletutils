/**
 * 
 */
package za.co.sindi.servlet.utils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.common.utils.Strings;

/**
 * @author Bienfait Sindi
 * @since 04 December 2013
 *
 */
public final class ServletUtils {

	private static final String NO_SERVLET_REQUEST = "A ServletRequest is required.";
	private static final String NO_SERVLET_RESPONSE = "A ServletResponse is required.";
	private static final String NO_COOKIE_NAME = "A cookie name is required.";
	
	private ServletUtils() {
		//TODO: Absolutely nothing...
	}
	
	private static void ensureRequest(ServletRequest request) {
		PreConditions.checkArgument(request != null, NO_SERVLET_REQUEST);
	}
	
	private static void ensureResponse(ServletResponse response) {
		PreConditions.checkArgument(response != null, NO_SERVLET_RESPONSE);
	}
	
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		ensureRequest(request);		
		PreConditions.checkArgument(!Strings.isNullOrEmpty(cookieName), NO_COOKIE_NAME);
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		
		return null;
	}
	
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie cookie = getCookie(request, cookieName);
		if (cookie != null) {
			return cookie.getValue();
		}
		
		return null;
	}
	
	public static void addCookie(HttpServletResponse response, Cookie cookie) {
		ensureResponse(response);
		
		if (cookie != null) {
			response.addCookie(cookie);
		}
	}
	
	public static void addCookie(HttpServletResponse response, String name, String value, String comment, String domain, String path, int maxAge, boolean secure) {
		addCookie(response, name, value, comment, domain, path, maxAge, secure, false);
	}
	
	public static void addCookie(HttpServletResponse response, String name, String value, String comment, String domain, String path, int maxAge, boolean secure, boolean httpOnly) {
		Cookie cookie = new Cookie(name, value);
		if (!Strings.isNull(comment)) {
			cookie.setComment(comment);
		}
		
		if (!Strings.isNull(domain)) {
			cookie.setDomain(domain);
		}
		
		if (!Strings.isNull(path)) {
			cookie.setPath(path);
		}
		
		cookie.setHttpOnly(httpOnly);
		cookie.setMaxAge(maxAge);
		cookie.setSecure(secure);
		
		addCookie(response, cookie);
	}
	
	public static void removeCookie(HttpServletResponse response, String name, String path) {
		addCookie(response, name, null, null, null, path, 0, false, false);
	}
	
	public static String getServletURLPath(HttpServletRequest request) {
		ensureRequest(request);
		
		String requestURL = request.getRequestURL().toString();
		return requestURL.substring(0, requestURL.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
	}
	
	public static String getCompleteRequestURL(HttpServletRequest request) {
		ensureRequest(request);
		
		StringBuffer requestURL = request.getRequestURL();
		if (request.getQueryString() != null) {
		    requestURL.append("?").append(request.getQueryString());
		}

		return requestURL.toString();
	}
	
	public static String getRemoteAddr(HttpServletRequest request) {
		ensureRequest(request);
		
		String forwardedFor = request.getHeader("X-Forwarded-For");

		if (!Strings.isNullOrEmpty(forwardedFor)) {
			return forwardedFor.split("\\s*,\\s*", 2)[0]; // It's a comma separated string: client,proxy1,proxy2,...
		}

		return request.getRemoteAddr();
	}
}

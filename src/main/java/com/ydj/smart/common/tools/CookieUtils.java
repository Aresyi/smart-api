package com.ydj.smart.common.tools;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ydj.smart.api.constant.Constant;

/**
 * @description
 * 
 * @author Frank
 * @version 1.0
 * @create_time 2010-2-21
 */
public class CookieUtils {
	
	public static final String COOKIE_DOMAIN = Constant.COOKIE_DOMAIN;

	public static Cookie newCookie(String name, String value) {

		return newCookie(name, value, false);

	}

	public static Cookie newCookie(String name, String value,
			boolean save_forever) {

		int max_age = save_forever ? Integer.MAX_VALUE : -1;
		return newCookie(name, value, max_age);

	}

	public static Cookie newCookie(String name, String value, int max_age) {

		try {
			value = new String(URLEncoder.encode(value, "utf-8").getBytes());
		} catch (Exception e) {
		}

		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(max_age);
		cookie.setPath("/");
		cookie.setDomain(COOKIE_DOMAIN);

		return cookie;

	}

	public static String getCookieValue(HttpServletRequest request,
			String cookieName) {

		Cookie[] cookies = request.getCookies();

		if (cookies == null)
			return "";

		for (int i = 0; i < cookies.length; i++) {
			if ((cookieName).equalsIgnoreCase(cookies[i].getName())) {
				try {
					return URLDecoder.decode(new String(cookies[i].getValue()
							.getBytes()), "utf-8");
				} catch (Exception e) {
					return cookies[i].getValue();
				}
			}
		}

		return "";
	}

	public static void removeCookie(HttpServletResponse response, Cookie cookie) {

		cookie.setDomain(COOKIE_DOMAIN);
		cookie.setPath("/");
		cookie.setValue("");
		cookie.setMaxAge(0);
		response.addCookie(cookie);

	}

	public static void removeCookie(HttpServletResponse response,
			String cookieName) {

		Cookie c = newCookie(cookieName, "");
		removeCookie(response, c);

	}

	public static String toString(Cookie cookie) {

		DateFormat GMTFormat = new SimpleDateFormat(
				"EEE dd-MMM-yyyy hh:mm:ss z", Locale.ENGLISH);
		GMTFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

		StringBuffer sb = new StringBuffer();
		sb.append(cookie.getName());
		sb.append("=");
		sb.append(cookie.getValue());
		sb.append("; domain=").append(COOKIE_DOMAIN)
				.append("; path=/; ");
		if (cookie.getMaxAge() != -1)
			sb.append("expires=")
					.append(GMTFormat.format(System.currentTimeMillis()
							+ cookie.getMaxAge())).append("; ");

		return (sb.toString());
	}

}

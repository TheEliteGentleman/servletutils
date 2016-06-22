/**
 * 
 */
package za.co.sindi.servlet.utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This code follows the pattern similar to Spring Framework <a href="http://docs.spring.io/autorepo/docs/spring-framework/3.0.x/api/org/springframework/web/servlet/view/RedirectView.html">RedirectView</a> class.
 * 
 * @author Bienfait Sindi
 * @since 08 August 2014
 *
 */
public class HttpRedirectView {

	private String location;
	private boolean contextRelative;
	private boolean http10Compatible;
	
	/**
	 * Default constructor (in case of <code>RedirectView.newInstance()</code>).
	 */
	public HttpRedirectView() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param location
	 */
	public HttpRedirectView(String location) {
		this(location, false);
	}

	/**
	 * @param location
	 * @param contextRelative
	 */
	public HttpRedirectView(String location, boolean contextRelative) {
		this(location, contextRelative, false);
	}

	/**
	 * @param location
	 * @param contextRelative
	 * @param http10Compatible
	 */
	public HttpRedirectView(String location, boolean contextRelative, boolean http10Compatible) {
		super();
		setLocation(location);
		setContextRelative(contextRelative);
		setHttp10Compatible(http10Compatible);
	}
	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the contextRelative
	 */
	public boolean isContextRelative() {
		return contextRelative;
	}

	/**
	 * @param contextRelative the contextRelative to set
	 */
	public void setContextRelative(boolean contextRelative) {
		this.contextRelative = contextRelative;
	}

	/**
	 * @return the http10Compatible
	 */
	public boolean isHttp10Compatible() {
		return http10Compatible;
	}

	/**
	 * @param http10Compatible the http10Compatible to set
	 */
	public void setHttp10Compatible(boolean http10Compatible) {
		this.http10Compatible = http10Compatible;
	}

	public void sendRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (location == null || location.isEmpty()) {
			throw new IllegalStateException("No location has been provided.");
		}
		
		if (location.startsWith("/") && contextRelative) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(location);
			if (dispatcher == null) {
				dispatcher = request.getServletContext().getRequestDispatcher(location);
			}
			
			dispatcher.forward(request, response);
		} else {
			if (http10Compatible) {
				//We must do a HTTP 302 Found.
				response.sendRedirect(response.encodeRedirectURL(location));
			} else {
				response.setStatus(HttpServletResponse.SC_SEE_OTHER); //HTTP 303
				response.addHeader("Location", response.encodeRedirectURL(location));
			}
		}
	}
}

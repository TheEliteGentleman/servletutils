/**
 * 
 */
package za.co.sindi.servlet.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Bienfait Sindi
 * @since 22 March 2014
 *
 */
public abstract class HttpFilter extends GenericFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -291328718608327863L;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (!(request instanceof HttpServletRequest && response instanceof HttpServletResponse)) {
			throw new IllegalStateException("Non HTTP Filter request or response.");
		}
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession(false);
		
		//doFilter
		doFilter(httpRequest, httpResponse, httpSession, chain);
	}

	protected abstract void doFilter(HttpServletRequest request, HttpServletResponse response, HttpSession session, FilterChain chain) throws IOException, ServletException;
}

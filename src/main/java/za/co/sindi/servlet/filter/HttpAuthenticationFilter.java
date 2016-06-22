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
 * @since 08 August 2014
 *
 */
public abstract class HttpAuthenticationFilter extends AuthenticationFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 778271307997620198L;
	
	/* (non-Javadoc)
	 * @see za.co.sindi.servlet.filter.AuthenticationFilter#executeLogin(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		
		return executeLogin(httpRequest, httpResponse, session);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.servlet.filter.AuthenticationFilter#onLoginSuccess(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void onLoginSuccess(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		
		onLoginSuccess(httpRequest, httpResponse, session, chain);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.servlet.filter.AuthenticationFilter#onLoginFailure(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected void onLoginFailure(ServletRequest request, ServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		onLoginFailure(httpRequest, httpResponse);
	}
	
	protected abstract boolean executeLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException;
	protected abstract void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, HttpSession session, FilterChain chain) throws IOException, ServletException;
	protected abstract void onLoginFailure(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}

/**
 * 
 */
package za.co.sindi.servlet.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Bienfait Sindi
 * @since 07 August 2014
 *
 */
public abstract class AuthorizationFilter extends GenericFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8634733104941199723L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		preHandle(request, response);
		
		//Allow access?
		if (!isAccessAllowed(request, response)) {
			onAccessDenied(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}
	
	protected void preHandle(ServletRequest request, ServletResponse response) throws IOException {
		
	}
	
	protected abstract boolean isAccessAllowed(ServletRequest request, ServletResponse response) throws IOException;
	protected abstract boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException, ServletException;
}

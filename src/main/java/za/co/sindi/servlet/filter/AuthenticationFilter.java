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
 * @since 08 August 2014
 *
 */
public abstract class AuthenticationFilter extends GenericFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -236699758284543671L;
	
	/* (non-Javadoc)
	 * @see za.co.sindi.servlet.filter.HttpFilter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (executeLogin(request, response)) {
			onLoginSuccess(request, response, chain);
		} else {
			onLoginFailure(request, response);
		}
	}
	
	protected abstract boolean executeLogin(ServletRequest request, ServletResponse response) throws IOException;
	protected abstract void onLoginSuccess(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException;
	protected abstract void onLoginFailure(ServletRequest request, ServletResponse response) throws IOException, ServletException;
}

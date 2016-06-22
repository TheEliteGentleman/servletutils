/**
 * 
 */
package za.co.sindi.servlet.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.transaction.UserTransaction;

/**
 * @author Bienfait Sindi
 * @since 18 March 2015
 *
 */
public class OpenTransactionInViewFilter extends GenericFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9137731043110443723L;

	private static final Logger LOGGER = Logger.getLogger(OpenTransactionInViewFilter.class.getName());
	
	@Resource
	private UserTransaction userTransaction;
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		try {
			if (userTransaction != null) {
				userTransaction.begin();
			}
			
			chain.doFilter(request, response);
			
			if (userTransaction != null) {
				userTransaction.commit();
			}
		} catch (Exception e) {
			if (LOGGER.isLoggable(Level.SEVERE)) {
				LOGGER.log(Level.SEVERE, "User Transaction failed.", e);
			}
		}
	}
}

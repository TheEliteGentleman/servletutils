/**
 * 
 */
package za.co.sindi.servlet.filter;

import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author Bienfait Sindi
 * @since 08 August 2014
 *
 */
public abstract class GenericFilter implements Filter, FilterConfig, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1165479327371219369L;
	
	private transient FilterConfig filterConfig;
	
	private void verifyFilterConfig() {
		if (filterConfig == null) {
			throw new IllegalStateException("Filter has not been initialized.");
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = filterConfig;
		init();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		doDestroy();
		
		if (filterConfig != null) {
			filterConfig = null;
		}
	}
	
	/**
	 * @return the filterConfig
	 */
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.FilterConfig#getFilterName()
	 */
	@Override
	public String getFilterName() {
		// TODO Auto-generated method stub
		verifyFilterConfig();
		return filterConfig.getFilterName();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.FilterConfig#getServletContext()
	 */
	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		verifyFilterConfig();
		return filterConfig.getServletContext();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.FilterConfig#getInitParameterNames()
	 */
	@Override
	public Enumeration<String> getInitParameterNames() {
		// TODO Auto-generated method stub
		verifyFilterConfig();
		return filterConfig.getInitParameterNames();
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.FilterConfig#getInitParameter(java.lang.String)
	 */
	@Override
	public String getInitParameter(String name) {
		// TODO Auto-generated method stub
		verifyFilterConfig();
		return filterConfig.getInitParameter(name);
	}

	protected void init() {
		
	}
	
	protected void doDestroy() {
		
	}
}

/**
 * 
 */
package za.co.sindi.servlet.filter;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Bienfait Sindi
 * @since 24 July 2014
 *
 */
public class CharacterEncodingFilter extends GenericFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3998696537923226351L;
	
	private static final String ENCODING_INIT_PARAM = "encoding";
	private static final String APPLY_ENCODING_ON_RESPONSE_INIT_PARAM = "applyEncodingOnResponse";
	private static final String DEFAULT_ENCODING = "UTF-8";
	
	private String encoding = DEFAULT_ENCODING;
	private boolean applyEncodingOnResponse = false;
	
	/* (non-Javadoc)
	 * @see za.co.sindi.servlet.filter.GenericFilter#init()
	 */
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		String encoding = getInitParameter(ENCODING_INIT_PARAM);
		if (encoding != null && !encoding.isEmpty()) {
			try {
				Charset.forName(encoding);
			} catch (Exception e) {
				throw new IllegalArgumentException("The '" + ENCODING_INIT_PARAM + "' init parameter contains an invalid encoding character set " + encoding + ".");
			}
			
			this.encoding = encoding;
		}
		
		applyEncodingOnResponse = Boolean.valueOf(getInitParameter(APPLY_ENCODING_ON_RESPONSE_INIT_PARAM));
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(encoding);
		}
		
		if (applyEncodingOnResponse) {
			//Only from Servlet 2.4 and higher can we set the response. This is to prevent issues for those using Servlet less than 2.4 as there is not character encoding methods on the response.
			ServletContext context = request.getServletContext();
			if (context.getMajorVersion() > 2 || (context.getMajorVersion() == 2 && context.getMinorVersion() >= 4)) {
				if (response.getCharacterEncoding() == null) {
					response.setCharacterEncoding(encoding);
				}
			}
		}
		
		//Now, call the chain...
		chain.doFilter(request, response);
	}
}

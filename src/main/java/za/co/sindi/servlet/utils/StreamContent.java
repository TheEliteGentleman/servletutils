/**
 * 
 */
package za.co.sindi.servlet.utils;

import java.io.InputStream;

/**
 * @author Bienfait Sindi
 * @since 25 September 2014
 *
 */
public interface StreamContent {

	public String getContentEncoding();
	public String getContentDisposition();
	public String getContentType();
	public InputStream getInputStream();
	public String getName();
}

/**
 * 
 */
package com.kunal.stock.dm.constants;

import java.io.Serializable;

/**
 * @author kunallimaye
 *
 */
public class Constants implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1639061642502023913L;

	public static final String SECURITY_REST_SERVICE		=	"securities";

	public static final String COMPANY_REST_SERVICE		=	"companies";
	public static final String COMPANY_BY_SYMBOL_PREFIX	=	"symbol:";
	public static final String COMPANY_BY_SYMBOL_REGEX	=	"[a-zA-Z]*.[a-zA-Z]*";
}

/**
 * Generic framework code included with 
 * <a href="http://www.amazon.com/exec/obidos/tg/detail/-/1861007841/">Expert One-On-One J2EE Design and Development</a>
 * by Rod Johnson (Wrox, 2002). 
 * This code is free to use and modify. 
 * Please contact <a href="mailto:rod.johnson@interface21.com">rod.johnson@interface21.com</a>
 * for commercial support.
 */

package org.springframework.dao;

/**
 * Exception thrown on an optimistic locking violation. This exception
 * will be thrown either by O/R mapping tools or by DAO implementations.
 * @author Rod Johnson
 * @version $Id: OptimisticLockingFailureException.java,v 1.2 2003-10-14 16:30:13 jhoeller Exp $
 */ 
public class OptimisticLockingFailureException extends DataAccessException {

	/**
	 * Constructor for OptimisticLockingFailureDataAccessException.
	 * @param msg mesg
	 */
	public OptimisticLockingFailureException(String msg) {
		super(msg);
	}

	/**
	 * Constructor for OptimisticLockingFailureDataAccessException.
	 * @param msg mesg
	 * @param ex root cause
	 */
	public OptimisticLockingFailureException(String msg, Throwable ex) {
		super(msg, ex);
	}

}

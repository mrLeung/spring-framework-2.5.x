/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.springframework.beans;

import org.springframework.core.NestedRuntimeException;

/**
 * Abstract superclass for all exceptions thrown in the beans package
 * and subpackages.
 *
 * <p>Note that this is a runtime (unchecked) exception. Beans exceptions
 * are usually fatal; there is no reason for them to be checked.
 *
 * @author Rod Johnson
 */
public abstract class BeansException extends NestedRuntimeException {

	/**
	 * Constructs a <code>BeansException</code> with the specified message.
	 * @param msg the detail message
	 */
	public BeansException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a <code>BeansException</code> with the specified message
	 * and root cause.
	 * @param msg the detail message
	 * @param ex the root cause
	 */
	public BeansException(String msg, Throwable ex) {
		super(msg, ex);
	}

}

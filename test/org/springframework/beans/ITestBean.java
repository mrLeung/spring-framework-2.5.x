
/*
 * Copyright 2002-2004 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 

package org.springframework.beans;

/**
 * Interface used for test beans.
 * 
 * @author Rod Johnson
 * @version $Id: ITestBean.java,v 1.5 2004-07-10 06:39:38 johnsonr Exp $
 */
public interface ITestBean {
	
	int getAge();
	
	void setAge(int age);
	
	String getName();
	
	void setName(String name);
	
	ITestBean getSpouse();
	
	void setSpouse(ITestBean spouse);
	
	/**
	 * t null no error
	 */
	void exceptional(Throwable t) throws Throwable;
	
	Object returnsThis();
	
	INestedTestBean getDoctor();
	
	INestedTestBean getLawyer();

	IndexedTestBean getNestedIndexedBean();
	
	/**
	 * Increment the age by one
	 * @return the previous age
	 *
	 */
	int haveBirthday();

}

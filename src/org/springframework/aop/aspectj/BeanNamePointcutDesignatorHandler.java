/*
 * Copyright 2002-2007 the original author or authors.
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
package org.springframework.aop.aspectj;

import org.aspectj.weaver.patterns.NamePattern;
import org.aspectj.weaver.tools.ContextBasedMatcher;
import org.aspectj.weaver.tools.FuzzyBoolean;
import org.aspectj.weaver.tools.MatchingContext;
import org.aspectj.weaver.tools.PointcutDesignatorHandler;

/**
 * Handler for the Spring-spefic bean() pointcut designator extension to
 * AspectJ.
 * <p>
 * This handler needs to be added to each pointcut object that needs to handle
 * the bean() PCD. Matching context is automatically obtained by examining a
 * thread local variable and therefore a matching context need not be set on the
 * pointcut.
 * 
 * @author Ramnivas Laddad
 * @since 2.1
 */
public class BeanNamePointcutDesignatorHandler implements PointcutDesignatorHandler {
	private static final String BEAN_DESIGNATOR_NAME = "bean";

	public String getDesignatorName() {
		return BEAN_DESIGNATOR_NAME;
	}

	public ContextBasedMatcher parse(String expression) {
		return new BeanNameContextMatcher(expression);
	}

	private static class BeanNameContextMatcher implements ContextBasedMatcher {
		private NamePattern expressionPattern;

		public BeanNameContextMatcher(String expression) {
			expressionPattern = new NamePattern(expression);
		}

		public boolean couldMatchJoinPointsInType(Class someClass) {
			return true;
		}

		public boolean couldMatchJoinPointsInType(Class someClass, MatchingContext context) {
			return contextMatch();
		}

		public boolean matchesDynamically(MatchingContext context) {
			return contextMatch();
		}

		public FuzzyBoolean matchesStatically(MatchingContext context) {
			return FuzzyBoolean.fromBoolean(contextMatch());
		}

		public boolean mayNeedDynamicTest() {
			return false;
		}

		private boolean contextMatch() {
			if (ProxyCreationContext.isProxyCreationInProgress()) {
				String advisedBeanName = ProxyCreationContext.getCurrentProxyingBeanName();
				return beanNameMatch(advisedBeanName);
			}
			/*
			 * If postprocessing for autoproxying purpose isn't going on, just
			 * declare it as a match. the bean() is a statically-determinable
			 * PCD. Therefore, we need matching process only during proxy
			 * creation. If a match is declared during that process, no further
			 * decision needs to be made. If a match is not declared, the
			 * corresponding advisor will no be considered eligible and
			 * therefore we will not be asked again.
			 */
			return true;
		}

		private boolean beanNameMatch(String beanName) {
			return beanName == null ? false : expressionPattern.matches(beanName);
		}
	}
}

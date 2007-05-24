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

package org.springframework.aop.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.ParserContext;

/**
 * Utility class for handling registration of auto-proxy creators used internally
 * by the '<code>aop</code>' namespace tags.
 *
 * <p>Only a single auto-proxy creator can be registered and multiple tags may wish
 * to register different concrete implementations. As such this class wraps a simple
 * escalation protocol, allowing classes to request a particular auto-proxy creator
 * and know that class, <code>or a subclass thereof</code>, will eventually be resident
 * in the application context.
 *
 * @author Rob Harrop
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @since 2.0
 */
public abstract class AopNamespaceUtils {

	public static void registerAutoProxyCreatorIfNecessary(ParserContext parserContext, Object sourceElement) {
		BeanDefinitionRegistry registry = parserContext.getRegistry();
		boolean alreadyRegistered = AopConfigUtils.isAutoProxyCreatorRegistered(registry);
		BeanDefinition beanDefinition = AopConfigUtils.registerAutoProxyCreatorIfNecessary(registry, parserContext.extractSource(sourceElement));
		if (!alreadyRegistered) {
			registerComponent(beanDefinition, parserContext);
		}		
	}

	public static void registerAspectJAutoProxyCreatorIfNecessary(ParserContext parserContext, Object sourceElement) {
		BeanDefinitionRegistry registry = parserContext.getRegistry();
		boolean alreadyRegistered = AopConfigUtils.isAutoProxyCreatorRegistered(registry);
		BeanDefinition beanDefinition = AopConfigUtils.registerAspectJAutoProxyCreatorIfNecessary(registry, parserContext.extractSource(sourceElement));
		if (!alreadyRegistered) {
			registerComponent(beanDefinition, parserContext);
		}
	}

	public static void registerAtAspectJAutoProxyCreatorIfNecessary(ParserContext parserContext, Object sourceElement) {
		BeanDefinitionRegistry registry = parserContext.getRegistry();
		boolean alreadyRegistered = AopConfigUtils.isAutoProxyCreatorRegistered(registry);
		BeanDefinition beanDefinition = AopConfigUtils.registerAtAspectJAutoProxyCreatorIfNecessary(registry, parserContext.extractSource(sourceElement));
		if (!alreadyRegistered) {
			registerComponent(beanDefinition, parserContext);
		}
	}


	private static void registerComponent(BeanDefinition beanDefinition, ParserContext parserContext) {
		BeanComponentDefinition componentDefinition =
				new BeanComponentDefinition(beanDefinition, AopConfigUtils.AUTO_PROXY_CREATOR_BEAN_NAME);
		parserContext.registerComponent(componentDefinition);
	}

}

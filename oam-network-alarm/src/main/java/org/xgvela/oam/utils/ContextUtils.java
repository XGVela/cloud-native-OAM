package org.xgvela.oam.utils;

import org.springframework.context.ApplicationContext;

import java.io.IOException;

/**
 * @author gengfei01
 *
 */
public class ContextUtils {

	public static ApplicationContext context;

	private ContextUtils() {
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		context = applicationContext;
	}

	public static Object getBean(String beanName) throws IOException {
		if (context == null) {
			throw new IOException("ApplicationContext not init");
		}
		return context.getBean(beanName);
	}

	public static <T> T getBean(Class<T> t) throws IOException {
		if (context == null) {
			throw new IOException("ApplicationContext not init");
		}
		return context.getBean(t);
	}
}
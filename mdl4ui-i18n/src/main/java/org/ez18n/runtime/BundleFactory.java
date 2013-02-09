package org.ez18n.runtime;

import java.lang.annotation.Annotation;
import java.util.ServiceLoader;

public class BundleFactory {
	public static final <S> S get(Class<S> service, Class<? extends Annotation> annotation) {
		final ServiceLoader<S> loader = ServiceLoader.<S> load(service);
		for (S bundle : loader) {
			if (bundle.getClass().getAnnotation(annotation) != null)
				return bundle;
		}
		throw new IllegalStateException("bundle not found for " + service.getName());
	}
}

package org.openjdk.jmc.flightrecorder.internal.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;

public class InternCacheProvider {

	public static final InternCacheProvider INSTANCE = new InternCacheProvider();

	private final Map<Class, Interner> weakInternerCache;

	public InternCacheProvider() {
		weakInternerCache = new ConcurrentHashMap<>();
	}

	@SuppressWarnings("unchecked")
	public <T> Interner<T> getWeakInterner(Class<T> clazz) {
		weakInternerCache.computeIfAbsent(clazz, c -> Interners.<T> newWeakInterner());
		return weakInternerCache.get(clazz);
	}
}

package aa.test.qa;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class GlobalCache {
	// Inner class to store value and its expiration time
	private static class CacheEntry {
		final Object value;
		final long expiryTime; // in milliseconds

		CacheEntry(Object value, long ttlMillis) {
			this.value = value;
			this.expiryTime = (ttlMillis > 0) ? System.currentTimeMillis() + ttlMillis : -1;
		}

		boolean isExpired() {
			return expiryTime > 0 && System.currentTimeMillis() > expiryTime;
		}
	}

	// Thread-safe map for storing cached values
	private static final ConcurrentHashMap<String, CacheEntry> cache = new ConcurrentHashMap<>();

	/**
	 * Stores a value in the cache with optional TTL.
	 * 
	 * @param key   Cache key
	 * @param value Value to store
	 * @param ttl   Time-to-live in milliseconds (0 for no expiry)
	 */
	public static void put(String key, Object value, long ttl) {
		if (key == null || value == null) {
			throw new IllegalArgumentException("Key and value must not be null");
		}
		cache.put(key, new CacheEntry(value, ttl));
	}

	/**
	 * Retrieves a value from the cache.
	 * 
	 * @param key Cache key
	 * @return Cached value or null if not found/expired
	 */
	public static Object get(String key) {
		CacheEntry entry = cache.get(key);
		if (entry == null)
			return null;
		if (entry.isExpired()) {
			cache.remove(key);
			return null;
		}
		return entry.value;
	}

	/**
	 * Removes a value from the cache.
	 */
	public static void remove(String key) {
		cache.remove(key);
	}

	/**
	 * Clears the entire cache.
	 */
	public static void clear() {
		cache.clear();
	}

	// Example usage
	public static void main(String[] args) throws InterruptedException {
		// Store a global variable with 2 seconds TTL
		GlobalCache.put("myVar", "Hello, Cache!", TimeUnit.SECONDS.toMillis(2));

		// Retrieve immediately
		System.out.println("Value: " + GlobalCache.get("myVar")); // Hello, Cache!

		// Wait for expiry
		Thread.sleep(2500);
		System.out.println("After expiry: " + GlobalCache.get("myVar")); // null
	}
}

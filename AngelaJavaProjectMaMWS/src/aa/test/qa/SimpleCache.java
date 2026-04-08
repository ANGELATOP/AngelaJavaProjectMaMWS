package aa.test.qa;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple cache implementation using HashMap with optional TTL (time-to-live).
 */
public class SimpleCache<K, V> {

    // Inner class to store value and its expiration time
    private static class CacheItem<V> {
        V value;
        long expiryTime; // in milliseconds

        CacheItem(V value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }
    }

    private final Map<K, CacheItem<V>> cache = new HashMap<>();

    /**
     * Puts a value into the cache with a TTL.
     * @param key The key to store.
     * @param value The value to store.
     * @param ttlMillis Time-to-live in milliseconds. Use 0 for no expiration.
     */
    public void put(K key, V value, long ttlMillis) {
        long expiryTime = (ttlMillis > 0) ? System.currentTimeMillis() + ttlMillis : 0;
        cache.put(key, new CacheItem<>(value, expiryTime));
    }

    /**
     * Retrieves a value from the cache.
     * @param key The key to retrieve.
     * @return The cached value, or null if not found or expired.
     */
    public V get(K key) {
        CacheItem<V> item = cache.get(key);
        if (item == null) {
            return null; // Not found
        }
        // Check expiration
        if (item.expiryTime > 0 && System.currentTimeMillis() > item.expiryTime) {
            cache.remove(key); // Remove expired entry
            return null;
        }
        return item.value;
    }

    /**
     * Removes a value from the cache.
     */
    public void remove(K key) {
        cache.remove(key);
    }

    /**
     * Clears the entire cache.
     */
    public void clear() {
        cache.clear();
    }

    // Example usage
    public static void main(String[] args) throws InterruptedException {
        SimpleCache<String, String> myCache = new SimpleCache<>();

        // Store a value with 2 seconds TTL
        myCache.put("greeting", "Hello, World!", 2000);

        // Retrieve immediately
        System.out.println("Cached value: " + myCache.get("greeting")); // Should print value

        // Wait 3 seconds to let it expire
        Thread.sleep(3000);
        System.out.println("After expiration: " + myCache.get("greeting")); // Should print null

        // Store without expiration
        myCache.put("permanent", "I never expire", 0);
        System.out.println("Permanent value: " + myCache.get("permanent"));
    }
}

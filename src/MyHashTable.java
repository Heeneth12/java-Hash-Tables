import java.util.LinkedList;

class MyHashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private LinkedList<Entry<K, V>>[] buckets;
    private int size;

    // Inner class to represent key-value pairs
    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity) {
        buckets = new LinkedList[capacity];
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        buckets[index].add(new Entry<>(key, value));
        size++;

        // Optional: Check if the load factor exceeds a threshold and resize the array
        // if needed.
        // (Omitted for simplicity in this example)
    }

    public V get(K key) {
        int index = hash(key);
        if (buckets[index] != null) {
            for (Entry<K, V> entry : buckets[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        if (buckets[index] != null) {
            buckets[index].removeIf(entry -> entry.key.equals(key));
            size--;

            // Optional: Check if the load factor drops below a threshold and resize the
            // array if needed.
            // (Omitted for simplicity in this example)
        }
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        MyHashTable<String, Integer> myHashTable = new MyHashTable<>();

        myHashTable.put("One", 1);
        myHashTable.put("Two", 2);
        myHashTable.put("Three", 3);

        System.out.println("Value for key 'Two': " + myHashTable.get("Two"));

        myHashTable.remove("Two");

        System.out.println("Size of the hash table: " + myHashTable.size());
    }
}

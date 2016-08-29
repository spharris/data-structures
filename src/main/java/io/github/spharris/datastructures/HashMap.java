package io.github.spharris.datastructures;

public class HashMap<K, V> implements Map<K, V> {

  private static final int INITIAL_LENGTH = 20;
  private static final float LOAD_FACTOR = 0.5f;
  
  private List<List<Entry<K, V>>> data;
  private int size;
  
  public HashMap() {
    data = new ArrayList<>();
    for (int i = 0; i < INITIAL_LENGTH; i++) {
      data.add(new ArrayList<Entry<K, V>>());
    }
    
    size = 0;
  }
  
  @Override
  public void put(K key, V value) {
    int hashCode = key.hashCode();
    Entry<K, V> newEntry = new HashMapEntry<>(key, value);

    boolean found = false;
    int i = 0;
    List<Entry<K, V>> bucket = data.get(hashCode % data.size());
    while (i < bucket.size() && !found) {
      Entry<K, V> entry = bucket.get(i);
      if (entry.key().equals(newEntry.key())) {
        bucket.insert(i, newEntry);
        found = true;
      }
      
      i++;
    }
    
    if (!found) {
      bucket.add(newEntry);
      size++;
    }
    
    checkLoadFactor();
  }

  private void checkLoadFactor() {
    if ((float) size / (float) data.size() > LOAD_FACTOR) {
      List<List<Entry<K, V>>> newData = new ArrayList<>();
      for (int i = 0; i < data.size() * 2; i++) {
        newData.add(new ArrayList<Entry<K, V>>());
      }
      
      List<List<Entry<K, V>>> oldData = data;
      data = newData;
      size = 0;
      for (int i = 0; i < oldData.size(); i++) {
        List<Entry<K, V>> bucket = oldData.get(i);
        for (int j = 0; j < bucket.size(); j++) {
          Entry<K, V> entry = bucket.get(j);
          put(entry.key(), entry.value());
        }
      }
    }
  }
  
  @Override
  public V get(K key) {
    int hashCode = key.hashCode();
    
    List<Entry<K, V>> bucket = data.get(hashCode % data.size());
    for (int i = 0; i < bucket.size(); i++) {
      Entry<K, V> entry = bucket.get(i);
      if (entry.key().equals(key)) {
        return entry.value();
      }
    }
    
    return null;
  }

  
  @Override
  public boolean containsKey(K key) {
    int hashCode = key.hashCode();
    
    List<Entry<K, V>> bucket = data.get(hashCode % data.size());
    for (int i = 0; i < bucket.size(); i++) {
      Entry<K, V> entry = bucket.get(i);
      if (entry.key().equals(key)) {
        return true;
      }
    }
    
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  private static class HashMapEntry<K, V> implements Map.Entry<K, V> {

    private final K key;
    private final V value;
    
    HashMapEntry(K key, V value) {
      this.key = key;
      this.value = value;
    }
    
    @Override
    public K key() {
      return key;
    }

    @Override
    public V value() {
      return value;
    }
    
  }
  
}

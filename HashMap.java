import java.util.LinkedList;
import java.util.Random;

public class HashMapImplementation {
    public static void main(String[] args){
        HashMap<Integer,String> map = new HashMap();
        for (int i = 0; i < (int)(Math.pow(16,3)); i++) {
            map.put(i, "Value" + i);
        }
        Random random = new Random();
        for(int i = 0;i < 5;i ++)
        
            System.out.println(map.get(random.nextInt((int)Math.pow(16,3))));
    }
}
class HashMap <K,V> {
    private int capacity;
    private int size;
    private LinkedList<Entry> buckets[];
    
    
    HashMap() {
        this.capacity = 16;
        this.size = 0;
        this.buckets = new LinkedList[this.capacity];
        
    }
    
    public int getSize() {
        return this.size;
    }
    
    public void put (K key,V value) {
        Entry entry = new Entry(key,value);
        int index = entry.hashCode();
        
        if(buckets[index] == null) {
            buckets[index] = new LinkedList();
            buckets[index].push(entry);
            size ++;
            return ;
        }
        for(Entry e : buckets[index]) 
            if(e.key == key) {
                e.value = value;
                return;
            }
        size ++;
        buckets[index].push(entry);
        if(buckets[index].size() >= capacity || size >= capacity) 
            rehash();
    }
    
    
    public void rehash() {
        this.capacity = (int)Math.pow(capacity,2);
        LinkedList<Entry> oldBuckets[] = buckets;
        buckets = new LinkedList[this.capacity];
        for(LinkedList<Entry> oldEntry:oldBuckets) {
            for(Entry e:oldEntry) 
                this.put(e.key,e.value);
        }
        System.out.println ("rehashing successful");
    }
    
    public V get (K key) {
        
        int index = Math.abs(key.hashCode() % capacity);
        
        if(buckets[index] == null) {
            return null;
        }
        for(Entry e : buckets[index]) 
            if(e.key == key) 
                return e.value;
        return null;
    }
    
    public boolean remove(K key) {
        int index = Math.abs(key.hashCode() % capacity);
        
        if(buckets[index] == null) {
            return false;
        }
        boolean present = buckets[index].removeIf(entry -> entry.key.equals(key));
        if(present) size --;
        return present;
    }
    
    private class Entry {
        K key;
        V value;
        Entry (K key,V value) {
            this.key = key;
            this.value = value;
        }
        @Override 
        public int hashCode() {
            return Math.abs(this.key.hashCode() % capacity);
        }
        
    }
                                
}

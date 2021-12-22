import java.util.Arrays;

class Scratch {
    public static void main(String[] args) throws Exception {
        HashTableLinearProbing<Integer, Integer> m = new HashTableLinearProbing<Integer, Integer>(8, (float)0.5);

//        m.add(1, 9);
//        m.add(123, 8);
//
//        System.out.println(m);
//
//        System.out.println("get 1");
//        Integer val = m.get(1);
//        System.out.println("val:" + val);
//
//        System.out.println("add ?");
//        m.add(5, 2);
//        System.out.println(m);
//
//
//        System.out.println("add 13");
//        m.add(13, 2);
//        System.out.println(m);
//
//        System.out.println("add 2");
//        m.add(2, 2);
//        System.out.println(m);
//
//        System.out.println("remove 2");
//        m.remove(2);
//        System.out.println(m);
//
//        System.out.println("add ?");
//        m.add(3, 9);
//        System.out.println(m);
//
//        System.out.println("Exist 3?" + m.exists(4));
//
//        System.out.println("add 2");
//        m.add(2, 2);
//        System.out.println(m);


    }
}


class KeyValuePair<K, V> {

    K key;
    V value;

    KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public String toString(){
        return "(" + this.key + ", " + this.value + ")";
    }

}

class HashTableLinearProbing<K, V> {

    KeyValuePair<K, V>[] arr;

    int capacity;
    int size;
    float loadFactor;

    HashTableLinearProbing(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.size = 0;
        this.arr = new KeyValuePair[this.capacity];
        this.loadFactor = loadFactor;
    }

    //  m is size of hash table
    public int hash(K k, int m) throws Exception {
        if (k instanceof Integer) {
            return (int) k % m;
        } else if (k instanceof String) {
            int sum = 0;
            String kstr = k.toString();
            for (int i = kstr.length() - 1; i >= 0; i--) {
                sum += kstr.charAt(i) * Math.pow(10, kstr.length() - i);
            }
            return sum % m;
        } else {
            throw new Exception("Unknown type!");
        }
    }

    private void resize(int m) throws Exception {
        System.out.println("Resizeing to: " + m);

        KeyValuePair<K, V>[] oldArr = this.arr;
        this.arr = new KeyValuePair[m];
        this.capacity = m;
        this.size = 0;

        for (int i = 0; i < oldArr.length; i++) {
            if (oldArr[i] != null && oldArr[i].key != "DELETED") {
                this.add(oldArr[i].key, oldArr[i].value);
            }
        }
    }

    // if key already exists, update value
    public void add(K key, V value) throws Exception {
        int hash = this.hash(key, this.capacity);
        while (this.arr[hash] != null && this.arr[hash].key != key && this.arr[hash].key != "DELETED") {
            hash = (hash + 1) % this.capacity;
        }
        this.arr[hash] = new KeyValuePair(key, value);

        this.size++;
        if ((float)this.size / this.capacity >= this.loadFactor) {
            this.resize(this.capacity * 2);
        }
    }

    public boolean exists(K key) throws Exception {
        int hash = this.hash(key, this.capacity);
        while (this.arr[hash] != null && this.arr[hash].key != key && this.arr[hash].key != "DELETED") {
            hash = (hash + 1) % this.capacity;
        }
        return this.arr[hash] != null && this.arr[hash].key == key;
    }

    public V get(K key) throws Exception {
        int hash = this.hash(key, this.capacity);
        System.out.println("hash:" + hash);
        while (this.arr[hash] != null && this.arr[hash].key != key && this.arr[hash].key != "DELETED") {
            hash = (hash + 1) % this.capacity;
        }
        System.out.println("hash:" + hash);
        if (this.arr[hash] != null && this.arr[hash].key == key){
            return this.arr[hash].value;
        }
        return null;
    }

    public void remove(K key) throws Exception {
        int hash = this.hash(key, this.capacity);
        while (this.arr[hash] != null && this.arr[hash].key != key && this.arr[hash].key != "DELETED") {
            hash = (hash + 1) % this.capacity;
        }
        if (this.arr[hash].key == key){
            this.arr[hash].key = (K) "DELETED";
        }
    }

    @Override
    public String toString(){

        String result = "";

        return Arrays.toString(this.arr);
    }
}
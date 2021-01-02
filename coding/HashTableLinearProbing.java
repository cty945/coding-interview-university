import java.util.Arrays;

class Scratch {
    public static void main(String[] args) throws Exception {

        HashTable m = new HashTable();

        m.add("1", "aaa");
        m.add("123", "bbb");

        System.out.println(m);

        String val = (String) m.get("1");
        System.out.println(val);

        m.add("2", 2);
        System.out.println(m);

        m.add("3", 3);
        System.out.println(m);

        m.remove("2");
        System.out.println(m);

        m.add("2", 2);
        System.out.println(m);

    }


    public static class KeyValuePair<K, V>{

        public K k;
        public V v;

        public KeyValuePair(K k, V v){
            this.k = k;
            this.v = v;
        }

        public String toString(){
            return "(" + this.k + ", " + this.v + ")";
        }
    }

    public static class HashTable<K, V>{


        private int size = 8;

        private int load = 0;

        private KeyValuePair arr[];

        public HashTable(){
            this.arr = new KeyValuePair[this.size];
        }

        private int hash(K k, int m){
            if (k instanceof Integer){
                return ((int) k) % m;
            } else if (k instanceof String){
                int sum = 0;
                String s = ((String) k);
                for (int i=0; i<s.length(); i++){
                    sum += Math.pow(10, i) * s.charAt(i);
                }
                return sum % m;
            }
            return -1;
        }
        private void resize(int newSize){

            System.out.println("resizing..");

            KeyValuePair oldArr[] = this.arr;
            this.arr = new KeyValuePair[newSize];
            this.size = newSize;
            this.load = 0;

            for (var i=0; i< this.size/2; i++){
                if (oldArr[i] != null && oldArr[i].k != "DUMMY"){
                    this.add((K) oldArr[i].k , (V) oldArr[i].v);
                }
            }
            System.out.println("Done once");
        }

        public void add(K key, V value){

            int index = this.hash(key, this.size);
            // If the hashed Index is ocupied, use linear probing.
            while (this.arr[index] != null && this.arr[index].k != key && this.arr[index].k != "DUMMY"){
                index = ++index % this.size;
            };
            this.arr[index] = new KeyValuePair(key, value);
            this.load++;

            if ((float)this.load / this.size >= 0.5){
                this.resize(this.size*2);
            }

        }

        public boolean exists(K key){
            int index = this.hash(key, this.size);
            while(this.arr[index] != null && this.arr[index].k != key){
                index = ++index % this.size;
            }
            if (this.arr[index]== null){
                return false;
            } else {
                return true;
            }
        }

        public V get(K key) throws Exception{

            int index = this.hash(key, this.size);
            if (this.arr[index] == null){
                throw new Exception(key + " does not exist.");
            }

            while(this.arr[index] != null && this.arr[index].k != key){
                index = ++index % this.size;
            }

            if (this.arr[index] != null && this.arr[index].k == key){
                return (V)this.arr[index].v;
            } else {
                return null;
            }
        }

        public void remove(K key) throws Exception {

            int index = this.hash(key, this.size);
            if (this.arr[index] == null){
                throw new Exception(key + " does not exist.");
            }

            while(this.arr[index] != null && this.arr[index].k != key){
                index = ++index % this.size;
            }

            if (this.arr[index] != null && this.arr[index].k == key){
                this.arr[index] = new KeyValuePair("DUMMY", null);
            }else {
                throw new Exception("Key " + key + " does not exist.");
            }


        }

        public String toString(){

            String result = "";

            return Arrays.toString(this.arr);
        }


    }
}
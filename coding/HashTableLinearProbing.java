class Scratch {
    public static void main(String[] args) {

        HashTable m = new HashTable();

    }




    public static class KeyValuePair<K, V>{

        public K k;
        public V v;

        public KeyValuePair(K k, V v){
            this.k = k;
            this.v = v;
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

            KeyValuePair oldArr[] = this.arr;
            this.arr = new KeyValuePair[newSize];
            this.size = newSize;

            for (var i=0; i< this.size; i++){
                if (oldArr[i] != null && oldArr[i].k != "DUMMY"){
                    this.add(((K) oldArr[i].k) , ((V) oldArr[i].v));
                }
            }
        }

        public void add(K key, V value){

            int index = this.hash(key, this.size);
            // If the hashed Index is ocupied, use linear probing.
            while (this.arr[index] != null && this.arr[index].k != key && this.arr[index].k != "DUMMY"){
                index = ++index % this.size;
            };
            this.arr[index] = new KeyValuePair(key, value);
            this.load++;

            if (this.load / this.size > 0.5){
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

//        public V get(K key){
//
//        }

//        public void remove(K key){
//
//        }
    }
}
import java.util.HashMap;

/*
    设计可以变更的缓存结构（LRU）
         【题目】 设计一种缓存结构，该结构在构造时确定大小，假设大小为K，并有两个功能：
      set(key,value)：将记录(key,value)插入该结构。 get(key)：返回key对应的value值。
         【要求】 1．set和get方法的时间复杂度为O(1)。 2．某个key的set或get操作一旦发生，
      认为这个key的记录成了最经常使用的。 3．当缓存的大小超过K时，移除最不经常使用的记录，
      即set或get最久远的。
         【举例】 假设缓存结构的实例是cache，大小为3，并依次发生如下行为： 1．cache.set("A",1)。
      最经常使用的记录为("A",1)。 2．cache.set("B",2)。最经常使用的记录为("B",2)，("A",1)变
      为最不经常的。 3．cache.set("C",3)。最经常使用的记录为("C",2)，("A",1)还是最不经常的。
       4．cache.get("A")。最经常使用的记录为("A",1)，("B",2)变为最不经常的。 5．cache.set("D",4)。
       大小超过了3，所以移除此时最不经常使用的记录("B",2)， 加入记录 ("D",4)，并且为最经常使用的
       记录，然后("C",2)变为最不经常使用的 记录

       哈希表 + 双向链表
 */
public class LRU {

    public static class Node<V> {
        private V value;
        private Node<V> last;
        private Node<V> next;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class NodeDoubleLinkedList<V> {
        private Node<V> head;
        private Node<V> tail;

        public NodeDoubleLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void addNode(Node<V> newNode) {
            if (newNode == null) {
                return;
            }
            if (this.head == null) {
                this.head = newNode;
                this.tail = newNode;
             } else {
                this.tail.next = newNode;
                newNode.last = this.tail;
                this.tail = newNode;
            }
        }

        public void moveNodeToTail(Node<V> node) {
            if (node == null) {
                return;
            }
            if (this.head == node) {
                this.head = node.next;
                this.head.last = null;
            } else if (this.tail == node) {
                return;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            this.tail.next = node;
            node.last = this.tail;
            node.next = null;
            this.tail = node;
        }

        public Node<V> removeHead() {
            if (this.head == null) {
                return null;
            }
            Node<V> res = this.head;
            if (this.head == this.tail) {
                this.head = null;
                this.tail = null;
            } else {
                this.head = res.next;
                this.head.last = null;
                res.next = null;
            }
            return res;
        }
    }

    public static class MyCache<K, V> {
        private HashMap<K, Node<V>> keyToNode;
        private HashMap<Node<V>, K> nodeToKey;
        private NodeDoubleLinkedList<V> list;
        private int capacity;

        public MyCache(int capacity) {
            if (capacity < 1) {
                throw new RuntimeException("capacity is too small to put it");
            }
            this.capacity = capacity;
            this.keyToNode = new HashMap<>();
            this.nodeToKey = new HashMap<>();
            this.list = new NodeDoubleLinkedList<>();
        }

        public V get(K key) {
            if (this.keyToNode.containsKey(key)) {
                Node<V> res = this.keyToNode.get(key);
                this.list.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }

        public void set(K key, V value) {
            if (this.keyToNode.containsKey(key)) {
                Node<V> node = this.keyToNode.get(key);
                node.value = value;
                this.list.moveNodeToTail(node);
            } else {
                Node<V> res = new Node<V>(value);
                this.keyToNode.put(key, res);
                this.nodeToKey.put(res, key);
                this.list.addNode(res);
                if (this.keyToNode.size() == capacity + 1) {
                    this.removeMostUnusedCache();
                }
            }
        }

        public void removeMostUnusedCache() {
            Node<V> node = this.list.removeHead();
            K k = this.nodeToKey.get(node);
            this.keyToNode.remove(k);
            this.nodeToKey.remove(node);
        }
    }

    public static void main(String[] args) {
        MyCache<String, Integer> testCache = new MyCache<String, Integer>(3);
        testCache.set("A", 1);
        testCache.set("B", 2);
        testCache.set("C", 3);
        System.out.println(testCache.get("B"));
        System.out.println(testCache.get("A"));
        testCache.set("D", 4);
        System.out.println(testCache.get("D"));
        System.out.println(testCache.get("C"));
    }

}

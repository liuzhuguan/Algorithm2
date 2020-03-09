import java.util.HashMap;

public class LFU {

    public static class Node {
        private Integer key;
        private Integer value;
        private Integer times;
        private Node up;
        private Node down;

        public Node(Integer key, Integer value, Integer times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }

    public static class NodeList {
        private Node head;
        private Node tail;
        private NodeList last;
        private NodeList next;

        public NodeList(Node node) {
            this.head = node;
            this.tail = node;
        }

        //从头开始加入
        public void addNodeFromHead(Node newHead) {
            newHead.down = head;
            head.up = newHead;
            head = newHead;
        }

        //判断头是否存在---不存在则为空
        public boolean isEmpty() {
            return head == null;
        }

        public void deleteNode(Node node) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                if (node == head) {
                    head = node.down;
                    head.up = null;
                } else if (node == tail) {
                    tail = node.up;
                    tail.down = null;
                } else {
                    node.up.down = node.down;
                    node.down.up = node.up;
                }
                node.down = null;
                node.up = null;
            }
        }
    }

    public static class LFUCache {
        private int capacity;   //容量
        private int size;       //现有大小
        private NodeList headList;  //头链表
        private HashMap<Integer, Node> records;  //key to value
        private HashMap<Node, NodeList> heads;  //各链表头

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.headList = null;
            this.records = new HashMap<>();
            this.heads = new HashMap<>();
        }

        public void set(int key, int value) {
            if (records.containsKey(key)) {
                Node node = records.get(key);
                node.value = value;
                node.times++;
                NodeList nodeList = heads.get(node);
                move(node, nodeList);
            } else {
                if (size == capacity) {
                    Node tail = headList.tail;
                    headList.deleteNode(tail);
                    modifyHeadList(headList);
                    records.remove(tail.key);
                    heads.remove(tail);
                    size--;
                }
                Node node = new Node(key, value, 1);
                if (headList == null) {
                    headList = new NodeList(node);
                } else {
                    if (headList.head.times == node.times) {
                        headList.addNodeFromHead(node);
                    } else {
                        NodeList nodeList = new NodeList(node);
                        nodeList.next = headList;
                        headList.last = nodeList;
                        headList = nodeList;
                    }
                }
                records.put(key,node);
                heads.put(node, headList);
                size++;
            }
        }

        public void move(Node node, NodeList oldList) {
            oldList.deleteNode(node);
            NodeList preList = modifyHeadList(oldList) ? oldList.last : oldList;
            NodeList nextList = oldList.next;
            if (nextList == null) {
                nextList = new NodeList(node);
                if (preList != null) {
                    preList.next = nextList;
                }
                nextList.last = preList;
                if (headList == null) {
                    headList = nextList;
                }
                heads.put(node,nextList);
            } else {
                if (nextList.head.times.equals(node.times)) {
                    nextList.addNodeFromHead(node);
                    heads.put(node, nextList);
                } else {
                    NodeList newList = new NodeList(node);
                    if (preList != null) {
                        preList.next = newList;
                    }
                    newList.last = preList;
                    newList.next = nextList;
                    nextList.last = newList;
                    if (headList == null) {
                        headList = newList;
                    }
                    heads.put(node, newList);
                }
            }
        }

        public boolean modifyHeadList(NodeList nodeList) {
            if (nodeList.isEmpty()) {
                if (headList == nodeList) {
                    headList = nodeList.next;
                    if (headList != null) {
                        headList.last = null;
                    }
                } else {
                    nodeList.last.next = nodeList.next;
                    if (nodeList.next != null) {
                        nodeList.next.last = nodeList.last;
                    }
                }
                return true;
            }
            return false;
        }

        public Node get(int key) {
            if (!records.containsKey(key)) {
                return null;
            }
            Node node = records.get(key);
            node.times++;
            NodeList nodeList = heads.get(node);
            nodeList.deleteNode(node);
            move(node, nodeList);
            return node;
        }
    }
}

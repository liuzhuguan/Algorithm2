/*
    给定一棵二叉树的头节点head，请返回最大搜索二叉子树的大小

    求解出左部信息，有部信息，左部头，右部头，左最大，右最小

    构造信息体 --- 左部头，右部头，左最大，右最小，size
 */
public class BiggestSubBSTInTree {

    public static class Node {
        private Node left;
        private Node right;
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class ReturnType {
        private int max;
        private int min;
        private int size;
        private Node head;

        public ReturnType(int max, int min, int size, Node head) {
            this.max = max;
            this.min = min;
            this.size = size;
            this.head = head;
        }
    }

    public static ReturnType process(Node head) {
        if (head == null) {
            return new ReturnType(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, null);
        }
        Node left = head.left;
        ReturnType leftType = process(left);
        Node right = head.right;
        ReturnType rightType = process(right);

        int includeIndex = 0;   //包括该头节点的最大节点数
        if (leftType.head == left && rightType.head == right
                && leftType.max < head.value && rightType.min > head.value
            ) {
            includeIndex = leftType.size + 1 + rightType.size;
        }
        int leftSize = leftType.size;
        int rightSize = rightType.size;
        int maxSize = Math.max(Math.max(leftSize, rightSize), includeIndex);

        Node maxHead = leftSize > rightSize ? leftType.head : rightType.head;
        if (maxSize == includeIndex) {
            maxHead = head;
        }
        return new ReturnType(Math.max(Math.max(leftType.max, rightType.max), head.value),
                Math.min(Math.min(leftType.min, rightType.min), head.value),
                maxSize,
                maxHead
                );
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);

        ReturnType process = process(head);
        System.out.println(process.size);
    }
}

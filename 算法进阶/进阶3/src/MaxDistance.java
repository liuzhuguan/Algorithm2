/*
        二叉树节点间距离的概念：二叉树一个节点到另一个节点间最短线路上的节点 数量，叫做两个节点间的距离。
    给定一棵二叉树的头节点head，请返回这棵二叉树上的最大距离。
 */
public class MaxDistance {

    public static class  Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int maxD(Node head) {
        int[] help = new int[1];
        return process(help, head);
    }

    public static int process(int[] arr, Node head) {
        if (head == null) {
            arr[0] = 0;
            return arr[0];
        }
        int leftMax = process(arr, head.left);
        int lmax = arr[0];
        int rightMax = process(arr, head.right);
        int rmax = arr[0];
        int curMax = rmax + lmax + 1;
        arr[0] = Math.max(rmax, lmax) + 1;
        return Math.max(Math.max(leftMax, rightMax), curMax);
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);
        head1.left.left.left = new Node(8);
        head1.right.left.right = new Node(9);
        System.out.println(maxD(head1));

        Node head2 = new Node(1);
        head2.left = new Node(2);
        head2.right = new Node(3);
        head2.right.left = new Node(4);
        head2.right.right = new Node(5);
        head2.right.left.left = new Node(6);
        head2.right.right.right = new Node(7);
        head2.right.left.left.left = new Node(8);
        head2.right.right.right.right = new Node(9);
        System.out.println(maxD(head2));
    }
}

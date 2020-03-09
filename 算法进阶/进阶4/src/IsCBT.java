import java.util.PriorityQueue;

/*
    是否为完全二叉树

    层序遍历 并判断
 */
public class IsCBT {

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left == null && node.right != null) {
                return false;
            } else if (node.left != null && node.right == null) {
                Node left = node.left;
                if (left.right.right != null || left.right.left != null || left.left.right != null || left.left.left != null) {
                    return false;
                }
            } else {
                return isCBT(node.left) && isCBT(node.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(2);
        Node node3 = new Node(2);
        Node node4 = new Node(2);

        head.left = node1;
        head.right = node2;
        head.left.left = node3;
        head.left.right = node4;
        boolean cbt = isCBT(head);
        System.out.println(cbt);
    }
}

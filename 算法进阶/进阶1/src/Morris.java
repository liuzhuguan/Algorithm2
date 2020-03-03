/*
    Morris遍历

    1) 来到当前节点，记为current 如果current无左孩子 , current向右移动 current = current.right
    2) 如果current有左孩子, 找到current左子树上最右的节点, 记为mosRight
        ①如果mosRight的right为null, 让其指向current. current向左移动 current = current.left
        ②如果mosRight指向了current, 让其指向null
 */
public class Morris {

    public static class Node {
        private Node left;
        private Node right;
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node current = head;
        Node mosRight = null;
        while (current != null) {
            mosRight = current.left;
            if (mosRight != null) {
                while (mosRight.right != null && mosRight.right != current) {
                    mosRight = mosRight.right;
                }
                if (mosRight.right == null) {
                    mosRight.right = current;
                    System.out.print(current.value + " ");
                    current = current.left;
                    continue;
                } else {
                    mosRight.right = null;
                }
            } else {
                System.out.print(current.value + " ");
            }
            current = current.right;
        }
        System.out.println();
    }

    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node current = head;
        Node mosRight = null;
        while (current != null) {
            mosRight = current.left;
            if (mosRight != null) {
                while (mosRight.right != null && mosRight.right != current) {
                    mosRight = mosRight.right;
                }
                if (mosRight.right == null) {
                    mosRight.right = current;
                    current = current.left;
                    continue;
                } else {
                    mosRight.right = null;
                }
            }
            System.out.print(current.value + " ");
            current = current.right;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node = new Node(2);
        Node node1 = new Node(3);
        Node node2 = new Node(4);
        Node node3 = new Node(5);
        Node node4 = new Node(6);
        Node node5 = new Node(7);

        head.left = node;
        head.right = node1;
        head.left.left = node2;
        head.left.right = node3;
        head.right.left = node4;
        head.right.right = node5;
        morrisIn(head);
        morrisPre(head);
    }
}

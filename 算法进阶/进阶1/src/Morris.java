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

    }

    public static void morrisIn(Node head) {

    }

    public static void morrisPos(Node head) {

    }
}

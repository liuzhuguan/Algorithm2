import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
    数组元素不重复
    每一子树头结点的值最大
 */
public class MaxTree {

    public static class Node {
        private Node left;
        private Node right;
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class MaxComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static Node getMaxTree(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new MaxComparator());
        LinkedList<Node> list = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        while (!queue.isEmpty()) {
            int num = queue.poll();
            Node cur = new Node(num);
            list.add(cur);
        }
        int index = 0;
        while (index >= 0 && index < list.size()) {
                Node cur = list.get(index);
                if ((index * 2 + 1) < list.size()) {
                    cur.left = list.get(index * 2 + 1);
                }
                if ((index * 2 + 2) < list.size()) {
                    cur.right = list.get(index * 2 + 2);
                }
                index++;
            }
        return list.get(0);
    }


    public static void main(String[] args) {
        int[] arr = {1,4,6,7,9};
        Node maxTree = getMaxTree(arr);

        System.out.println(maxTree.left.value);
    }
}

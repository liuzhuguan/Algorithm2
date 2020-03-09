/*
    给定一个数组，求子数组的最大异或和。
    一个数组的异或和为，数组中所有的数异或起来的结果。
 */
public class MaxEOR {

    public static class Node {
        public Node[] nexts = new Node[2];  //可以走 0  1
    }

    public static class NumTrie {
        public Node head = new Node();

        public void add(int num) {
            Node cur = head;
            for (int i = 31; i >= 0 ; i--) {
                int path = ((num >> i) & 1);
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        public int maxXor(int num) {
            Node node = head;
            int res = 0;
            for (int i = 31; i >= 0; i--) {
                int path = ((num >> i) & 1);
                int best = i == 31 ? path : path^1;
                best = node.nexts[best] == null ? best^1 : best;
                res |= (best ^ path) << i;
                node = node.nexts[best];
            }
            return res;
        }
    }

    public static int maxXorSubarray(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int eor = 0;
        NumTrie numTrie = new NumTrie();
        numTrie.add(0);
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            max = Math.max(max, numTrie.maxXor(eor));
            numTrie.add(eor);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1,5,8,4,2};
        int i = maxXorSubarray(arr);
        System.out.println(i);
    }
}

import java.util.ArrayList;
import java.util.List;

/*
      一个公司的上下节关系是一棵多叉树，这个公司要举办晚会，你作为组织者已经摸清了大家的心理：
    一个员工的直 接上级如果到场，这个员工肯定不会来。每个员工都有一个活跃度的值，决定谁来你
    会给这个员工发邀请函，怎么 让舞会的气氛最活跃？返回最大的活跃值。
        举例：
        给定一个矩阵来表述这种关系
        matrix = { 1,6 1,5 1,4 }
        这个矩阵的含义是：
        matrix[0] = {1 , 6}，表示0这个员工的直接上级为1,0这个员工自己的活跃度为6
        matrix[1] = {1 , 5}，表示1这个员工的直接上级为1（他自己是这个公司的最大boss）,1这个员工自己的活跃度 为5
        matrix[2] = {1 , 4}，表示2这个员工的直接上级为1,2这个员工自己的活跃度为4
        为了让晚会活跃度最大，应该让1不来，0和2来。最后返回活跃度为10
 */
public class MaxHappy {

    public static class Node {
        private int value;
        private List<Node> nexts;

        public Node(int value) {
            this.value = value;
            this.nexts = new ArrayList<>();
        }
    }

    public static int getMax(Node head) {
        ReturnData process = process(head);
        return Math.max(process.comeV, process.notComeV);
    }

    public static class ReturnData {
        private int comeV;
        private int notComeV;

        public ReturnData(int comeV, int notComeV) {
            this.comeV = comeV;
            this.notComeV = notComeV;
        }
    }

    public static ReturnData process(Node head) {
        int comeV = head.value;
        int notComeV = 0;
        for (int i = 0; i < head.nexts.size(); i++) {
            Node node = head.nexts.get(i);
            ReturnData returnData = process(node);
            comeV += returnData.notComeV;
            notComeV += Math.max(returnData.comeV, returnData.notComeV);
        }
        return new ReturnData(comeV, notComeV);
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        Node a = new Node(1);
        Node b = new Node(8);
        Node c = new Node(2);
        Node d = new Node(7);
        Node e = new Node(14);
        Node f = new Node(2);
        Node g = new Node(9);

        head.nexts.add(a);
        head.nexts.add(b);
        a.nexts.add(c);
        a.nexts.add(d);
        c.nexts.add(e);
        e.nexts.add(f);
        e.nexts.add(g);
        int max = getMax(head);
        System.out.println(max);
    }
}

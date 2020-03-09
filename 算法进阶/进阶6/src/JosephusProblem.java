public class JosephusProblem {

    public static class Node {
        public Node next;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getAliveOne(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node cur = head;
        int temp = 1;
        while (cur.next != head) {
            cur = cur.next;
            temp++;
        }
        int alive = getAlive(temp, m);      //拿到位置
        while (--alive != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }

    public static int getAlive(int num, int m) {
        if (num == 1) {
            return 1;
        }
        return (getAlive(num - 1, m) + m - 1) % num + 1;
    }

    public static void printCircularList(Node head) {
        if (head == null) {
            return;
        }
        System.out.print("Circular List: " + head.value + " ");
        Node cur = head.next;
        while (cur != head) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println("-> " + head.value);
    }


    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = head1;
        printCircularList(head1);
        head1 = getAliveOne(head1, 3);
        printCircularList(head1);

    }
}

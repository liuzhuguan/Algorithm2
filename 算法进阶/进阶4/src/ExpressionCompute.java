import java.util.LinkedList;

/*

        给定一个字符串str，str表示一个公式，公式里可能有整数、
     加减乘除符号和 左右括号，返回公式的计算结果。
        【举例】 str="48*((70-65)-43)+8*1"，返回-1816。 str="3+1*4"，返回7。
     str="3+(1*4)"，返回7。
        【说明】 1．可以认为给定的字符串一定是正确的公式，
     即不需要对str做公式有效性检 查。 2．如果是负数，就需要用括号括起来，
     比如"4*(-3)"。但如果负数作为公式的 开头或括号部分的开头，则可以没有括号，
     比如"-3*4"和"(-3*4)"都是合法的。 3．不用考虑计算过程中会发生溢出的情况
 */
public class ExpressionCompute {

    public static int getValue(String str) {
        return value(str.toCharArray(),0)[0];
    }

    public static int[] value(char[] chars, int i) {
        LinkedList<String> linkedList = new LinkedList<>();
        int pre = 0;
        int[] bra = null;
        while (i < chars.length && chars[i] != ')') {
            if (chars[i] <= '9' && chars[i] >= '0') {
                pre = pre * 10 + chars[i++] - '0';
            } else if (chars[i] != '(') {
                addNum(linkedList,pre);
                linkedList.addLast(String.valueOf(chars[i++]));
                pre = 0;
            } else {
                bra = value(chars, i + 1);
                pre = bra[0];
                i = bra[1];
            }
        }
        addNum(linkedList,pre);
        return new int[]{getNum(linkedList), i};
    }

    private static int getNum(LinkedList<String> linkedList) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!linkedList.isEmpty()) {
            cur = linkedList.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

    public static void addNum(LinkedList<String> list, int num) {
        if (!list.isEmpty()) {
            int cur = 0;
            String s = list.pollLast();
            if (s.equals("+") || s.equals("-")) {
                list.addLast(s);
            } else {
                cur = Integer.valueOf(list.pollLast());
                num = s.equals("*") ? cur * num : cur / num;
            }
        }
        list.addLast(String.valueOf(num));
    }

    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp));

        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp));

        exp = "10-5*3";
        System.out.println(getValue(exp));

        exp = "-3*4";
        System.out.println(getValue(exp));

        exp = "3+1*4";
        System.out.println(getValue(exp));

    }
}

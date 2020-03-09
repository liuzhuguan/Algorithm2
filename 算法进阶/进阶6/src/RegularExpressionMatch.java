/*
    字符串匹配问题

    【题目】 给定字符串str，其中绝对不含有字符'.'和'*'。再给定字符串exp，
     其中可以含有'.'或'*'，'*'字符不能是exp的首字符，并且任意两个 '*'字
     符不相邻。exp中的'.'代表任何一个字符，exp中的'*'表示'*' 的前一个字
     符可以有0个或者多个。请写一个函数，判断str是否能被 exp匹配。

     【举例】 str="abc"，exp="abc"，返回true。 str="abc"，exp="a.c"，
     exp中单个'.'可以代表任意字符，所以返回 true。 str="abcd"，
     exp=".*"。exp中'*'的前一个字符是'.'，所以可表示任 意数量
     的'.'字符，当exp是"...."时与"abcd"匹配，返回true。 str=""
     ，exp="..*"。exp中'*'的前一个字符是'.'，可表示任意数量 的'.'
     字符，但是".*"之前还有一个'.'字符，该字符不受'*'的影响，
     所以str起码有一个字符才能被exp匹配。所以返回false。
 */
public class RegularExpressionMatch {

    public static boolean isValid(char[] str, char[] exp) {
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '.' || str[i] == '*') {
                return false;
            }
        }
        for (int i = 0; i < exp.length; i++) {
            if (exp[i] == '*' && (i == 0 || exp[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMatch(String s, String e) {
        if (s == null && e == null) {
            return true;
        } else if (s == null || e == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] exp = e.toCharArray();
        return isValid(str, exp) && process(str, exp, 0, 0);
    }

    public static boolean process(char[] str, char[] exp, int i, int j) {
        if (j == exp.length) {
            return i == str.length;
        }
        if (j + 1 == exp.length || exp[j + 1] != '*') {
            return i < str.length && (exp[j] == str[i] || exp[j] == '.') && process(str, exp, i + 1, j + 1);
        }
        while (i < str.length && (exp[j] == str[i] || exp[j] == '.')) {
            if (process(str, exp, i, j + 2)) {
                return true;
            }
            i++;
        }
        return process(str, exp, i, j + 2);
    }

    public static boolean isMatchDP(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        if (!isValid(s, e)) {
            return false;
        }
        boolean[][] dp = initDPMap(s, e);
        for (int i = s.length - 1; i > -1; i--) {
            for (int j = e.length - 2; j > -1; j--) {
                if (e[j + 1] != '*') {
                    dp[i][j] = (s[i] == e[j] || e[j] == '.')
                            && dp[i + 1][j + 1];
                } else {
                    int si = i;
                    while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
                        if (dp[si][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (dp[i][j] != true) {
                        dp[i][j] = dp[si][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    private static boolean[][] initDPMap(char[] str, char[] exp) {
        int sLen = str.length;
        int eLen = exp.length;
        boolean[][] dp = new boolean[sLen + 1][eLen + 1];
        dp[sLen][eLen] = true;
        for (int i = eLen - 2; i > - 1 ; i--) {
            if (exp[i]  != '*' && exp[i + 1] == '*') {
                dp[sLen][i] =true;
            } else {
                break;
            }
        }
        if (eLen > 0 && sLen > 0) {
            if (exp[eLen - 1] == '.' || exp[eLen - 1] == str[sLen - 1]) {
                dp[sLen - 1][eLen - 1] = true;
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        String str = "abcccdefg";
        String exp = "ab.*d.*e.*";
        System.out.println(isMatch(str, exp));
    }
}

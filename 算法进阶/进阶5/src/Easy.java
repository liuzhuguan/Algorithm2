public class Easy {

    public static int ways(int K, int M, int N, int P) {
        if (K < 1 || K > N || M < 1 || M > N || P < 0) {
            return 0;
        }
        if (P == 0) {
            return K == M ? 1 : 0;
        }
        int res = 0;
        if (M == 1) {
            res = ways(K, M + 1, N, P - 1);
        } else if (M == N) {
            res = ways(K, M - 1, N, P - 1);
        } else {
            res = ways(K, M + 1, N, P - 1) + ways(K, M - 1, N, P - 1);
        }
        return res;
    }
}


public class st4 {
    public static int knapSack(final int weightCapacity, final int[] weights, final int[] values) {

        int[] dp = new int[weightCapacity + 1];

        for (int i = 0; i < values.length; i++) {
            for (int w = weightCapacity; w > 0; w--) {
                if (weights[i] <= w) {
                    dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
                }
            }
        }

        return dp[weightCapacity];
    }

    public static void main(String[] args) {
        // int[] weights = {2, 3, 4, 5};
        // int[] values = {3, 4, 5, 6};
        // int weightCapacity = 5;

        int[] weights = { 2, 4, 6, 9 };
        int[] values = { 10, 10, 12, 18 };
        int weightCapacity = 15;

        int result = knapSack(weightCapacity, weights, values);
        System.out.println(result);
    }
}

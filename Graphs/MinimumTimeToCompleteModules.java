/*
 * Problem: Minimum Time to Complete All Modules
 * Platform: Other
 * Difficulty: Medium
 *
 * Approach:
 * Build a directed graph from module dependencies and use Kahn's
 * algorithm for topological sorting. For each module, store the
 * earliest time at which it can be completed. The answer is the
 * maximum completion time among all modules.
 *
 * Time Complexity: O(n + m)
 * Space Complexity: O(n + m)
 */

import java.util.*;

class Solution {
    public int minTime(int[] duration, int[][] dependencies) {
        int n = duration.length;

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[n];

        for (int[] edge : dependencies) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            indegree[v]++;
        }

        long[] completion = new long[n];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                completion[i] = duration[i];
                q.offer(i);
            }
        }

        int processed = 0;
        long answer = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            processed++;

            answer = Math.max(answer, completion[u]);

            for (int v : graph.get(u)) {
                completion[v] = Math.max(
                    completion[v],
                    completion[u] + duration[v]
                );

                indegree[v]--;

                if (indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        if (processed != n) {
            return -1;
        }

        return (int) answer;
    }
}
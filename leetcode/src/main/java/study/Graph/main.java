package study.Graph;

import java.security.PrivateKey;
import java.util.*;

public class main {
    /**
     * LCR 110. 所有可能的路径
     * @param graph
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> arrayList = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int len = graph.length;
        arrayList.add(0);
        dfs(graph, 0);
        return result;

    }
    void dfs(int[][] graph, int n){
        if (n == graph.length-1){
            result.add(new ArrayList<>(arrayList));
        }
        for (int i = 0; i < graph[n].length; i++) {
            arrayList.add(graph[n][i]);
            dfs(graph, graph[n][i]);
            arrayList.remove(arrayList.size()-1);
        }
    }

    /**
     * 200. 岛屿数量
     * @param grid
     * @return
     */
    public static int numIslands(char[][] grid) {
        int len = grid.length;
        int lon = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        int count = 0;

        Deque<String> set = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < lon; j++) {
                if (grid[i][j] == '1'){
                    set.add(i + "+" + j);
                }
            }
        }

        while (!set.isEmpty()){
            queue.addLast(paraseSet(set.removeFirst()));
            while (!queue.isEmpty()){
                int[] x = queue.removeFirst();
                int row = x[0];
                int col = x[1];
                grid[row][col] = '0';

                if (row - 1 >= 0 && grid[row-1][col] == '1'){
                    queue.addLast(new int[]{row-1, col});
                    set.remove(row-1 + "+" + col);
                    grid[row-1][col] = '0';
                }
                if (col - 1 >= 0 && grid[row][col-1] == '1'){
                    queue.addLast(new int[]{row, col-1});
                    set.remove(row + "+" + (col-1));
                    grid[row][col-1] = '0';

                }
                if (row + 1 < len && grid[row+1][col] == '1'){
                    queue.addLast(new int[]{row+1, col});
                    set.remove((row+1) + "+" + col);
                    grid[row+1][col] = '0';
                }
                if (col + 1 < lon && grid[row][col + 1] == '1'){
                    queue.addLast(new int[]{row, col + 1});
                    set.remove(row + "+" + (col+1));
                    grid[row][col + 1] = '0';
                }
            }
            count ++;
        }


        return count;
    }

    private static int[] paraseSet(String s){
        String[] split = s.split("\\+");
        return new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])};
    }



    public static void main(String[] args) {
        char[][] grid = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        System.out.println(numIslands(grid));;
    }


}

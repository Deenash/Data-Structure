import java.util.HashSet;
import java.util.Set;

public class DistinctIsland
{

    public static int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, "start:");
                    grid[i][j] = 0;
                    System.out.println(sb.toString());
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }

    private static void dfs(int[][] grid, int i, int j, StringBuilder sb, String dir) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] == 0) return;
        sb.append(dir);
        grid[i][j] = 0;
        dfs(grid, i-1, j, sb, "u");
        dfs(grid, i+1, j, sb, "d");
        dfs(grid, i, j-1, sb, "l");
        dfs(grid, i, j+1, sb, "r");
        sb.append("#");
    }

    public static void main (String[] args)
    {
        int[][] mat = { { 1, 1, 0, 1, 1 }, 
                        { 1, 0, 0, 0, 0 }, 
                        { 0, 0, 0, 1, 1 }, 
                        { 1, 1, 0, 1, 1 } };
        System.out.println(numDistinctIslands(mat));
    }
}



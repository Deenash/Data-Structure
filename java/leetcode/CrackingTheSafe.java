package com.tool.java.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CrackingTheSafe
{
    Set<String> seen;
    StringBuilder ans;

    public String crackSafe(int n, int k) {
        if (n == 1 && k == 1) return "0";
        seen = new HashSet();
        ans = new StringBuilder();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n-1; ++i)
            sb.append("0");
        String start = sb.toString();

        dfs(start, k);
        ans.append(start);
        return new String(ans);
    }

    public void dfs(String node, int k) {
        for (int x = 0; x < k; ++x) {
            String nei = node + x;
            if (!seen.contains(nei)) {
                seen.add(nei);
                dfs(nei.substring(1), k);
                ans.append(x);
            }
        }
    }
    
    public static void main (String[] args)
    {
        CrackingTheSafe c = new CrackingTheSafe();
        c.crackSafe(3,4);
    }
}

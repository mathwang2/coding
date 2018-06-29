package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2 {
    static class Cell{
        int x, y, val;
        Cell(int x, int y, int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    public static int trapRainWater(int[][] heightMap) {
        if(heightMap == null || heightMap.length <= 2 || heightMap[0].length <= 2){
            return 0;
        }

        int m = heightMap.length, n = heightMap[0].length;
        PriorityQueue<Cell> pq = new PriorityQueue<>(1, new Comparator<Cell>(){
            public int compare(Cell a, Cell b){
                return a.val - b.val;
            }
        });

        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            pq.offer(new Cell(i, 0, heightMap[i][0]));
            pq.offer(new Cell(i, n-1, heightMap[i][n-1]));
            visited[i][0] = true;
            visited[i][n-1] = true;
        }

        for(int j = 0; j < n; j++){
            pq.offer(new Cell(0, j, heightMap[0][j]));
            pq.offer(new Cell(m-1, j, heightMap[m-1][j]));
            visited[0][j] = true;
            visited[m-1][j] = true;
        }

        int[][] dirs = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int res = 0;
        while(!pq.isEmpty()){
            Cell c = pq.poll();
            for(int[] dir : dirs){
                int nx = c.x + dir[0];
                int ny = c.y + dir[1];
                if(nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]){
                    if(heightMap[nx][ny] < c.val){
                        res += c.val - heightMap[nx][ny];
                    }
                    visited[nx][ny] = true;
                    pq.offer(new Cell(nx, ny, Math.max(heightMap[nx][ny], c.val)));
                }
            }
        }

        return res;
    }


}

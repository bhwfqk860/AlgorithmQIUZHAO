package algorithm.week03;

import java.util.HashSet;
import java.util.Set;

public class RobotSim {
    public int robotSim(int[] commands, int[][] obstacles) {
        // North, East, South, West
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int x = 0, y = 0, di = 0;

        Set<String> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "," + obstacle[1]);
        }

        int result = 0;
        for (int cmd : commands) {
            if (cmd == -1) {
                di = (di + 1) % 4;
            } else if (cmd == -2) {
                di = (di + 3) % 4;
            } else {
                for (int k = 0; k < cmd; k++) {
                    int nx = x + dx[di];
                    int ny = y + dy[di];
                    if (!obstacleSet.contains(nx + "," + ny)) {
                        x = nx;
                        y = ny;
                        result = Math.max(result, x * x + y * y);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] commands = {4, -1, 4, -2, 4};
        int[][] obstacles = {{2, 4}};
        new RobotSim().robotSim(commands, obstacles);
    }
}

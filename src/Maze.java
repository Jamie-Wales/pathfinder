import java.util.Arrays;


public class Maze {
    enum WALLTYPE {
        PATH,
        WALL,
        START,
        END
    }

    private boolean start = false;
    private boolean end = false;
    private WALLTYPE[][] map;

    Maze(int rows, int cols) {
        map = new WALLTYPE[rows][cols];
        for (WALLTYPE[] item : map) {
            Arrays.fill(item, WALLTYPE.PATH);
        }

    }

    public boolean needsStart() {
        return start;
    }

    public boolean needsEnd() {
        return end;
    }

    public void setPath(int rows, int cols) {
        start = true;
        map[rows][cols] = WALLTYPE.START;
    }

    public void setEnd(int rows, int cols) {
        end = true;
        map[rows][cols] = WALLTYPE.END;
    }

    public void removePath(int rows, int cols) {
        start = false;
        map[rows][cols] = WALLTYPE.PATH;
    }

    public void removeEnd(int rows, int cols) {
        end = false;
        map[rows][cols] = WALLTYPE.PATH;
    }

    public boolean isWall(int row, int cols) {
        return map[row][cols] == WALLTYPE.WALL;
    }

    public boolean isStart(int row, int cols) {
        return WALLTYPE.START.equals(map[row][cols]);
    }

    public boolean isEnd(int row, int cols) {
        return WALLTYPE.END.equals(map[row][cols]);
    }

    public void setNode(int rows, int cols, int nodeType) throws Exception {
        if (nodeType == 1) {
            map[rows][cols] = WALLTYPE.WALL;
        } else if (nodeType == 0) {
            map[rows][cols] = WALLTYPE.PATH;
        } else {
            throw new RuntimeException("incorrect nodeType");
        }
    }




}

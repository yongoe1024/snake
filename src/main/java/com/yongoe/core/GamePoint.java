package com.yongoe.core;

import java.util.Objects;

/**
 * 坐标
 *
 * @author yongoe
 * @since 2023/1/1
 */
public class GamePoint {
    private int x;
    private int y;

    /**
     * 地图内随机坐标
     */
    public static GamePoint createPoint() {
        int x = (int) (GameConstant.xNum * Math.random());
        int y = (int) (GameConstant.yNum * Math.random());
        return new GamePoint(x, y);
    }

    public boolean equals(GamePoint gamePoint) {
        if (this == gamePoint) return true;
        if (gamePoint == null || getClass() != gamePoint.getClass()) return false;
        return x == gamePoint.x && y == gamePoint.y;
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        // 解决穿墙
        if (x < 0)
            this.x = GameConstant.xNum + x;
        else if (x == GameConstant.xNum)
            this.x = 0;
        else
            this.x = x;
    }

    public void setY(int y) {
        // 解决穿墙
        if (y < 0)
            this.y = GameConstant.yNum + y;
        else if (y == GameConstant.yNum)
            this.y = 0;
        else
            this.y = y;
    }

    public GamePoint() {
    }

    public GamePoint(int x, int y) {
        setX(x);
        setY(y);
    }
}
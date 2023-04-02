package com.yongoe.core;

/**
 * 食物
 *
 * @author yongoe
 * @since 2023/1/1
 */
public class Food {
    private GamePoint gamePoint;

    public Food() {
        this.gamePoint = GamePoint.createPoint();
    }

    /**
     * 生成新的食物
     */
    public void createFood() {
        this.gamePoint = GamePoint.createPoint();
    }

    public GamePoint getGamePoint() {
        return gamePoint;
    }
}

package com.yongoe.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 蛇身
 * <p>
 * <p>
 * 0_______ x
 * ｜
 * ｜
 * ｜
 * y
 *
 * @author yongoe
 * @since 2023/1/1
 */
public class Snake {
    private List<GamePoint> list;
    private Direction direction;

    /**
     * 创建一个随机坐标的蛇和方向
     */
    public Snake() {
        this.list = new ArrayList<>();
        list.add(GamePoint.createPoint());
        this.direction = GameConstant.startDir;
    }

    /**
     * 吃到食物
     */
    public void eat(Food food) {
        int x = food.getGamePoint().getX();
        int y = food.getGamePoint().getY();
        GamePoint gamePoint = this.addFood(x, y);
        list.add(gamePoint);
    }

    /**
     * 判断能否吃到食物
     */
    public boolean isEat(Food food) {
        GamePoint last = this.getSnakeHead();
        GamePoint gamePoint = food.getGamePoint();
        return last.equals(gamePoint);
    }

    /**
     * 在蛇头前边加一个点，作为吃food的增加
     */
    private GamePoint addFood(int x, int y) {
        switch (this.direction) {
            case up:
                y -= 1;
                break;
            case down:
                y += 1;
                break;
            case left:
                x -= 1;
                break;
            case right:
                x += 1;
                break;
        }
        return new GamePoint(x, y);
    }

    /**
     * 移动，从末尾移动到头部
     */
    public void move() {
        switch (this.direction) {
            case up:
                update(0, -1);
                break;
            case down:
                update(0, 1);
                break;
            case left:
                update(-1, 0);
                break;
            case right:
                update(1, 0);
                break;
        }
    }

    public int lenght() {
        return this.list.size();
    }

    /**
     * 蛇头的点
     */
    public GamePoint getSnakeHead() {
        return list.get(list.size() - 1);
    }

    public List<GamePoint> getList() {
        return list;
    }

    /**
     * 删除蛇尾，蛇头加一个点
     */
    private void update(int x, int y) {
        GamePoint oldGamePoint = list.get(list.size() - 1);
        GamePoint newGamePoint = new GamePoint();
        newGamePoint.setX(oldGamePoint.getX() + x);
        newGamePoint.setY(oldGamePoint.getY() + y);
        list.add(newGamePoint);
        list.remove(0);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}

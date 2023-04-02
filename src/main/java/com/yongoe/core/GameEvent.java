package com.yongoe.core;

import java.awt.*;
import java.util.List;

/**
 * 游戏事件
 *
 * @author yongoe
 * @since 2023/1/1
 */
public class GameEvent {
    private Snake snake;
    private Food food;

    /**
     * 初始化
     */
    public GameEvent() {
        this.snake = new Snake();
        this.food = new Food();
    }


    /*
      画出大号字体
    public void drawTitle(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", Font.BOLD, GameConstant.fontSizeBig));
        g.setColor(new Color(255, 255, 255, 128));
        g.fillRect(0, 0, GameConstant.maxWidth, GameConstant.maxHeight);
        g.drawString("空格开始游戏", GameConstant.maxWidth / 5, GameConstant.maxHeight / 3);
    }
     */

    /**
     * 画出小号字体
     */
    public void drawGameInfo(Graphics g) {
        this.drawFont(g, "得分：" + (snake.lenght() - 1), -2);
        this.drawFont(g, "速度：" + GameConstant.speed, -1);
        String str = snake.getSnakeHead().getX() + "," + snake.getSnakeHead().getY();
        this.drawFont(g, "头坐标: " + str, 0);
        this.drawFont(g, "空格键：开始/暂停", 1);
        String str2 = "食物：" + food.getGamePoint().getX() + " ," + food.getGamePoint().getY();
        this.drawFont(g, str2, 2);
    }

    /**
     * 画地图
     */
    public void drawMap(Graphics g) {
        //绘制静态界面
        g.setColor(GameConstant.mapColor);
        g.fillRect(GameConstant.xOffset, GameConstant.yOffset, GameConstant.mapWidth, GameConstant.mapHeight);
        g.setColor(Color.DARK_GRAY);
        // 画网格线 竖线
        for (int i = 0; i < GameConstant.xNum; i++) {
            g.drawLine(GameConstant.xOffset + i * GameConstant.chunkWidth,
                    GameConstant.yOffset,
                    GameConstant.xOffset + i * GameConstant.chunkWidth,
                    GameConstant.yOffset + GameConstant.mapHeight);
        }
        // 画网格线 横线
        for (int i = 0; i < GameConstant.yNum; i++) {
            g.drawLine(GameConstant.xOffset,
                    GameConstant.yOffset + i * GameConstant.chunkHeigh,
                    GameConstant.xOffset + GameConstant.mapWidth,
                    GameConstant.yOffset + i * GameConstant.chunkHeigh);
        }
    }

    /**
     * 画食物
     */
    public void drawFood(Graphics g) {
        g.setColor(GameConstant.foodColor);
        int height = GameConstant.chunkHeigh;
        int width = GameConstant.chunkWidth;
        g.fillOval(GameConstant.xOffset + width * food.getGamePoint().getX(),
                GameConstant.yOffset + height * food.getGamePoint().getY(),
                width, height);
    }

    /**
     * 画蛇
     */
    public void drawSnake(Graphics g) {
        List<GamePoint> list = snake.getList();
        int width = GameConstant.chunkWidth;
        int height = GameConstant.chunkHeigh;
        for (int i = 0; i < snake.lenght(); i++) {
            int x = list.get(i).getX();
            int y = list.get(i).getY();
            // 蛇头
            if (i == snake.lenght() - 1) {
                g.setColor(GameConstant.snakeHeadColor);
                g.fillOval(GameConstant.xOffset + width * x,
                        GameConstant.yOffset + height * y,
                        width, height);
            } else {
                //蛇身
                Color color1 = GameConstant.snakeColor;
                Color color2 = new Color((int) (255 * Math.random()), (int) (255 * Math.random()), (int) (255 * Math.random()));
                // 判断是否要 彩色身体
                if (GameConstant.changeColor) {
                    g.setColor(color2);
                } else {
                    g.setColor(color1);
                }
                // 蛇身 四周宽度减小 10%
                g.fillRect(GameConstant.xOffset + width * x + (int) (width * 0.1),
                        GameConstant.yOffset + height * y + (int) (width * 0.1),
                        width - (int) (width * 0.2), height - (int) (width * 0.2));
            }
        }
    }


    /**
     * 绘制一行文字
     *
     * @param g   Graphics
     * @param str 文字
     * @param row 距离窗口中心位置的行数
     */
    private void drawFont(Graphics g, String str, int row) {
        g.setColor(Color.red);
        g.setFont(new Font("微软雅黑", Font.BOLD, GameConstant.fontSizeSmall));
        g.drawString(str,
                GameConstant.xOffset + GameConstant.mapWidth + 10,
                GameConstant.yOffset + GameConstant.mapHeight / 2 + GameConstant.fontSizeSmall * row);
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }
}

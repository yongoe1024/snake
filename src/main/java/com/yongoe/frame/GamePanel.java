package com.yongoe.frame;

import com.yongoe.core.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    GameEvent gameEvent;
    // 游戏状态
    boolean status = false;
    boolean lock = false;   // 按键锁，防止按的过快

    public GamePanel() {
        gameEvent = new GameEvent();
        //获得焦点和键盘事件
        this.setFocusable(true);//焦点事件，专注游戏窗口
        this.addKeyListener(this);//键盘事件，当前类中实现了，所以用this
        new Thread(() -> {
            while (true) {
                try {
                    this.repaint();// 绘制
                    this.lock = false;
                    Thread.sleep(GameConstant.speed);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    /**
     * 每帧执行代码
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(GameConstant.background);
        gameEvent.drawMap(g);
        if (status) {
            Snake snake = gameEvent.getSnake();
            Food food = gameEvent.getFood();
            snake.move();
            if (snake.isEat(food)) {
                snake.eat(food);
                food.createFood();
            }
        }
        gameEvent.drawFood(g);
        gameEvent.drawSnake(g);
        gameEvent.drawGameInfo(g);
    }


    /**
     * 键盘监听事件
     */
    @Override
    public void keyPressed(KeyEvent e) {
        Snake snake = gameEvent.getSnake();
        Direction d = snake.getDirection();
        if (!lock) {
            int keycode = e.getKeyCode();//获取键盘按键
            if (keycode == KeyEvent.VK_SPACE) {
                // 暂停，开始游戏
                this.status = !this.status;
                return;
            }
            if (keycode == KeyEvent.VK_W && d != Direction.up && d != Direction.down) {
                snake.setDirection(Direction.up);
            } else if (keycode == KeyEvent.VK_D && d != Direction.right && d != Direction.left) {
                snake.setDirection(Direction.right);
            } else if (keycode == KeyEvent.VK_S && d != Direction.down && d != Direction.up) {
                snake.setDirection(Direction.down);
            } else if (keycode == KeyEvent.VK_A && d != Direction.left && d != Direction.right) {
                snake.setDirection(Direction.left);
            } else
                return;
        }
        //加锁 防止连按
        this.lock = true;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
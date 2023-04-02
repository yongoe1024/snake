package com.yongoe.core;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * 游戏常量
 *
 * @author yongoe
 * @since 2023/1/1
 */
public class GameConstant {
    // * 手动设定的变量

    // 当前游戏速度
    public static int speed = 200;
    // 初始速度
    public static int startSpeed = 200;

    // 网格数量
    public static int xNum = 20;
    public static int yNum = 20;
    // 蛇头初始方向
    public static Direction startDir = Direction.right;

    public static Color mapColor = Color.black;
    public static Color background = Color.white;
    // 蛇身yanse
    public static Color snakeColor = Color.white;
    // 蛇头颜色
    public static Color snakeHeadColor = Color.red;
    // 食物颜色
    public static Color foodColor = Color.yellow;


    // 窗口总长
    static public int maxWidth;
    static public int maxHeight;
    // 地图距离窗口左上角的偏移
    public static int xOffset;
    public static int yOffset;
    // 地图的长宽
    public static int mapWidth;
    public static int mapHeight;
    // 小格子长宽
    public static int chunkWidth;
    public static int chunkHeigh;
    // 大字的size
    public static int fontSizeBig;
    // 小字的size
    public static int fontSizeSmall;
    public static boolean changeColor;

    public static void init(String path) {
        Properties prop = new Properties();
        try {
            InputStream inputStream1 = Files.newInputStream(Paths.get(path));
            prop.load(inputStream1);
            startSpeed = Integer.parseInt(prop.getProperty("startSpeed"));
            speed = startSpeed;
            xNum = Integer.parseInt(prop.getProperty("xNum"));
            yNum = Integer.parseInt(prop.getProperty("yNum"));
            changeColor = Boolean.parseBoolean(prop.getProperty("changeColor"));
            mapColor = inv(prop.getProperty("mapColor"));

            background = inv(prop.getProperty("background"));
            snakeColor = inv(prop.getProperty("snakeColor"));
            snakeHeadColor = inv(prop.getProperty("snakeHeadColor"));
            foodColor = inv(prop.getProperty("foodColor"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rect = ge.getMaximumWindowBounds();
        maxWidth = rect.width;
        maxHeight = rect.height;

        // 计算上下留出的距离
        xOffset = (int) (maxHeight * 0.1);
        yOffset = (int) (maxHeight * 0.1);
        // 计算 网格大小
        chunkWidth = (maxHeight - yOffset * 2) / xNum;
        chunkHeigh = (maxHeight - yOffset * 2) / yNum;
        // 网格长度 * 网格数 = 地图长度
        mapWidth = chunkWidth * xNum;
        mapHeight = chunkHeigh * yNum;

        fontSizeBig = (int) (maxHeight * 0.08);
        fontSizeSmall = (int) (maxHeight * 0.03);
    }

    private static Color inv(String name) {
        try {
            for (Field field : Color.class.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals(name)) {
                    Color o = (Color) field.get(name);
                    return o;
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return Color.white;
    }


}

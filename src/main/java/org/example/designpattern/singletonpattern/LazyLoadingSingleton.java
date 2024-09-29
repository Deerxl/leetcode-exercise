package org.example.designpattern.singletonpattern;

/**
 * 懒加载模式，不支持多线程
 * @author xiaolu
 * @date 2024/9/29 10:24
 */
public class LazyLoadingSingleton {
    private static LazyLoadingSingleton instance;

    private LazyLoadingSingleton() {

    }

    public static synchronized LazyLoadingSingleton getInstance() {
        if (instance == null) {
            instance = new LazyLoadingSingleton();
        }
        return instance;
    }

    public void printMsg() {
        System.out.println("LazyLoadingSingleton: Hello World!");
    }

    public static void main(String[] args) {
        LazyLoadingSingleton instance1 = LazyLoadingSingleton.getInstance();
        instance1.printMsg();
    }
}

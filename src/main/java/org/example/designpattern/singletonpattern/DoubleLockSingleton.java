package org.example.designpattern.singletonpattern;

/**
 * 双锁机制，安全且在多线程情况下能保持高性能。
 * @author xiaolu
 * @date 2024/9/29 10:24
 */
public class DoubleLockSingleton {

    private static volatile DoubleLockSingleton instance;

    private DoubleLockSingleton() {

    }

    public static DoubleLockSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleLockSingleton.class) {
                instance = new DoubleLockSingleton();
            }
        }
        return instance;
    }

    public void printMsg() {
        System.out.println("DoubleLockSingleton: Hello world!");
    }

    public static void main(String[] args) {
        DoubleLockSingleton instance = DoubleLockSingleton.getInstance();
        instance.printMsg();
    }
}

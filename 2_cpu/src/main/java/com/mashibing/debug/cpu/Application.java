package com.mashibing.debug.cpu;


public class Application {

    public static void main(String[] args) {
        try {
            //模拟CPU占用率
            cpu50(Integer.parseInt(args[0]));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void cpu50(int num) {
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                while (true) {
                    long time_start;

                    int fulltime = 100;
                    int runtime = 50;
                    while (true) {
                        time_start = System.currentTimeMillis();
                        while ((System.currentTimeMillis() - time_start) < runtime) {
                        }
                        try {
                            Thread.sleep(fulltime - runtime);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            }).start();
        }


    }
}

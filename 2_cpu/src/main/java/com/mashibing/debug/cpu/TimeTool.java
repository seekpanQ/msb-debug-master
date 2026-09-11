package com.mashibing.debug.cpu;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>计算方法执行时间工具类-粗略测试方法执行时间</p>
 *
 * @author sunzhiqiang
 * @date 2021/9/27
 */
public final class TimeTool {

    private TimeTool() {

    }

    public static void check(String title, Task task) {
        if (task == null) {
            return;
        }
        title = (title == null) ? "" : ("【" + title + "】");
        System.out.println(title);
        long begin = System.currentTimeMillis();
        task.execute();
        long end = System.currentTimeMillis();
        System.out.println("结束：" + new SimpleDateFormat("HH::mm:ss.SSS").format(new Date()));
        double delta = (end - begin);
        System.out.println("耗时：" + delta + "毫秒");
        System.out.println("-------------------------");

    }

    /**
     * 执行任务
     */
    public interface Task {
        void execute();
    }
}

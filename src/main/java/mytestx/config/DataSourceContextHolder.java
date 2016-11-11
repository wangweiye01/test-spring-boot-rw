package mytestx.config;

/**
 * Created by Administrator on 2016/11/11.
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read() {
        local.set(String.valueOf(DataSourceType.read.getType()));
        System.out.println("正在设置读数据源---------------------》");
    }

    /**
     * 写只有一个库
     */
    public static void write() {
        local.set(String.valueOf(DataSourceType.write.getType()));
    }

    public static String getJdbcType() {
        return local.get();
    }
}

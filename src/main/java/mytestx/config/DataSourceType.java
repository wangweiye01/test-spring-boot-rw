package mytestx.config;



/**
 * Created by Administrator on 2016/11/11.
 */
public enum DataSourceType {
    read("read", "从库"), write("write", "主库");
    private String type;
    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }


    public Object getType() {
        return type;
    }
}

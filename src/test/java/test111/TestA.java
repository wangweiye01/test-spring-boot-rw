package test111;

/**
 * Created by Administrator on 2016/11/11.
 */
public class TestA extends Father {
    @Override
    public void test() {
        System.out.println("testA...................");
    }

    public static void main(String[] args) {
         TestA testA = new TestA();
    }
}

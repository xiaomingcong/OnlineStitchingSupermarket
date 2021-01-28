import com.xmc.config.cas.SysUser;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/1/11 8:08 下午
 * Version 1.0
 */
public class ThreadLocalTest {
    private final ThreadLocal<SysUser> tt = new ThreadLocal<>(){
        @Override
        protected SysUser initialValue() {
            return new SysUser("xiaomingcong");
        }
    };
    public static void main(String[] args) {
        ThreadLocalTest t = new ThreadLocalTest();
        t.test1();
        t.test2();
    }

    private void test1(){
        SysUser sysUser = tt.get();
        System.out.println(sysUser.getUsername());
        tt.set(new SysUser("123"));
    }

    private void test2(){

        SysUser sysUser = tt.get();

        System.out.println(sysUser.getUsername());
    }
}

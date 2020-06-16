import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.dao.UserMapper;
import com.qf.popj.User;
import com.qf.util.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MybatisTest01 {

    private static UserMapper userMapper;
    static {
        userMapper = MyBatisUtils.getMapper(UserMapper.class);
    }

    //查询所有用户
    @Test
    public void testFindAll() throws Exception{
        //1.获取读取MyBatis配置文件的流对象
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //2.构建SqlSession连接对象的工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //3.通过工厂获得连接对象
        SqlSession sqlSession = factory.openSession();
        //4.通过连接对象获取接口实现类对象
        UserMapper userMapper1 = sqlSession.getMapper(UserMapper.class);

        List<User> users = userMapper1.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    //通过姓名查询用户 --- sql语句片段 + 动态sql：where
    @Test
    public void testFindByUserName(){
        User user = userMapper.findByUserName("alex");
        System.out.println(user);
    }

    //通过ID修改用户 --- 动态sql：set
    @Test
    public void testUpdateUser(){
        User user = new User(2,"json",null,null,"杭州");
        int i = userMapper.updateUser(user);
        System.out.println(i +"---"+ userMapper.findByUserName("json"));
        MyBatisUtils.commit();
    }

    //通过姓名和性别查询用户 --- 动态sql：trim + where
    @Test
    public void testFindByUserNameAndSex_trim(){
        User user = userMapper.findByUserNameAndSex_trim("alex",'男');
        System.out.println(user);
    }

    //通过ID修改用户 --- 动态sql：trim + set
    @Test
    public void testUpdateUser_trim(){
        User user = new User(2,"will",null,'男',"广州" );
        int i = userMapper.updateUser_trim(user);
        System.out.println(i + "---" +userMapper.findByUserName("will"));
        MyBatisUtils.commit();
    }

    //通过ID查询用户 --- 动态sql：foreach
    @Test
    public void testFindAllById(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<User> users = userMapper.findAllById(list);
        for (User user : users) {
            System.out.println(user);
        }
    }

    //PageHelper提供的静态方法设置分页查询条件
    @Test
    public void testPageHelper(){
        PageHelper.startPage(1,2); //设置分页条件，当前页数，每页记录数
        List<User> list = userMapper.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(list); //将数据导入PageInfo对象的构造方法，生成分页后的对象
        System.out.println(pageInfo);
    }

    //通过地址查询用户 --- mybatis注解操作
    @Test
    public void testFindByAddress(){
        List<User> users = userMapper.findByAddress("郑州");
        for (User user : users) {
            System.out.println(user);
        }
    }


}


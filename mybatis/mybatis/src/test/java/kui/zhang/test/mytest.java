package kui.zhang.test;

import kui.zhang.dao.IuserDao;
import kui.zhang.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class mytest {
    private IuserDao userdao;
    private SqlSession sqlSession;
    private InputStream in;
    @Before//用于方法测试之前执行
    public void init() throws IOException {
        //1 .创建配置文件Resources
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建工厂制造者SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.创建工厂build
        SqlSessionFactory factory = builder.build(in);
        //4.创建生产者SqlSession  openSessoin()
        sqlSession = factory.openSession();
        //5.创建生产者dao代理对象getMapper()
        userdao = sqlSession.getMapper(IuserDao.class);
    }
    @After//用于测试方法测试之后执行
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }
    @Test
public void test1(){
        List<User> users = userdao.find();
        for (User user : users) {
            System.out.println(user);
        }
    }
}

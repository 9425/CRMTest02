package Test;

import domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test01 {
    public static void main(String[] args) {
        String resource="mybatis-config.xml";
        //输入流
        InputStream inputStream=null;
        try {
            //此处将需要用到数据源导入
            inputStream= Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        * SqlSessionFactiontoryBuilder:SqlSessionFactor的建造者，通过该建造者
        * 对象调用建造方法，为我们创建一个SqlSessionFactory对象
        *
        *
        * */
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //以后所有的操作，都是使用SqlSession对象session来完成
        //例如增删改查，处理事务等等，都是统一使用session对象来完成
        //当然，SqlSession的产生当然是需要SqlSessionFactory来完成
        SqlSession session=sqlSessionFactory.openSession();
        /*
        * 现在我们来完成我们的需求，根据id查单条
        * 如果取得的是单挑记录，我们使用selectOne方法
        * 参数1：根据命名空间.sqlId的形式找到我们需要使用的sql语句
        * 参数2：我们要为sql语句中传递参数
        * */
        Student student01=session.selectOne("test1.getById01","A001");
        System.out.println(student01);
        session.close();
    }
}

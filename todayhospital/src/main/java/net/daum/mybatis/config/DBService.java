package net.daum.mybatis.config;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatis SQL 세션 팩토리 제공 클래스
 * - 외부 db.properties와 mybatis-config.xml 병합 로딩
 * - SqlSessionFactory 싱글턴 제공
 */
public class DBService {

    private static final String MYBATIS_CONFIG_FILE = "mybatis-config.xml";
    private static final String DB_PROPERTIES_FILE = "db.properties"; 

    private static SqlSessionFactory factory;

    static {
        try (
                Reader configReader = Resources.getResourceAsReader(MYBATIS_CONFIG_FILE);
                Reader propReader = new InputStreamReader(
                        Thread.currentThread().getContextClassLoader().getResourceAsStream(DB_PROPERTIES_FILE))) {
            Properties props = new Properties();
            props.load(propReader);

            factory = new SqlSessionFactoryBuilder().build(configReader, props);

        } catch (Exception e) {
            System.err.println("[ERROR] SqlSessionFactory 초기화 실패: " + e.getMessage());
            throw new RuntimeException("DBService 초기화 실패", e);
        }
    }

    public static SqlSessionFactory SqlSessionFactory() {
        return factory;
    }
}
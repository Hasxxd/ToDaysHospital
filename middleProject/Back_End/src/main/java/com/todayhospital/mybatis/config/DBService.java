package com.todayhospital.mybatis.config;

import java.io.FileReader;
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

    private static SqlSessionFactory factory;

    static {
        try {
            // MyBatis 설정 파일 (resources 경로 기준)
            String configPath = "mybatis.config.xml";
            Reader configReader = Resources.getResourceAsReader(configPath);

            // 외부 환경 설정 파일 경로
            String externalPropPath = "C:/Users/PC-20/OneDrive/development_project/A_Daedeok/WorkSpace/db.properties";

            // 가장 보안성 및 신뢰성이 우수한 방식 : 시스템 프로퍼티의 디렉토리를 환경 변수화(세팅 잡을때 귀찮음)
            // String externalPropPath = System.getProperty("user.dir") + "/db.properties";

            Properties props = new Properties();
            try (FileReader propReader = new FileReader(externalPropPath)) {
                props.load(propReader);
            }

            // SqlSessionFactory 생성 (프로퍼티 주입 포함)
            factory = new SqlSessionFactoryBuilder().build(configReader, props);

            configReader.close();

        } catch (Exception e) {
            System.err.println("MyBatis SqlSessionFactory 초기화 실패: " + e.getMessage());
            throw new RuntimeException("DBService 초기화 실패", e);
        }
    }

    public static SqlSessionFactory SqlSessionFactory() {
        return factory;
    }
}

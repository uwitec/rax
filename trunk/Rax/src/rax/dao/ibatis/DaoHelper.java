package rax.dao.ibatis;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public final class DaoHelper {

    private static SqlMapClient sqlMap;

    static {
        try {
            String resource = "sqlmap-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (Exception ex) {

        }
    }

    public static SqlMapClient getSqlMapper() {
        return sqlMap;
    }

}

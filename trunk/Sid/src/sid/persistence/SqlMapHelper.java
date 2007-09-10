package sid.persistence;

import java.io.IOException;
import java.io.Reader;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapHelper {

	private final static Logger logger = Logger.getLogger(SqlMapHelper.class);
	private static final String resource = "SqlMapConfig.xml";
	private static SqlMapClient sqlMapper;

	static {
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		} catch (IOException e) {
			logger.error("Building SqlMapClient instance:" + e.toString());
		}
	}

	public static SqlMapClient getMapper() {
		return sqlMapper;
	}

}

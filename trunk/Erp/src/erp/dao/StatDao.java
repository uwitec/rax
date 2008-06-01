package erp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import erp.model.Stat;

public interface StatDao {

    public double storeAmount() throws DataAccessException;

    public List<Stat> listProfitByDay(int index, int num)
            throws DataAccessException;

    public List<Stat> listCountByDay(int index, int num)
            throws DataAccessException;

    public List<Stat> listProfitByMonth(Date from, Date to)
            throws DataAccessException;

    public List<Stat> listCountByMonth(Date from, Date to)
            throws DataAccessException;

}

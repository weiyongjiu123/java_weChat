package Dao;

import Bean.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {

    public List<User> getUserList() throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "select openId,schedule from users where login=1 and subscribe=1";
        List<User> userList = queryRunner.query(sql, new BeanListHandler<User>(User.class));
        return userList;
    }
    public int setTodaySchedule(String todayScheduleStr,String openId) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "update users set todaySchedule=? where openId=?";
        int row = queryRunner.update(sql, todayScheduleStr, openId);
        return row;
    }

    public List<User> getTodaySchedule(boolean isDayRemind) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql;
        if(isDayRemind)
        {
             sql = "select openId,todaySchedule from users where dayRemind=1 and subscribe=1 and login=1";
        }else{
             sql = "select openId,todaySchedule from users where beforeRemind=1 and subscribe=1 and login=1";
        }
        List<User> userList = queryRunner.query(sql, new BeanListHandler<User>(User.class));
        return userList;
    }
}

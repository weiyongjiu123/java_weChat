package Dao;

import Bean.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public int setRemind(String userOpenId, String type, int status) {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "update users set " + type + "=? where openId=?";
        try {
            int row = queryRunner.update(sql, status, userOpenId);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<User> getRemindStatus(String fromUserName) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "select dayRemind,beforeRemind from users where openId=?";
        List<User> usersList = queryRunner.query(sql, new BeanListHandler<User>(User.class), fromUserName);
        return usersList;
    }

    public List<User> getSchedule(String openId) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "select schedule from users where openId=?";
        List<User> userList = queryRunner.query(sql, new BeanListHandler<User>(User.class), openId);
        return userList;
    }

    public boolean isLogin(String openId) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "select id from users where openId=? and login=1";
        List<User> userList = queryRunner.query(sql, new BeanListHandler<User>(User.class), openId);
        if (userList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int setSchedule(String openId, String schedule) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "update users set schedule=? where openId=?";
        int row = queryRunner.update(sql, schedule, openId);
        return row;
    }

    public int setLogin(String openId, String status) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "update users set login=? where openId=?";
        int row = queryRunner.update(sql, status, openId);
        return row;
    }

    public int doSubscribe(String openId,String subscribeStatus) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "update users set subscribe=? where openId=?";
        int row = queryRunner.update(sql,subscribeStatus,openId);
        return row;
    }
    public boolean isHasUser(String openId) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "select openId from users where openId=?";
        List<User> userList = queryRunner.query(sql, new BeanListHandler<User>(User.class), openId);
        if(userList.size() > 0)
        {
            return true;
        }else{
            return false;
        }
    }

    public int addUser(String openId) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "insert into users(openId,subscribe,login,dayRemind,beforeRemind) value(?,1,0,1,1)";
        int row = queryRunner.update(sql, openId);
        return row;
    }
}

package cn.llwstu.sql.filter.demo.service;

import cn.llwstu.sql.filter.demo.utils.GetJdbcConnect;
import cn.llwstu.sql.filter.demo.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @Author: Alickx
 * @Date: 2022/06/23/19:30
 * @Description:
 */
@Service
@Slf4j
public class SqlService {

    private static final String DATABASE = "sql_filter";

    private static final String TABLE = "t_user";

    public Result getColumns() {

        try (Connection connection = GetJdbcConnect.getConnection()) {
            // 获取所有列
            String sql = "SELECT * FROM information_schema.columns WHERE table_schema = '" + DATABASE + "'" + " AND table_name = '" + TABLE + "'";

            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();
            List<Map<String,String>> colList = new ArrayList<>();

            while (resultSet.next()) {
                Map<String,String> colMap = new HashMap<>(2);
                colMap.put("columnName", resultSet.getString("column_name"));
                colMap.put("columnComment", resultSet.getString("column_comment"));
                colList.add(colMap);
            }
            return Result.ok().put("data", colList).put("tableName", TABLE);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.error();

    }

    public Result execute(Map<String,String> dataMap) {
        log.info("dataMap: {}", dataMap);
        try (Connection connection = GetJdbcConnect.getConnection()) {
            String sql = dataMap.get("sql");
            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();
            List<Map<String, Object>> list = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    map.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getObject(i));
                }
                list.add(map);
            }
            return Result.ok().put("data", list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Result.error();
    }
}

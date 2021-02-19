/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import domain.GenericEntity;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;

/**
 *
 * @author Milos Milic
 */
public class RepositoryDBGeneric implements DbRepository<GenericEntity> {

    @Override
    public void add(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(entity.getTableName())
                    .append(" (").append(entity.getColumnNamesForInsert()).append(")")
                    .append(" VALUES (")
                    .append(entity.getInsertValues())
                    .append(")");

            String query = sb.toString();

            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                entity.setId(id);
            }
            statement.close();
            rsKey.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<GenericEntity> getAll(GenericEntity param) throws Exception {
        List<GenericEntity> resultList = new ArrayList<>();
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        Map<String, String> FKMap = getForeignKeys(connection, param.getTableName());
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        if (!FKMap.isEmpty()) {
            sb.append(param.getTableName()).append(" ")
              .append(param.getTableName().charAt(0));
            for (Map.Entry<String, String> entry : FKMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                sb.append(" JOIN ")
                        .append(key).append(" ")
                        .append(key.charAt(0)).append(" ON ")
                        .append(param.getTableName().charAt(0)).append(".")
                        .append(value).append("=")
                        .append(key.charAt(0)).append(".")
                        .append(value);
            }
        } else {
            sb.append(param.getTableName());
        }

        String query = sb.toString();
        System.out.println(query);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            resultList.add(param.getNewRecord(rs));
        }

        return resultList;
    }

    @Override
    public void edit(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(entity.getTableName())
                    .append(" SET ")
                    .append(entity.setAtrValues())
                    .append(" WHERE ")
                    .append(entity.getWhereCondition());

            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();

        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void delete(GenericEntity entity) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ")
                .append(entity.getTableName())
                .append(" WHERE ")
                .append(entity.getWhereCondition());

        String query = sb.toString();
        System.out.println(query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

    
   

    public Map<String, String> getForeignKeys(Connection connection, String tableName) throws SQLException {
        Map<String, String> mapTableKeyFK = new HashMap<>();
        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getImportedKeys(connection.getCatalog(), connection.getSchema(), tableName);
        while (rs.next()) {
            String fkTableName = rs.getString("PKTABLE_NAME");
            String fkColumnName = rs.getString("FKCOLUMN_NAME");
            mapTableKeyFK.put(fkTableName, fkColumnName);
            // int fkSequence = rs.getInt("KEY_SEQ");
//            System.out.println("getExportedKeys(): fkTableName=" + fkTableName);
//            System.out.println("getExportedKeys(): fkColumnName=" + fkColumnName);
            // System.out.println("getExportedKeys(): fkSequence=" + fkSequence);
        }
        return mapTableKeyFK;
    }
}

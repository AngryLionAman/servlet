/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topic;
import com.connect.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author inquiryhere.com
 */
public class topicFollow {
    public boolean topicFollw(int topicId,int userId) throws SQLException{
        database obj = new database();
        boolean status;
        try(Connection connection = obj.connect()) {
            status = false;
            try{
                String sql = "select topic_id from topic_followers_detail where topic_id = ? and user_or_followers_id =?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, topicId);
                preparedStatement.setInt(2, userId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    status = true;
                }
            }catch(SQLException msg){
                throw msg;
            }
        }
        obj.disconnect();
        return status;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.index;

import com.connect.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author inquiryhere.com
 */
public class totalAnswer {
    public int totalAnswer(int questionId) throws SQLException{
        int totalAnswer = 0;
        database obj = new database();
        try{
            Connection con = obj.connect();
            String sql = "SELECT COUNT(a_id)as cnt FROM answer WHERE q_id = ? group by q_id";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                totalAnswer = rs.getInt("cnt");
            }
            obj.disconnect();
            if(!con.isClosed()){
                con.close();
            }
        }catch(SQLException msg){
            throw msg;
        }
        return totalAnswer;
    }
    
}

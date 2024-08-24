/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;
import Model.ChucVu;
import java.sql.*;
import java.util.ArrayList;
import Ult.DBconnect1111;
/**
 *
 * @author admin
 */
public class ChucVu_Service {
    private Connection con= null;
    private PreparedStatement ps= null;
    private ResultSet rs= null;
    private String sql= null;
    
    public ArrayList<Model.ChucVu> getAll(){
        ArrayList<ChucVu> listCV= new ArrayList<>();
        sql="select Id,Ma,Ten from ChucVu";
        try {
            con=DBconnect1111.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                String id;
                String ma;
                String ten;
                id=rs.getString(1);
                ma=rs.getString(2);
                ten=rs.getString(3);
                listCV.add(new ChucVu(id, ma, ten));
            }
            return listCV;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

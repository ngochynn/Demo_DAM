/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Model.ChucVu;
import Model.NhanVien;
import java.sql.*;
import Ult.DBconnect1111;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class NhanVien_Service {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<NhanVien> getAll() {
        ArrayList<NhanVien> listNV = new ArrayList<>();
        sql = "select NV.ma, NV.ten,gioitinh,nv.ngaysinh,cv.id,cv.ma,cv.ten from "
                + "nhanvien nv join chucvu cv on cv.id=nv.idcv";
        try {
            con = DBconnect1111.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                String ma;
                String ten;
                String gioitinh;
                String ngaysinh;

                ma = rs.getString(1);
                ten = rs.getString(2);
                gioitinh = rs.getString(3);
                ngaysinh = rs.getString(4);
                ChucVu cv = new ChucVu(rs.getString(5), rs.getString(6), rs.getString(7));
                NhanVien nv = new NhanVien(ma, ten, gioitinh, ngaysinh, cv);
                listNV.add(nv);
            }
            return listNV;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int them(NhanVien NV) {
        sql = "INSERT INTO NhanVien(Ma,Ten,GioiTinh,NgaySinh,IdCV) VALUES(?,?,?,?,?)";
        try {
            con = DBconnect1111.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, NV.getMa());
            ps.setObject(2, NV.getTen());
            ps.setObject(3, NV.getGioiTinh());
            ps.setObject(4, NV.getNgaySinh());
            ps.setObject(5, NV.getChucVu().getId());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public NhanVien checkTrung(String maC) {
        sql = "select nv.ma, nv.ten,gioitinh,nv.ngaysinh,cv.id,cv.ma,cv.ten \n"
                + "                from nhanvien nv join chucvu cv on cv.id=nv.idcv\n"
                + "                 where NV.ma=?";

        NhanVien m = null;
        try {
            con = DBconnect1111.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maC);
            rs = ps.executeQuery();

            while (rs.next()) {

                String ma;
                String ten;
                String gioitinh;
                String ngaysinh;

                ma = rs.getString(1);
                ten = rs.getString(2);
                gioitinh = rs.getString(3);
                ngaysinh = rs.getString(4);
                ChucVu cv = new ChucVu(rs.getString(5), rs.getString(6), rs.getString(7));
                m = new NhanVien(ma, ten, gioitinh, ngaysinh, cv);

            }
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int sua(NhanVien nv, String maS) {
        sql = "UPDATE NhanVien set Ten=?, GioiTinh=?,NgaySinh=?,IdCV=? where Ma=?";
        try {
            con = DBconnect1111.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getTen());
            ps.setObject(2, nv.getGioiTinh());
            ps.setObject(3, nv.getNgaySinh());
            ps.setObject(4, nv.getChucVu().getId());
            ps.setObject(5, maS);
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int xoa(String maX) {
        sql = "delete from nhanvien where ma=?";
        try {
            con = DBconnect1111.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maX);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Nhan Vien: "+new NhanVien_Service().checkTrung("Nguyenvv4"));
    }
}

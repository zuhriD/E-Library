/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.library;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class Database {
    Connection conn = null;
    Statement s = null;
    
   public void insert(int a,String i, String jd, String pnl, String pnr, String sa, String d, String status, String f, String o,int is)  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = (Statement) conn.createStatement();
            String sql = null;
            if (status == "buku") {
                 sql = "insert into buku values("+0+",'"+i+"','"+jd+"','"+pnl+"','"+pnr+"','"+sa+"','"+d+"','belum_pinjam','"+f+"','"+o+"')";
            }
            else if(status == "mahasiswa"){
                sql = "insert into mahasiswa values("+a+","+is+",'"+i+"','"+jd+"','"+pnl+"','"+pnr+"','"+sa+"','"+d+"','"+f+"','"+o+"')";
            }
            else if(status == "admin"){
                sql = "insert into admin values("+a+",'"+jd+"','"+i+"','"+is+"','"+pnl+"','"+pnr+"','"+sa+"','"+f+"','"+o+"')";
            }
            s.execute(sql);
            JOptionPane.showMessageDialog(null, "Insert Data Success");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Insert Data Not Success");
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
                s.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public void register(int a,String i, String jd, String pnl, String pnr, String sa, String d, String status, String f, String o,int is)  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = (Statement) conn.createStatement();
            String sql = null;
            if(status == "mahasiswa"){
                sql = "insert into mahasiswa values("+a+","+is+",'"+i+"','"+jd+"','"+pnl+"','"+pnr+"','"+sa+"','"+d+"','"+f+"','"+o+"')";
            }
            else if(status == "admin"){
                sql = "insert into admin values("+a+",'"+jd+"','"+i+"','"+is+"','"+pnl+"','"+pnr+"','"+sa+"','"+f+"','"+o+"')";
            }
            s.execute(sql);
            JOptionPane.showMessageDialog(null, "Insert Data Success");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Insert Data Not Success");
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
                s.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public void update(int a,String i, String jd, String pnl, String pnr, String sa, String d, String status, String f, String o,int is)  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = (Statement) conn.createStatement();
            String sql = null;
            if (status == "buku") {
                 sql = "update buku set  judul_buku='"+jd+"',penulis='"+pnl+"',penerbit='"+pnr+"',thn_terbit='"+sa+"',"
                         + "kategori='"+d+"',status='belum_pinjam',nama_image='"+f+"',image_path='"+o+"' where kode_buku='"+i+"'";
            }
            else if (status == "mahasiswa") {
                 sql = "update mahasiswa set  nama_mhs='"+jd+"',username='"+pnl+"',fakulitas='"+sa+"',jurusan='"+d+"',"
                         + "nama_image='"+f+"',image_path='"+o+"' where kd_mhs='"+i+"'";
            }
             else if (status == "admin") {
                 sql = "update admin set  nama_admin='"+i+"',username='"+pnl+"',jabatan='"+sa+"',"
                         + "nama_image='"+f+"',image_path='"+o+"' where kd_admin='"+jd+"'";
            }
            s.execute(sql);
            JOptionPane.showMessageDialog(null, "Upadte Data Success");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Update Data Not Success");
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
                s.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
   public void delete(String i, String status, int a)  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = (Statement) conn.createStatement();
            String sql = null;
            if (status == "buku") {
                 sql = "delete from buku where kode_buku= '"+i+"'";
            }
            else if(status == "mahasiswa"){
                 sql = "delete from mahasiswa where kd_mhs= '"+i+"'";
            }
            else if(status == "peminjaman"){
                 sql = "delete from peminjaman where kd_peminjaman= '"+i+"'";
            }
            else if(status == "admin"){
                 sql = "delete from admin where kd_admin= '"+i+"'";
            }
            s.execute(sql);
            JOptionPane.showMessageDialog(null, "Delete Data Success");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Delete Data Not Success");
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
                s.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
   
   
}

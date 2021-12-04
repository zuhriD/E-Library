/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author LENOVO
 */
public class LoginForm extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    Connection con;
    Statement stm;
    Orang org[] = new Orang[2];
    String noMhs = null;
    String noAdmin = null;
    public LoginForm() {
        initComponents();
        this.setLocationRelativeTo(null);
         jPanel6.setVisible(false);
         jPanel7.setVisible(false);
         jPanel8.setVisible(false);
        autoNumberAdmin();
        autoNumberMhs();
    }

    private void autoNumberMhs()  {
        noMhs = "MHS000";
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            stm = (com.mysql.jdbc.Statement) con.createStatement();
            String sql = "select kd_mhs from mahasiswa";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                noMhs = rs.getString("kd_mhs");
            }
            noMhs = noMhs.substring(3);
            i = Integer.parseInt(noMhs)+1;
            noMhs = "00"+i;
            noMhs = "MHS"+noMhs.substring(noMhs.length()-3);
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(this, e);
        } catch (ClassNotFoundException ex) {
              Logger.getLogger(BukuForm.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }
    private void autoNumberAdmin() {
        noAdmin = "ADM000";
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            stm = (com.mysql.jdbc.Statement) con.createStatement();
            String sql = "select kd_admin from admin";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                noAdmin = rs.getString("kd_admin");
            }
            noAdmin = noAdmin.substring(3);
            i = Integer.parseInt(noAdmin)+1;
            noAdmin = "00"+i;
            noAdmin = "ADM"+noAdmin.substring(noAdmin.length()-3);
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(this, e);
        } catch (ClassNotFoundException ex) {
              Logger.getLogger(BukuForm.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }
    private void login(String st){
        try {
             
             Class.forName("com.mysql.jdbc.Driver");
             con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root",""); 
             stm = con.createStatement();
             ResultSet rs  = null;
             String sql = null;
             if (st == "user") {
                 sql = "select * from mahasiswa where username='"+tUUsernameLog.getText()+"' and password='"+tUPassLog.getText()+"'";
                 
            }else if(st == "admin"){
                  sql = "select * from admin where username='"+tAUsernameLog.getText()+"' and password='"+tAPassLog.getText()+"'";
                 
            }
             rs = stm.executeQuery(sql);
            if (rs.next()) {
                if (tUUsernameLog.getText().equals(rs.getString("username")) || tAPassLog.getText().equals(rs.getString("password"))
                        || tAUsernameLog.getText().equals(rs.getString("username")) ||tUPassLog.getText().equals(rs.getString("password")) ) {
                    String status= null;
                    String nama = null; 
                     String role = rs.getString("id_role");
                     switch(role){
                         case "1":
                             status = "Admin";
                        nama = rs.getString("nama_admin");
                        break;
                        case "2":
                            status = "Mahasiswa";
                        nama = rs.getString("nama_mhs");
                        break;
                        default:
                            break;
                     }
                  
                    
                    JOptionPane.showMessageDialog(rootPane, "Login Berhasil anda adalah "+status);
                    new Menu(role,nama).setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Password Salah");
                    tUPassLog.setText("");
                    tUUsernameLog.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "User Tidak ditemukan");
                tUUsernameLog.setText("");
                tUPassLog.setText("");
                
            }
        } catch (Exception e) {
               e.printStackTrace();
        }
    }
    private void regis(String st){
        try {
             
             Class.forName("com.mysql.jdbc.Driver");
             con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root",""); 
             stm = con.createStatement();
             ResultSet rs  = null;
             String sql = null;
             if (st == "user") {
                 sql = "select * from mahasiswa where username='"+tUsername.getText()+"' and password='"+tPass.getText()+"'";
                 
            }else if(st == "admin"){
                  sql = "select * from admin where username='"+tUserAd.getText()+"' and password='"+tPassAd.getText()+"'";
                 
            }
             rs = stm.executeQuery(sql);
            if (rs.next()) {
                if (tUserAd.getText().equals(rs.getString("username")) || tPassAd.getText().equals(rs.getString("password"))
                        || tUsername.getText().equals(rs.getString("username")) ||tPass.getText().equals(rs.getString("password")) ) {
                    String status= null;
                    String nama = null; 
                     String role = rs.getString("id_role");
                     switch(role){
                         case "1":
                             status = "Admin";
                        nama = rs.getString("nama_admin");
                        break;
                        case "2":
                            status = "Mahasiswa";
                        nama = rs.getString("nama_mhs");
                        break;
                        default:
                            break;
                     }
                  
                    
                    JOptionPane.showMessageDialog(rootPane, "Registrasi Berhasil anda adalah "+status);
                    new Menu(role,nama).setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Password Salah");
                    tUPassLog.setText("");
                    tUUsernameLog.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "User Tidak ditemukan");
                tUUsernameLog.setText("");
                tUPassLog.setText("");
                
            }
        } catch (Exception e) {
               e.printStackTrace();
        }
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel_SoftZyd = new javax.swing.JLabel();
        jLabel_inven = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        tNama = new javax.swing.JTextField();
        tFakulitas = new javax.swing.JTextField();
        tJurusan = new javax.swing.JTextField();
        tUsername = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        btnSignUpUser = new javax.swing.JButton();
        tPass = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        tNamaAd = new javax.swing.JTextField();
        tUserAd = new javax.swing.JTextField();
        tPassAd = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        btnSignAd = new javax.swing.JButton();
        tJabatan = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tUUsernameLog = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tUPassLog = new javax.swing.JPasswordField();
        btnLoginUser = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        btnLoginAdmin = new javax.swing.JButton();
        tAPassLog = new javax.swing.JPasswordField();
        jLabel29 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tAUsernameLog = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(910, 620));
        setMinimumSize(new java.awt.Dimension(910, 620));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 131, 3));
        jPanel4.setPreferredSize(new java.awt.Dimension(450, 600));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_SoftZyd.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel_SoftZyd.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_SoftZyd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/book_100px.png"))); // NOI18N
        jLabel_SoftZyd.setText("E-Library");
        jPanel4.add(jLabel_SoftZyd, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 314, -1));

        jLabel_inven.setFont(new java.awt.Font("Trebuchet MS", 0, 36)); // NOI18N
        jLabel_inven.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_inven.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_inven.setText("Library Management");
        jPanel4.add(jLabel_inven, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 380, 50));

        jPanel6.setBackground(new java.awt.Color(255, 131, 3));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tNama.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        tNama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 87, 9)));
        tNama.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tNamaMouseMoved(evt);
            }
        });
        jPanel6.add(tNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 330, 40));

        tFakulitas.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        tFakulitas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 87, 9)));
        tFakulitas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tFakulitasMouseMoved(evt);
            }
        });
        jPanel6.add(tFakulitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 330, 40));

        tJurusan.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        tJurusan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 87, 9)));
        tJurusan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tJurusanMouseMoved(evt);
            }
        });
        jPanel6.add(tJurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 330, 40));

        tUsername.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        tUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 87, 9)));
        tUsername.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tUsernameMouseMoved(evt);
            }
        });
        jPanel6.add(tUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 330, 40));

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Library Management");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 190, -1));

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Name");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 90, 20));

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Jurusan");
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 314, -1));

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Username");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 314, -1));

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Fakulitas");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 314, -1));

        jButton14.setBackground(new java.awt.Color(169, 224, 49));
        jButton14.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/back_to_30px.png"))); // NOI18N
        jButton14.setText("Back");
        jButton14.setBorder(null);
        jButton14.setContentAreaFilled(false);
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.setRequestFocusEnabled(false);
        jButton14.setVerifyInputWhenFocusTarget(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 110, -1));

        btnSignUpUser.setBackground(new java.awt.Color(163, 87, 9));
        btnSignUpUser.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        btnSignUpUser.setForeground(new java.awt.Color(255, 255, 255));
        btnSignUpUser.setText("Sign up");
        btnSignUpUser.setBorder(null);
        btnSignUpUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSignUpUser.setFocusPainted(false);
        btnSignUpUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpUserActionPerformed(evt);
            }
        });
        jPanel6.add(btnSignUpUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 330, 40));

        tPass.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        tPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 87, 9)));
        tPass.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tPassMouseMoved(evt);
            }
        });
        jPanel6.add(tPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 330, 40));

        jLabel25.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Password");
        jPanel6.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 314, -1));

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("E-Library");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 310, -1));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 410, 560));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/cancel_30px.png"))); // NOI18N
        jButton10.setToolTipText("Close");
        jButton10.setBorder(null);
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setRequestFocusEnabled(false);
        jButton10.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/cancel1_30px.png"))); // NOI18N
        jButton10.setVerifyInputWhenFocusTarget(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 40, 40));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/macos_minimize_30px.png"))); // NOI18N
        jButton11.setToolTipText("Minimize");
        jButton11.setBorder(null);
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setFocusPainted(false);
        jButton11.setRequestFocusEnabled(false);
        jButton11.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/macos_minimize1_30px.png"))); // NOI18N
        jButton11.setVerifyInputWhenFocusTarget(false);
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 40, 40));

        jPanel7.setBackground(new java.awt.Color(255, 131, 3));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tNamaAd.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        tNamaAd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 87, 9)));
        tNamaAd.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tNamaAdMouseMoved(evt);
            }
        });
        jPanel7.add(tNamaAd, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 330, 40));

        tUserAd.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        tUserAd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 87, 9)));
        tUserAd.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tUserAdMouseMoved(evt);
            }
        });
        jPanel7.add(tUserAd, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 330, 40));

        tPassAd.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        tPassAd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 87, 9)));
        tPassAd.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tPassAdMouseMoved(evt);
            }
        });
        jPanel7.add(tPassAd, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 330, 40));

        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("E-Library");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 310, -1));

        jLabel23.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Library Management");
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 190, -1));

        jLabel24.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Name");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 90, 20));

        jLabel26.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Password");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 314, -1));

        jLabel28.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Username");
        jPanel7.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 314, -1));

        jButton15.setBackground(new java.awt.Color(169, 224, 49));
        jButton15.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/back_to_30px.png"))); // NOI18N
        jButton15.setText("Back");
        jButton15.setBorder(null);
        jButton15.setContentAreaFilled(false);
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton15.setRequestFocusEnabled(false);
        jButton15.setVerifyInputWhenFocusTarget(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 110, -1));

        btnSignAd.setBackground(new java.awt.Color(163, 87, 9));
        btnSignAd.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        btnSignAd.setForeground(new java.awt.Color(255, 255, 255));
        btnSignAd.setText("Sign up");
        btnSignAd.setBorder(null);
        btnSignAd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSignAd.setFocusPainted(false);
        btnSignAd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignAdActionPerformed(evt);
            }
        });
        jPanel7.add(btnSignAd, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 330, 40));

        tJabatan.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        tJabatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 87, 9)));
        tJabatan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tJabatanMouseMoved(evt);
            }
        });
        jPanel7.add(tJabatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 330, 40));

        jLabel27.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Jabatan");
        jPanel7.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 90, 20));

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 410, 560));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 460, 620));

        jPanel3.setBackground(new java.awt.Color(163, 87, 9));
        jPanel3.setPreferredSize(new java.awt.Dimension(910, 620));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tUUsernameLog.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        tUUsernameLog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 131, 3)));
        tUUsernameLog.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tUUsernameLogMouseMoved(evt);
            }
        });
        jPanel3.add(tUUsernameLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 205, 260, 40));

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Username");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 70, -1));

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Password");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 70, -1));

        tUPassLog.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        tUPassLog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 131, 3)));
        jPanel3.add(tUPassLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 305, 260, 40));

        btnLoginUser.setBackground(new java.awt.Color(255, 131, 3));
        btnLoginUser.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        btnLoginUser.setForeground(new java.awt.Color(255, 255, 255));
        btnLoginUser.setText("Sign In");
        btnLoginUser.setBorder(null);
        btnLoginUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLoginUser.setFocusPainted(false);
        btnLoginUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginUserActionPerformed(evt);
            }
        });
        jPanel3.add(btnLoginUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 260, 40));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(169, 224, 49));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/user_24px.png"))); // NOI18N
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, 45));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(169, 224, 49));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/lock_30px.png"))); // NOI18N
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, 45));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/user_shield_100px.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Create New User");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setRequestFocusEnabled(false);
        jButton2.setVerifyInputWhenFocusTarget(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 480, 140, 30));

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("User");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 110, -1));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Are You Admin? Click Here!");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setRequestFocusEnabled(false);
        jButton3.setVerifyInputWhenFocusTarget(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 200, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, -1));

        jPanel8.setBackground(new java.awt.Color(163, 87, 9));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton16.setBackground(new java.awt.Color(169, 224, 49));
        jButton16.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/back_to1_30px.png"))); // NOI18N
        jButton16.setText("Back");
        jButton16.setBorder(null);
        jButton16.setContentAreaFilled(false);
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.setRequestFocusEnabled(false);
        jButton16.setVerifyInputWhenFocusTarget(false);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 480, 110, -1));

        btnLoginAdmin.setBackground(new java.awt.Color(255, 131, 3));
        btnLoginAdmin.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        btnLoginAdmin.setForeground(new java.awt.Color(255, 255, 255));
        btnLoginAdmin.setText("Sign In");
        btnLoginAdmin.setBorder(null);
        btnLoginAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLoginAdmin.setFocusPainted(false);
        btnLoginAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginAdminActionPerformed(evt);
            }
        });
        jPanel8.add(btnLoginAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 260, 40));

        tAPassLog.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        tAPassLog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 131, 3)));
        jPanel8.add(tAPassLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 260, 40));

        jLabel29.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Password");
        jPanel8.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 70, -1));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(169, 224, 49));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/lock_30px.png"))); // NOI18N
        jPanel8.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, 45));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(169, 224, 49));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/user_24px.png"))); // NOI18N
        jPanel8.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, 45));

        tAUsernameLog.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        tAUsernameLog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 131, 3)));
        jPanel8.add(tAUsernameLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 260, 40));

        jLabel30.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Username");
        jPanel8.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 70, -1));

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Admin");
        jPanel8.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 110, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/user_shield_100px.png"))); // NOI18N
        jPanel8.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Create New Admin");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setRequestFocusEnabled(false);
        jButton1.setVerifyInputWhenFocusTarget(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 140, 30));

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tUUsernameLogMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tUUsernameLogMouseMoved

    }//GEN-LAST:event_tUUsernameLogMouseMoved

    private void btnLoginUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginUserActionPerformed
     login("user"); 
    }//GEN-LAST:event_btnLoginUserActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jLabel_SoftZyd.setVisible(false);
        jLabel_inven.setVisible(false);
        jPanel6.setVisible(false);
        jPanel7.setVisible(true);
//               jLabel7.setVisible(false);
//               jLabel8.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tNamaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tNamaMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tNamaMouseMoved

    private void tFakulitasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tFakulitasMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tFakulitasMouseMoved

    private void tJurusanMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tJurusanMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tJurusanMouseMoved

    private void tUsernameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tUsernameMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tUsernameMouseMoved

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        jPanel6.setVisible(false);
        jPanel7.setVisible(false);
        jLabel_SoftZyd.setVisible(true);
        jLabel_inven.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void btnSignUpUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpUserActionPerformed
        // TODO add your handling code here:
         org[0]= new Mahasiswa(noMhs, tNama.getText(),tUsername.getText(),tPass
        .getText(),tFakulitas.getText(),tJurusan.getText(), "", "");
         org[0].register();
        regis("user");
    }//GEN-LAST:event_btnSignUpUserActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

    }//GEN-LAST:event_jButton11ActionPerformed

    private void tNamaAdMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tNamaAdMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tNamaAdMouseMoved

    private void tUserAdMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tUserAdMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tUserAdMouseMoved

    private void tPassAdMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tPassAdMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tPassAdMouseMoved

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        jPanel6.setVisible(false);
        jPanel7.setVisible(false);
        jLabel_SoftZyd.setVisible(true);
        jLabel_inven.setVisible(true);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void btnSignAdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignAdActionPerformed
        // TODO add your handling code here:
        org[1]= new Admin(noAdmin, tNamaAd.getText(), 1, tUserAd.getText(), tPassAd.getText(), 
                tJabatan.getText(),"","");
         org[1].register();
        regis("admin");
    }//GEN-LAST:event_btnSignAdActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jLabel_SoftZyd.setVisible(false);
        jLabel_inven.setVisible(false);
        jPanel6.setVisible(true);
        jPanel7.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tPassMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tPassMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tPassMouseMoved

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jPanel8.setVisible(true);
        jPanel3.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        jPanel8.setVisible(false);
        jPanel3.setVisible(true);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void btnLoginAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginAdminActionPerformed
        // TODO add your handling code here:
        login("admin");
    }//GEN-LAST:event_btnLoginAdminActionPerformed

    private void tJabatanMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tJabatanMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tJabatanMouseMoved

    /**
     * @param args the command line arguments
     */ 
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoginAdmin;
    private javax.swing.JButton btnLoginUser;
    private javax.swing.JButton btnSignAd;
    private javax.swing.JButton btnSignUpUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_SoftZyd;
    private javax.swing.JLabel jLabel_inven;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPasswordField tAPassLog;
    private javax.swing.JTextField tAUsernameLog;
    private javax.swing.JTextField tFakulitas;
    private javax.swing.JTextField tJabatan;
    private javax.swing.JTextField tJurusan;
    private javax.swing.JTextField tNama;
    private javax.swing.JTextField tNamaAd;
    private javax.swing.JTextField tPass;
    private javax.swing.JTextField tPassAd;
    private javax.swing.JPasswordField tUPassLog;
    private javax.swing.JTextField tUUsernameLog;
    private javax.swing.JTextField tUserAd;
    private javax.swing.JTextField tUsername;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.library;

import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class AdminForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form AdminForm
     */
      String  crudImageAbsolutePath = null;
       String     crudImageName = null;
       Connection conn = null;
       Statement s = null;
    public AdminForm() {
        initComponents();
          this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        autoNumber();
        showData();
        inisiasi();
    }
        private void inisiasi(){
        txtnama.setText("");
        txtusername.setText("");
        txtpassword.setText("");
        txtjabatan.setText("");
        btnedit.setEnabled(false);
        btnhapus.setEnabled(false);
        btnsimpan.setEnabled(true);
        tempatFoto.setIcon(null);
    }
        private void autoNumber() {
        String noAdmin = "ADM000";
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = (com.mysql.jdbc.Statement) conn.createStatement();
            String sql = "select kd_admin from admin";
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                noAdmin = rs.getString("kd_admin");
            }
            noAdmin = noAdmin.substring(3);
            i = Integer.parseInt(noAdmin)+1;
            noAdmin = "00"+i;
            noAdmin = "ADM"+noAdmin.substring(noAdmin.length()-3);
            txtkdadmin.setText(noAdmin);
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(this, e);
        } catch (ClassNotFoundException ex) {
              Logger.getLogger(BukuForm.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }
        private void showData(){
         DefaultTableModel model = new DefaultTableModel();
         model.addColumn("Kode Admin");
         model.addColumn("Nama");
         model.addColumn("Username");
         model.addColumn("Jabatan");
         model.addColumn("Gambar Path");
         
         try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (com.mysql.jdbc.Connection)  DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = (com.mysql.jdbc.Statement) conn.createStatement();
            String sql = "select *from admin order by id_admin asc ";
             ResultSet rs = s.executeQuery(sql);
             
             while (rs.next()) {                 
                 model.addRow(new Object[]{
                     rs.getString("kd_admin"),
                     rs.getString("nama_admin"),
                     rs.getString("username"),
                     rs.getString("jabatan"),
                     rs.getString("image_path")
                 });
             }
             TADMIN.setModel(model);
        } catch (Exception e) {
            System.out.println("eror" + e.getMessage());
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
      public void editBtn(){
           btnedit.setEnabled(true);
        btnhapus.setEnabled(true);
        btnsimpan.setEnabled(false);
      }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtkdadmin = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        txtjabatan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TADMIN = new javax.swing.JTable();
        btnsimpan = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tempatFoto = new javax.swing.JLabel();
        btnFoto = new javax.swing.JButton();
        txtpassword = new javax.swing.JPasswordField();

        setPreferredSize(new java.awt.Dimension(873, 612));

        jPanel1.setBackground(new java.awt.Color(255, 131, 3));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("KODE ADMIN");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NAMA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("PASSWORD");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("JABATAN");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, -1, -1));

        txtkdadmin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 87, 9)));
        jPanel1.add(txtkdadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 134, 30));

        txtnama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 87, 9)));
        jPanel1.add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 134, 30));

        txtjabatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 87, 9)));
        jPanel1.add(txtjabatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 125, 30));

        TADMIN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TADMIN.setRowHeight(25);
        TADMIN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TADMINMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TADMIN);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 347, 750, 150));

        btnsimpan.setBackground(new java.awt.Color(163, 87, 9));
        btnsimpan.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        btnsimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 130, 40));

        btnedit.setBackground(new java.awt.Color(163, 87, 9));
        btnedit.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        btnedit.setForeground(new java.awt.Color(255, 255, 255));
        btnedit.setText("Edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        jPanel1.add(btnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 510, 100, 40));

        btnbatal.setBackground(new java.awt.Color(163, 87, 9));
        btnbatal.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        btnbatal.setForeground(new java.awt.Color(255, 255, 255));
        btnbatal.setText("BATAL");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });
        jPanel1.add(btnbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, 120, 40));

        btnhapus.setBackground(new java.awt.Color(163, 87, 9));
        btnhapus.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        btnhapus.setForeground(new java.awt.Color(255, 255, 255));
        btnhapus.setText("HAPUS");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 510, 120, 40));

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Data Admin");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 290, -1));

        txtusername.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtusername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 87, 9)));
        txtusername.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                txtusernameMouseMoved(evt);
            }
        });
        jPanel1.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 130, 30));

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("USERNAME");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 90, 20));

        tempatFoto.setBackground(new java.awt.Color(204, 204, 204));
        tempatFoto.setOpaque(true);
        jPanel1.add(tempatFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 220, 260));

        btnFoto.setBackground(new java.awt.Color(163, 87, 9));
        btnFoto.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        btnFoto.setForeground(new java.awt.Color(255, 255, 255));
        btnFoto.setText("CHOOSE PHOTO");
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });
        jPanel1.add(btnFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 300, 130, 40));

        txtpassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 87, 9)));
        txtpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpasswordActionPerformed(evt);
            }
        });
        jPanel1.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 130, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        Admin ad = new Admin(txtkdadmin.getText(), txtnama.getText(), 1, txtusername.getText(), txtpassword.getText(), 
                txtjabatan.getText(),crudImageName,crudImageAbsolutePath);
        ad.insert();
        inisiasi();
        showData();
         autoNumber();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void txtusernameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtusernameMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusernameMouseMoved

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed
        // TODO add your handling code here:
        try{
            String currentDirectoryPath = "C:\\Users\\LENOVO\\Pictures";
            JFileChooser imageFileChooser = new JFileChooser(currentDirectoryPath);
            imageFileChooser.setDialogTitle("Choose Image");

            //selanjutnya, memilih salah satu gambar masi baru ngatur broswer
            FileNameExtensionFilter imageFNEF = new FileNameExtensionFilter ("IMAGES","png","jpeg","jpg");
            imageFileChooser.setFileFilter(imageFNEF);
            int imageChooser = imageFileChooser.showOpenDialog(null);
            if(imageChooser == JFileChooser.APPROVE_OPTION){

                // memberi alamat image
                File imageFile = imageFileChooser.getSelectedFile();
                crudImageAbsolutePath = imageFile.getAbsolutePath();
                crudImageName = imageFile.getName();

                //            //menampilkan image di label
                ImageIcon imageIcon = new ImageIcon(crudImageAbsolutePath);
                tempatFoto.setIcon(imageIcon);
                //            //resize Image to Fit JLabel;
                Image imageResize = imageIcon.getImage().getScaledInstance(tempatFoto.getWidth(), tempatFoto.getHeight(), Image.SCALE_SMOOTH);
                tempatFoto.setIcon(new ImageIcon(imageResize));
            }
        }
        catch(Exception e){

        }
    }//GEN-LAST:event_btnFotoActionPerformed

    private void txtpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        inisiasi();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        Admin ad = new Admin(txtkdadmin.getText(), txtnama.getText(), 1, txtusername.getText(), txtpassword.getText(), txtjabatan.getText(),crudImageName,crudImageAbsolutePath);
        ad.update();
        inisiasi();
        showData();
        autoNumber();
    }//GEN-LAST:event_btneditActionPerformed

    private void TADMINMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TADMINMouseClicked
        // TODO add your handling code here:
        
         int baris = TADMIN.rowAtPoint(evt.getPoint());
        String id = TADMIN.getValueAt(baris, 0).toString();
        this.txtkdadmin.setText(id);
        
        String nama = TADMIN.getValueAt(baris, 1).toString();
        txtnama.setText(nama);
        String username = TADMIN.getValueAt(baris, 2).toString();
        txtusername.setText(username);
        String fakulitas = TADMIN.getValueAt(baris, 3).toString();
        txtjabatan.setText(fakulitas);
        String namaP = TADMIN.getValueAt(baris, 4).toString();
        ImageIcon imageIcon = new ImageIcon(namaP);
                tempatFoto.setIcon(imageIcon);
                //            //resize Image to Fit JLabel;
                Image imageResize = imageIcon.getImage().getScaledInstance(tempatFoto.getWidth(), tempatFoto.getHeight(), Image.SCALE_SMOOTH);
                tempatFoto.setIcon(new ImageIcon(imageResize));
       editBtn();
    }//GEN-LAST:event_TADMINMouseClicked

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        Admin ad = new Admin(txtkdadmin.getText(), txtnama.getText(), 1, txtusername.getText(), txtpassword.getText(), txtjabatan.getText(),crudImageName,crudImageAbsolutePath);
        ad.delete();
        inisiasi();
        showData();
        autoNumber();
    }//GEN-LAST:event_btnhapusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TADMIN;
    private javax.swing.JButton btnFoto;
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel tempatFoto;
    private javax.swing.JTextField txtjabatan;
    private javax.swing.JTextField txtkdadmin;
    private javax.swing.JTextField txtnama;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.library;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author LENOVO
 */
public class PeminjamanFrom extends javax.swing.JInternalFrame {

    /**
     * Creates new form PeminjamanFrom
     */
     Connection conn = null;
       Statement s = null;
       private static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
       Date tgl=new Date();
       String dataTabel = null;
       String jumlahpinjam = null;
    public PeminjamanFrom() {
        initComponents();
          this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        tampilKodeAnggota();
        tampilKodeBuku();
        autoNumber();
        Waktu();
        showDataPeminjaman();
    }
    public void inisiasi(){
        TXTNAMAANGGOTA.setText("");
        TXTFAKULITAS.setText("");
        TXTJURUSAN.setText("");
        TXTJUDULBUKU.setText("");
        TXTPENGARANG.setText("");
        TXTNAMAANGGOTA.setText("");
        TXTJUMLAHPINJAM.setText("");
        TXTTOTALPINJAM.setText("");
    }
    private void autoNumber()  {
        String noPJM = "PJM000";
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = (com.mysql.jdbc.Statement) conn.createStatement();
            String sql = "select kd_pinjam from pengembalian";
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                noPJM = rs.getString("kd_pinjam");
            }
            noPJM = noPJM.substring(3);
            i = Integer.parseInt(noPJM)+1;
            noPJM = "00"+i;
            noPJM = "PJM"+noPJM.substring(noPJM.length()-3);
            TXTKODEPINJAM.setText(noPJM);
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(this, e);
        } catch (ClassNotFoundException ex) {
              Logger.getLogger(BukuForm.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }
    public void tampilKodeAnggota(){
         try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = conn.createStatement();
            String sql = "select kd_mhs from mahasiswa order by kd_mhs asc";
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
               CBANGGOTA.addItem(rs.getString("kd_mhs"));
            }
           
            conn.close();
            s.close(); 
        } catch (SQLException e) {
             JOptionPane.showConfirmDialog(this, e);
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(PeminjamanFrom.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
      public void tampilDataAnggota(){
         try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = conn.createStatement();
            String sql = "select * from mahasiswa where kd_mhs='"+CBANGGOTA.getSelectedItem()+"'";
            ResultSet rs = s.executeQuery(sql);
             if (rs.next()) {
                 TXTNAMAANGGOTA.setText(rs.getString("nama_mhs").toString());
                 TXTJURUSAN.setText(rs.getString("jurusan").toString());
                  TXTFAKULITAS.setText(rs.getString("fakulitas").toString());
             }
            
            conn.close();
            s.close(); 
        } catch (SQLException e) {
             JOptionPane.showConfirmDialog(this, e);
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(PeminjamanFrom.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
       public void tampilDataBuku(){
         try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = conn.createStatement();
            String sql = "select * from buku where kode_buku='"+CBBUKU.getSelectedItem()+"'";
            ResultSet rs = s.executeQuery(sql);
             if (rs.next()) {
                 TXTJUDULBUKU.setText(rs.getString("judul_buku").toString());
                 TXTPENGARANG.setText(rs.getString("penulis").toString());
             }
            
            conn.close();
            s.close(); 
        } catch (SQLException e) {
             JOptionPane.showConfirmDialog(this, e);
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(PeminjamanFrom.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
     public void tampilKodeBuku(){
         try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = conn.createStatement();
            String sql = "select kode_buku from buku order by kode_buku asc";
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
               CBBUKU.addItem(rs.getString("kode_buku"));
            }
            
            conn.close();
            s.close(); 
        } catch (SQLException e) {
             JOptionPane.showConfirmDialog(this, e);
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(PeminjamanFrom.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
      private void prosestambah(){
        try {
            DefaultTableModel tableModel =(DefaultTableModel)TPINJAM.getModel();
            String[]data = new String[4];
            data[0]= String.valueOf(CBBUKU.getSelectedItem());
            data[1]= TXTJUDULBUKU.getText();
            data[2]= TXTPENGARANG.getText();
            data[3]= TXTJUMLAHPINJAM.getText();
            tableModel.addRow(data);            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error masukkan data \n"+e.getMessage());
        }
    }    
     private void Total(){
      int jumlahBaris = TPINJAM.getRowCount();
        int ttlpinjam = 0, jlhpinjam=0;
        int Jlhtotalpinjam;
        
        TableModel tblmodel;
        tblmodel = TPINJAM.getModel();
        for (int i=0; i<jumlahBaris; i++){
            Jlhtotalpinjam=Integer.parseInt(tblmodel.getValueAt(i, 3).toString());
                jlhpinjam=Jlhtotalpinjam+jlhpinjam;
                }
        TXTTOTALPINJAM.setText(String.valueOf(jlhpinjam));
     }
     public void Waktu(){
        Date tgl=new Date();
        JTANGGAL.setDate(tgl);
     }
     private void simpanPeminjaman(){
       
            try {
                int i=0;
                jumlahpinjam=TXTTOTALPINJAM.getText();
                 Class.forName("com.mysql.jdbc.Driver");
                  conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
                   s = conn.createStatement();
                   Date tgl=new Date();
                   Calendar c = Calendar.getInstance();
                   c.setTime(tgl);
                   c.add(Calendar.DATE, 7);
                   Date tglkembali = c.getTime(); 
//                   JOptionPane.showMessageDialog(this, dateFormat.format(tglkembali));
             
                    s.executeUpdate("insert into peminjaman"
                            + "(kd_mhs,kd_peminjaman,tgl_pinjam,tgl_kembali,jml_pinjam)"
                            + "values('"+CBANGGOTA.getSelectedItem().toString()+"',"
                            +"'"+TXTKODEPINJAM.getText()+"',"
                            + "'"+dateFormat.format(tgl)+"',"
                            + "'"+dateFormat.format(tglkembali).toString()+"',"
                            + "'"+jumlahpinjam+"')");
               
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Gagal Menyimpan..!!"+e);;
            }
        
    }
      private void simpandetail(){
        int jumlah_baris=TPINJAM.getRowCount();
        if (jumlah_baris == 0) {
            JOptionPane.showMessageDialog(rootPane, "Table is empty!!");
        } else {
            try {
                int i=0;
                 conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
                   s = conn.createStatement();
                while(i<jumlah_baris){
                    s.executeUpdate("insert into detail_peminjaman"
                            + "(kd_pinjam,kd_buku,judul_buku,penulis,jml_pinjam)"
                            + "values('"+TXTKODEPINJAM.getText()+"',"
                            + "'"+TPINJAM.getValueAt(i,0)+"',"
                            + "'"+TPINJAM.getValueAt(i,1)+"',"
                            + "'"+TXTPENGARANG.getText()+"',"
                            + "'"+TXTJUMLAHPINJAM.getText()+"')");
                    i++;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Gagal Menyimpan..!!"+e);
            }
        }
    }
     
  private void hapustable() {
    DefaultTableModel model = (DefaultTableModel)TPINJAM.getModel();

    while (model.getRowCount() > 0){
        for (int i = 0; i < model.getRowCount(); ++i){
            model.removeRow(i);
        }
    }
}
   private void showDataPeminjaman(){
         DefaultTableModel model = new DefaultTableModel();
         model.addColumn("KODE  PEMINJAMAN");
         model.addColumn("KODE MAHASISWA");
         model.addColumn("TANGGAL PINJAM");
         model.addColumn("TANGGAL KEMBALI");
         model.addColumn("JUMLAH PINJAM");
         
         try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (com.mysql.jdbc.Connection)  DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = (com.mysql.jdbc.Statement) conn.createStatement();
            String sql = "select *from peminjaman order by kd_peminjaman asc ";
             ResultSet rs = s.executeQuery(sql);
             
             while (rs.next()) {                 
                 model.addRow(new Object[]{
                     rs.getString("kd_peminjaman"),
                     rs.getString("kd_mhs"),
                     rs.getString("tgl_pinjam"),
                     rs.getString("tgl_kembali"),
                      rs.getString("jml_pinjam"),
                 });
             }
             TPEMINJAMAN.setModel(model);
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TXTKODEPINJAM = new javax.swing.JTextField();
        JTANGGAL = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        TXTNAMAANGGOTA = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TXTFAKULITAS = new javax.swing.JTextField();
        TXTJURUSAN = new javax.swing.JTextField();
        CBANGGOTA = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TXTJUDULBUKU = new javax.swing.JTextField();
        TXTPENGARANG = new javax.swing.JTextField();
        TXTJUMLAHPINJAM = new javax.swing.JTextField();
        BTNTAMBAH = new javax.swing.JButton();
        BtnKurang = new javax.swing.JButton();
        CBBUKU = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TPEMINJAMAN = new javax.swing.JTable();
        BTNHAPUS = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        TXTTOTALPINJAM = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TPINJAM = new javax.swing.JTable();
        BTNSIMPAN = new javax.swing.JButton();
        BTNEDIT = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 131, 3));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(163, 87, 9));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KODE PINJAM");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 18, -1, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TANGGAL PINJAM");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 44, -1, -1));

        TXTKODEPINJAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTKODEPINJAMActionPerformed(evt);
            }
        });
        jPanel2.add(TXTKODEPINJAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 161, -1));

        JTANGGAL.setDateFormatString("dd-MM-YYY");
        jPanel2.add(JTANGGAL, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 160, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 500, 80));

        jPanel3.setBackground(new java.awt.Color(163, 87, 9));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INPUT DATA ANGGOTA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("KODE ANGGOTA");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 35, -1, -1));
        jPanel3.add(TXTNAMAANGGOTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 61, 113, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("NAMA ANGGOTA");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 64, -1, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Fakulitas");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 90, -1, -1));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jurusan");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 122, -1, -1));
        jPanel3.add(TXTFAKULITAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 90, 113, -1));
        jPanel3.add(TXTJURUSAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 119, 113, -1));

        CBANGGOTA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        CBANGGOTA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBANGGOTAItemStateChanged(evt);
            }
        });
        CBANGGOTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBANGGOTAActionPerformed(evt);
            }
        });
        jPanel3.add(CBANGGOTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 93, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 260, 150));

        jPanel5.setBackground(new java.awt.Color(163, 87, 9));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INPUT DATA BUKU", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("KODEBUKU");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 35, -1, -1));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("JUDUL BUKU");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 64, -1, -1));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PENGARANG");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 93, -1, -1));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("JUMLAH PINJAM");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 122, -1, -1));
        jPanel5.add(TXTJUDULBUKU, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 61, 127, -1));
        jPanel5.add(TXTPENGARANG, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 90, 116, -1));
        jPanel5.add(TXTJUMLAHPINJAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 119, 41, -1));

        BTNTAMBAH.setText("+");
        BTNTAMBAH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNTAMBAHActionPerformed(evt);
            }
        });
        jPanel5.add(BTNTAMBAH, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 149, -1, -1));

        BtnKurang.setText("-");
        BtnKurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKurangActionPerformed(evt);
            }
        });
        jPanel5.add(BtnKurang, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 149, 47, -1));

        CBBUKU.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        CBBUKU.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBBUKUItemStateChanged(evt);
            }
        });
        CBBUKU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBBUKUActionPerformed(evt);
            }
        });
        jPanel5.add(CBBUKU, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 32, 93, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 300, 180));

        TPEMINJAMAN.setModel(new javax.swing.table.DefaultTableModel(
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
        TPEMINJAMAN.setRowHeight(25);
        TPEMINJAMAN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TPEMINJAMANMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TPEMINJAMAN);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, 500, 180));

        BTNHAPUS.setText("HAPUS");
        BTNHAPUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNHAPUSActionPerformed(evt);
            }
        });
        jPanel1.add(BTNHAPUS, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 510, 110, 30));

        jButton4.setText("BATAL");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 510, 110, 30));

        jLabel15.setText("TOTAL PINJAM");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, -1, 40));
        jPanel1.add(TXTTOTALPINJAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 250, 140, 30));

        TPINJAM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "KODE BUKU", "JUDUL BUKU", "PENGARANG", "JUMLAH PINJAM"
            }
        ));
        jScrollPane1.setViewportView(TPINJAM);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 500, 100));

        BTNSIMPAN.setText("SIMPAN");
        BTNSIMPAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSIMPANActionPerformed(evt);
            }
        });
        jPanel1.add(BTNSIMPAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 510, 120, 30));

        BTNEDIT.setText("REFRESH");
        BTNEDIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNEDITActionPerformed(evt);
            }
        });
        jPanel1.add(BTNEDIT, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 510, 100, 30));

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Peminjaman");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 290, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CBANGGOTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBANGGOTAActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_CBANGGOTAActionPerformed

    private void BTNTAMBAHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNTAMBAHActionPerformed
        // TODO add your handling code here:
        prosestambah();
        Total();
    }//GEN-LAST:event_BTNTAMBAHActionPerformed

    private void BtnKurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKurangActionPerformed
        // TODO add your handling code here:
          DefaultTableModel model = (DefaultTableModel)TPINJAM.getModel();
        int row = TPINJAM.getSelectedRow();
            if (row>=0) {
                int ok = JOptionPane.showConfirmDialog(null, "You sure you want to Delete","Message",JOptionPane.YES_NO_OPTION);
            
                if (ok==0){
                    model.removeRow(row);
                }
                }
            Total();
    }//GEN-LAST:event_BtnKurangActionPerformed

    private void BTNHAPUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNHAPUSActionPerformed
        Peminjaman pjm = new Peminjaman(CBANGGOTA.getSelectedItem().toString(),dataTabel,CBBUKU.getSelectedItem().toString(), dateFormat.format(tgl), "");
        pjm.delete();
        showDataPeminjaman();
        inisiasi();
        autoNumber();
    }//GEN-LAST:event_BTNHAPUSActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
       inisiasi();
       hapustable();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void BTNSIMPANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSIMPANActionPerformed
        simpandetail();
        simpanPeminjaman();
        inisiasi();
        JOptionPane.showMessageDialog(this, "Data berhasil tersimpan");
        showDataPeminjaman();
        hapustable();
    }//GEN-LAST:event_BTNSIMPANActionPerformed

    private void CBBUKUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBBUKUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBBUKUActionPerformed

    private void CBANGGOTAItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBANGGOTAItemStateChanged
        // TODO add your handling code here:
         tampilDataAnggota();
    }//GEN-LAST:event_CBANGGOTAItemStateChanged

    private void CBBUKUItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBBUKUItemStateChanged
        // TODO add your handling code here:
        tampilDataBuku();
    }//GEN-LAST:event_CBBUKUItemStateChanged

    private void TXTKODEPINJAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTKODEPINJAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTKODEPINJAMActionPerformed

    private void TPEMINJAMANMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TPEMINJAMANMouseClicked
        // TODO add your handling code here:
         int baris = TPEMINJAMAN.rowAtPoint(evt.getPoint());
         dataTabel = TPEMINJAMAN.getValueAt(baris, 0).toString();
        
      
    }//GEN-LAST:event_TPEMINJAMANMouseClicked

    private void BTNEDITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNEDITActionPerformed
        // TODO add your handling code here:
        
        CBANGGOTA.removeAllItems();
        CBBUKU.removeAllItems();
        CBANGGOTA.addItem("Pilih");
        CBBUKU.addItem("Pilih");
        showDataPeminjaman();
       tampilKodeAnggota();
       tampilKodeBuku();
       autoNumber();
    }//GEN-LAST:event_BTNEDITActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNEDIT;
    private javax.swing.JButton BTNHAPUS;
    private javax.swing.JButton BTNSIMPAN;
    private javax.swing.JButton BTNTAMBAH;
    private javax.swing.JButton BtnKurang;
    private javax.swing.JComboBox<String> CBANGGOTA;
    private javax.swing.JComboBox<String> CBBUKU;
    private com.toedter.calendar.JDateChooser JTANGGAL;
    private javax.swing.JTable TPEMINJAMAN;
    private javax.swing.JTable TPINJAM;
    private javax.swing.JTextField TXTFAKULITAS;
    private javax.swing.JTextField TXTJUDULBUKU;
    private javax.swing.JTextField TXTJUMLAHPINJAM;
    private javax.swing.JTextField TXTJURUSAN;
    private javax.swing.JTextField TXTKODEPINJAM;
    private javax.swing.JTextField TXTNAMAANGGOTA;
    private javax.swing.JTextField TXTPENGARANG;
    private javax.swing.JTextField TXTTOTALPINJAM;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}

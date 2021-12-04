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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class PengembalianForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form PengembalianForm
     */
      Connection conn = null;
       Statement s = null;
       public int Keterlambatan,jumlahpinjam,Denda,Hasil;//untuk deklrasi simpan penjuualan
    public PengembalianForm() {
        initComponents();
          this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        autoNumber();
        showDataPengembalian();
         TgldiText();
         tampilKodePeminjaman();
    }
    public void tampilKodePeminjaman(){
         try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = conn.createStatement();
            String sql = "select kd_peminjaman from peminjaman order by kd_peminjaman asc";
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
               CBKODEPINJAM.addItem(rs.getString("kd_peminjaman"));
            }
           
            conn.close();
            s.close(); 
        } catch (SQLException e) {
             JOptionPane.showConfirmDialog(this, e);
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(PeminjamanFrom.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
     private void autoNumber()  {
        String noPGM = "PGM000";
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = (com.mysql.jdbc.Statement) conn.createStatement();
            String sql = "select kd_pengembalian from pengembalian";
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                noPGM = rs.getString("kd_pengembalian");
            }
            noPGM = noPGM.substring(3);
            i = Integer.parseInt(noPGM)+1;
            noPGM = "00"+i;
            noPGM = "PGM"+noPGM.substring(noPGM.length()-3);
            TXTKODEKEMBALI.setText(noPGM);
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(this, e);
        } catch (ClassNotFoundException ex) {
              Logger.getLogger(BukuForm.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }
     public void tampilDataPeminjaman(){
         try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = conn.createStatement();
            String sql = "select * from peminjaman where kd_peminjaman='"+CBKODEPINJAM.getSelectedItem()+"'";
            ResultSet rs = s.executeQuery(sql);
             if (rs.next()) {
                 txtTanggalPinjam.setText(rs.getString("tgl_pinjam").toString());
                 TXTKODEANGGOTA.setText(rs.getString("kd_mhs").toString());
                 TXTJUMLAHPINJAM.setText(rs.getString("jml_pinjam").toString());
                 txtTglKembali.setText(rs.getString("tgl_kembali").toString());
             }
            
            conn.close();
            s.close(); 
        } catch (SQLException e) {
             JOptionPane.showConfirmDialog(this, e);
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(PeminjamanFrom.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
     public void tampilDenda(){
           // TODO add your handling code here:
               DateFormat df = new SimpleDateFormat("dd-MM-yyy");
        try{

            //Konversi dari string ke tanggal
            java.util.Date tanggalpinjam = df.parse(txtTglKembali.getText());
            java.util.Date tanggalkembali = df.parse(txtTanggalKembali.getText());

            //Tgl di konversi ke milidetik
            long Hari1 = tanggalpinjam.getTime();
            long Hari2 = tanggalkembali.getTime();
            long diff = Hari2 - Hari1;
            long Lama = diff / (24 * 60 * 60 * 1000);
            if (Lama < 0) {
                TXTKETERLAMBATAN.setText("0");
            }else{
                 TXTKETERLAMBATAN.setText(Long.toString(Lama));
             }
           

           Keterlambatan=Integer.parseInt(TXTKETERLAMBATAN.getText());
            
        
             Hasil=3000*Keterlambatan;
             if (Hasil < 0) {
                txtdenda.setText("0");
            }else{
                txtdenda.setText(String.valueOf(Hasil));
             }
            
            } catch (ParseException e)
            {
                e.printStackTrace();
            }
    }
     public void TgldiText(){
        Date tgl=new Date();
        SimpleDateFormat Kembali=new SimpleDateFormat("dd-MM-yyy");
        txtTanggalKembali.setText(Kembali.format(tgl));
       }
     private void showDataPengembalian(){
         DefaultTableModel model = new DefaultTableModel();
         model.addColumn("Kode Pengembalian");
         model.addColumn("Kode Peminjaman");
         model.addColumn("Kode Peminjam");
         model.addColumn("Tanggal Pinjam");
         model.addColumn("Tanggal Kembali");
         model.addColumn("Jumlah Pinjam");
         model.addColumn("Keterlambatan");
           model.addColumn("Denda");
         
         try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (com.mysql.jdbc.Connection)  DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = (com.mysql.jdbc.Statement) conn.createStatement();
            String sql = "select *from pengembalian order by kd_pengembalian asc ";
             ResultSet rs = s.executeQuery(sql);
             
             while (rs.next()) {                 
                 model.addRow(new Object[]{
                     rs.getString("kd_pengembalian"),
                     rs.getString("kd_pinjam"),
                     rs.getString("kode_mhs"),
                     rs.getString("tgl_pinjam"),
                     rs.getString("tgl_kembali"),
                      rs.getString("jml_pinjam"),
                        rs.getString("keterlambatan"),
                          rs.getString("denda")
                 });
             }
             TPENGEMBALIAN.setModel(model);
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
      private void showDataDetailPeminjaman(){
         DefaultTableModel model = new DefaultTableModel();
         model.addColumn("Kode Pinjam");
         model.addColumn("Kode Buku");
         model.addColumn("Judul Buku");
         model.addColumn("Penulis");
         model.addColumn("Jumlah Pinjam");
         
         try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (com.mysql.jdbc.Connection)  DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
            s = (com.mysql.jdbc.Statement) conn.createStatement();
            String sql = "select *from detail_peminjaman where kd_pinjam='"+CBKODEPINJAM.getSelectedItem()+"'  order by kd_pinjam asc ";
             ResultSet rs = s.executeQuery(sql);
             
             while (rs.next()) {                 
                 model.addRow(new Object[]{
                     rs.getString("kd_pinjam"),
                     rs.getString("kd_buku"),
                     rs.getString("judul_buku"),
                     rs.getString("penulis"),
                      rs.getString("jml_pinjam"),
                 });
             }
             TdetailPengembalian.setModel(model);
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
     private void simpanPengembalian(){
       try {
                int i=0;
                 Class.forName("com.mysql.jdbc.Driver");
                  conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/e_library","root","");
                   s = conn.createStatement();
                   String sql1 = "insert into pengembalian"
                            + "(kd_pengembalian,kd_pinjam,kode_mhs,tgl_pinjam,tgl_kembali,jml_pinjam,keterlambatan, denda)"
                            + "values('"+TXTKODEKEMBALI.getText()+"',"
                            +"'"+CBKODEPINJAM.getSelectedItem().toString()+"',"
                            + "'"+TXTKODEANGGOTA.getText()+"',"
                            + "'"+txtTanggalPinjam.getText()+"',"
                            + "'"+txtTglKembali.getText()+"',"
                            + "'"+TXTJUMLAHPINJAM.getText()+"',"
                            + "'"+TXTKETERLAMBATAN.getText()+"',"
                            + "'"+txtdenda.getText()+"')";
                   String sql2 = "delete from peminjaman where kd_peminjaman= '"+CBKODEPINJAM.getSelectedItem().toString()+"'";
                   String sql3 = "delete from detail_peminjaman where kd_pinjam= '"+CBKODEPINJAM.getSelectedItem().toString()+"'";
                   s.addBatch(sql2);
                   s.addBatch(sql3);
                   s.addBatch(sql1);
                   
                   s.executeBatch();
                    JOptionPane.showMessageDialog(rootPane, "Berhasil Menyimpan..!!");;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Gagal Menyimpan..!!"+e);;
            }
    }
     public void refresh(){
         CBKODEPINJAM.removeAllItems();
        CBKODEPINJAM.addItem("Pilih");
        tampilKodePeminjaman();
        tampilDataPeminjaman();
        showDataDetailPeminjaman();
     }
     public void inisiasi(){
         txtTanggalPinjam.setText("");
         txtTglKembali.setText("");
         TXTKODEANGGOTA.setText("");
         TXTJUMLAHPINJAM.setText("");
         TXTKETERLAMBATAN.setText("");
         txtdenda.setText("0,");
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
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        BtnHapus = new javax.swing.JButton();
        BtnBatal = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TXTKODEKEMBALI = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtdenda = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CBKODEPINJAM = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        TXTKODEANGGOTA = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TXTJUMLAHPINJAM = new javax.swing.JTextField();
        txtTanggalPinjam = new javax.swing.JTextField();
        txtTglKembali = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TPENGEMBALIAN = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        TXTKETERLAMBATAN = new javax.swing.JTextField();
        txtTanggalKembali = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Btnsimpan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TdetailPengembalian = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 131, 3));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Ketelambatan ");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, -1, -1));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("3000/hari");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, -1, -1));

        BtnHapus.setBackground(new java.awt.Color(163, 87, 9));
        BtnHapus.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        BtnHapus.setForeground(new java.awt.Color(255, 255, 255));
        BtnHapus.setText("Refresh");
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        jPanel1.add(BtnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 370, 80, 40));

        BtnBatal.setBackground(new java.awt.Color(163, 87, 9));
        BtnBatal.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        BtnBatal.setForeground(new java.awt.Color(255, 255, 255));
        BtnBatal.setText("BATAL");
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        jPanel1.add(BtnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 430, 80, 40));

        jPanel2.setBackground(new java.awt.Color(163, 87, 9));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("KODE PENGEMBALIAN");

        TXTKODEKEMBALI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTKODEKEMBALIKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(TXTKODEKEMBALI, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TXTKODEKEMBALI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("DENDA");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, -1, -1));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Keterangan :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, -1));

        txtdenda.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        txtdenda.setForeground(new java.awt.Color(255, 255, 255));
        txtdenda.setText("0,");
        jPanel1.add(txtdenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tgl/ PENGEMBALIAN");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, -1));

        jPanel3.setBackground(new java.awt.Color(163, 87, 9));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("KODE ANGGOTA");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("KODE PEMINJAM");

        CBKODEPINJAM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", " " }));
        CBKODEPINJAM.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBKODEPINJAMItemStateChanged(evt);
            }
        });
        CBKODEPINJAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBKODEPINJAMActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tgl/PINJAM");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("JUMLAH PINJAM");

        TXTJUMLAHPINJAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTJUMLAHPINJAMActionPerformed(evt);
            }
        });

        txtTglKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTglKembaliActionPerformed(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tgl/KEMBALI");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TXTJUMLAHPINJAM, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(txtTglKembali)
                    .addComponent(TXTKODEANGGOTA)
                    .addComponent(CBKODEPINJAM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTanggalPinjam))
                .addGap(47, 47, 47))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CBKODEPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(TXTKODEANGGOTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(TXTJUMLAHPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 180));

        TPENGEMBALIAN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "KODE PENGEMBALIAN", "TANGGAL KEMBALI", "KODE PINJAM", "TANGGAL PINJAM", "NAMA ANGGOTA", "JUMLAH PINJAM", "KETERLAMBATAN", "DENDA"
            }
        ));
        jScrollPane1.setViewportView(TPENGEMBALIAN);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 740, 120));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("KETERLAMBATAN");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, -1, -1));

        TXTKETERLAMBATAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTKETERLAMBATANActionPerformed(evt);
            }
        });
        jPanel1.add(TXTKETERLAMBATAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 170, -1));

        txtTanggalKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTanggalKembaliActionPerformed(evt);
            }
        });
        jPanel1.add(txtTanggalKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 170, -1));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("/Hari");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 100, -1, -1));

        Btnsimpan.setBackground(new java.awt.Color(163, 87, 9));
        Btnsimpan.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        Btnsimpan.setForeground(new java.awt.Color(255, 255, 255));
        Btnsimpan.setText("SIMPAN");
        Btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(Btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 310, 80, 40));

        TdetailPengembalian.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(TdetailPengembalian);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 730, 130));

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Pengembalian");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 270, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        // TODO add your handling code here:
        refresh();
        inisiasi();
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
     
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void TXTKODEKEMBALIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTKODEKEMBALIKeyPressed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_TXTKODEKEMBALIKeyPressed

    private void CBKODEPINJAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBKODEPINJAMActionPerformed
        // TODO add your handling code here:
  
    }//GEN-LAST:event_CBKODEPINJAMActionPerformed

    private void TXTJUMLAHPINJAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTJUMLAHPINJAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTJUMLAHPINJAMActionPerformed

    private void TXTKETERLAMBATANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTKETERLAMBATANActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTKETERLAMBATANActionPerformed

    private void txtTanggalKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTanggalKembaliActionPerformed
        // TODO add your handling code here:
        tampilDenda();
    }//GEN-LAST:event_txtTanggalKembaliActionPerformed

    private void BtnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnsimpanActionPerformed
        // TODO add your handling code here:
        simpanPengembalian();
        showDataPengembalian();
        refresh();
        inisiasi();
    }//GEN-LAST:event_BtnsimpanActionPerformed

    private void CBKODEPINJAMItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBKODEPINJAMItemStateChanged
        // TODO add your handling code here:
        tampilDataPeminjaman();
        showDataDetailPeminjaman();
    }//GEN-LAST:event_CBKODEPINJAMItemStateChanged

    private void txtTglKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTglKembaliActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtTglKembaliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBatal;
    private javax.swing.JButton BtnHapus;
    private javax.swing.JButton Btnsimpan;
    private javax.swing.JComboBox<String> CBKODEPINJAM;
    private javax.swing.JTable TPENGEMBALIAN;
    private javax.swing.JTextField TXTJUMLAHPINJAM;
    private javax.swing.JTextField TXTKETERLAMBATAN;
    private javax.swing.JTextField TXTKODEANGGOTA;
    private javax.swing.JTextField TXTKODEKEMBALI;
    private javax.swing.JTable TdetailPengembalian;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtTanggalKembali;
    private javax.swing.JTextField txtTanggalPinjam;
    private javax.swing.JTextField txtTglKembali;
    private javax.swing.JLabel txtdenda;
    // End of variables declaration//GEN-END:variables
}

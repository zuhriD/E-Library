/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.library;

/**
 *
 * @author LENOVO
 */
public class Peminjaman {
    private String kd_mhs, kd_peminjaman, kode_buku, tgl_pinjam,tgl_kembali,status;
    Database db = new Database();

    Peminjaman(String m,String p, String b, String t, String tg){
        setKd_mhs(m);
        setKd_peminjaman(p);
        setKode_buku(b);
        setTgl_pinjam(t);
        setTgl_kembali(tg);
        setStatus("peminjaman");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getKd_mhs() {
        return kd_mhs;
    }

    public void setKd_mhs(String kd_mhs) {
        this.kd_mhs = kd_mhs;
    }

    public String getKd_peminjaman() {
        return kd_peminjaman;
    }

    public void setKd_peminjaman(String kd_peminjaman) {
        this.kd_peminjaman = kd_peminjaman;
    }

    public String getKode_buku() {
        return kode_buku;
    }

    public void setKode_buku(String kode_buku) {
        this.kode_buku = kode_buku;
    }

    public String getTgl_pinjam() {
        return tgl_pinjam;
    }

    public void setTgl_pinjam(String tgl_pinjam) {
        this.tgl_pinjam = tgl_pinjam;
    }

    public String getTgl_kembali() {
        return tgl_kembali;
    }

    public void setTgl_kembali(String tgl_kembali) {
        this.tgl_kembali = tgl_kembali;
    }
     public void update(){
        db.update(0, getKd_mhs(), getKd_peminjaman(), getKode_buku(), getTgl_pinjam(), getTgl_kembali(), "", getStatus(), "", "", 0);
    }
      public void delete(){
        db.delete(getKd_peminjaman(),getStatus(),0);
    }
}

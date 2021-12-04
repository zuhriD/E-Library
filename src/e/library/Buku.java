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
public class Buku {
    private String kd_buku;
    private String judul_buku, penulis, penerbit, tahun_terbit, kategori, status;
    private String nama_image, image_path;
    Database db = new Database();

    public String getKd_buku() {
        return kd_buku;
    }

    public void setKd_buku(String kd_buku) {
        this.kd_buku = kd_buku;
    }

    public String getJudul_buku() {
        return judul_buku;
    }

    public void setJudul_buku(String judul_buku) {
        this.judul_buku = judul_buku;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getTahun_terbit() {
        return tahun_terbit;
    }

    public void setTahun_terbit(String tahun_terbit) {
        this.tahun_terbit = tahun_terbit;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNama_image() {
        return nama_image;
    }

    public void setNama_image(String nama_image) {
        this.nama_image = nama_image;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
    Buku(String kd, String jd, String pnl, String pnr, String thn, String ktg, String ni, String np){
        setKd_buku(kd);
        setJudul_buku(jd);
        setPenulis(pnl);
        setPenerbit(pnr);
        setTahun_terbit(thn);
        setKategori(ktg);
        setNama_image(ni);
        setImage_path(np);
    }
    public void insert(){
        setStatus("buku");
       db.insert(0,getKd_buku(),getJudul_buku(),getPenulis(),getPenerbit(),getTahun_terbit(),getKategori(),getStatus(),getNama_image(),getImage_path(),0);
        
    }
    public void update(){
        setStatus("buku");
       db.update(0,getKd_buku(),getJudul_buku(),getPenulis(),getPenerbit(),getTahun_terbit(),getKategori(),getStatus(),getNama_image(),getImage_path(),0);
    }
     public void delete(){
        setStatus("buku");
       db.delete(getKd_buku(),getStatus(),0);
    }
}

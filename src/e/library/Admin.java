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
public class Admin extends Orang{
    Admin(String n, String kd,int a, String us, String pas,String jab,String im, String s){
        setKode_admin(n);
        setNama(kd);
        setId_role(a);
        setUsername(us);
        setPassword(pas);
        setJabatan(jab);
        setStatus("admin");
        setNama_image(im);
        setImage_path(s);
    }
    private String jabatan, kode_admin;
    Database db = new Database();

    public String getKode_admin() {
        return kode_admin;
    }

    public void setKode_admin(String kode_admin) {
        this.kode_admin = kode_admin;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
    @Override
    public void insert(){
      db.insert(0, getNama(), getKode_admin(), getUsername(), getPassword(), 
              getJabatan(), "", getStatus(), getNama_image(), getImage_path(), getId_role());
    }
    @Override
     public void update(){
      db.update(0, getNama(), getKode_admin(), getUsername(), getPassword(), 
              getJabatan(), "", getStatus(),getNama_image(), getImage_path(), getId_role());
    }
    @Override
       public void delete(){
      db.delete(getKode_admin(), getStatus(),0);
    }
       @Override
    public void register(){
      db.insert(0, getNama(), getKode_admin(), getUsername(), getPassword(), 
              getJabatan(), "", getStatus(), getNama_image(), getImage_path(), getId_role());
    }
}

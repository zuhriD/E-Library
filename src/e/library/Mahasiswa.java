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
public class Mahasiswa extends Orang{
    Mahasiswa(String kd,String nm, String us, String pas, String fak, String jur,String s, String a){
        setId_mhs(kd);
        setNama(nm);
        setUsername(us);
        setPassword(pas);
        setFakulitas(fak);
        setJurusan(jur);
        setNama_image(s);
        setImage_path(a);
        setId_role(2);
        setStatus("mahasiswa");
    }
    private String id_mhs;
   
    private String fakulitas, jurusan;
    Database db = new Database();

   
    
    public String getId_mhs() {
        return id_mhs;
    }

    public void setId_mhs(String id_mhs) {
        this.id_mhs = id_mhs;
    }
    
    public String getFakulitas() {
        return fakulitas;
    }

    public void setFakulitas(String fakulitas) {
        this.fakulitas = fakulitas;
    }

    public String getJurusan() {
        return jurusan;
    }
    @Override
    public String getStatus(){
        return status;
    }
    @Override
     public void setStatus(String status) {
        this.status = status;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
    @Override
    public void insert(){
        db.insert(0,getId_mhs(), getNama(), getUsername(), getPassword(), 
                getFakulitas(), getJurusan(), getStatus(),getNama_image(),getImage_path(),getId_role());
    }
    @Override
     public void update(){
        db.update(0,getId_mhs(), getNama(), getUsername(), getPassword(), 
                getFakulitas(), getJurusan(), getStatus(),getNama_image(),getImage_path(),getId_role());
    }
    @Override
     public void delete(){
         db.delete(getId_mhs(), getStatus(),0 );
     }
    @Override
     public void register(){
          db.register(0,getId_mhs(), getNama(), getUsername(), getPassword(), 
                getFakulitas(), getJurusan(), getStatus(),getNama_image(),getImage_path(),getId_role());
     }
}

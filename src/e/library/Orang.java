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
public class Orang {

    String nama;
    protected String username, password, status;
    private String nama_image, image_path;
    private int id_role;

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
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }
    public void insert(){
    }
     public void update(){
      }
     public void delete(){
        }
    public void register(){
    
    }
}

package com.example.mobile_crud;

public class RecyclerViewData {
    private String id;
    private String nama;
    private String nim;
    private String no_telp;
    private String email;
    public RecyclerViewData() {

    }

    public RecyclerViewData(String id, String nama, String nim, String no_telp, String email) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.no_telp = no_telp;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

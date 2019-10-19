/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minggu8;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author choirulandriansyah
 */
public class Mobil implements java.io.Serializable {
    private String merk;
    private String nama;
    private double harga;
    private transient String code;
    
    public Mobil() { }
    
    public Mobil(String code, String merk, String nama, double harga){
        this.code = code;
        this.merk = merk;
        this.nama = nama;
        this.harga = harga;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getMerk() {
        return merk;
    }
    
    public void setMerk(String merk) {
        this.merk = merk;
    }
    
    public double getHarga() {
        return harga;
    }
    
    public void setHarga(double harga) {
        this.harga = harga;
    }
    
    public boolean validasiHarga(String harga){
        for(char c: harga.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "Mobil <code:"+code+", merk: "+merk+", nama: "+nama+", harga: "+String.valueOf(harga)+">";
    }
}

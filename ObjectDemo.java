/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minggu8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author choirulandriansyah
 */
public class ObjectDemo {
    public static String filename = "Mobil.o";
    private Mobil car;
    private MobilGui view;
    private List<Mobil> listmobil = new ArrayList<Mobil>();
    
    public ObjectDemo(MobilGui view){
        this.view = view;
        this.view.getRead().addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 try {
                     bacaObject();
                 } catch (IOException ex) {
                     Logger.getLogger(ObjectDemo.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(ObjectDemo.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             });
        this.view.getjButton1().addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 try {
                     addTable();
                 } catch (IOException ex) {
                     Logger.getLogger(ObjectDemo.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(ObjectDemo.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             });
        
        this.view.getSave().addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 try {
                     simpanObject();
                 } catch (IOException ex) {
                     Logger.getLogger(ObjectDemo.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             });
        
        this.view.getClear().addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                clearTable();
             }
             });
    }
    
//    public static void main(String[] cho){
        
//        Mobil m = new Mobil();
//        
//        m.setCode("CV-R");
//        m.setMerk("Honda");
//        m.setNama("CRV Type R");
//        m.setHarga(1200000000);
//        
//        System.err.println(m);
//        
//        try {
//            simpanObject(listmobil);
//            Mobil n = bacaObject();
//            System.out.println(n);
//        } catch (IOException ex) {
//            Logger.getLogger(ObjectDemo.class.getName()).log(Level.SEVERE,
//            null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(ObjectDemo.class.getName()).log(Level.SEVERE,
//            null, ex);
//        }
//        }
    
        private void simpanObject() throws
            FileNotFoundException, IOException{
            FileOutputStream fout;
            fout = new FileOutputStream(filename);
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            oout.writeObject(listmobil);
        }
        
        private void bacaObject() throws FileNotFoundException,
            IOException, ClassNotFoundException{
            ObjectInputStream ois;
            ois = new ObjectInputStream(new FileInputStream(filename));
            List<Mobil> mobil = (List<Mobil>) ois.readObject();
            listmobil = mobil;
            loadTable();
            
        }
        
        private void addTable() throws FileNotFoundException, IOException, ClassNotFoundException {
            String kode = this.view.getKode().getText();
            String merk = this.view.getMerk().getText();
            String nama = this.view.getNama().getText();
            String harga = this.view.getHarga().getText();
            Mobil mobil = new Mobil();
        
            if(car.validasiHarga(harga)){
               mobil.setCode(kode);
               mobil.setNama(nama);
               mobil.setMerk(merk);
               mobil.setHarga(Double.parseDouble(harga));
               listmobil.add(mobil);
               loadTable();
                this.view.getKode().setText("");
                this.view.getNama().setText("");
                this.view.getMerk().setText("");
                 this.view.getHarga().setText("");
            }else{
               JOptionPane.showMessageDialog(view, "Harga Bukan Angka", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        private void loadTable() {
            DefaultTableModel model = (DefaultTableModel)this.view.getjTable1().getModel();
            model.setRowCount(0);
            int i = 1;
            for(Mobil mobil : listmobil){
                System.err.println(mobil.toString());
                 Vector row = new Vector();
                row.add(i);
                row.add(mobil.getCode());
                row.add(mobil.getMerk());
                row.add(mobil.getNama());
                row.add(mobil.getHarga());
                model.addRow(row);
                i++;
            }
            this.view.getjTable1().setModel(model);
            System.out.println(listmobil);
        }
        
        private void clearTable(){
            DefaultTableModel model = (DefaultTableModel)  this.view.getjTable1().getModel();
            model.setRowCount(0);   
        
        }
    
}

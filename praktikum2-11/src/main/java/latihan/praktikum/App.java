/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package latihan.praktikum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Putra Tarihoran
 */
public class App {
        public static void main(String[] x) throws Exception {
        //1. buat variabel bertipe FileReader
        String namafile = "src/main/resources/data_mahasiswa.csv";
        FileReader fr = new FileReader (namafile);
        
        //2. Bungkus dalam BufferedReader supaya ada method radLine
        BufferedReader reader = new BufferedReader (fr);
        
        //3. Looping, baca data, dan tampilkan
        String data = reader.readLine(); //header, ignore
        data = reader.readLine();
        
        // 0. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/mahasiswa";
        String dbUser = "root";
        String dbPass = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        
        while(data != null){
            System.out.println("========================");
            String[] data_mahasiswa = data.split(",");
            String sql = "INSERT INTO mahasiswa (id,nim,nama,telp,angkatan,alamat) values (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, data_mahasiswa[0]);
            ps.setString(2, data_mahasiswa[1]);
            ps.setString(3, data_mahasiswa[2]);
            ps.setString(4, data_mahasiswa[3]);
            ps.setString(5, data_mahasiswa[4]);
            ps.setString(6, data_mahasiswa[5]);
            ps.executeUpdate();
            data = reader.readLine();
        }
        //4. Tutup file
        reader.close();
        conn.close();
    }
}

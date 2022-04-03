package service;

import java.io.*;
import java.sql.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class FileHandler {
    /**
     * @param file (String) - Path and name of the file
     * */
    public static byte[] readImageToByteStream(String file,String fileName) throws Exception{
        if(file.length() == 0){
            throw new Exception("No name provided.");
        }

        BufferedImage bufferedImage = ImageIO.read(new File(file));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, file.substring(file.length() - 3), byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();

        return data;
    }

    /**
     *
     * @param file (byte[]) - The file converted to a byte stream
     * @throws SQLException
     */
    public static int uploadFile(byte[] file) throws SQLException {
        Connection con = PostgresConnection.initializePostgresqlDatabase();
        PreparedStatement query = null;

        if(con == null)
            System.out.println("Failed to connect to the database.");

            String sql = "INSERT INTO photos (data) VALUES (?)";

            query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setBytes(1, file);
            System.out.println(file);
            query.executeUpdate();

            ResultSet rs = query.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                System.out.println(generatedKey);
                return generatedKey;
            }

            return  0;
    }

    /**
     *
     * @param id - (int) The row id, where the file is (Will be changed to be able to search by name)
     * @throws SQLException
     * @throws NullPointerException
     * */
    public static InputStream getFile(int id) throws Exception {
        Connection con = PostgresConnection.initializePostgresqlDatabase();
        PreparedStatement query = null;

        if(con == null)
            System.out.println("Failed to connect to the database.");

        String sql = "SELECT data FROM photos WHERE id=? ORDER BY id ASC LIMIT 1";

        query = con.prepareStatement(sql);
        query.setInt(1, id);

        ResultSet res = query.executeQuery();

        res.next();
        InputStream data = res.getBinaryStream(1);
        return data;
    }
}

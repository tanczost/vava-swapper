package service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class FileHandler {
    /**
     * @param file (String) - Path and name of the file
     * */
    public static byte[] readImageToByteStream(String file) throws Exception{
        if(file.length() == 0){
            throw new Exception("No name provided.");
        }

        BufferedImage image = ImageIO.read(new File(file));
        ByteArrayOutputStream imageInBytes = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", imageInBytes);
        return imageInBytes.toByteArray();
    }

    /**
     * @param bStream (byte[]) - The byte stream, from which the actual file should be created
     * */
    public static BufferedImage createImageFromByteStream(byte[] bStream) throws IOException {
        if(bStream == null)
            throw new NullPointerException("Empty input byte stream.");

        ByteArrayInputStream bufferedStream = new ByteArrayInputStream(bStream);
        return ImageIO.read(bufferedStream);
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
    public static byte[] getFile(int id, String name) throws SQLException, NullPointerException {
        Connection con = PostgresConnection.initializePostgresqlDatabase();
        PreparedStatement query = null;
        byte[] data = null;

        if(con == null)
            System.out.println("Failed to connect to the database.");
        try {
            String sql = "SELECT * FROM photos WHERE id=? OR name=? ORDER BY id ASC LIMIT 1";

            query = con.prepareStatement(sql);
            query.setInt(1, id);
            query.setString(2, name);
            ResultSet res = query.executeQuery();
            res.next();

            data = res.getBytes("data");
        } catch (SQLException ignored) {
        }catch(NullPointerException np){
            System.out.println("No data for the file found.");
            throw new NullPointerException("No data for the file found.");
        }

        return data;
    }
}

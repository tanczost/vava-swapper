package service;

import java.io.*;
import java.sql.*;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class FileHandler {
    /**
     * @param file (String) - Path and name of the file
     * */
    public static byte[] readImageToByteStream(String file) throws Exception{
        if(file.length() == 0){
            throw new Exception("No name provided.");
        }

        //File image = new File(file);
        //FileInputStream fileInputStream = new FileInputStream(image);
        BufferedImage bufferedImage = ImageIO.read(new File(file));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();

        return data;
    }

    /**
     * @param bStream (byte[]) - The byte stream, from which the actual file should be created
     * */
    public static BufferedImage createImageFromByteStream(byte[] bStream) throws Exception {
        if(bStream == null)
            throw new NullPointerException("Empty input byte stream.");

        //System.out.println(Arrays.toString(bStream));
        ByteArrayInputStream bufferedStream = new ByteArrayInputStream(bStream);
        //BufferedImage test = ImageIO.read(bufferedStream);

        /*byte[] testRead = readImageToByteStream("C:\\Users\\madre\\Documents\\GitHub\\vava-swapper\\src\\main\\resources\\test.png");
        ByteArrayInputStream buffTest = new ByteArrayInputStream(testRead);
        BufferedImage testReadIO = ImageIO.read(buffTest);

        ImageIO.write(testReadIO, "png", new File("output.png") );
        System.out.println("image created");*/

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
    public static byte[] getFile(int id) throws Exception {
        Connection con = PostgresConnection.initializePostgresqlDatabase();
        PreparedStatement query = null;

        if(con == null)
            System.out.println("Failed to connect to the database.");

        String sql = "SELECT data FROM photos WHERE id=? ORDER BY id ASC LIMIT 1";

        query = con.prepareStatement(sql);
        query.setInt(1, id);
        ResultSet res = query.executeQuery();

        res.next();
        byte[] data = res.getBytes(1);
        System.out.println(Arrays.toString(data));

        ImageIO.write(createImageFromByteStream(data), "jpg", new File("output2.jpg") );
        System.out.println("image created");

        return data;
    }
}

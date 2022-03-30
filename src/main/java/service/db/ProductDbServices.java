package service.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static service.PostgresConnection.connection;

public class ProductDbServices {
    public static int insertProductDb(String name, String description, boolean topped, int userId, int imgId,String category){
        try {
            //TODO: add img_id
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO products(name, description, topped, user_id,img_id, product_t) " +
                    "VALUES((?), (?), (?), (?), (?), (?));");

            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setBoolean(3, topped);
            stmt.setInt(4, userId);
            stmt.setInt(5, imgId);
            stmt.setString(6, category);

            return stmt.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return 0;
        }
    }

    public static int insertOfferDb(int proposalId, int offerId){
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO product_offers(proposal_id, offer_id) " +
                            "VALUES((?), (?));");

            stmt.setInt(1, proposalId);
            stmt.setInt(2, offerId);

            return stmt.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return 0;
        }
    }

    public static int updateProduct(int productId, String  name, String description, boolean top) throws SQLException {
        String params = "";

        if(!name.isEmpty()){
            params =params.concat("name = '" + name + "',");
        }

        if(!description.isEmpty()){
            params =params.concat("description = '" + description + "',");
        }

        if(top){
            params =params.concat("topped = true,");
        }
        else{
            params =params.concat("topped = false,");
        }

        if(!params.isEmpty()){
            params = params.substring(0, params.length() - 1);

        }

        PreparedStatement stmt = connection.prepareStatement(
                "UPDATE products SET " +
                        params +
                        " WHERE id =(?);");

        stmt.setInt(1,productId);


        return stmt.executeUpdate();
    }

    public static ResultSet getProductById(int productId) throws SQLException {
        //TODO check if sometyhing was returned
            PreparedStatement stmt = connection.prepareStatement("SELECT *" +
                    "FROM products WHERE id = (?)");

            stmt.setInt(1, productId);
            ResultSet sqlReturnValues = stmt.executeQuery();
            return sqlReturnValues;
    }

    public static ResultSet getUsersProposals(int userId) throws SQLException {
        //TODO check if sometyhing was returned
            PreparedStatement stmt = connection.prepareStatement("SELECT *" +
                    "FROM products WHERE user_id = (?)");

            stmt.setInt(1, userId);


            ResultSet sqlReturnValues = stmt.executeQuery();
            return sqlReturnValues;

    }

    public static ResultSet getOffersForProduct(int productId) throws SQLException {
        //TODO check if sometyhing was returned
        PreparedStatement stmt = connection.prepareStatement(
                "SELECT products.id, name, description, topped, img_id, user_id, created_at" +
                " FROM products JOIN product_offers on products.id = product_offers.offer_id " +
                " WHERE product_offers.proposal_id = (?)");

        stmt.setInt(1, productId);
        ResultSet sqlReturnValues = stmt.executeQuery();
        return sqlReturnValues;
    }
}

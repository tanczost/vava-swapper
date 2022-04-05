package service.db;

import service.PostgresConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;


public class ProductDbServices extends PostgresConnection {
    public static int insertProductDb(String name, String description, boolean topped, int userId, int imgId, String category) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO products(name, description, topped, user_id, img_id, category) " +
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

    public static int insertOfferDb(int proposalId, int offerId) {
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

    public static int updateProduct(int productId, String name, String description, boolean top) throws SQLException {
        String params = "";

        if (!name.isEmpty()) {
            params = params.concat("name = '" + name + "',");
        }

        if (!description.isEmpty()) {
            params = params.concat("description = '" + description + "',");
        }

        if (top) {
            params = params.concat("topped = true,");
        } else {
            params = params.concat("topped = false,");
        }

        if (!params.isEmpty()) {
            params = params.substring(0, params.length() - 1);

        }

        PreparedStatement stmt = connection.prepareStatement(
                "UPDATE products SET " +
                        params +
                        " WHERE id =(?);");

        stmt.setInt(1, productId);


        return stmt.executeUpdate();
    }

    public static ResultSet getProductById(int productId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT *" +
                "FROM products WHERE id = (?)");

        stmt.setInt(1, productId);
        ResultSet sqlReturnValues = stmt.executeQuery();

        if (isResultEmpty(sqlReturnValues)) {
            return null;
        }

        return sqlReturnValues;
    }

    public static ResultSet getUsersProposals(int userId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT *" +
                "FROM products WHERE user_id = (?)");

        stmt.setInt(1, userId);
        ResultSet sqlReturnValues = stmt.executeQuery();

        if (isResultEmpty(sqlReturnValues)) {
            return null;
        }

        return sqlReturnValues;
    }

    public static ResultSet getOffersForProduct(int productId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "SELECT products.id, name, description, topped, img_id, user_id, created_at" +
                        " FROM products JOIN product_offers on products.id = product_offers.offer_id " +
                        " WHERE product_offers.proposal_id = (?)");

        stmt.setInt(1, productId);
        ResultSet sqlReturnValues = stmt.executeQuery();

        if (isResultEmpty(sqlReturnValues)) {
            return null;
        }

        return sqlReturnValues;
    }

    public static ResultSet getProductsByName(String name) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT *" +
                "FROM products WHERE name LIKE %(?)%");

        stmt.setString(1, name);
        ResultSet sqlReturnValues = stmt.executeQuery();

        if (isResultEmpty(sqlReturnValues)) {
            return null;
        }

        return sqlReturnValues;
    }

    public static ResultSet getTopProduct()throws SQLException{
        PreparedStatement stmt = connection.prepareStatement("SELECT *" +
                "FROM products WHERE topped = true ORDER BY created_at LIMIT 1");

        ResultSet sqlReturnValues = stmt.executeQuery();

        if (isResultEmpty(sqlReturnValues)) {
            return null;
        }

        return sqlReturnValues;
    }

    public static void tradeProduct(int proposalId, int offerId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "DELETE FROM product_offers " +
                        "WHERE proposal_id = (?) OR offer_id = (?);"
        );

        stmt.setInt(1, proposalId);
        stmt.setInt(2, offerId);
        stmt.executeUpdate();

        stmt = connection.prepareStatement("DELETE FROM products WHERE id = (?);");
        stmt.setInt(1, proposalId);
        stmt.executeUpdate();


        stmt = connection.prepareStatement("DELETE FROM products WHERE id = (?);");
        stmt.setInt(1, offerId);
        stmt.executeUpdate();

    }

    /**
     * @param timeStampFrom (Instant) - From which creation date should the products be selected.
     * @param timeStampTo   (Instant) - Till which creation date should the products be selected.
     * @param category      (String)  - The products with this exact category will be selected.
     * @throws SQLException - On database connection issues.
     * @return ResultSet - The list of products which met the criteria
     * */
    public static ResultSet getFilteredProducts(Instant timeStampFrom, Instant timeStampTo, String category) throws SQLException {
        //TODO maybe return everything then?
        //dont query the database if no filters were set
        if(timeStampFrom == null && timeStampTo == null && category == null)
            return null;

        java.util.Date dateFrom = null;
        java.util.Date dateTo = null;
        if(timeStampFrom != null)
            dateFrom = (java.util.Date) java.util.Date.from(timeStampFrom);
        if(timeStampTo != null)
            dateTo = (java.util.Date) java.util.Date.from(timeStampTo);
        String sql = "SELECT * FROM products WHERE ";

        //Add the required specifiers
        if(timeStampFrom != null)
            sql = sql.concat("created_at>(?)");
        if(timeStampTo != null){
            if(timeStampFrom != null)
                sql = sql.concat(" AND ");
            sql = sql.concat("created_at<(?)");
        }
        if(category != null){
            if(timeStampFrom != null || timeStampTo != null)
                sql = sql.concat(" AND ");
            if(!category.equals("TOP"))
                sql = sql.concat("category='" + category + "'");
            else
                sql = sql.concat("topped=TRUE");
        }
        //TODO order by what?
        //TODO maybe limit?
        sql = sql.concat(" ORDER BY ID DESC");

        PreparedStatement stmt = connection.prepareStatement(sql);

        //Bind the data in the correct order/spot
        if(timeStampFrom != null){
            stmt.setDate(1, new java.sql.Date(dateFrom.getTime()));
        }
        if(timeStampTo != null){
            if(timeStampFrom != null)
                stmt.setDate(2, new java.sql.Date(dateTo.getTime()));
            else
                stmt.setDate(1, new java.sql.Date(dateTo.getTime()));
        }
        //TODO bind the category not set it straight
        /* if(category != null && !category.equals("TOP")){
            //only bind category, if we are not searching for products with TOPPED set to TRUE
            if(timeStampFrom != null && timeStampTo != null)
                stmt.setString(3, category);
            else if(timeStampFrom != null || timeStampTo != null)
                stmt.setString(2, category);
            else
                stmt.setString(1, category);
        } */


        return stmt.executeQuery();
    }
}

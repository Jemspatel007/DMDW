import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetItemsDetails {

    public static void fetchDetails(Connection remoteCon) {
        String fetchQuery = "SELECT * FROM Inventory";
        try (PreparedStatement st = remoteCon.prepareStatement(fetchQuery);
             ResultSet resultSet = st.executeQuery()) {
            long startTime = System.currentTimeMillis();
            while (resultSet.next()) {
                System.out.println("Item_id : " + resultSet.getInt("item_id") + " Item_name : " + resultSet.getString("item_name") +
                        " Available_quantity :" + resultSet.getInt("available_quantity"));
            }
            long endTime = System.currentTimeMillis();
            System.out.println("SQL query execution time for fetching details of items : " +
                    (endTime - startTime) / 1000.0 + " sec");
        } catch (SQLException e) {
            System.out.println("Error fetching item details: " + e.getMessage());
        }
    }
}

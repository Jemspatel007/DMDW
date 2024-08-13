import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateOrderInfo {
    public static void updateInfo(Connection localCon, String item_name, int item_quantity) {
        String orderDetailsQuery = "INSERT INTO Order_info(user_id, item_name, quantity, order_date) VALUES(?,?,?,?)";
        try (PreparedStatement st = localCon.prepareStatement(orderDetailsQuery)) {
            st.setInt(1, 3);
            st.setString(2, item_name);
            st.setInt(3, item_quantity);
            st.setString(4, "2024-01-28");
            long startTime = System.currentTimeMillis();
            st.executeUpdate();
            long endTime = System.currentTimeMillis();
            System.out.println("Order placed successfully.");
            System.out.println("SQL query execution time for inserting details into Order_info : " + (endTime - startTime) / 1000.0 + " sec ");
        } catch (SQLException e) {
            System.out.println("Error updating order info table: " + e.getMessage());
        }
    }
}

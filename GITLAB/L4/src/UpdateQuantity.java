import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateQuantity {
    public static void updateInventory(Connection databaseConnection, String itemName, int quantity) {
        String updateQuery = "UPDATE Inventory SET available_quantity = available_quantity - ? WHERE item_name = ?";
        try (PreparedStatement statement = databaseConnection.prepareStatement(updateQuery)) {
            statement.setInt(1, quantity);
            statement.setString(2, itemName);
            long startTime = System.currentTimeMillis();
            int rowsAffected = statement.executeUpdate();
            long endTime = System.currentTimeMillis();
            if (rowsAffected > 0) {
                System.out.println("Quantity updated successfully for item: " + itemName);
                System.out.println("SQL query execution time: " + (endTime - startTime) / 1000.0 + " seconds");
            } else {
                System.out.println("No rows updated. Item not found: " + itemName);
            }
        } catch (SQLException exception) {
            System.out.println("Error updating item quantity: " + exception.getMessage());
        }
    }
}

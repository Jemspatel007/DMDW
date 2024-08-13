import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String localUrl = "jdbc:mysql://localhost:3306/gcplocal";
        String localUsername = "root";
        String localPassword = "Jems@1205";
        String remoteUrl = "jdbc:mysql://35.222.91.18:3306/gcplab3";
        String remoteUsername = "root";
        String remotePassword = "Bholu@1205";

        try (Connection localCon = DriverManager.getConnection(localUrl, localUsername, localPassword);
             Connection remoteCon = DriverManager.getConnection(remoteUrl, remoteUsername, remotePassword)) {

            System.out.println("Databases connected successfully.");

            fetchItemDetails(remoteCon);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter item name : ");
            String item_name = scanner.nextLine();
            System.out.println("Enter quantity : ");
            int item_quantity = scanner.nextInt();

            updateOrderInfoTable(localCon, item_name, item_quantity);

            updateItemsQuantity(remoteCon, item_quantity, item_name);

        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    public static void fetchItemDetails(Connection remoteCon) {
        String fetchQuery = "SELECT * FROM Inventory";
        try (PreparedStatement st = remoteCon.prepareStatement(fetchQuery);
             ResultSet resultSet = st.executeQuery()) {
            long startTime = System.currentTimeMillis();
            while (resultSet.next()) {
                int item_id = resultSet.getInt("item_id");
                String item_name = resultSet.getString("item_name");
                int available_quantity = resultSet.getInt("available_quantity");
                System.out.println("Item_id : " + item_id + " Item_name : " + item_name +
                        " Available_quantity :" + available_quantity);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("SQL query execution time for fetching details of items : " +
                    (endTime - startTime) / 1000.0 + " sec");
        } catch (SQLException e) {
            System.out.println("Error fetching item details: " + e.getMessage());
        }
    }

    public static void updateItemsQuantity(Connection remoteCon, int quantity, String item_name) {
        String getAvailableQuantityQuery = "SELECT available_quantity FROM Inventory WHERE item_name = ?";
        String updateInventoryQuery = "UPDATE Inventory SET available_quantity = ? WHERE item_name = ?";
        try (PreparedStatement st1 = remoteCon.prepareStatement(getAvailableQuantityQuery);
             PreparedStatement st2 = remoteCon.prepareStatement(updateInventoryQuery)) {

            st1.setString(1, item_name);
            ResultSet resultSet = st1.executeQuery();
            int currentQuantity = 0;
            while (resultSet.next()) {
                currentQuantity = resultSet.getInt("available_quantity");
            }

            int remainingQuantity = currentQuantity - quantity;
            st2.setInt(1, remainingQuantity);
            st2.setString(2, item_name);
            long startTime = System.currentTimeMillis();
            st2.executeUpdate();
            long endTime = System.currentTimeMillis();
            System.out.println("SQL query execution time for updating item quantity info : "
                    + (endTime - startTime) / 1000.0 + " sec");
        } catch (SQLException e) {
            System.out.println("Error updating item quantity: " + e.getMessage());
        }
    }

    public static void updateOrderInfoTable(Connection localCon, String item_name, int item_quantity) {
        String orderDetailsQuery = "INSERT INTO Order_info(user_id, item_name, quantity, order_date) VALUES(?,?,?,?)";
        try (PreparedStatement st = localCon.prepareStatement(orderDetailsQuery)) {
            st.setInt(1, 3); // Example user_id
            st.setString(2, item_name);
            st.setInt(3, item_quantity);
            st.setString(4, "2024-01-28"); // Example order_date
            long startTime = System.currentTimeMillis();
            st.executeUpdate();
            long endTime = System.currentTimeMillis();
            System.out.println("SQL query execution time for inserting details into Order_info : " + (endTime - startTime) / 1000.0 + " sec ");
        } catch (SQLException e) {
            System.out.println("Error updating order info table: " + e.getMessage());
        }
    }
}
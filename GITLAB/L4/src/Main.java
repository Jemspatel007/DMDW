import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection remoteConnection = db.connectToRemoteDatabase();
            Connection localConnection = db.connectToLocalDatabase();

            System.out.println("Databases connected successfully.");

            GetItemsDetails.fetchDetails(remoteConnection);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter item name : ");
            String item_name = scanner.nextLine();
            System.out.println("Enter quantity : ");
            int item_quantity = scanner.nextInt();

            UpdateOrderInfo.updateInfo(localConnection, item_name, item_quantity);
            UpdateQuantity.updateInventory(remoteConnection,item_name,item_quantity);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
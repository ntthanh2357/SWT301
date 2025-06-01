import java.io.*;

import de180010.AccountService;

public class AccountServiceTest {
    public static void main(String[] args) {
        AccountService service = new AccountService();

        String inputFile = "src/test-data.csv";
        String outputFile = "UnitTest.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line = reader.readLine(); // Đọc dòng tiêu đề đầu tiên
            writer.write("username,password,email,expected,actual,result\n");

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1); // Đọc cả giá trị rỗng

                String username = parts[0].trim();
                String password = parts[1].trim();
                String email = parts[2].trim();
                boolean expected = Boolean.parseBoolean(parts[3].trim());

                boolean actual = service.registerAccount(username, password, email);
                boolean result = (actual == expected);

                writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                        username, password, email,
                        expected, actual, result ? "PASS" : "FAIL"));
            }

            System.out.println(" Unit test hoàn tất. Kết quả ghi vào UnitTest.csv");

        } catch (IOException e) {
            System.out.println(" Lỗi khi đọc hoặc ghi file:");
            e.printStackTrace();
        }
    }
}

import enums.Position;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import enums.Position;

public class EmployeeGenerator {

    private static final String inputFilePath = "resources/names.txt";


    public static List<Employee> generate() throws IOException {

        File file = new File(inputFilePath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        Random random = new Random();

        List<Employee> employees = new ArrayList<>();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] parsedName = line.split("  ");
            Employee employee = new Employee(
                    parsedName[0],
                    parsedName[1],
                    random.nextInt(18,80),
                    new BigDecimal(random.nextInt(2000, 3500)),
                    Position.getRandomPosition()
            );

            employees.add(employee);
            System.out.println(employee);
        }

        bufferedReader.close();

        return employees;
    }
}

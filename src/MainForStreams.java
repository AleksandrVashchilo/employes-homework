import enums.Position;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainForStreams {

    public static void main(String[] args) throws IOException {

       System.out.println(method(10_000) + " %");

        List<Employee> employees = EmployeeGenerator.generate();
//        System.out.println(employees.size());

//        Map<enums.Position, BigDecimal> averageSalariesForAllPositions = averageSalaryOnPosition(employees);
//        System.out.println(averageSalariesForAllPositions);

//        Map<enums.Position, Integer> countOfEmployeesOnEachPosition = numberOfEmployeesByPositions(employees);
//        System.out.println(countOfEmployeesOnEachPosition);

        Map<String, Integer> range = numberOfEmployeesByAge(employees);
//        System.out.println(range);
        range.forEach((s, integer) -> System.out.printf("In %s- %d employees\n", s, integer));
    }

    /**
     * 1 Напишите метод, который будет генерировать поток случайных чисел заданного размера
     * (в смысле размер потока, например 10 000 чисел) и определять долю нечетных чисел в нем.
     **/

    public static double method(long size) {
        Random random = new Random();
        IntStream stream = random.ints(size, 0, 10_000);
        double count = stream.filter(element -> element % 2 != 0)
                .count();
        double answer = count / size * 100;

        return answer;
    }

    /**
     * 2 Создайте метод, который будет принимать коллекцию с сотрудниками
     * (можно использовать класс Employee с урока, только создайте чуть больше объектов)
     * и на выходе выдавать мапу где ключом будет должность сотрудника, а значением - средняя заработная плата на этой должности.
     **/

    public static Map<Position, BigDecimal> averageSalaryOnPosition(List<Employee> list) {
        Map<Position, BigDecimal> map = new HashMap<>();

        Set<Position> positions = list.stream()
//                .map(Employee::getPosition)
                .map(emp -> emp.getPosition())
                .collect(Collectors.toSet());

        for (Position pos : positions) {
            double average = list.stream()
                    .filter(e -> e.getPosition().equals(pos))
//                    .map(Employee::getSalary)
                    .map(emp -> emp.getSalary())
//                    .mapToDouble(BigDecimal::doubleValue).average()
                    .mapToDouble(num -> num.doubleValue())
                    .average()
                    .orElse(0.0);
            map.put(pos, new BigDecimal(average).setScale(2, RoundingMode.HALF_UP));
        }

        return map;
    }

    /**
     * 3 Создайте метод, который будет принимать коллекцию с сотрудниками и на выходе выдавать мапу,
     * где ключом будет должность, а значением количество сотрудников, работающих в этой должности.
     **/

    public static Map<Position, Integer> numberOfEmployeesByPositions(List<Employee> list) {
        Map<Position, Integer> map = new HashMap<>();

        Set<Position> positions = list.stream()
                .map(Employee::getPosition)
                .collect(Collectors.toSet());

        for (Position pos : positions) {
            long count = list.stream()
                    .filter(emp -> emp.getPosition().equals(pos))
                    .count();
            map.put(pos, (int) count);
        }

        return map;
    }


    /**
     * 4 Создайте метод, который будет принимать коллекцию с сотрудниками и на выходе выдавать мапу,
     * где ключами будут отрезки возрастов по 10 лет, типа 20 - 30, 30 - 40 и тп, а значением количество сотрудников
     * в этом возрастном диапазоне. Можно еще одну вариацию, в значение посчитать долю сотрудников в этом возрасте
     * относительно всех сотрудников
     **/

    public static Map<String, Integer> numberOfEmployeesByAge(List<Employee> list) {

        String str1 = "Age 20 - 30 "; // ["Age 20 ", " 30 "] -> ["20", "30"]
        String str2 = "Age 31 - 40 ";
        String str3 = "Age 41 - 50 ";
        String str4 = "Age 51 - 60 ";
        String str5 = "Age 61 - 70 ";
        String str6 = "Age 71 - 80 ";

        List<String> range = List.of(str1, str2, str3, str4, str5, str6);

        Map<String, Integer> map = new HashMap<>();

        for (String str : range) {
            List<Integer> parsedInt = Arrays.stream(str.split("-"))
                    .map(s -> s.replaceAll("[^0-9]", ""))
//                    .map(Integer::valueOf)
                    .map(string -> Integer.valueOf(string))
                    .toList();
            long count = list.stream()
                    .filter(e -> e.getAge() >= parsedInt.get(0) && e.getAge() <= parsedInt.get(1))
                    .count();
            map.put(str, (int) count);
        }

        return map;
    }

}

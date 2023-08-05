import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            // Запрашиваем у пользователя данные
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите Фамилию Имя Отчество дату рождения номер телефона пол (разделенные пробелом): ");
            String input = scanner.nextLine();

            // Разделяем введенные данные по пробелам и сохраняем в массив строк
            String[] data = input.split(" ");

            // Проверяем, что количество данных соответствует ожидаемому (6 элементов)
            if (data.length != 6) {
                throw new Exception("Неверное количество данных");
            }

            // Получаем значения из массива и сохраняем в соответствующие переменные
            String surname = data[0];
            String name = data[1];
            String patronymic = data[2];
            String birthDate = data[3];
            long phoneNumber = Long.parseLong(data[4]);
            char gender = data[5].charAt(0);

            // Проверяем формат даты рождения
            if (!isValidDate(birthDate)) {
                throw new Exception("Неверный формат даты рождения");
            }

            // Проверяем, что пол равен 'f' или 'm'
            if (gender != 'f' && gender != 'm') {
                throw new Exception("Неверный формат пола");
            }

            // Создаем строку для записи в файл
            String record = surname + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender;

            // Создаем файл с названием равным фамилии и записываем данные в него
            BufferedWriter writer = new BufferedWriter(new FileWriter(surname + ".txt", true));
            writer.write(record);
            writer.newLine();
            writer.close();

            System.out.println("Данные успешно записаны в файл.");

        } catch (Exception e) {
            // Обработка исключений
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Метод для проверки формата даты рождения
    private static boolean isValidDate(String date) {
        return date.matches("\\d{2}\\.\\d{2}\\.\\d{4}");
    }
}

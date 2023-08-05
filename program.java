import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class program {
    public static void main(String[] args) {
        String pathToFile = "D:\\result\\";
        rwDb users = new rwDb();

        System.out.println("Проверьте, существует ли последовательность каталогов - " + pathToFile);
        System.out.println("Формат ввода данных: Петров Иван Иваныч 10.01.1983 79995556532 m");
        System.out.println("Команды \u001B[31m quit \u001B[0m или \u001B[31m exit \u001B[0m завершают работу программы");
        while(true){

            Scanner in = new Scanner(System.in);
            System.out.print("Введите данные пользователя: ");
            String cmd = in.nextLine();

            if(cmd.equals("exit") || cmd.equals("quit")){
                System.out.println("Работа с программой завершена");
                break;
            }

            try{
                users.parseString(cmd);
            } catch (ErrorArray e){
                System.out.println(e.getMessage());
                continue;
            }
            try{
                users.convert();
            } catch (DateTimeParseException e){
                System.out.println("Неверный формат даты рождения.");
                System.out.println("Формат должен быть следующим: день.месяц.год");
                System.out.println("Например: 31.12.2020");
                continue;
            } catch (NumberFormatException e){
                System.out.println("Неверный формат телефонного номера.");
                System.out.println("Формат должен быть следующим: 79995556532 или +79995556532");
                continue;
            }

            users.setFile(pathToFile, users.getUser()[0]);

            try {
                users.createDbFile(users.getFile());
            } catch (Exception e){
                System.out.println("Критическая ошибка!!! Программа завершает свою работу!");
                System.out.println("Проверьте, существует ли постедовательность каталогов -  " + pathToFile);
                System.out.println("где программа пытается создать файл.");
                System.out.println("Если такого каталога не существует, то пропишите правильный путь к каталогу");
                System.out.println("И после этого запустите программу снова");
                break;
            }

            try{
                users.addUser(users.getFile());
                System.out.print("Данные пользователя: ");
                System.out.println(Arrays.toString(users.getUser()));
                System.out.println("Записаны в файл: " + users.getFile());
            } catch (IOException e){
                System.out.println("Критическая ошибка!!! Программа завершает свою работу!");
                System.out.println("Проверьте наличие файла по указанному пути - " + users.getFile());
                System.out.println("И запустите программу снова.");
                break;
            }

        }
    }
}

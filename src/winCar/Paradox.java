package winCar;

import java.util.Random;
import java.util.Scanner;

public class Paradox {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //Для статистики игрок задает количество проходов
            long number = countLoop(scanner);
            if (number == 0) {
                System.out.println("Эксперимент окончен!");
                return;
            }
            long victoriesOld = checkOldChoice(number);
            System.out.println("Количество выигранных автомобилей, если пользователь не менял дверь: " + victoriesOld);
            long victoriesNew = checkNewChoice(number);
            System.out.println("Количество выигранных автомобилей, если пользователь менял дверь: " + victoriesNew);
        }
    }

    /*Ввод end*/
    private static boolean endGame(Scanner scanner) {
        return scanner.hasNext("end");
    }

    /*Задаем число проходов*/
    private static long countLoop(Scanner scanner) {
        System.out.println("Задайте количество экспериментов.");
        if (endGame(scanner)) {
           return 0;
        }
        return scanner.nextLong();
    }

    /*Случайное число выбирает дверь, где расположена машина**/
    private static int pick() {
        Random rand = new Random();
        int begin = 1;
        int end = 3;
        return begin + rand.nextInt(end - begin + 1);
    }

    /*Бежим по циклу и не меняем номер заданной дери
     * и считаем количество выигранных автомобилей
     * number - число экспериментов
     * return - количество выигрышей*/
    private static long checkOldChoice(long number) {
        long count = 0;
        for (int i = 0; i < number; i++) {
            int hideCar = pick();
            int userChoice = pick();
            //Если выбор был правильный и мнение не менялось,
            //то игрок выиграл машину
            count = (hideCar == userChoice) ? ++count : count;
        }
        return count;
    }

    /*Бежим по циклу и меняем номер заданной дери
     * и считаем количество выигранных автомобилей
     * number - число экспериментов
     * return - количество выигрышей*/
    private static long checkNewChoice(long number) {
        long count = 0;
        for (int i = 0; i < number; i++) {
            int hideCar = pick();
            int userChoice = pick();
            //Если изначально выбор был не правильный,
            //но в процессе игры мнение изменилось, то игрок выиграл
            count = (hideCar != userChoice) ? ++count : count;
        }
        return count;
    }
}

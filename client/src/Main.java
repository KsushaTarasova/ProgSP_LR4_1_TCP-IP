import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        try (Socket clientSocket = new Socket("127.0.0.1", 2525)) {
            System.out.println("Соединение установлено...");
            Scanner scanner = new Scanner(System.in);
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            String s;
            int n = 0, m = 0, choice = 0;
            int[][] matrix;

            boolean flag = true;
            while (flag) {
                System.out.println("[1] - ввести размерность матрицы");
                System.out.println("[2] - завершить работу");
                System.out.print("Ваш выбор: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1: {
                        System.out.print("Введите количество строк - ");
                        n = scanner.nextInt();
                        System.out.print("Введите количество стобцов - ");
                        m = scanner.nextInt();
                        matrix = new int[n][m];
                        System.out.println("Матрица: ");
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < m; j++) {
                                matrix[i][j] = (int) (Math.random() * 20);
                                System.out.print(matrix[i][j] + "\t");
                            }
                            System.out.println();
                        }
                        oos.writeObject(matrix);
                        System.out.println("Строка с минимальным значением - " + ((int) ois.readObject() + 1));
                        System.out.println("Столбец с максимальным значением - " + ((int) ois.readObject() + 1));
                        break;
                    }
                    case 2: {
                        flag = false;
                        break;
                    }
                    default: {
                        System.out.println("Введено неверное значение");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
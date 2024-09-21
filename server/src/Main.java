import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2525)) {
            System.out.println("Сервер стартовал...");
            System.out.println("at IP = " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("at port = " + serverSocket.getLocalPort());
            while (true) {
                try {
                    Socket clientAccepted = serverSocket.accept();
                    new Thread(() -> {
                        try (ObjectOutputStream oos = new ObjectOutputStream(clientAccepted.getOutputStream());
                             ObjectInputStream ois = new ObjectInputStream(clientAccepted.getInputStream())) {
                            System.out.println(" **Подключился клиент (IP = " + clientAccepted.getInetAddress() + ", at port = " + clientAccepted.getPort() + ")");
                            int[][] clientMesssageRecieved;
                            while (true) {
                                clientMesssageRecieved = (int[][]) ois.readObject();
                                System.out.println("Получено сообщение от клиента (IP = " + clientAccepted.getInetAddress()
                                        + ", at port = " + clientAccepted.getPort() + "): ");
                                for (int i = 0; i < clientMesssageRecieved.length; i++) {
                                    for (int j = 0; j < clientMesssageRecieved[0].length; j++) {
                                        System.out.print(clientMesssageRecieved[i][j] + "\t");
                                    }
                                    System.out.println();
                                }
                                int[] indexes = Matrix.minInRow(clientMesssageRecieved);
                                System.out.println("Строка с минимальным значением - " + (indexes[0] + 1));
                                oos.writeObject(indexes[0]);
                                System.out.println("Столбец с максимальным значением - " + (indexes[1] + 1));
                                oos.writeObject(indexes[1]);
                            }
                        } catch (IOException e) {
                            System.out.println(" **Клиент отключился (IP = " + clientAccepted.getInetAddress() + ", at port = " + clientAccepted.getPort() + ", " + ")");
                        } catch (ClassNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }).start();
                } catch (IOException e) {
                    System.out.println("Произошла ошибка");
                }
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка");
        }
    }
}
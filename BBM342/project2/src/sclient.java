import java.io.PrintWriter;
import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class sclient {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (!args[0].equals("-a")) {
            System.err.println("First cmd argument not equals -a");
            return;
        }
        if (!args[2].equals("-p")) {
            System.err.println("Second cmd argument not equals -p");
            return;
        }
        if (!args[4].equals("-ch")) {
            System.err.println("4. cmd argument not equals -ch");
            return;
        }
        // connect server
        int port = Integer.parseInt(args[3]);
        Socket socket = new Socket(args[1], port);


        // print arguments to console
        System.out.println("Address: " + args[1]);
        System.out.println("Port Number: " +args[3]);
        System.out.println("Channel ID: "+ args[5]);
        // send data from client to server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(args[5]);
        // Delay
        TimeUnit.SECONDS.sleep(1);

        // Get output from server
        Scanner in = new Scanner(socket.getInputStream());

        System.out.println("Server response: " + in.nextLine());

        out.println("okey");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Server response:");

        while (in.hasNextLine()) {
            System.out.println(in.nextLine());
        }

        out.close();
        in.close();
        socket.close();
    }
}

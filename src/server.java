import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    private ServerSocket serverSocket;

    public server(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }

    public void startServer()
    {
        try
        {
            while(!serverSocket.isClosed())
            {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");

                clientHandler clientHandler = new clientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }

        catch(IOException e)
        {

        }
    }

    public void closeServerSocket()
    {
        try
        {
            if(serverSocket != null)
            {
                serverSocket.close();
            }
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        ServerSocket serverSocket = new ServerSocket(1234);

        server server = new server(serverSocket);

        server.startServer();
    }
}

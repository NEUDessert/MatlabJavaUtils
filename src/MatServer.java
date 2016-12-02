import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Lawrence on 2016/12/2.
 *
 */
public class MatServer {
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    public MatServer(int port) {
        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getLength() throws IOException {
        socket = serverSocket.accept();
        inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int res = dataInputStream.readInt();
        return res;
    }
    public double[] getData(int length) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        double[] res = new double[length];
        for(int i = 0; i < length; i++) {
            res[i] = dataInputStream.readDouble();
        }
        return res;
    }
    public void sendAnayResult(double result) throws IOException {
        outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeDouble(result);
        inputStream.close();
        outputStream.close();
        socket.close();

    }
    public void closeServer() throws IOException {
        serverSocket.close();
    }
}

package Core;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

public class Common {

    public static Process server;

    public static void startServer() throws IOException, InterruptedException {
        server = Runtime.getRuntime().exec("cmd.exe");
        server.getOutputStream().write("java -jar phonebook.jar".getBytes());
        Thread.sleep(30000);
    }

    public static void terminateServer() throws IOException {
        server.destroy();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        startServer();
    }

    public static String jsonReader(String json, String parameter) {
        Object obj = new JsonParser().parse(String.valueOf(json));
        JsonObject jo = (JsonObject) obj;
        return jo.get(parameter).toString();
    }
}

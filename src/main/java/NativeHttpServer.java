/**
 * Created by dakshins on 06/04/18.
 */

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;


public class NativeHttpServer 
{
    private HttpServer server;
    private static final int port = 9080;

    public NativeHttpServer() throws Exception
    {
        server = HttpServer.create(new InetSocketAddress(port), 0);
    }
    
    private void start() {
    	server.start();
    }
    private void initializeRoutine() throws Exception
    {
        server.setExecutor(Executors.newFixedThreadPool(8));
        Routes.setup(server);
        CoreNLPWrapper.initialize();
    }
    
    public static void main (String [] strings) 
    {
        System.out.println("Beging server initialization...");
        System.out.println("Initialization sequence complete. Server up and running");
        System.out.println("Browse to http://localhost:"+port);
        try {
        	NativeHttpServer app = new NativeHttpServer();
        	app.initializeRoutine();
        	app.start();
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
    }
}
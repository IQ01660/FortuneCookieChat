
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Random;
import java.io.OutputStream;


public class CookieServer
{

    private static final int PORT = 3800;

    public static ServerSocket server;
    //all fortunes
    public static void main (String[] args)
    {
        //filling the fortune database
        Fortune.loadFortunes();

        try
        {
            ServerSocket server = new ServerSocket(PORT);
            Socket connectionSocket = server.accept();

            System.out.println("Connection established");

            OutputStream outputStr = connectionSocket.getOutputStream();


            //creating a random fortune cookie
            Fortune randomFortune = new Fortune();
            System.out.println("Sending the fortune...");

            outputStr.write(randomFortune.fortuneBytes);
            //Byte a = 100;
            //System.out.println(a.toString());
            System.out.println("Fortune Sent");

            // connectionSocket.close();
            // server.close();
        }
        catch(Exception e)
        {
            System.out.println("CONNECTION ERROR TO CLIENT");
        }
        
    }


    /**
     * Creates a server socket at a given port
     * @param port
     */
    private static void createSocket (int port)
    {
         
    }
}




class Fortune
{
    //all fortunes
    private static LinkedList<String> fortuneDataBase = new LinkedList<>();

    public String fortuneMsg;
    public byte[] fortuneBytes;

    public Fortune()
    {
        Random rand = new Random();
        int randPos = rand.nextInt(Fortune.fortuneDataBase.size());

        this.fortuneMsg = fortuneDataBase.get(randPos);

        this.fortuneBytes = this.toBytesArray(this.fortuneMsg);
    }

    private byte[] toBytesArray(String msg)
    {
        char[] msgChars = msg.toCharArray();
        
        byte[] toReturn = new byte[msgChars.length];

        for (int i = 0; i < msgChars.length; i++)
        {
            toReturn[i] = (byte) msgChars[i];
        }

        return toReturn;
    }

    /**
     * This will be called in server's main method to load
     * all fortunes into the vector
     */
    public static void loadFortunes()
    {
        Fortune.fortuneDataBase.add("The fortune you seek is in another cookie.");
        Fortune.fortuneDataBase.add("A closed mouth gathers no feet");
        Fortune.fortuneDataBase.add("A conclusion is simply the place where you got tired of thinking");
        Fortune.fortuneDataBase.add("A cynic is only a frustrated optimist");
        Fortune.fortuneDataBase.add("A foolish man listens to his heart. A wise man listens to cookies.");
        Fortune.fortuneDataBase.add("You will die alone and poorly dressed");
        Fortune.fortuneDataBase.add("A fanatic is one who can't change his mind, and won't change the subject.");
        Fortune.fortuneDataBase.add("If you look back, you'll soon be going that way.");
        Fortune.fortuneDataBase.add("The greatest danger could be your stupidity.");
        Fortune.fortuneDataBase.add("Hank Moody is the best");
        Fortune.fortuneDataBase.add("You will be hungry again in one hour");
        Fortune.fortuneDataBase.add("Help! I am being held prisoner in a fortune cookie factory.");
        Fortune.fortuneDataBase.add("All fortunes are wrong except this one.");
    }

    @Override
    public String toString()
    {
        return this.fortuneMsg;
    }
}
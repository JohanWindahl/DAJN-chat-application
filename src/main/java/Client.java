import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by danielghandahari on 2016-11-25.
 */
public class Client implements Runnable {

    public String userName;
    public Socket cs;
    public ObjectInputStream in;
    public ObjectOutputStream out;
    public Message message;

    public ArrayList<Message> syncedArrayList = new ArrayList<Message>();
    public ArrayList<Message> notSyncedArrayList = new ArrayList<Message>();



    public Client(){}

    public Message getMessage(){ return message;}

    public String getUserName(){ return userName;}

    public ObjectOutputStream getOut(){ return out;}

    public void setMessage(Message message){ this.message = message;}



    //tar bort hela a2, tar bort hela a1, tar bort X(hårdkodat till 10) antal element från syncedArrayList
    //och lägger till 10 nya meddelanden i rätt ordning
    private void syncLists(ArrayList<Message> a1, ArrayList<Message> a2) {

        syncedArrayList.addAll(a1);
        notSyncedArrayList.clear();

    }


    //kollar om 2 arraylistor är synkade
    private boolean areListSynced(ArrayList<Message> a1, ArrayList<Message> a2){
        return a1.equals(a2);
    }

    private void logOut(){
        try{
            Message exitMessage = new Message("exit", this.userName, new Date());
            out.writeObject(exitMessage);
            System.out.println("You left the conversation...");
        } catch (IOException ioe){
            System.out.printf("IOException in logOut");
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String hostName = args[0];
        int port = Integer.parseInt(args[1]);

        try (Socket s = new Socket(hostName, port);
             ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(s.getInputStream());
             BufferedReader stdIn = new BufferedReader((new InputStreamReader(System.in)))
        ){

            Client client = new Client();
            client.userName = args[2];
            client.cs = s;
            client.in = in;
            client.out = out;

            (new Thread(client)).start();
            System.out.println("Connection successful!");

            while (true) {

                String strFromClient = stdIn.readLine();


                if (strFromClient.equals("exit")) {
                    client.logOut();
                    break;
                }

                client.setMessage(new Message(strFromClient, client.getUserName(), new Date()));
                client.getOut().writeObject(client.getMessage());

            }
            System.exit(0);


        } catch (IOException ioe){
            System.out.println("Exception occurred in main");
            ioe.printStackTrace();
        }

    }

    public void run() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // Sätter så att JVM är i UTC tid istället för lokal tidzon.
        System.out.println(new Date().toString());
        try {
            while (true) {
                Object serverResponse = in.readObject();
                if (serverResponse != null) {
                    if (serverResponse instanceof String) {
                        String stringMessageServerResponse  = (String)(serverResponse);
                        ClientGUIn.getChatArea().add(stringMessageServerResponse);
                    }
                    else {
                        Message messageServerResponse = (Message) (serverResponse);
                        if (messageServerResponse.getSyncedListFromServer() == null) {
                            System.out.println("Fått ett MESSAGE utan arraylist");
                            System.out.println(serverResponse);
                            System.out.println(messageServerResponse.getUserName() + ": " + messageServerResponse.getTextMessage());
                            Date date;
                            date = messageServerResponse.getTimeStamp();
                            ClientGUIn.getChatArea().add(("[" + (new SimpleDateFormat("HH:mm:ss").format(date)) + "] " + messageServerResponse.getUserName() + ": " + messageServerResponse.getTextMessage()));
                        } else {
                            ArrayList<Message> arrayListServerResponse = messageServerResponse.getSyncedListFromServer();

                            System.out.println("Fått ett MESSAGE MED arraylist");
                            System.out.println("dfdf" + serverResponse);

                            System.out.println("#######Detta är arraylisten vi får: " + arrayListServerResponse);
                            Integer minBetweenServerArraySizeAndChatAreaSize = Math.min(arrayListServerResponse.size(), ClientGUIn.getChatArea().getItemCount());
                            System.out.println(arrayListServerResponse.size());
                            System.out.println(ClientGUIn.getChatArea().getItemCount());
                            System.out.println("minBetweenServerArraySizeAndChatAreaSize = " + minBetweenServerArraySizeAndChatAreaSize);

                            for (int i = 0; i < minBetweenServerArraySizeAndChatAreaSize; i++) {
                                System.out.println("dags o looopa bois");

                                Integer currentMessageIndex = ClientGUIn.getChatArea().getItemCount() - minBetweenServerArraySizeAndChatAreaSize + i;
                                Thread.sleep(50);

                                Date date = arrayListServerResponse.get(i).getTimeStamp();

                                ClientGUIn.getChatArea().replaceItem("[" + (new SimpleDateFormat("HH:mm:ss").format(date)) + "] " + arrayListServerResponse.get(i).getUserName() + " : " + arrayListServerResponse.get(i).getTextMessage() + " [S]", currentMessageIndex);


                                System.out.println("replacing: " + ClientGUIn.getChatArea().getItem(currentMessageIndex) + " on index in array: " + currentMessageIndex);
                                System.out.println("with: " + arrayListServerResponse.get(i).getTextMessage() + " on serverarray index: " + i);

                            }
                            arrayListServerResponse.clear();

                            System.out.println("klar med hela arraylistfixet");
                        }
                    }
                }


            }
        } catch (IOException e) {
            System.out.println("Problems with I/O in run()");
            e.printStackTrace();

        } catch (ClassNotFoundException cnfe){
            System.out.println("Problems finding class in run()");
            cnfe.printStackTrace();

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
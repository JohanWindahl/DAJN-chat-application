import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.*;

//TODO behövs dessa under? fixa catch
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by danielghandahari on 2016-11-25.
 */
public class Server extends Thread implements Runnable {

    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public ArrayList<Message> SyncBuffer = new ArrayList<Message>();
    private Integer port;

    /*
    * list containing all client output streams
    * for clients that are connected to the
    * server.
    * */
    //TODO MÅSTE CLOSEA DENNA SEN
    public static ArrayList<ObjectOutputStream> outputStreams = new ArrayList<ObjectOutputStream>();


    public class Listen_to_clients implements Runnable {

        private Socket clientSocket;
        private ObjectInputStream in;
        private ObjectOutputStream out;

        private Listen_to_clients(ObjectOutputStream out, Socket clientSocket) {
            this.out = out;
            this.clientSocket = clientSocket;
        }


        public void run() {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // Sätter så att JVM är i UTC tid istället för lokal tidzon.

        /*
        * if client gets disconnected and the operating
        * system is Windows, the input stream throw a
        * SocketExcpetion. Otherwise, just exits the try block.
        * Therefore, for non-Windows platforms, the disconnection
        * is handled in a finally block.
        *
        * this bool makes sure that non-Windows clients not enter
        * the finally block when they leave the conversation, i.e
        * leaving on purpose and not unintentionally.
        * */
            boolean tryWithSuccess = false;
            Message objectFromClient = null;
            try (ObjectInputStream clientInputStream = new ObjectInputStream(clientSocket.getInputStream())) {

                this.in = clientInputStream;
                String strFromClient;

                while ((objectFromClient = (Message) in.readObject()) != null) {

                    if(objectFromClient.getIsExitMessage() == true){
                        exitClient(outputStreams, objectFromClient);
                        break;
                    }


                    strFromClient = objectFromClient.getTextMessage();
                    //printar på server side, ska bort sen


                    Date date = objectFromClient.getTimeStamp();
                    System.out.println("loopen fångar strängen : " + strFromClient);
                    ServerGUIn.getStatusArea().add("[" + (new SimpleDateFormat("HH:mm:ss").format(date)) + "] " + objectFromClient.getUserName() + "(" + clientSocket.getInetAddress().getHostName() + ")" + ": " + strFromClient);
                    System.out.println("Detta objekt skickas till alla: ----" + objectFromClient + "----");

                    sendMessageFromClientToClients(outputStreams, objectFromClient);
                }
                tryWithSuccess = true;

            } catch (SocketException s) {

                tryWithSuccess = false;
                try {
                    handleLostConnection(objectFromClient);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException ie) {

                tryWithSuccess = false;

                ie.printStackTrace();
                outputStreams.remove(this.out);
            } catch (ClassNotFoundException cnfe) {

                tryWithSuccess = false;

                System.out.println("Problems finding class in run()");
                cnfe.printStackTrace();
                outputStreams.remove(this.out);
            } finally {

                String OSName = objectFromClient.getOS();

                if ((!OSName.startsWith("Windows") && (tryWithSuccess == false))) {
                    try {
                        handleLostConnection(objectFromClient);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public Server(ObjectOutputStream out, Socket clientSocket) {
        this.out = out;
        this.clientSocket = clientSocket;
    }

    public Server() {}

    private void addAndSortToArrayList(ArrayList<Message> aList, Message message) {
        aList.add(message);
        if (aList.size() > 1){
            Collections.sort(aList, new Comparator<Message>(){
                @Override
                public int compare(Message a, Message b){
                    return a.getTimeStamp().compareTo(b.getTimeStamp());
                }
            });
        }
    }

    private void sendStringMessageFromClientToClients(ArrayList<ObjectOutputStream> outputStreams, String clientMessage) throws IOException {
        ServerGUIn.getStatusArea().add(clientMessage);
        for (int i = 0; i < outputStreams.size(); i++) {
            outputStreams.get(i).writeObject(clientMessage);
        }
    }

    private void sendMessageFromClientToClients(ArrayList<ObjectOutputStream> outputStreams, Message clientMessage) {
        addAndSortToArrayList(SyncBuffer, clientMessage);
        System.out.println("Såhär stor är SyncBuffer : " + SyncBuffer.size());



        for (int i = 0; i < outputStreams.size(); i++) {
            try {
                    outputStreams.get(i).writeObject(clientMessage);

                if (SyncBuffer.size() % 10 == 0) {
                    ArrayList<Message> testArr = new ArrayList<Message>();
                    for(int j = 0; j < SyncBuffer.size(); j++){
                        testArr.add(new Message(SyncBuffer.get(j).getCount(), SyncBuffer.get(j).getTimeStamp(), SyncBuffer.get(j).getUserName(), SyncBuffer.get(j).getTextMessage(), SyncBuffer.get(j).getSyncedListFromServer(), SyncBuffer.get(j).getOS()));
                    }
                    Message messageWithArraylist = new Message(testArr);
                    messageWithArraylist.setTextMessage("hejdå");
                    outputStreams.get(i).writeObject(messageWithArraylist);
                }
                if(SyncBuffer.size() == 10 && (outputStreams.get(i) == outputStreams.get(outputStreams.size()-1))) SyncBuffer.clear();

            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void handleLostConnection(Message objectFromClient) throws IOException {
        exitClient(outputStreams, objectFromClient);
    }

    private static void closeAllClientOutputStreams(ArrayList<ObjectOutputStream> freeList) {

        try {
            for (ObjectOutputStream o : freeList) {
                o.close();
            }
        } catch (IOException ioe) {
            System.out.println("Could not close all client sockets.");
            ioe.printStackTrace();
        }

    }

    private void exitClient(ArrayList<ObjectOutputStream> informedClients, Message messageToClients) throws IOException {

        if (informedClients.contains(out)) informedClients.remove(out);
        sendStringMessageFromClientToClients(informedClients, messageToClients.getTextMessage());




    }

    public static void main(String[] args) {
        Integer porta = 2222;

        //Integer port = Integer.parseInt(args[0]);
        /*
        * list containing all client output streams
        * ever added and will close all those
        * sockets when server terminates.
        * */
        //TODO socket stuff som ska bort!!!!!
        ArrayList<ObjectOutputStream> freelist = new ArrayList<ObjectOutputStream>();


        //try (ServerSocket serverSocket = new ServerSocket(port)){
        try {
            ServerSocket serverSocket = new ServerSocket(porta);
            System.out.println("Listening for clients... on port: " + porta);


            while (true) {
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                Server server = new Server(out, clientSocket);
                outputStreams.add(out);
                freelist.add(out);
                //TODO flytta t run m anv namn
                System.out.println("Client " + clientSocket.getInetAddress().getHostName() + " connected!");

                new Thread(server).start();
            }

        } catch (SocketException s) {
            System.out.println("socket exception in main");
            s.printStackTrace();

        } catch (IOException ie) {
            ie.printStackTrace();

        } finally {
            //TODO socket stuff som ska bort!!!!!
            closeAllClientOutputStreams(freelist);
            System.out.println("All client sockets are closed.");
        }

    }

    public void run() {

        try {
            this.port = ServerGUIn.getPort();
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Listening for clients... on port: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                Listen_to_clients listen = new Listen_to_clients(out, clientSocket);
                outputStreams.add(out);


                new Thread(listen).start();
            }
        } catch (SocketException s) {
            System.out.println("socket exception in main");
            s.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } finally {
            System.out.println("All client sockets are closed.");
        }
    }


}


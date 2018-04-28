import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by danielghandahari on 2016-11-25.
 */
public class Message implements Serializable {
    private Integer count;
    private Date timeStamp;
    private String userName;
    private String textMessage;
    private ArrayList<Message> syncedListFromServer;
    private boolean isExitMessage = false;

    /*
    * This attribute is needed because of BufferedReader
    * catching IOException only for Windows
    * */



    private String OS = System.getProperty("os.name");



    public Message(Integer count, Date timeStamp, String userName, String textMessage, ArrayList<Message> syncedListFromServer, String OS) {
        this.count = count;
        this.timeStamp = timeStamp;
        this.userName = userName;
        this.textMessage = textMessage;
        this.syncedListFromServer = syncedListFromServer;
        this.OS = OS;
    }

    public Message(String textMessage, String userName) {
        this.textMessage = textMessage;
        this.userName = userName;
    }

    public Message(ArrayList<Message> syncedListFromServer) {
        this.syncedListFromServer = syncedListFromServer;

    }

    public Message(Integer count, Date timeStamp, String userName, String textMessage) {
        this.count = count;
        this.timeStamp = timeStamp;
        this.userName = userName;
        this.textMessage = textMessage;
    }

    public Message(String textMessage, String userName, Date timestamp){
        this.textMessage = textMessage;
        this.userName = userName;
        this.timeStamp = timestamp;
    }


    public ArrayList<Message> getSyncedListFromServer(){
        return syncedListFromServer;
    }
    public void setSyncedListFromServer(ArrayList<Message> syncedListFromServer){
        this.syncedListFromServer = syncedListFromServer;
    }

    public boolean getIsExitMessage(){
        return isExitMessage;
    }

    public void setIsExitMessage(boolean isExitMessage){
        this.isExitMessage = isExitMessage;
    }

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return the timeStamp
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the textMessage
     */
    public String getTextMessage() {
        return textMessage;
    }

    /**
     * @param textMessage the textMessage to set
     */
    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    /**
     * @return the OS
     */
    public String getOS() {
        return OS;
    }

    /**
     * @param OS the OS to set
     */
    public void setOS(String OS) {
        this.OS = OS;
    }

    public static boolean compareMessages(Message m1, Message m2) {
        if (m1.getCount().equals(m2.getCount()) &&
                m1.getTimeStamp().equals(m2.getTimeStamp()) &&
                m1.getTextMessage().equals(m2.getTextMessage()) &&
                m1.getOS().equals(m2.getOS()) &&
                m1.getUserName().equals(m2.getUserName())) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return ("Count: " + this.count + " - Timestamp: " + this.timeStamp + " - OS: " + this.OS + " - UserName: " + this.userName + " - Message: " + this.textMessage + "\n" + this.syncedListFromServer + "\n");
    }
}

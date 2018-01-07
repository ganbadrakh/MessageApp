package technikum.fh.messageapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kerberos on 01.01.2018.
 */

class Messages {

    @SerializedName("messages")
    private List<ShortMessages> messages = new ArrayList<>();

    public List<ShortMessages> getMessages() {
        return messages;
    }
    public void setMessages(List<ShortMessages> messages){
        this.messages = messages;
    }

}

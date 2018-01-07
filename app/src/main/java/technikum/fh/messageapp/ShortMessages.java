package technikum.fh.messageapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kerberos on 01.01.2018.
 */

class ShortMessages {

    @SerializedName("created")
    private final String created;

    @SerializedName("text")
    private final String text;

    public ShortMessages(String created, String text) {
        this.created = created;
        this.text = text;
    }

    public String getCreated() {
        return created;
    }

    public String getText() {
        return text;
    }
}

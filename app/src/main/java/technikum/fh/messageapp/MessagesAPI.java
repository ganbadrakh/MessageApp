package technikum.fh.messageapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kerberos on 01.01.2018.
 */

interface MessagesAPI {

    String BASE_URL = "https://short-messages-web-api.azurewebsites.net/api/ShortMessages/";

   // @GET("consumerKey")
   // Call<Messages> getShortMessages();

    @GET("{consumerKey}")
    Call<Messages> getShortMessages(@Path("consumerKey") String consumerKey);

}

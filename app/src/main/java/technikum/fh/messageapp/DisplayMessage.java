package technikum.fh.messageapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DisplayMessage extends AppCompatActivity {

    private ListView listView;
    private String lastMessage;

    public static final String SENT_MESSAGE = "MSG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        listView = findViewById(R.id.msgList);

        Intent intent = getIntent();
        String consumerKey = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        if (savedInstanceState != null) {
            lastMessage = savedInstanceState.getString(SENT_MESSAGE);
        }

        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add("short-messages-web-api.azurewebsites.net", "sha256/OtGR7ixvqZTxaH6c5Vpeje4IUMgdfqgYdIq5ZykUcgc=")
                .build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .certificatePinner(certificatePinner)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MessagesAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        MessagesAPI api = retrofit.create(MessagesAPI.class);

        Call<Messages> call = api.getShortMessages(consumerKey);

        call.enqueue(new Callback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, Response<Messages> response) {

                if(response.isSuccessful()){
                    List<ShortMessages> MessageList = response.body().getMessages();

                    if(MessageList.size() > 0){
                        String[] Message = new String[MessageList.size()];
                        for(int i = 0; i < MessageList.size(); i++){
                            Message[i] = MessageList.get(i).getText();
                        }
                        listView.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, Message));
                    }else{

                        Toast.makeText(getApplicationContext(), "No new message there or the key was wrong!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

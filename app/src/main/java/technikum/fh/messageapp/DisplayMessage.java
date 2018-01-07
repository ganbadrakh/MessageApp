package technikum.fh.messageapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DisplayMessage extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        listView = findViewById(R.id.msgList);

        Intent intent = getIntent();
        String consumerKey = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MessagesAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MessagesAPI api = retrofit.create(MessagesAPI.class);

        Call<Messages> call = api.getShortMessages(consumerKey);

        call.enqueue(new Callback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, Response<Messages> response) {
                List<ShortMessages> MessageList = response.body().getMessages();

                String[] Message = new String[MessageList.size()];

                for(int i = 0; i < MessageList.size(); i++){
                    Message[i] = MessageList.get(i).getText();
                }
                listView.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, Message));
            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

package technikum.fh.messageapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private EditText et;
    private String consumerKey;
    public static final String EXTRA_MESSAGE = "EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        et = findViewById(R.id.editText);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                consumerKey = et.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void onButtonClick(View v) {

        if (et.getText().toString().length() == 0) {
            Toast t = Toast.makeText(getApplicationContext(), "Please enter the key!", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
        } else {
            Intent i = new Intent(this, DisplayMessage.class);
            //i.putExtra(EXTRA_MESSAGE, "consumerKey");
            i.putExtra(EXTRA_MESSAGE,consumerKey);
            startActivity(i);
        }
    }
}


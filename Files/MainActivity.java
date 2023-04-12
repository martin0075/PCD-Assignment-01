package it.unibo.stradivarius.coinflip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonToss;
    private Button buttonRepeat;
    private Button buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonToss = findViewById(R.id.buttonToss);
        buttonRepeat = findViewById(R.id.buttonRepeat);
        buttonExit = findViewById(R.id.buttonExit);

        /* What we do with the TOSS button */
        buttonToss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* BEGIN Code to use if no buttons are used */
                Random r = new Random();

                if (r.nextDouble() > 0.5) {
                    // We flipped heads
                    ImageView IW = findViewById(R.id.imageView);
                    TextView TW = findViewById(R.id.textResult);

                    IW.setImageResource(R.drawable.head);
                    TW.setText(R.string.head);
                    buttonToss.setVisibility(View.INVISIBLE);
                }
                else {
                    // We flipped tails
                    ImageView IW = findViewById(R.id.imageView);
                    TextView TW = findViewById(R.id.textResult);

                    IW.setImageResource(R.drawable.tail);
                    TW.setText(R.string.tails);
                    buttonToss.setVisibility(View.INVISIBLE);
                }
                /* END  Code to use if no buttons are used */
            }
        });

        /* What we do with the REPEAT button */
        buttonRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView IW = findViewById(R.id.imageView);
                TextView TW = findViewById(R.id.textResult);

                IW.setImageResource(R.drawable.question);
                TW.setText("");
                buttonToss.setVisibility(View.VISIBLE);
            }
        });

        /* What we do with the EXIT button */
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
package hr.fer.ruazosa.hw3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button chanceCalcButton = (Button) findViewById(R.id.launch_chance_calculator);
        chanceCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchChanceCalculatorActivity();
            }
        });

        Button ticketCreatorButton = (Button) findViewById(R.id.launch_ticket_creator);
        ticketCreatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchLottoTicketCreatorActivity();
            }
        });

        Button wheelOfFortuneButton = (Button) findViewById(R.id.launch_wheel_of_fortune);
        wheelOfFortuneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWheelOfFortuneActivity();
            }
        });

    }

    private void launchActivity(final Class<? extends Activity> activityClass) {
        final Intent i = new Intent(this, activityClass);
        startActivity(i);
    }

    private void launchWheelOfFortuneActivity() {
        launchActivity(WheelOfFortuneActivity.class);
    }

    private void launchChanceCalculatorActivity() {
        launchActivity(LottoChanceCalculatorActivity.class);
    }

    private void launchLottoTicketCreatorActivity() {
        launchActivity(LottoTicketCreatorActivity.class);
    }

}

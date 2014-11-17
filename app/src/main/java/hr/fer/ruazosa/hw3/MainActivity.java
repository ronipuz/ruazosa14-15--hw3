package hr.fer.ruazosa.hw3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

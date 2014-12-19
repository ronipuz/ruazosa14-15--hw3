package hr.fer.ruazosa.hw3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Activity creates lotto ticket.
 * @author roni
 */
public class LottoTicketCreatorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto_ticket_creator);

        final EditText inputMinor = (EditText) findViewById(R.id.calculator_minor);
        final EditText inputMajor = (EditText) findViewById(R.id.calculator_major);
        final TextView result = (TextView) findViewById(R.id.creator_result);

        inputMinor.addTextChangedListener(new GenericTextWatcher(inputMinor, getApplicationContext()));
        inputMajor.addTextChangedListener(new GenericTextWatcher(inputMajor, getApplicationContext()));
        inputMinor.setText("");
        inputMajor.setText("");
        findViewById(R.id.creator_generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputMinorText = inputMinor.getText().toString();
                String inputMajorText = inputMajor.getText().toString();
                if (inputMinorText.trim().length() == 0 || inputMajorText.trim().length() == 0 ||
                        inputMinorText.contains(".") || inputMajorText.contains(".")) {

                    result.setText(getResources().getString(R.string.calculator_result_error));
                } else {

                    if (inputMajor.getError() != null || inputMinor.getError() != null) {
                        if (inputMajor.getError() != null) {
                            result.setText(inputMajor.getError());
                        } else {
                            result.setText(inputMinor.getError());
                        }
                    } else {

                        try {
                            int minor = Integer.parseInt(inputMinorText);
                            int major = Integer.parseInt(inputMajorText);
                            if (minor > major) {
                                result.setText(getResources().getString(R.string.calculator_result_error_minor_gt_major));
                            } else {
                                result.setText(createLottoTicket(minor, major));
                            }
                        } catch (NumberFormatException e) {
                            result.setText(getResources().getString(R.string.calculator_result_error));
                        }
                    }
                }
            }
        }
        );
    }

    /**
     * Method generates lotto ticket.
     * @param minor how many numbers from major are chosen
     * @param major numbers count
     * @return {@link String} ticket
     */
    private String createLottoTicket(int minor, int major) {

        Set<Integer> ticketSet = new HashSet<Integer>();
        StringBuilder ticketString = new StringBuilder();
        Integer randomNumber;

        for(int i=0; i<minor; i++) {
            randomNumber = randomPositiveNumberLowerThanMaxValue(major);
            while(ticketSet.contains(randomNumber)) {
                randomNumber = randomPositiveNumberLowerThanMaxValue(major);
            }
            ticketSet.add(randomNumber);
            ticketString.append(randomNumber).append(", ");
        }
        ticketString.delete(ticketString.length()-2, ticketString.length());
        return ticketString.toString();
    }

    /**
     * Method than generates random number from 1 to max value.
     * @param max max value
     * @return random number
     */
    private int randomPositiveNumberLowerThanMaxValue(int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt(max) + 1;
        return randomNum;
    }
}

package hr.fer.ruazosa.hw3;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity calculates chances of winning lotto ticket.
 * @author roni
 */
public class LottoChanceCalculatorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto_chance_calculator);

        final EditText inputMinor = (EditText) findViewById(R.id.calculator_minor);
        final EditText inputMajor = (EditText) findViewById(R.id.calculator_major);
        final TextView result = (TextView) findViewById(R.id.calculator_result);

        inputMinor.addTextChangedListener(new GenericTextWatcher(inputMinor, getApplicationContext()));
        inputMajor.addTextChangedListener(new GenericTextWatcher(inputMajor, getApplicationContext()));
        inputMinor.setText("");
        inputMajor.setText("");
        findViewById(R.id.calculator_calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputMinorText = inputMinor.getText().toString();
                String inputMajorText = inputMajor.getText().toString();
                if(inputMinorText.trim().length() == 0 || inputMajorText.trim().length() == 0 ||
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
                                result.setText(lottoChanceResult(minor, major));
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

    private String lottoChanceResult(int minor, int major) {
        return "1 in " + (binomialUpperCoefficient(minor, major) / factorial(minor));
    }

    private int factorial(int n) {
        int prod = 1;
        for (int i = 2; i <= n; i++) {
            prod = prod * i;
        }

        return prod;
    }

    //to avoid large numbers (regular binomial)
    private long binomialUpperCoefficient(int minor, int major) {
        long result=1;
        for(int i=0; i<minor; i++) {
            result = result * major;
            major--;
        }
        return result;
    }
}

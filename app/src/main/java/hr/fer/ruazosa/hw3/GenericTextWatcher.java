package hr.fer.ruazosa.hw3;

import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;


/**
 * Created by roni on 12/13/14.
 * Class represents TextWatcher for two EditText views. Checks input
 * after text changes.
 */
public class GenericTextWatcher implements TextWatcher {

    private View view;
    private Context context;
    private LottoChanceCalculatorInputNumber inputNumber = new LottoChanceCalculatorInputNumber("");

    public GenericTextWatcher(View view, Context content) {
        this.view = view;
        this.context = content;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        String number = s.toString();
        inputNumber.setInputNumber(number);

        switch (view.getId()) {
            case R.id.calculator_minor:
                checkInput(number);
                break;
            case R.id.calculator_major:
                checkInput(number);
                break;
            default:
                break;
        }
    }

    private void checkInput(String number) {
        EditText editText = (EditText) view;
        if(!inputNumber.isNumber() || !inputNumber.isPositive()) {
            editText.setError(context.getResources().getString(R.string.calculator_input_error));
        } else {
            editText.setError(null);
        }
    }
}

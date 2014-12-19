package hr.fer.ruazosa.hw3;

/**
 * Created by roni on 12/12/14.
 * Class represents input of two {@link android.widget.EditText} from
 * {@link hr.fer.ruazosa.hw3.LottoChanceCalculatorActivity} with
 * functions for checking input.
 */
public class LottoChanceCalculatorInputNumber {

    /** text input**/
    private String inputNumber;
    /** parsed text **/
    private Integer number;

    public LottoChanceCalculatorInputNumber(String inputNumber) {
        this.inputNumber = inputNumber;
    }

    /**
     * Setting text input. Used for changing numbers in this object.
     * @param inputNumber
     */
    public void setInputNumber(String inputNumber) {
        this.inputNumber = inputNumber;
        isNumber();
    }

    /**
     * Checking if inputNumber is number. Checking by parsing
     * the input text.
     * @return <code>true</code> or <code>false</code>
     */
    public boolean isNumber() {
        try {
            number = Integer.parseInt(inputNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Getter
     * @return
     */
    public int getNumber() {
        return number.intValue();
    }

    /** Method checks if number is positive.
     *
     * @return <code>true</code> or <code>false</code>
     */
    public boolean isPositive() {
        return (number.intValue() > 0);
    }
}

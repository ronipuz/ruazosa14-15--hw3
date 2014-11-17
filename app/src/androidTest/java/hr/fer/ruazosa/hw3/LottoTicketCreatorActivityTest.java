package hr.fer.ruazosa.hw3;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import static android.test.TouchUtils.clickView;
import static android.test.ViewAsserts.assertGroupContains;
import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL;
import static org.assertj.android.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketCreatorActivityTest extends ActivityInstrumentationTestCase2<LottoTicketCreatorActivity> {

    private LottoTicketCreatorActivity activity;
    private LinearLayout root;
    private TextView title;
    private TextView subtitle;
    private LinearLayout inputContainer;
    private EditText inputMinor;
    private EditText inputMajor;
    private TextView inputDivider;
    private Button submit;
    private TextView result;

    public LottoTicketCreatorActivityTest() {
        super(LottoTicketCreatorActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activity = getActivity();

        root = (LinearLayout) activity.findViewById(R.id.root);
        title = (TextView) activity.findViewById(R.id.title);
        subtitle = (TextView) activity.findViewById(R.id.subtitle);
        inputContainer = (LinearLayout) activity.findViewById(R.id.input_container);
        inputMinor = (EditText) activity.findViewById(R.id.calculator_minor);
        inputMajor = (EditText) activity.findViewById(R.id.calculator_major);
        inputDivider = (TextView) activity.findViewById(R.id.input_divider);
        submit = (Button) activity.findViewById(R.id.creator_generate);
        result = (TextView) activity.findViewById(R.id.creator_result);
    }

    @MediumTest
    public void testRoot() {
        assertThat(root).isVisible().hasChildCount(5).isVertical();

        assertGroupContains(root, title);
        assertGroupContains(root, subtitle);
        assertGroupContains(root, inputContainer);
        assertGroupContains(root, submit);
        assertGroupContains(root, result);
    }

    @MediumTest
    public void testTitle() {
        assertThat(title).isVisible().hasText(R.string.creator_title);
    }

    @MediumTest
    public void testSubtitle() {
        assertThat(subtitle).isVisible().hasText(R.string.creator_subtitle);
    }

    @MediumTest
    public void testInputContainer() {
        assertThat(inputContainer).isVisible().hasChildCount(3).isHorizontal();

        assertGroupContains(inputContainer, inputMinor);
        assertGroupContains(inputContainer, inputDivider);
        assertGroupContains(inputContainer, inputMajor);
    }

    @MediumTest
    public void testInputMinor() {
        assertThat(inputMinor).isVisible().hasHint(R.string.calculator_minor_hint).hasInputType(TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL).hasMaxLines(1);
    }

    @MediumTest
    public void testInputDivider() {
        assertThat(inputDivider).isVisible().hasText(R.string.calculator_input_divider);
    }

    @MediumTest
    public void testInputMajor() {
        assertThat(inputMajor).isVisible().hasHint(R.string.calculator_input_major_hint).hasInputType(TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL).hasMaxLines(1);
    }

    @MediumTest
    public void testSubmit() {
        assertThat(submit).isVisible().hasText(R.string.creator_generate);
    }

    @MediumTest
    public void testResult() {
        assertThat(result).isVisible().hasHint(R.string.creator_result_hint);
    }

    @MediumTest
    public void testShouldGenerate7Of39() {
        final String minorInput = "7";
        final String majorInput = "39";

        checkOutput(minorInput, majorInput);
    }

    @MediumTest
    public void testShouldGenerate6Of45() {
        final String minorInput = "6";
        final String majorInput = "45";

        checkOutput(minorInput, majorInput);
    }

    @MediumTest
    public void testShouldntCalculateBlankFields() {
        final String minorInput = "";
        final String majorInput = "";
        final int expectedOutput = R.string.calculator_result_error;

        testFunctionality(minorInput, majorInput, expectedOutput);
    }

    @MediumTest
    public void testShouldntCalculateWithReals() {
        final String minorInput = "1.25";
        final String majorInput = "1.25";
        final int expectedOutput = R.string.calculator_result_error;

        testFunctionality(minorInput, majorInput, expectedOutput);
    }

    @MediumTest
    public void testShouldntCalculateIfMinorIsGreaterThanMajor() {
        final String minorInput = "5";
        final String majorInput = "3";
        final int expectedOutput = R.string.calculator_result_error_minor_gt_major;

        testFunctionality(minorInput, majorInput, expectedOutput);
    }

    @MediumTest
    public void testMinorShouldShowErrorOnZeroInput() {
        testInputHasError(inputMinor, "0");
    }

    @MediumTest
    public void testMinorShouldShowErrorOnFloatInput() {
        testInputHasError(inputMinor, "1.25");
    }

    @MediumTest
    public void testMinorShouldShowErrorOnBlankInput() {
        testInputHasError(inputMinor, "");
    }

    @MediumTest
    public void testMajorShouldShowErrorOnZeroInput() {
        testInputHasError(inputMajor, "0");
    }

    @MediumTest
    public void testMajorShouldShowErrorOnFloatInput() {
        testInputHasError(inputMajor, "1.25");
    }

    @MediumTest
    public void testMajorShouldShowErrorOnBlankInput() {
        testInputHasError(inputMajor, "");
    }

    private void input(final View view, final String text) {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                view.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync(text);
        getInstrumentation().waitForIdleSync();
    }

    private void inputData(final String minor, final String major) {
        input(inputMinor, minor);
        input(inputMajor, major);
    }

    private void testFunctionality(final String minor, final String major, final int expectedOutput) {
        inputData(minor, major);
        clickView(this, submit);
        assertThat(result).containsText(expectedOutput);
    }

    private String testFunctionality(final String minor, final String major) {
        inputData(minor, major);
        clickView(this, submit);
        return result.getText().toString();
    }

    private void checkOutput(final String minor, final String major) {
        final String output = testFunctionality(minor, major);

        final String[] parts = output.split(", ");
        final Set<Integer> numbers = new HashSet<Integer>();

        for (String part : parts) {
            try {
                final Integer integer = Integer.parseInt(part);
                assertThat(integer).isBetween(1, Integer.valueOf(major));
                assertThat(numbers).doesNotContain(integer);
                numbers.add(integer);
            } catch (NumberFormatException e) {
                fail("Expect result to consist of integers. Got " + part);
            }
        }

        assertThat(numbers).hasSize(Integer.valueOf(minor));
    }

    private void testInputHasError(EditText view, String input) {
        input(view, input);
        assertThat(view).hasError(R.string.calculator_input_error);
    }
}

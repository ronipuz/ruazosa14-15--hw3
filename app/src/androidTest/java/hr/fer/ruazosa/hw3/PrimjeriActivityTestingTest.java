package hr.fer.ruazosa.hw3;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.test.TouchUtils.clickView;
import static android.test.ViewAsserts.assertLeftAligned;
import static android.test.ViewAsserts.assertRightAligned;
import static org.assertj.android.api.Assertions.assertThat;

/**
 * Created by roni on 12/12/14.
 */
public class PrimjeriActivityTestingTest {

    //extends ActivityInstrumentationTestCase2<MainActivity>

    private static final String RESULT_FORMAT = "Hello, %s!";

    private MainActivity activity;
    private EditText input;
    private TextView output;
    private Button submit;

    private View decorView;
    private Instrumentation instrumentation;

    /*public PrimjeriActivityTestingTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);

        activity = getActivity();
        decorView = activity.getWindow().getDecorView();

        input = (EditText) activity.findViewById(R.id.name);
        submit = (Button) activity.findViewById(R.id.submit);
        output = (TextView) activity.findViewById(R.id.result);

        instrumentation = getInstrumentation();
    }

    @MediumTest
    public void testInputLayout() {
        assertThat(input)
                .isShown()
                .hasHint("Unesi ime");
    }

    @MediumTest
    public void testSubmitLayout() {
        assertThat(submit)
                .isShown()
                .hasText("Pozdrav!");

        assertEquals(input.getBottom(), submit.getTop());
        assertLeftAligned(input, submit);
        assertRightAligned(input, submit);
    }

    @MediumTest
    public void testOutputLayout() {
        assertThat(output)
                .isShown();

        assertEquals(submit.getBottom(), output.getTop());
        assertLeftAligned(submit, output);
        assertRightAligned(submit, output);
    }

    @MediumTest
    public void testHelloShouldAppear() {
        final String name = "hello";
        final String expectedOutput = String.format(RESULT_FORMAT, name);

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                input.requestFocus();
            }
        });

        instrumentation.waitForIdleSync();
        instrumentation.sendStringSync(name);
        instrumentation.waitForIdleSync();

        clickView(this, submit);

        assertThat(output).hasText(expectedOutput);
    }*/
}

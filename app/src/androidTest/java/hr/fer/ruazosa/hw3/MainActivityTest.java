package hr.fer.ruazosa.hw3;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.test.TouchUtils.clickView;
import static android.test.ViewAsserts.assertGroupContains;
import static org.assertj.android.api.Assertions.assertThat;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public static final int ACTIVITY_TIMEOUT = 1000;

    private MainActivity activity;

    private LinearLayout layoutRoot;
    private TextView title;
    private Button launchChanceCalculator;
    private Button launchTicketCreator;
    private Button launchWheelOfFortune;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activity = getActivity();
        title = (TextView) activity.findViewById(R.id.title);
        launchChanceCalculator = (Button) activity.findViewById(R.id.launch_chance_calculator);
        launchTicketCreator = (Button) activity.findViewById(R.id.launch_ticket_creator);
        launchWheelOfFortune = (Button) activity.findViewById(R.id.launch_wheel_of_fortune);

        layoutRoot = (LinearLayout) activity.findViewById(R.id.root);
    }

    @MediumTest
    public void testTitle() {
        assertThat(title).isVisible().hasText(R.string.main_title);
    }

    @MediumTest
    public void testLayout() {
        assertThat(layoutRoot)
                .hasChildCount(4)
                .isVertical()
                .isVisible();
        assertGroupContains(layoutRoot, launchChanceCalculator);
        assertGroupContains(layoutRoot, launchTicketCreator);
        assertGroupContains(layoutRoot, launchWheelOfFortune);
    }

    @MediumTest
    public void testLaunchChanceCalculatorLayout() {
        assertThat(launchChanceCalculator).isShown();
    }

    @MediumTest
    public void testLaunchTicketCreatorLayout() {
        assertThat(launchTicketCreator).isShown();
    }

    @MediumTest
    public void testLaunchWheelOfFortuneLayout() {
        assertThat(launchWheelOfFortune).isShown();
    }

    @MediumTest
    public void testLaunchChanceCalculator() {
        testLaunchActivity(launchChanceCalculator, LottoChanceCalculatorActivity.class);
    }

    @MediumTest
    public void testLaunchTicketCreator() {
        testLaunchActivity(launchTicketCreator, LottoTicketCreatorActivity.class);
    }

    @MediumTest
    public void testLaunchWheelOfFortune() {
        testLaunchActivity(launchWheelOfFortune, WheelOfFortuneActivity.class);
    }

    private void testLaunchActivity(final View button, Class<? extends Activity> activityClass) {
        final Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(activityClass.getName(), null, false);
        clickView(this, button);

        Activity activity = monitor.waitForActivityWithTimeout(ACTIVITY_TIMEOUT);
        assertThat(activity).isNotNull();
        assertEquals(activityClass, activity.getClass());

        activity.finish();
    }

}

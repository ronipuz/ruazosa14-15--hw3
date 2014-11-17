package hr.fer.ruazosa.hw3;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import static android.test.ViewAsserts.assertBottomAligned;
import static android.test.ViewAsserts.assertGroupContains;
import static android.test.ViewAsserts.assertLeftAligned;
import static android.test.ViewAsserts.assertRightAligned;
import static android.test.ViewAsserts.assertTopAligned;
import static org.assertj.android.api.Assertions.assertThat;

public class WheelOfFortuneActivityTest extends ActivityInstrumentationTestCase2<WheelOfFortuneActivity> {

    private WheelOfFortuneActivity activity;
    private ViewGroup root;
    private SeekBar seekBar;
    private ImageView image;

    public WheelOfFortuneActivityTest() {
        super(WheelOfFortuneActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activity = getActivity();
        root = (ViewGroup) activity.findViewById(R.id.root);
        seekBar = (SeekBar) activity.findViewById(R.id.wof_seekbar);
        image = (ImageView) activity.findViewById(R.id.wof_wheel);
    }

    @MediumTest
    public void testRoot() {
        assertThat(root).isShown().hasChildCount(2);

        assertGroupContains(root, image);
        assertGroupContains(root, seekBar);
    }

    @MediumTest
    public void testImage() {
        assertThat(image).isShown();

        assertEquals(0, (int) image.getRotation());
        assertTopAligned(root, image, root.getPaddingTop());
        assertLeftAligned(root, image, root.getPaddingLeft());
        assertRightAligned(root, image, root.getPaddingRight());
        assertBottomAligned(root, image, root.getPaddingBottom());
    }

    @MediumTest
    public void testSeekbar() {
        assertThat(seekBar).isShown().hasMaximum(360);

        assertLeftAligned(root, seekBar, root.getPaddingLeft());
        assertRightAligned(root, seekBar, root.getPaddingRight());
        assertBottomAligned(root, seekBar, root.getPaddingBottom());
    }

    @MediumTest
    public void testImageShouldRotateTo90() {
        testRotation(90);
    }

    @MediumTest
    public void testImageShouldRotateTo180() {
        testRotation(180);
    }

    @MediumTest
    public void testImageShouldRotateTo270() {
        testRotation(270);
    }

    @MediumTest
    public void testImageShouldRotateTo360() {
        testRotation(360);
    }

    @MediumTest
    public void testImageShouldRotateTo14() {
        testRotation(14);
    }

    private void testRotation(final int degrees) {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(degrees);
            }
        });

        assertEquals(degrees, (int) image.getRotation());
    }

}

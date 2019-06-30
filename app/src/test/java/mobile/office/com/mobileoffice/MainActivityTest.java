package mobile.office.com.mobileoffice;

import android.widget.ImageButton;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity mActivity;
    private CustomTextView mText;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .get();
        mText = mActivity.findViewById(R.id.text);
    }

    @Test
    public void activityShouldNotBeNull() throws Exception {
        assertNotNull(mActivity);
    }

    @Test
    public void activityHasTextWithString() {
        assertEquals(mActivity.getResources().getString(R.string.text_lorem),
                mText.getText());
    }

    @Test
    public void increaseButtonShouldIncreaseTextSize() {
        ImageButton incButton = mActivity.findViewById(R.id.increase_button);
        int oldSize = (int) mText.getTextSize();
        incButton.performClick();
        int size = (int) mText.getTextSize();
        assert (oldSize != size - 1);
    }

    @Test
    public void decreaseButtonShouldDecreaseTextSize() {
        ImageButton incButton = mActivity.findViewById(R.id.decrease_button);
        int oldSize = (int) mText.getTextSize();
        incButton.performClick();
        int size = (int) mText.getTextSize();
        assert oldSize != size + 1;
    }

    @Test
    public void boldButtonShouldChangeTextStyleBold() {
        ImageButton boldButton = mActivity.findViewById(R.id.bold_button);
        boolean textIsBold = mText.isBold();
        boldButton.performClick();
        assert (textIsBold != mText.isBold());
        textIsBold = mText.isBold();
        boldButton.performClick();
        boldButton.performClick();
        assert textIsBold == mText.isBold();
    }

    @Test
    public void italicButtonShouldChangeTextStyleItalic() {
        ImageButton italicButton = mActivity.findViewById(R.id.bold_button);
        boolean textIsItalic = mText.isItalic();
        italicButton.performClick();
        assert (textIsItalic != mText.isItalic());
        textIsItalic = mText.isItalic();
        italicButton.performClick();
        italicButton.performClick();
        assert textIsItalic == mText.isItalic();
    }
}
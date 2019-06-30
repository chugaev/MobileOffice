package mobile.office.com.mobileoffice;

import android.support.v7.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class CustomTextViewTest {

    private CustomTextView mTextView;
    private String testString = "test";

    @Before
    public void setUp() throws Exception {
        AppCompatActivity activity = Robolectric.buildActivity(AppCompatActivity.class).create().get();
        mTextView = new CustomTextView(activity);
        mTextView.setText(testString);
    }

    @Test
    public void customTextViewShouldHasText() {
        assertEquals(mTextView.getText(), testString);
    }

    @Test
    public void testSetSizeMethod() {
        for (int size = 1; size <= 100; size++) {
            mTextView.setTextSize(size);
            assert mTextView.getTextSize() == size;
        }
    }

    @Test
    public void testSetBoldMethod() {
        assert !mTextView.isBold();
        mTextView.setBold(true);
        assert mTextView.isBold();
        assert !mTextView.isItalic();
        mTextView.setBold(true);
        assert mTextView.isBold();
        assert !mTextView.isItalic();
        mTextView.setBold(true);
        mTextView.setBold(true);
        mTextView.setBold(true);
        assert mTextView.isBold();
        assert !mTextView.isItalic();
        mTextView.setBold(false);
        assert !mTextView.isBold();
        assert !mTextView.isItalic();
        mTextView.setBold(false);
        mTextView.setBold(false);
        assert !mTextView.isBold();
        assert !mTextView.isItalic();
    }

    @Test
    public void testSetItalicMethod() {
        assert !mTextView.isItalic();
        mTextView.setItalic(true);
        assert mTextView.isItalic();
        assert !mTextView.isBold();
        mTextView.setItalic(true);
        assert mTextView.isItalic();
        assert !mTextView.isBold();
        mTextView.setItalic(true);
        mTextView.setItalic(true);
        mTextView.setItalic(true);
        assert mTextView.isItalic();
        assert !mTextView.isBold();
        mTextView.setItalic(false);
        assert !mTextView.isItalic();
        assert !mTextView.isBold();
        mTextView.setItalic(false);
        mTextView.setItalic(false);
        assert !mTextView.isItalic();
        assert !mTextView.isBold();
    }
}

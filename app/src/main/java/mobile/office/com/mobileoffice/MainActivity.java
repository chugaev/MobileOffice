package mobile.office.com.mobileoffice;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    //private static final String TAG = "Main";

    private CustomTextView mText;
    private ImageButton mBold;
    private ImageButton mItalic;
    private ImageButton mDecrease;
    private ImageButton mIncrease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = findViewById(R.id.text);
        mBold = findViewById(R.id.bold_button);
        mItalic = findViewById(R.id.italic_button);
        mDecrease = findViewById(R.id.decrease_button);
        mIncrease = findViewById(R.id.increase_button);
        mText.setText(getString(R.string.text_lorem));
        setOnClickListeners();
    }

    // add all clicklisteners for all buttons
    private void setOnClickListeners() {
        mBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mText.isBold()) {
                    mBold.setColorFilter(getResources()
                            .getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
                    mText.setBold(false);
                } else {
                    mBold.setColorFilter(getResources()
                            .getColor(R.color.bold_italic_active_color), PorterDuff.Mode.SRC_IN);
                    mText.setBold(true);
                }
            }
        });
        mItalic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mText.isItalic()) {
                    mItalic.setColorFilter(getResources()
                            .getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
                    mText.setItalic(false);
                } else {
                    mItalic.setColorFilter(getResources()
                            .getColor(R.color.bold_italic_active_color), PorterDuff.Mode.SRC_IN);
                    mText.setItalic(true);
                }
            }
        });
        mDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mText.setTextSize((mText.getTextSize() - 1));
            }
        });
        mIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mText.setTextSize((mText.getTextSize() + 1));
            }
        });
    }
}

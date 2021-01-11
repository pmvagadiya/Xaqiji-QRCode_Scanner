package com.aperitivoexpress.fooddelivery.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class AssistantBoldTextview extends androidx.appcompat.widget.AppCompatTextView {
    public AssistantBoldTextview(Context context) {
        super(context);
        init();
    }

    public AssistantBoldTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AssistantBoldTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + "Assistant-Regular.ttf");
        setTypeface(myTypeface);
    }
}


package local.openweather.model;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Climacons for the view of weather data
 */
public class ClimaconsTextView extends TextView {
    public ClimaconsTextView(Context context) {
        super(context);
    }

    public ClimaconsTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public ClimaconsTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null && !isInEditMode()) {
            Typeface localTypeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/Climacons.ttf");
            setTypeface(localTypeFace);
        }
    }
}

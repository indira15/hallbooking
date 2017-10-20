package com.example.hallbook.hallbooking.fcm;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.widget.Toast;

import com.example.hallbook.hallbooking.R;

import java.text.Normalizer;

import static com.example.hallbook.hallbooking.R.string.message;

/**
 * Created by ARUN SUTHAR on 25-09-2017.
 */

public class Utils  {

    public static boolean isActivityAlive(Activity activity)
    {
     return !(null == activity || activity.isFinishing() || activity.isDestroyed());

    }
    public static void showToast(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public static CharSequence highlight(String search, String originalText) {
        // ignore case and accents
        // the same thing should have been done for the search text
        String normalizedText = Normalizer.normalize(originalText,
                Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        int start = normalizedText.indexOf(search);
        if (start < 0) {
            // not found, nothing to to
            return originalText;
        } else {
            Spannable spannable = new SpannableString(originalText);
            ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{
                    ContextCompat.getColor(HallBookingApplication.getInstance(), R.color.colorPrimary)
            });
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD_ITALIC,
                    -1, blueColor, null);
            spannable.setSpan(highlightSpan, start, start + search.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            return spannable;
        }
    }

    }

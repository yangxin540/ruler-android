package org.javia.ruler;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.view.MotionEvent;
import android.util.DisplayMetrics;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.ToneGenerator;

public class Ruler extends Activity implements Button.OnClickListener
{
    TextView textMM, textInch;
    Button reset;
    static private final float mmPerPx = 4.7f;
    float xdpmm, ydpmm;
    private double sumX, lastUpdate;
    ToneGenerator toneGenerator = new ToneGenerator(AudioManager.STREAM_RING, 70);
    int lastmm;

    static Paint paint = new Paint(), textPaint = new Paint();

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textMM   = (TextView) findViewById(R.id.mm);
	textInch = (TextView) findViewById(R.id.inch);
	reset = (Button) findViewById(R.id.reset);
	reset.setOnClickListener(this);

	DisplayMetrics dm = new DisplayMetrics();
	getWindowManager().getDefaultDisplay().getMetrics(dm);
	// Log.d("Ruler", "" + dm.xdpi + ' ' + dm.ydpi);
        xdpmm = dm.xdpi / 25.4f;
	ydpmm = dm.ydpi / 25.4f;
	reset();

	paint.setStyle(Paint.Style.STROKE);
	paint.setStrokeWidth(0);
	paint.setAntiAlias(false);
	paint.setColor(0xff000000);
	
	textPaint.setStyle(Paint.Style.STROKE);
	textPaint.setStrokeWidth(0);
	textPaint.setAntiAlias(true);
	textPaint.setColor(0xff000000);
    }

    private void reset() {
	sumX = 0;
	lastUpdate = -1;
	textMM.setText("0");
	textInch.setText("0.0");
	lastmm = 0;
    }

    public boolean onTrackballEvent(MotionEvent e) {
	if (e.getAction() == MotionEvent.ACTION_MOVE) {
	    sumX += e.getX();
	    if (Math.abs(sumX - lastUpdate) >= 1) {
		lastUpdate = sumX;
		toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP);
	    }
	}
	double mm = Math.abs(sumX * mmPerPx);
	int imm = (int) Math.round(mm);
	if (lastmm != imm) {
	    textMM.setText("" + imm);
	    textInch.setText("" + (Math.round(mm * 10 / 25.4) / 10.));
	    lastmm = imm;
	}	
	return true;
    }

    public void onClick(View view) {
	reset();
    }
}

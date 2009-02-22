package org.javia.ruler;

import android.graphics.Canvas;
import android.graphics.Paint;

import android.view.View;
import android.content.Context;
import android.util.AttributeSet;

public class RulerViewInch extends View {
    Paint paint = new Paint();
    static final private float pxinch = 480 / 67.f * 25.4f / 16;
    float width, height;

    public RulerViewInch(Context context, AttributeSet foo) {
	super(context, foo);
	paint.setStyle(Paint.Style.STROKE);
	paint.setStrokeWidth(0);
	paint.setAntiAlias(false);
	paint.setColor(0xff000000);
    }

    public void onSizeChanged(int w, int h, int oldW, int oldH) {
	width = w;
	height = h;
    }

    public void onDraw(Canvas canvas) {
	canvas.drawColor(0xffffffff);
	for (int i = 1; ; ++i) {
	    float y = i * pxinch;
	    if (y > 480) {
		break;
	    }
	    int size = (i%16==0) ? 45 : (i%8==0) ? 30 : (i%4==0) ? 22 : (i%2==0) ? 15 : 10;
	    canvas.drawLine(0, y, size, y, paint);
	}
    }
}

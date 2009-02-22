package org.javia.ruler;

import android.graphics.Canvas;
import android.graphics.Paint;

import android.view.View;
import android.content.Context;
import android.util.AttributeSet;

public class RulerViewMm extends View {
    Paint paint = new Paint();
    static final private float pxmm = 480 / 67.f;
    float width, height;

    public RulerViewMm(Context context, AttributeSet foo) {
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
	    float y = i * pxmm;
	    if (y > 480) {
		break;
	    }
	    int size = (i%10==0) ? 30 : (i%5==0) ? 17 : 10;
	    canvas.drawLine(width-size, y, width, y, paint);
	}
    }
}

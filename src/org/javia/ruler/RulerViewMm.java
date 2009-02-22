package org.javia.ruler;

import android.graphics.Canvas;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;

public class RulerViewMm extends View {
    static final private float pxmm = 480 / 67.f;
    float width, height;

    public RulerViewMm(Context context, AttributeSet foo) {
	super(context, foo);
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
	    int size = (i%10==0) ? 33 : (i%5==0) ? 17 : 10;
	    canvas.drawLine(width-size, y, width, y, Ruler.paint);
	}
	canvas.rotate(90);
	for (int i = 1; i <= 6; ++i) {
	    canvas.drawText("" + i, i * pxmm * 10 - 10, -17, Ruler.textPaint);
	}
    }
}

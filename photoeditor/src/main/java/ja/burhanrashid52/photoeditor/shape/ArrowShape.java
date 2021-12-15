package ja.burhanrashid52.photoeditor.shape;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import android.graphics.Path;
import android.util.Log;

import androidx.annotation.NonNull;

public class ArrowShape extends AbstractShape {

    private float lastX, lastY;

    @Override
    protected String getTag() { return "LineShape"; }

    @Override
    public void startShape(float x, float y) {
        Log.d(getTag(), "startShape@ " + x + "," + y);
        left = x;
        top = y;
    }

    @Override
    public void moveShape(float x, float y) {
        right = x;
        bottom = y;

        float dx = Math.abs(x - lastX);
        float dy = Math.abs(y - lastY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path = createLinePath();
            lastX = x;
            lastY = y;
        }
    }

    private @NonNull Path createLinePath() {
        float angle = 90;
        float radius = 35;
        float angleRad = (float) (Math.PI*angle/180.0f);
        float lineAngle = (float) (Math.atan2(bottom-top,right-left));
        Path path = new Path();
        // line
        path.moveTo(left, top);
        path.lineTo(right, bottom);
        // arrow head
        path.moveTo(right, bottom);
        path.lineTo(
            (float)(right-radius*cos(lineAngle - (angleRad / 2.0))),
            (float)(bottom-radius*sin(lineAngle - (angleRad / 2.0)))
        );
        path.lineTo(
            (float)(right-radius*cos(lineAngle + (angleRad / 2.0))),
            (float)(bottom-radius*sin(lineAngle + (angleRad / 2.0)))
        );
        path.close();
        return path;
    }

    @Override
    public void stopShape() {
        Log.d(getTag(), "stopShape");
    }

}

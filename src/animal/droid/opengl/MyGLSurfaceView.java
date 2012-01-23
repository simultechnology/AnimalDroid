package animal.droid.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class MyGLSurfaceView extends GLSurfaceView {

	private MyRenderer mMyRenderer;
	private float mWidth;
	private float mHeight;
	
	public MyGLSurfaceView(Context context) {
		super(context);
		
		setFocusable(true);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		super.surfaceChanged(holder, format, w, h);
		this.mWidth = w;
		this.mHeight = h;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = (event.getX() / (float) mWidth) * 3.f - 1.5f;
		float y = (event.getY() / (float) mHeight) * 2.f - 1.f;
		
		mMyRenderer.touched(x, -y);
		
		return false;
	}
	
	@Override
	public void setRenderer(Renderer renderer) {
		super.setRenderer(renderer);
		this.mMyRenderer = (MyRenderer) renderer;
	}
}

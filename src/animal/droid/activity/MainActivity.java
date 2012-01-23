package animal.droid.activity;

import animal.droid.opengl.MyGLSurfaceView;
import animal.droid.opengl.MyRenderer;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends BaseActivity {
	private MyGLSurfaceView mMyGlSurfaceView;	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // フルスクリーン表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        MyRenderer renderer = new MyRenderer(getApplicationContext());
        mMyGlSurfaceView = new MyGLSurfaceView(this);
        mMyGlSurfaceView.setRenderer(renderer);
        
        setContentView(mMyGlSurfaceView);
    }
}
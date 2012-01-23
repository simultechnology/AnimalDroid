package animal.droid.activity;

import android.app.Activity;
import android.os.Bundle;
import animal.droid.sound.Bgm;

/**
 * Activityの共通的な振る舞いを定義するクラスです。
 *
 */
public class BaseActivity extends Activity {
	
	private Bgm mBgm;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.mBgm = new Bgm(this);
        mBgm.start();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	
    	mBgm.stop();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
		
    	mBgm.start();
    }
}

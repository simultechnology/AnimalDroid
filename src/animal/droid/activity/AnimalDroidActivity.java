package animal.droid.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import animal.droid.R;

public class AnimalDroidActivity extends BaseActivity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // フルスクリーン表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        setContentView(R.layout.menu);
        ImageButton enterButton = (ImageButton) findViewById(R.id.enter_button);
        enterButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AnimalDroidActivity.this,MainActivity.class);
				startActivityForResult(intent,0);
			}
		});
    }
}
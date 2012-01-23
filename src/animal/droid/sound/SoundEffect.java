package animal.droid.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import animal.droid.R;;

public class SoundEffect {
	 
	private SoundPool mSoundPool;
	private int lionSound;
	private int penguinSound;
	
	public SoundEffect(Context context) {
		this.mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		
		this.lionSound = mSoundPool.load(context, R.raw.lion1, 1);
		this.penguinSound = mSoundPool.load(context, R.raw.penguin1, 1);
	}
	
	public void playLionSound() {
		mSoundPool.play(lionSound, 1.f, 1.f, 1, 0, 1.f);
	}
	
	public void playPenguinSound() {
		mSoundPool.play(penguinSound, 1.f, 1.f, 1, 0, 1.f);

	}

}

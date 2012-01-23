package animal.droid.sound;

import android.content.Context;
import android.media.MediaPlayer;
import animal.droid.R;

public class Bgm {
	private MediaPlayer mMediaPlayer;
	
	public Bgm(Context context) {
		this.mMediaPlayer = MediaPlayer.create(context, R.raw.zoobgm);
		this.mMediaPlayer.setVolume(1.f, 1.f);
	}
	
	public void start() {
		if (!mMediaPlayer.isPlaying()) {
			mMediaPlayer.seekTo(0);
			mMediaPlayer.start();
		}
	}
	
	public void stop() {
		if (mMediaPlayer.isPlaying()) {
			mMediaPlayer.stop();
			mMediaPlayer.prepareAsync();
		}
	}
}

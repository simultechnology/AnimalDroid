package animal.droid.opengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import animal.droid.R;
import animal.droid.sound.SoundEffect;
import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

public class MyRenderer implements Renderer {
	
	private Context mContext;
	
	private int mWidth;
	private int mHeight;
	
	// テクスチャを管理するためのID
	private int mTexture;
	// 草の画像ID
	private int mTextureKusa;
	// ライオンの画像ID
	private int mTextureLion;
	// 叫んでいるライオンの画像ID
	private int mTextureLionOver;
	// ライオンがクリックされたらfalse
	private boolean lionOver = false;
	
	// ペンギンの画像ID
	private int mTexturePenguin;
	// 鳴いているペンギンの画像ID
	private int mTexturePenguinOver;
	// ペンギンがクリックされたらfalse
	private boolean penguinOver = false;
	
	private float mLionX, mLionY;
	
	private float mLionSize;
	
	private float mPenquinX, mPenquinY;
	
	private float mPenquinSize;
	
	private float mLionAngle = 0.f;
	private float mPenguinAngle = 0.f;

	// 効果音
	private SoundEffect mSoundEffect;
	
	public MyRenderer(Context context) {
		this.mContext = context;
		
		this.mLionX = -0.6f;
		this.mLionY = -0.28f;
		this.mLionSize = 1.f;

		this.mPenquinX = 0.6f;
		this.mPenquinY = -0.29f;
		this.mPenquinSize = 1.f;
		
		this.mSoundEffect = new SoundEffect(context); 
	}

	
	public void renderMain(GL10 gl) {
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		GraphicUtils.drawTexture(gl, 0.f, 0.f, 3.f, 2.f, mTexture, 0.f, 0.f, 1.f, 0.57f, 1.0f, 1.0f, 1.0f, 1.f);
		
		gl.glPushMatrix();
		if (!lionOver) {
			GraphicUtils.drawTexture(gl, mLionX, mLionY, 1.05f, 1.05f, mTextureLion, 0.f, 0.f, 1.f, 1.f, 1.0f, 1.0f, 1.0f, 1.f);
		}
		else {
			gl.glTranslatef(0.f, (float) (Math.sin(Math.toRadians(mLionAngle)) / 1.5),  0.f);
			GraphicUtils.drawTexture(gl, mLionX, mLionY, 1.15f, 1.15f, mTextureLionOver, 0.f, 0.f, 1.f, 1.f, 1.0f, 1.0f, 1.0f, 1.f);
			gl.glLoadIdentity();
			mLionAngle += 7.5f;
			if (mLionAngle > 170.f) {
				lionOver = false;
				mLionAngle = 0.f;
			}
		}
		gl.glPopMatrix();
		gl.glPushMatrix();
		if (!penguinOver) {
			GraphicUtils.drawTexture(gl, mPenquinX, mPenquinY, 1.f, 1.f, mTexturePenguin, 0.f, 0.f, 1.f, 1.f, 1.f, 1.f, 1.f, 1.f);
		}
		else {
			gl.glTranslatef(0.f, (float) (Math.sin(Math.toRadians(mPenguinAngle)) / 1.5),  0.f);
			GraphicUtils.drawTexture(gl, mPenquinX, mPenquinY, 1.f, 1.f, mTexturePenguinOver, 0.f, 0.f, 1.f, 1.f, 1.0f, 1.0f, 1.0f, 1.f);
			gl.glLoadIdentity();
			mPenguinAngle += 7.5f;
			if (mPenguinAngle > 170.f) {
				penguinOver = false;
				mPenguinAngle = 0.f;
			}
		}
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		GraphicUtils.drawTexture(gl, 0.f, -0.2f, 3.f, 2.f, mTextureKusa, 0.f, 0.f, 1.f, 0.35f, 1.0f, 1.0f, 1.0f, 1.f);
		gl.glPopMatrix();
		
		gl.glDisable(GL10.GL_BLEND);
		
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glViewport(0, 0, mWidth, mHeight);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		
		gl.glLoadIdentity();
		gl.glOrthof(-1.5f, 1.5f, -1.f, 1.0f, 0.5f, -0.5f);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		renderMain(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		this.mWidth = width;
		this.mHeight = height;
		
		Global.gl = gl;
		
		// テクスチャの生成を行う
		this.mTexture = GraphicUtils.loadTexture(gl, mContext.getResources(), R.drawable.my_summer_bg);
		if (this.mTexture ==  0) {
			Log.e(getClass().toString(), "texture load Error!");
		}
		
		// 草テクスチャの生成を行う
		this.mTextureKusa = GraphicUtils.loadTexture(gl, mContext.getResources(), R.drawable.my_kusa);
		if (this.mTextureKusa ==  0) {
			Log.e(getClass().toString(), "texture load Error!");
		}
		
		// ライオンテクスチャの生成を行う
		this.mTextureLion = GraphicUtils.loadTexture(gl, mContext.getResources(), R.drawable.lion);
		if (this.mTextureLion ==  0) {
			Log.e(getClass().toString(), "texture load Error!");
		}
		
		// 吠えているライオンテクスチャの生成を行う
		this.mTextureLionOver = GraphicUtils.loadTexture(gl, mContext.getResources(), R.drawable.lion_over);
		if (this.mTextureLionOver ==  0) {
			Log.e(getClass().toString(), "texture load Error!");
		}
		
		// ペンギンテクスチャの生成を行う
		this.mTexturePenguin = GraphicUtils.loadTexture(gl, mContext.getResources(), R.drawable.penguin);
		if (this.mTexturePenguin ==  0) {
			Log.e(getClass().toString(), "texture load Error!");
		}
		
		// 鳴いているペンギンテクスチャの生成を行う
		this.mTexturePenguinOver = GraphicUtils.loadTexture(gl, mContext.getResources(), R.drawable.penguin_over);
		if (this.mTexturePenguinOver ==  0) {
			Log.e(getClass().toString(), "texture load Error!");
		}
		
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO 自動生成されたメソッド・スタブ

	}
	
	public void touched(float x, float y) {
		float dx = x - mLionX;
		float dy = y - mLionY;
		float distance = (float) Math.sqrt(dx * dx + dy * dy);
		
		if (distance <= mLionSize * 0.6f) {
			if (!lionOver) {
				mSoundEffect.playLionSound();
			}
			lionOver = true;
		}
		
		float px = x - mPenquinX;
		float py = y - mPenquinY;
		float pDistance = (float) Math.sqrt(px * px + py * py);
		
		if (pDistance <= mPenquinSize * 0.6f) {
			if (!penguinOver) {
				mSoundEffect.playPenguinSound();
			}
			penguinOver = true;
		}
	}
}

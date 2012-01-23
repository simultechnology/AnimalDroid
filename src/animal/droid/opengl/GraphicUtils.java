package animal.droid.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.opengl.GLUtils;

public class GraphicUtils {

	public static final FloatBuffer makeFloatBuffer(float[] arr) {
		ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer fb = bb.asFloatBuffer();
		fb.put(arr);
		fb.position(0);
		return fb;
	}
	
	public static final void drawSquare(GL10 gl) {
		drawSquare(gl, 1.f, 0.f, 0.f, 1.f);
			
	}
	
	public static final void drawSquare(GL10 gl, float r, float g, float b, float a) {
		drawSquare(gl, 0.f, 0.f, r, g, b, a);
		
	}
	
	public static final void drawSquare(GL10 gl, float x, float y, float r, float g, float b, float a) {
		drawRectangle(gl, x, y, 1.f, 1.f, r, g, b, a);
		
	}
	
	public static final void drawRectangle(GL10 gl, float x, float y, 
			float width, float height, float r, float g, float b, float a) {
		float[] verticies = {
				-0.5f * width + x, -0.5f * height + y,
				0.5f * width + x, -0.5f * height + y,
				-0.5f * width + x, 0.5f * height + y,
				0.5f * width + x, 0.5f * height + y,
			};
			
		float[] colors = {
			r, g, b, a,
			r, g, b, a,
			r, g, b, a,
			r, g, b, a,
		};
		
		FloatBuffer polygonVerticies = GraphicUtils.makeFloatBuffer(verticies);
		FloatBuffer polygonColors = GraphicUtils.makeFloatBuffer(colors);
		
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, polygonVerticies);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, polygonColors);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		
	}
	
	/**
	 * 
	 * @param gl
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param texture
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 */
	public static final void drawTexture(GL10 gl, float x, float y, 
			float width, float height, int texture, float r, float g, float b, float a) {
		
		drawTexture(gl, x, y, width, height, texture, 0.f, 0.f, 1.f, 1.f, r, g, b, a);
		
	}
	
	public static final void drawTexture(GL10 gl, float x, float y, 
			float width, float height, int texture, float u, float v, float tex_w,
			float tex_h, float r, float g, float b, float a) {
		
		float[] verticies = {
				-0.5f * width + x, -0.5f * height + y,
				0.5f * width + x, -0.5f * height + y,
				-0.5f * width + x, 0.5f * height + y,
				0.5f * width + x, 0.5f * height + y,
			};
			
		float[] colors = {
			r, g, b, a,
			r, g, b, a,
			r, g, b, a,
			r, g, b, a,
		};
		
		float[] coords = {
					u, 	v + tex_h,	
			u + tex_w, 	v + tex_h,	
					u, 			v,	
			u + tex_w, 			v,	
		};
		
		FloatBuffer polygonVerticies = GraphicUtils.makeFloatBuffer(verticies);
		FloatBuffer polygonColors = GraphicUtils.makeFloatBuffer(colors);
		FloatBuffer texCoords = GraphicUtils.makeFloatBuffer(coords);
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		// テクスチャオブジェクトの指定
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, polygonVerticies);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, polygonColors);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texCoords);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		
	}
	
	private static final BitmapFactory.Options options = new BitmapFactory.Options();
	static {
		options.inScaled = false;
		options.inPreferredConfig = Config.ARGB_8888;
	}
	
	/**
	 * 
	 * @param gl
	 * @param resources
	 * @param resourceId
	 * @return
	 */
	public static final int loadTexture(GL10 gl, Resources resources, int resourceId) {
		
		int[] textures = new int[1]; 
		
		Bitmap bmp = BitmapFactory.decodeResource(resources, resourceId, options);
		if (bmp == null) {
			return 0;
		}
		
		// OpenGL用のテクスチャを生成する
		gl.glGenTextures(1, textures, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);//テクスチャの繰り返し方法を設定
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
		
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		
		// OpenGLへの転送が完了したので、VMメモリ上に作成したBitmapを破棄する
		bmp.recycle();
		
		TextureManager.addTexture(resourceId, textures[0]);
		
		return textures[0];
	}
}

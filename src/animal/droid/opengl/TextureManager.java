package animal.droid.opengl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

public class TextureManager {
	// テクスチャを保持する
	private static Map<Integer, Integer> mTexture =
										new HashMap<Integer, Integer>();
	
	/**
	 * ロードしたテクスチャを保持します
	 * 
	 * @param resourceId
	 * @param texId
	 */
	public static final void addTexture(int resourceId, int texId) {
		mTexture.put(resourceId, texId);
	}
	
	/**
	 * テクスチャを削除します
	 * 
	 * @param gl
	 * @param resourceId
	 */
	public static final void deleteTexure(GL10 gl, int resourceId) {
		if (mTexture.containsKey(resourceId)) {
			int[] texId = new int[1];
			texId[0] = mTexture.get(resourceId);
			gl.glDeleteTextures(1, texId, 0);
			mTexture.remove(resourceId);
		}
	}
	
	/**
	 * 全てのテクスチャを削除します
	 * 
	 * @param gl
	 */
	public static final void deleteAll(GL10 gl) {
		List<Integer> keys = new ArrayList<Integer>(mTexture.keySet());
		for (Integer key : keys) {
			deleteTexure(gl, key);
		}
	}
}

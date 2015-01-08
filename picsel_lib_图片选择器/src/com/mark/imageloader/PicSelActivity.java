package com.mark.imageloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mark.imageloader.ListImageDirPopupWindow.OnImageDirSelected;
import com.mark.imageloader.MyAdapter.FileUtils;
import com.mark.imageloader.MyAdapter.OnPicSelCallBack;
import com.mark.utils.ImageLoader.Type;
import com.mark.utils.bean.ImageFloder;
import com.mark.utils.ImageLoader;
import com.mark.utils.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.widget.StaggeredGridView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PicSelActivity extends Activity implements OnImageDirSelected {
	protected static final int MAX_SEL_ENABLE = 5;
	public static final int REQUEST_PICS_CODE = 201;
	public static final int RESULT_PICS_OK_CODE = 202;
	public static final String RESULT_PICS_ARRAY = "RESULT_PICS_ARRAY";
	private ProgressDialog mProgressDialog;

	/**
	 * �洢�ļ����е�ͼƬ����
	 */
	private int mPicsSize;
	/**
	 * ͼƬ���������ļ���
	 */
	private File mImgDir;
	/**
	 * ���е�ͼƬ
	 */
	private List<String> mImgs;

	private StaggeredGridView mGirdView;
	private MyAdapter mAdapter;
	/**
	 * ��ʱ�ĸ����࣬���ڷ�ֹͬһ���ļ��еĶ��ɨ��
	 */
	private HashSet<String> mDirPaths = new HashSet<String>();

	/**
	 * ɨ���õ����е�ͼƬ�ļ���
	 */
	private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();

	// private RelativeLayout mBottomLy;

	// private TextView mChooseDir;
	// private TextView mImageCount;
	int totalCount = 0;
	private int mScreenHeight;
	private ListImageDirPopupWindow mListImageDirPopupWindow;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mProgressDialog.dismiss();
			// ΪView������
			data2View();
			// ��ʼ��չʾ�ļ��е�popupWindw
			initListDirPopupWindw();
		}
	};

	// ͼƬѡ��ص�
	private OnPicSelCallBack onPicSelCallBack = new OnPicSelCallBack() {

		@Override
		public void onSel(final Set<String> mSelectedImage) {
			PicSelActivity.this.onSel(mSelectedImage);
		}
	};

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	protected void onSel(final Set<String> mSelectedImage) {
		if (mSelectedImage == null) {
			return;
		}
		ly_bt_content.removeAllViews();
		
		for (final String uri:mSelectedImage) {
			View child = getLayoutInflater().inflate(
					R.layout.com_mark_utils_picsel_bottom_item, null);
			ImageView img = (ImageView) child.findViewById(R.id.id_item_image);
			ImageLoader.getInstance(3, Type.LIFO).loadImage(uri, img);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					dip2px(PicSelActivity.this, 77), dip2px(
							PicSelActivity.this, 77));
			child.setLayoutParams(params);

			RelativeLayout.LayoutParams paramsr = new RelativeLayout.LayoutParams(
					dip2px(PicSelActivity.this, 57), dip2px(
							PicSelActivity.this, 57));
			paramsr.topMargin = dip2px(PicSelActivity.this, 10);
			paramsr.leftMargin = dip2px(PicSelActivity.this, 10);
			img.setLayoutParams(paramsr);
			child.findViewById(R.id.id_item_close).setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View view) {
							ly_bt_content.removeView((View) view.getParent());
							mSelectedImage.remove(uri);
							mAdapter.notifyDataSetChanged();
							id_sel_count.setText(mSelectedImage.size() + "/"
									+ MAX_SEL_ENABLE);
						}
					});
			ly_bt_content.addView(child);
		}
		
//		for (int i = 0; i < mSelectedImage.size(); i++) {
//			final String uri = mSelectedImage.get(i);
//			View child = getLayoutInflater().inflate(
//					R.layout.com_mark_utils_picsel_bottom_item, null);
//			ImageView img = (ImageView) child.findViewById(R.id.id_item_image);
//			ImageLoader.getInstance(3, Type.LIFO).loadImage(uri, img);
//			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//					dip2px(PicSelActivity.this, 77), dip2px(
//							PicSelActivity.this, 77));
//			child.setLayoutParams(params);
//
//			RelativeLayout.LayoutParams paramsr = new RelativeLayout.LayoutParams(
//					dip2px(PicSelActivity.this, 57), dip2px(
//							PicSelActivity.this, 57));
//			paramsr.topMargin = dip2px(PicSelActivity.this, 10);
//			paramsr.leftMargin = dip2px(PicSelActivity.this, 10);
//			img.setLayoutParams(paramsr);
//			child.findViewById(R.id.id_item_close).setOnClickListener(
//					new OnClickListener() {
//						@Override
//						public void onClick(View view) {
//							ly_bt_content.removeView((View) view.getParent());
//							mSelectedImage.remove(uri);
//							mAdapter.notifyDataSetChanged();
//							id_sel_count.setText(mSelectedImage.size() + "/"
//									+ MAX_SEL_ENABLE);
//						}
//					});
//			ly_bt_content.addView(child);
//		}
		id_sel_count.setText(mSelectedImage.size() + "/" + MAX_SEL_ENABLE);
	}

	public static int dip2px(Context context, int dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	private LinearLayout ly_bt_content;

	private TextView id_sel_count;

	private View ly_top;
	private File tmpFile;
	private String tmpPath;
	private static PicSelActivity instance;

	/**
	 * ΪView������
	 */
	private void data2View() {
		if (mImgDir == null) {
			Toast.makeText(getApplicationContext(), "����һ��ͼƬûɨ�赽",
					Toast.LENGTH_SHORT).show();
			return;
		}

		mImgs = Arrays.asList(mImgDir.list());
		/**
		 * ���Կ����ļ��е�·����ͼƬ��·���ֿ����棬����ļ������ڴ�����ģ�
		 */
		mAdapter = new MyAdapter(getApplicationContext(), mImgs,
				R.layout.com_mark_utils_picsel_grid_item,
				mImgDir.getAbsolutePath());
		mAdapter.setOnPicSelCallBack(onPicSelCallBack);
		mGirdView.setAdapter(mAdapter);
		// mImageCount.setText(totalCount + "��");
	};

	/**
	 * ��ʼ��չʾ�ļ��е�popupWindw
	 */
	private void initListDirPopupWindw() {
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.com_mark_utils_picsel_list_dir, null);
		mListImageDirPopupWindow = new ListImageDirPopupWindow(
				LayoutParams.MATCH_PARENT, (int) (mScreenHeight - dip2px(this,
						110)), mImageFloders, view);

		mListImageDirPopupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				// ���ñ�����ɫ�䰵
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 1.0f;
				getWindow().setAttributes(lp);
			}
		});
		// ����ѡ���ļ��еĻص�
		mListImageDirPopupWindow.setOnImageDirSelected(this);
		view.findViewById(R.id.id_cancel).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						mListImageDirPopupWindow.dismiss();
					}
				});
	}

	public static File getSDCardDir(Context context, String dirName) {
		FileUtils fileUtils = new FileUtils();
		if (!fileUtils.isSDCardMounted()) {
			return context.getFilesDir();
		}
		if (!fileUtils.isDirectory(fileUtils.getSDPATH() + dirName)) {
			fileUtils.createSDDir(dirName);
		}
		return new File(fileUtils.getSDPATH() + dirName);
	}

	public static PicSelActivity getInstance() {
		return instance;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		instance = null;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		instance = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.com_mark_utils_picsel);

		ArrayList<String> pics = getIntent().getStringArrayListExtra(
				RESULT_PICS_ARRAY);
		if (pics != null) {
			if (MyAdapter.mSelectedImage ==null) {
				MyAdapter.mSelectedImage = new HashSet<String>();
			}
			MyAdapter.mSelectedImage.clear();
			MyAdapter.mSelectedImage.addAll(pics);
			
		} else {
			if (MyAdapter.mSelectedImage != null) {
				MyAdapter.mSelectedImage.clear();
			}
		}

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		mScreenHeight = outMetrics.heightPixels;

		initView();
		getImages();
		initEvent();

		onSel(MyAdapter.mSelectedImage);
	}

	/**
	 * ����ContentProviderɨ���ֻ��е�ͼƬ���˷��������������߳��� ���ͼƬ��ɨ�裬���ջ��jpg�����Ǹ��ļ���
	 */
	private void getImages() {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "�����ⲿ�洢", Toast.LENGTH_SHORT).show();
			return;
		}
		// ��ʾ������
		mProgressDialog = ProgressDialog.show(this, null, "���ڼ���...");

		new Thread(new Runnable() {
			@Override
			public void run() {

				String firstImage = null;

				Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				ContentResolver mContentResolver = PicSelActivity.this
						.getContentResolver();

				// ֻ��ѯjpeg��png��ͼƬ
				Cursor mCursor = mContentResolver.query(mImageUri, null,
						MediaStore.Images.Media.MIME_TYPE + "=? or "
								+ MediaStore.Images.Media.MIME_TYPE + "=?",
						new String[] { "image/jpeg", "image/png" },
						MediaStore.Images.Media.DATE_MODIFIED);

				Log.e("TAG", mCursor.getCount() + "");
				while (mCursor.moveToNext()) {
					// ��ȡͼƬ��·��
					String path = mCursor.getString(mCursor
							.getColumnIndex(MediaStore.Images.Media.DATA));

					Log.e("TAG", path);
					// �õ���һ��ͼƬ��·��
					if (firstImage == null)
						firstImage = path;
					// ��ȡ��ͼƬ�ĸ�·����
					File parentFile = new File(path).getParentFile();
					if (parentFile == null)
						continue;
					String dirPath = parentFile.getAbsolutePath();
					ImageFloder imageFloder = null;
					// ����һ��HashSet��ֹ���ɨ��ͬһ���ļ��У���������жϣ�ͼƬ�����������൱�ֲ���~~��
					if (mDirPaths.contains(dirPath)) {
						continue;
					} else {
						mDirPaths.add(dirPath);
						// ��ʼ��imageFloder
						imageFloder = new ImageFloder();
						imageFloder.setDir(dirPath);
						imageFloder.setFirstImagePath(path);
					}

					int picSize = parentFile.list(new FilenameFilter() {
						@Override
						public boolean accept(File dir, String filename) {
							if (filename.endsWith(".jpg")
									|| filename.endsWith(".png")
									|| filename.endsWith(".jpeg"))
								return true;
							return false;
						}
					}).length;
					totalCount += picSize;

					imageFloder.setCount(picSize);
					mImageFloders.add(imageFloder);

					if (picSize > mPicsSize) {
						mPicsSize = picSize;
						mImgDir = parentFile;
					}
				}
				mCursor.close();

				// ɨ����ɣ�������HashSetҲ�Ϳ����ͷ��ڴ���
				mDirPaths = null;

				// ֪ͨHandlerɨ��ͼƬ���
				mHandler.sendEmptyMessage(0x110);

			}
		}).start();

	}

	/**
	 * ��ʼ��View
	 */
	private void initView() {
		mGirdView = (StaggeredGridView) findViewById(R.id.id_gridView);
		mGirdView.setColumnCount(4);
		ly_bt_content = (LinearLayout) findViewById(R.id.ly_bt_content);
		id_sel_count = (TextView) findViewById(R.id.id_sel_count);
		id_sel_count.setText("0/" + MAX_SEL_ENABLE);
		findViewById(R.id.id_cancel).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						finish();
					}
				});
		findViewById(R.id.id_ok).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent data = new Intent();
				ArrayList<String> tmpArrayList=new ArrayList<String>();
				if (MyAdapter.mSelectedImage!=null) {
					tmpArrayList.addAll(MyAdapter.mSelectedImage);
				}
				if (MyAdapter.mSelectedImage.size() > 0) {
					data.putExtra(RESULT_PICS_ARRAY, tmpArrayList);
				}
				setResult(RESULT_PICS_OK_CODE, data);
				finish();
			}
		});

	}

	private void initEvent() {
		ly_top = findViewById(R.id.ly_top);
		/**
		 * Ϊ�ײ��Ĳ������õ���¼�������popupWindow
		 */
		findViewById(R.id.sel_pic).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mListImageDirPopupWindow
						.setAnimationStyle(R.style.anim_popup_dir);
				mListImageDirPopupWindow.showAsDropDown(ly_top, 0, 0);

				// ���ñ�����ɫ�䰵
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = .3f;
				getWindow().setAttributes(lp);
			}
		});
	}

	@Override
	public void selected(ImageFloder floder) {

		mImgDir = new File(floder.getDir());
		mImgs = Arrays.asList(mImgDir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				if (filename.endsWith(".jpg") || filename.endsWith(".png")
						|| filename.endsWith(".jpeg"))
					return true;
				return false;
			}
		}));
		/**
		 * ���Կ����ļ��е�·����ͼƬ��·���ֿ����棬����ļ������ڴ�����ģ�
		 */
		mAdapter = new MyAdapter(getApplicationContext(), mImgs,
				R.layout.com_mark_utils_picsel_grid_item,
				mImgDir.getAbsolutePath());
		mAdapter.setOnPicSelCallBack(onPicSelCallBack);
		mGirdView.setAdapter(mAdapter);
		// mAdapter.notifyDataSetChanged();
		// mImageCount.setText(floder.getCount() + "��");
		// mChooseDir.setText(floder.getName());
		mListImageDirPopupWindow.dismiss();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK
				&& requestCode == PicSelActivity.REQUEST_PICS_CODE) {
			if (tmpPath!=null) {
				Toast.makeText(this,tmpPath, Toast.LENGTH_SHORT).show();
				MyAdapter.mSelectedImage.add(tmpPath);
				onSel(MyAdapter.mSelectedImage);
			}
		}
	}

	int count;
	public File getTmpFile() {
		File dir = getSDCardDir(this, "mljia");
		if (dir != null) {
			tmpFile = new File(dir, "tmp.jpg"+count);
			if (tmpFile != null) {
				tmpPath = tmpFile.getAbsolutePath();
				count++;
				if (count>MAX_SEL_ENABLE) {
					count=0;
				}
				return tmpFile;
			}
		}
		return null;
	}

	public static Bitmap comPressBitmap(String path, int w, int h) {
		Bitmap tmpBitmap = null;
		try {
			tmpBitmap = createNewBitmapAndCompressByFile(path,
					new int[] { w, h });
		} catch (RuntimeException e) {
		} catch (Exception e) {
		}
		return tmpBitmap;
	}

	public static Bitmap createNewBitmapAndCompressByFile(String filePath,
			int wh[]) {
		int offset = 100;
		File file = new File(filePath);
		int raye = readPictureDegree(filePath);

		long fileSize = file.length();
		if (200 * 1024 < fileSize && fileSize <= 1024 * 1024)
			offset = 90;
		else if (1024 * 1024 < fileSize)
			offset = 85;

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true; // Ϊtrue��ֻ��ͼƬ����Ϣ������������ص�bitmapΪnull
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		options.inDither = false;
		/**
		 * ����ͼƬ�ߴ� //TODO ���������ųߴ�
		 */
		BitmapFactory.decodeFile(filePath, options);

		int bmpheight = options.outHeight;
		int bmpWidth = options.outWidth;
		int inSampleSize = bmpheight / wh[1] > bmpWidth / wh[0] ? bmpheight
				/ wh[1] : bmpWidth / wh[0];
		// if(bmpheight / wh[1] < bmpWidth / wh[0]) inSampleSize = inSampleSize
		// * 2 / 3;//TODO ���ͼƬ̫����߶�̫С����ѹ������̫�����Գ���2/3
		if (inSampleSize > 1)
			options.inSampleSize = inSampleSize;// �������ű���
		options.inJustDecodeBounds = false;

		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			return null;
		}
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(is, null, options);
		} catch (OutOfMemoryError e) {
			System.gc();
			bitmap = null;
		}

		if (offset == 100)
			return bitmap;// ��С����
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		if (bitmap != null) {
			bitmap.compress(Bitmap.CompressFormat.JPEG, offset, baos);
		}
		byte[] buffer = baos.toByteArray();
		options = null;
		if (buffer.length >= fileSize)
			return bitmap;
		try {
			return BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (raye != 0) {
			bitmap = rotaingImageView(-raye, bitmap);
		}
		return bitmap;
	}

	/**
	 * @desc <pre>
	 * ��תͼƬ
	 * </pre>
	 * @author Weiliang Hu
	 * @date 2013-9-18
	 * @param angle
	 * @param bitmap
	 * @return
	 */
	public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
		// ��תͼƬ ����
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		System.out.println("angle2=" + angle);
		// �����µ�ͼƬ
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		bitmap.recycle();
		return resizedBitmap;
	}

	/**
	 * @desc <pre>
	 * ��ȡͼƬ��ת�ĽǶ�
	 * </pre>
	 * @author Weiliang Hu
	 * @date 2013-9-18
	 * @param path
	 * @return
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

}

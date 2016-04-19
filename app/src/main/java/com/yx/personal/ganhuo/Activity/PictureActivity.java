package com.yx.personal.ganhuo.Activity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yx.personal.ganhuo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PictureActivity extends BaseActivity {
    private SimpleDraweeView simpleDraweeView;
    private String url;

    @Override
    protected int getContentView() {
        return R.layout.activity_picture;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_picture_toolbar, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Balala");
        setIsShowBack(true);
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.draweeview_picture_show);
        url = getIntent().getStringExtra("url");
        simpleDraweeView.setImageURI(Uri.parse(url));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_download:
                Log.e("TAG", "action_download" + Environment.getExternalStorageDirectory());
                saveImg();
                Log.e("TAG", "action_download");
                break;
            case R.id.action_share:
                Log.e("TAG", "action_share");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveImg() {
        FileBinaryResource resource = (FileBinaryResource) Fresco.getImagePipelineFactory().getMainDiskStorageCache().getResource(new SimpleCacheKey(url.toString()));
        File file = resource.getFile();
        Log.e("TAG", file.getName());
        File appDir = new File(Environment.getExternalStorageDirectory(), "GanHuo");
        if(!appDir.exists()){
            appDir.mkdir();
        }
        Bitmap bitmap = null;
        String fileName ="dsfg" + ".jpg";
        File newfile = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            assert bitmap != null;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        File appDirFile = new File(appDir,file.getName());
        Log.e("TAG", appDir.getAbsolutePath());
    }


}

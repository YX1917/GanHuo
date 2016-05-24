package com.yx.personal.ganhuo.Activity;

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
import java.io.FileInputStream;
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
        // Inflate the menu; this adds items to the action bar if it is present.
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_download:
                Log.e("TAG", "action_download");
                saveImg();
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
        String fileName =file.getName().replace(".cnt",".png");
        File newFile = new File(appDir, fileName);
        if (newFile.exists()) {
            newFile.delete();
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(newFile);
            byte[] b = new byte[1024];
            int len;
            while ((len = fis.read(b)) != -1) {
                fos.write(b, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }




}

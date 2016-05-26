package com.yx.personal.ganhuo.Activity;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.Utils.AppManager;
import com.yx.personal.ganhuo.Utils.PictUtil;

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
        AppManager.getAppManager().addActivity(this);


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
            case android.R.id.home:
                AppManager.getAppManager().finishActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveImg() {
        FileBinaryResource resource = (FileBinaryResource) Fresco.getImagePipelineFactory().getMainDiskStorageCache().getResource(new SimpleCacheKey(url.toString()));
        File file = resource.getFile();
        if(PictUtil.saveFile(file)){
            Toast.makeText(PictureActivity.this,"图片已保存",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(PictureActivity.this,"图片保存失败，请重试",Toast.LENGTH_SHORT).show();
        }
    }
}
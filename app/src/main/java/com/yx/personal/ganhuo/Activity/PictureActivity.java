package com.yx.personal.ganhuo.Activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yx.personal.ganhuo.R;

public class PictureActivity extends BaseActivity {
    private SimpleDraweeView simpleDraweeView;

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

        simpleDraweeView.setImageURI(Uri.parse(getIntent().getStringExtra("url")));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_download:
                Log.e("TAG", "action_download");
                break;
            case R.id.action_share:
                Log.e("TAG", "action_share");
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}

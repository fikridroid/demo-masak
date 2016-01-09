package tuxdev.studio.demokutaibarat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

public class ActivityDetailBerita extends AppCompatActivity {
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    String judul, gambar, tahun, genre, deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);
        Toolbar mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        //Show back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SharedPreferences prefPersen = getSharedPreferences("Berita",
                Context.MODE_PRIVATE);


        judul = prefPersen.getString("judul", null);
        gambar = prefPersen.getString("gambar", null);
        tahun = prefPersen.getString("tahun", null);
        genre = prefPersen.getString("genre", null);
        deskripsi = prefPersen.getString("deskripsi", null);

        setTitle(judul);

        Log.d("DEMO", judul);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        FeedImageView feedImageView= (FeedImageView) findViewById(R.id.listImage);
        TextView TvDeskripsi = (TextView) findViewById(R.id.detail_berita);
        TextView Tvgenre = (TextView) findViewById(R.id.genre);
        TextView Tvyear = (TextView) findViewById(R.id.tahun);

        // getting movie data for the row

        // Feed image
        if (gambar != null) {
            feedImageView.setImageUrl(gambar
                    , imageLoader);
            feedImageView.setVisibility(View.VISIBLE);
            feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {
            feedImageView.setVisibility(View.GONE);
        }

        // title
//        title.setText(m.getTitle());

        // rating
//        rating.setText("Rating: " + String.valueOf(m.getRating()));

        // genre
        Tvgenre.setText("Jenis Berita : "+genre);

        // release year
        Tvyear.setText("Tahun : "+tahun);

        TvDeskripsi.setText(deskripsi);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package tuxdev.studio.demokutaibarat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by ukietux on 09/01/16.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DetailItem> detailItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<DetailItem> detailItems) {
        this.activity = activity;
        this.detailItems = detailItems;
    }

    @Override
    public int getCount() {
        return detailItems.size();
    }

    @Override
    public Object getItem(int location) {
        return detailItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        CustomImageView customImageView = (CustomImageView) convertView
                .findViewById(R.id.listImage);
        TextView title = (TextView) convertView.findViewById(R.id.judul);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView year = (TextView) convertView.findViewById(R.id.tahun);

        // getting movie data for the row
        DetailItem m = detailItems.get(position);

        // Feed image
        if (m.getThumbnailUrl() != null) {
            customImageView.setImageUrl(m.getThumbnailUrl()
                    , imageLoader);
            customImageView.setVisibility(View.VISIBLE);
            customImageView
                    .setResponseObserver(new CustomImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {
            customImageView.setVisibility(View.GONE);
        }

        // title
        title.setText(m.getTitle());

        // rating
//        rating.setText("Rating: " + String.valueOf(m.getRating()));

        // genre
        genre.setText(m.getGenre());

        // release year
        year.setText(String.valueOf(m.getYear()));

        return convertView;
    }

}

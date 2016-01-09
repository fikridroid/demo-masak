package tuxdev.studio.demokutaibarat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ukietux on 09/01/16.
 */
public class FragmentLaporanAll extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<FeedItem> feedItems;
    SwipeRefreshLayout swipeRefreshLayout;
    View x;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        x = inflater.inflate(R.layout.fragment_laporan_all, container, false);

        //deklarasi listView
        listView = (ListView) x.findViewById(R.id.list);
        swipeRefreshLayout = (SwipeRefreshLayout) x.findViewById(R.id.swipe_refresh_layout);

        feedItems = new ArrayList<FeedItem>();

        listAdapter = new FeedListAdapter(getActivity(), feedItems);
        listView.setAdapter(listAdapter);


        getAll();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()

                                                {
                                                    @Override
                                                    public void onRefresh() {
                                                        // Your code to refresh the list here.
                                                        // Make sure you call swipeContainer.setRefreshing(false)
                                                        // once the network request has completed successfully.
                                                        getAll();
                                                    }
                                                }

        );
        swipeRefreshLayout.post(new

                                        Runnable() {
                                            @Override
                                            public void run() {
                                                swipeRefreshLayout.setRefreshing(true);
                                                getAll();
                                            }
                                        }

        );

        return x;
    }


    public void getAll() {
        feedItems.clear();
        for (int a = 1; a < 6; a++) {
            FeedItem item = new FeedItem();

            String gbr = "http://3.bp.blogspot.com/-8xYp3OmaVio/UO_-o4HBmoI/AAAAAAAAE3Y/jXI9dLjXvaY/s1600/Logo+Kabupaten+Kutai+Barat.jpg";

            item.setId(a);
            item.setNama("DEMO APLIKASI "+String.valueOf(a));
            item.setGambar(gbr);
            item.setKomentar("Komentar "+String.valueOf(a));
            item.setWaktu("2016-01-09 19:0" + String.valueOf(a) + ":30");
            feedItems.add(item);
        }
        // notify data changes to list adapater
        listAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onRefresh() {
        getAll();
    }
}
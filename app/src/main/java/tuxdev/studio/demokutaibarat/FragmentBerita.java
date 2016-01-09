package tuxdev.studio.demokutaibarat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ukietux on 09/01/16.
 */
public class FragmentBerita extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView listView;
    private CustomListAdapter listAdapter;
    private List<DetailItem> detailItems;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<HashMap<String, String>> list_data;

    View x;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        x = inflater.inflate(R.layout.fragment_berita, container, false);

        //deklarasi listView
        listView = (ListView) x.findViewById(R.id.listberita);
        swipeRefreshLayout = (SwipeRefreshLayout) x.findViewById(R.id.swipe_refresh_layout);

        detailItems = new ArrayList<DetailItem>();

        listAdapter = new CustomListAdapter(getActivity(), detailItems);
        listView.setAdapter(listAdapter);
        getAll();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences prefpersen = getActivity().getSharedPreferences("Berita",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor mEditorPersen = prefpersen.edit();
                mEditorPersen.putString("judul", list_data.get(position).get("judul"));
                mEditorPersen.putString("gambar", list_data.get(position).get("gambar"));
                mEditorPersen.putString("tahun", list_data.get(position).get("tahun"));
                mEditorPersen.putString("genre", list_data.get(position).get("genre"));
                mEditorPersen.putString("deskripsi", list_data.get(position).get("deskripsi"));
                mEditorPersen.commit();

                Intent i = new Intent(getActivity(), ActivityDetailBerita.class);
                startActivity(i);
            }
        });

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
        detailItems.clear();
        list_data = new ArrayList<HashMap<String, String>>();
        for (int a = 1; a < 6; a++) {
            DetailItem item = new DetailItem();

            String gbr = "http://images1.rri.co.id/thumbs/berita_155013_800x600_ABED_NEGO.jpg";


            item.setTitle("Berita " + String.valueOf(a));
            item.setThumbnailUrl(gbr);
            item.setYear("201" + String.valueOf(a));
            item.setGenre("politik");


            // Create HashMap to store row values
            HashMap<String, String> recordData =
                    new HashMap<String, String>();

            recordData.put("judul", "Berita " + String.valueOf(a));
            recordData.put("gambar", gbr);
            recordData.put("tahun", "201" + String.valueOf(a));
            recordData.put("genre", "Politik");
            recordData.put("deskripsi", "KBRN, Sendawar : Memasuki masa pensiunya sejak pertengahan tahun 2014 lalu sebagai Pegawai Negeri Sipil (PNS), kini Abed Nego Siap bersaing dalam Perhelatan Pemilihan Kepala Daerah (Pilkada) Kabupaten Kutai Barat bulan Desember 2015 mendatang. Meski sempat mendaftar ke beberapa partai politik, namun demi mempermudah pencalonan dirinya, ia juga memilih melalui Jalur Independen. ");
            list_data.add(recordData);


            detailItems.add(item);
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
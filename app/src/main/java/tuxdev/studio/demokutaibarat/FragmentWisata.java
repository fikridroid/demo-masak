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
public class FragmentWisata extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView listView;
    private CustomListAdapter listAdapter;
    private List<DetailItem> detailItems;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<HashMap<String, String>> list_data;

    View x;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        x = inflater.inflate(R.layout.fragment_wisata, container, false);

        //deklarasi listView
        listView = (ListView) x.findViewById(R.id.listwisata);
        swipeRefreshLayout = (SwipeRefreshLayout) x.findViewById(R.id.swipe_refresh_layout);

        detailItems = new ArrayList<DetailItem>();

        listAdapter = new CustomListAdapter(getActivity(), detailItems);
        listView.setAdapter(listAdapter);
        getAll();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences prefpersen = getActivity().getSharedPreferences("Wisata",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor mEditorPersen = prefpersen.edit();
                mEditorPersen.putString("judul", list_data.get(position).get("judul"));
                mEditorPersen.putString("gambar", list_data.get(position).get("gambar"));
                mEditorPersen.putString("tahun", list_data.get(position).get("tahun"));
                mEditorPersen.putString("genre", list_data.get(position).get("genre"));
                mEditorPersen.putString("deskripsi", list_data.get(position).get("deskripsi"));
                mEditorPersen.commit();

                Intent i = new Intent(getActivity(), ActivityDetailWisata.class);
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

            String gbr = "http://assets.kompas.com/data/photo/2013/12/03/1530550rasaaa780x390.jpg";


            item.setTitle("Berita Wisata " + String.valueOf(a));
            item.setThumbnailUrl(gbr);
            item.setYear("201" + String.valueOf(a));
            item.setGenre("kuliner");


            // Create HashMap to store row values
            HashMap<String, String> recordData =
                    new HashMap<String, String>();

            recordData.put("judul", "Wisata " + String.valueOf(a));
            recordData.put("gambar", gbr);
            recordData.put("tahun", "201" + String.valueOf(a));
            recordData.put("genre", "Wista Kuliner");
            recordData.put("deskripsi", "Belum lagi ikan pepes daun empruk, pumni (sejenis sayur labu, umbut rotan, dan ikan baung), buras (lontong beras ditambah ikan gabus berkuah). Aroma pedas menguar dari sambal lumatan jaung (kecombrang), umbut rotan (pokok batang rotan muda), berbalur dengan wangi bawang merah, bawang rambut, cabai, dan ikan salai. Semua menebarkan aroma khas kecombrang dan cabai, menggugah rasa lapar di tengah keriuhan dalam lamin itu.");
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


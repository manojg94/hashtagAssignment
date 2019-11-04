package com.manoj.hashtagassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.manoj.hashtagassignment.api.Adapter.recyclerModel;
import com.manoj.hashtagassignment.api.Adapter.recyclerViewAdapter;
import com.manoj.hashtagassignment.api.Adapter.type;
import com.manoj.hashtagassignment.api.api;
import com.manoj.hashtagassignment.api.pojo.datalist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public List<recyclerModel> recyclerModels=new ArrayList<>();
    public List<type> ttttt=new ArrayList<>();
    public recyclerViewAdapter adapter;
    RecyclerView recyclerView;
    Spinner type,number;
    String[] types={"Artist","Album"};
    ArrayList<String> noof = new ArrayList<String>();
    Integer values=1;


    Map<String, ArrayList<String>> namess = new LinkedHashMap<>();
    Set<String> ss = new HashSet<>();
    ArrayList<String> artist = new ArrayList<String>();
    ArrayList<String> artistnamess = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.my_recycler_view);
        type = findViewById(R.id.type);
        number = findViewById(R.id.count);
//Creating the ArrayAdapter instance having the employee list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, types);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(aa);

        type.setOnItemSelectedListener(this);
        number.setOnItemSelectedListener(this);

        adapter=new recyclerViewAdapter(MainActivity.this.artist,namess,values,getApplicationContext());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {

            case R.id.type:
                Log.d("spinnert",String.valueOf(i));
                Toast.makeText(getApplicationContext(), String.valueOf(types[i]), Toast.LENGTH_LONG).show();
                if (types[i].equals("Album")){
                    clear();
                    getData("Album");
                }else {
                    clear();
                    getData("Artist");
                }
                break;
            case R.id.count:
                Toast.makeText(getApplicationContext(), String.valueOf(noof.get(i)), Toast.LENGTH_LONG).show();
                values=Integer.parseInt(noof.get(i));
                adapter=new recyclerViewAdapter(MainActivity.this.artist,namess,values, getApplicationContext());
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(adapter);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    private void getData(final String datatype) {
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(api.baseurl)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        api retrfit = retro.create(api.class);
        Call<datalist> call;
        call = retrfit.getdata();


        call.enqueue(new Callback<datalist>() {
            @Override
            public void onResponse(Call<datalist> call, retrofit2.Response<datalist> response) {

                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        Toast.makeText(MainActivity.this, "SuccessFully ...", Toast.LENGTH_SHORT).show();
                        Log.d("results",response.body().getData().toString());

                        recyclerModel datalist=new recyclerModel(
                                response.body().getData().get(i).getName(),
                                response.body().getData().get(i).getArtist(),
                                response.body().getData().get(i).getAlbum()
                        );
                        recyclerModels.add(datalist);
                    }


                    if (datatype.equals("Album")){
                        DisplayAlbumData();
                    }else {
                        DisplayArtistData();
                    }


                } else {
                    Toast.makeText(MainActivity.this, "Failed ", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<datalist> call, Throwable t) {
                Log.d("errorin:",t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

    private void DisplayArtistData() {
        //adding into set
        for (int j=0;j<recyclerModels.size();j++){
            ss.add(recyclerModels.get(j).getArtist());
        }
        //reterving from set and adding into arraylist
        MainActivity.this.artist.addAll(ss);

        for ( int i=0;i<artist.size();i++){
            noof.add(String.valueOf(i+1));
        }
        //Creating the ArrayAdapter instance having the year list
        ArrayAdapter yy = new ArrayAdapter(this, android.R.layout.simple_spinner_item, noof);
        yy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        number.setAdapter(yy);

        //comparing artist arrylist and List<model>
        for (int i = 0; i< MainActivity.this.artist.size(); i++){
            for (int j=0;j<recyclerModels.size();j++){
                if (MainActivity.this.artist.get(i).contains(recyclerModels.get(j).getArtist())) {
                    artistnamess.add(recyclerModels.get(j).getName());
                    Log.d("jncedc", MainActivity.this.artist.get(i)+"::"+recyclerModels.get(j).getName());
                }
            }
            Log.d("ansd",artistnamess.toString());
            namess.put(MainActivity.this.artist.get(i), new ArrayList<String>(artistnamess));
            artistnamess.clear();
            Log.d("ansdnames",namess.toString());
        }

        Log.d("ansd",ss.toString());
        Log.d("ansd",String.valueOf(ss.size()));
        Log.d("jcfhk",String.valueOf(namess));
        for (int i=0;i<namess.size();i++){
            Log.d("gjgh",String.valueOf(namess.get(MainActivity.this.artist.get(i))));

        }


        adapter.notifyDataSetChanged();
    }
    public void clear(){
        noof.clear();
        recyclerModels.clear();
        ss.clear();
        artist.clear();
        artistnamess.clear();
        artistnamess.clear();
        namess.clear();
    }
    private void DisplayAlbumData() {
        //adding into set
        for (int j=0;j<recyclerModels.size();j++){
            ss.add(recyclerModels.get(j).getAlbum());
        }
        //reterving from set and adding into arraylist
        MainActivity.this.artist.addAll(ss);
        //assigning number to display number of albums
        for ( int i=0;i<artist.size();i++){
            noof.add(String.valueOf(i+1));
        }
        //Creating the ArrayAdapter instance having the year list
        ArrayAdapter yy = new ArrayAdapter(this, android.R.layout.simple_spinner_item, noof);
        yy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        number.setAdapter(yy);


        //comparing artist arrylist and List<model>
        for (int i = 0; i< MainActivity.this.artist.size(); i++){
            for (int j=0;j<recyclerModels.size();j++){
                if (MainActivity.this.artist.get(i).contains(recyclerModels.get(j).getAlbum())) {
                    artistnamess.add(recyclerModels.get(j).getName());
                    Log.d("jncedc", MainActivity.this.artist.get(i)+"::"+recyclerModels.get(j).getName());
                }
            }
            Log.d("ansd",artistnamess.toString());
            namess.put(MainActivity.this.artist.get(i), new ArrayList<String>(artistnamess));
            artistnamess.clear();
            Log.d("ansdnames",namess.toString());
        }

        Log.d("ansd",ss.toString());
        Log.d("ansd",String.valueOf(ss.size()));
        Log.d("jcfhk",String.valueOf(namess));
        for (int i=0;i<namess.size();i++){
            Log.d("gjgh",String.valueOf(namess.get(MainActivity.this.artist.get(i))));

        }


        adapter.notifyDataSetChanged();
    }
}

package com.example.pokeapi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends AppCompatActivity {

    private DataBaseHelper db;

    //List<Phone> phones = new ArrayList<>();
    static final String BASE_URL = "https://pokeapi.co/api/v2/";
    List<DataList> dataLists = new ArrayList<>();;
    RecyclerView recyclerView;
    Adapter adapter;
//    List<DataList> dataLists;
//    RecyclerView recyclerView;
//    Adapter adapter;
   // SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        db = CreateDataBase.getInstance().getDatabaseInstance();

//        db = CreateDataBase.getInstance().getDatabaseInstance();
//        DataBaseModel dataBaseModel = new DataBaseModel();
//        dataBaseModel.setName("dasdadassa");
//        db.getDataBaseDao().insert(dataBaseModel);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, Fragment_Recycler.newInstance()).commit();
        }

        //dataLists = new ArrayList<>();
//        recyclerView = findViewById(R.id.recycler);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new Adapter(getApplicationContext(),dataLists);
//        recyclerView.setAdapter(adapter);
        //adapter.addDate(ParsRetrofitForApi.date());
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create()).build();
//
//        ParserJSON parserJSON = retrofit.create(ParserJSON.class);
//
//
//        Call<Parser> call = parserJSON.PokemonParser();
//  //      try {
//
//            call.enqueue(new Callback<Parser>() {
//                @RequiresApi(api = Build.VERSION_CODES.N)
//                @Override
//                public void onResponse(Call<Parser> call, Response<Parser> response) {
//                    if (response.isSuccessful()) {
////                        db = CreateDataBase.getInstance().getDatabaseInstance();
////                        DataBaseModel dataBaseModel = new DataBaseModel();
//                        Parser parser = response.body();
//
//                        for (Pokemon pokemon : parser.getResults()) {
////                        list.add(new DataList(pokemon.getName(),pokemon.getUrl()));
//                            //dataLists.add(new DataList(pokemon.getName(), pokemon.getUrl()));
////                            dataBaseModel.setName(pokemon.getName());
//                        }
//                        Log.e("fasfsdfsdfsdfsd88", db.toString());
//
////                        db.getDataBaseDao().insert(dataBaseModel);
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<Parser> call, Throwable t) {
//                    Log.e("Error", t.toString());
//                }
//
//            });
//            Thread.sleep(20000);
//        }catch (Exception e){}

       // Log.e("fasfsdfsdfsdfsd99",dataLists.toString());
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create()).build();
//
//        ParserJSON parserJSON = retrofit.create(ParserJSON.class);
//
//
//        Call<Parser> call = parserJSON.PokemonParser();
//
//        call.enqueue(new Callback<Parser>() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onResponse(Call<Parser> call, Response<Parser> response) {
//                if (response.isSuccessful()) {
//                    Parser parser = response.body();
//
//
//                    for (Pokemon pokemon : parser.getResults()) {
//                        list.add(new DataList(pokemon.getName(),pokemon.getUrl()));
//                    }
//                    //Log.e("fasfsdfsdfsdfsd88",list.toString());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Parser> call, Throwable t) {
//                Log.e("Error",t.toString());
//            }
//
//        });

    }

}
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create()).build();
//
//        ParserJSON parserJSON = retrofit.create(ParserJSON.class);
//
//        Call<Parser> call = parserJSON.PokemonParser();
//        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
//        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, url TEXT)");
//
//        try {
//            make(call);
//            Thread.sleep(2000);
//        } catch (Exception e) { }
//
//        Toast.makeText(this, String.valueOf(db.query("users",null,null,null,null,null,null,null).getBlob(0).toString()), LENGTH_SHORT).show();
//
//    }
//
//    public void make(Call<Parser> call) {
//
//        call.enqueue(new Callback<Parser>() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onResponse(Call<Parser> call, Response<Parser> response) {
////                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
////                SharedPreferences.Editor editor = sharedPreferences.edit();
//                if (response.isSuccessful()) {
//                    Parser parser = response.body();
//                       // String k = "key";
//                        //int l = 0;
//                    for (Pokemon pokemon : parser.getResults()) {
//                        db.execSQL("INSERT INTO users VALUES ('pokemon.getName()', 'pokemon.getUrl()');");
//                        //editor.putString(k+l,pokemon.getName());
//                        // dataLists.add(new DataList(pokemon.getName()));
//                       // editor.putStringSet();
//                        //dataList.add(0,new DataList(pokemon.getName(),pokemon.getUrl()));
//                        //Log.e("name",pokemon.getName()+"      " + pokemon.getUrl());
//                    }
//                    //editor.apply();
//                    // Log.e("fasfsdfsdfsdfsd88",dataList.toString());
//                }
//                db.close();
//            }
//
//            @Override
//            public void onFailure(Call<Parser> call, Throwable t) {
//                Log.e("Error",t.toString());
//            }
//        });
//
//    }

//}

//        setInitialData();
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
//        // создаем адаптер
//        DataAdapter adapter = new DataAdapter(this, phones);
//        // устанавливаем для списка адаптер
//        recyclerView.setAdapter(adapter);
//
//        DataAdapter.OnPhoneClickListener onPhoneClickListener = new DataAdapter.OnPhoneClickListener() {
//            @Override
//            public void onItemClicked(Phone phone) {
//                Toast.makeText(MainActivity.this,phone.toString(), Toast.LENGTH_SHORT).show();
//            }
//        };
//
//    }
//
//    private void setInitialData() {
//        phones.add(new Phone ("Huawei P10", "Huawei", R.mipmap.ic_launcher));
//        phones.add(new Phone ("Elite z3", "HP", R.mipmap.ic_launcher));
//        phones.add(new Phone ("Galaxy S8", "Samsung", R.mipmap.ic_launcher));
//        phones.add(new Phone ("LG G 5", "LG", R.mipmap.ic_launcher));
//    }
//}

//class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
//
//    private OnPhoneClickListener onPhoneClickListener;
//    private LayoutInflater inflater;
//    private List<Phone> phones;
//
//    DataAdapter(Context context, List<Phone> phones) {
//        this.phones = phones;
//        this.inflater = LayoutInflater.from(context);
////        DataAdapter(OnPhoneClickListener onPhoneClickListener) {
////        this.onPhoneClickListener = onPhoneClickListener;
//    }
//
//    DataAdapter(OnPhoneClickListener onPhoneClickListener){
//        this.onPhoneClickListener=onPhoneClickListener;
//    }
//
//    @Override
//    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.list_item, parent, false);
//        return  new ViewHolder(view);
//    }
//
//    public interface OnPhoneClickListener{
//        void onItemClicked(Phone phone);
//    }
//
//    @Override
//    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
//        Phone phone = phones.get(position);
//        holder.imageView.setImageResource(phone.getImage());
//        holder.nameView.setText(phone.getName());
//        holder.companyView.setText(phone.getCompany());
//    }
//
//    @Override
//    public int getItemCount() {
//        return phones.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        final ImageView imageView;
//        final TextView nameView, companyView;
//        ViewHolder(View view){
//            super(view);
//            imageView = (ImageView)view.findViewById(R.id.image);
//            nameView = (TextView) view.findViewById(R.id.name);
//            companyView = (TextView) view.findViewById(R.id.company);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Phone phone = phones.get(getLayoutPosition());
//                    onPhoneClickListener.onItemClicked(phone);
//                }
//            });
//        }
//    }
//}

//        RecyclerView rv = (RecyclerView)findViewById(R.id.recycler_view);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        rv.setLayoutManager(llm);
//
//         List<Person> persons;
//
//            persons = new ArrayList<>();
//            persons.add(new Person("Emma Wilson", "23 years old", R.mipmap.ic_launcher));
//            persons.add(new Person("Lavery Maiss", "25 years old", R.mipmap.ic_launcher));
//            persons.add(new Person("Lillie Watts", "35 years old", R.mipmap.ic_launcher));
//
//        RVAdapter adapter = new RVAdapter(persons);
//        rv.setAdapter(adapter);
//
//        rv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("vvv",String.valueOf(v));
//            }
//        });


//    }
//}

//class Person {
//    String name;
//    String age;
//    int photoId;
//    Person(String name, String age, int photoId) {
//        this.name = name;
//        this.age = age;
//        this.photoId = photoId;
//    }
//}
//
//class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {
//    List<Person> persons;
//    RVAdapter(List<Person> persons){
//        this.persons = persons;
//    }
//    @NonNull
//    @Override
//    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
//        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
//        PersonViewHolder pvh = new PersonViewHolder(v);
//        return pvh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int i) {
//        personViewHolder.personName.setText(persons.get(i).name);
//        personViewHolder.personAge.setText(persons.get(i).age);
//        personViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
//
//    }
//
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//    }
//
//    @Override
//    public int getItemCount() {
//        return persons.size();
//    }
//
//    public static class PersonViewHolder extends RecyclerView.ViewHolder {
//        CardView cv;
//        TextView personName;
//        TextView personAge;
//        ImageView personPhoto;
//        PersonViewHolder(View itemView) {
//            super(itemView);
//            cv = (CardView)itemView.findViewById(R.id.cv);
//            personName = (TextView)itemView.findViewById(R.id.person_name);
//            personAge = (TextView)itemView.findViewById(R.id.person_age);
//            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
//        }
//    }
//}
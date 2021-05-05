package com.example.quizapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.adapter.QuizAdapter;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.utils.FireBaseAuthantication;
import com.firebase.client.Firebase;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    Toolbar topAppBar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    RecyclerView.LayoutManager layoutManager;

    RecyclerView recyclerView;
    QuizAdapter quizAdapter;
    NavigationView navigationMenu;

    FirebaseFirestore firestore;

    public List<Quiz> quizList  = new ArrayList<Quiz>();
    private Quiz Quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topAppBar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.mainDrawer);
        firebaseAuth = firebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recycleView);
        firestore  = FirebaseFirestore.getInstance();
        navigationMenu = findViewById (R.id.navigationMenu);
        setUpFireStore();

        setSupportActionBar(topAppBar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name);
        actionBarDrawerToggle.syncState();


//        quizList.add(new Quiz("12/10/2021","12/10/2021"));
//        quizList.add(new Quiz("12/10/2022","12/10/2022"));
//        quizList.add(new Quiz("12/10/2023","12/10/2023"));
//        quizList.add(new Quiz("12/10/2024","12/10/2024"));
//        quizList.add(new Quiz("12/10/2025","12/10/2025"));
//        quizList.add(new Quiz("12/10/2026","12/10/2026"));
//        quizList.add(new Quiz("12/10/2027","12/10/2027"));
//        quizList.add(new Quiz("12/10/2028","12/10/2028"));


        quizAdapter  = new QuizAdapter(quizList,MainActivity.this);
        layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(quizAdapter);
        navigationMenu.setNavigationItemSelectedListener (new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                sideMenuItem(item);
                Toast.makeText (getApplicationContext (),item.getTitle ().toString (),Toast.LENGTH_SHORT).show ();
                return false;
            }
        });

    }

    private void sideMenuItem(MenuItem item) {
//        if(firebaseAuth.getCurrentUser ()!=null){
            if(item.getTitle ().toString ().equalsIgnoreCase ("logout")){
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);

                startActivity (intent);
                firebaseAuth.signOut ();
                finish ();

            }
//        }


    }

    private void setUpFireStore() {
        CollectionReference collectionReference = firestore.collection("Quiz");

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
           if(value==null||error!=null) {
               Toast.makeText(getApplicationContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
               return;
           }

           Log.d("DATA", value.toObjects(com.example.quizapp.model.Quiz.class).toString());
           quizList.clear();
           quizList.addAll(value.toObjects(com.example.quizapp.model.Quiz.class));
           quizAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        if(currentUser != null){
//            FireBaseAuthantication authantication = new FireBaseAuthantication(MainActivity.this);
//            authantication.reload();
//        }
//    }

}
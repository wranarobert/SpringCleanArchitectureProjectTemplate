package ca.project.presentation.android;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import an.awesome.pipelinr.Pipeline;
import dagger.android.support.DaggerAppCompatActivity;
import ca.project.domain.entities.User;
import ca.project.domain.exceptions.validation.ValidationException;
import ca.project.service.user.CreateUser.CreateUserRequest;

public class MainActivity extends DaggerAppCompatActivity {
    private static final String TAG = "MainActivity";

    @Inject
    Pipeline pipeline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    User user = (new CreateUserRequest("fdhgsfg").execute(pipeline)).get();

                    Log.v("AppMod", " user = " + user.getUsername());
                }catch(Exception e){
                    Log.v("AppMod", ((ValidationException)e).getMessage());
                }

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

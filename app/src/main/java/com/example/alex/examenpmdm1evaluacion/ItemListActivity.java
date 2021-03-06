package com.example.alex.examenpmdm1evaluacion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ItemListActivity extends AppCompatActivity
        implements ItemListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_app_bar);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ItemListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.item_list))
                    .setActivateOnItemClick(true);
            
        }

    }

    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {

            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();


        } else {

            Intent detailIntent = new Intent(this, ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
            //mandamos con el forResult el intent y el numero que sera luego lo que recibe el requestCode
            startActivityForResult(detailIntent, 1);
        }
        //Si la variable dual panel es true es ke esta en land y sacamos la toast
        if (getResources().getBoolean(R.bool.dual_panel)==true){

            Toast.makeText(getBaseContext(), "Tumbado", Toast.LENGTH_SHORT).show();

        }
        //si es false esta en portrait y sacomos la toast
        else if (getResources().getBoolean(R.bool.dual_panel)==false){

            Toast.makeText(getBaseContext(), "Portrait", Toast.LENGTH_SHORT).show();
        }
    }

    //Creamos el metodo onActivityResult por si queremos recibir cosas desde el intent
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //si recibe un requestCode de 1 del intent y un activity.resultOk tambien del intent muestra una toast que dice Activity Cerrada
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                Toast.makeText(getBaseContext(), "Activity Cerrada", Toast.LENGTH_SHORT).show();

            }
        }
    }
}

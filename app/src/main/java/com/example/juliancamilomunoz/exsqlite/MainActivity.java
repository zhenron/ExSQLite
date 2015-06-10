package com.example.juliancamilomunoz.exsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private Cursor cursor;
    private ListView lista;
    private SimpleCursorAdapter adapter;
    private EditText tv;
    private ImageButton bt;
    private DataBaseManager manager;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageButton){
            tv.getText().toString();
            Cursor c = manager.buscarContacto(tv.getText().toString());
            adapter.changeCursor(c);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper helper = new DbHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

       manager = new DataBaseManager(this);

//        DataBaseManager manager = new DataBaseManager(this);
/*        manager.insertar("Xingke","123445434");
        manager.insertar("German","34533244");
        manager.insertar("Rob","2345234234");
        manager.insertar("Hubble","23563234");*/

/*        manager.eliminar_nombre("Rob");
        manager.eliminar_id("2");
        manager.modificar_telefono("Xingke","9999999");*/

        lista = (ListView) findViewById(R.id.lista);
        String[] from = new String[] {manager.CN_NAME,manager.CN_PHONE};
        int[] to = new int[] {android.R.id.text1,android.R.id.text2};
        cursor = manager.cargarCursor();

        adapter = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,from,to,0);
        lista.setAdapter(adapter);

        tv = (EditText) findViewById(R.id.editText);
        bt = (ImageButton) findViewById(R.id.imageButton);
        bt.setOnClickListener(this);
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

package gr.hua.dit.android.contactscontentproviderclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String AUTHORITY = "gr.hua.dit.android.contactscontentprovider";
    public static final String CONTENT_URI = "content://"+AUTHORITY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentResolver resolver = this.getContentResolver();

        findViewById(R.id.insertButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString();
                String phone = ((EditText)findViewById(R.id.editTextTextPhoneNumber)).getText().toString();
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("phone",phone);
                Uri result = resolver.insert(Uri.parse(CONTENT_URI+"/contacts"),values);
                Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.selectButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = resolver.query(Uri.parse(CONTENT_URI+"/contacts"),null,null,null,null);
                if (cursor.moveToFirst()){
                    do{
                        Toast.makeText(MainActivity.this, cursor.getString(1), Toast.LENGTH_SHORT).show();
                    }while (cursor.moveToNext());
                }
            }
        });
    }
}
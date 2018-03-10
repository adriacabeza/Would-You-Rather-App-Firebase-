package me.adriacabeza.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by usuario on 30/01/2018.
 */

public class GameEditActivity  extends AppCompatActivity {
    Button button;
    EditText texto1;
    EditText texto2;
   int size=-1;
    private DatabaseReference DBref;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.game_edit);
        DBref=FirebaseDatabase.getInstance().getReference();

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              texto1  = (EditText) findViewById(R.id.red2);
               texto2 = (EditText) findViewById(R.id.blue2);
               String red2 = texto1.getText().toString();
                String blue2  = texto2.getText().toString();

                Pregunta pregunta = new Pregunta(red2,blue2,0,0);
                int length = red2.length();
                int length2 = blue2.length();

                if (length == 0 && length2==0){
                    Toast toast = Toast.makeText (getApplicationContext(), "¡Te falta escribir las opciones!", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if (length2==0){
                    Toast toast = Toast.makeText (getApplicationContext(), "¡Te falta escribir una opción!", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if (length==0){
                    Toast toast = Toast.makeText (getApplicationContext(), "¡Te falta escribir una opción!", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else{
                    DBref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            size =(int) dataSnapshot.getChildrenCount();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                      if(size!= -1)  {
                      DBref.child("pregunta"+size).setValue(pregunta);
                        Toast toast = Toast.makeText (getApplicationContext(), "Enviado", Toast.LENGTH_SHORT);
                        toast.show();
                        texto1.setText("");
                        texto2.setText("");
                    }
                }
            }
        });}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_2, R.anim.slide_3);
    }
}
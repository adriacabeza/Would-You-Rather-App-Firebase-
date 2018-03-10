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
    String mCurrentPhotoPath;
    Button button;
    EditText texto1;
    EditText texto2;
   int size=-1;
    private DatabaseReference DBref;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
       /* mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDialog();
            }

        });
    }




    private void startDialog() {
        final CharSequence[] items = { "Take Photo", "Choose from Gallery",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    dispatchTakePictureIntent();

                } else if (items[item].equals("Choose from Gallery")) {
                    loadGallery();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }



    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix
                ".jpg",         /* suffix
                storageDir      /* directory
        );

        // Save a file: path for use with ACTION_VIEW intents

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_PICK_IMAGE = 2 ;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                return; }
            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this, "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void loadGallery(){
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");
        Intent pickIntent= new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
        startActivityForResult(pickIntent, REQUEST_PICK_IMAGE);
    }




    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Glide.with(this).load(new File(mCurrentPhotoPath)).into(mImageView);
        }
        else if(requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK ){
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                mImageView.setImageBitmap(bitmap);

                mCurrentPhotoPath =getRealPathFromURI(data.getData());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }*/
}
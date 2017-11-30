package com.example.layout.image;
//Package objects contain version information about the implementation and specification of a Java package

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
//here i have created main class
//public keyword is used in the declaration of a class,method or field;public classes,method and fields can be accessed by the members of any class.
//extends is for extending a class. implements is for implementing an interface
//AppCompatActivity is a parent class

    ImageView image;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

//Variables, methods, and constructors, which are declared protected in a superclass can be accessed only by the subclasses
        // in other package or any class within the package of the protected members class.
        //void is a Java keyword.  Used at method declaration and definition to specify that the method does not return any type,
        // the method returns void.
        //onCreate Called when the activity is first created. This is where you should do all of your normal static set up: create views,
        // bind data to lists, etc. This method also provides you with a Bundle containing the activity's previously frozen state,
        // if there was one.Always followed by onStart().
        //Bundle is most often used for passing data through various Activities.
// This callback is called only when there is a saved instance previously saved using onSaveInstanceState().
// We restore some state in onCreate() while we can optionally restore other state here, possibly usable after onStart() has
// completed.The savedInstanceState Bundle is same as the one used in onCreate().

        super.onCreate(savedInstanceState);
        //// call the super class onCreate to complete the creation of activity like the view hierarchy
        setContentView(R.layout.activity_main);

        //R means Resource
        //layout means design
        //  main is the xml you have created under res->layout->main.xml
        //  Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        // The other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        // the design
        image=(ImageView)findViewById(R.id.imageView);
        imageView=(ImageView)findViewById(R.id.imageView);
        //creating the object of image view
    }
    public void Click(View view)
            //This will be invoked when the click method is used
    {
        //The intent itself, an Intent object, is a passive data structure holding an abstract description of an operation to be
        // performed.
        //Display information about the person

        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_PICK);
        //setAction to perforn the action
        //ACTION_PICK: Pick an item from the data, returning what was selected.
        intent.setType("image/*");
        //setType is for setting the format we want
        startActivityForResult(intent,100);
        // startActivityForResult:Starting another activity doesn't have to be one-way. You can also start another activity and
        // receive a result back
        //Activity is started with requestCode 100
    }

    @Override
    //// Call Back method  to get the Message form other Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 100
       // onActivityResult method that is invoked automatically when second activity returns result.
        if(requestCode==100 && resultCode==RESULT_OK)
        {
            //Uri  Uniform Resource Identifier
            //gets the data using uri
            Uri uri= data.getData();
            try {
                //InputStream:This abstract class is the superclass of all classes representing an input stream of bytes.
                //Android provides Bitmap class to handle images. This can be found under android.graphics.bitmap. There are many ways through which you can instantiate bitmap. We are creating a bitmap of image from the imageView.
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap map= BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(map);
            } catch (FileNotFoundException e) {
                //if image was not found
                e.printStackTrace();
            }
        }
        else
        {

        }
    }
}

package com.example.imagedownload;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    public void downloadImage(View view)
    {
        DownloadTask downloadTask = new DownloadTask();
        try {
            Bitmap bitmapImage = downloadTask.execute("https://upload.wikimedia.org/wikimedia/en/a/aa/Bart_Simpson_200px.png").get();
            imageView.setImageBitmap(bitmapImage);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("TAG","Button Clicked");
    }

    public class DownloadTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url ;
            HttpURLConnection urlConnection;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Bitmap bitmap = downloadTask.execute("https://www.google.com/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fen%2Fa%2Faa%2FBart_Simpson_200px.png&imgrefurl=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FBart_Simpson&docid=vFAi7OjJ2lytKM&tbnid=NOBTlKyLlEJevM%3A&vet=10ahUKEwj3-6L80sXmAhUiIzQIHY1IBUQQMwh3KAAwAA..i&w=200&h=298&itg=1&bih=751&biw=1536&q=bart%20simpson&ved=0ahUKEwj3-6L80sXmAhUiIzQIHY1IBUQQMwh3KAAwAA&iact=mrc&uact=8");
       // Button button = findViewById(R.id.downloadButton);
        imageView = findViewById(R.id.imageBox);
//        imageView.setImageBitmap(bitmap);
    }
}

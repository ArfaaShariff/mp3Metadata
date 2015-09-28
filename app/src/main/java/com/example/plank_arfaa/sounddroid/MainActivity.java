package com.example.plank_arfaa.sounddroid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    public static VideoView mVideoView_vw;
    private static String vidAddress;
    private Uri vidUri;
    private Boolean firstLooking = true;



    ImageView album_art;
    TextView album, artist, genre;
    String viewSource="sdcard/Download/A.mp3";

    MediaMetadataRetriever metaRetriever;
    byte[] art;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getInit();



      

        // Album_art retrieval code //

        metaRetriever = new MediaMetadataRetriever();
        metaRetriever.setDataSource(viewSource);
        try {
            art = metaRetriever.getEmbeddedPicture();
            Bitmap songImage = BitmapFactory
                    .decodeByteArray(art, 0, art.length);
            album_art.setImageBitmap(songImage);
            album.setText(metaRetriever
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));
            artist.setText(metaRetriever
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
            genre.setText(metaRetriever
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE));
        } catch (Exception e) {
            album_art.setBackgroundColor(Color.GRAY);
            album.setText("Unknown Album");
            artist.setText("Unknown Artist");
            genre.setText("Unknown Genre");
        }

    }

    // Fetch Id's form xml

    public void getInit() {

        album_art = (ImageView) findViewById(R.id.album_art);
        album = (TextView) findViewById(R.id.Album);
        artist = (TextView) findViewById(R.id.artist_name);
        genre = (TextView) findViewById(R.id.genre);

    }
}
package harsha.github.imageaveraging;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Bitmap dest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final  ImageView imageView = findViewById(R.id.imageView);
       final Bitmap first = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        Bitmap second = BitmapFactory.decodeResource(getResources(), R.drawable.cat2);
        dest = Bitmap.createBitmap(first.getWidth(), first.getHeight(), Bitmap.Config.ARGB_8888);
        int max = 0;
        Log.e("Notice", "Started"+dest.getWidth());
        for (int i = 0; i < dest.getWidth(); i++) {
            for (int j = 0; j < dest.getHeight(); j++) {
                int k = first.getPixel(i, j);
                int l = second.getPixel(i, j);
                int red = (Color.red(k) + Color.red(l)) / 2;
                int green = (Color.green(k) + Color.green(l)) / 2;
                int blue = (Color.blue(k) + Color.blue(l)) / 2;

                int m = Color.argb(255, red, green, blue);
                if (red > max) max = red;//image is greyscale, red=green=blue for the entire image
                dest.setPixel(i, j, m);
            }
        }
        imageView.setImageBitmap(dest);
        Log.e("Notice", "Finished");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final float scale=max/255;
        Button but = findViewById(R.id.button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "After normalization", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < dest.getWidth(); i++) {
                    for (int j = 0; j < dest.getHeight(); j++) {
                        int k = first.getPixel(i, j);
                        int red= (int) (Color.red(k)*scale);

                        int blue= (int) (Color.blue(k)*scale);
                        int green= (int)(Color.green(k)*scale);
                        int m = Color.argb(255, red, green, blue);
                        dest.setPixel(i,j,m);

                    }

                }
                Log.e("Normalized", "Notice");
                imageView.setImageBitmap(dest);
            }
        });

    }
}

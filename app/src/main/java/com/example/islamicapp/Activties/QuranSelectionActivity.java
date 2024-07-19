package com.example.islamicapp.Activties;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.islamicapp.R;

public class QuranSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quran_selection);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//             v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        // Load the drawable resource
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background4);

        // Create a bitmap with rounded top corners
        Bitmap roundedBitmap = getRoundedCornerBitmap(bitmap, 230); // Adjust the radius as needed

        // Set the rounded bitmap as the background of the LinearLayout
        LinearLayout linearLayout = findViewById(R.id.linearLayout2);
        linearLayout.setBackground(new BitmapDrawable(getResources(), roundedBitmap));


        CardView readBtn = findViewById(R.id.readQuranBtn);
        CardView listenBtn = findViewById(R.id.readListenQuranBtn);

        readBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuranActivity.class);
            intent.putExtra("check",true);
            startActivity(intent);
        });

        listenBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuranActivity.class);
            intent.putExtra("check",false);
            startActivity(intent);
        });
    }
    private Bitmap getRoundedCornerBitmap(Bitmap bitmap, int radius) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        Path path = new Path();
        RectF rect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        float[] radii = {radius, radius, radius, radius, 0, 0, 0, 0}; // top-left, top-right, bottom-right, bottom-left
        path.addRoundRect(rect, radii, Path.Direction.CW);

        canvas.drawPath(path, paint);
        return output;
    }
}
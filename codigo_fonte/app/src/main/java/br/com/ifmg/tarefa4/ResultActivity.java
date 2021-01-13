package br.com.ifmg.tarefa4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private int mean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Primeira Parte
        RatingBar ratingMean = findViewById(R.id.ratingMean);
        TextView txtComment = findViewById(R.id.txtComment);
        TextView txtRatingFinal = findViewById(R.id.txtRatingFinal);

        // Segunda Parte
        ProgressBar progressDelivery = findViewById(R.id.progressDelivery);
        TextView txtDelivery = findViewById(R.id.txtDelivery);

        ProgressBar progressFood = findViewById(R.id.progressFood);
        TextView txtFood = findViewById(R.id.txtFood);

        ProgressBar progressCorrect = findViewById(R.id.progressCorrect);
        TextView txtCorrect = findViewById(R.id.txtCorrect);

        Button btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(v -> finish());

        Bundle b = getIntent().getExtras();
        if (b != null) {
            RatingData ratingData = b.getParcelable(Keys.RATING_DATA);
            int ratingFood = ratingData.getRatingFood();
            int ratingDelivery = ratingData.getRatingDelivery();
            int ratingCorrect = ratingData.getRatingCorrect();
            String comment = ratingData.getComment();

            // Aplica o valor da média nas estrelas
            mean = (ratingCorrect + ratingDelivery + ratingFood) / 3;
            ratingMean.setRating(mean);

            // Aplica o valor do comentário
            txtComment.setText(comment);

            // Aplica o valor da média no texto de média final
            txtRatingFinal.setText(mean + "/" + 5);

            // Aplica os valores nos progressos em círculo
            progressDelivery.setProgress(ratingDelivery * 20);
            txtDelivery.setText(Integer.toString(ratingDelivery));

            progressFood.setProgress(ratingFood * 20);
            txtFood.setText(Integer.toString(ratingFood));

            progressCorrect.setProgress(ratingCorrect * 20);
            txtCorrect.setText(Integer.toString(ratingCorrect));
        } else {
            Toast.makeText(this, "Não foi possível buscar os dados da activity anterior!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra(Keys.RATING_MEAN, mean);
        setResult(RESULT_OK, data);

        super.finish();
    }
}
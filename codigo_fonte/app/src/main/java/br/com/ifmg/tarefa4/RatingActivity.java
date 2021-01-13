package br.com.ifmg.tarefa4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RatingActivity extends AppCompatActivity {

    private AlertDialog.Builder builder;

    public static final int RATING_SUCCESS = 9850;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        RatingBar ratingFood = findViewById(R.id.ratingFood);
        RatingBar ratingDelivery = findViewById(R.id.ratingDelivery);
        RatingBar ratingCorrect = findViewById(R.id.ratingCorrect);
        EditText editComment = findViewById(R.id.editComment);

        builder = new AlertDialog.Builder(this);
        builder.setCancelable(true).setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

        Button btnLogin = findViewById(R.id.btnSend);
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, ResultActivity.class);
            RatingData ratingData = new RatingData();
            ratingData.setRatingFood((int) ratingFood.getRating());
            ratingData.setRatingDelivery((int) ratingDelivery.getRating());
            ratingData.setRatingCorrect((int) ratingCorrect.getRating());
            ratingData.setComment(editComment.getText().toString());
            intent.putExtra(Keys.RATING_DATA, ratingData);
            startActivityForResult(intent, RATING_SUCCESS);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == RATING_SUCCESS) {
            if (data != null && data.hasExtra(Keys.RATING_MEAN)) {

                int mean = data.getIntExtra(Keys.RATING_MEAN, -1);

                // Não conseguiu buscar os dados
                if (mean <= 0 ) {
                    builder.setTitle("Erro!");
                    builder.setMessage("Não foi possível registrar sua avaliação.\nTente novamente mais tarde...");
                }

                // Ruim (1 a 2 estrelas)
                if (mean > 0 && mean < 3) {
                    builder.setTitle("Avaliação: RUIM");
                    builder.setMessage("Sentimos muito que não tenha gostado!\nFaz assim... Toma aqui um cupom de desconto de 15% para a sua próxima compra ;)");
                }

                // Regular (3 estrelas)
                if (mean == 3) {
                    builder.setTitle("Avaliação: Regular");
                    builder.setMessage("Esperamos fazer ainda melhor da próxima vez!");
                }

                // Ótima (4 e 5 estrelas)
                if (mean > 3) {
                    builder.setTitle("Avaliação: Ótima");
                    builder.setMessage("Excelente! Esperamos sempre manter um bom serviço!");
                }

            } else {
                builder.setTitle("Erro!");
                builder.setMessage("Não foi possível registrar sua avaliação.\nTente novamente mais tarde...");
            }
            builder.create().show();
        }
    }
}
package com.example.demoretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoretrofit.api.ApiService;
import com.example.demoretrofit.model.Product;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvTerm;
    private TextView tvPrivicy;
    private TextView tvSourve;
    private TextView tvQuote;
    private Button btnCallAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPrivicy = findViewById(R.id.tv_privicy);
        tvTerm = findViewById(R.id.tv_term);
        tvSourve = findViewById(R.id.tv_source);
        tvQuote = findViewById(R.id.tv_quote);
        btnCallAPI = findViewById(R.id.btn_callapi);

        btnCallAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCallAPI();
            }
        });
    }

    private void clickCallAPI() {
        //    http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
        Map<String, String> options = new HashMap<>();
        options.put("access_key", "843d4d34ae72b3882e3db642c51e28e6");
        options.put("currencies", "VND");
        options.put("source", "USD");
        options.put("format", "1");

        ApiService.API_SERVICE.callProducts3(options)
                .enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        Toast.makeText(MainActivity.this, "Call API success", Toast.LENGTH_SHORT).show();

                        Product product = response.body();
                        if(product != null && product.isSuccess()){
                            tvTerm.setText(product.getTerms());
                            tvPrivicy.setText(product.getPrivacy());
                            tvQuote.setText(String.valueOf(product.getQuotes().getUsdVnd()));
                            tvSourve.setText(product.getSource());
                        }
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Call API error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
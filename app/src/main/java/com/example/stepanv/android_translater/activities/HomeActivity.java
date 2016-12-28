package com.example.stepanv.android_translater.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.stepanv.android_translater.R;
import com.example.stepanv.android_translater.dictionaryShema.Cit;
import com.example.stepanv.android_translater.dictionaryShema.Entry;
import com.example.stepanv.android_translater.dictionaryShema.OfflineTranslator;
import com.example.stepanv.android_translater.dictionaryShema.Sense;
import com.example.stepanv.android_translater.rest.YandexTranslatorApiService;
import com.example.stepanv.android_translater.rest.ServiceFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.enterText)
    EditText enterTextField;

    @BindView(R.id.translation)
    TextView translationField;

    @BindView(R.id.connection)
    TextView connectionField;

    private String LOG_TAG = "HomeActivity";
    private YandexTranslatorApiService onlineTranslator;
    private OfflineTranslator offlineTranslator;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initApiService();

        enterTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() >= 1) {
                    subscriptions.add(
                            onlineTranslator.getTranslation(YandexTranslatorApiService.LANGUAGE, YandexTranslatorApiService.API_KEY, charSequence.toString())
                                    .subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            translation -> {
                                                translationField.setText(translation.getText().get(0));
                                                connectionField.setText("Online");
                                            },
                                            throwable -> {
                                                Log.e(LOG_TAG, throwable.getMessage(), throwable);
                                                connectionField.setText("Offline");
                                                initOfflineTranslator();
                                                searchOfflineFor(charSequence.toString().toLowerCase());
                                            }
                                    )
                    );
                } else {
                    translationField.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void searchOfflineFor(String searchSequence) {
        translationField.setText(getDefinition(searchSequence));
    }

    private String getDefinition(String searchSequence) {
        for (Entry entry : offlineTranslator.getBody().getEntry()) {
            if (entry.getForm().getOrth().toLowerCase().equals(searchSequence)) {
                return getComplexTranslation(entry);
            }
        }
        return "No translation found";
    }

    private String getComplexTranslation(Entry entry) {
        String translation = "";
        for (Sense sense : entry.getSense()) {
            for (Cit cit : sense.getCit()) {
                translation += cit.getQuote() + "\n";
            }
        }
        return translation;
    }

    private void initOfflineTranslator() {
        if (offlineTranslator == null) {
            InputStream rawDictionary = getResources().openRawResource(R.raw.defs);
            try {
                final ObjectMapper mapper = new ObjectMapper()
                        .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                offlineTranslator = mapper.readValue(rawDictionary,
                        new TypeReference<OfflineTranslator>() {
                        });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initApiService() {
        onlineTranslator = ServiceFactory.createRetrofitService(YandexTranslatorApiService.class, YandexTranslatorApiService.ENDPOINT);
    }

    @Override
    protected void onDestroy() {
        subscriptions.unsubscribe();
        super.onDestroy();
    }

}

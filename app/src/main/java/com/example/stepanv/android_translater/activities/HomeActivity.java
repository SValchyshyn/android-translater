package com.example.stepanv.android_translater.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.stepanv.android_translater.R;
import com.example.stepanv.android_translater.rest.NewsApiService;
import com.example.stepanv.android_translater.rest.ServiceFactory;

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

  private String LOG_TAG = "HomeActivity";
  private NewsApiService onlineTranslator;
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
        if (charSequence.length() >= 1)
          subscriptions.add(
              onlineTranslator.getTranslation(NewsApiService.LANGUAGE, NewsApiService.API_KEY, charSequence.toString())
                  .subscribeOn(Schedulers.newThread())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(
                      translation -> {
                        translationField.setText(translation.getText().get(0));
                      },
                      throwable -> {
                        Log.e(LOG_TAG, throwable.getMessage(), throwable);
                      }
                  )
          );
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });
  }

  private void initApiService() {
    onlineTranslator = ServiceFactory.createRetrofitService(NewsApiService.class, NewsApiService.ENDPOINT);
  }

  @Override
  protected void onDestroy() {
    subscriptions.unsubscribe();
    super.onDestroy();
  }

}

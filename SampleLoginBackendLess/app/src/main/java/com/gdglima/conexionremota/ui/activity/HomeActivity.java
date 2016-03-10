package com.gdglima.conexionremota.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.gdglima.conexionremota.R;
import com.gdglima.conexionremota.model.entity.UserEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @Bind(R.id.tviUsername) TextView tviUsername;

    private UserEntity userEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        injectView();
        extras();
        populate();
    }

    private void populate() {
        if(userEntity!=null)
        {
            StringBuilder stringBuilder= new StringBuilder();
            stringBuilder.append("Bienvenido "+userEntity.getName()+"\n"+"email : "+userEntity.getEmail());
            tviUsername.setText(stringBuilder.toString());
        }

    }

    private void extras() {
        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                userEntity= (UserEntity)bundle.getSerializable("ENTITY");
            }
        }
    }
    private void injectView() {
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}

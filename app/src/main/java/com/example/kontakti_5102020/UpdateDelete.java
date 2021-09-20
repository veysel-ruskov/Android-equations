package com.example.kontakti_5102020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDelete extends ActivityBase {

    protected String ID;
    protected EditText euA, euB, euC;
    protected Button btnUpdate, btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        euA=findViewById(R.id.euA);
        euB=findViewById(R.id.euB);
        euC=findViewById(R.id.euC);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        Bundle b=getIntent().getExtras();
        if(b!=null){
            ID=b.getString("ID");
            euA.setText(b.getString("A"));
            euB.setText(b.getString("B"));
            euC.setText(b.getString("C"));
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ExecSQL(
                            "DELETE FROM URAVNENIQ WHERE ID=?",
                            new Object[]{
                                    ID
                            },
                            new OnQuerySuccess() {
                                @Override
                                public void OnSuccess() {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "Record deleted",
                                            Toast.LENGTH_LONG

                                    ).show();
                                }
                            }
                    );


                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }

                finishActivity(200);
                startActivity(new Intent(UpdateDelete.this,
                        MainActivity.class));
            }
        });


        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            ExecSQL(
                                    "UPDATE URAVNENIQ SET A=?, B=?, C=?" +
                                            "where ID=? ",
                                    new Object[]{
                                            euA.getText().toString(),
                                            euB.getText().toString(),
                                            euC.getText().toString(),
                                            ID
                                    },
                                    new OnQuerySuccess() {
                                        @Override
                                        public void OnSuccess() {
                                            Toast.makeText(
                                                   getApplicationContext(),
                                                   "Record updated",
                                                   Toast.LENGTH_LONG

                                            ).show();
                                        }
                                    }
                            );


                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),
                                    e.getMessage(),
                                    Toast.LENGTH_LONG
                                    ).show();
                        }

                        finishActivity(200);
                        startActivity(new Intent(UpdateDelete.this,
                                MainActivity.class));

                    }
                }
        );

    }
}
package com.example.kontakti_5102020;

import androidx.annotation.CallSuper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ActivityBase {
    protected EditText editA, editB, editC;
    protected TextView resultTextView;
    protected Button btnInsert;
    protected ListView simpleList;

    protected void FillListView() throws Exception{
        final ArrayList<String> listResults=new ArrayList<>();
        SelectSQL(
                "SELECT * FROM URAVNENIQ",
                null,
                new OnSelectElement() {
                    @Override
                    public void OnElementIterate(String A, String B, String C, String ID) {
                        listResults.add(ID+"\tA="+A+"\tB="+B+"\tC="+C+"\n");
                    }
                }
        );
        simpleList.clearChoices();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.activity_listview,
                R.id.textView,
                listResults
        );
        simpleList.setAdapter(arrayAdapter);

    }
    @Override
    @CallSuper
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        try {
            FillListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String Add_Spaces(String str)
    {
        str = str.replaceAll(" ","");
        return str;
    }

    public static String Single_String_Multiplication(String str)
    {
        double a = 1;
        double b = 1;

        for(int i = str.length()-1; i>0; i--)
        {

            if(str.charAt(i) == '*')
            {
                System.out.println("Multiplying");
                boolean check = false;
                int j = i+1;
                int start = i;
                while(!check)
                {
                    if(!("0123456789. ".contains(String.valueOf(str.charAt(j)))) || j == str.length()-1)
                    {
                        if(j == str.length()-1)
                        {
                            a = Double.parseDouble(str.substring(i+1,j+1));
                        }
                        else
                        {
                            a = Double.parseDouble(str.substring(i+1,j));
                        }
                        check = true;
                    }
                    j++;
                }
                check = false;
                int k = i;
                while(!check)
                {
                    k--;
                    if(!("0123456789. ".contains(String.valueOf(str.charAt(k)))) || k == 0)
                    {
                        if(str.contains("*-")) {
                            if(k == 0) {
                                b = Double.parseDouble(str.substring(k,i+1));
                            }
                            else {
                                b = Double.parseDouble(str.substring(k+1,i+1));
                            }
                        }
                        else {
                            if(k == 0) {
                                b = Double.parseDouble(str.substring(k,i));
                            }
                            else {
                                b = Double.parseDouble(str.substring(k+1,i));
                            }
                            check = true;
                        }
                    }
                }
                double sum = a*b;
                if(k == 0)
                {
                    str = str.replace(str.substring(k,j-1), String.valueOf(sum));
                }
                else if(j == str.length()) {
                    str = str.replace(str.substring(k+1,j),String.valueOf(sum));
                }
                else {
                    str = str.replace(str.substring(k+1,j-1), String.valueOf(sum));
                }
                i = str.length()-1;
            }
        }

        return str;
    }

    public static String Single_String_Division(String str)
    {
        double a = 1;
        double b = 1;

        for(int i = str.length()-1; i>0; i--)
        {

            if(str.charAt(i) == '/')
            {
                System.out.println("Dividing");
                boolean check = false;
                int j = i+1;
                if(str.contains("/-")) j++;
                while(!check)
                {
                    if(!("0123456789. ".contains(String.valueOf(str.charAt(j)))) || j == str.length()-1)
                    {

                        if(j == str.length()-1)
                        {
                            a = Double.parseDouble(str.substring(i+1,j+1));
                        }
                        else
                        {
                            a = Double.parseDouble(str.substring(i+1,j));
                        }
                        check = true;
                    }
                    j++;
                }
                check = false;
                int k = i;
                while(!check)
                {
                    k--;
                    if(!("0123456789. ".contains(String.valueOf(str.charAt(k)))) || k == 0)
                    {
                        if(k == 0) {
                            b = Double.parseDouble(str.substring(k,i));
                        }
                        else {
                            b = Double.parseDouble(str.substring(k+1,i));
                        }
                        check = true;
                    }
                }
                System.out.println("A = " + a);
                System.out.println("B = " + b);
                double sum;
                if(a == 0)
                {
                    sum = 0;
                }

                else
                {
                    sum = b/a;
                }
                if(k == 0)
                {
                    str = str.replace(str.substring(k,j-1), String.valueOf(sum));
                }
                else {
                    str = str.replace(str.substring(k+1,j), String.valueOf(sum));
                }
                System.out.println("The sum is " + sum);
                System.out.println("New String = " + str);
                i = str.length()-1;
            }
        }

        return str;
    }

    public static String Single_String_Addition(String str)
    {
        double a = 1;
        double b = 1;

        for(int i = str.length()-1; i>0; i--)
        {
            if(str.charAt(i) == '+')
            {
                System.out.println("Adding");
                boolean check = false;
                int j = i+1;
                while(!check)
                {
                    if(!("0123456789. ".contains(String.valueOf(str.charAt(j)))) || j == str.length()-1)
                    {
                        if(j == str.length()-1)
                        {
                            a = Double.parseDouble(str.substring(i+1,j+1));
                        }
                        else
                        {
                            a = Double.parseDouble(str.substring(i+1,j));
                        }
                        check = true;
                    }
                    j++;
                }
                check = false;
                int k = i;
                while(!check)
                {
                    k--;
                    if(!("0123456789. ".contains(String.valueOf(str.charAt(k)))) || k == 0)
                    {
                        if(k == 0) {
                            b = Double.parseDouble(str.substring(k,i));
                        }
                        else {
                            b = Double.parseDouble(str.substring(k+1,i));
                        }
                        check = true;
                    }
                }
                System.out.println("A = " + a);
                System.out.println("B = " + b);
                double sum = a+b;

                if(k == 0)
                {
                    str = str.replace(str.substring(k,j-1), String.valueOf(sum));
                }
                else {
                    str = str.replace(str.substring(k+1,j-1), String.valueOf(sum));
                }
                System.out.println("The sum is " + sum);
                System.out.println("New String = " + str);
                i = str.length()-1;
            }
        }

        return str;
    }

    public static String Single_String_Substraction(String str) {

        System.out.println("Called Substraction");
        System.out.println(str);
        double a = 1;
        double b = 1;

        for(int i = str.length()-1; i>0; i--)
        {
            if(str.charAt(i) == '–' || str.charAt(i) == '-')
            {
                System.out.println("Substracting  " + str);
                boolean check = false;
                int j = i+1;
                System.out.println("J Index :" + j + i);
                if(str.contains("–") && str.contains("–-")) j++;
                while(!check)
                {
                    if(!("0123456789.. ".contains(String.valueOf(str.charAt(j)))) || j == str.length()-1)
                    {
                        System.out.println((String.valueOf(str.charAt(j))));
                        System.out.println("i = " + i);
                        System.out.println("j = " + j);
                        System.out.println("Length : " + str.length());
                        //System.out.println(str.substring(i-2,j+1));
                        if(str.contains("–-")) {
                            //System.out.println("Succ");
                            if(j == 0) {
                                a = Double.parseDouble(str.substring(i+2,j+1));
                                a = 0 -a;
                            }
                            else {
                                a = Double.parseDouble(str.substring(i+1,j+1));
                                a = 0-a;
                            }
                        }
                        else {
                            if(j == str.length()-1)
                            {
                                a = Double.parseDouble(str.substring(i+1,j+1));
                            }
                            else
                            {
                                a = Double.parseDouble(str.substring(i+1,j));
                            }
                        }
                        check = true;
                    }
                    j++;
                }
                check = false;
                int k = i;
                if(str.contains("–-")) k--;
                while(!check)
                {
                    k--;
                    if(!("0123456789. ".contains(String.valueOf(str.charAt(k)))) || k == 0)
                    {
                        if(str.contains("–-")) {
                            if(k == 0) {
                                b = Double.parseDouble(str.substring(k,i-1));
                            }
                            else {
                                b = Double.parseDouble(str.substring(k+1,i));
                            }
                        }
                        else {
                            if(k == 0) {
                                b = Double.parseDouble(str.substring(k,i));
                            }
                            else {
                                System.out.println("String : " + str);
                                b = Double.parseDouble(str.substring(k+1,i));
                            }
                        }
                        check = true;
                    }
                }

                double sum = b-a;
                System.out.println("A = " + a);
                System.out.println("B = " + b);

                if(k == 0)
                {
                    str = str.replace(str.substring(k,j), String.valueOf(sum));
                }
                else {
                    str = str.replace(str.substring(k+1,j), String.valueOf(sum));
                }
                System.out.println("The sum is " + sum);
                System.out.println("New String = " + str);
                i = str.length()-1;
            }
        }

        return str;
    }

    public static String Check_For_Brackets(String str)
    {
        String str_new;
        int bracket = 0;
        for(int i = 0; i<str.length(); i++)
        {
            if(str.charAt(i) == '(')
            {
                for(int j = i; j<str.length(); j++)
                {
                    if(str.charAt(j) == ')')
                    {
                        bracket = j;
                        break;
                    }
                }
                if(!str.substring(i+1,bracket).contains("("))
                {
                    System.out.println("The Part " + str.charAt(i) + str.charAt(i+1) + " To " + str.charAt(bracket) + str.charAt(bracket-1) + " Does not contain any more brackets");

                    str_new = Calculate(str.substring(i+1,bracket));
                    str = str.replace(str.substring(i,bracket+1), str_new);
                    System.out.println(str);
                }
                else
                {
                    System.out.println("The Part " + str.charAt(i) + str.charAt(i+1) + str.charAt(i+2) + " To " + str.charAt(bracket) + str.charAt(bracket-1) + " Contains a bracket");
                    System.out.println(str);
                }

            }

        }
        if(str.contains("("))
        {
            str = Check_For_Brackets(str);
        }


        return str;
    }

    public static String Calculate(String str) {

        str = Check_For_Brackets(str);
        str = Single_String_Multiplication(str);
        str = Single_String_Division(str);
        str = Single_String_Addition(str);
        str = Single_String_Substraction(str);

        return str;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editA=findViewById(R.id.editA);
        editB=findViewById(R.id.editB);
        editC=findViewById(R.id.editC);
        resultTextView=findViewById(R.id.resultTextView);
        btnInsert=findViewById(R.id.btnInsert);
        simpleList=findViewById(R.id.simpleList);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView clickedText=view.findViewById(R.id.textView);
                String selected=clickedText.getText().toString();
                String[] elements=selected.split("\t");
                String ID=elements[0];
                String A=elements[1];
                String B=elements[2];
                String C=elements[3];
                Intent intent=new Intent(MainActivity.this, UpdateDelete.class);
                Bundle b=new Bundle();
                b.putString("ID", ID);
                b.putString("A", A);
                b.putString("B", B);
                b.putString("C", C);
                intent.putExtras(b);
                startActivityForResult(intent, 200, b);

            }
        });


        try {
            initDb();
            FillListView();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),
                    e.getMessage(),
                    Toast.LENGTH_LONG
                    ).show();
        }

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    ExecSQL("INSERT INTO URAVNENIQ(A, B, C)" +
                                    "VALUES(?, ?, ?)",
                            new Object[]{editA.getText().toString(),
                                    editB.getText().toString(),
                                    editC.getText().toString()
                            },
                            ()->{
                        Toast.makeText(
                                    getApplicationContext(),
                                    "Record Inserted",
                                    Toast.LENGTH_LONG
                            ).show();

                                try {
                                    FillListView();
                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(),
                                            e.getMessage(),
                                            Toast.LENGTH_LONG
                                            ).show();
                                    e.printStackTrace();
                                }


                            }

                    );

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }
                String strA = editA.getText().toString();
                String strB = editB.getText().toString();
                String strC = editC.getText().toString();
                strA = Calculate(strA);
                strB = Calculate(strB);
                strC = Calculate(strC);
                double a = Double.parseDouble(strA);
                double b = Double.parseDouble(strB);
                double c = Double.parseDouble(strC);

                double D = (b*b) - (4*a*c);
                if (D < 0){
                    resultTextView.setText("D < 0, следователно няма реални корени!");

                }else if (D == 0){
                    double x1 = -b/(2*a);
                    String result = Double.toString(x1);
                    resultTextView.setText("D = 0, следователно има само един корен:" + result);
                }else {
                    double x1 = (-b + Math.sqrt(D))/(2*a);
                    double x2 = (-b - Math.sqrt(D))/(2*a);
                    String result = Double.toString(x1);
                    String result2 = Double.toString(x2);
                    resultTextView.setText("X1 = "+ result + "\nX2 = " + result2);
                }
            }
        });




    }
}
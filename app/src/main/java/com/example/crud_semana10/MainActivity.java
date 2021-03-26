package com.example.crud_semana10;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText cod, nombre, direccion, ubicacion;
    Button ins, update, list;
    DatabaseHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cod=findViewById(R.id.cod);
        nombre=findViewById(R.id.nombre);
        direccion=findViewById(R.id.direccion);
        ubicacion=findViewById(R.id.ubicacion);
        ins=findViewById(R.id.btInsert);
        update=findViewById(R.id.btUpdate);
        list=findViewById(R.id.btList);
        DB=new DatabaseHandler(this);

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codTiendaTXT = cod.getText().toString();
                String nombreTXT = nombre.getText().toString();
                String direccionTXT = direccion.getText().toString();
                String ubicacionTXT = ubicacion.getText().toString();
                Boolean checkInsert = DB.insertData(codTiendaTXT, nombreTXT, direccionTXT, ubicacionTXT);

                if (checkInsert == true) {
                    Toast.makeText(MainActivity.this, "Se ha insertado un nuevo dato", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "No se ha insertado el dato", Toast.LENGTH_LONG).show();

                }
            }
        });

list.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Cursor result=DB.getData();
        if(result.getCount()==0){
            Toast.makeText(MainActivity.this,"Aun no hay registros", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while(result.moveToNext()){
            buffer.append("Codigo: "+result.getString(0)+"\n");
            buffer.append("Nombre: "+result.getString(1)+"\n");
            buffer.append("Direccion: "+result.getString(2)+"\n");
            buffer.append("Ubicacion: "+result.getString(3)+"\n\n");

        }
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Listado de tiendas registradas");
        builder.setMessage(buffer.toString());
        builder.show();
    }
});
update.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String codTiendaTXT = cod.getText().toString();
        String nombreTXT = nombre.getText().toString();
        String direccionTXT = direccion.getText().toString();
        String ubicacionTXT = ubicacion.getText().toString();

        Boolean checkInsert = DB.updateData(codTiendaTXT, nombreTXT, direccionTXT, ubicacionTXT);
        if(checkInsert==true){
            Toast.makeText(MainActivity.this,"Su dato se actualizo",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(MainActivity.this,"Su dato no se actualizo",Toast.LENGTH_LONG).show();

        }
    }
});
    }
}
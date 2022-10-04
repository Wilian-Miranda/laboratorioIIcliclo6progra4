package com.example.labotatorioiiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    //variables para los elementos de las cajas
    private lateinit var etName: EditText;
    private lateinit var etnAge: EditText;
    private lateinit var etDepartment: EditText;

    private lateinit var lvPeople: ListView;

    private lateinit var tvResult: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.et_name);
        etnAge = findViewById(R.id.etn_age);
        etDepartment = findViewById(R.id.et_departament);

        lvPeople = findViewById(R.id.lv_people);

        tvResult = findViewById(R.id.tv_result);


    }

    private var name: String = "";
    private var age: Int = 0;
    private var department: String = "";

    //arreglos dinamicos que contendran todos los datos, uno para los nombres, otro para la edad
    // y para el departemento
    private var names = ArrayList<String>();
    private var ages = ArrayList<Int>();
    private var departments = ArrayList<String>();

    public fun addPerson(view: View){
        //si todos los datos estan validados agregamos al array
        if (validateDataFromEditText()){
            //llamamos al método que agrega los datos al array
            addDataToArrays(name,age,department);
            //luego de agregar limpiamos
            cleanDataInEditTextOrViewText();
            //agregamos al listview
            addDataToListView();

            lvPeople.onItemClickListener = object: AdapterView.OnItemClickListener{
                override fun onItemClick(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                    tvResult.setText("Esperando datos...");
                    tvResult.setText("La persona seleccionada es${names[index]} de edad: ${ages[index]} y del" +
                            "departamento de ${departments[index]}");
                    println("${names[index]}");
                }

            }
        }

    }

    private fun validateDataFromEditText(): Boolean{
        //variable que permite saber si los datos fueron debidamente validados
        var validation: Boolean = false;

        //validando el nombre
        if (etName.text.isNotEmpty() && etName.text.isNotBlank()){
            name = etName.text.toString();
            //validando la edad
            if (etnAge.text.isNotEmpty() && etnAge.text.isNotBlank()){
                age = etnAge.text.toString().toInt();
                if (etDepartment.text.isNotEmpty() && etDepartment.text.isNotBlank()){
                    department = etDepartment.text.toString();
                    //al estar todos los datos validados le decimos que todo esta validado
                    validation = true;
                }else{
                    Toast.makeText(this,"Campo de nombre DEPARTAMENTO o vacio. Campo obligatorio",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"Campo de EDAD nulo o vacio. Campo obligatorio",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Campo de NOMBRE nulo o vacio. Campo obligatorio",Toast.LENGTH_SHORT).show();
        }
        return validation;
    }

    //esta funcion luego de agregados los datos los va agregando a los diferentes arrays segun el dato
    private fun addDataToArrays(name: String, age: Int, department: String){
        names.add(name);
        ages.add(age);
        departments.add(department);
    }

    //metodo para limpiar los datos de las variables luego de haberlos agregado a los arrays y tambien
    // de los EditText de la vista
    private fun cleanDataInEditTextOrViewText(){
        //limpiando variables locales
        name = "";
        age = 0;
        department = "";

        //limpiando datos de la vista
        etName.text.clear();
        etnAge.text.clear();
        etDepartment.text.clear();
        tvResult.setText("Esperando datos...");
    }

    private fun addDataToListView(){
        var adapter= ArrayAdapter(this,R.layout.lista_personas,names);
            lvPeople.adapter = adapter;
    }

    //funcion para agregar los datos al ListView


}
package victorcaceres.reservacionrestaurante1;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDrinksActivity extends ActionBarActivity {
    EditText id,name,price;
    Button btnAdd,btnDelete,btnEdit,btnViewAll;
    SQLiteDatabase db;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_drinks);
        id=(EditText)findViewById(R.id.drink_id);
        name=(EditText)findViewById(R.id.drink_name);
        price=(EditText)findViewById(R.id.drink_price);
        db=openOrCreateDatabase("Foods_DB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS table_drinks(drink_id int,drink_name VARCHAR,price int);");
        addListenerOnButton();
    }
    private void addListenerOnButton() {
        final Context context = this;
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnViewAll = (Button) findViewById(R.id.btnViewAll);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.execSQL("INSERT INTO table_drinks VALUES('"+id.getText()+"','"+name.getText()+
                        "','"+price.getText()+"');");
                showMessage("Success", "Record added");
            }

        });
        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.execSQL("UPDATE table_drinks SET drink_name='"+name.getText()+"',price='"+price.getText()+
                        "' WHERE drink_id='"+id.getText()+"'");
                showMessage("Success", "Record Modified");
            }

        });
        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.execSQL("DELETE FROM table_drinks WHERE drink_id='"+id.getText()+"'");
                showMessage("Success", "Record Deleted");
            }

        });
        btnViewAll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Cursor c=db.rawQuery("SELECT * FROM table_drinks", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Id: "+c.getString(0)+"\n");
                    buffer.append("Name: "+c.getString(1)+"\n");
                    buffer.append("Price: "+c.getString(2)+"\n\n");
                }
                showMessage("Student Details", buffer.toString());
            }

        });
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

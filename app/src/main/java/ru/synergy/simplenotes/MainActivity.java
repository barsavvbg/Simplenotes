package ru.synergy.simplenotes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final static String FILE_NAME = "content.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // сохранение файла
    public void saveText(View view) {
        
        FileOutputStream fos = null;
        try {
            // Получаем содержимое EditText
            EditText textBox = (EditText) findViewById(R.id.save_text);
            String text = textBox.getText().toString();
            
            // Подготавливаем файл для вывода
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            // Записываем данные в файл
            fos.write(text.getBytes());
            // Выводим уведомление
            Toast.makeText(this, "Файл сохранён", Toast.LENGTH_SHORT).show();
        }
        catch (IOException ex) {
            // В случае исключения ввода/вывода выводим информацию
            // для отладки
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                if (fos != null)
                    fos.close();
            }
            catch (IOException ex) {

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    // Открытие файла
    public void openText(View view) {

        FileInputStream fin = null;
        TextView textView =(TextView) findViewById(R.id.open_text);
        try {
            // читаем данные из файла
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            // Записываем данные в TextView
            textView.setText(text);
        }
        catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                if (fin != null)
                    fin.close();
            }
            catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
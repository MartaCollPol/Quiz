package edu.upc.eseiaat.pma.quiz;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    /* Creem una "taula" per als ids de les diferents respostes, ho fem per
     * poder fer un bucle i evitar repetir linies de codi al omplir els
     * Radiobuttons. Al utilitzar [] buit fa que detecti la mida de la taula
     * automàticament evitant haber de canviaro si volem afegir o treure contingut.
     * Pel cas dels strings creem directament als Recursos un array amb les diferents
     * respostes.
     */
    private int id_answers[]= {
            R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TextView text_question = (TextView) findViewById(R.id.text_question);
        text_question.setText(R.string.question_content);

        /* Obtenim els recursos directament, els index dels Array comencen a 0 */
        String[] answers = getResources().getStringArray(R.array.answers);


        for (int i=0; i<id_answers.length; i++) {
            RadioButton rb = (RadioButton) findViewById(id_answers[i]);
            rb.setText(answers[i]);
        }

        /* Ara definim qué passa quan cliquem el boto Check per a fer-ho necesitem
        * prèviament posar un ' Listener ' que detecti quan es clica un boto.
        * Obtindrem dels recursos un int apuntant a la posició de la resposta correcta.
         */
        final int correct_answer = getResources().getInteger(R.integer.correct_answer);
        /* Afegim final "const" per a poder accedir-hi des del On click. */
        final RadioGroup group = (RadioGroup) findViewById(R.id.radiogroup);

        Button btn_check = (Button) findViewById(R.id.btn_check);
        btn_check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int id = group.getCheckedRadioButtonId();
                int index = -1;
                for(int i=0; i< id_answers.length; i++){
                    if (id_answers[i] == id){
                        index = i;
                    }
                }
                if(index == correct_answer){
                    Toast.makeText(QuizActivity.this, R.string.ok, Toast.LENGTH_SHORT).show();
                }
                else if (index == -1) {
                    Toast.makeText(QuizActivity.this, R.string.select, Toast.LENGTH_SHORT).show();
                }
                else {

                    Toast.makeText(QuizActivity.this, R.string.notok, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

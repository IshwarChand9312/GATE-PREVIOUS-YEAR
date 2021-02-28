package com.example.gatepreviousyear

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.gatepreviousyear.databinding.FragmentQuestionPaperBinding


class QuestionPaper : Fragment() {

    data class Question(
        val text: String,
        val answers: List<String>)


    private val questions: MutableList<Question> = mutableListOf(
        Question(text = "What is Android Jetpack?",
            answers = listOf("all of these", "tools", "documentation", "libraries"),),
        Question(text = "Base class for Layout?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")),
        Question(text = "Layout for complex Screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")),
        Question(text = "Pushing structured data into a Layout?",
            answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")),
        Question(text = "Inflate layout in fragments?",
            answers = listOf("onCreateView", "onViewCreated", "onCreateLayout", "onInflateLayout")),
        Question(text = "Build system for Android?",
            answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")),
        Question(text = "Android vector format?",
            answers = listOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")),
        Question(text = "Android Navigation Component?",
            answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")),
        Question(text = "Registers app with launcher?",
            answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")),
        Question(text = "Mark a layout for Data Binding?",
            answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>"))
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = questions.size
    var num = arrayOf<Int>(numQuestions,0)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentQuestionPaperBinding>(inflater,R.layout.fragment_question_paper,container,false)


        QuestionRandomized();

        // Bind this fragment class to the layout
        binding.game = this

        binding.next.setOnClickListener {view : View ->

            val checkedId = binding.options.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.option2 -> answerIndex = 1
                    R.id.option3 -> answerIndex = 2
                    R.id.option4 -> answerIndex = 3
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    num[questionIndex] = 1
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    }

                    if( questionIndex >= numQuestions){

                        view.findNavController().navigate(R.id.action_questionPaper_to_result)


                    }
                }
                else {

                    Toast.makeText(context,"you loss the game ",Toast.LENGTH_SHORT).show()

                }


//                if(answers[answerIndex] == currentQuestion.answers[0]){
//
//                    num[questionIndex]  = 1
//                }
//
//
//                else {
//                    num[questionIndex] = -1
//                }
//
//                questionIndex++
//                if(questionIndex < numQuestions) {
//                    currentQuestion = questions[questionIndex]
//                    setQuestion()
//                    binding.invalidateAll()
//                }
//
//            }
//
//
//            else{
//
//           Toast.makeText(context, "Question has finished",Toast.LENGTH_LONG).show()
       }
        }

        return binding.root
    }

    private fun QuestionRandomized(){

        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion(){

        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
    }



}
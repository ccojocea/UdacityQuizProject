# UdacityQuizProject
Udacity Project 3 - Quiz App

What you can expect to find in this app:
- 12 questions, 4 single choice (RadioGroup/RadioButton), 4 multiple choice (CheckBox), 4 typed answers (EditText)
- Some of the questions have code displayed inside ImageView, while others just have a string based question.
- The multiple choice questions can be graded using 3 different systems, selectable through a Spinner. The normal system allows no room for error (according to project 3 design specifications). The other two systems are both partial grading, giving 0.5 points per correct answer, and penalising the user in different ways for trying to select too many answers or for selecting the wrong ones.
- A timer is started as soon as the quiz is available, displayed at the top right of the screen. The timer is paused if the device screen turns off and continues correctly afterwards. The timer is stopped permanently when the user ends the quiz.
- Selecting to check the answers presents the user with a dialog informing him of any questions that he missed (with numbers) and allowing him to cancel his action or end the quiz.
- After ending the quiz, the input is blocked from all answers, preventing changes. A Toast message is displayed in the event they user tries to keep changing anything.
- The score is displayed in a Toast once after finishing, and also in a flashing TextView always visible at the top of the screen.
- All correct/wrong answers are highlighted at the end, including showing EditText correct answers in a separate TextView.
- A restart button is available after completing the quiz which resets everything.
- There's a short and simple splash screen implemented when the app first starts.
- There are separate landscape layouts, although with very minor additions.
- All of the text is in strings.xml and strings_questions.xml (at least I hope so), ready for localisation if needed, used the xliff:g tag as well where needed.

![alt text](https://raw.githubusercontent.com/ccojocea/UdacityQuizProject/blob/master/extras/device_1.png)
![alt text](https://raw.githubusercontent.com/ccojocea/UdacityQuizProject/blob/master/extras/device_2.png)
![alt text](https://raw.githubusercontent.com/ccojocea/UdacityQuizProject/blob/master/extras/device_3.png)
![alt text](https://raw.githubusercontent.com/ccojocea/UdacityQuizProject/blob/master/extras/device_4.png)
![alt text](https://raw.githubusercontent.com/ccojocea/UdacityQuizProject/blob/master/extras/device_5.png)

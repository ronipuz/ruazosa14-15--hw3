# RUAZOSA 14/15 - HW3
[![Build Status](https://travis-ci.org/mrPjer/RUAZOSA-14-15---HW3.png)](https://travis-ci.org/mrPjer/RUAZOSA-14-15---HW3)

This is the third homework for the Android development course [RUAZOSA at FER](https://www.fer.unizg.hr/predmet/ruazosa).

The goal of this homework is to observe and apply the principles learned at the lecture titled "Testing and Continuous Integration". To do this, the homework is split into two tasks.

## Task 1
Your first task is to clone this repository into Android Studio. To do so, simply use the bundled functionality available under *VCS -> Checkout from Version Control -> Git (or GitHub)*.

Inside the repository you will find an Android project with only the tests implemented. Your task is to implement the rest of the functionality based on these tests. You can consider the task done when all tests pass.

Keep in mind the following things:

* You are free to add your own tests, but are forbidden from changing the provided test classes.
* Your goal for this task is to pass the tests - see if you can save some time by providing mock implementations instead of calculating actual data
* If you encounter what you think may be an error (i.e. an unpassable test), let us know. Accidents do happen.
* Finally, feel free to look at the code in the tests - this is the easiest way to find out what is being tested and how to pass the test.

The solution to this task will be pushed to this repository after the deadline.

## Task 2
Your second task is to try out writing tests on your own. Suppose that a friend of yours gave you a project specification in form of the following set of stories:

* On the top of the screen there are two input fields, one on the left side and one on the right. You can only enter numbers into these fields.
* If nothing is entered, the left one should show a hint saying *Real part* and the right one should show a hint saying *Imaginary part*
* Beneath these two fields there is an exact same set of fields, but these are meant for inputting the second complex number
* And beneath those there is a standard select which allows you to choose between four operations - *Add*, *Subtract*, *Multiply* and *Divide*
* Finally, beneath the select there is a button labeled *Calculate*. When you press that button, a new screen should open
* On this screen the result should be displayed in two forms - the standard *{real} + {imag}i* and polar *{radius}*e^i{angle}*, both in their own line
* All values must be rounded to two decimals

Your task is to implement as many tests as you can think of which will test these stories. You are free to implement the solution as well, but this isn't necessary. If you are happy with your solution to HW2, you can add these tests to that project.

Take good care of testing the layout, as well as testing the functionality. If you want to take things even further, we suggest testing the application's response to lifecycle events as well.

You can apply the same patterns as those provided for Task 1 or you can try experimenting with third party libraries such as Roboelectric. You are encouraged to experiment. If you are not sure how to implement some things, try seeking inspiration in the history of the project given in Task 1.

Also, we recommend implementing the input fields as custom views (e.g. ComplexNumberInputView). This way you can shorten the work necessary to test the activity (since you won't be working with nested layouts), but if you do go that route, it would be a good idea to provide tests for the custom component as well.

One last thing to keep in mind is to keep the tests isolated - do not write tests which start off in one Activity and end in another. Rather try testing each Activity in isolation by sending the necessary data through intents.

## Optional - Version control with git
It is highly recommended that you use git to track your changes. There are plenty of tutorials online on how to do this (and some are mentioned in the presentation). Android Studio also comes bundled with git support - since you imported the project from git, it should be able to track changes immediately. After changing some files, try exploring the commit dialog by clicking on *VCS -> Commit Changes*.

Since you will be working on this task alone, commiting should be enough, but you are encouraged to explore other possibilities of git, especially features like file history and annotations / blame.

## Optional - GitHub and Travis CI
This repository has been provided with a .travis.yml file which is enough to configure Travis to start doing integration testing on your project. Setting up Continuous Integration is quite easy - simply create a new GitHub repository into which you will push your solution. If you are doing this before the deadline, make sure that the repository is private. After that, log into Travis with your GitHub account - your repository should be detected and you should be able to enable CI on it.

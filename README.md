# iebis_swdev_debugging
Source code to test debugging

## Instructions
First, **Fork** this project.

There are three exercises splitted in three branches of this repository. You must switch branches to checkout the code of each exercise.
Then, find the bugs that appear in each branch.
Fix the bugs if you can and answer to the questions proposed below.
Commit the code before checking out a different branch to avoid loosing the fixes that you have made to the code.

Once that you are done fixing bugs, **to score you must**:
1. Switch to the master branch.
2. Type below in this README.md file the answer to each question and paste some code that you have used to solve them.
3. Commit the changes
4. Push to your GitHub repository
5. **Finally place a Pull Request so I can see your proposed answers**


## Exercises
### Exercise 1
In this code there is a class called WordAnalyzer that contains several methods that analyzes some characteristics of the word passed as argument when the object WordAnalyzer is created.

For some reason, the methods are not working properly, sometimes they return the correct value and others don't. You need to answer the next questions.

#### Why the method _firstMultipleCharacter_ is returning "c" for the word _comprehensive_, when the correct answer should be "e"?
  The reason for c being returned is inside the private int find function. In the for loop, i is made equal to pos, so the first letter will always be returned by this function because it is equal to its own position in the word. To solve this, all one needs to do is add 1 to int i in the for loop, like so:
  > for (int i = pos + 1; i < word.length(); i++)
  
#### Why the method _firstRepeatedCharacter_ is throwing an exception?
  The exception that is being shown is an out of bounds exception, meaning that a item in a position outside of the array is being called. In this case, this occurs here:
  > if (ch == word.charAt(i + 1))
  
  In order to solve this, the for loop has to be modified so that it is not allowed to run outside of the word's length, and this can be done by subtracting 1 from word.length(), like so:
  > for (int i = 0; i < word.length()-1; i++)
  
  This was tested multiple times and worked with the following "words": Hollow, vroom, vimm. In all cases, the adjacent letters were correctly identified.
  
#### Why the method _countGroupsRepeatedCharacters_ returns 3 in one case when it should be 4?
  This function will not work with words in which the first letter is a repeated group. For example, mississippi will correctly give out 3 groups, but eel and llama will return 0. This is due to the fact that in the for loop, int i is made equal to 1, thus ignoring the first character of the input. The solution would be making int i = 0, like so:
  > for (int i = 0; i < word.length() - 1; i++)
  
  This however causes another error in the function that ensures that repeated groups can contain more than 2 adjacent letters, which uses (i - 1) as a parameter. To solve this next issue, an if and else statement was created. If i is not equal to 0, the process remains unchanged, but if it is equal to 0, the aforementioned part of the statement is ignored, allowing for the first letter to be considered. This is the modification that was made:
  > if (i != 0) {
                        if (word.charAt(i - 1) != word.charAt(i)) // it't the start
                            c++;
                    } else {
                        c++;
                    }
                    
  Since it is inside the repetition for loop, it will only do c++ if the first letter is repeated, so it won't just add 1 to c for every first letter. That would be a lazy solution.
        
**Strategy**: Place breakpoints before the methods are executed, step into them and see what happens.


### Exercise 2
In this code we are placing mines in a board game where we have several spaces that can be mined. 
The boards can contain _Element_ objects, and since _Space_ and _Mine_ inherits from _Element_, the boards can contain this kind as well.

We have two boards of different size and place a different number of mines on each one. But in the second case it takes longer to place all the mines.

#### Why placing less bombs takes longer in the second case?
#### Knowing that usually there are going to be more bombs than spaces in the final boards, how would you change the method _minningTheBoard_ to be more efficient?

**Strategy**: Understand well what the code does. Use conditionals breakpoints.


### Exercise 3
In this case this code looks really simple. When the "d" reaches the value 1.0, the program should end, but it never does.

#### Why does not appear a message indicating that "d is 1"?
  The issue here is the way double and float values are handled by java. The values are derived from the translation of fractions into binary, and the process through which this is done causes small errors, which result in an innacurate number. Through the use of breakpoints one can see that these errors accumulate, so for example instead of d being equal to 3.0 after the third attempt, it is 3.0000000000004. This means the if(d != 1.0) will never be fulfilled, as d will be equal to 1.09999999999, rather than 1.0.
  
#### How will you fix it?
  The solution is to not use doubles (or floats, though they are not relevant to this exercise), and instead use a class called BigDecimal from java.math. This class allows one to control the decimal places that are considered, so while the number may still be inaccurate if left unchecked, the accumulated error can be mitigated by using BigDecimal.setScale, which allows rounding, unlike primitive types such as float and double. Here is the code which functioned correctly (one must remember to import the BigDecimal class):
  > public class Main {

        public static void main(String [] args) {
            BigDecimal d = new BigDecimal(0.0);
            BigDecimal a = new BigDecimal(1.0);
            BigDecimal x = new BigDecimal(0.1);

            while (d.compareTo(a) != 0) {
                BigDecimal sum = d.add(x);
                d = sum.setScale(2, RoundingMode.FLOOR);

            }

            System.out.println("d is 1");
        }
}

Word Density Analysis
Shengqian Liu

Classes
main  

    -App
        The main class for this application
        It calls the Crawer to get the DOM document and then calls Page to analysis and stores the result in a
        maxHeap.
        Then it displays result in maxHeap.
    
    -Crawler
        A web crawler that uses Jsoup to parse a webpage (url) to a list of string list, based on stopWords.txt.
        A new list of words is started when stopword or punctuation is detected.
        Stopwords will not be included in the analysis.
        
        For example: a is in the stopWords.txt
            <html>a word a word a word density word density </html> 
                         ||
                          V
            [word]
            [word]
            [word density word density]
            
    -Page
        A word tree. It takes a a list of list of string and converts them to a word tree.
        For example:
            
            [word]
            [word]
            [word density word density]
                     ||
                     V
                    root
                   /   \
             density:2  word:4          (unique childern of root, in this case, only word and density)
                |         |
             word:1    density:2        (density is the only unique child of word, the count 2 means
                                         there are two word phrases of "word density")
                |         |
           density:1     word:1
                          |
                      density:1
                      
        It then can takes this tree and converts it to a maxHeap, the priority is of course the frequency(count)
        of words.
        For example, the above tree will convert to:
                                            word : 4
                            /                                   \
                    density:2                                word density:2
                   /       \                                 /             \
     word density word:1  word density word density : 1  density word:1  density word density:1
        
    -Util
        A utility class that contains several utility functions
        
    -Word
        A word is defined as
             - the String value
             - the count (frequency)
             - its children, which are also Words
                -children is implemented by HashMap to get O(1) access by String key
                
test

    -AppTest
    -CrawlerTest
    -PageTest
    -UtilTest
    -WordTest
    
    These are simple unit tests for all the classes.
 
Sample outputs:

Input:
java -jar WordDensityAnalysis.jar

Output:
No URL input, using default URL: http://www.brightedge.com/
content : 29
marketing : 11
performance : 11
brightedge : 11
performance : 10
content performance : 10
learn : 7
performance marketing : 5
content performance marketing : 5
careers : 4
search : 4
content marketing : 4
win : 3
customers : 3
brands : 3
business : 3
locations : 3
marketers : 3
optimize : 3
roi : 3
top : 3
understand : 2
seo : 2
campaigns : 2
email : 2
news : 2
address : 2
allows : 2
billions : 2
companies : 2
.....

Input:
java -jar WordDensityAnalysis.jar "http://www.cnn.com/2013/06/10/politics/edward-snowden-profile/"

Output:
nsa : 16
snowden : 14
said : 10
just : 8
us : 8
government : 8
watched : 7
watch : 6
just watched : 6
videos : 6
replay : 6
worked : 5
hawaii : 5
guardian : 5
privacy : 4
kong : 4
told : 4
hong : 4
contractor : 4
obama : 4
leaks : 4
act : 4
world : 4
patriot : 4
patriot act : 4
whistleblower : 4
public : 3
security : 3
says : 3
the : 3
surveillance : 3
news : 3
left : 3
programs : 3
decide : 3
source : 3
im : 3
intelligence : 3
firm : 3
man : 3
cnn : 3
wrong : 3
job : 2
team : 2
leaving : 2
internet : 2
doing : 2
united : 2
liberty : 2
booz : 2
allen : 2
booz allen : 2
hamilton : 2
right : 2
americans : 2
meet : 2
world : 2
election : 2
election results : 2
election results nation : 2
living : 2
community : 2
calls : 2
center : 2
family : 2
answers : 2
outcry : 2
america : 2
russia : 2
safeguard : 2
...


Constraints:

-Crawler
    1. It seems like Jsoup.document.text() only gets the text inside <body> tag, so other text are 
       not included in the analysis.
    2. Some attributes are not removed from the Document object, e.g. "alt", "src", because I did not
       find a good way in Jsoup to do so.
-Page
    1. Becasue stopwords are excluded, some phrases including the stopwords like "your word", "the word"
       are also excluded.
    2. The space complexity is high, which may scale up to O(n * n!) where n is the number of unique words
    
-App
    1. Does not support other parameters, such as specifying the number of word
        - This can be easily implemented though by skipping words in the maxHeap or do something when 
          building the heap
    2. No advanced exception handling ,when exception occurs, the program will simply print the call
       stack and terminates.
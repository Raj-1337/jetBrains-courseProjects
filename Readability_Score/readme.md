* This is a simple java project which calculates readability score for a given text file and gives approximate 
age which is required for understanding the given piece of text.
* It has four different approaches, you can select either one of them or all of them to get an approximate minimum age.  
    >* [ARI](https://en.wikipedia.org/wiki/Automated_readability_index) - Automated Readability Index  
    >* [FK](https://en.wikipedia.org/wiki/Flesch%E2%80%93Kincaid_readability_tests) - Flesch-Kincaid readability score
    >* [SMOG](https://en.wikipedia.org/wiki/SMOG) - Simple Measure of Gobbledygook
    >* [CL](https://en.wikipedia.org/wiki/Coleman%E2%80%93Liau_index) - Coleman–Liau readability score
    >* All outputs score of all the above and gives an approximate age from those.
* scores are calculated based on following parameters:  
    >* number of characters
    >* number of words
    >* number of sentences
    >* number of syllables  
    >* number of polysyllables
* Syllables are counted in a word as:  
   > * number of vowels in a word.
   > * "e" at the end of the word is not counted.
   > * double vowels for example "ai" in "flair" is counted as 1.
   > * if the word has no vowel or has only "e" and ends with it, the enitre syllable of the word is 1.
* Polysyllables - number of words with more than 2 syllables.
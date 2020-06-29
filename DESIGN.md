Core functionality:

* Read at least 2 RSS feeds
    * How exactly does an RSS feed look like? Download one to inspect it, google!
    * Seems to be list of strings with headlines, at least in the example
    
  Analyze feeds:
   * Find potential hot topics = "overlaps" between the news
   * What exactly is an overlap?
     * Could be simply "for each word in feed one, see if you find it also in feeds 2..n"
     * Maybe "filler" words like and,in, ... should be excluded from analysis
     * Are there lists for such (english) words available? -> Google
     * Idea for data structure: list of words, with associated data:
        * Frequency: in how many feeds the word is found
        * Original headline
        * Link to original article (for later text retrieval)
        
   Decision: for the application to be useful, filler words should not be regarded as potential topics.
   * Need a way to filter out the irrelevant words. Idea: only consider nons, verbs and adjectives
   * Or find a list of english filler words0
        
        
* For one stored set of feeds (referenced by id)
    * Return top 3 results as json object ordered by frequency of occurence
    * Idea for data structure:
        * See previous paragraph
        
How to start?

* Domain analysis - do I understand all the objects? "News", "Elements"
* Start without thinking about JSON, endpoints, etc. what is the business logic, exactly?

        
   
   
 
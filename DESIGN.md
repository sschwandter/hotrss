Design diary:

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
   * Need a way to filter out the irrelevant words. Idea: only consider nouns, verbs and adjectives
   * Or find a list of english filler words -> found list, seems to work nicely
        
* For one stored set of feeds (referenced by id)
    * Return top 3 results as json object ordered by frequency of occurence
    * Idea for data structure:
        * See previous paragraph
        
How to start?

* Domain analysis - do I understand all the objects? "News", "Elements"
* Start without thinking about JSON, endpoints, etc. what is the business logic, exactly?

Current status:

* Decided on layered architecture with controllers, services and repositories
* Decided this app only makes sense if filler words are filtered out, and words are normalized so that
  words with different capitalization are considered for the same topic 
* Decided to reuse domain objects as JPA entities and for REST API for this small example, although it pollutes them
  with annotations, default constructurs and non-final fields etc. Seemed overkill not to do so.
* Chose to not re-implement RSS feed parsing but to use a library that does job fine
* For time reasons, only wrote unit tests for the actual topic extraction business logic. Would consider
  integration tests for RSSReaderApptastic and data layer next probably.
* Kept the integration test for RssReader in, although it should be changed according to TODOs in file
* Decided on letting JPA generate ids for the analyses, alternative would have been UUID generation and
  getting rid of the Analysis entity entirely. But it seems ok to do so, "Analysis" could contain more other
  data besides the list if topics in the future and the ids look nicer than UUIDs.

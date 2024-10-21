/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param testingTweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
	//implementing the guessfolloesgraph
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> testingTweets) {
    	 Map<String, Set<String>> f_Graph = new HashMap<>();

    	    for (Tweet t : testingTweets) {
    	        String Theauthor = t.getAuthor().toLowerCase();
    	        f_Graph.putIfAbsent(Theauthor, new HashSet<>());
    	        

    	       
    	        Set<String> Users = new HashSet<>();
    	        String details = t.getText();
    	        String[] words = details.split("\\s+");
    	        //checking the mentioned users
    	        for (String word : words) {
    	            if (word.startsWith("@")) {
    	                String un = word.substring(1).toLowerCase(); 
    	                Users.add(un);
    	            }
    	        }

    	       
    	        for (String using : Users) {
    	        	f_Graph.putIfAbsent(using, new HashSet<>());
    	        	f_Graph.get(Theauthor).add(using);
    	        }
    	    }

    	    return f_Graph;
    	}
    

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    
    //making influencers function
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        Map<String, Integer> fcs = new HashMap<>();

                for (Set<String> f_Users : followsGraph.values()) {
            for (String user : f_Users) {
            	fcs.put(user, fcs.getOrDefault(user, 0) + 1);
            }
        }

       //we sort the influencers
        List<String> influencers = new ArrayList<>(fcs.keySet());
        influencers.sort((first, second) -> fcs.get(second) - fcs.get(first));

        return influencers;
    }

}

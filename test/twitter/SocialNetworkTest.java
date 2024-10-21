/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
	 private static final Tweet tweet1 = new Tweet(1, "alyssa", "is @user it reasonable to talk about rivest so much?", d1);
	    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest @user and @alyssa talk in 30 minutes #hype", d2);
	    private static final Tweet tweet3 = new Tweet(3, "user", "Good afternoon", d2);
	    private static final Tweet tweet4 = new Tweet(4, "user1", "@alyssa Good afternoon", d2);
	    private static final Tweet tweet5 = new Tweet(5, "user2", "@user Good afternoon", d2);
	    
	    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    //incase one is mentioned
    @Test
    public void testForOne() {
        List<Tweet> testingTweets = Arrays.asList(tweet1);
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(testingTweets);
        
        assertEquals(2, followsGraph.size());
        assertTrue(followsGraph.get("alyssa").contains("user"));


    }
    //in case no mentioened in the tweet
    @Test
    public void notMentioned() {
    	 List<Tweet> testingTweets = Arrays.asList(tweet3);
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(testingTweets);
        assertTrue("expected empty graph", followsGraph.isEmpty());
       
    	
    }
    //in case multiple users are mentioned in the tweet
    @Test
    public void MultipleMentioned() {
    	 List<Tweet> testingTweets = Arrays.asList(tweet2);
         Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(testingTweets);
         
         assertEquals(3, followsGraph.size());
         assertTrue(followsGraph.get("bbitdiddle").contains("alyssa"));
         assertTrue(followsGraph.get("bbitdiddle").contains("user"));
         assertTrue(followsGraph.get("alyssa").isEmpty());
         assertTrue(followsGraph.get("user").isEmpty());
         
        
    }
    //if they have same author
    @Test
    public void sameAuthor(){
    	List<Tweet> testingTweets = Arrays.asList(tweet4,tweet5);
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(testingTweets);
        
        assertEquals(4, followsGraph.size());
        assertTrue(followsGraph.get("user1").contains("alyssa"));
        assertTrue(followsGraph.get("user2").contains("user"));
        assertTrue(followsGraph.get("alyssa").isEmpty());
        assertTrue(followsGraph.get("user").isEmpty());
       
    }
    
    
    //if the influencer list is empty
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }
    
//in case there are no foloowers
    @Test
    public void NoUsers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user", new HashSet<>());

        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertTrue("Expected empty list", influencers.isEmpty());
    }
//in case there is one follower
    @Test
    public void Single() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user", new HashSet<>(Arrays.asList("hadiya")));

        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertEquals( 1, influencers.size());
        assertEquals("hadiya", influencers.get(0));
    }
//in case there are multiple followers
    @Test
    public void Multiple() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user", new HashSet<>(Arrays.asList("hadiya", "user1")));
        followsGraph.put("hadiya", new HashSet<>(Arrays.asList("user1")));

        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertEquals(2, influencers.size());
        
        assertEquals("user1", influencers.get(0)); 
        assertEquals("hadiya", influencers.get(1)); 
    }
//in case there are same amount of followers
    @Test
    public void sameamount() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user2", new HashSet<>(Arrays.asList("user")));
        followsGraph.put("user1", new HashSet<>(Arrays.asList("user")));

        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertEquals( 1, influencers.size());
        assertEquals("user", influencers.get(0));
    }
    
    
    
    
    
    

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}

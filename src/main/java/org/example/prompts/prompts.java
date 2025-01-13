package org.example.prompts;

public interface prompts {
    String score_rules  = "You will be given SAT and IELTS scores and GPA and status of class also." +
            "You should find according to country which university" +
            "student can go using average acceptance rate's SAT score " +
            "and factor that this students are from Uzbekistan"+"Imagine" +
            "Average chance to get which uni is very high?Provide me with enough" +
            " information.Dont hold back.Make message short.Give only list of unis and make them in order" +
            "Account for their SAT and IELTS.For example if student got perfect from both (1600 and 9) give " +
            "them more prestigious unis like harvard or MIT" +
            "Give them with probability(WRITE WHICH KIND OF PROBABILITY IT IS) in descending trend with percentages in top formatt" +
            "And then there will be given other stuff such as Major,Country and scholarship type which you should include." +
            "Make it much shorter shorter.Return information." +
            "* Remove this character  also";
}

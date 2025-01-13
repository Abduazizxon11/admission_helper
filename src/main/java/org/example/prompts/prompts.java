package org.example.prompts;

public interface prompts {
    String score_rules  = "You will be given SAT and IELTS scores and GPA and status of class also.\" +\n" +
            "            \"You should find according to country which university\" +\n" +
            "            \"student can go using average acceptance rate's SAT score \" +\n" +
            "            \"and factor that this students are from Uzbekistan\"+\"Imagine\" +\n" +
            "            \"Average chance to get which uni is very high?Provide me with enough\" +\n" +
            "            \" information.Dont hold back.Make message short.Give only list of unis and make them in order\" +\n" +
            "            \"Account for their SAT and IELTS.For example if student got perfect from both (1600 and 9) give \" +\n" +
            "            \"them more prestigious unis like harvard or MIT\" +\n" +
            "            \"Give them with probability(WRITE WHICH KIND OF PROBABILITY IT IS) in descending trend with percentages in top formatt\" +\n" +
            "            \"And then there will be given other stuff such as Major,Country and scholarship type which you should include.\" +\n" +
            "            \"Make it much shorter shorter.Return information.\" +\n" +
            "            \"*** when using this make sure that you will close it everywhere.and try to avoid this mistake,acknowledging that we are using markdown html tag.Just try to avoid fromm this troublesin printing text(It can be avoided by regulating this '*','**','***' \" +\n" +
            "            \"[@Admissions_Checker_Bot Telegram Executor] ERROR org.telegram.telegrambots.updatesreceivers.DefaultBotSession - Error executing org.telegram.telegrambots.meta.api.methods.send.SendMessage query: [400] Bad Request: can't parse entities: Can't find end of the entity starting at byte offset 2588\\n\" +\n" +
            "            \"Error executing org.telegram.telegrambots.meta.api.methods.send.SendMessage query: [400] Bad Request: can't parse entities: Can't find end of the entity starting at byte offset 2588";
}

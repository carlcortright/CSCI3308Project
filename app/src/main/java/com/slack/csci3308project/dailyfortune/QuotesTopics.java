package com.slack.csci3308project.dailyfortune;

import java.util.List;
import java.util.Random;

/**
 * Created by john on 4/3/2016.
 */
public class QuotesTopics {

    //keywords related to waking up in the morning
    private static final String[] MORNING_TOPIC_KEYWORDS = {"wake up", "start day", "morning",
            "sleep", "tired", "energy", "relaxed"};

    /**
     * Determine if a quote is a Morning Quote
     * @param quote The quote we are testing
     * @return true if the quote is related to the morning, false otherwise
     */
    private boolean isMorningQuote(String quote) {
        for (String morningKeyword : MORNING_TOPIC_KEYWORDS) {
            if (quote.contains(morningKeyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pick a morning quote from a GeneralQuoteDatatasource
     * @param dataSource The datasource to get the morning quote from
     * @return a GeneralQuote which is a morning quote
     */
    public GeneralQuote pickMorningQuote(GeneralQuoteDataSource dataSource) {
        List<GeneralQuote> quotes = dataSource.getAllGeneralQuotes();
        Random r = new Random();
        GeneralQuote morningQuote = null;
        while (quotes.size() > 0) {
            int index = r.nextInt(quotes.size());
            GeneralQuote quote = quotes.get(index);
            if (isMorningQuote(quote.getQuote())) {
                morningQuote = quote;
                break;
            }
            quotes.remove(index);
        }

        if (morningQuote != null) {
            return morningQuote;
        }

        return dataSource.getRandomGeneralQuote(); //we tried our best
    }

}

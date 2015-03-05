package com.example.abc.hurl;

/**
 * Created by abc on 3/5/15.
 */
public class QuestionsLoadedEvent {
    final SOQuestions questions;
    public QuestionsLoadedEvent(SOQuestions soQuestions) {
        this.questions = soQuestions;
    }
}

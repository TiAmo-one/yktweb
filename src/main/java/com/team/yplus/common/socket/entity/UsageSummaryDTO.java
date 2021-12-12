package com.team.yplus.common.socket.entity;


import com.team.yplus.common.socket.annotation.RequestType;

@RequestType("UsageSummary")
public class UsageSummaryDTO {
    private IQRDistributionVO lessonUsage;
    private IQRDistributionVO lessonproblemUsage;
    private IQRDistributionVO cardsviewUsage;
    private IQRDistributionVO cardsproblemUsage;
    private IQRDistributionVO danmuUsage;
    private IQRDistributionVO quizUsage;
    private IQRDistributionVO examUsage;
    private IQRDistributionVO tougaoUsage;
    private IQRDistributionVO feedbackUsage;

    public UsageSummaryDTO() {}

    public IQRDistributionVO getLessonUsage() {
        return lessonUsage;
    }

    public void setLessonUsage(IQRDistributionVO lessonUsage) {
        this.lessonUsage = lessonUsage;
    }

    public IQRDistributionVO getLessonproblemUsage() {
        return lessonproblemUsage;
    }

    public void setLessonproblemUsage(IQRDistributionVO lessonproblemUsage) {
        this.lessonproblemUsage = lessonproblemUsage;
    }

    public IQRDistributionVO getCardsviewUsage() {
        return cardsviewUsage;
    }

    public void setCardsviewUsage(IQRDistributionVO cardsviewUsage) {
        this.cardsviewUsage = cardsviewUsage;
    }

    public IQRDistributionVO getCardsproblemUsage() {
        return cardsproblemUsage;
    }

    public void setCardsproblemUsage(IQRDistributionVO cardsproblemUsage) {
        this.cardsproblemUsage = cardsproblemUsage;
    }

    public IQRDistributionVO getDanmuUsage() {
        return danmuUsage;
    }

    public void setDanmuUsage(IQRDistributionVO danmuUsage) {
        this.danmuUsage = danmuUsage;
    }

    public IQRDistributionVO getQuizUsage() {
        return quizUsage;
    }

    public void setQuizUsage(IQRDistributionVO quizUsage) {
        this.quizUsage = quizUsage;
    }

    public IQRDistributionVO getExamUsage() {
        return examUsage;
    }

    public void setExamUsage(IQRDistributionVO examUsage) {
        this.examUsage = examUsage;
    }

    public IQRDistributionVO getTougaoUsage() {
        return tougaoUsage;
    }

    public void setTougaoUsage(IQRDistributionVO tougaoUsage) {
        this.tougaoUsage = tougaoUsage;
    }

    public IQRDistributionVO getFeedbackUsage() {
        return feedbackUsage;
    }

    public void setFeedbackUsage(IQRDistributionVO feedbackUsage) {
        this.feedbackUsage = feedbackUsage;
    }

    @Override
    public String toString() {
        return "UsageSummaryDTO{" +
                "lessonUsage=" + lessonUsage +
                ", lessonproblemUsage=" + lessonproblemUsage +
                ", cardsviewUsage=" + cardsviewUsage +
                ", cardsproblemUsage=" + cardsproblemUsage +
                ", danmuUsage=" + danmuUsage +
                ", quizUsage=" + quizUsage +
                ", examUsage=" + examUsage +
                ", tougaoUsage=" + tougaoUsage +
                ", feedbackUsage=" + feedbackUsage +
                '}';
    }
}

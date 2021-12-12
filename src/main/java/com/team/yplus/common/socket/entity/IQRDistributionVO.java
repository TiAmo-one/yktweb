package com.team.yplus.common.socket.entity;

public class IQRDistributionVO {
    private Integer MinToQ1Count;
    private Integer Q1ToQ2Count;
    private Integer Q3ToUpperInnerFenceCount;
    private Integer UpperInnerFenceToMaxCount;

    public IQRDistributionVO() {}

    public IQRDistributionVO(Integer minToQ1Count, Integer q1ToQ2Count, Integer q3ToUpperInnerFenceCount, Integer upperInnerFenceToMaxCount) {
        MinToQ1Count = minToQ1Count;
        Q1ToQ2Count = q1ToQ2Count;
        Q3ToUpperInnerFenceCount = q3ToUpperInnerFenceCount;
        UpperInnerFenceToMaxCount = upperInnerFenceToMaxCount;
    }

    public Integer getMinToQ1Count() {
        return MinToQ1Count;
    }

    public void setMinToQ1Count(Integer minToQ1Count) {
        MinToQ1Count = minToQ1Count;
    }

    public Integer getQ1ToQ2Count() {
        return Q1ToQ2Count;
    }

    public void setQ1ToQ2Count(Integer q1ToQ2Count) {
        Q1ToQ2Count = q1ToQ2Count;
    }

    public Integer getQ3ToUpperInnerFenceCount() {
        return Q3ToUpperInnerFenceCount;
    }

    public void setQ3ToUpperInnerFenceCount(Integer q3ToUpperInnerFenceCount) {
        Q3ToUpperInnerFenceCount = q3ToUpperInnerFenceCount;
    }

    public Integer getUpperInnerFenceToMaxCount() {
        return UpperInnerFenceToMaxCount;
    }

    public void setUpperInnerFenceToMaxCount(Integer upperInnerFenceToMaxCount) {
        UpperInnerFenceToMaxCount = upperInnerFenceToMaxCount;
    }

    @Override
    public String toString() {
        return "IQRDistributionVO{" +
                "MinToQ1Count=" + MinToQ1Count +
                ", Q1ToQ2Count=" + Q1ToQ2Count +
                ", Q3ToUpperInnerFenceCount=" + Q3ToUpperInnerFenceCount +
                ", UpperInnerFenceToMaxCount=" + UpperInnerFenceToMaxCount +
                '}';
    }
}

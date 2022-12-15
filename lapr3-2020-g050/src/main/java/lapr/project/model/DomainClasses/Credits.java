package lapr.project.model.DomainClasses;

import lapr.project.data.CreditsDB;

import java.util.Map;

import java.util.Objects;

public class Credits {

    private final int id;
    private static final Double RATIO_EURO_TO_CREDIT = 1.0;
    private Integer numberOfCredits;
    private String clientNif;

    public Credits(int id, int numberOfCredits, String clientNif) {
        this.id = id;
        this.numberOfCredits = numberOfCredits;
        this.clientNif = clientNif;
    }

    public static Double getRatioEuroToCredit() {
        return RATIO_EURO_TO_CREDIT;
    }

    public int getId() {
        return id;
    }

    public Integer getNumberOfCredits() {
        return numberOfCredits;
    }

    public String getClientNif() {
        return clientNif;
    }

    public void setNumberOfCredits(Integer numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credits credits = (Credits) o;
        return Objects.equals(numberOfCredits, credits.numberOfCredits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfCredits);
    }

    @Override
    public String toString() {
        return String.format("Credits->Id:%s, ratioEuroToCredit= '%s', numberOfCredits= '%s'%n", id, RATIO_EURO_TO_CREDIT, numberOfCredits);
    }
}


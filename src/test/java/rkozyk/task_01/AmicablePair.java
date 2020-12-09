package rkozyk.task_01;

public class AmicablePair {
    private int firstNumber;
    private int secondNumber;

    public AmicablePair(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + firstNumber;
        result = prime * result + secondNumber;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AmicablePair other = (AmicablePair) obj;
        if (firstNumber != other.firstNumber)
            return false;
        if (secondNumber != other.secondNumber)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[" + firstNumber + ", " + secondNumber + "]";
    }
}

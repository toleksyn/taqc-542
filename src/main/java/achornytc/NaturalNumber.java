package achornytc;

import java.util.*;

public class NaturalNumber implements NaturalNumberInterface {
    private Long value;

    /**
     * Constructor
     * @param number
     */
    public NaturalNumber(Long number) {
        this.setValue(number);
    }

    /**
     * Constructor
     * @param number
     */
    public NaturalNumber(String number) {
        this.setValue(Long.valueOf(number));
    }

    /**
     * Constructor
     * @param number
     */
    public NaturalNumber(Double number) {
        this.setValue(number.longValue());
    }

    /**
     * Getter
     * @return
     */
    @Override
    public Long getValue() {
        return this.value;
    }

    /**
     * Setter
     * @param number
     */
    @Override
    public void setValue(Long number) {
        if (number>0) {
            this.value = number;
        }
        else {
            throw new NumberFormatException(number + " is not a Natural Number");
        }
    }

    /**
     * Checking if Instance of NaturalNumber is simple
     * @return
     */
    @Override
    public boolean isSimple() {
        return (this.getNaturalDividers().toArray().length <= 2);
    }

    /**
     * Checking if Instance of NaturalNumber
     * @param number
     * @return
     */
    @Override
    public boolean isDividerOf(NaturalNumber number) {
        return (this.getValue() == 1) ||
                (number.getValue() % this.getValue() == 0);
    }

    /**
     * Returns paired divider for Instance of NaturalNumber and its other given divider
     * @param divider
     * @return
     */
    @Override
    public NaturalNumber getPairedDividerFor(NaturalNumber divider) {
        return new NaturalNumber(this.getValue() / divider.getValue());
    }

    /**
     * Compares an Instance of NaturalNumber to the other one given
     * @param number
     * @return
     */
    @Override
    public int compareTo(NaturalNumber number) {
        return this.value.compareTo(number.value);
    }

    /**
     * Checking if an Instance of NaturalNumber equals the other one given
     * @param number
     * @return
     */
    @Override
    public boolean equals(NaturalNumber number) {
        return this.compareTo(number) == 0;
    }

    /**
     * Adding Long number ot an Instance of NaturalNumber
     * @param step
     */
    @Override
    public void add(Long step) {
        this.setValue(this.getValue() + step);
    }

    /**
     * Overloaded addition with an Integer argument
     * @param step
     */
    @Override
    public void add(Integer step) {
        this.add(step.longValue());
    }

    /**
     * Overloaded addition with an Byte argument
     * @param step
     */
    @Override
    public void add(Byte step){
        this.add(step.longValue());
    }

    /**
     * Checking if an Instance of NaturalNumber is paired
     * @return
     */
    @Override
    public boolean isPaired() {
        return new NaturalNumber(2L).isDividerOf(this);
    }

    /**
     * Checking if an Instance of NaturalNumber ends with 6 (for internal optimization of calculation)
     * @return
     */
    @Override
    public boolean endsWith6(){
        return this.getValue() % 10 == 6;
    }

    /**
     * Powering the Instance of NaturalNumber
     * @param power
     * @return
     */
    @Override
    public NaturalNumber powTo(Integer power){
        return new NaturalNumber(Math.pow(this.getValue(), power.doubleValue()));
    }

    /**
     * Getting number of digits in an Instance of NaturalNumber
     * @return
     */
    @Override
    public Integer numberOfDigits() {
        return this.getValue().toString().length();
    }

    /**
     * Getting n tale digits from an Instance of NaturalNumbers
     * @param n
     * @return
     */
    @Override
    public NaturalNumber getTaleDigits(Integer n) {
        return new NaturalNumber(this.getValue() % new NaturalNumber(10L).powTo(n).getValue());
    }

    /**
     * A method getNaturalDividers() solves exercise:
     * 224. A natural number n is given. Find all its natural dividers
     * @return a List of natural all dividers
     */
    @Override
    public Set<Long> getNaturalDividers() {
        Set<Long> result = new HashSet<>();
        NaturalNumber minDivider = new NaturalNumber(1L);
        NaturalNumber maxDivider = getPairedDividerFor(minDivider);

        int step = isPaired() ? 1 : 2;

        while(minDivider.compareTo(maxDivider) < 0) {
            if ( minDivider.isDividerOf(this) ) {
                maxDivider = getPairedDividerFor(minDivider);
                result.add(minDivider.getValue());
                result.add(maxDivider.getValue());
            }
            minDivider.add(step);
        }
        return result;
    }

    /**
     * A method getSimpleNaturalDividers() solves exercise:
     * 325. A natural number n is given. Find all its simple natural dividers
     * @return a List of simple dividers
     */
    @Override
    public Set<Long> getSimpleNaturalDividers() {
        Set<Long> simpleDividers = new HashSet<>();

        for (Long divider : this.getNaturalDividers()) {
            if (NaturalNumberInterface.isSimple(divider)) {
                simpleDividers.add(divider);
            }
        }
        return simpleDividers;
    }

    /**
     * A method getPowered2TaleDigitsCoinciders() solves exercise:
     * 561. A natural number n is given. Among the numbers 1, ..., n find all such,
     * whose record coincides with the last digits of their power 2 (5 - 25, 6 - 36, 25 - 625, etc.)
     * @return Map {number : pow(number, 2)}
     */
    @Override
    public Map<Long, Long> getPowered2TaleDigitsCoinciders() {
        Map<Long, Long> pow2Coincidence = new HashMap<>();

        NaturalNumber i = new NaturalNumber(1L);
        while(i.compareTo(this) <= 0) {
            NaturalNumber powered2 = i.powTo(2);
            if ( i.equals( powered2.getTaleDigits( i.numberOfDigits() ) ) ) {
                    pow2Coincidence.putIfAbsent(i.getValue(), powered2.getValue());
                }
            i.add(i.endsWith6() ? 9 : 1);
        }
        return pow2Coincidence;
    }
}

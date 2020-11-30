package achornytc.task_01;

import java.util.Map;
import java.util.Set;

public interface NaturalNumberInterface {

    Long getValue();

    void setValue(Long number);

    static boolean isSimple(Long number) {
        return ((new NaturalNumber(number)).getNaturalDividers().toArray().length <= 2);
    }

    boolean isSimple();

    boolean isDividerOf(NaturalNumber number);

    NaturalNumber getPairedDividerFor(NaturalNumber divider);

    int compareTo(NaturalNumber number);

    boolean equals(NaturalNumber number);

    void add(Long step);

    void add(Integer step);

    boolean isPaired();

    boolean endsWith6();

    NaturalNumber powTo(Integer power);

    Integer numberOfDigits();

    NaturalNumber getTaleDigits(Integer n);

    Set<Long> getNaturalDividers();

    Set<Long> getSimpleNaturalDividers();

    Map<Long, Long> getPowered2TaleDigitsCoinciders();
}

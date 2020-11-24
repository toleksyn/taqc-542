
package achornytc.task_01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class NaturalNumberTestSuit {

    private static Stream<Arguments> getVariableSizedListOfTestParameters(String fileCSV) {
        List<Arguments> listOfArguments = new ArrayList<>();
        List<String> parametersList;
        Long number;
        try (Scanner scanner = new Scanner(new File(fileCSV))) {
            while (scanner.hasNextLine()) {
                parametersList = getListFromLine(scanner.nextLine(), ",");
                List<Long> listOfNumbers = new ArrayList<>();
                number = Long.valueOf(parametersList.get(0).trim());
                for (int i = 1; i < parametersList.size(); i++) {
                    listOfNumbers.add(Long.valueOf(parametersList.get(i).trim()));
                }
                listOfArguments.add(Arguments.of(number, listOfNumbers));
            }
        }
        catch (IOException e) {
            System.out.println("File reading error.");
        }
        return listOfArguments.stream();
    }

    private static List<String> getListFromLine(String line, String delimiter) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(delimiter);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, Long.MAX_VALUE})
    void verifySetGetValue_PositiveCases_InitializedLongShouldEqualGetValue(Long number) {
        NaturalNumber naturalNumber = new NaturalNumber(number);
        assertEquals(number, naturalNumber.getValue(),
                "testing Setter, Getter - positive cases");
    }

    @ParameterizedTest
    @ValueSource(longs = {-1L, 0, Long.MAX_VALUE + 1L})
    void verifySetGetValue_NegativeCases_InitializationShouldThrowException(Long number) {
        assertThrows(NumberFormatException.class, () -> {
            new NaturalNumber(number);
        }, "testing Setter, Getter - negative cases");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/isSimplePositiveCases.csv")
    void verifyIsSimple_PositiveCases_ShouldReturnTrue(Long number) {
        assertTrue(new NaturalNumber(number).isSimple(), "NaturalNumber.isSimple() positive test");
        Assertions.assertTrue(NaturalNumberInterface.isSimple(number), "NaturalNumberInterface.isSimple(Long) positive test");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/isSimpleNegativeCases.csv")
    void verifyIsSimple_NegativeCases_ShouldReturnFalse(Long number) {
        assertFalse(new NaturalNumber(number).isSimple(), "NaturalNumber.isSimple() negative test");
        assertFalse(NaturalNumberInterface.isSimple(number), "NaturalNumberInterface.isSimple(Long) negative test");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/isDividerOfPositiveCases.csv", numLinesToSkip = 1)
    void verifyIsDividerOf_PositiveCases_ShouldReturnTrue(String divider, String dividend) {
        assertTrue(new NaturalNumber(divider).isDividerOf(new NaturalNumber(dividend)));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/isDividerOfNegativeCases.csv", numLinesToSkip = 1)
    void verifyIsDividerOf_NegativeCases_ShouldReturnFalse(String divider, String dividend) {
        assertFalse(new NaturalNumber(divider).isDividerOf(new NaturalNumber(dividend)));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/compareToTestCaseData.csv", numLinesToSkip = 1)
    void verifyCompareTo(String compared, String compareTo, String result) {
        switch (result) {
            case "-1":
                assertTrue(new NaturalNumber(compared)
                        .compareTo(new NaturalNumber(compareTo)) < 0);
                break;
            case "0":
                assertTrue(new NaturalNumber(compared)
                        .compareTo(new NaturalNumber(compareTo)) == 0);
                break;
            case "1":
                assertTrue(new NaturalNumber(compared)
                        .compareTo(new NaturalNumber(compareTo)) > 0);
                break;
            default:
                fail("wrong format of test data: " + compared + "|" + compareTo + "|" + result);
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addTestCaseData.csv", numLinesToSkip = 1)
    void verifyAdd(String additive1, String additive2, String sum_expected, String isValid) {
        NaturalNumber sum_actual = new NaturalNumber(additive1);
        NaturalNumber add2 = new NaturalNumber(additive2);

        switch (isValid.toLowerCase()) {
            case "true":
                sum_actual.add(add2.getValue());
                assertTrue(sum_actual.equals(new NaturalNumber(sum_expected)));
                break;
            case "false":
                sum_actual.add(add2.getValue());
                assertFalse(sum_actual.equals(new NaturalNumber(sum_expected)));
                break;
            case "exception":
                assertThrows(NumberFormatException.class, ()->{
                    sum_actual.add(add2.getValue());
                });
                break;
            default:
                fail("wrong format of test data: " + additive1 + "|" + additive2 + "|"
                        + sum_expected + "|" + isValid);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/isPairedPositiveCases.csv")
    void verifyIsPaired_PositiveCases_ShouldReturnTrue(String number) {
        assertTrue(new NaturalNumber(number).isPaired());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/isPairedNegativeCases.csv")
    void verifyIsPaired_NegativeCases_ShouldReturnFalse(String number) {
        assertFalse(new NaturalNumber(number).isPaired());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/powToPositiveCases.csv", numLinesToSkip = 1)
    void verifyPowTo_PositiveCases_ShouldBeEqualWithExpected(String powered, String power, String expected_pow) {
        assertEquals(new NaturalNumber(powered).powTo(Integer.valueOf(power)).getValue(),
                new NaturalNumber(expected_pow).getValue());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/powToNegativeCases.csv", numLinesToSkip = 1)
    void verifyPowTo_PositiveCases_ShouldBeNotEqualWithExpected(String powered, String power, String expected_pow) {
        assertNotEquals(new NaturalNumber(powered).powTo(Integer.valueOf(power)).getValue(),
                new NaturalNumber(expected_pow).getValue());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/numberOfDigitsPositiveCases.csv", numLinesToSkip = 1)
    void verifyNumberOfDigits_PositiveCases_ShouldBeEqualWithExpected(String number, String expectedNOfDigits) {
        assertEquals(new NaturalNumber(number).numberOfDigits(), Integer.valueOf(expectedNOfDigits));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/numberOfDigitsNegativeCases.csv", numLinesToSkip = 1)
    void verifyNumberOfDigits_PositiveCases_ShouldBeNotEqualWithExpected(String number, String expectedNOfDigits) {
        assertNotEquals(new NaturalNumber(number).numberOfDigits(), Integer.valueOf(expectedNOfDigits));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/getTaleDigitsPositiveCases.csv", numLinesToSkip = 1)
    void verifyGetTaleDigits_PositiveCases_ShouldBeEqualWithExpected(String number, String lengthOfTale, String taleDigits) {
        assertEquals(new NaturalNumber(number).getTaleDigits(Integer.parseInt(lengthOfTale)).getValue(),
                new NaturalNumber(taleDigits).getValue());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/getTaleDigitsNegativeCases.csv", numLinesToSkip = 1)
    void verifyGetTaleDigits_PositiveCases_ShouldBeNotEqualWithExpected(String number, String lengthOfTale, String taleDigits) {
        assertNotEquals(new NaturalNumber(number).getTaleDigits(Integer.parseInt(lengthOfTale)).getValue(),
                new NaturalNumber(taleDigits).getValue());
    }


    @ParameterizedTest
    @MethodSource(value = "dataProvider_NaturalDividers_PositiveCases")
    void verifyGetNaturalDividers_Positive_ActualSetShouldBeEqualToExpected(Long number, List<Long> listOfNumbers) {
        Set<Long> expectedSet = Set.copyOf(listOfNumbers);
        NaturalNumber testedNumber = new NaturalNumber(number);
        Set<Long> actualSet = testedNumber.getNaturalDividers();
        assertEquals(expectedSet, actualSet);
    }

    private static Stream<Arguments> dataProvider_NaturalDividers_PositiveCases() {
        String dataSourcePath = NaturalNumberTestSuit.class.getResource("/getNaturalDividersPositiveCases.csv").getPath();
        return getVariableSizedListOfTestParameters(dataSourcePath);
    }

    @ParameterizedTest
    @MethodSource(value = "dataProvider_NaturalDividers_NegativeCases")
    void verifyGetNaturalDividers_Negative_ActualSetShouldNotBeEqualToExpected(Long number, List<Long> listOfNumbers) {
        Set<Long> expectedSet = Set.copyOf(listOfNumbers);
        NaturalNumber testedNumber = new NaturalNumber(number);
        Set<Long> actualSet = testedNumber.getNaturalDividers();
        assertNotEquals(expectedSet, actualSet);
    }

    private static Stream<Arguments> dataProvider_NaturalDividers_NegativeCases() {
        String dataSourcePath = NaturalNumberTestSuit.class.getResource("/getNaturalDividersNegativeCases.csv").getPath();
        return getVariableSizedListOfTestParameters(dataSourcePath);
    }

    @ParameterizedTest
    @MethodSource(value = "dataProvider_SimpleNaturalDividers_PositiveCases")
    void verifyGetSimpleNaturalDividers_Positive_ActualSetShouldBeEqualToExpected(Long number, List<Long> listOfNumbers) {
            Set<Long> expectedSet = Set.copyOf(listOfNumbers);
            NaturalNumber testedNumber = new NaturalNumber(number);
            Set<Long> actualSet = testedNumber.getSimpleNaturalDividers();
            assertEquals(expectedSet, actualSet);
    }

    private static Stream<Arguments> dataProvider_SimpleNaturalDividers_PositiveCases() {
        String dataSourcePath = NaturalNumberTestSuit.class.getResource("/getSimpleNaturalDividersPositiveCases.csv").getPath();
        return getVariableSizedListOfTestParameters(dataSourcePath);
    }

    @ParameterizedTest
    @MethodSource(value = "dataProvider_SimpleNaturalDividers_NegativeCases")
    void verifyGetSimpleNaturalDividers_Negative_ActualSetShouldNotBeEqualToExpected(Long number, List<Long> listOfNumbers) {
        Set<Long> expectedSet = Set.copyOf(listOfNumbers);
        NaturalNumber testedNumber = new NaturalNumber(number);
        Set<Long> actualSet = testedNumber.getSimpleNaturalDividers();
        assertNotEquals(expectedSet, actualSet);
    }

    private static Stream<Arguments> dataProvider_SimpleNaturalDividers_NegativeCases() {
        String dataSourcePath = NaturalNumberTestSuit.class.getResource("/getSimpleNaturalDividersNegativeCases.csv").getPath();
        return getVariableSizedListOfTestParameters(dataSourcePath);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/getPowered2TaleDigitsCoincidersPositive.csv", numLinesToSkip = 1)
    void verifyGetPowered2TaleDigitsCoinciders_Positive_ExpectedPairsShouldBeInActualMap(Long upperLimit, Long number,
                                                                                   Long pow2coincider) {
        NaturalNumber testedNumber = new NaturalNumber(upperLimit);
        Map<Long, Long> actualMap = testedNumber.getPowered2TaleDigitsCoinciders();
        assertEquals(pow2coincider, actualMap.get(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/getPowered2TaleDigitsCoincidersNegative.csv", numLinesToSkip = 1)
    void verifyGetPowered2TaleDigitsCoinciders_Negative_ExpectedPairsShouldNotBeInActualMap(Long upperLimit, Long number,
                                                                                      Long pow2coincider) {
        NaturalNumber testedNumber = new NaturalNumber(upperLimit);
        Map<Long, Long> actualMap = testedNumber.getPowered2TaleDigitsCoinciders();
        assertNotEquals(pow2coincider, actualMap.get(number));
    }
}
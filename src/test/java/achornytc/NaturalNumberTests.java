
package achornytc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class NaturalNumberTests {
    /**
     * Method implements data provider for reading different size data sets from CSV
     * @param fileCSV path to data source
     * @return Stream of Arguments
     */
    private static Stream<Arguments> multipleResultCSVDataProvider(String fileCSV) {
        List<Arguments> listOfArguments = new ArrayList<>();
        List<String> line;
        Long number;
        try (Scanner scanner = new Scanner(new File(fileCSV))) {
            while (scanner.hasNextLine()) {
                line = getRecordFromLine(scanner.nextLine(), ",");
                List<Long> listOfNumbers = new ArrayList<>();
                number = Long.valueOf(line.get(0).trim());
                for (int i = 1; i < line.size(); i++) {
                    listOfNumbers.add(Long.valueOf(line.get(i).trim()));
                }
                listOfArguments.add(Arguments.of(number, listOfNumbers));
            }
        }
        catch (IOException e) {
            System.out.println("File reading error.");
        }
        return listOfArguments.stream();
    }

    /**
     * Method implements CSV rows splitter.
     * @param line a string from CSV file
     * @param delimiter CSV cells delimiter
     * @return a List of CSV cells
     */
    private static List<String> getRecordFromLine(String line, String delimiter) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(delimiter);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     *  Test of Getter, Setter - Testing technique - BOUNDARY VALUES
     *  POSITIVE: min natural, max natural
     * @param number testing data, received from DataProvider - @ValueSource
     */
    @ParameterizedTest
    @ValueSource(longs = {1L, Long.MAX_VALUE})
    void testSetGetValue_PositiveCases_InitializedLongShouldEqualGetValue(Long number) {
        NaturalNumber naturalNumber = new NaturalNumber(number);
        assertEquals(number, naturalNumber.getValue(),
                "testing Setter, Getter - positive cases");
    }

    /**
     * Test of Getter, Setter
     * Testing technique - BOUNDARY VALUES
     * NEGATIVE: negative Long, 0, maxLong + 1
     * @param number testing data, received from DataProvider - @ValueSource
     */
    @ParameterizedTest
    @ValueSource(longs = {-1L, 0, Long.MAX_VALUE + 1L})
    void testSetGetValue_NegativeCases_InitializationShouldThrowException(Long number) {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            new NaturalNumber(number);
        }, "testing Setter, Getter - negative cases");
    }

    /**
     * Test of method isSimple()
     * Testing technique - EQUIVALENCE PARTITIONING - we have two partitions: simple and not simple numbers
     * POSITIVE - testing with list of simple natural numbers
     * @param number testing data, received from DataProvider - @CsvFileSource
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/isSimplePositiveCases.csv")
    void testIsSimple_PositiveCases_ShouldReturnTrue(Long number) {
        assertTrue(new NaturalNumber(number).isSimple(), "NaturalNumber.isSimple() positive test");
        assertTrue(NaturalNumberInterface.isSimple(number), "NaturalNumberInterface.isSimple(Long) positive test");
    }

    /**
     * Test of method isSimple()
     * Testing technique - EQUIVALENCE PARTITIONING - we have two partitions: simple and not simple numbers
     * NEGATIVE - testing with list of not simple natural numbers
     * @param number testing data, received from DataProvider - @CsvFileSource
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/isSimpleNegativeCases.csv")
    void testIsSimple_NegativeCases_ShouldReturnFalse(Long number) {
        assertFalse(new NaturalNumber(number).isSimple(), "NaturalNumber.isSimple() negative test");
        assertFalse(NaturalNumberInterface.isSimple(number), "NaturalNumberInterface.isSimple(Long) negative test");
    }

    //DECISION TABLE : dividend(indep.) | divider(indep.)
    //POSITIVE - map of correct pairs in .CSV file
    @ParameterizedTest
    @CsvFileSource(resources = "/isDividerOfPositiveCases.csv", numLinesToSkip = 1)
    void testIsDividerOf_PositiveCases_ShouldReturnTrue(String divider, String dividend) {
        assertTrue(new NaturalNumber(divider).isDividerOf(new NaturalNumber(dividend)));
    }
    //NEGATIVE - map of correct pairs in .CSV file
    @ParameterizedTest
    @CsvFileSource(resources = "/isDividerOfNegativeCases.csv", numLinesToSkip = 1)
    void testIsDividerOf_NegativeCases_ShouldReturnFalse(String divider, String dividend) {
        assertFalse(new NaturalNumber(divider).isDividerOf(new NaturalNumber(dividend)));
    }

    //DECISION TABLE : compared(indep.) | compareTo(indep.) | result(dep.){"-1","0","1" - comparator}
    //POSITIVE & NEGATIVE : map of correct triples in .CSV file
    @ParameterizedTest
    @CsvFileSource(resources = "/compareToTestCaseData.csv", numLinesToSkip = 1)
    void testCompareTo(String compared, String compareTo, String result) {
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

    //DECISION TABLE : additive1(indep.) | additeve2(indep.) | sum_expected(indep.) | isValid(dep.)
    //POSITIVE & NEGATIVE
    @ParameterizedTest
    @CsvFileSource(resources = "/addTestCaseData.csv", numLinesToSkip = 1)
    void testAdd(String additive1, String additive2, String sum_expected, String isValid) {
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

    //EQIUVALENCE PARTITIONING : Paired and Non Paired partitions
    //POSITIVE - paired natural numbers
    @ParameterizedTest
    @CsvFileSource(resources = "/isPairedPositiveCases.csv")
    void testIsPaired_PositiveCases_ShouldReturnTrue(String number) {
        assertTrue(new NaturalNumber(number).isPaired());
    }

    //NEGATIVE  - non paired natural numbers
    //NEGATIVE  - non paired natural numbers
    //NEGATIVE  - non paired natural numbers
    @ParameterizedTest
    @CsvFileSource(resources = "/isPairedNegativeCases.csv")
    void testIsPaired_NegativeCases_ShouldReturnFalse(String number) {
        assertFalse(new NaturalNumber(number).isPaired());
    }

    //POSITIVE - map of positive decisions in .CSV file
    @ParameterizedTest
    @CsvFileSource(resources = "/powToPositiveCases.csv", numLinesToSkip = 1)
    void testPowTo_PositiveCases_ShouldBeEqualWithExpected(String powered, String power, String expected_pow) {
        assertEquals(new NaturalNumber(powered).powTo(Integer.valueOf(power)).getValue(),
                new NaturalNumber(expected_pow).getValue());
    }

    //NEGATIVE - map of negative decisions in .CSV file
    @ParameterizedTest
    @CsvFileSource(resources = "/powToNegativeCases.csv", numLinesToSkip = 1)
    void testPowTo_PositiveCases_ShouldBeNotEqualWithExpected(String powered, String power, String expected_pow) {
        assertNotEquals(new NaturalNumber(powered).powTo(Integer.valueOf(power)).getValue(),
                new NaturalNumber(expected_pow).getValue());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/numberOfDigitsPositiveCases.csv", numLinesToSkip = 1)
    void testNumberOfDigits_PositiveCases_ShouldBeEqualWithExpected(String number, String expectedNOfDigits) {
        assertEquals(new NaturalNumber(number).numberOfDigits(), Integer.valueOf(expectedNOfDigits));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/numberOfDigitsNegativeCases.csv", numLinesToSkip = 1)
    void testNumberOfDigits_PositiveCases_ShouldBeNotEqualWithExpected(String number, String expectedNOfDigits) {
        assertNotEquals(new NaturalNumber(number).numberOfDigits(), Integer.valueOf(expectedNOfDigits));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/getTaleDigitsPositiveCases.csv", numLinesToSkip = 1)
    void testGetTaleDigits_PositiveCases_ShouldBeEqualWithExpected(String number, String lengthOfTale, String taleDigits) {
        assertEquals(new NaturalNumber(number).getTaleDigits(Integer.parseInt(lengthOfTale)).getValue(),
                new NaturalNumber(taleDigits).getValue());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/getTaleDigitsNegativeCases.csv", numLinesToSkip = 1)
    void testGetTaleDigits_PositiveCases_ShouldBeNotEqualWithExpected(String number, String lengthOfTale, String taleDigits) {
        assertNotEquals(new NaturalNumber(number).getTaleDigits(Integer.parseInt(lengthOfTale)).getValue(),
                new NaturalNumber(taleDigits).getValue());
    }


    /**
     * Test of getNaturalDividers()
     * Testing technique - EQUIVALENCE PARTITIONING - partitions: 1, 2, 3, 4, 5 digits numbers
     * POSITIVE - given Expected Lists of simple natural dividers should be equal to Actual Result list for given number
     * @param number tested number received from Data Provider - @MethodSource
     * @param listOfNumbers expected list of simple natural dividers of given number
     *                      received from Data Provider - @MethodSource
     */
    @ParameterizedTest
    @MethodSource(value = "dataProvider_NaturalDividers_PositiveCases")
    void testGetNaturalDividers_Positive_ActualSetShouldBeEqualToExpected(Long number, List<Long> listOfNumbers) {
        Set<Long> expectedSet = Set.copyOf(listOfNumbers);
        NaturalNumber testedNumber = new NaturalNumber(number);
        Set<Long> actualSet = testedNumber.getNaturalDividers();
        assertEquals(expectedSet, actualSet);
    }

    private static Stream<Arguments> dataProvider_NaturalDividers_PositiveCases() {
        String dataSourcePath = NaturalNumberTests.class.getResource("/getNaturalDividersPositiveCases.csv").getPath();
        return multipleResultCSVDataProvider(dataSourcePath);
    }

    /**
     * Test of getNaturalDividers()
     * Testing technique - EQUIVALENCE PARTITIONING - partitions: 1, 2, 3, 4, 5 digits numbers
     * NEGATIVE - given Expected Lists of simple natural dividers should not be equal to Actual Result list for given
     * number
     * @param number tested number received from Data Provider - @MethodSource
     * @param listOfNumbers expected list of simple natural dividers of given number
     *                      received from Data Provider - @MethodSource
     */
    @ParameterizedTest
    @MethodSource(value = "dataProvider_NaturalDividers_NegativeCases")
    void testGetNaturalDividers_Negative_ActualSetShouldNotBeEqualToExpected(Long number, List<Long> listOfNumbers) {
        Set<Long> expectedSet = Set.copyOf(listOfNumbers);
        NaturalNumber testedNumber = new NaturalNumber(number);
        Set<Long> actualSet = testedNumber.getNaturalDividers();
        assertNotEquals(expectedSet, actualSet);
    }

    private static Stream<Arguments> dataProvider_NaturalDividers_NegativeCases() {
        String dataSourcePath = NaturalNumberTests.class.getResource("/getNaturalDividersNegativeCases.csv").getPath();
        return multipleResultCSVDataProvider(dataSourcePath);
    }

    /**
     * Test of getSimpleNaturalDividers()
     * Testing technique - EQUIVALENCE PARTITIONING - partitions: 1, 2, 3, 4, 5 digits numbers
     * POSITIVE - given Expected Lists of simple natural dividers should be equal to Actual Result list for given number
     * @param number tested number received from Data Provider - @MethodSource
     * @param listOfNumbers expected list of simple natural dividers of given number
     *                      received from Data Provider - @MethodSource
     */
    @ParameterizedTest
    @MethodSource(value = "dataProvider_SimpleNaturalDividers_PositiveCases")
    void getSimpleNaturalDividers_Positive_ActualSetShouldBeEqualToExpected(Long number, List<Long> listOfNumbers) {
            Set<Long> expectedSet = Set.copyOf(listOfNumbers);
            NaturalNumber testedNumber = new NaturalNumber(number);
            Set<Long> actualSet = testedNumber.getSimpleNaturalDividers();
            assertEquals(expectedSet, actualSet);
    }

    private static Stream<Arguments> dataProvider_SimpleNaturalDividers_PositiveCases() {
        String dataSourcePath = NaturalNumberTests.class.getResource("/getSimpleNaturalDividersPositiveCases.csv").getPath();
        return multipleResultCSVDataProvider(dataSourcePath);
    }

    /**
     * Test of getSimpleNaturalDividers()
     * Testing technique - EQUIVALENCE PARTITIONING - partitions: 1, 2, 3, 4, 5 digits numbers
     * NEGATIVE - given Expected Lists of simple natural dividers should not be equal to Actual Result list for given
     * number
     * @param number tested number received from Data Provider - @MethodSource
     * @param listOfNumbers expected list of simple natural dividers of given number
     *                      received from Data Provider - @MethodSource
     */
    @ParameterizedTest
    @MethodSource(value = "dataProvider_SimpleNaturalDividers_NegativeCases")
    void getSimpleNaturalDividers_Negative_ActualSetShouldNotBeEqualToExpected(Long number, List<Long> listOfNumbers) {
        Set<Long> expectedSet = Set.copyOf(listOfNumbers);
        NaturalNumber testedNumber = new NaturalNumber(number);
        Set<Long> actualSet = testedNumber.getSimpleNaturalDividers();
        assertNotEquals(expectedSet, actualSet);
    }

    private static Stream<Arguments> dataProvider_SimpleNaturalDividers_NegativeCases() {
        String dataSourcePath = NaturalNumberTests.class.getResource("/getSimpleNaturalDividersNegativeCases.csv").getPath();
        return multipleResultCSVDataProvider(dataSourcePath);
    }

    /**
     * Test of getPowered2TaleDigitsCoinciders()
     * Testing technique - EQUIVALENCE PARTITIONING - partitions: 1, 2, 3, 4, 5 digits numbers
     * POSITIVE - given Expected Pairs of numbers should be in Actual Result map
     * @param upperLimit determines max. value in the partition, tested method is called with this parameter
     * @param number tested number - expected
     * @param pow2coincider pow(number, 2) which ends with number
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/getPowered2TaleDigitsCoincidersPositive.csv", numLinesToSkip = 1)
    void getPowered2TaleDigitsCoinciders_Positive_ExpectedPairsShouldBeInActualMap(Long upperLimit, Long number,
                                                                                   Long pow2coincider) {
        NaturalNumber testedNumber = new NaturalNumber(upperLimit);
        Map<Long, Long> actualMap = testedNumber.getPowered2TaleDigitsCoinciders();
        assertEquals(pow2coincider, actualMap.get(number));
    }

    /**
     * Test of getPowered2TaleDigitsCoinciders()
     * Testing technique - EQUIVALENCE PARTITIONING - partitions: 1, 2, 3, 4, 5 digits numbers
     * NEGATIVE - given Expected Pairs of numbers should not be in Actual Result map
     * @param upperLimit determines max. value in the partition, tested method is called with this parameter
     * @param number tested number - expected
     * @param pow2coincider pow(number, 2) which ends with number
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/getPowered2TaleDigitsCoincidersNegative.csv", numLinesToSkip = 1)
    void getPowered2TaleDigitsCoinciders_Negative_ExpectedPairsShouldNotBeInActualMap(Long upperLimit, Long number,
                                                                                      Long pow2coincider) {
        NaturalNumber testedNumber = new NaturalNumber(upperLimit);
        Map<Long, Long> actualMap = testedNumber.getPowered2TaleDigitsCoinciders();
        assertNotEquals(pow2coincider, actualMap.get(number));
    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReadWriteFilesTest {

    ReadWriteFiles readWriteFiles = new ReadWriteFiles();
    ArrayList<GymMembers> gymMembersListTest = new ArrayList<>();
    final boolean isTest = true;

    @BeforeEach
    void setUp() {
        String filePathTest = "Test/TextFilesTests/TextTest.txt";
        readWriteFiles.readFileData(filePathTest);

        //Index 0
        GymMembers gymMemberTest1 = new GymMembers(
                "Linda Nyberg",
                "Grangatan 6, 47371 Halmstad",
                "rally@fakemail.se",
                "490827-3164",
                "2023-10-10",
                "2024-10-10",
                "Platina"
        );
        //Index 1
        GymMembers gymMemberTest2 = new GymMembers(
                "Oskar Bengtsson",
                "Ängstorget 59, 24436 Lund",
                "lucky@fakemail.com",
                "361015-9737",
                "2021-08-09",
                "2023-08-09",
                "Guld"
        );

        gymMembersListTest.add(gymMemberTest1);
        gymMembersListTest.add(gymMemberTest2);
    }

    //Read test

    @Test
    public void readFileDataToArrayTest() {

        assertEquals(gymMembersListTest.get(0).toString(), readWriteFiles.gymMemberList.get(0).toString());
        assertEquals(gymMembersListTest.get(1).toString(), readWriteFiles.gymMemberList.get(1).toString());
        assertNotEquals(gymMembersListTest.get(1).toString(), readWriteFiles.gymMemberList.get(0).toString());
        assertNotEquals(gymMembersListTest.get(0).toString(), readWriteFiles.gymMemberList.get(1).toString());

    }

    @Test
    void isInputAMemberTest() {
        String expectedName1 = "Linda Nyberg";
        String expectedName2 = "Oskar Bengtsson";
        String expectedID1 = "490827-3164";
        String expectedID2 = "361015-9737";
        String falseName = "Gabby Lindbloom";
        String falseID = "20521215-3561";



        assertEquals(readWriteFiles.isInputAMember(expectedName1).getName(), gymMembersListTest.getFirst().getName());
        assertEquals(readWriteFiles.isInputAMember(expectedName2).getName(), gymMembersListTest.get(1).getName());
        assertEquals(readWriteFiles.isInputAMember(expectedID1).getPersonnummer(), gymMembersListTest.getFirst().getPersonnummer());
        assertEquals(readWriteFiles.isInputAMember(expectedID2).getPersonnummer(), gymMembersListTest.get(1).getPersonnummer());

        assert(readWriteFiles.isInputAMember(falseName) == null);
        assert(readWriteFiles.isInputAMember(falseID) == null);
    }

    @Test
    public void checkDaysSinceLastPaymentTest() {

        // Test date is : 2024-11-24
        String lindaSubscribed = gymMembersListTest.get(0).getLatestMembershipPayment(); //2024-10-10
        String oskarSubscribed = gymMembersListTest.get(1).getLatestMembershipPayment(); //2023-08-09
        String oneYearLaterTest = "2023-11-25";
        String sameDayTest = "2024-11-24";

        assert(readWriteFiles.checkDaysSinceLastPayment(isTest, lindaSubscribed) < 365);
        assert(readWriteFiles.checkDaysSinceLastPayment(isTest, oskarSubscribed) >= 365);
        assert(readWriteFiles.checkDaysSinceLastPayment(isTest, oneYearLaterTest) >= 365);
        assert(readWriteFiles.checkDaysSinceLastPayment(isTest, sameDayTest) == 0);

    }

    //Write tests

    @Test
    public void CheckPTStringMessage() {

        //Test date is : 2024-11-24
        //Format > Name;ID;Date <
        String expectedLinda = "Linda Nyberg;490827-3164;2024-11-24";
        String expectedOskar = "Oskar Bengtsson;361015-9737;2024-11-24";

        assertEquals(expectedLinda, readWriteFiles.getPrintToPTFile(isTest, gymMembersListTest.getFirst()));
        assertEquals(expectedOskar, readWriteFiles.getPrintToPTFile(isTest, gymMembersListTest.get(1)));
        assertNotEquals(expectedLinda, readWriteFiles.getPrintToPTFile(isTest, gymMembersListTest.get(1)));
        assertNotEquals(expectedOskar, readWriteFiles.getPrintToPTFile(isTest, gymMembersListTest.getFirst()));

    }
}
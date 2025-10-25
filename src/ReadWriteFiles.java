import javax.swing.JOptionPane;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ReadWriteFiles {

    ArrayList<GymMembers> gymMemberList = new ArrayList<>();

    //reads the file, checks line is not null, sends data to ArrayList setter
    public void readFileData(String filePath) {
        //read the file data to a string
        try (BufferedReader buffRead = new BufferedReader(new FileReader(filePath))) {
            //Reads the file line by line,
            // putting each line into a temp String,
            // then sending it to be processed
            String tempReadInfo;
            while ((tempReadInfo = buffRead.readLine()) != null) {
                readDataToObjectArray(tempReadInfo);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File couldn't be found");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Sets data into ArrayList
    public void readDataToObjectArray(String fileData) {
        //Splits the string into several variables
        //Creates an object
        //Puts object into Array
        String[] splitter = fileData.split(";");

        String name, adress, mailadress, personnummer, boughtMembership, lastSubscriptionPayment, memberLevel;

         name = splitter[0].trim();
         adress = splitter[1].trim();
         mailadress = splitter[2].trim();
         personnummer = splitter[3].trim();
         boughtMembership = splitter[4].trim();
         lastSubscriptionPayment = splitter[5].trim();
         memberLevel = splitter[6].trim();

        gymMemberList.add(new GymMembers(name, adress, mailadress, personnummer, boughtMembership, lastSubscriptionPayment, memberLevel));
    }

    //Checks if user input is a member.
    // - if member return member object.
    // - if not return null.
    public GymMembers isInputAMember(String userInput) {

        for (GymMembers member : gymMemberList) {
            if (member.getName().equalsIgnoreCase(userInput) || member.getPersonnummer().equals(userInput)) {
                return member;
            }
        }
        return null;
    }

    //Check to see how long ago subscription was paid.
    public long checkDaysSinceLastPayment(boolean isTest, String recentPurchase) {

        LocalDate dateNow;
        long daysSincePayment;

        if (isTest) {
            dateNow = LocalDate.of(2024, 11, 24);
        } else {
            dateNow = LocalDate.now();
        }
        String[] dateSplitter = recentPurchase.split("-");
        int year = Integer.parseInt(dateSplitter[0].trim());
        int month = Integer.parseInt(dateSplitter[1].trim());
        int day = Integer.parseInt(dateSplitter[2].trim());
        LocalDate purchaseDate = LocalDate.of(year, month, day);
        daysSincePayment = ChronoUnit.DAYS.between(purchaseDate, dateNow);

        return daysSincePayment;
    }

    //Write to File

    //Builds message for the PT file. (Separate method for testing)
    public String getPrintToPTFile(boolean isTest, GymMembers member) {

        String dateToday;

        if(isTest) {
            dateToday = "2024-11-24";
        }else{
            dateToday = String.valueOf(LocalDate.now());
        }

        String messageToPTFile = (member.getName() + ";" + member.getPersonnummer() +";"+ dateToday);
        return messageToPTFile;
    }

    //Writes PT message to PT file
    public void printToPTFile(String PT_FILE_PATH, String messageToPtFile) {

        try(BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PT_FILE_PATH, true))) {

            buffWrite.write(messageToPtFile + System.lineSeparator());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

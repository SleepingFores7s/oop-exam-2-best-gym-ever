package Objectorienterad_Programmering_och_Java.BestGymEver;

import javax.swing.*;

public class BestGymEver {

    final String GYM_TITLE = "Best Gym Ever!";

    public void MemberProgram() {

        //Essentials
        final String GYM_DATA_PATH = "src/Objectorienterad_Programmering_och_Java/ExaminationWork_2/Resources/Data till inlämningsuppgift 2.txt";
        ReadWriteFiles readWrite = new ReadWriteFiles();
        GymMembers gymMember;

        //Employee loop
        do {

            //Reads the file into ArrayList to start
            readWrite.readFileData(GYM_DATA_PATH);

            //asks the user for a name/ID
            String userInput = JOptionPane.showInputDialog("Input a members Name or ID:number");

            //Exit condition
            if (userInput == null) {
                exitWindow();
            }

            //Checks if the name/ID is a member, and if they are it returns that object
            gymMember = readWrite.isInputAMember(userInput);

            //If object is null, it is not a member.
            if (gymMember == null) {
                messageWindow("The customer is not a member.");
            } else {

                //How long ago in days the last payment was done.
                long daysSinceLastPayment = readWrite.checkDaysSinceLastPayment(false, gymMember.getLatestMembershipPayment());

                //Calls and builds the complete information message.
                String completeMessage = getCompleteMessage(daysSinceLastPayment, gymMember);

                //TODO - Needs to exclude expired people from getting sent to PT file
                if(gymMember.isMembershipStatus()) {

                    //Builds the message to PT file
                    String messageToPT = readWrite.getPrintToPTFile(false, gymMember);

                    //Writes to PT file
                    readWrite.printToPTFile(messageToPT);

                }

                //Shows a window with the information message about selected member.
                messageWindow(completeMessage);

            }

        } while (true);
    }

    private static String getCompleteMessage(long daysSinceLastPayment, GymMembers gymMember) {
        int daysInAYear = 365;
        int daysUntilNextPayment = (int) (daysInAYear - daysSinceLastPayment);

        String completeMessage;

        //Checks if the last payment was more than 1 year ago and creates the String format accordingly.
        if (daysUntilNextPayment < 0) {
            gymMember.setMembershipStatus(false);
            completeMessage = String.format("Name: %s \nMembership plan: Expired", gymMember.getName());
        } else {
            gymMember.setMembershipStatus(true);
            completeMessage = String.format("Name: %s \nMembership plan: %s\nDays until next payment: %d", gymMember.getName(), gymMember.getMemberLevel(), daysUntilNextPayment);
        }
        return completeMessage;
    }

    private void messageWindow(String message) {

        int jExit = JOptionPane.showConfirmDialog(
                null,
                message,
                GYM_TITLE,
                JOptionPane.DEFAULT_OPTION
        );
        if (jExit == -1) {
            exitWindow();
        }

    }

    private void exitWindow() {
        JOptionPane.showMessageDialog(
                null,
                "Closing the program.",
                GYM_TITLE,
                JOptionPane.PLAIN_MESSAGE
        );

        System.exit(0);
    }
}

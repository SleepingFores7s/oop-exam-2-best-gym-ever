package Objectorienterad_Programmering_och_Java.BestGymEver;

public class GymMembers {

    private String name;
    private String adress;
    private String mailAdress;
    private String personnummer;
    private String boughtMembership;
    private String latestMembershipPayment;
    private String memberLevel;
    private boolean membershipStatus;

    public GymMembers(String name,
                      String adress,
                      String mailAdress,
                      String personnummer,
                      String boughtMembership,
                      String latestMembershipPayment,
                      String memberLevel) {
        this.name = name;
        this.adress = adress;
        this.mailAdress = mailAdress;
        this.personnummer = personnummer;
        this.boughtMembership = boughtMembership;
        this.latestMembershipPayment = latestMembershipPayment;
        this.memberLevel = memberLevel;
    }

    public String getName() {
        return this.name;
    }

    public String getPersonnummer() {
        return this.personnummer;
    }

    public String getBoughtMembership() {
        return this.boughtMembership;
    }

    public String getMemberLevel() {
        return this.memberLevel;
    }

    public String getLatestMembershipPayment() {
        return this.latestMembershipPayment;
    }

    public boolean isMembershipStatus() {
        return this.membershipStatus;
    }

    public void setMembershipStatus(boolean membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public String toString() {
        return (this.name +"\n"+
                this.adress +"\n"+
                this.mailAdress +"\n"+
                this.personnummer +"\n"+
                this.boughtMembership +"\n"+
                this.latestMembershipPayment +"\n"+
                this.memberLevel
        );
    }



}

package com.example.loginactivitytest.helpers;

public class IdToVenueConverter {

    public static String getVenueName(String data){
        if (data.equals("1")){
            return "Main Campus";
        }

       else if (data.equals("2")){
            return "Permanent Campus";
        }

        else if (data.equals("3")){
            return "Uttara_Campus";
        }
        else if (data.equals("3")){
            return "C&B";
        }
        else if (data.equals("6")){
            return "Mirpur-11";
        }
        else if (data.equals("11")){
            return "Mirpur-1";
        }
        else if (data.equals("13")){
            return "Saver";
        }
        else if (data.equals("14")){
            return "Gazipur";
        }
        else if (data.equals("15")){
            return "TestVenue1";
        }
        else {
            return "Something went wrong";
        }

    }
}

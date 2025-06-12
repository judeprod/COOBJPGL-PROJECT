package com.example;

public class Location {

        private String loc;
    private int bookingprice;
    private String maps;

    public Location(String loc, int bookingprice, String maps) {
        this.loc = loc;
        this.bookingprice = bookingprice;
        this.maps = maps;
    }

    public String getLoc() { return loc; }
    public int getBprice() { return bookingprice; }
    public String getMaps() { return maps; }
   public String getTitle() {String[] words = loc.split(" ");
    if (words.length >= 3) {return words[0] + " " + words[1] + " " + words[2];}
            return null;
}

}

class HighScore {
    private String name;
    private String date;
    private int time;
    private String capital;

    public HighScore(String name, String date, int time, String capital){
        this.name = name;
        this.date = date;
        this.time = time;
        this.capital = capital;
    }

    public int getTime(){
        return time;
    }

    public String makeString() {
        return String.format("%s | %s | %d | %s", name, date, time, capital);
    }
}

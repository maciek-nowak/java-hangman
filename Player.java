class Player {
    private int life;
    private String name;

    public Player(String name) {
        this.name = name;
        this.life = 5;
    }

    public String getName(){
        return name;
    }

    public int getLife(){
        if (life < 0) {
            life = 0;
        }
        return life;
    }

    public void decreaseLife(int damage){
        life -= damage;
    }

    public boolean isAlive() {
        if (life > 0) {
            return true;
        }
        return false;
    }
}

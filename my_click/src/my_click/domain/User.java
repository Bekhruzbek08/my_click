package my_click.domain;

public class User {

    private static Integer sequence = 0;

    {
        sequence++;
    }
    private Integer id = sequence;
    private String name;
    private String password;
    private Card card;
    public User() {
    }

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("Name = %s, Id = %s,",name,id);
    }
}

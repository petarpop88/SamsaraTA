package objects;

// POJO - Plain Old Java Object

import com.github.javafaker.Faker;
import org.testng.Assert;
import utils.DateTimeUtils;
import utils.PropertiesUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class User {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String about;
    private String secretQuestion;
    private String secretAnswer;
    private Long createdAt;
    private Integer heroCount;
    private List<Hero> heroes;

    private Faker faker;


    public User(String username, String password, String email, String firstName, String lastName, String about, String secretQuestion, String secretAnswer, Date createdAt, Integer heroCount, List<Hero> heroes) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.about = about;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
        setCreatedAt(createdAt);
        this.heroCount = heroCount;
        this.heroes = heroes;
    }

    private User(String username, String password, String email, String firstName, String lastName, String about, String secretQuestion, String secretAnswer) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.about = about;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
        this.createdAt = null;
        this.heroCount = 0;
        this.heroes = new ArrayList<>();
    }

    private User(String username) {
        this.username = username;
        this.password = "Password123";
        this.email = username + "@mail.com";
        this.firstName = faker.name().username();
        this.lastName = faker.name().lastName();
        // this.about = createRandomAbout();
        this.about = "About Me Text";
        this.secretQuestion = "Question?";
        this.secretAnswer = "Answer";
        this.createdAt = null;
        this.heroCount = 0;
        this.heroes = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }


    public Date getCreatedAt() {
        if (this.createdAt == null) {
            return null;
        }
      return DateTimeUtils.getDateTime(this.createdAt);

    }
    public void setCreatedAt(Date date) {
        if (date == null) {
            this.createdAt = null;
        } else {
            this.createdAt = date.getTime();
        }
    }

    public Integer getHeroCount() {
        return heroCount;
    }

    public void setHeroCount(Integer heroCount) {
        this.heroCount = heroCount;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
        if(heroes == null) {
            this.heroCount = 0;
        } else {
            this.heroCount = heroes.size();
        }
    }

    public void addHero(Hero hero) {
        if (this.heroes == null) {
            heroes = new ArrayList<>();
            heroCount = 0;
        }
        if(!heroes.contains(hero)) {
            heroes.add(hero);
            heroCount = heroes.size();
        } else {
            Assert.fail("User '" + getUsername() + "' cannot have two heroes with the same name ");
        }
    }



    public void removeHero(Hero hero) {
        if (heroes.contains(hero)) {
            heroes.remove(hero);
            heroCount = heroes.size();
        } else {
            Assert.fail("User '" + getUsername() + "' doesn't have Hero with the name ");
        }
    }

   public static User createNewUniqueUser(String sUsername) {
        String username = sUsername.toLowerCase() + DateTimeUtils.getDateTimeStamp();
        if (username.length() > 35) {
            Assert.fail("Username ' " + username + " cannot be longer than 35 characters" );
        }
        return new User(username);
    }

    public Faker getFaker() {
        return faker;
    }

    public void setFaker(Faker faker) {
        this.faker = faker;
    }

    @Override
    public String toString () {
        return "User {"
                + "Username: " + getUsername() + ", "
                + "Password: " + getPassword() + ", "
                + "Email: " + getEmail() + ", "
                + "Name: " + getFullName() + ", "
                + "About: " + getAbout() + ", "
                + "Secret Question: " + getSecretQuestion() + ", "
                + "Secret Answer: " + getSecretAnswer() + ", "
                + "Created at: " + getCreatedAt() + ", "
                + "Hero Count: " + getHeroCount() + ", "
                + "Heroes: " + getHeroes() + "}";

    }
}
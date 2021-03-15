import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;

public class Information {
    private String name, phone, gender, email;
    private LocalDate birthDate;
    private ArrayList<String> languages;
    private ArrayList<String> languagesLevel;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Information(String name, String phone, String gender, String email, String birthDate) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
        this.birthDate = LocalDate.parse(birthDate, formatter);
        this.languages = new ArrayList<>();
        this.languagesLevel = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() { return email; }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public  ArrayList<String> getLanguagesLevel() {
        return languagesLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) { this.email = email; }

    public void setBirthDate(String birthDate) {
        this.birthDate = LocalDate.parse(birthDate, formatter);
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public void setLanguagesLevel(ArrayList<String> languagesLevel) {
        this.languagesLevel = languagesLevel;
    }

    public void addLanguage(String language, String languageLevel) {
        languages.add(language);
        languagesLevel.add(languageLevel);
    }

    public void removeLanguage(String language) {
        languagesLevel.remove(languages.indexOf(language));
        languages.remove(language);
    }

    @Override
    public String toString() {
        return "Information{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", languages=" + languages +
                ", languagesLevel=" + languagesLevel +
                '}';
    }
}

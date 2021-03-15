import java.util.ArrayList;

public class User extends Consumer {
    public ArrayList<String> companies;

    public User() {
        super();
        companies = new ArrayList<>();
    }

    public Employee convert() {
        Employee employee = new Employee();
        employee.resume = this.resume;
        return employee;
    }

    public Double getTotalScore() {
        int yearsOfExp = 0;
        for (Experience e : this.resume.experiences) {
            if (e.getEndDate().getYear() == e.getStartDate().getYear())
                yearsOfExp++;
            else
                yearsOfExp += e.getEndDate().compareTo(e.getStartDate());
        }
        return yearsOfExp * 1.5 + this.meanGPA();
    }

    public Integer getYearsOfExperience() {
        int yearsOfExp = 0;
        for (Experience e : this.resume.experiences) {
            if (e.getEndDate().getYear() == e.getStartDate().getYear())
                yearsOfExp++;
            else
                yearsOfExp += e.getEndDate().compareTo(e.getStartDate());
        }

        return yearsOfExp;
    }

    @Override
    public String toString() {
        return resume.userInfo.getName() + "\n";
    }
}

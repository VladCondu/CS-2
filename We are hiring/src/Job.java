import java.util.ArrayList;

public class Job {
    public String name;
    public Company company;
    public boolean flag;
    public Constraint graduationYearConstraint;
    public Constraint yearsOfExperiece;
    public Constraint requiredGPA;
    public ArrayList<User> candidates;
    public int noPositions;
    public long salary;

    public Job(String name, Company company, Double lwrGrad, Double uppGrad, Double lwrExp,
               Double uppExp, Double lwrGPA, Double uppGPA, int noPositions, long salary) {
        this.name = name;
        this.company = company;
        flag = true;
        graduationYearConstraint = new Constraint(lwrGrad, uppGrad);
        yearsOfExperiece = new Constraint(lwrExp, uppExp);
        requiredGPA = new Constraint(lwrGPA, uppGPA);
        candidates = new ArrayList<>();
        this.noPositions = noPositions;
        this.salary = salary;
    }

    public void apply(User user) {
        if(!meetsRequirements(user))
            return;
        Recruiter recruiter = company.getRecruiter(user);
        candidates.add(user);

        // sort candidates in descending order by score
        candidates.sort((u1, u2) -> u2.getTotalScore().compareTo(u1.getTotalScore()));

        if(this.flag)
            recruiter.evaluate(this, user);
    }

    public boolean meetsRequirements(User user) {
        if(user.getGraduationYear() < graduationYearConstraint.lower ||
        user.getGraduationYear() > graduationYearConstraint.upper)
            return false;

        if(user.getYearsOfExperience() < yearsOfExperiece.lower ||
        user.getYearsOfExperience() > yearsOfExperiece.upper)
            return false;

        if(user.meanGPA() < requiredGPA.lower || user.meanGPA() > requiredGPA.upper)
            return false;

        return true;
    }
}

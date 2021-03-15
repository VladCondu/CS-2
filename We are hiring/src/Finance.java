import java.time.LocalDate;

public class Finance extends Department{
    @Override
    public double getTotalSalaryBudget() {
        double sum = 0;
        for(Employee employee : employees) {
            for(Experience experience : employee.resume.experiences)
                if(experience.getEndDate() == null) {
                    if (LocalDate.now().getYear() - experience.getStartDate().getYear() <= 1)
                        sum += employee.getSalary() * 1.1;
                    else
                        sum += employee.getSalary() * 1.16;
                }
        }

        return sum;
    }
}

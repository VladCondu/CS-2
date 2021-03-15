public class Management extends Department{
    @Override
    public double getTotalSalaryBudget() {
        double sum = 0;
        for(Employee e : employees)
            sum += e.getSalary() * 1.16;
        return sum;
    }
}

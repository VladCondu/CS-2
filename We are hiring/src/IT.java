public class IT extends Department{
    @Override
    public double getTotalSalaryBudget() {
        double sum = 0;
        for(Employee e : employees)
            sum += e.getSalary();
        return sum;
    }
}

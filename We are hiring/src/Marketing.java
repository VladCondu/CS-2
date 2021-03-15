public class Marketing extends Department{
    @Override
    public double getTotalSalaryBudget() {
        double sum = 0;
        for(Employee e : employees) {
            if(e.getSalary() > 5000)
                sum += e.getSalary() * 1.1;
            else if(e.getSalary() < 3000)
                sum += e.getSalary();
            else
                sum += e.getSalary() * 1.16;
        }
        return sum;
    }
}

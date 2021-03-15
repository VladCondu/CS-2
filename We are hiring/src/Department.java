import java.util.ArrayList;

public abstract class Department {
    public ArrayList<Employee> employees;
    public ArrayList<Job> jobs;

    public Department() {
        employees = new ArrayList<>();
        jobs = new ArrayList<>();
    }

    public abstract double getTotalSalaryBudget();

    public ArrayList<Job> getJobs() {
        ArrayList<Job> openedJobs = new ArrayList<>();
        for (Job job : jobs)
            if (job.flag)
                openedJobs.add(job);
        return openedJobs;
    }

    public void add(Employee employee) {
        employees.add(employee);
    }

    public void remove(Employee employee) {
        employees.remove(employee);
    }

    public void add(Job job) {
        jobs.add(job);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}

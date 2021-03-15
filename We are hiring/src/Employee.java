public class Employee extends Consumer {
    private Company company;
    private long salary;

    public Employee() {
        super();
        this.company = null;
        this.salary = 0;
    }


    public Company getCompany() {
        return company;
    }

    public long getSalary() {
        return salary;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return resume.userInfo.getName();
    }
}

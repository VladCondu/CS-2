import java.util.ArrayList;

public class Company {
    private final String name;
    private Manager manager;
    public ArrayList<Department> departments;
    public ArrayList<Recruiter> recruiters;

    public Company(String name) {
        this.name = name;
        this.manager = null;
        departments = new ArrayList<>();

        // all departments are added for testing purposes
        departments.add(new IT());
        departments.add(new Management());
        departments.add(new Marketing());
        departments.add(new Finance());

        recruiters = new ArrayList<>();
    }
    public Company(String name, Manager manager) {
        this.name = name;
        this.manager = manager;
        departments = new ArrayList<>();

        // all departments are added for testing purposes
        departments.add(new IT());
        departments.add(new Management());
        departments.add(new Marketing());
        departments.add(new Finance());

        recruiters = new ArrayList<>();
    }

    public void add(Department department) {
        departments.add(department);
    }

    public void add(Recruiter recruiter) {
        for (Department dep : this.departments)
            if (dep instanceof IT)
                this.add(recruiter, dep);
        recruiters.add(recruiter);
    }

    public void add(Employee employee, Department department) {
        for (Department dep : departments)
            if (dep.equals(department))
                dep.add(employee);
    }

    public void remove(Employee employee) {
        for (Department department : departments)
            if (department.getEmployees().contains(employee))
                department.remove(employee);
    }

    public void remove(Department department) {
        for (Department dep : departments)
            if (dep.equals(department)) {
                dep.getEmployees().clear();
                departments.remove(dep);
            }
    }

    public void remove(Recruiter recruiter) {
        recruiters.remove(recruiter);
    }

    public void move(Department source, Department destination) {
        //TODO move Department from source to destination
    }

    public void move(Employee employee, Department newDepartment) {
        //TODO move Employee to new Department
    }

    public boolean contains(Department department) {
        return departments.contains(department);
    }

    public boolean contains(Employee employee) {
        for (Department department : departments)
            if (department.getEmployees().contains(employee))
                return true;
        return false;
    }

    public boolean contains(Recruiter recruiter) {
        return recruiters.contains(recruiter);
    }

    public Recruiter getRecruiter(User user) {
        Recruiter bestRecruiter = recruiters.get(0);
        int max = 0;
        int friendshipDegree;
        for (Recruiter recruiter : recruiters) {
            friendshipDegree = user.getDegreeInFriendship(recruiter);
            if (max < friendshipDegree)
                bestRecruiter = recruiter;
            if (max == friendshipDegree)
                if (bestRecruiter.rating < recruiter.rating)
                    bestRecruiter = recruiter;
        }
        return bestRecruiter;
    }

    public ArrayList<Job> getJobs() {
        ArrayList<Job> availableJobs = new ArrayList<>();
        for (Department department : departments)
            availableJobs.addAll(department.getJobs());
        return availableJobs;
    }

    public String getName() {
        return name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", manager=" + manager +
                '}';
    }
}

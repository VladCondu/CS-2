import java.util.*;

public class Application {
    private static Application application;
    ArrayList<Company> companies;
    ArrayList<User> users;

    // Singleton design pattern used for Application instance
    private Application() {
        companies = new ArrayList<>();
        users = new ArrayList<>();
    }

    public static Application getInstance() {
        if (application == null)
            application = new Application();

        return application;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public Company getCompany(String name) {
        for (Company c : companies)
            if (c.getName().equals(name))
                return c;
        return null;
    }

    public void add(Company company) {
        companies.add(company);
    }

    public void add(User user) {
        users.add(user);
    }

    public boolean remove(Company company) {
        return companies.remove(company);
    }

    public boolean remove(User user) {
        return users.remove(user);
    }

    public ArrayList<Job> getJobs(List<String> companies) {
        ArrayList<Job> jobs = new ArrayList<>();
        for (String search : companies)
            jobs.addAll(getCompany(search).getJobs());
        return jobs;
    }
}

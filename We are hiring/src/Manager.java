import java.util.ArrayList;

public class Manager extends Employee {
    ArrayList<Request<Job, User>> requestList;

    public Manager() {
        super();
        requestList = new ArrayList<>();
    }

    public void process(Job job) {
        for (Request<Job, User> request : requestList) {
            for (int i = 0; i < request.getKey().noPositions; ++i) {
                if (Application.getInstance().users.contains(request.getValue1())) {
//                    if(this.getCompany().departments.isEmpty())
//                        this.getCompany().departments.add(new IT());
                    for (Department dep : this.getCompany().departments)
                        if (dep instanceof IT) {
                            Employee hire = request.getValue1().convert();
                            hire.setCompany(this.getCompany());
                            hire.setSalary(request.getKey().salary);
                            dep.add(hire);
                        }
                    Application.getInstance().users.remove(request.getValue1());
                }
            }
        }
    }

    @Override
    public String toString() {
        return resume.userInfo.getName();
    }
}

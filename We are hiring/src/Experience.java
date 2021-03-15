import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Experience implements Comparable<Experience> {
    private String company;
    private String position;
    //    private String department;
    private LocalDate startDate;
    private LocalDate endDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Experience(String company, String position, String startDate, String endDate) throws Exception {
        this.company = company;
        this.position = position;
//        this.department = department;
        if (startDate == null)
            throw new Exception("InvalidDatesException");
        this.startDate = LocalDate.parse(startDate, formatter);

        if (endDate == null) {
            this.endDate = null;
        } else {
            this.endDate = LocalDate.parse(endDate, formatter);
            if (this.endDate.compareTo(this.startDate) < 1) {
                this.startDate = null;
                this.endDate = null;
                throw new Exception("InvalidDatesException");
            }
        }
    }

    @Override
    public int compareTo(Experience exp) {
        if (this.endDate == null || exp.endDate == null)
            return this.company.compareTo(exp.company);
        return this.endDate.compareTo(exp.endDate) * (-1);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }

//    public String getDepartment() { return department; }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate, formatter);
    }

    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate, formatter);
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPosition(String position) {
        this.position = position;
    }

//    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return "Experience{" +
                "company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

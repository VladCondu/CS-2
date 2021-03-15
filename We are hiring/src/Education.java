import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Education implements Comparable<Education> {
    private String name, level;
    private LocalDate startDate, endDate;
    private Double grade;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Education (String name, String level, String startDate, String endDate, Double grade) throws Exception {
        this.name = name;
        this.level = level;
        this.grade = grade;
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
    // results may be multiplied by -1 to get the descending order
    public int compareTo(Education edu) {
        if (this.endDate == null || edu.endDate == null)
            return this.startDate.compareTo(edu.startDate);

        int ending = this.endDate.compareTo(edu.endDate) * (-1);

        // compare by grade if dates are equal
        return ending == 0 ? this.grade.compareTo(edu.grade) * (-1) : ending;
    }

    public String getName() { return name; }

    public String getLevel() { return level; }

    public LocalDate getStartDate() { return startDate; }

    public LocalDate getEndDate() { return endDate; }

    public Double getGrade() { return grade; }

    public void setName(String name) { this.name = name; }

    public void setLevel(String level) { this.level = level; }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate, formatter);
    }

    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate, formatter);
    }

    public void setGrade(Double grade) { this.grade = grade; }

    public boolean checkFinished(Education e) {
        if(e != null)
            return e.endDate != null;
        return false;
    }

    @Override
    public String toString() {
        return "Education{" +
                "name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", grade=" + grade +
                '}';
    }
}

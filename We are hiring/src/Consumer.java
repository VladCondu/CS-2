import java.util.*;

public abstract class Consumer {
    static class Resume {
        Information userInfo;
        ArrayList<Education> educations;
        ArrayList<Experience> experiences;

        public Resume() {
            educations = new ArrayList<>();
            experiences = new   ArrayList<>();
        }
    }

    public ArrayList<Consumer> socialNetwork;
    public Resume resume;

    public Consumer() {
        socialNetwork = new ArrayList<>();
        resume = new Resume();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return Objects.equals(resume.userInfo.getEmail(), consumer.resume.userInfo.getEmail());
    }

    public void add(Information information) {
        resume.userInfo = information;
    }

    public void add(Education education) {
        resume.educations.add(education);
        Collections.sort(resume.educations);
    }

    public void add(Experience experience) {
        resume.experiences.add(experience);
        Collections.sort(resume.experiences);
    }

    public void add(Consumer consumer) {
        this.socialNetwork.add(consumer);
    }

    public int getDegreeInFriendship(Consumer consumer) {
        int degree = 0;
        boolean pendingDegreeIncrease = true;
        int timeToDegreeIncrease = 1;
        ArrayList<Consumer> visited = new ArrayList<>();
        Queue<Consumer> queue = new LinkedList<>();

        visited.add(this);
        queue.add(this);

        // BFS algorithm to search for degree of friendship
        // which translates to the level which the node is located
        // pendingDegreeIncrease and timeToDegreeIncrease where used to track the level
        while (!queue.isEmpty()) {
            // Dequeue
            Consumer temp = queue.poll();

            timeToDegreeIncrease--;

            if (timeToDegreeIncrease == 0) {
                degree++;
                pendingDegreeIncrease = true;
            }
            if (temp.equals(consumer))
                return degree;
            for (Consumer neighbour : temp.socialNetwork) {
                if (!visited.contains(neighbour)) {
                    if (pendingDegreeIncrease) {
                        timeToDegreeIncrease = queue.size();
                        pendingDegreeIncrease = false;
                    }
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        return -1;
    }

    public void remove(Consumer consumer) {
        this.socialNetwork.remove(consumer);
    }

    // graduation year of college (Bachelor's degree)
    public Integer getGraduationYear() {
        for (Education e : resume.educations)
            if (e.getLevel().equals("college"))
                return e.getEndDate().getYear();
        return null;
    }

    // get meanGPA (academic mean)
    public Double meanGPA() {
        double sum = 0;
        for (Education e : resume.educations)
            if (e.getLevel().equals("college"))
                sum = e.getGrade();

        return sum;
    }

}

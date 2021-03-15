public class Recruiter extends Employee {
    public double rating;

    public Recruiter() {
        super();
        rating = 5;
    }

    public int evaluate(Job job, User user) {
        Double score = rating * user.getTotalScore();
        this.getCompany().getManager().requestList.add(new Request<Job, User>(job, user, this, score));
        rating += 0.1;

        return score.intValue();
    }
}

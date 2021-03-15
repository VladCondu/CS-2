import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import javax.swing.*;


public class UserInterface {
    private JPanel AppGUI;
    private JTextArea textArea1;
    private JList list1;

    public UserInterface() {
        textArea1.append(Application.getInstance().users.toString());
    }

    public static void main(String[] args) throws Exception {
        JSONParser parser = new JSONParser();

        // Add companies from json file
        try (FileReader reader = new FileReader("companies.json")) {
            Object obj = parser.parse(reader);
            JSONArray companies = (JSONArray) obj;

            for (Object o : companies) {
                JSONObject company = (JSONObject) o;
                Application.getInstance().add(new Company((String) company.get("name")));
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        // Add jobs from json
        try (FileReader reader = new FileReader("jobs.json")) {
            Object obj = parser.parse(reader);
            JSONArray jobs = (JSONArray) obj;

            for (Object o : jobs) {

                JSONObject job = (JSONObject) o;
                String name = (String) job.get("name");
                String company = (String) job.get("company");
                String department = (String) job.get("department");
                long salary = (long) job.get("salary");

                Long gradMin = (Long) job.get("lwr_grad");
                Long gradMax = (Long) job.get("upp_grad");

                Long expMin = (Long) job.get("lwr_exp");
                Long expMax = (Long) job.get("upp_exp");


                Double lwrGrad = null;
                Double uppGrad = null;
                Double lwrExp = null;
                Double uppExp = null;

                if (gradMin != null)
                    lwrGrad = gradMin.doubleValue();
                if (gradMax != null)
                    uppGrad = gradMax.doubleValue();
                if (expMin != null)
                    lwrExp = expMin.doubleValue();
                if (expMax != null)
                    uppExp = expMax.doubleValue();

                Double lwrGPA = (Double) job.get("lwr_GPA");
                Double uppGPA = (Double) job.get("upp_GPA");

                Long noPositions = (Long) job.get("no_positions");

                if (department.equals("IT")) {
                    for (Department dep : Application.getInstance().getCompany(company).departments)
                        dep.add(new Job(name, Application.getInstance().getCompany(company),
                                lwrGrad, uppGrad, lwrExp, uppExp, lwrGPA, uppGPA, noPositions.intValue(), salary));
                }
                //TODO: add entry for other departments
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        // Add comsumers from json
        try (FileReader reader = new FileReader("consumers.json")) {
            //Read json file
            Object obj = parser.parse(reader);

            JSONObject consumers = (JSONObject) obj;
            JSONArray employees = (JSONArray) consumers.get("employees");
            JSONArray recruiters = (JSONArray) consumers.get("recruiters");
            JSONArray users = (JSONArray) consumers.get("users");
            JSONArray managers = (JSONArray) consumers.get("managers");

            addConsumers(users, "user");
            addConsumers(employees, "employee");
            addConsumers(recruiters, "recruiter");
            addConsumers(managers, "manager");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("App GUI");
        frame.setContentPane(new UserInterface().AppGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private static void addConsumers(JSONArray consumersList, String type) throws Exception {
        for (Object obj : consumersList) {
            JSONObject consumer = (JSONObject) obj;
            JSONArray education = (JSONArray) consumer.get("education");
            JSONArray experience = (JSONArray) consumer.get("experience");
            String name = (String) consumer.get("name");
            String email = (String) consumer.get("email");
            String phone = (String) consumer.get("phone");
            String birthDate = (String) consumer.get("date_of_birth");
            String gender = (String) consumer.get("genre");
            JSONArray languages = (JSONArray) consumer.get("languages");
            JSONArray languagesLevel = (JSONArray) consumer.get("languages_level");

            // add user to user list in app instance
            if (type.equals("user")) {
                JSONArray interestedCompanies = (JSONArray) consumer.get("interested_companies");
                User newUser = new User();

                newUser.add(new Information(name, phone, gender, email, birthDate));

                // add languages and languages levels
                for (int j = 0; j < languages.size(); ++j) {
                    newUser.resume.userInfo.addLanguage((String) languages.get(j), (String) languagesLevel.get(j));
                }

                for (Object interestedCompany : interestedCompanies) {
                    newUser.companies.add((String) interestedCompany);
                }

                for (Object o : education) {
                    JSONObject eduObject = (JSONObject) o;
                    String level = (String) eduObject.get("level");
                    String edu_name = (String) eduObject.get("name");
                    String startDate = (String) eduObject.get("start_date");
                    String endDate = (String) eduObject.get("end_date");
                    Double grade = (Double) eduObject.get("grade");


                    newUser.add(new Education(edu_name, level, startDate, endDate, grade));
                }

                for (Object o : experience) {
                    JSONObject expObject = (JSONObject) o;
                    String company = (String) expObject.get("company");
                    String position = (String) expObject.get("position");
                    String startDate = (String) expObject.get("start_date");
                    String endDate = (String) expObject.get("end_date");

                    newUser.add(new Experience(company, position, startDate, endDate));
                }

                Application.getInstance().add(newUser);
            }

            // add employees to employeesList
            if (type.equals("employee")) {
                Employee newEmployee = new Employee();

                newEmployee.add(new Information(name, phone, gender, email, birthDate));
                newEmployee.setSalary((long) consumer.get("salary"));

                // add languages and languages levels
                for (int j = 0; j < languages.size(); ++j) {
                    newEmployee.resume.userInfo.addLanguage((String) languages.get(j), (String) languagesLevel.get(j));
                }

                for (Object o : education) {
                    JSONObject eduObject = (JSONObject) o;
                    String level = (String) eduObject.get("level");
                    String edu_name = (String) eduObject.get("name");
                    String startDate = (String) eduObject.get("start_date");
                    String endDate = (String) eduObject.get("end_date");
                    Double grade = (Double) eduObject.get("grade");


                    newEmployee.add(new Education(edu_name, level, startDate, endDate, grade));
                }

                for (Object o : experience) {
                    JSONObject expObject = (JSONObject) o;
                    String company = (String) expObject.get("company");
                    String department = (String) expObject.get("department");
                    String position = (String) expObject.get("position");
                    String startDate = (String) expObject.get("start_date");
                    String endDate = (String) expObject.get("end_date");

                    newEmployee.add(new Experience(company, position, startDate, endDate));
                    if (endDate == null) {
                        if (department.equals("IT"))
                            for (Department dep : Application.getInstance().getCompany(company).departments)
                                if (dep instanceof IT) {
                                    newEmployee.setCompany(Application.getInstance().getCompany(company));
                                    Application.getInstance().getCompany(company).add(newEmployee, dep);
                                }

                        if (department.equals("Management"))
                            for (Department dep : Application.getInstance().getCompany(company).departments)
                                if (dep instanceof Management) {
                                    newEmployee.setCompany(Application.getInstance().getCompany(company));
                                    Application.getInstance().getCompany(company).add(newEmployee, dep);
                                }
                        if (department.equals("Marketing"))
                            for (Department dep : Application.getInstance().getCompany(company).departments)
                                if (dep instanceof Marketing) {
                                    newEmployee.setCompany(Application.getInstance().getCompany(company));
                                    Application.getInstance().getCompany(company).add(newEmployee, dep);
                                }
                        if (department.equals("Finance"))
                            for (Department dep : Application.getInstance().getCompany(company).departments)
                                if (dep instanceof Finance) {
                                    newEmployee.setCompany(Application.getInstance().getCompany(company));
                                    Application.getInstance().getCompany(company).add(newEmployee, dep);
                                }
                    }
                }
            }

            // add recruiters to their company
            if (type.equals("recruiter")) {
                Recruiter newRecruiter = new Recruiter();

                newRecruiter.add(new Information(name, phone, gender, email, birthDate));
                newRecruiter.setSalary((long) consumer.get("salary"));
                // add languages and languages levels
                for (int j = 0; j < languages.size(); ++j) {
                    newRecruiter.resume.userInfo.addLanguage((String) languages.get(j), (String) languagesLevel.get(j));
                }

                for (Object o : education) {
                    JSONObject eduObject = (JSONObject) o;
                    String level = (String) eduObject.get("level");
                    String edu_name = (String) eduObject.get("name");
                    String startDate = (String) eduObject.get("start_date");
                    String endDate = (String) eduObject.get("end_date");
                    Double grade = (Double) eduObject.get("grade");


                    newRecruiter.add(new Education(edu_name, level, startDate, endDate, grade));
                }

                for (Object o : experience) {
                    JSONObject expObject = (JSONObject) o;
                    String company = (String) expObject.get("company");
                    String position = (String) expObject.get("position");
                    String startDate = (String) expObject.get("start_date");
                    String endDate = (String) expObject.get("end_date");

                    newRecruiter.add(new Experience(company, position, startDate, endDate));
                    if (endDate == null) {
                        for (Department dep : Application.getInstance().getCompany(company).departments)
                            if (dep instanceof IT) {
                                newRecruiter.setCompany(Application.getInstance().getCompany(company));
                                Application.getInstance().getCompany(company).add(newRecruiter, dep);
                            }
                    }
                }
            }

            // add managers to their company
            if (type.equals("manager")) {
                Manager newManager = new Manager();

                newManager.add(new Information(name, phone, gender, email, birthDate));
                newManager.setSalary((long) consumer.get("salary"));
                // add languages and languages levels
                for (int j = 0; j < languages.size(); ++j) {
                    newManager.resume.userInfo.addLanguage((String) languages.get(j), (String) languagesLevel.get(j));
                }

                for (Object o : education) {
                    JSONObject eduObject = (JSONObject) o;
                    String level = (String) eduObject.get("level");
                    String edu_name = (String) eduObject.get("name");
                    String startDate = (String) eduObject.get("start_date");
                    String endDate = (String) eduObject.get("end_date");
                    Double grade = (Double) eduObject.get("grade");


                    newManager.add(new Education(edu_name, level, startDate, endDate, grade));
                }

                for (Object o : experience) {
                    JSONObject expObject = (JSONObject) o;
                    String company = (String) expObject.get("company");
                    String position = (String) expObject.get("position");
                    String startDate = (String) expObject.get("start_date");
                    String endDate = (String) expObject.get("end_date");

                    newManager.add(new Experience(company, position, startDate, endDate));
                    if (endDate == null) {
                        Application.getInstance().getCompany(company).setManager(newManager);
                    }
                }
            }

        }
    }
}

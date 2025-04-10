
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

//type this in terminal to run the program
//java -jar ResumeBuilder.jar

// Abstract Base Class
abstract class ResumeSection {
        public abstract void addToDocument(Document document);
}

// Skills Section
class SkillsSection extends ResumeSection {
        private String[] skills;

        public SkillsSection(String[] skills) {
                this.skills = skills;
        }

        @Override
        public void addToDocument(Document document) {
                document.add(new Paragraph("\nSKILLS:"));
                for (String skill : skills) {
                        if (!skill.trim().isEmpty()) {
                                document.add(new Paragraph("• " + skill.trim()));
                        }
                }
        }
}

// Education Section
class EducationSection extends ResumeSection {
        private String[] education;

        public EducationSection(String[] education) {
                this.education = education;
        }

        @Override
        public void addToDocument(Document document) {
                document.add(new Paragraph("\nEDUCATION:"));
                for (String edu : education) {
                        if (!edu.trim().isEmpty()) {
                                document.add(new Paragraph("• " + edu.trim()));
                        }
                }
        }
}

// Experience Section
class ExperienceSection extends ResumeSection {
        private String[] experience;

        public ExperienceSection(String[] experience) {
                this.experience = experience;
        }

        @Override
        public void addToDocument(Document document) {
                document.add(new Paragraph("\nWORK EXPERIENCE:"));
                for (String exp : experience) {
                        if (!exp.trim().isEmpty()) {
                                document.add(new Paragraph("• " + exp.trim()));
                        }
                }
        }
}

// Certificates Section
class CertificatesSection extends ResumeSection {
        private String[] certificates;

        public CertificatesSection(String[] certificates) {
                this.certificates = certificates;
        }

        @Override
        public void addToDocument(Document document) {
                document.add(new Paragraph("\nCERTIFICATES:"));
                for (String cert : certificates) {
                        if (!cert.trim().isEmpty()) {
                                document.add(new Paragraph("• " + cert.trim()));
                        }
                }
        }
}

// Project Section
class ProjectSection extends ResumeSection {
        private String[] projects;

        public ProjectSection(String[] projects) {
                this.projects = projects;
        }

        @Override
        public void addToDocument(Document document) {
                document.add(new Paragraph("\nPROJECTS:"));
                for (String project : projects) {
                        if (!project.trim().isEmpty()) {
                                document.add(new Paragraph("• " + project.trim()));
                        }
                }
        }
}

// Main Class
public class ResumeBuilder {
        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                // Collect details
                System.out.print("Enter your full name: ");
                String name = scanner.nextLine();

                System.out.print("Enter your email: ");
                String email = scanner.nextLine();

                System.out.print("Enter your phone number: ");
                String phone = scanner.nextLine();

                System.out.print("Enter your LinkedIn profile URL: ");
                String linkedin = scanner.nextLine();

                System.out.print("Summarize yourself:");
                String summary = scanner.nextLine();

                System.out.print("Enter your skills (comma-separated): ");
                String[] skills = scanner.nextLine().split(",");

                System.out.print("Enter your education (semicolon-separated): ");
                String[] education = scanner.nextLine().split(";");

                System.out.print("Enter your experience (semicolon-separated): ");
                String[] experience = scanner.nextLine().split(";");

                System.out.print("Enter your certificates (semicolon-separated): ");
                String[] certificates = scanner.nextLine().split(";");

                System.out.print("Enter your projects (semicolon-separated): ");
                String[] projects = scanner.nextLine().split(";");

                // Create section objects
                List<ResumeSection> sections = new ArrayList<>();
                sections.add(new SkillsSection(skills));
                sections.add(new EducationSection(education));
                sections.add(new ExperienceSection(experience));
                sections.add(new CertificatesSection(certificates));
                sections.add(new ProjectSection(projects));

                // Generate PDF
                String filePath = "ResumeBuilder.pdf";
                try {
                        PdfWriter writer = new PdfWriter(filePath);
                        PdfDocument pdfDoc = new PdfDocument(writer);
                        Document document = new Document(pdfDoc);

                        document.add(new Paragraph("RESUME"));
                        document.add(new Paragraph("==============================="));
                        document.add(new Paragraph("NAME: " + name));
                        document.add(new Paragraph("EMAIL: " + email));
                        document.add(new Paragraph("PHONE: " + phone));
                        document.add(new Paragraph("LINKEDIN: " + linkedin));
                        document.add(new Paragraph("====================================="));

                        document.add(new Paragraph("About Me:" + summary));
                        document.add(new Paragraph("------------------------------------"));

                        for (ResumeSection section : sections) {
                                section.addToDocument(document);
                                document.add(new Paragraph("------------------------------------"));
                        }

                        document.close();
                        System.out.println("\nResume successfully saved as: " + filePath);
                } catch (FileNotFoundException e) {
                        System.out.println("File not Found.");
                }

                scanner.close();
        }
}

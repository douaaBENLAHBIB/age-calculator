import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Calendar;

public class CalculateurAge extends JFrame {
    private JTextField champNom, champPrenom, champAnnee;
    private JLabel labelResultat;

    public CalculateurAge() {
        setTitle("Calculer mon âge");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);


        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 206, 251));


        JLabel titre = new JLabel("Calculer mon âge", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 26));
        titre.setForeground(new Color(92, 52, 175));


        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 10, 15));
        fieldsPanel.setBackground(Color.WHITE);
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        champNom = new JTextField();
        champPrenom = new JTextField();
        champAnnee = new JTextField();

        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        champNom.setFont(fieldFont);
        champPrenom.setFont(fieldFont);
        champAnnee.setFont(fieldFont);


        champNom.setBorder(BorderFactory.createLineBorder(new Color(187, 100, 237), 1));
        champPrenom.setBorder(BorderFactory.createLineBorder(new Color(187, 100, 237), 1));
        champAnnee.setBorder(BorderFactory.createLineBorder(new Color(187, 100, 237), 1));

        fieldsPanel.add(new JLabel("Nom :"));
        fieldsPanel.add(champNom);
        fieldsPanel.add(new JLabel("Prénom :"));
        fieldsPanel.add(champPrenom);
        fieldsPanel.add(new JLabel("Année de naissance :"));
        fieldsPanel.add(champAnnee);


        JButton btnCalculer = new JButton("Calculer");
        btnCalculer.setFont(new Font("Arial", Font.BOLD, 16));
        btnCalculer.setBackground(new Color(131, 180, 70));
        btnCalculer.setForeground(Color.WHITE);
        btnCalculer.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        btnCalculer.addActionListener((ActionEvent e) -> calculerAge());


        labelResultat = new JLabel(" ", SwingConstants.CENTER);
        labelResultat.setFont(new Font("Arial", Font.BOLD, 18));
        labelResultat.setForeground(new Color(147, 103, 193));
        labelResultat.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));


        mainPanel.add(titre, BorderLayout.NORTH);
        mainPanel.add(fieldsPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(new Color(240, 248, 255));
        southPanel.add(btnCalculer, BorderLayout.NORTH);
        southPanel.add(labelResultat, BorderLayout.CENTER);

        mainPanel.add(southPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    private void calculerAge() {
        String nom = champNom.getText().trim();
        String prenom = champPrenom.getText().trim();
        String anneeText = champAnnee.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || anneeText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez remplir tous les champs !",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            int anneeNaissance = Integer.parseInt(anneeText);
            int anneeActuelle = Calendar.getInstance().get(Calendar.YEAR);

            if (anneeNaissance > anneeActuelle) {
                JOptionPane.showMessageDialog(this,
                        "Année de naissance invalide !\nDoit être inférieur à " + anneeActuelle,
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int age = anneeActuelle - anneeNaissance;
            String message = String.format("Bonjour %s %s, vous avez : %d ans",
                    nom.toUpperCase(),
                    prenom.toLowerCase(),
                    age);
            labelResultat.setText(message);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "L'année de naissance doit être un nombre !",
                    "Erreur de saisie",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculateurAge().setVisible(true);
        });
    }
}